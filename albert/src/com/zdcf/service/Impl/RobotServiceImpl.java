package com.zdcf.service.Impl;

import java.io.IOException;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zdcf.mapper.Plugin;
import com.zdcf.service.RobotService;
import com.zdcf.tool.Aes;
import com.zdcf.tool.Md5;
import com.zdcf.tool.PostServer;

@Service
@Transactional
public class RobotServiceImpl implements  RobotService {

		
	@Autowired
	public Plugin plugin;
	
	@Override
	public String getAnswerFromRobot(String question,int userId) {
		//图灵网站上的secret
				String secret = "6a011592ee36f8ae";
				//图灵网站上的apiKey
				String apiKey = "3d7392cdc0d54822bf8252383331f8a5";
				String cmd = question;
				//待加密的json数据
				String data = "{\"key\":\""+apiKey+"\",\"info\":\""+cmd+"\",\"userid\":\""+userId+"\"}";
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
				String result = PostServer.SendPost(json.toString(), "http://www.tuling123.com/openapi/api");
				System.out.println(result);
				return result;
	}

	@Override
	public String getZhiHuAirticleList() {
		
		HttpClient client = new DefaultHttpClient();
		 
		 HttpGet get = new HttpGet("http://news-at.zhihu.com/api/4/news/latest");
		 
		 HttpResponse response;
		 
		 String result = null;
		try {
			response = client.execute(get);
			
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
//			System.out.println(result);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		 
		 return result;
	}
	
	
	//已废弃，由于知乎不再提供此接口
	@Override
	public String getZhiHuAirticleListBefore(String date) {
		
		HttpClient client = new DefaultHttpClient();
		 
		 HttpGet get = new HttpGet("http://news.at.zhihu.com/api/4/news/before/"+date);
		 
		 HttpResponse response;
		 
		 String result = null;
		try {
			response = client.execute(get);
			
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
//			System.out.println(result);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		 
		 return result;
	}

	@Override
	public String getZhiHuAirticleDetail(String id) {
		
		HttpClient client = new DefaultHttpClient();
		 
		 HttpGet get = new HttpGet("http://news-at.zhihu.com/api/4/news/"+id);
		 
		 HttpResponse response;
		 
		 String result = null;
		try {
			response = client.execute(get);
			
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
//			System.out.println(result);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	

	

}
