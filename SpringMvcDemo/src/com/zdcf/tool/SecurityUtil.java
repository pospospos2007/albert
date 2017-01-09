package com.zdcf.tool;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.regex.Pattern;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.MySQLCodec;
import org.springframework.ui.ModelMap;

public class SecurityUtil {
	
	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
		'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	
	private static final char hexDigitsabc[] = { '0', '1', '2', '3', '4', '5',
		'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	private static String LOGIN_SALT = "kaKNLKN:LKSDFDSF";
	
	private static final Codec MYSQL_CODEC = new MySQLCodec(MySQLCodec.Mode.STANDARD);
	public static final String escapeMySQL(String source){
		return ESAPI.encoder().encodeForSQL(MYSQL_CODEC, source);
	}
	
	public static final String encodeForHTML(String source){
		return ESAPI.encoder().encodeForHTML(source);
	}
	
	public static final String encodeForHTMLAttribute(String source){
		return ESAPI.encoder().encodeForHTMLAttribute(source);
	}
	
	public static void validateReturnURL(ModelMap model, String siteURL, String returnURL){
		if(SecurityUtil.isValidURL(siteURL, returnURL)){
			model.addAttribute("returnURL", SecurityUtil.encodeForHTMLAttribute(returnURL));
		} else {
			model.addAttribute("returnURL", null);
		}
	}
	
	public static boolean isValidURL(String siteURL, String target){
		// http://dai.pinganziben.com/reg?returnURL=http://dai.pinganziben.com
		// http://dai.pinganziben.com/reg?returnURL=http://dai.pinganziben.com/main
		// http://dai.pinganziben.com/login?returnURL=http://dai.pinganziben.com/loan/detail/view/100150403000101
		// http://dai.pinganziben.com/login?returnURL=http://202.168.1.1/loan/detail/view/100150403000101
		if (target == null || target.trim() == "" || target.length() == 0) {
			return false;
		}else if (checkUrl(target)) {
			return true;
		} else {
			int index_1 = target.indexOf(".");
			if(index_1 == -1 ){
				return true;
			}
			if (target.substring(index_1) != null && target.substring(index_1).contains("/")) {
				int index_2 = target.indexOf("/", index_1);
				String domainName = target.substring(0, index_2);
				String requestPath = target.substring(index_2);
				// 正则表达式匹配 只能包含字母，数字和“/”且长度不能超过50个字符 ： ^[/0-9a-z]{0,50}$
				if (siteURL != null && siteURL.equalsIgnoreCase(domainName) && requestPath.matches("^[/0-9a-zA-Z]{0,70}$")) {
					return true;
				}
			} else if (siteURL != null && siteURL.equalsIgnoreCase(target)) {
				return true;
			} else {
				return false;
			}
		}
		
		return false;
	}
	
	private static boolean checkUrl(String url){
		String patternStr = "[A-Za-z0-9/]+";
		return Pattern.matches(patternStr, url); 
	}
	public static void main(String[] args){
		
		//System.out.println(escapeMySQL("----''''\"\"-insert"));
		
		
		// http://dai.pinganziben.com/login?returnURL=http://dai.pinganziben.com
		// http://dai.pinganziben.com/login?returnURL=http://123.57.76.59
		// http://dai.pinganziben.com/login?returnURL=http://dai.pinganziben.com/main
		// http://dai.pinganziben.com/login?returnURL=http://123.57.76.59/mail
		// http://dai.pinganziben.com/login?returnURL=http://dai.pinganziben.com/loan/detail/view/100150403000101
		// http://dai.pinganziben.com/login?returnURL=http://123.57.76.59/loan/detail/view/100150403000101
		// http://dai.pinganziben.com/login?returnURL=http://dai.pinganziben.com/loan/detail/view/"00150403000101
		// http://dai.pinganziben.com/login?returnURL=http://123.57.76.59/loan/detail/view/"00150403000101
		// http://dai.pinganziben.com/login?returnURL=http://dai.pinganziben.com/loan/detail/view/>00150403000101
		// http://dai.pinganziben.com/login?returnURL=http://123.57.76.59/loan/detail/view/>00150403000101
		
		String siteURL = "http://dai.pinganziben.com";
		
		String[] testReturnURL = {	
				"http://dai.pinganziben.com",													// valid returnURL
				"http://123.57.76.59",															// IP
				"http://dai.pinganziben.com/main",												// valid returnURL
				"http://123.57.76.59/mail",														// IP
				"http://dai.pinganziben.com/loan/detail/view/100150403000101",					// valid returnURL
				"http://123.57.76.59/loan/detail/view/100150403000101",							// IP
				"http://dai.pinganziben.com/loan/detail/view/\"00150403000101",					// contains """
				"http://123.57.76.59/loan/detail/view/\"00150403000101",						// contains """ with IP
				"http://dai.pinganziben.com/loan/detail/view/>00150403000101",					// contains ">"
				"http://123.57.76.59/loan/detail/view/>00150403000101",							// contains ">" with IP
				"http://dai.pinganziben.com/loan/detail/view/00150403000101000000000000000000", // 50 characters, valid returnURL
				"http://dai.pinganziben.com/loan/detail/view/001504030001010000000000000000000" // 51 characters
		};
		
		boolean[] isValid = {false, false, false, false, false, false, false, false, false, false, false, false};
		
		for (int i = 0; i < 12; i ++) {
			isValid[i] = isValidURL(siteURL, testReturnURL[i]);
			System.out.println("isValid[" + i+ "]: Test for \"" + testReturnURL[i] + "\" is " + Boolean.toString(isValid[i]).toUpperCase());
		}
	}

	public static String secureMD5(String source) {
		return secureMD5(source, LOGIN_SALT);
	}

	public static String secureMD5(String source, String salt) {
		String md5 = source;
		for (int i = 0; i < 100; i++) {
			md5 = MD5(md5) + LOGIN_SALT;
		}

		return MD5(md5);
	}
	
	public final static String sha256(String source) {
		try {
			StringBuffer sb = new StringBuffer();
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(source.getBytes("UTF-8"));
			for (byte code : md.digest()){
	            String temp = Integer.toHexString(code & 0xff);
	            if (temp.length() == 1) {
	            	sb.append("0");
	            } else {
	            	sb.append(temp);
	            }
			}
			
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public final static String secureSha256(String source, String salt) {
		
		String sha = source;
		for (int i = 0; i < 100; i++) {
			sha = sha256(sha) + RandomUtil.code() + salt;
		}

		return sha256(sha);
	}
	
	public final static String MD5(byte[] btInput) {
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public final static String md5abc(byte[] btInput) {
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigitsabc[byte0 >>> 4 & 0xf];
				str[k++] = hexDigitsabc[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public final static String md5abc(String s) {
		return md5abc(s.getBytes());
	}
	
	public final static String MD5(String s) {
		return MD5(s.getBytes());
	}
	
	public final static String MD5WithUtf8(String s) {
		try {
			return MD5(s.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
