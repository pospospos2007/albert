package com.zdcf.test;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class ThreadPoolHttpClient {
    // 线程池
    private ExecutorService exe = null;
    // 线程池的容量
    private static final int POOL_SIZE = 20;
    private HttpClient client = null;
    String[] urls={"http://192.168.0.102:8080/SpringMvcDemo/lab/mutiThreads"};
    public ThreadPoolHttpClient(String[] urls){
        this.urls=urls;
    }
    
    public void test() throws Exception {
        exe = Executors.newFixedThreadPool(POOL_SIZE);
        HttpParams params =new BasicHttpParams();
        /* 从连接池中取连接的超时时间 */ 
        ConnManagerParams.setTimeout(params, 1000);
        /* 连接超时 */ 
        HttpConnectionParams.setConnectionTimeout(params, 2000); 
        /* 请求超时 */
        HttpConnectionParams.setSoTimeout(params, 4000);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(
                new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));

        //ClientConnectionManager cm = new PoolingClientConnectionManager(schemeRegistry);
        PoolingClientConnectionManager cm=new PoolingClientConnectionManager(schemeRegistry);
        cm.setMaxTotal(10);
        final HttpClient httpClient = new DefaultHttpClient(cm,params);

        // URIs to perform GETs on
        final String[] urisToGet = urls;
        /* 有多少url创建多少线程，url多时机子撑不住
        // create a thread for each URI
        GetThread[] threads = new GetThread[urisToGet.length];
        for (int i = 0; i < threads.length; i++) {
            HttpGet httpget = new HttpGet(urisToGet[i]);
            threads[i] = new GetThread(httpClient, httpget);            
        }
        // start the threads
        for (int j = 0; j < threads.length; j++) {
            threads[j].start();
        }

        // join the threads，等待所有请求完成
        for (int j = 0; j < threads.length; j++) {
            threads[j].join();
        }
        使用线程池*/
        for (int i = 0; i < urisToGet.length; i++) {
            final int j=i;
            System.out.println(j);
            HttpGet httpget = new HttpGet(urisToGet[i]);
            exe.execute( new GetThread(httpClient, httpget));
        }
        
        
        //创建线程池，每次调用POOL_SIZE
        /*
        for (int i = 0; i < urisToGet.length; i++) {
            final int j=i;
            System.out.println(j);
            exe.execute(new Thread() {
                @Override
                public void run() {
                    this.setName("threadsPoolClient"+j);
                    
                        try {
                            this.sleep(100);
                            System.out.println(j);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        
                        HttpGet httpget = new HttpGet(urisToGet[j]);
                        new GetThread(httpClient, httpget).get();
                    }
                    
                    
                
            });
        }
        
        */
        //exe.shutdown();
        System.out.println("Done");
    }
    static class GetThread extends Thread{
        
        private final HttpClient httpClient;
        private final HttpContext context;
        private final HttpGet httpget;
        
        public GetThread(HttpClient httpClient, HttpGet httpget) {
            this.httpClient = httpClient;
            this.context = new BasicHttpContext();
            this.httpget = httpget;
        }
        @Override
        public void run(){
            this.setName("threadsPoolClient");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            get();
        }
        
        public void get() {
            try {
                HttpResponse response = this.httpClient.execute(this.httpget, this.context);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    System.out.println(this.httpget.getURI()+": status"+response.getStatusLine().toString());
                }
                // ensure the connection gets released to the manager
                EntityUtils.consume(entity);
            } catch (Exception ex) {
                this.httpget.abort();
            }finally{
                httpget.releaseConnection();
            }
        }
    }
}