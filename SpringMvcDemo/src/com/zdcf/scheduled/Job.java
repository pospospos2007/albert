package com.zdcf.scheduled;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zdcf.base.Constants;
import com.zdcf.dto.ZhihuDTO;
import com.zdcf.mapper.Plugin;
import com.zdcf.model.TwitterMedia;
import com.zdcf.model.TwitterPost;
import com.zdcf.model.TwitterUser;
import com.zdcf.model.Zhihu;
import com.zdcf.service.MessageService;
import com.zdcf.service.MovieService;
import com.zdcf.service.RobotService;
import com.zdcf.service.TwitterMediaService;
import com.zdcf.service.TwitterPostService;
import com.zdcf.service.TwitterUserService;
import com.zdcf.tool.PageInfo;
import com.zdcf.tool.ProxyUtil;
import com.zdcf.tool.StringUtil;
import com.zdcf.weibo.Config;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;


@Component("Job")
@Transactional
public class Job {
	
	private static final Log log = LogFactory
			.getLog(Job.class);
	
	@Autowired
	private RobotService robotService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private TwitterPostService twitterPostService;
	
	@Autowired
	private TwitterMediaService twitterMediaService;
	
	@Autowired
	private TwitterUserService twitterUserService;
	
	@Autowired
	private Plugin plugin;
	
//	@Scheduled(cron = "1 0/1 * * * ? ")
	@Scheduled(cron = "0 0 8,20 * * ? ")
	public void execute() {
		
		JSONObject jsonObject = null;
		
		String result = robotService.getZhiHuAirticleList();
		
		jsonObject = JSONObject.fromObject(result);
		
		List<Map<String,Object>>  stories = (List<Map<String, Object>>) jsonObject.get("stories");
		
		for(int i=0;i<stories.size();i++){
			
			Zhihu  zhihuAirticle = new Zhihu();
			
			zhihuAirticle.setTitle(stories.get(i).get("title").toString().replaceAll("\\\\", "/"));
			
			log.info("插入："+zhihuAirticle.getTitle());
			
			int id = Integer.parseInt(stories.get(i).get("id").toString());
			
			zhihuAirticle.setId(id);
			
			ZhihuDTO zhihu = messageService.getZhihuDetailById(id);
			
			if(null!=zhihu){
				continue;
			}
			
			JSONObject detailObject = null;
			
			detailObject =  JSONObject.fromObject(robotService.getZhiHuAirticleDetail(stories.get(i).get("id").toString()));
			
			String content = detailObject.get("body").toString().replaceAll("\\\\", "/");

			zhihuAirticle.setCss(detailObject.get("css").toString().replaceAll("\\\\", "/").replace("\"", "").replace("[", "").replace("]", ""));
			
			zhihuAirticle.setJs(detailObject.get("js").toString().replaceAll("\\\\", "/").replace("\"", "").replace("[", "").replace("]", ""));
			
			zhihuAirticle.setImages(detailObject.get("image").toString().replaceAll("\\\\", "/"));
			
			zhihuAirticle.setContent(content);
			
			
			try {
				messageService.addZhihuAirticle(zhihuAirticle);
			} catch (Exception e) {
				log.error("无法插入此数据");
			}
			
			
		}
		
	}
	
