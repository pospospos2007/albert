package com.zdcf.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class testHttp {
	
	public static String getZhiHuAirticleList() {
		
		 HttpClient client1 = new DefaultHttpClient();
		 
		 HttpGet get = new HttpGet("http://news-at.zhihu.com/api/4/news/latest");
		 
		 HttpResponse response;
		 
		 String result = null;
		try {
			response = client1.execute(get);
			
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 
		 
		 return result;
	}
}
