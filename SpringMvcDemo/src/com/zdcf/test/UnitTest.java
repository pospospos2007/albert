package com.zdcf.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;

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
import com.zdcf.dto.ZhihuDTO;
import com.zdcf.service.BaseService;
import com.zdcf.tool.DateUtil;
import com.zdcf.weibo.Config;

import net.sf.json.JSONObject;

import com.zdcf.base.Constants;


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
		 
		 HttpGet get = new HttpGet("http://192.168.0.102:8080/SpringMvcDemo/lab/mutiThreads");
		 
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
	
	@Test
	public void testFileUtil(){
		String url = "https://avatar.csdn.net/7/E/1/1_zndxlxm.jpg";
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
	
	
}
