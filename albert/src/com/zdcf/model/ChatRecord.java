package com.zdcf.model;

import java.util.Date;

import lombok.Data;

/**
 * 聊天记录
 */
@Data
public class ChatRecord {
	
	private String sender; //消息发送者
	private String receiver;; //消息接收者
	private String message; //消息内容
	private Date time; //时间
	
	
}
