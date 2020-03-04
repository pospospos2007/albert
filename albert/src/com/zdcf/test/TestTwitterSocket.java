package com.zdcf.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;

import com.zdcf.base.Constants;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

public class TestTwitterSocket {
  public static void main(String[] args) throws Exception {
	  OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
				Constants.ConsumerKey,
				Constants.ConsumerSecret);
	  consumer.setTokenWithSecret(Constants.AccessToken, Constants.AccessSecret);
	  
	  
    HttpParams params = new BasicHttpParams();
    // HTTP 协议的版本,1.1/1.0/0.9
    HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
    // 字符集
    HttpProtocolParams.setContentCharset(params, "UTF-8");
    // 伪装的浏览器类型
    // IE7 是 
    // Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 6.0)
    //
    // Firefox3.03
    // Mozilla/5.0 (Windows; U; Windows NT 5.2; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3
    //
    HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
    HttpProtocolParams.setUseExpectContinue(params, true);
    BasicHttpProcessor httpproc = new BasicHttpProcessor();
    httpproc.addInterceptor(new RequestContent());
    httpproc.addInterceptor(new RequestTargetHost());
    httpproc.addInterceptor(new RequestConnControl());
    httpproc.addInterceptor(new RequestUserAgent());
    httpproc.addInterceptor(new RequestExpectContinue());
    HttpRequestExecutor httpexecutor = new HttpRequestExecutor();
    HttpContext context = new BasicHttpContext(null);
    HttpHost host = new HttpHost("127.0.0.1", 1080);
    DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
    ConnectionReuseStrategy connStrategy = new DefaultConnectionReuseStrategy();
    context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
    context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, host);
    try {
      String[] targets = { "https://www.twitter.com"};
      for (int i = 0; i < targets.length; i++) {
        if (!conn.isOpen()) {
          Socket socket = new Socket(host.getHostName(), host.getPort());
          conn.bind(socket, params);
        }
        
	  
        BasicHttpRequest request = new BasicHttpRequest("GET", targets[i]);
//        consumer.sign(request);
        
        System.out.println(">> Request URI: " + request.getRequestLine().getUri());
        context.setAttribute(ExecutionContext.HTTP_REQUEST, request);
        request.setParams(params);
        httpexecutor.preProcess(request, httpproc, context);
        HttpResponse response = httpexecutor.execute(request, conn, context);
        response.setParams(params);
        httpexecutor.postProcess(response, httpproc, context);
        // 返回码
        System.out.println("<< Response: " + response.getStatusLine());
        // 返回的文件头信息
//        Header[] hs = response.getAllHeaders();
//        for (Header h : hs) {
//          System.out.println(h.getName() + ":" + h.getValue());
//        }
        // 输出主体信息
//        System.out.println(EntityUtils.toString(response.getEntity()));
        
        HttpEntity entry = response.getEntity();
        StringBuffer sb = new StringBuffer();
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
  	  
        System.out.println("==============");
        if (!connStrategy.keepAlive(response, context)) {
          conn.close();
        } else {
          System.out.println("Connection kept alive...");
        }
      }
    } finally {
      conn.close();
    }
  }
}