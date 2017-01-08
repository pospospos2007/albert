package com.zdcf.tool;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class Tools {
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/*** 
	*  
	* @param content 内容String 
	* @param p >0 .位数 
	* @return @tale: 
	* @purpose：得到相应位数已过滤html、script、style 标签的内容 内容结尾 为... 
	* @author：Simon - 赵振明 
	* @CreationTime：Aug 25, 201011:07:06 AM 
	*/  
	public static String getNoHTMLString(String content){  
	    
	    if(null==content) return "";  
	    
	    java.util.regex.Pattern p_script;   
	         java.util.regex.Matcher m_script;   
	         java.util.regex.Pattern p_style;   
	         java.util.regex.Matcher m_style;   
	         java.util.regex.Pattern p_html;   
	         java.util.regex.Matcher m_html;   
	          
	     try {   
	         String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";  
	         //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }   
	         String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";   
	               //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }   
	               String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式   
	             
	               p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);   
	               m_script = p_script.matcher(content);   
	               content = m_script.replaceAll(""); //过滤script标签  
	               p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);   
	               m_style = p_style.matcher(content);   
	               content = m_style.replaceAll(""); //过滤style标签   
	             
	               p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);   
	               m_html = p_html.matcher(content);   
	                 
	               content = m_html.replaceAll(""); //过滤html标签   
	           }catch(Exception e) {   
	                   return "";  
	           }   
	    
	    
	    
	    
	   return content;  
	}  
	   
	
	public static String getIpAddr(HttpServletRequest request) { 
		String ip = request.getHeader("X-Real-IP");
		if (null != ip && !"".equals(ip.trim())
				&& !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (null != ip && !"".equals(ip.trim())
				&& !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		return request.getRemoteAddr();
	   } 
	
	
	public static String removeFourChar(String content) {
        byte[] conbyte = content.getBytes();
        for (int i = 0; i < conbyte.length; i++) {
            if ((conbyte[i] & 0xF8) == 0xF0) {
                for (int j = 0; j < 4; j++) {                          
                    conbyte[i+j]=0x30;                     
                }  
                i += 3;
            }
        }
        content = new String(conbyte);
        return content.replaceAll("0000", "");
    }
	
	 public static String  getServerIp(){
		 String SERVER_IP = null;
	    	try {
				Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
				InetAddress ip = null;
				while (netInterfaces.hasMoreElements()) {
					NetworkInterface ni = (NetworkInterface) netInterfaces
							.nextElement();
					ip = (InetAddress) ni.getInetAddresses().nextElement();
					SERVER_IP = ip.getHostAddress();
					if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
							&& ip.getHostAddress().indexOf(":") == -1) {
						SERVER_IP = ip.getHostAddress();
						break;
					} else {
						ip = null;
					}
				}
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	      return SERVER_IP;
	    }
	 
	 public static String getLocalIP(){   
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
			        //System.out.println(ipAddrStr);   
			                return ipAddrStr;   
			}  
	   
	   
	
}
