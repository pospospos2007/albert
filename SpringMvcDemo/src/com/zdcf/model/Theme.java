package com.zdcf.model;

import lombok.Data;

import com.zdcf.base.Constants;

/**
 * 用户t_message
 * @author Li
 *
 */

@Data
public class Theme extends Cacheable {

	private String theme;
	
	private Integer userId;
	
	private String content;
	
	@Override
	public String getCacheKey() {
		return Constants.Cache.Theme+getId();
	}

}
