package com.zdcf.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdcf.tool.Tools;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/")
public class GameAction {

	@RequestMapping("/toGameList")
	public String toGameList(String code){
		
		return "game/gameList";
	}
	
	
	@RequestMapping("/streetFighter")
	public String streetFighter(HttpServletRequest request,HttpServletResponse respons){
		
		return "streetFighter";
	}
	
	@RequestMapping("/gomoku")
	public String gothic(HttpServletRequest request,HttpServletResponse respons){
		
		return "gomoku";
	}
	
	@RequestMapping("/gomokuHelper")
	public String gothicHelper(HttpServletRequest request,HttpServletResponse respons){
		
		return "gomokuHelper";
	}
	
	
	@RequestMapping("/kofWing")
	public String kofWing(HttpServletRequest request,HttpServletResponse respons) throws UnsupportedEncodingException{
		
		String ip = Tools.getNoHTMLString(getIpAddr(request));
		
		
		log.info("ip:"+ip+" 在玩拳皇Wing");
		
		
		return "/game/kofWing";
	}
	
	@RequestMapping("/QBNB")
	public String QBNB(HttpServletRequest request,HttpServletResponse respons) throws UnsupportedEncodingException{
		
		String ip = Tools.getNoHTMLString(getIpAddr(request));
		
		log.info("ip:"+ip+" 在玩Q版泡泡堂");
		
		return "/game/QBNB";
	}
	
	@RequestMapping("/Landlords")
	public String Landlords(HttpServletRequest request,HttpServletResponse respons){
		
		String ip = Tools.getNoHTMLString(getIpAddr(request));
		
		log.info("ip:"+ip+" 在玩斗地主游戏");
		
		
		return "/game/Landlords";
	}
	
	@RequestMapping("/Ensign")
	public String Ensign(HttpServletRequest request,HttpServletResponse respons) throws UnsupportedEncodingException{
		
		String ip = Tools.getNoHTMLString(getIpAddr(request));
		
		log.info("ip:"+ip+" 在玩暗翻军旗（联机）游戏");
		
		return "/game/Ensign";
	}
	
	@RequestMapping("/JellyDrips")
	public String JellyDrips(HttpServletRequest request,HttpServletResponse respons) throws UnsupportedEncodingException{
		
		String ip = Tools.getNoHTMLString(getIpAddr(request));
		
		
		log.info("ip:"+ip+" 在玩果冻大逃亡游戏");
		
		return "/game/JellyDrips";
	}
	
	public String getIpAddr(HttpServletRequest request) { 
		String ip = request.getHeader("X-Real-IP");
		if (null != ip && !"".equals(ip.trim())
				&& !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (null != ip && !"".equals(ip.trim())
				&& !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		return request.getRemoteAddr();
	   } 
	
}
