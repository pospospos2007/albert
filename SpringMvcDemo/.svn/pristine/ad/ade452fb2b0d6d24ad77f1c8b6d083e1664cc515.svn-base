package com.zdcf.model;

import com.zdcf.base.Constants;

public class FileExchange extends Cacheable {

	String oldUrl;
	
	String newUrl;

	public String getOldUrl() {
		return oldUrl;
	}

	public void setOldUrl(String oldUrl) {
		this.oldUrl = oldUrl;
	}

	public String getNewUrl() {
		return newUrl;
	}

	public void setNewUrl(String newUrl) {
		this.newUrl = newUrl;
	}
	
	@Override
	public String getCacheKey() {
		return Constants.Cache.FileExchange+getOldUrl();
	}
}
