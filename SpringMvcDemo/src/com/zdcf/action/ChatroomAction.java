package com.zdcf.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.GsonBuilder;
import com.zdcf.model.User;
import com.zdcf.po.Message;
import com.zdcf.service.UserService;
import com.zdcf.tool.UserSessionUtil;
import com.zdcf.websocket.MyWebSocketHandler;

@Controller
@RequestMapping("/chatroom")
public class ChatroomAction {

	private static Logger logger = Logger.getLogger(ChatroomAction.class);
	
	@Autowired
	MyWebSocketHandler handler;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/toChatroom")
	public String chatroom(){
		return "/chatroom/chatroom";
	}
			
	
	@RequestMapping("/onlineusers")
	@ResponseBody
	public Set<String> onlineusers(HttpSession session){
		Map<Integer, WebSocketSession> map=MyWebSocketHandler.userSocketSessionMap;
		Set<Integer> set=map.keySet();
		Iterator<Integer> it = set.iterator();
		Set<String> nameset=new HashSet<String>();
		while(it.hasNext()){
			Integer entry = it.next();
			String name=userService.getNameById(entry);
			String user=UserSessionUtil.currentUser().getUsername();
			if(!user.equals(name))
				nameset.add(name);
		}
		return nameset;
	}
	
	// 发布系统广播（群发）
	@ResponseBody
	@RequestMapping(value = "broadcast", method = RequestMethod.POST)
	public void broadcast(@RequestParam("text") String text) throws IOException {
		Message msg = new Message();
		msg.setDate(new Date());
		msg.setFrom(-1);//-1表示系统广播
		msg.setFromName("系统广播");
		msg.setTo(0);
		msg.setText(text);
		handler.broadcast(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
	}
		
	@RequestMapping("getuid")
	@ResponseBody
	public User getuid(@RequestParam("username")String username){
		User user=userService.getUserByName(username);
		return user;
	}
	
}
