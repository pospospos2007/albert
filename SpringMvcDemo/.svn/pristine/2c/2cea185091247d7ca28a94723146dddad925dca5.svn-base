package com.zdcf.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdcf.dto.Message;

@Controller
@RequestMapping("/chatroom")
public class ChatroomAction {

	private static Logger logger = Logger.getLogger(ChatroomAction.class);
	
	
	@RequestMapping("/toChatroom")
	public String gameofThrones(HttpServletRequest request,HttpServletResponse respons){
		
		return "chatroom/index";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		
		String user = request.getParameter("user");
		if(user !=null && user !=""){		//非空验证
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("type", request.getParameter("type"));
			
			return "chatroom/talk"; 
		}else{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">alert('登录失败');</script>");
			out.flush();
			out.close();
		}	
		
		return "chatroom/login";
	}
	
	@RequestMapping("/GetMessage")
	public void GetMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Object obj = request.getSession().getAttribute("user");			//从Session中获取当前用户
		if(obj != null){												//如果用户存在 则读取消息

			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			StringBuilder sb = new StringBuilder();			//xml字符串拼接对象
			sb.append("<date>");
			
			String username = obj.toString();							//获取用户名
			
			//从Session中获取消息集合
			ConcurrentLinkedQueue<Message> msglist = (ConcurrentLinkedQueue<Message>)(request.getSession().getAttribute("msglist"));
			//如果不存在 则创建一个新的消息集合
			if(msglist == null){
				msglist = new ConcurrentLinkedQueue<Message>();
				
				//写入Session
				request.getSession().setAttribute("msglist",msglist);
				
				//写入Application
				ServletContext context = request.getSession().getServletContext();
				((Map<String, ConcurrentLinkedQueue<Message>>) context.getAttribute("totalmsglist")).put(username, msglist);
//				((Map<String, ConcurrentLinkedQueue<Message>>)request.getServletContext().getAttribute("totalmsglist")).put(username, msglist);
			}
			
			Iterator<Message> it = msglist.iterator();
			while(it.hasNext()){
				Message m = it.next();
				sb.append("<msg s=\""+m.getSender()+"\" m=\""+m.getMessage()+"\" t=\""+m.getSendtime().toString()+"\" r=\""+(m.getReader()==null?"":m.getReader())+"\" />");
				it.remove();
			}
			
			sb.append("</date>");
			out.print(sb.toString());		//输入获取到的消息字符串
			out.flush();
			out.close();
		}else {
			response.sendRedirect("login.html");
		}	
		
	}
	
	@RequestMapping("/GetMessageLong")
	public void GetMessageLong(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		Object obj = request.getSession().getAttribute("user");			//从Session中获取当前用户
		if(obj != null){												//如果用户存在 则读取消息

			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();

			while(true){
				
				String username = obj.toString();							//获取用户名
				//从Session中获取消息集合
				ConcurrentLinkedQueue<Message> msglist = (ConcurrentLinkedQueue<Message>)(request.getSession().getAttribute("msglist"));
				//如果不存在 则创建一个新的消息集合
				if(msglist == null){
					msglist = new ConcurrentLinkedQueue<Message>();
					
					//写入Session
					request.getSession().setAttribute("msglist",msglist);
					
					//写入Application
					ServletContext context = request.getSession().getServletContext();
					((Map<String, ConcurrentLinkedQueue<Message>>)context.getAttribute("totalmsglist")).put(username, msglist);
					
					waitMessage();
				}
				
				boolean isMessage=false;
				
				StringBuilder sb = new StringBuilder();			//xml字符串拼接对象
				
				Iterator<Message> it = msglist.iterator();
				while(it.hasNext()){
					Message m = it.next();
					//在XML字符串拼接对象中拼接出XML字符串
					isMessage = true;
					sb.append("<msg s=\""+m.getSender()+"\" m=\""+m.getMessage()+"\" t=\""+m.getSendtime().toString()+"\" r=\""+(m.getReader()==null?"":m.getReader())+"\" />");
					it.remove();
				}
				
				if(isMessage){						//如果读取到消息了
					sb.insert(0,"<date>");
					sb.append("</date>");
					out.print(sb.toString());		//输入获取到的消息字符串
					out.flush();
					out.close();
					return;
				}else{
					waitMessage();
				}
			}
		}else {
			response.sendRedirect("login.html");
		}
	}
	
	private void waitMessage() {
		try {
			//System.out.println("sleep");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/SendMessage")
	public void SendMessage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String str = request.getParameter("msg");
		String reader =request.getParameter("reader");

		Object obj = request.getSession().getAttribute("user");		//从Session中获取发送者名字
		if(obj == null || str == null || str == ""){	//非空验证
			out.print("false");
		}else{
			ServletContext context = request.getSession().getServletContext();
			Map<String,ConcurrentLinkedQueue<Message>> msglist =(Map<String,ConcurrentLinkedQueue<Message>>)(context.getAttribute("totalmsglist"));
			
			//构造出消息对象 并添加到Application中
			Message msg = new Message();
			msg.setMessage(str);
			msg.setSender(obj.toString());
			msg.setSendtime(new Date());
			if(reader==null || reader.equals("")){
				Iterator<String> mlist = msglist.keySet().iterator();
				while(mlist.hasNext()){
					String key = mlist.next();
					if(!key.equals(obj.toString())){
						msglist.get(key).add(msg);
					}
				}
			}else {
				msg.setReader(reader);
				ConcurrentLinkedQueue<Message> mlist = msglist.get("reader");
				if(mlist !=null){
					mlist.add(msg);
				}
			}
			out.print("true");
		}
		out.flush();
		out.close();
	}
	
	
}
