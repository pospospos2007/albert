package com.zdcf.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zdcf.tool.Aes;
import com.zdcf.tool.Md5;
import com.zdcf.tool.PostServer;

/**
 * 加密请求测试类
 * @author 图灵机器人
 *
 */
public class AesTest {
	
	@Test
	public void testAes(){
		//图灵网站上的secret
		String secret = "6a011592ee36f8ae";
		//图灵网站上的apiKey
		String apiKey = "3d7392cdc0d54822bf8252383331f8a5";
		String cmd = "明天从北京到南昌的飞机";//测试用例
		//待加密的json数据
		String data = "{\"key\":\""+apiKey+"\",\"info\":\""+cmd+"\"}";
		//获取时间戳
		String timestamp = String.valueOf(System.currentTimeMillis());
		
		//生成密钥
		String keyParam = secret+timestamp+apiKey;
		String key = Md5.MD5(keyParam);
		
		//加密
		Aes mc = new Aes(key);
		data = mc.encrypt(data);		
		
		//封装请求参数
		JSONObject json = new JSONObject();
		json.put("key", apiKey);
		json.put("timestamp", timestamp);
		json.put("data", data);
		//请求图灵api
		JSONObject jsonObject = null;
		String result = PostServer.SendPost(json.toString(), "http://www.tuling123.com/openapi/api");
//		jsonObject = JSONObject.fromObject(result);
//		System.out.println(jsonObject.get("text").toString());
		System.out.println(result);
	}
	
//	Iterator Map
	
	
}