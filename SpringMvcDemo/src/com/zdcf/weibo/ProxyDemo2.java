package com.zdcf.weibo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.zdcf.tool.StringUtil;

import net.sf.json.JSONObject;
 
public class ProxyDemo2 {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://api.twitter.com/1.1/oauth/request_token.json");
        // /创建代理服务器
        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 1080);
//         Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr); // Socket 代理
        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
//        Authenticator.setDefault(new MyAuthenticator("username", "password"));// 设置代理的用户和密码
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);// 设置代理访问
        
        JSONObject jsonObject = null;
        
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        JSONObject jsonObj = new JSONObject();
		jsonObj.put("consumerkey", "aWgjz4QU7vm6cITrJ1Ff9Ve49");//用户名
		jsonObj.put("consumersecret","FrBakfTEvCVop4BxD3ttrE3fFojp62IqweqA9dTl9Uu2undD9y");
		jsonObj.put("accesstoken", "959746950-ifkHxIgWxgodhDeLYeGeFHf6Vle3gzHpwJnTmIvW");
		jsonObj.put("accesstokensecret", "Y9c4Aad4qCC3sbJJ16avGCVvqvAJuvE1ivbZ3oLemVFsv");
		jsonObj.put("twitteruser", "pospospos2007");
        String jsonStr = jsonObj.toString();
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(jsonStr.toString().getBytes("UTF-8"));
		outputStream.flush();
 
		if (connection.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ connection.getResponseCode());
		}
 
		BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
				(connection.getInputStream())));
 
		String output;
		System.out.println("Output from Server:\n");
		while ((output = responseBuffer.readLine()) != null) {
			System.out.println(output);
//			if(!StringUtil.isEmpty(output)){
//				jsonObject = JSONObject.fromObject(output);
//			}
		}
		
		connection.disconnect();
//        System.out.println(jsonObject.toString());
        
//        Document doc =  Jsoup.parse(connection.getInputStream(), "utf-8", "https://www.facebook.com");
//        System.out.println(doc.toString());
        
//        InputStreamReader in = new InputStreamReader(connection.getInputStream());
//        BufferedReader reader = new BufferedReader(in);
//        while (true) {
//            String s = reader.readLine();
//            if (s != null) {
//                System.out.println(s);
//            }
//        }
    }
 
    static class MyAuthenticator extends Authenticator {
        private String user = "";
        private String password = "";
 
        public MyAuthenticator(String user, String password) {
            this.user = user;
            this.password = password;
        }
 
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password.toCharArray());
        }
    }
 
}