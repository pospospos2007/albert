package com.zdcf.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.zdcf.base.Constants;
import com.zdcf.base.Constants.Cache;
import com.zdcf.model.Cacheable;
import com.zdcf.model.FileExchange;
import com.zdcf.model.Movie;
import com.zdcf.model.Zhihu;
import com.zdcf.service.BaseService;
import com.zdcf.service.ICacheService;

@Service("redisCacheService")
public class RedisCacheService extends BaseService implements ICacheService{
	private static final Logger log = Logger.getLogger(RedisCacheService.class);
	
	@Autowired
	private RedisTemplate template;
	
	public void set(String key, Object value) {
		template.opsForValue().set(key, toJSON(value));
	}
	
	public void hSet(String key, Map map) {
		template.opsForHash().putAll(key, map);
	}
	
	public void process(Object object, Cache.Type type){
		
		Map<String, String> map = new HashMap<String, String>();
		
		String json ="";
		String key = "";
		if(object instanceof Zhihu){
			Zhihu zhihu = (Zhihu) object;
			json = toJSON(zhihu);
			key = zhihu.getCacheKey();
			map.put(Cache.Entity, json);
		}else if(object instanceof Movie){
			Movie movie = (Movie) object;
			json = toJSON(movie);
			key = movie.getCacheKey();
			map.put(Cache.Entity, json);
		}else if(object instanceof FileExchange){
			FileExchange file = (FileExchange)object;
			json = toJSON(file);
			key = file.getCacheKey();
			map.put(Cache.Entity, json);
		}
		
		if(Cache.Type.save == type || Cache.Type.update == type){
			this.hSet(key, map);
//			设置过期时间
			template.expire(key, 20, TimeUnit.DAYS);
		} else if(Cache.Type.del == type){
			this.del(key);
		}
	}
	
	public <T extends Cacheable> Map<Integer, T> get(List<String> keys, Class<T> t){
		if(log.isDebugEnabled()){
			log.debug("trying to look up " + t.getClass().getSimpleName() + " with keys: " + StringUtils.join(keys, ",") );
		}
		
		Map<Integer, T> map = new HashMap<Integer, T>();
		for(String key : keys){
			T object = get(key, t);
			
			if(object != null){
				map.put(object.getId(), object);
			}
		}
		
		return map;
	}
	
	public <T extends Cacheable> T get(String key, Class<T> t) {
		Map map = template.opsForHash().entries(key);
		if(map != null && map.size() > 0){
			String json = (String)map.get(Constants.Cache.Entity);
			
			T object = toObject(json, t);
			
//			int pageView = parseInt((String)map.get(Constants.Cache.PageView));
//			object.setReviewNum(pageView);
			
//			if(t.isAssignableFrom(Scorable.class)){
//				Scorable scorable = (Scorable)object;
//				
//				double avgScore = Double.parseDouble((String)map.get(Constants.Cache.AveScore));
//				int totalCount = parseInt((String)map.get(Constants.Cache.TotalCount));
//				int totalScore = parseInt((String)map.get(Constants.Cache.TotalScore));
//				
//				scorable.setAvgScore(avgScore);
//				scorable.setScoreNum(totalCount);
//				scorable.setTotalScore(totalScore);
//			}
			
			return object;
		}
		
		return null;
	}
	
	private int parseInt(String source){
		try{
			return Integer.parseInt(source);
		}catch(Exception e){
			return 0;
		}
	}
	
	public Integer getInt(String key){
		String json = (String)template.opsForValue().get(key);
		
		if(StringUtils.isBlank(json)){
			return Integer.parseInt(json);
		}
		
		return null;
	}

	public void del(String key) {
		template.delete(key);
	}

	public void update(String key, Cacheable value) {
		//
	}



}
