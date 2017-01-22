package com.zdcf.tool;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;

public class ProxyUtil {
	//设置代理
	public static HttpClient getHttpClient() {

		DefaultHttpClient httpClient = new DefaultHttpClient();
		String proxyHost = "127.0.0.1";
		int proxyPort = 8123;
//		int proxyPort = 1080;
//				 String userName = "111";
//				 String password = "111";
//				 httpClient.getCredentialsProvider().setCredentials(
//				   new AuthScope(proxyHost, proxyPort),
//				   new UsernamePasswordCredentials(userName, password));
		HttpHost proxy = new HttpHost(proxyHost,proxyPort);
		httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
		return httpClient;
	}
}
