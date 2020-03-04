/**
 * 
 */
package com.zdcf.weibo;

import java.io.IOException;

import javax.annotation.Resource;
import javax.json.JsonObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.zdcf.model.WeiboStatus;
import com.zdcf.weibo.StatusWapper;
import com.zdcf.weibo.WeiboException;
import com.zdcf.weibo.JsonUtil;
import com.zdcf.weibo.SinaTasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cheetyan
 * 
 */
public class Timeline {
	private static Logger logger = LoggerFactory.getLogger(Timeline.class.getName());
	private SinaTasks sinaTasks;
	private String url;
	
	public Timeline(){
		
		this.sinaTasks =new SinaTasks();
		init();
	}

	public Timeline(SinaTasks sinaTasks) {
		this.sinaTasks = sinaTasks;
		init();
	}
	
	private void init() {
		url = sinaTasks.getBaseURL() + "statuses/public_timeline.json" + "?access_token=" + sinaTasks.getAccesstoken();
	}

	public StatusWapper getPublicTimeline() throws WeiboException, IOException {
		return WeiboStatus.constructWapperStatus(get(url));
	}

	public StatusWapper getPublicTimeline(int count, int baseApp) throws WeiboException, IOException {
		baseApp = 0;
		return WeiboStatus.constructWapperStatus(get(url + "&count=" + String.valueOf(count) + "&base_app=" + String.valueOf(baseApp)));
	}

	public StatusWapper getUserTimelineByName(String screen_name,com.zdcf.weibo.Paging page, Integer base_app, Integer feature) throws WeiboException {
		throw new UnsupportedOperationException("not implementd yet");
	}

	public StatusWapper getUserTimelineByName(String username) {
		throw new UnsupportedOperationException("not implemented yet");
	}

	private static JsonObject get(String url) throws IOException {
		logger.info("start get url : " + url);
		HttpGet get = new HttpGet(url);
		HttpClient client = new DefaultHttpClient();
		HttpResponse response;  
		String s="";
		try{
			response = client.execute(get);
			
			s = EntityUtils.toString(response.getEntity(), "UTF-8");
		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String s = HttpClientPoolUtil.execute(get);
		return JsonUtil.JsonObject(s);
	}

	public SinaTasks getSinaTasks() {
		return sinaTasks;
	}

	public void setSinaTasks(SinaTasks sinaTasks) {
		this.sinaTasks = sinaTasks;
	}
	
}
