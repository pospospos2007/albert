package com.zdcf.model;

import com.zdcf.base.Constants;

import lombok.Data;

@Data
public class FileExchange extends Cacheable {

	String oldUrl;
	
	String newUrl;

	@Override
	public String getCacheKey() {
		return Constants.Cache.FileExchange+getOldUrl();
	}
}
