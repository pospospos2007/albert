package com.zdcf.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.zdcf.base.Constants;

/**
 * 用户t_message
 * @author Li
 *
 */

public class Theme extends Cacheable {

	private String theme;
	
	private Integer userId;
	
	private String content;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	@Override
	public String getCacheKey() {
		return Constants.Cache.Theme+getId();
	}

}
