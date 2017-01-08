package com.zdcf.dto;

import java.util.Date;

/**
 * 消息实体
 * @author Admin
 *
 */
public class Message {
	/**
	 * 获取发送者
	 * @return
	 */
	public String getSender() {
		return sender;
	}
	
	/**
	 * 设置发送者
	 * @param sender
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	/**
	 * 获取接收者(null代表所有人)
	 * @return
	 */
	public String getReader() {
		return reader;
	}
	
	/**
	 * 设置接收者
	 * @param reader
	 */
	public void setReader(String reader) {
		this.reader = reader;
	}
	
	/**
	 * 获取消息内容
	 * @return
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 设置消息内容
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 获取发送时间
	 * @return
	 */
	public Date getSendtime() {
		return sendtime;
	}
	
	/**
	 * 设置发送时间
	 * @param sendtime
	 */
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	private String sender;
	private String reader;
	private String message;
	private Date sendtime;
}
