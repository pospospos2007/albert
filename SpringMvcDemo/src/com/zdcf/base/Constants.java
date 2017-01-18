package com.zdcf.base;

public interface Constants {

	//推特验证信息
	static String AccessToken = "Your AccessToken";
	static String AccessSecret = "Your AccessSecret";
	static String ConsumerKey = "Your ConsumerKey";
	static String ConsumerSecret = "Your ConsumerSecret";
	
	String TWITTER_MY_TIME_LINE="https://api.twitter.com/1.1/statuses/home_timeline.json";
	
	String TWITTER_USER_TIME_LINE="https://api.twitter.com/1.1/statuses/user_timeline.json";
	
	String TWITTER_MY_MENTIONS_TIME_LINE="https://api.twitter.com/1.1/statuses/mentions_timeline.json";
	
	String TWITTER_SEARCH_TWEETS="https://api.twitter.com/1.1/search/tweets.json";
	
	String USER_SESSION_KEY = "USER_SESSION_KEY";
	String ADMIN_SESSION_KEY = "ADMIN_SESSION_KEY";
	
	String FORGET_VERIFIED_KEY = "FORGET_VERIFIED_KEY";
	//系统语言
	String SYS_LANGUAGE_SESSION_KEY = "SYS_LANGUAGE_SESSION_KEY";
	
	String COOKIE_REMEMBER_USER__PASSWORD__NAME = "COOKIE_REMEMBER_USER__PASSWORD__NAME";
	
    String SESSION_IMAGE_CODE = "SESSION_IMAGE_CODE";//图形验证码
	
	// 系统中的主要频道编码
	interface Chnl {
		int House = 100; // 租房
	}
	
	interface Cache{
		String Entity= "entity";
		String PageView = "pv";
		String TotalScore = "score";
		String TotalCount = "count";
		String AveScore = "avg";
		
		String Zhihu = "zhihu:";
		String Theme = "theme:";
		String Movie = "movie:";
		String FileExchange = "fileExchange:";
		
		enum Type{
			save, del, update
		}
	}
	
	interface LOCALE_LANGUAGE{
		String  zh_CN = "zh_CN"; //中文
		String  en_US = "en_US"; //英文
		String  zh_HK = "zh_HK"; //繁体
	}
		
}
