package com.zdcf.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Li
 *
 */

public class FileDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7700427778203223954L;

	private Integer id;
	
	private String title;

	private String url;
	
	private String discription;
	
	private Integer userId;
	
	private Date addTime;
	
	private String fileFormat;
	
	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Override
	public String toString() {
		return "FileDTO [id=" + id + ", title=" + title + ", url=" + url
				+ ", discription=" + discription + ", userId=" + userId
				+ ", addTime=" + addTime + ", fileFormat=" + fileFormat + "]";
	}

	
	

	
	

}
