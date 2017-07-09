package com.zdcf.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public abstract class Cacheable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5799919392583215955L;

	private Integer id ;
	
	private String cacheKey;
	
	private Integer reviewNum;
	
	private Date addTime;
	
	private Date updateTime;
	
	public abstract String getCacheKey();
	

}

