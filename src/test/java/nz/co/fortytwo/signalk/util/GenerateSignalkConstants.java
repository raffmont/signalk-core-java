/*
 * 
 * Copyright (C) 2012-2014 R T Huitema. All Rights Reserved.
 * Web: www.42.co.nz
 * Email: robert@42.co.nz
 * Author: R T Huitema
 * 
 * This file is part of the signalk-server-java project
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
package nz.co.fortytwo.signalk.util;


import static nz.co.fortytwo.signalk.util.SignalKConstants.dot;
import static nz.co.fortytwo.signalk.util.SignalKConstants.pgn;
import static nz.co.fortytwo.signalk.util.SignalKConstants.source;
import static nz.co.fortytwo.signalk.util.SignalKConstants.sourceRef;
import static nz.co.fortytwo.signalk.util.SignalKConstants.timestamp;
import static nz.co.fortytwo.signalk.util.SignalKConstants.type;
import static nz.co.fortytwo.signalk.util.SignalKConstants.value;
import static nz.co.fortytwo.signalk.util.SignalKConstants.values;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mjson.Json;

public class GenerateSignalkConstants {

	//private  defMap;


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	//@Ignore
	public void test() throws Exception {
		
		createSignalkData("./../", "motu", "", true,true);
	}
	
	public static void createSignalkData(String schemaDir, String uuid, String filter, boolean skipAttr, boolean skipMeta ) throws IOException{
		File schemaRoot = new File(schemaDir);
		createSignalkData(schemaRoot, uuid, filter, skipAttr, skipMeta);
	}
	public static void createSignalkData(File schemaRoot, String uuid, String filter, boolean skipAttr, boolean skipMeta ) throws IOException{
		
		//add definitions
		File[] definitions = new File[]{
				new File(schemaRoot,"specification/schemas/definitions.json"),
				//new File(schemaRoot,"specification/schemas/external/geojson/geometry.json"),
				new File(schemaRoot,"specification/schemas/groups/environment.json"),
				new File(schemaRoot,"specification/schemas/groups/tanks.json"),
				new File(schemaRoot, "specification/schemas/groups/electrical.json")};
		Map<String, Json> defMap=addDefinitions(definitions);
		
		//load schema
		File schemaFile = new File("./../specification/schemas/signalk.json");
		String schemaString = FileUtils.readFileToString(schemaFile);
		Json schemaJson = Json.read(schemaString);
		
		ConcurrentSkipListMap<String, Object> keyList = new ConcurrentSkipListMap<String,Object>();
		recurse(schemaJson, defMap,"", schemaFile, keyList, skipAttr, skipMeta);
		
		ConcurrentSkipListMap<String, Object> outList = new ConcurrentSkipListMap<String,Object>();
		if(StringUtils.isNotBlank(uuid)){
			for(Entry<String, Object> entry: keyList.entrySet()){
				if(entry.getKey().contains(filter)){
					String key = entry.getKey();
					//then replace it;
					key = key.replaceFirst("vessels\\..*?\\.", "vessels\\."+uuid+"\\.");
					outList.put(key, entry.getValue());
				}
			}
		}
		// output json

		System.out.println("\nJava:");
		for(Entry<String, Object> entry: outList.entrySet()){
			if(entry.getKey().contains(filter)){
				String key = entry.getKey();
				key=key.replace("vessels.motu.","");
				String val = key;
				val=val.replace(".id.", ".*.");
				key=key.replace(".","_");
				key=key.replace("navigation","nav");
				key=key.replace("environment","env");
				System.out.println("public static final String "+key +"=\""+val+"\";");
			}
		}
		
		
	}


	private static Map<String, Json> addDefinitions(File[] definitions) throws IOException {
		Map<String,Json> definitionsMap = new HashMap<>();
		for(File def: definitions){
			String definitionsString = FileUtils.readFileToString(def);
			Json definitionsJson = Json.read(definitionsString);
			definitionsMap.putAll(definitionsJson.at("definitions").asJsonMap());
		}
		return definitionsMap;
	}

	private static void recurse(Json schemaJson, Map<String, Json> defMap, String pad, File schemaFile, Map<String, Object>  keyList, boolean skipAttr, boolean skipMeta) throws IOException {
		
	
		if(schemaJson.at("$ref")!=null){
			String src = schemaJson.at("$ref").asString();
			//System.out.println(pad + "ref:" + src);
			if(src.contains("definitions.json#")||src.contains("#/definitions/")){
				//System.out.println("   Getting "+src);
				//../definitions.json#/definitions/timestamp
				recurseDefs(defMap, src,pad,schemaFile,keyList,skipAttr,skipMeta);
				return;
			}
			src=src.replace('#',' ').trim();
			File next = new File(schemaFile.getParentFile(),src);
			
			if(next.exists()){
				Json srcJson = Json.read(FileUtils.readFileToString(next));
				recurse(srcJson, defMap,pad, schemaFile, keyList,skipAttr,skipMeta);
			}else{
				System.out.println("   err:Cant find "+next.getAbsolutePath());
			}
			return;
		}
		if(schemaJson.at("allOf")!=null){
			List<Json> list = schemaJson.at("allOf").asJsonList();
			//System.out.println("   Recursing allOf:"+list);
			for(Json j:list){
				//System.out.println("     Recurse j:"+j);
				recurse(j,defMap,pad,schemaFile,keyList,skipAttr,skipMeta);
			}
			
			
		}
		
		if(schemaJson.at("anyOf")!=null){
			List<Json> list = schemaJson.at("anyOf").asJsonList();
			for(Json j:list){
				//System.out.println("     Recurse j:"+j);
				recurse(j,defMap,pad,schemaFile,keyList,skipAttr,skipMeta);
			}
			
			
		}
		Json props = schemaJson.at("properties");
		if (props != null) {
			Map<String, Json> map = props.asJsonMap();
			//System.out.println("properties="+map.keySet());
			for (String e : map.keySet()) {
				//skip meta and _attr?
				if (skipMeta && e.equals("meta")){
					continue;
				}
				if (skipAttr && e.equals("_attr")){
					continue;
				}
				if (e.equals(timestamp)){
					//keyList.put(pad +  sanitiseKey(e), Util.getIsoTimeString());
					continue;
				}
				if (e.equals(sourceRef) || e.equals(source)||e.equals(pgn)||e.equals(value)||e.equals(values)||e.equals("sentence")){
					//keyList.put(pad +  source+dot+type, "testType");
					//keyList.put(pad +  source+".label", "testLabel");
					continue;
				}
				//System.out.println("properties key="+pad +  e);
				
				keyList.put(pad +  sanitiseKey(e), "dummy");
					
				if (props.at(e).isObject()) {
					//System.out.println("   Recurse:"+e);
					recurse(props.at(e), defMap,pad +sanitiseKey(e)+"." , schemaFile, keyList,skipAttr,skipMeta);
				}

			}
		}
		/*Json addProps = schemaJson.at("additionalProperties");
		if (addProps != null) {

			Map<String, Json> map = addProps.asJsonMap();
			for (String e : map.keySet()) {
				if (e.equals("timestamp"))
					continue;
				if (e.equals("source"))
					continue;
				
				keyList.add(pad +  e);
				if (addProps.at(e).isObject()) {
					recurse(addProps.at(e), pad +e+"." , schemaFile, keyList);
				}

			}
		}*/
		Json patternProps = schemaJson.at("patternProperties");
		if (patternProps != null) {
			Map<String, Json> map = patternProps.asJsonMap();
			for (String e : map.keySet()) {
				if (e.equals("timestamp")){
					keyList.put(pad +  sanitiseKey(e), Util.getIsoTimeString());
					continue;
				}
				if (e.equals("source")){
					keyList.put(pad +  sanitiseKey(e)+dot+type, "testType");
					keyList.put(pad +  sanitiseKey(e)+".label", "testLabel");
					continue;
				}
				
				//System.out.println("patternProperties key="+pad +  sanitiseKey(e));
				Object value = getValueForType(map.get(e).asJsonMap());
				if(value!=null){
					keyList.put(pad +  sanitiseKey(e), value);
					//System.out.println("    key="+pad +  sanitiseKey(e)+"="+value);
				}
				if (patternProps.at(e).isObject()) {
					recurse(patternProps.at(e),defMap, pad +sanitiseKey(e)+".", schemaFile, keyList,skipAttr,skipMeta);
				}

			}
		}
		
	}

	private static String sanitiseKey(String e) {
		//String uuid = UUID.randomUUID().toString();
		if(e.equals("(^urn:mrn:(imo|signalk):(mmsi:[2-7][0-9]{8,8}|uuid:[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-4[0-9A-Fa-f]{3}-[89ABab][0-9A-Fa-f]{3}-[0-9A-Fa-f]{12}))|^(http(s?):.*|mailto:.*|tel:(\\+?)[0-9]{4,})$"))return "id";
		if(e.equals("^urn:mrn:signalk:uuid:[0-9A-Fa-f]{8}-[0-9A-Fa-f]{4}-4[0-9A-Fa-f]{3}-[89ABab][0-9A-Fa-f]{3}-[0-9A-Fa-f]{12}$"))return "id";
		if(e.equals("(^source$)"))return "source";
		if(e.equals("(^message$)"))return "message";
		if(e.equals("(^method$)"))return "method";
		if(e.equals("(^timestamp$)"))return "timestamp";
		if(e.equals("(^state$)"))return "state";
		if(e.equals("(^[A-Za-z0-9]+$)"))return "id";
		if(e.equals("(^[A-Za-z0-9_-]{8,}$)"))return "id";
		if(e.equals(".*"))return "id";
		if(e.equals("(^[A-Za-z0-9-]+$)"))return "id";
		if(e.equals("(^[A-Za-z0-9_-]+$)"))return "id";
		if(e.equals("(single)|([A-C])"))return "id";
		if(e.equals("(^[a-zA-Z0-9]+$)"))return "id";
		return e;
	}

	private static void recurseDefs(Map<String, Json> defMap, String src, String pad, File schemaFile,
			Map<String, Object> keyList, boolean skipAttr, boolean skipMeta) throws IOException {
		int pos = src.lastIndexOf("/")+1;
		//System.out.println("   Lookup "+src.substring(pos)+" from "+defMap);
		Json def = defMap.get(src.substring(pos));
		//System.out.println("   Found "+def);
		try{
			recurse(def, defMap, pad, schemaFile, keyList,skipAttr,skipMeta);
		}catch(NullPointerException ne){
			System.out.println("   Null Error on  "+pad+"/"+src);
		}
		return;
		
	}

	private static Object getValueForType(Map<String, Json> map) {
		
		//if(map.containsKey("example"))return map.get("example").getValue();
		//System.out.println("   map="+map);
		
		String type = null;
		try{
			if(map.containsKey("type")){
				Json json = map.get("type");
				if(json.isArray()){
					type=json.asJsonList().get(0).asString();
				}else{
					type=json.asString();
				}
			}
		}catch(UnsupportedOperationException use){
			System.out.println("   No type in map="+map);
		}
		if("object".equals(type))return null;
		if("string".equals(type)){
			if(map.containsKey("enum")){
				Json eNum = map.get("enum");
				return eNum.asJsonList().get(0).asString();
			}
			return "ipsum";
		}
		if("boolean".equals(type))return true;
		if("integer".equals(type))return Math.round(Math.random()*100);
		if("number".equals(type))return Math.random()*100;
		if("mmsi".equals(type))return "398765432";
		if("array".equals(type)){
			//map={minItems=2, description="A single position, in x,y order (Lon, Lat)", items=[{"type":"number"},{"type":"number"}], additionalItems=false, type="array"}
			Json items = map.get("items");
			if(items==null)return "[]";
			StringBuffer val = new StringBuffer("[");
			if(items.isArray()){
				for(Json item:items.asJsonList()){
					val.append(getValueForType(item.asJsonMap()));
					val.append(",");
				}
				val.delete(val.length()-1, val.length());
			}else{
				val.append(getValueForType(items.asJsonMap()));
			}
			
			return val.append("]").toString();
		}
		
		return type;
	}

	

}
