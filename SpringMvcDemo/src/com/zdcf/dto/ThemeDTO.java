package com.zdcf.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Li
 *
 */

public class ThemeDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8736721416496463128L;

	private Integer id;
	
	private String theme;
	
	private String userId;
	
	private String content;
	
	private Date addTime;
	
	private String username;
	
	private String ip;
	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ThemeDTO [id=" + id + ", theme=" + theme + ", userId=" + userId
				+ ", content=" + content + ", addTime=" + addTime
				+ ", username=" + username + ", ip=" + ip + "]";
	}

	

	
	

}