	@Test
//	@Scheduled(cron = "1 0/1 * * * ? ")
	public void addWeibo(){
		Config config =new Config();
		config.setPlugin(plugin);
		config.getAllThreadsRun();
//		HttpClientPoolUtil.shutDown();
//		ctx.close();
	}
	
//	@Scheduled(cron = "1 0/1 * * * ? ")
	@Test
	public void addTwitter() throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException{
	  OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
				Constants.ConsumerKey,
				Constants.ConsumerSecret);
	  consumer.setTokenWithSecret(Constants.AccessToken, Constants.AccessSecret);
	  StringBuffer sb = new StringBuffer();
	  HttpClient client = ProxyUtil.getHttpClient();
//	  https://api.twitter.com/1.1/statuses/home_timeline.json?cursor=-1&count=200   //我的主页
//	  https://api.twitter.com/1.1/statuses/user_timeline.json?count=200&screen_name=YouTube  //某个用户时间线
//	  https://api.twitter.com/1.1/statuses/mentions_timeline.json?count=10  //我的回复
//	  https://api.twitter.com/1.1/search/tweets.json?q=pospospos2007&count=100  //搜索
//	  HttpGet httpGet = new HttpGet(Constants.TWITTER_MY_TIME_LINE+"?cursor=-1&count=200");
//	  HttpGet httpGet = new HttpGet(Constants.TWITTER_USER_TIME_LINE+"?cursor=-1&count=200&screen_name=YouTube");
//	  https://api.twitter.com/1.1/statuses/user_timeline.json?count=200&screen_name=YouTube
	  HttpGet httpGet = new HttpGet(Constants.TWITTER_SEARCH_TWEETS+"?q=pospospos2007&cursor=-1&count=100");
//	  HttpGet httpGet = new HttpGet("https://api.twitter.com/1.1/statuses/user_timeline.json?cursor=-1&count=200&screen_name=pospsopos200");
	  consumer.sign(httpGet);
		 
	  HttpResponse response = client.execute(httpGet);
	  
	  if(200!=response.getStatusLine().getStatusCode()){
		  Long remain = Long.valueOf(response.getAllHeaders()[9].getValue())/1000;
		  System.out.println("调用超过限制，请等待"+remain+"秒");
	  }
	  
	  Header[] headers = response.getAllHeaders();
	  System.out.println("剩余次数："+headers[18].getValue());//调用剩余次数
	  
//	  System.out.println(response.getHeaders("x-rate-limit-remaining"));
	  
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
	  System.out.println(sb.toString());
	  
	  plugin.setNamesUtf8mb4();//允许插入emoji
	  JSONArray jsonArray = null;
	  JSONArray mediaArray = null;
	  JSONObject jsonObject=null;
	  JSONObject userObject=null;
	  TwitterPost twitterPost = new TwitterPost();
	  TwitterUser twitteruser =new TwitterUser();
	  TwitterMedia twitterMedia =new TwitterMedia();
	  jsonArray = JSONArray.fromObject(sb.toString());
	  for(int i=0;i<jsonArray.size();i++){
		  jsonObject = jsonArray.getJSONObject(i);
		  userObject = jsonObject.getJSONObject("user");
		  if(jsonArray.getJSONObject(i).getJSONObject("entities").containsKey("media"))
			  mediaArray =jsonArray.getJSONObject(i).getJSONObject("entities").getJSONArray("media");
		  //帖子有则不插入
		  if(null==twitterPostService.findById(jsonObject.getLong("id"))){
			  twitterPost.setId(jsonObject.getLong("id"));
			  twitterPost.setText(jsonObject.getString("text"));
			  twitterPost.setCreateAt(new Date(jsonObject.getString("created_at")));
			  twitterPost.setPostType(Constants.TWITTER_POST_TYPE.MY_POST);
			  twitterPost.setUserId(userObject.getLong("id"));
			  twitterPostService.insert(twitterPost);
		  }
		  //用户有则不添加
		  if(null==twitterUserService.findById(userObject.getLong("id"))){
			  twitteruser.setId(userObject.getLong("id"));
			  twitteruser.setScreenName(userObject.getString("screen_name"));
			  twitteruser.setName(userObject.getString("name"));
			  twitteruser.setDescription(userObject.getString("description"));
			  twitteruser.setLocation(userObject.getString("location"));
			  twitteruser.setAvatar(userObject.getString("profile_image_url"));
			  twitterUserService.insert(twitteruser);
		  }
		  
		  //图片、视频有则不添加
		  if(null!=mediaArray&&mediaArray.size()>0){
			  for(int j=0;j<mediaArray.size();j++){
				  if(null==twitterMediaService.findById(mediaArray.getJSONObject(j).getLong("id"))){
					  twitterMedia.setId(mediaArray.getJSONObject(j).getLong("id"));
					  twitterMedia.setMediaUrl(mediaArray.getJSONObject(j).getString("media_url"));
					  twitterMedia.setPostId(jsonObject.getLong("id"));
					  if(jsonArray.getJSONObject(i).containsKey("extended_entities")
							  &&jsonArray.getJSONObject(i).getJSONObject("extended_entities").containsKey("media")
							  &&jsonArray.getJSONObject(i).getJSONObject("extended_entities").getJSONArray("media").getJSONObject(0).containsKey("video_info")
							  &&jsonArray.getJSONObject(i).getJSONObject("extended_entities").getJSONArray("media").getJSONObject(0).getJSONObject("video_info").getJSONArray("variants").getJSONObject(0).containsKey("url")){
						  twitterMedia.setVideoInfoUrl(jsonArray.getJSONObject(i).getJSONObject("extended_entities").getJSONArray("media").getJSONObject(0).getJSONObject("video_info").getJSONArray("variants").getJSONObject(0).getString("url"));
					  }
					  twitterMediaService.insert(twitterMedia);
				  }
			  }
		  }
			  
	  }
	}
	
