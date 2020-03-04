package com.zdcf.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdcf.tool.AddressUtils;
import com.zdcf.tool.Tools;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/")
public class NeuralNetworksAction {

	@RequestMapping("/kofWing2")
	public String kofWing2(HttpServletRequest request,HttpServletResponse respons) throws UnsupportedEncodingException{
		
		String ip = Tools.getNoHTMLString(getIpAddr(request));
		
		String address = AddressUtils.getAddresses("ip="+ip, "utf-8");
		
		log.info("ip:"+ip+"("+address+")"+" 在玩拳皇Wing");
		
		
		return "/game/kofWing";
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
