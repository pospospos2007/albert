package com.zdcf.model;

import java.util.Date;

/**
 * 聊天记录
 */
public class ChatRecord {
	
	private String sender; //消息发送者
	private String receiver;; //消息接收者
	private String message; //消息内容
	private Date time; //时间
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
}
