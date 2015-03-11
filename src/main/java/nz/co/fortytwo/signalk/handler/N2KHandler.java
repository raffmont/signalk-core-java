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

import static nz.co.fortytwo.signalk.util.JsonConstants.SELF;
import static nz.co.fortytwo.signalk.util.JsonConstants.VESSELS;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collection;

import mjson.Json;
import nz.co.fortytwo.signalk.model.SignalKModel;
import nz.co.fortytwo.signalk.model.impl.SignalKModelFactory;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

/**
 * Updates the convert n2k json to signalk tree
 * 
 * @author robert
 * 
 */
public class N2KHandler {

	private static final String FILTER = "filter";
	private static final String NODE = "node";
	private static final String SOURCE = "source";
	private static final String self = VESSELS + "." + SELF + ".";
	private static Logger logger = Logger.getLogger(N2KHandler.class);
	// private static DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
	private NumberFormat numberFormat = NumberFormat.getInstance();
	private Json mappings = null;
	private JsonPath pgnPath = JsonPath.compile("$.pgn");
	private Multimap<String, N2KHolder> nodeMap = HashMultimap.create();

	public N2KHandler() {
		File mappingFile = new File("./conf/n2kMappings.json");
		try {
			mappings = Json.read(FileUtils.readFileToString(mappingFile));
			for (String pgn : mappings.asJsonMap().keySet()) {
				Json mappingArray = mappings.at(pgn);
				logger.debug("Array="+mappingArray);
				for (Json j : mappingArray.asJsonList()) {
					logger.debug("Json="+j);
					String filter = "$";
					if(j.at(FILTER)!=null && !j.at(FILTER).isNull()){
						filter=j.at(FILTER).toString();
					}
					JsonPath compiledPath = JsonPath.compile(filter + "." + j.at(SOURCE));
					String node = j.at(NODE).asString();
					nodeMap.put(pgn, new N2KHolder(node, compiledPath));
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
	}

	

	/*
	 * {
	 * "timestamp": "2013-10-08-15:47:28.264",
	 * "prio": "2",
	 * "src": "2",
	 * "dst": "255",
	 * "pgn": "129025",
	 * "description": "Position, Rapid Update",
	 * "fields": {
	 * "Latitude": "60.1445540",
	 * "Longitude": "24.7921348"
	 * }
	 * }
	 */

	// @Override
	/**
	 * 
	 * Converts a Json n2k message (from CANboat analyser) to a signalK json message
	 * @param n2kmsg
	 * @return
	 */
	public SignalKModel handle(String n2kmsg) {
		// get the pgn value
		DocumentContext n2k = JsonPath.parse(n2kmsg);
		String pgn = n2k.read(pgnPath);
		if (logger.isDebugEnabled())
			logger.debug("processing n2k pgn " + pgn);
		if (nodeMap.containsKey(pgn)) {
			// process it, mappings is n2kMapping.json as a json object
			Collection<N2KHolder> entries = nodeMap.get(pgn);

			// make a dummy signalk object
			SignalKModel temp = SignalKModelFactory.getCleanInstance();
			// mapping contains an array
			for (N2KHolder entry : entries) {
				Object var = n2k.read(entry.path);
				// put in signalk tree
				temp.put(self + entry.node, resolve(var));
			}
			if (logger.isDebugEnabled())
				logger.debug("N2KHandler output  " + temp);
			return temp;
		}
		return null;

	}

	private Object resolve(Object var) {
		if (var == null || !(var instanceof String))
			return var;
		try {
			return numberFormat.parse((String)var);
		} catch (ParseException e) {
			return var;
		}

	}

	class N2KHolder {
		String node = null;
		JsonPath path = null;

		public N2KHolder(String node, JsonPath path) {
			this.node = node;
			this.path = path;
		}
	}

}
