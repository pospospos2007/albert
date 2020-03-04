package com.zdcf.test;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.junit.Test;

public class TestGuava {
	/**
     * 不需要延迟处理(泛型的方式封装)
     * @return
     */
    public  <K , V> LoadingCache<K , V> cached(CacheLoader<K , V> cacheLoader) {
          LoadingCache<K , V> cache = CacheBuilder
          .newBuilder()
          .maximumSize(2)
          .weakKeys()
          .softValues()
          .refreshAfterWrite(120, TimeUnit.SECONDS)
          .expireAfterWrite(10, TimeUnit.MINUTES)        
          .removalListener(new RemovalListener<K, V>(){
            @Override
            public void onRemoval(RemovalNotification<K, V> rn) {
                System.out.println(rn.getKey()+"被移除");  
                
            }})
          .build(cacheLoader);
          return cache;
    }
    
    /**
     * 通过key获取value
     * 调用方式 commonCache.get(key) ; return String
     * @param key
     * @return
     * @throws Exception
     */
  
    public  LoadingCache<String , String> commonCache(final String key) throws Exception{
        LoadingCache<String , String> commonCache= cached(new CacheLoader<String , String>(){
                @Override
                public String load(String key) throws Exception {
                    return "hello "+key+"!";    
                }
          });
        return commonCache;
    }
    
    @Test
    public void testCache() throws Exception{
        LoadingCache<String , String> commonCache=commonCache("peida");
        System.out.println("peida:"+commonCache.get("peida"));
//        commonCache.apply("harry");
//        System.out.println("harry:"+commonCache.get("harry"));
//        commonCache.apply("lisa");
//        System.out.println("lisa:"+commonCache.get("lisa"));
    }
}
