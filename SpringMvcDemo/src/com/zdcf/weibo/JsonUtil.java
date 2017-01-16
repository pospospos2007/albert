/**
 * 
 */
package com.zdcf.weibo;

import java.io.StringReader;

import javax.json.*;

/**
 * @author cheetyan
 * 
 */
public class JsonUtil {
	public static JsonObject JsonObject(String s){
		//System.out.println(s);
		JsonReader jsonReader = Json.createReader(new StringReader(s));
		JsonObject json = jsonReader.readObject();
		jsonReader.close();
		return json;
	}
	public static JsonArray JsonArray(String s){
		JsonReader jsonReader = Json.createReader(new StringReader(s));
		JsonArray json = jsonReader.readArray();
		jsonReader.close();
		return json;
	}
	public static String getString(JsonObject json,String key){
		if(json.containsKey(key)){
			return json.getString(key);
		}else{
			return null;
		}
	}
	public static Long getLong(JsonObject json,String key){
		if(json.containsKey(key)){
			return json.getJsonNumber(key).longValue();
		}else{
			return null;
		}
	}
}
