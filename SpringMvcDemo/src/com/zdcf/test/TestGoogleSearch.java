package com.zdcf.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import com.zdcf.base.Constants;
import com.zdcf.model.GoogleSearchResult;
import com.zdcf.tool.ProxyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestGoogleSearch {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		StringBuffer sb = new StringBuffer(); 
		HttpClient client = ProxyUtil.getHttpClient();
		 HttpGet httpGet = new HttpGet(Constants.GOOGLE_SEARCH_URL+"我");
		 HttpResponse response = client.execute(httpGet);
		 
		 Header[] headers = response.getAllHeaders();
		 
		 if(200!=response.getStatusLine().getStatusCode()){
			  System.out.println("无法访问");
		 }
		 
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
		 
		 
		 
//		 System.out.println(sb.toString());
		  JSONArray jsonArray = null;
		  JSONObject jsonObject=null;
		  jsonArray = JSONObject.fromObject(sb.toString()).getJSONArray("items");
		  
		  GoogleSearchResult googleSearchResult =new GoogleSearchResult();
		  
		  for(int i=0,length=jsonArray.size();i<length;i++){
			  jsonObject = jsonArray.getJSONObject(i);
			  googleSearchResult.setHtmlTitle(jsonObject.getString("htmlTitle"));
			  googleSearchResult.setLink(jsonObject.getString("link"));
			  googleSearchResult.setLink(jsonObject.getString("htmlSnippet"));
			  if(jsonObject.containsKey("pagemap")&&jsonObject.getJSONObject("pagemap").containsKey("cse_thumbnail")){
				  googleSearchResult.setSrc(jsonObject.getJSONObject("pagemap").getJSONArray("cse_thumbnail").getJSONObject(0).getString("src"));
				  googleSearchResult.setWidth(jsonObject.getJSONObject("pagemap").getJSONArray("cse_thumbnail").getJSONObject(0).getString("width"));
				  googleSearchResult.setHeight(jsonObject.getJSONObject("pagemap").getJSONArray("cse_thumbnail").getJSONObject(0).getString("height"));
			  }
			  System.out.println(googleSearchResult.toString());
		  }
		 
	}
	
	 
}
