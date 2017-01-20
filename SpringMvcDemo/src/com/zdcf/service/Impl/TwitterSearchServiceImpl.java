package com.zdcf.service.Impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdcf.base.Constants;
import com.zdcf.dao.TwitterMediaMapper;
import com.zdcf.dao.TwitterPostMapper;
import com.zdcf.dao.TwitterPostSearchMapper;
import com.zdcf.dao.TwitterSearchHistoryMapper;
import com.zdcf.dao.TwitterUserMapper;
import com.zdcf.dao.customize.CustomizeTwitterPostMapper;
import com.zdcf.dao.customize.CustomizeTwitterSearchHistoryMapper;
import com.zdcf.mapper.Plugin;
import com.zdcf.model.TwitterMedia;
import com.zdcf.model.TwitterMediaExample;
import com.zdcf.model.TwitterPost;
import com.zdcf.model.TwitterPostSearch;
import com.zdcf.model.TwitterSearchHistory;
import com.zdcf.model.TwitterSearchHistoryExample;
import com.zdcf.model.TwitterUser;
import com.zdcf.service.TwitterSearchService;
import com.zdcf.tool.DateUtil;
import com.zdcf.tool.PageVo;
import com.zdcf.tool.ProxyUtil;
import com.zdcf.tool.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

@Service
@Transactional
public class TwitterSearchServiceImpl implements  TwitterSearchService {

	
	@Autowired
	private TwitterPostMapper twitterPostMapper;
	
	@Autowired
	private TwitterSearchHistoryMapper twitterSearchHistoryMapper;
	
	@Autowired
	private TwitterPostSearchMapper twitterPostSearchMapper;
	
	@Autowired
	private TwitterMediaMapper twitterMediaMapper;
	
	@Autowired
	private CustomizeTwitterPostMapper customizeTwitterPostMapper;
	
	@Autowired
	private CustomizeTwitterSearchHistoryMapper customizeTwitterSearchHistoryMapper;
	
	@Autowired
	private TwitterUserMapper twitterUserMapper;
	
	
	@Autowired
	private Plugin plugin;
	
	public int insert(TwitterPost twitterPost){
		
		int  count = twitterPostMapper.insert(twitterPost);
		
		return count;
	}

