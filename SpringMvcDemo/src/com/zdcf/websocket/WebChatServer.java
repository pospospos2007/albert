package com.zdcf.websocket;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.zdcf.model.ChatRecord;
import com.zdcf.service.ChatRecordService;
import com.zdcf.tool.SensitiveWordsFilterUtils;


@ServerEndpoint("/webchat/{nickName}")
@Transactional
public class WebChatServer{
	
	private static Map<String, WebChatServer> users = new HashMap<>(); //用户集合
	private Session session; //客户端标识
	private String currentUser; //当前用户
	private static int onlineCount; //在线人数
	private List<ChatRecord> records; //聊天记录集合
//	private List<ChatRecord> result; //从数据库中查询出来的聊天记录
	private List<Map<String, Object>> result;
	
//	@Autowired
//	public ChatRecordService chatRecordService;
	
	private static JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String TYPE_MESSAGE = "message"; //消息
	private static final String TYPE_RECORD = "record"; //聊天记录
	
	
	public WebChatServer() {
		records = new ArrayList<>();
	}
	
	@OnOpen
	public void onOpen(Session session,@PathParam(value = "nickName")String nickName) throws JSONException{
		
		this.session = session;
		this.currentUser = nickName;
		//查询当前用户的聊天记录
		try {
			String sql = "select * from chat_record where sender='"+currentUser+"' or receiver='"+currentUser+"' order by time desc limit 0,50";
			result = jdbcTemplate.queryForList(sql);
			
//			List<Map<String, Object>> list = chatRecordService.queryChatRecords(currentUser);
//			result = list;
		} catch (Exception e) {
			result = null;
			e.printStackTrace();
		}
		//将当前用户添加到用户集合中
		users.put(currentUser, this);
		addCount();
		broadcast("system",MessageFormat.format("{0} 上线了！", currentUser));
		sendMsg(getJsonMsg("", "", TYPE_RECORD));
	}
	
	@OnError
	public void onError(Throwable throwable){
		System.out.println(throwable.toString());
		saveRecords();
	}

	//将消息转发到各个客户端
	@OnMessage
	public void onMessage(String msg) throws JSONException{

		JSONObject jsonObj = new JSONObject(msg);
		String sender = (String)jsonObj.get("from");
		String receiver = (String) jsonObj.get("to");
		String color = (String)jsonObj.get("color");
		String message = (String) jsonObj.get("content");
		message = "<font color=\""+color+"\">"+message+"</font>";
		
		//将当前的一条消息保存到消息集合中
		ChatRecord record = new ChatRecord();
		record.setReceiver(receiver);
		record.setSender(sender);
		record.setMessage(message);
		record.setTime(new Date());
		records.add(record);
		
		if (!"所有人".equals(receiver)) { //私聊 
			users.get(receiver).sendMsg(getJsonMsg(currentUser, "（私聊消息）"+message, TYPE_MESSAGE));
			this.sendMsg(getJsonMsg(currentUser, "@"+ receiver +" "+message, TYPE_MESSAGE)); //给自己发消息
			return;
		}
		
		broadcast(sender, message);
	}

	@OnClose
	public void onClose() throws JSONException{
		//将当前对象从集合中移除
		users.remove(currentUser);
		subCount();
		broadcast("system",MessageFormat.format("{0} 下线了！", currentUser));
		
		saveRecords();
	}

	/** 保存聊天记录到数据库 */
	private void saveRecords() {
		for(ChatRecord record : records){
			try {
//				chatRecordsMapper.insertChatRecord(record);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/** 发送消息 */
	private void sendMsg(String msg) {

		//敏感词过滤
		msg = SensitiveWordsFilterUtils.filterWords(msg);
		if (this.session.isOpen()) {
			try {
				this.session.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/** 给集合中的每个对象广播消息 
	 * @throws JSONException */
	private void broadcast(String from, String msg) throws JSONException{

		for(WebChatServer server : users.values()){

			//判断是否是当前用户
			if(server.session != this.session)
				server.sendMsg(getJsonMsg(from, msg, TYPE_MESSAGE));
			else if("system".equals(from)) //系统消息（取消当前用户上线及下线提醒）
				this.sendMsg(getJsonMsg(currentUser, "", TYPE_MESSAGE));
			else
				this.sendMsg(getJsonMsg(currentUser, msg, TYPE_MESSAGE));
			System.out.println("消息：======================\n"+msg);
		}
	}
	
	/** 获取在线人数 */
	private synchronized int getOnlineCount(){
		return onlineCount;
	}
	
	/** 在线用户数加一*/
	private synchronized void addCount(){
		onlineCount++;
	}
	
	/** 在线用户数减一 */
	private synchronized void subCount(){
		if (onlineCount > 0){			
			onlineCount--;
		}
	}
	
	/** 获取在线用户列表 */
	private synchronized String getOnlineUsers(){
		
		String result = "";
		for(String user : users.keySet()){ 
			result += user+"@";
		}
		if (result.length() > 0) {
			result = result.substring(0,result.length()-1);
		}
		return result;
	}
	
	/** 获取json格式的消息 
	 * @throws JSONException */
	private String getJsonMsg(String from, String msg, String msgType) throws JSONException{
		
		JSONObject jsonObj = new JSONObject();
		JSONArray records = new JSONArray();
		if (msgType == TYPE_RECORD){
			for(Map<String, Object> record : result){
				JSONObject recordJson = new JSONObject();
				recordJson.append("sender", record.get("sender"));
				recordJson.append("receiver", record.get("receiver"));
				recordJson.append("message", record.get("message"));
				recordJson.append("time", new SimpleDateFormat("MM-dd HH:mm:ss").format(record.get("time")));
				records.put(recordJson);
			}
			jsonObj.append("records", records);
		}
		jsonObj.append("count", getOnlineCount());
		jsonObj.append("userlist", getOnlineUsers());
		jsonObj.append("from", from);
		jsonObj.append("msg", msg);
		jsonObj.append("time", new SimpleDateFormat("HH:mm:ss").format(new Date()));
		System.out.println("===================================\n"+jsonObj.toString());
		return jsonObj.toString();
	}
	
}
