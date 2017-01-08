package com.zdcf.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户t_message
 * @author Li
 *
 */

public class Message implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8736721416496463128L;

	private Integer id;
	
	private String message;
	
	private int themeId;
	
	private int userId;
	
	private Date addTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getThemeId() {
		return themeId;
	}

	public void setThemeId(int themeId) {
		this.themeId = themeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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
		return "Message [id=" + id + ", message=" + message + ", themeId="
				+ themeId + ", userId=" + userId + ", addTime=" + addTime + "]";
	}
	
	
	
	
	
	
	

	
	
	

}
