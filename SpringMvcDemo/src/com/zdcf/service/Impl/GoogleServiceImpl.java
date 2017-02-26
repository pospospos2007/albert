package com.zdcf.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdcf.base.Constants;
import com.zdcf.model.GoogleSearchResult;
import com.zdcf.service.BaseService;
import com.zdcf.service.GoogleService;
import com.zdcf.tool.ProxyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class GoogleServiceImpl extends BaseService implements GoogleService {


	public List<GoogleSearchResult> search(String searchKey) throws ClientProtocolException, IOException{
			List<GoogleSearchResult> list= new ArrayList<>();
			StringBuffer sb = new StringBuffer(); 
			HttpClient client = ProxyUtil.getHttpClient();
			 HttpGet httpGet = new HttpGet(Constants.GOOGLE_SEARCH_URL+searchKey);
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
				  list.add(googleSearchResult);
//				  System.out.println(googleSearchResult.toString());
			  }
			  return list;
	}
		
}
