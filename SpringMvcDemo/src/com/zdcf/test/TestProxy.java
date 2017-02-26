package com.zdcf.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestProxy {
	public static void main(String args[])
	{
	 StringBuffer sb = new StringBuffer();
	 //创建HttpClient实例
	 HttpClient client = getHttpClient();
	 //创建httpGet
//	 https://developers.google.com/custom-search/
//	 https://cse.google.com/cse/publicurl?cx=007821717631179546661:42aw6xv7bxe&q=%queryExpression%&searchType=image&start=%start%&num=%num%
//	 https://www.googleapis.com/customsearch/v1?cx={APP
//		 ID}&key={API Key}&q={key word}
	 HttpGet httpGet = new HttpGet("https://www.googleapis.com/customsearch/v1?cx=007821717631179546661:42aw6xv7bxe&key=AIzaSyD440A4WVC9ozZgcM518QJJfxAhoq9U8zY&q=人");
	 
	 //执行
	 try {
	  HttpResponse response = client.execute(httpGet);
	  
	  HttpEntity entry = response.getEntity();
	  
	  if(entry != null)
	  {
	   InputStreamReader is = new InputStreamReader(entry.getContent());
	   BufferedReader br = new BufferedReader(is);
	   String str = null;
	   while((str = br.readLine()) != null)
	   {
	    sb.append(str.trim());
	   }
	   br.close();
	  }
	  
	 } catch (ClientProtocolException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
	 } catch (IOException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
	 }
	 System.out.println(sb.toString());
	}

	//设置代理

	public static HttpClient getHttpClient() {

	 DefaultHttpClient httpClient = new DefaultHttpClient();
	 String proxyHost = "127.0.0.1";
	 int proxyPort = 1080;
//	 String userName = "111";
//	 String password = "111";
//	 httpClient.getCredentialsProvider().setCredentials(
//	   new AuthScope(proxyHost, proxyPort),
//	   new UsernamePasswordCredentials(userName, password));
	 HttpHost proxy = new HttpHost(proxyHost,proxyPort);
	 httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
	 return httpClient;
	}

//	导入：commons-logging-1.1.jar，httpclient-4.0-beta2.jar ，httpcore-4.1-alpha1.jar 和 commons-codec-1.4.jar架包

}
