/*
 * 
 * Copyright (C) 2012-2014 R T Huitema. All Rights Reserved.
 * Web: www.42.co.nz
 * Email: robert@42.co.nz
 * Author: R T Huitema
 * 
 * This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING THE
 * WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nz.co.fortytwo.signalk.handler;

import static nz.co.fortytwo.signalk.util.SignalKConstants.CONTEXT;
import static nz.co.fortytwo.signalk.util.SignalKConstants.PATH;
import static nz.co.fortytwo.signalk.util.SignalKConstants.UPDATES;
import static nz.co.fortytwo.signalk.util.SignalKConstants.attr;
import static nz.co.fortytwo.signalk.util.SignalKConstants.meta;
import static nz.co.fortytwo.signalk.util.SignalKConstants.source;
import static nz.co.fortytwo.signalk.util.SignalKConstants.sourceRef;
import static nz.co.fortytwo.signalk.util.SignalKConstants.timestamp;
import static nz.co.fortytwo.signalk.util.SignalKConstants.vessels;

import java.util.ArrayList;
import java.util.List;

import mjson.Json;
import nz.co.fortytwo.signalk.util.SignalKConstants;

import org.apache.logging.log4j.LogManager; import org.apache.logging.log4j.Logger;

/**
 * Convert the full format to delta format
 * 
 * @author robert
 * 
 */
public class FullToDeltaConverter {

	private static Logger logger = LogManager.getLogger(FullToDeltaConverter.class);


	/*
	 * {
	 * "context": "vessels.motu.navigation",
	 * "updates":[
	 * {
	 * "source": {
	 * "device" : "/dev/actisense",
	 * "timestamp":"2014-08-15-16:00:00.081",
	 * "src":"115",
	 * "pgn":"128267"
	 * },
	 * "values": [
	 * { "path": "courseOverGroundTrue","value": 3.0176 },
	 * { "path": "speedOverGround","value": 3.85 }
	 * ]
	 * },
	 * {
	 * "source": {
	 * "device" : "/dev/actisense",
	 * "timestamp":"2014-08-15-16:00:00.081",
	 * "src":"115",
	 * "pgn":"128267"
	 * },
	 * "values": [
	 * { "path": "courseOverGroundTrue","value": 3.0176 },
	 * { "path": "speedOverGround","value": 3.85 }
	 * ]
	 * }
	 * ]
	 * 
	 * }
	 */

	
	/**
	 * Converts the full tree format to the signalk delta format
	 * 
	 * @param node
	 * @return
	 */
	public List<Json> handle(Json node) {
		// avoid full signalk syntax
		List<Json> list = new ArrayList<Json>();
		if (node.has(CONTEXT)){
			list.add(node);
			return list;
		}
		// deal with diff format
		if (node.has(vessels)) {
			if(logger.isDebugEnabled())if(logger.isDebugEnabled())logger.debug("processing full format  " + node);
			// find the first branch that splits
			//Json ctx = getContext();
			for(Json vessel : node.at(vessels).asJsonMap().values()){
				String context = vessel.getPath();
				// process it

				// add values
				Json updates = Json.array();
				getEntries(updates, Json.array(), null, null, vessel, context.length() + 1);

				if (updates.asList().size() > 0){
	
					Json delta = Json.object();
					delta.set(CONTEXT, context);
					delta.set(UPDATES, updates);
					list.add(delta);
				}
			}
			
		}
		if(list.size()==0)return null;
		// misc types
		return list;
	}

	/**
	 * Find the first node with more than one child.
	 * 
	 * @param node
	 * @return
	 */
	protected Json getContext(Json node) {
		
		// look down the tree until we get more than one branch, thats the context
		if ( node.asJsonMap().size() > 1){
			if(logger.isDebugEnabled())logger.debug("Context is :"+node.asJsonMap().size()+", "+ node);
			return node;
		}
		for (Json j : node.asJsonMap().values()) {
			
			if(j.isPrimitive()){
				return node;
			}
			if(logger.isDebugEnabled())logger.debug("Context recurse :"+ j);
			return getContext(j);
		}
		return node;
	}

