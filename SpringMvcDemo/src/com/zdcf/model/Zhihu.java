package com.zdcf.model;

import lombok.Data;

import com.zdcf.base.Constants;

@Data
public class Zhihu extends Cacheable{

	private String title;
	
	private String content;
	
	private String images;
	
	private String js;
	
	private String css;
	

	@Override
	public String getCacheKey() {
		return Constants.Cache.Zhihu+getId();
	}
	
	

}
