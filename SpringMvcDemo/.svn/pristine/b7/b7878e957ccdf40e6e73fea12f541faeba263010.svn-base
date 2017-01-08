package com.zdcf.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zdcf.mapper.AirticleMapper;
import com.zdcf.mapper.UserMapper;
import com.zdcf.model.Airticle;
import com.zdcf.model.User;
import com.zdcf.service.AirticleService;
import com.zdcf.tool.AddressUtils;
import com.zdcf.tool.EncodingTool;
import com.zdcf.tool.Tools;

@Controller
@RequestMapping("/")
public class NeuralNetworksAction {

	private static Logger logger = Logger.getLogger(NeuralNetworksAction.class);

	@RequestMapping("/kofWing2")
	public String kofWing2(HttpServletRequest request,HttpServletResponse respons) throws UnsupportedEncodingException{
		
		String ip = Tools.getNoHTMLString(getIpAddr(request));
		
		String address = AddressUtils.getAddresses("ip="+ip, "utf-8");
		
		logger.info("ip:"+ip+"("+address+")"+" 在玩拳皇Wing");
		
		
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
