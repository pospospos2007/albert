
package com.zdcf.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.zdcf.model.User;
import com.zdcf.tool.UserSessionUtil;


/**
 * Socket建立连接（握手）和断开
 */
public class HandShake implements HandshakeInterceptor {

	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		User user =UserSessionUtil.currentUser();
		System.out.println("Websocket:用户[ID:" + user.getId() + "]已经建立连接");
		if (request instanceof ServletServerHttpRequest) {
			// 标记用户
			Integer uid = user.getId();
			if(uid!=null){
				attributes.put("uid", uid);
			}else{
				return false;
			}
		}
		return true;
	}

	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
	System.out.println("after hand");
	
	}

}
