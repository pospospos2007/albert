package com.zdcf.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.zdcf.base.Constants;
import com.zdcf.dto.ZhihuDTO;
import com.zdcf.service.BaseService;
import com.zdcf.tool.DateUtil;
import com.zdcf.weibo.Config;

import net.sf.json.JSONObject;


public class UnitTest extends BaseService {
	
	@Test
	public  void testPost() throws IOException {
		
		/** 
		 * 首先要和URL下的URLConnection对话。 URLConnection可以很容易的从URL
		 * 得到。比如： // Using java.net.URL and //java.net.URLConnection
		 * 
		 */
		URL url = new URL("http://www.baidu.com");   
		
	    URLConnection connection = url.openConnection(); 
		
	    connection.setDoOutput(true);  
	    
	    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "8859_1");
	    
	    out.write("");
	    
	    out.flush();   
	    
	    out.close(); 
	    
	    String sCurrentLine; 
	    
	    String sTotalString;
	    
	    sCurrentLine = ""; 
	    
	    sTotalString = "";   
	    
	    InputStream l_urlStream;  
	    
	    l_urlStream = connection.getInputStream();   
	    
	    BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));    
	    
	    while ((sCurrentLine = l_reader.readLine()) != null) {   
	    
	    	sTotalString += sCurrentLine + "\r\n";   
	    }
	    System.out.println(sTotalString);
	    
		
	}
	
	@Test
	public void testGet(){
		
		 HttpClient client = new DefaultHttpClient();
		 
		 HttpGet get = new HttpGet("http://www.meiguoshenpo.com/fengshui/zhishi/");
		 
		 HttpResponse httpResponse;  
		 
		 try {
			httpResponse = client.execute(get);
			
			Document doc = Jsoup.parse(httpResponse.getEntity().getContent(), "utf8", "http://www.meiguoshenpo.com/fengshui/zhishi/");

			Elements elemnt = doc.getElementsByClass("list_item");
			
			System.out.println(elemnt.toString());
           
//			String codeValue = elemnt.attr("placeholder");
//            
//			String code = codeValue.substring(5);
//			
//			System.out.println("code:"+code);
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		
		
		
	}
	
	
	@Test
	public void getZhiHuAirticleList() {
		
		 HttpClient client1 = new DefaultHttpClient();
		 
		 HttpGet get = new HttpGet("http://news-at.zhihu.com/api/4/news/latest");
		 
		 HttpResponse response;
		 
		 String result = null;
		try {
			response = client1.execute(get);
			
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			System.out.println(result);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
//		 return result;
	}
	
	
	@Test
	public void getZhiHuAirticleDetail() {
		
		 HttpClient client = new DefaultHttpClient();
		 
		 HttpGet get = new HttpGet("http://news-at.zhihu.com/api/4/news/8382966");
		 
		 HttpResponse response;
		 
		 String result = null;
		try {
			response = client.execute(get);
			
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			System.out.println(result);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
	}
	
	@Test
	public void testRandomNum(){
	System.out.println(randomString(-229985452) + " " + randomString(-147909649));
	}

	public static String randomString(int i)
	{
	   Random ran = new Random(i);
	   StringBuilder sb = new StringBuilder();
	   while (true)
	   {
	       int k = ran.nextInt(27);
	        if (k == 0)
	           break;

	       sb.append((char)('`' + k));
	   }
	   return sb.toString();
	}
	
	@Test
	 public void  getLocalIP(){   
			InetAddress addr = null;   
			            try {
							addr = InetAddress.getLocalHost();
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}   
			        
			        byte[] ipAddr = addr.getAddress();   
			        String ipAddrStr = "";   
			        for (int i = 0; i < ipAddr.length; i++) {   
			            if (i > 0) {   
			                ipAddrStr += ".";   
			            }   
			            ipAddrStr += ipAddr[i] & 0xFF;   
			        }   
			        System.out.println(ipAddrStr);   
			}  
	
	@Test
	public  void testUrl() throws Exception {

	       URL aURL = new URL("http://example.com:80/docs/books/tutorial"
	                          + "/index.html?name=networking#DOWNLOADING");

	       System.out.println("protocol = " + aURL.getProtocol());
	       System.out.println("authority = " + aURL.getAuthority());
	       System.out.println("host = " + aURL.getHost());
	       System.out.println("port = " + aURL.getPort());
	       System.out.println("path = " + aURL.getPath());
	       System.out.println("query = " + aURL.getQuery());
	       System.out.println("filename = " + aURL.getFile());
	       System.out.println("ref = " + aURL.getRef());
	   }
	
	@Test
	public void testSyso(){
		String openDayStr="";
	    for(int i=0;i<7;i++){
	    	if((94&(int)Math.pow(2, i))>0){
	    		openDayStr = openDayStr+(int)Math.pow(2, i)+",";
	    	}
	    	
	    }
		System.out.println(openDayStr);
	}
	
	
	@Test
	public void testMutiThreads(){
		
		 HttpClient client = new DefaultHttpClient();
		 
		 HttpGet get = new HttpGet("http://192.168.0.102:8080/albert/lab/mutiThreads");
		 
		 HttpResponse httpResponse;  
		 
		 try {
			 
			 for(int i=0;i<100;i++){
			httpResponse = client.execute(get);
			
			if (httpResponse.getStatusLine().getStatusCode() == 200) {  
                // 得到httpResponse的实体数据  
                HttpEntity httpEntity = httpResponse.getEntity();  
                if (httpEntity != null) {  
                    try {  
                        BufferedReader bufferedReader = new BufferedReader(  
                        new InputStreamReader(httpEntity.getContent(),"UTF-8"), 8 * 1024);  
                        StringBuilder entityStringBuilder = new StringBuilder();  
                        String line = null;  
                        while ((line = bufferedReader.readLine()) != null) {  
                            entityStringBuilder.append(line + "/n");  
                        }  
                        // 利用从HttpEntity中得到的String生成JsonObject  
                        System.out.println(entityStringBuilder.toString());
//                        resultJsonObject = new JSONObject(entityStringBuilder.toString());  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
			
			 }
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		
		
		
		
	}
	
	@Test
	public void testSplice(){
		String test="1-2.jpg";
		System.out.println(test.substring(test.lastIndexOf(".")+1,test.length()));
	}
	
	@Test
	public <T> void testReflection() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class<?> class1 = null;  
        class1 = Class.forName("com.zdcf.dto.FileDTO");  
        System.out.println("Demo2:(写法1) 包名: " +((T)class1.newInstance()).toString());
	}
	
	@Test
	public void testStringReplace(){
		String a = "235235rf4562t54t/upload/4tabcdbc";
//		a = a.replace("abcd","");
//		int index =a.lastIndexOf("upload",);
		String[] options = a.split( "upload/");
		
//		String b =a.substring("upload/",a.length());
		System.out.println(options[1]);
	}
	
	@Test
	public void testTransform(){
		int A = 1+64;
		char c = (char)A;
		System.out.println(c);
	}
	
	@Test
	public  void testAuto() {
		if (new Object() {
	          public boolean foo() {
	            System.out.print("a");
	            return false;
	          }
	        }.foo()) {
	      System.out.print("a");
	    } else {
	      System.out.print("b");
	    }
	  }
	
	 private static Vector v = new Vector(10);

	 @Test
    public void neilastvalkue()
    {
        String path="images/asdasd/cvcv/asddsa/sddds.jpg";
        
        String fileDir = path.substring(0,
        		path.lastIndexOf(File.separator));
        
        System.out.println(File.separator);
    }
	 
	@Test
   public  void testInteger() {
        int i1 = 100;
        int i2 = 100;
        int i3 = 200;
        int i4 = 200;
        Integer i5 =100;
        Integer i6 =100;
        Integer i7 =200;
        Integer i8 =200;
        
        
        System.out.println(i1==i2);
        System.out.println(i3==i4);
        System.out.println(i5==i6);
        System.out.println(i7==i8);
        System.out.println(i7.equals(i8));
    }
	
//	@Test
//	public void testqiniu(){
////		package util;import com.qiniu.api.rs.*;import com.qiniu.api.config.Config;import com.qiniu.api.auth.digest.Mac;import com.qiniu.api.net.CallRet;public class Qiniu {    public static String uploadToken(String bucket, String accessKey, String secretKey){
//		PutPolicy upPolicy = new PutPolicy(bucket);
//        upPolicy.endUser = user;
//        upPolicy.callbackUrl = callbackUrl;
//        upPolicy.callbackBody = "key=$(key)&hash=$(etag)&width=$(imageInfo.width)&height=$(imageInfo.height)&user=$(endUser)&size=$(fsize)&mime=$(mimeType)";        String token = null;        Mac mac = new Mac(accessKey, secretKey);        try{
//            token = upPolicy.token(mac);
//        }catch (Exception e){
//            e.printStackTrace();
//        }        return token;
//    }
//}
//	}
	
	@Test
	public  void getPreMonth() {
		String repeatDate  ="201201";
        String lastMonth = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
        int year = Integer.parseInt(repeatDate.substring(0, 4));
        String monthsString = repeatDate.substring(4, 6);
        int month;
        if ("0".equals(monthsString.substring(0, 1))) {
            month = Integer.parseInt(monthsString.substring(1, 2));
        } else {
            month = Integer.parseInt(monthsString.substring(0, 2));
        }
        cal.set(year,month,Calendar.DATE);
        lastMonth = dft.format(cal.getTime());
        System.out.println(lastMonth);
    }
	
	@Test
	public void testStrang(){
		int j=0;
		
		for(int i=0;i<100;i++){
			 j = j++;
		}
		System.out.println("j="+j);
		
	}
	
	@Test
	public void testRedis(){
		try{
			ZhihuDTO zhihu = new ZhihuDTO();
			zhihu.setContent("this is s test");
			zhihu.setId(1);
			zhihu.setCss("11");
			zhihu.setImages("22");
			zhihu.setTitle("11");
			zhihu.setJs("1");
			this.sendMessage(zhihu, Constants.Cache.Type.save);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGetBaidu(){
		
		 HttpClient client = new DefaultHttpClient();
		 
		 HttpGet get = new HttpGet("http://www.baidu.com");
		 
		 HttpResponse httpResponse;  
		 
		 try {
			httpResponse = client.execute(get);
			
			Document doc = Jsoup.parse(httpResponse.getEntity().getContent(), "utf8", "http://www.baidu.com");

//			Elements elemnt = doc.getElementsByClass("list_item");
			
			System.out.println(doc.toString());
           
//			String codeValue = elemnt.attr("placeholder");
//            
//			String code = codeValue.substring(5);
//			
//			System.out.println("code:"+code);
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
//	@Test 
//	public void testFetchString(){ 
//		String aa = "223 3 #d 的 是个很__  EEE=+";
//        aa = aa.replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5]", "");
//        System.out.println(aa);
//　　    replaceAll("[\\s*|\t|\r|\n]", "");  // 去除所有空格，制表符
//　　    }
	
	//保留数字、汉字和字母
	@Test
	public void testsaddsa(){
		String aa = "[肖申克的救赎][1994][欧美][剧情][BD-RMVB-1.80G/720P-2.9GB][中英字幕]";
//		.replaceAll("[^/u4E00-/u9FA5]", "")
//        aa = aa.replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5]", "");
        String bb = aa.replaceAll("[^\u4E00-\u9FA5]", "");
        System.out.println(bb);
	}
	
	@Test
	public void getZhiHuAirticleListAgo() {
		
		 HttpClient client1 = new DefaultHttpClient();
		 
		 HttpGet get = new HttpGet("http://news.at.zhihu.com/api/4/news/before/20151215");
		 
		 HttpResponse response;
		 
		 String result = null;
		try {
			response = client1.execute(get);
			
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			JSONObject jsonObject = null;
			
			jsonObject = JSONObject.fromObject(result);
			
			List<Map<String,Object>>  stories = (List<Map<String, Object>>) jsonObject.get("stories");
			
			System.out.println(stories.toString());
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
//		 return result;
	}
	
	@Test
	public void testDate(){
		Date now = new Date();
		Date day = DateUtil.addDays(now, -1);
		System.out.println(DateUtil.dateToStr(day, DateUtil.TIMEDATE));
	}
	
	/** 获取两个日期间相隔的天数 **/
	@Test
	public void getBetweenDays() {
		Date d1 = new Date();
		Date d2 = DateUtil.addDays(d1, 1);
		int betweenDays = 0; 
		Calendar c1 = Calendar.getInstance(); 
		Calendar c2 = Calendar.getInstance(); 
		c1.setTime(d1);
		c2.setTime(d2);  // 保证第二个时间一定大于第一个时间
		//if (c1.after(c2)) {   c1 = c2;   c2.setTime(d1);  } 
		int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR); 
		betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR); 
		for (int i = 0; i < betweenYears; i++) {  
			c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));  
			betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);  
		}  
		System.out.println(Math.abs(betweenDays));
	}
	
	/**
	 * 测试文件类型
	 */
	@Test
	public void testFileUtil(){
		String url = "https://avatar.csdn.net/7/E/1/1_zndxlxm.MP4";
		System.out.println(FilenameUtils.getExtension(url));
	}
	
	/**
	 * 测试国际化，获取所有支持的国家和语言
	 */
	@Test
	public void testMore(){
		Locale[] localeList = Locale.getAvailableLocales();
		for(int i=0; i<localeList.length; i++) {
		    System.out.println(localeList[i].getDisplayCountry()+"="+localeList[i].getCountry() + " " + localeList[i].getDisplayLanguage() + "=" +localeList[i].getLanguage());
	    }
	}
	
	/**
	 * 测试微博
	 * 
	 */
	@Test
	public void testWeibo(){
		Config config =new Config();
		config.getAllThreadsRun();
	}
	
	/**
	 * 测试最大数字
	 * 
	 */
	@Test
	public void testMaxInt(){
		System.out.println("max:"+Integer.MAX_VALUE);
		System.out.println("min:"+Integer.MIN_VALUE);
		System.out.println("max:"+Long.MAX_VALUE);
	}
	/**
	 * 测试时间转换
	 */
	@Test
	public void testTransformDateString(){
		String dateStr = "Thu Jan 19 07:00:23 +0000 2017";
		Date date =new Date(dateStr);
	}
	@Test
	public void testJsonObject(){
	}
	
	/**
	 * 获取备案号
	 */
	@Test
	public void testGetNumber(){
		
		 HttpClient client = new DefaultHttpClient();
		 
		 HttpGet get = new HttpGet("http://yinhechuangxiang.com/solution/show.do");
		 
		 HttpResponse httpResponse;  
		 
		 try {
			httpResponse = client.execute(get);
			
			Document doc = Jsoup.parse(httpResponse.getEntity().getContent(), "utf8", "http://yinhechuangxiang.com/solution/show.do");

			Elements elemnt = doc.getElementsByClass("footer").select("p").eq(1);
			
//			System.out.println(elemnt.html());
			
			String regEx="[^0-9]";   
			Pattern p = Pattern.compile(regEx);   
			Matcher m = p.matcher(elemnt.html());   
			System.out.println( m.replaceAll("").trim());
           
//			String codeValue = elemnt.attr("placeholder");
//            
//			String code = codeValue.substring(5);
//			
//			System.out.println("code:"+code);
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		
		
		
	}
	
	@Test
	public void rongliangTest(){
		System.out.println("TotalMemory :::: " + (Runtime.getRuntime().
				totalMemory()/(1024*1024) + "M")); 
	}
	
	/**
	 * 测试某年某月得最后一天
	 */
	@Test
	public void MonetDayTest(){
	  Calendar cal = Calendar.getInstance();
	  //下面可以设置月份，注：月份设置要减1，所以设置1月就是1-1，设置2月就是2-1，如此类推
	  cal.set(Calendar.MONTH, 4-1);
	  cal.set(Calendar.YEAR,2015);
	  //调到上个月
	  cal.add(Calendar.MONTH, -1);
	  //得到一个月最最后一天日期(31/30/29/28)
	  int MaxDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	  //按你的要求设置时间
	  cal.set( cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), MaxDay, 23, 59, 59);
	  //按格式输出
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
	  System.out.println(sdf.format(cal.getTime())); 
	}
	
	@Test
	public void testStr(){
		String a = null;
		String b = "null";
		String c = a+b;
		System.out.println(c);
	}
	
	//测试可以重复key的集合
	@Test
	public void testColeection(){
		Multimap<Integer, String> keyValues = ArrayListMultimap.create();
        keyValues.put(1, "a");
        keyValues.put(1, "b");
        keyValues.put(2, "c");
        System.out.println(keyValues.toString());
	}
	
	@Test
	public void testCheckNull(){
//		String inputName = "null";
//		String name = Preconditions.checkNotNull(inputName);
//		System.out.println(name);
		
//		List<String> names = Lists.newArrayList();
//        names.add("iamzhongyong");
//        names.add("bixiao.zy");
//        StringBuilder sb = new StringBuilder();
//        String rs = Joiner.on(",").appendTo(sb, names).toString();
//        System.out.println(rs);
		
	}
	
	public void getNameFromLocalCache() throws Exception{
        //new一个cache的对象出来
        Cache<String/*name*/,String/*nick*/> cache = CacheBuilder.newBuilder().maximumSize(10).build();
        //在get的时候，如果缓存里面没有，则通过实现一个callback的方法去获取
        String name = cache.get("bixiao", new Callable<String>() {
            public String call() throws Exception {
                return "bixiao.zy"+"-"+"iamzhongyong";
            }
        });
        System.out.println(name);
        System.out.println(cache.toString());
    }
	
	
	public void getNameLoadingCache(String name) throws Exception{
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            //设置大小，条目数
            .maximumSize(20)
            //设置失效时间，创建时间
            .expireAfterWrite(20, TimeUnit.SECONDS)
            //设置时效时间，最后一次被访问
            .expireAfterAccess(20, TimeUnit.HOURS)
            //移除缓存的监听器
            .removalListener(new RemovalListener<String, String>() {
                public void onRemoval(RemovalNotification<String, String> notification) {
                    System.out.println("有缓存数据被移除了");
                }})
            //缓存构建的回调
            .build(new CacheLoader<String, String>(){//加载缓存
                @Override
                public String load(String key) throws Exception {
                    return key + "-" + "iamzhongyong";
                }
        });
 
        System.out.println(cache.get(name));
        cache.invalidateAll();
    }
	
	@Test
	public void testCache(){
		
	}
	
	@Test
	public void testCal(){
		System.out.println(3*4);
		System.out.println(3<<2);
	}
	
	@Test
	public void testNullKeyHashMap(){
		Map<String, String> map = new HashMap<String,String>(16);
		map.put(null, "11");
		System.out.println(map.get(null));
	}
	
	
	@Test
    public void TestLoadingCache() throws Exception{
        LoadingCache<String,String> cahceBuilder=CacheBuilder
        .newBuilder()
        .build(new CacheLoader<String, String>(){
            @Override
            public String load(String key) throws Exception {        
                String strProValue="hello "+key+"!";                
                return strProValue;
            }
            
        });        
         
        System.out.println("jerry value:"+cahceBuilder.apply("jerry"));
        System.out.println("jerry value:"+cahceBuilder.get("jerry"));
        System.out.println("peida value:"+cahceBuilder.get("peida"));
        System.out.println("peida value:"+cahceBuilder.apply("peida"));
        System.out.println("lisa value:"+cahceBuilder.apply("lisa"));
        cahceBuilder.put("harry", "ssdded");
        System.out.println("harry value:"+cahceBuilder.get("harry"));
    }
	
//	@Test
//    public void testcallableCache()throws Exception{
//        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000).build();  
//        String resultVal = cache.get("jerry", new Callable<String>() {  
//            public String call() {  
//                String strProValue="hello "+"jerry"+"!";                
//                return strProValue;
//            }  
//        });  
//        System.out.println("jerry value : " + resultVal);
//        
//        resultVal = cache.get("peida", new Callable<String>() {  
//            public String call() {  
//                String strProValue="hello "+"peida"+"!";                
//                return strProValue;
//            }  
//        });  
//        System.out.println("peida value : " + resultVal);
        
//        @Test
//        public  void basd(){
//        	 ArrayList<String> list = Lists.newArrayList("America", "ABC", "CNN", "OK", "ASYNC");
//             List<String> strings = list.stream().filter(e -> e.startsWith("A")).map(e -> e + " nice").collect(Collectors.toList());
//        }
	

	


	
	
	
	
}
