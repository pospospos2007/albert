package com.zdcf.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.zdcf.model.Theme;

/**
 * @author Li
 *
 */

public class ThemeDTO extends Theme{

	
	private String content;
	
	private Date addTime;
	
	private String username;
	
	private String ip;
	
	private String email;
	
	private String avatar;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


}
