package com.zdcf.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 文章
 * @author Li
 *
 */

public class Airticle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4594642554359915857L;



	/**
	 * 文章id
	 */
	private Integer airticleId;

	

	/**
	 * 标题
	 */
	private String title;
	
	
	/**
	 * 内容
	 */
	private String content;
	
	private Date updateTime;
	
	private Date insertTime;
	
	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public Date getInsertTime() {
		return insertTime;
	}


	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}


	public Integer getAirticleId() {
		return airticleId;
	}


	public void setAirticleId(Integer airticleId) {
		this.airticleId = airticleId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	

}