//	@Scheduled(cron = "1 22 0/1 * * ? ")
	@Scheduled(cron = "0 0 0 * * ? ")
	public void addNewMovie(){
		
		System.out.println("获取新电影：");
		PageInfo page = movieService.buildPage(1);//max 192
		movieService.getPage(page);
		
	}
	
//	@Scheduled(cron = "1 37 0/1 * * ? ")
//	public void addMovie(){
//		
//		for(int i=1;i<192;i++){
//			System.out.println("页数："+i);
//			PageInfo page = movieService.buildPage(i);//max 192
//			movieService.getPage(page);
//		}
//	}
	
	
//	@Scheduled(cron = "1 46 0/1 * * ? ")
//	public void saveAll(){
//		messageService.storeAllZhihu();
//	}

//	@Scheduled(cron = "1 17 0/1 * * ? ")
//	public void execute() throws ParseException {
//		
//		for(int m=0;m<1;m--){
//		DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
//		Date dateIndex = dateFormat2.parse("20151208");
//		--i;
//		Date day = DateUtil.addDays(dateIndex, -1+i);
//		String dateString = DateUtil.dateToStr(day, DateUtil.TIMEDATE);
//		System.out.println("日报："+dateString+":");
//		
//		JSONObject jsonObject = null;
//		
//		String result = robotService.getZhiHuAirticleListBefore(dateString);
//		
//		jsonObject = JSONObject.fromObject(result);
//		
//		List<Map<String,Object>>  stories = (List<Map<String, Object>>) jsonObject.get("stories");
//		
//		for(int i=0;i<stories.size();i++){
//			
//			try {
//				
//			Zhihu  zhihuAirticle = new Zhihu();
//			
//			zhihuAirticle.setTitle(stories.get(i).get("title").toString().replaceAll("\\\\", "/"));
//			
//			int id = Integer.parseInt(stories.get(i).get("id").toString());
//			
//			zhihuAirticle.setId(id);
//			
//			ZhihuDTO zhihu = messageService.getZhihuDetailById(id);
//			
//			if(null!=zhihu){
//				continue;
//			}
//			
//			JSONObject detailObject = null;
//			
//			detailObject =  JSONObject.fromObject(robotService.getZhiHuAirticleDetail(stories.get(i).get("id").toString()));
//			
//			String content = detailObject.get("body").toString().replaceAll("\\\\", "/");
//
//			zhihuAirticle.setCss(detailObject.get("css").toString().replaceAll("\\\\", "/").replace("\"", "").replace("[", "").replace("]", ""));
//			
//			zhihuAirticle.setJs(detailObject.get("js").toString().replaceAll("\\\\", "/").replace("\"", "").replace("[", "").replace("]", ""));
//			
//			zhihuAirticle.setImages(detailObject.get("image").toString().replaceAll("\\\\", "/"));
//			
//			zhihuAirticle.setContent(content);
//			
//			
//				messageService.addZhihuAirticle(zhihuAirticle);
//			} catch (Exception e) {
//				log.error("无法插入此数据");
//			}
//			
//			
//		}
//		
//		
//		
//	}
//	}
	
}
