/**
 * 
 */
package com.zdcf.weibo;

import java.io.IOException;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;



/**
 * @author cheetyan
 *
 */
public class HttpClientPoolUtil {
	static int maxTotal=20000;  //max total connection
	static int maxPerRoute=2000; 
	static PoolingHttpClientConnectionManager cm = getCM();
	
	static int keepAlive = 30; //keep connection alive for 30s,must be less than the serverside keep alive value 
	static ConnectionKeepAliveStrategyImp ck = new ConnectionKeepAliveStrategyImp();
	
	static CloseableHttpClient httpClient = getHttpClient();
	
	public static String execute(HttpUriRequest request) throws IOException { // httppost,httpget
		String responseBody = null;		
		responseBody = httpClient.execute(request, new ResponseHandlerImp());
		//httpclient.close();
		return responseBody;
	}
	public static HttpEntity execute1(HttpUriRequest request) throws IOException { // httppost,httpget
		HttpResponse responseBody = null;		
		responseBody = httpClient.execute(request);
		HttpEntity entity = responseBody.getEntity();
		
		return entity;
	}
	public static void shutDown() throws IOException{
		httpClient.close();
	}
	
	static PoolingHttpClientConnectionManager getCM() {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// Increase max total connection to 200
		cm.setMaxTotal(maxTotal);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(maxPerRoute);
		// Increase max connections for localhost:80 to 50
		//HttpHost localhost = new HttpHost("locahost", 80);
		//cm.setMaxPerRoute(new HttpRoute(localhost), 50);
		return cm;
	}

	static CloseableHttpClient getHttpClient() {

		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).setKeepAliveStrategy(ck).build();
		return httpClient;
	}

	static class ConnectionKeepAliveStrategyImp implements ConnectionKeepAliveStrategy {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.apache.http.conn.ConnectionKeepAliveStrategy#getKeepAliveDuration
		 * (org.apache.http.HttpResponse, org.apache.http.protocol.HttpContext)
		 */
		@Override
		public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
			// Honor 'keep-alive' header
			HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(org.apache.http.protocol.HTTP.CONN_KEEP_ALIVE));
			while (it.hasNext()) {
				HeaderElement he = it.nextElement();
				String param = he.getName();
				String value = he.getValue();
				if (value != null && param.equalsIgnoreCase("timeout")) {
					try {
						return Long.parseLong(value) * 1000;
					} catch (NumberFormatException ignore) {
					}
				}
			}
			/*HttpHost target = (HttpHost) context.getAttribute(HttpClientContext.HTTP_TARGET_HOST);
			if ("www.naughty-server.com".equalsIgnoreCase(target.getHostName())) {
				// Keep alive for 5 seconds only
				return 5 * 1000;
			} else {
				// otherwise keep alive for 30 seconds
				return keepAlive * 1000;
			}*/
			return keepAlive * 1000;
		}

	}
	static class ResponseHandlerImp implements ResponseHandler<String> {
		public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				return entity != null ? EntityUtils.toString(entity, "UTF-8") : null;
			} else {
				throw new ClientProtocolException("Unexpected response status: " + status);
			}
		};
	}
}