	private void getEntries(Json updates, Json values, String jsSrcRef, String tsRef, Json j, int prefix) {
		if (!j.isObject())
			return;
		boolean added = false;
		Json entry = Json.object();
		
		for (Json js : j.asJsonMap().values()) {
			if(logger.isDebugEnabled())logger.debug("Process : "+js+", is primitive:"+js.isPrimitive());
			if (js == null)
				continue;
			
			if (js.isObject() && (js.has(sourceRef) || js.has(source))) {
				if(logger.isDebugEnabled())logger.debug("Process source : "+js);
				Json jsSrc = js.at(sourceRef);
				if(jsSrc==null)jsSrc = js.at(source);
				Json ts = js.at(timestamp);
				//has it changed
				if(jsSrcRef==null || !jsSrcRef.equals(jsSrc.toString())
						||tsRef==null || !tsRef.equals(ts.toString())){
						
					//existing entry
					if(entry.asJsonMap().size()>0){
						updates.add(entry);
					}
					jsSrcRef=jsSrc.toString();
					tsRef=ts.toString();
					//new entry
					entry=Json.object();
					values = Json.array();
					entry.set(SignalKConstants.values, values);
					entry.set(source, jsSrc.getValue());
					if(js.has(timestamp)){
						entry.set(timestamp, js.at(timestamp));
					}
				}
				
			}
			
			if (js.isArray()){
				if(logger.isDebugEnabled())logger.debug("Process array : "+js);
				String path = js.getPath().substring(prefix);
				Json value = Json.object();
				value.set(PATH, path);
				value.set(SignalKConstants.value, js);
				values.add(value);
				added=true;
				continue;
			}
			if (js.isObject() && js.has(SignalKConstants.value)){
				if(logger.isDebugEnabled())logger.debug("Process value : "+js);
				String path = js.getPath().substring(prefix);
				Json value = Json.object();
				value.set(PATH, path);
				value.set(SignalKConstants.value, js.at(SignalKConstants.value));
				
				if (js.has(meta)){
					if(logger.isDebugEnabled())logger.debug("Process meta : "+js);
					value.set(meta, js.at(meta));
					values.add(value);
					added=true;
				}
				values.add(value);
				added=true;
				continue;
			}
			
			if (js.isObject() && (js.has(sourceRef)|| js.has(source)) && !js.has(SignalKConstants.value)) {
				if(logger.isDebugEnabled())logger.debug("Process value object : "+js);
				String path = js.getPath().substring(prefix);
				Json value = Json.object();
				value.set(PATH, path);
				js.delAt(source);
				js.delAt(sourceRef);
				js.delAt(timestamp);
				js.delAt(attr);
				//js.delAt(meta);
				value.set(SignalKConstants.value, js);
				if (js.has(meta)){
					if(logger.isDebugEnabled())logger.debug("Process meta : "+js);
					value.set(meta, js.at(meta));
					//values.add(value);
				}
				values.add(value);
				added=true;
				continue;
			}
			if (js.isPrimitive()) {
				if(logger.isDebugEnabled()){
					logger.debug("Process primitive : "+js+" at "+js.getPath()+", prefix:"+prefix);
				}
				String path = js.getPath().substring(prefix);
				Json value = Json.object();
				value.set(PATH, path);
				value.set(SignalKConstants.value, js);
				values.add(value);
				added=true;
				continue;
			}
			if (js.isObject()) {
				if(js.getParentKey().equals(timestamp))continue;
				if(js.getParentKey().equals(source))continue;
				
				if(js.getParentKey().equals(SignalKConstants.value))continue;
				if(logger.isDebugEnabled())logger.debug("Recurse : "+js);
				getEntries(updates, values, jsSrcRef, tsRef, js, prefix);
			}
		}
		if(entry.asJsonMap().size()>0){
			if(logger.isDebugEnabled())logger.debug("Clean up last values array : "+values);
			updates.add(entry);
		}else{
			if(added){
				entry.set(SignalKConstants.values, values);
				if(logger.isDebugEnabled())logger.debug("Add entry : "+entry);
				updates.add(entry);
			}
		}

	}

}