	@Override
	public TwitterPost findById(Long id){
		
		return twitterPostMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public Serializable getListPage(
			PageVo<Map<String, Object>> pageVo,TwitterSearchHistory tsh) {
		int offset = pageVo.getCurrentPage() - 1;
		if (offset < 0)
			offset = 0;
		List<Map<String, Object>> list = customizeTwitterSearchHistoryMapper
				.getListPage( offset * pageVo.getPageSize(),
						pageVo.getPageSize(),tsh);
		int count = customizeTwitterSearchHistoryMapper.getCount(tsh);
		Long postId;
		List<TwitterMedia> mediaList =null;
		for(int i=0;i<list.size();i++){
			postId = StringUtil.ObjectToLongUtil(list.get(i).get("id"));
			mediaList =this.getMediaListByPostId(postId);
			list.get(i).put("mediaList", mediaList);
		}
		pageVo.setVoList(list);
		pageVo.setRecordCount(count);
		
		return pageVo;
		
	}
	
	@Override
	public Map<String, Object> check(TwitterSearchHistory tsh) throws RuntimeException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException{
		Map<String, Object> map =new HashMap<String, Object>();
		//检查是否已经搜索过此内容或用户
		TwitterSearchHistoryExample example = new TwitterSearchHistoryExample();
		example.createCriteria().andSearchKeyEqualTo(tsh.getSearchKey())
								.andSearchTypeEqualTo(tsh.getSearchType());
		List<TwitterSearchHistory> list =twitterSearchHistoryMapper.selectByExample(example);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		if(null!=list&&list.size()>0&&sdf.format(list.get(0).getSearchDate()).compareTo(sdf.format(new Date()))==0){
			tsh =list.get(0);
			map.put("searchId", tsh.getId());
			map.put("status", Boolean.TRUE);
		}else{
			//今天没有搜索过，则调用推特api查询数据
			map = this.addTwitterPostByKey(tsh);
		}
		return map;
	}
	
	protected Map<String, Object> addTwitterPostByKey(TwitterSearchHistory tsh) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException{
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
				Constants.ConsumerKey,
				Constants.ConsumerSecret);
	  Map<String, Object> map =new HashMap<String, Object>();
	  consumer.setTokenWithSecret(Constants.AccessToken, Constants.AccessSecret);
	  StringBuffer sb = new StringBuffer();
	  HttpClient client = ProxyUtil.getHttpClient();
	  HttpGet httpGet =null;
	  if(Constants.TWITTER_SEARCH_TYPE.SEARCH_USER.equals(tsh.getSearchType())){
		  httpGet = new HttpGet(Constants.TWITTER_USER_TIME_LINE+"?cursor=-1&count=200&screen_name="+tsh.getSearchKey());
	  }else{
		  httpGet = new HttpGet(Constants.TWITTER_SEARCH_TWEETS+"?cursor=-1&q="+tsh.getSearchKey()+"&count=100");
	  }
	  consumer.sign(httpGet);
		 
	  HttpResponse response = client.execute(httpGet);
	  
	  if(200!=response.getStatusLine().getStatusCode()){
		  Long remain = Long.valueOf(response.getAllHeaders()[9].getValue())/1000;
		  System.out.println("调用超过限制，请等待"+remain+"秒");
		  map.put("status", Boolean.FALSE);
		  map.put("msg", "当前搜索超过限制，请等待"+remain+"秒"+"或者尝试按照另一个类别搜索");
		  return map;
	  }
	  Header[] headers = response.getAllHeaders();
	  System.out.println("剩余次数："+headers[18].getValue());//调用剩余次数
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
	  
	  //允许插入emoji
	  plugin.setNamesUtf8mb4();
	  
	  JSONArray jsonArray = null;
	  JSONArray mediaArray = null;
	  JSONObject jsonObject=null;
	  JSONObject userObject=null;
	  TwitterPost twitterPost = new TwitterPost();
	  TwitterUser twitteruser =new TwitterUser();
	  TwitterMedia twitterMedia =new TwitterMedia();
	  
	  if(Constants.TWITTER_SEARCH_TYPE.SEARCH_USER.equals(tsh.getSearchType())){
		  jsonArray = JSONArray.fromObject(sb.toString());
	  }else{
		  jsonArray = JSONObject.fromObject(sb.toString()).getJSONArray("statuses");
	  }
	  
	  //有此搜索记录则不添加，更新搜索日期
	  tsh.setSearchDate(new Date());
	  if(null!=tsh.getId()){
		  twitterSearchHistoryMapper.updateByPrimaryKey(tsh);
	  }else{
		  twitterSearchHistoryMapper.insert(tsh);
	  }
	  map.put("searchId", tsh.getId());
	  
	  TwitterPostSearch twitterPostSearch =new TwitterPostSearch();
	  
	  for(int i=0;i<jsonArray.size();i++){
		  jsonObject = jsonArray.getJSONObject(i);
		  userObject = jsonObject.getJSONObject("user");
		  if(jsonArray.getJSONObject(i).getJSONObject("entities").containsKey("media"))
			  mediaArray =jsonArray.getJSONObject(i).getJSONObject("entities").getJSONArray("media");
		  //帖子有则不插入
		  if(null==twitterPostMapper.selectByPrimaryKey(jsonObject.getLong("id"))){
			  twitterPost.setId(jsonObject.getLong("id"));
			  twitterPost.setText(jsonObject.getString("text"));
			  twitterPost.setCreateAt(new Date(jsonObject.getString("created_at")));
			  twitterPost.setPostType(Constants.TWITTER_POST_TYPE.SEARCH_RESULT);
			  twitterPost.setUserId(userObject.getLong("id"));
			  twitterPostMapper.insert(twitterPost);
			  
			  //增加到搜索结果记录
			  twitterPostSearch.setPostId(twitterPost.getId());
			  twitterPostSearch.setSearchId(tsh.getId());
			  twitterPostSearchMapper.insert(twitterPostSearch);
		  }
		  //用户有则不添加
		  if(null==twitterUserMapper.selectByPrimaryKey(userObject.getLong("id"))){
			  twitteruser.setId(userObject.getLong("id"));
			  twitteruser.setScreenName(userObject.getString("screen_name"));
			  twitteruser.setName(userObject.getString("name"));
			  twitteruser.setDescription(userObject.getString("description"));
			  twitteruser.setLocation(userObject.getString("location"));
			  twitteruser.setAvatar(userObject.getString("profile_image_url"));
			  twitterUserMapper.insert(twitteruser);
		  }
		  
		  //图片、视频有则不添加
		  if(null!=mediaArray&&mediaArray.size()>0){
			  for(int j=0;j<mediaArray.size();j++){
				  if(null==twitterMediaMapper.selectByPrimaryKey(mediaArray.getJSONObject(j).getLong("id"))){
					  twitterMedia.setId(mediaArray.getJSONObject(j).getLong("id"));
					  twitterMedia.setMediaUrl(mediaArray.getJSONObject(j).getString("media_url"));
					  twitterMedia.setPostId(jsonObject.getLong("id"));
					  if(jsonArray.getJSONObject(i).containsKey("extended_entities")
							  &&jsonArray.getJSONObject(i).getJSONObject("extended_entities").containsKey("media")
							  &&jsonArray.getJSONObject(i).getJSONObject("extended_entities").getJSONArray("media").getJSONObject(0).containsKey("video_info")
							  &&jsonArray.getJSONObject(i).getJSONObject("extended_entities").getJSONArray("media").getJSONObject(0).getJSONObject("video_info").getJSONArray("variants").getJSONObject(0).containsKey("url")){
						  twitterMedia.setVideoInfoUrl(jsonArray.getJSONObject(i).getJSONObject("extended_entities").getJSONArray("media").getJSONObject(0).getJSONObject("video_info").getJSONArray("variants").getJSONObject(0).getString("url"));
					  }
					  twitterMediaMapper.insert(twitterMedia);
				  }
			  }
		  }
			  
	  }
	  map.put("status", Boolean.TRUE);
	  return map;
	}
	
	protected List<TwitterMedia> getMediaListByPostId(Long id){
		TwitterMediaExample example = new TwitterMediaExample();
		example.createCriteria().andPostIdEqualTo(id);
		return twitterMediaMapper.selectByExample(example);
	}
	
}
