package com.zdcf.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReviewNum() {
		return reviewNum;
	}

	public void setReviewNum(Integer reviewNum) {
		this.reviewNum = reviewNum;
	}
	
}

