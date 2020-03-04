package com.zdcf.service;

import java.util.List;
import java.util.Map;

import com.zdcf.base.Constants;
import com.zdcf.model.Cacheable;

public interface ICacheService {
	public void set(String key, Object value);
	
	public <T extends Cacheable> T get(String key, Class<T> t);
	
	public <T extends Cacheable> Map<Integer, T> get(List<String> keys, Class<T> t);
	
	public Integer getInt(String key);
	
	public void del(String key);
	
	public void update(String key, Cacheable value);
	
	public void process(Object object, Constants.Cache.Type type);
}
