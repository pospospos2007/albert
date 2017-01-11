package com.zdcf.dto;


import com.zdcf.model.Message;
/**
 * 用户t_message
 * @author Li
 *
 */

public class MessageDTO extends Message{

	private String email;
	
	private String avatar;
	
	private String ip;
	
	private String username;
	
	private String theme;

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

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
}
