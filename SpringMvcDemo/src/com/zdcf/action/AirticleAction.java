package com.zdcf.action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdcf.base.BaseAction;
import com.zdcf.model.Airticle;
import com.zdcf.service.AirticleService;
import com.zdcf.tool.Tools;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/")
public class AirticleAction extends BaseAction {

	
	@Resource
	private AirticleService airticleService;

	
	
	@RequestMapping("/getAllAirticle")
	public String getAllAirticle(HttpServletRequest request,HttpServletResponse response){
		
		
		List<Airticle> airticles = new ArrayList<Airticle>();
		
		airticles = airticleService.getAirticleList();
		
		request.setAttribute("airticles", airticles);
		
		/*ModelAndView mv = new ModelAndView();  
		
		mv.addObject("airticles", airticles);      
		        mv.setViewName("/airticleList");  */

		return "/airticleList";
	}
	
	@RequestMapping("/toAddAirticle")
	public String toAddAirticle(){
		
		return "addAirticle";
	}
	
	public   String StringFilter(String   str)   throws   PatternSyntaxException   {     
        // 只允许字母和数字       
        // String   regEx  =  "[^a-zA-Z0-9]";                     
           // 清除掉所有特殊字符  
		  String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
		  Pattern   p   =   Pattern.compile(regEx);     
		  Matcher   m   =   p.matcher(str);     
		  return   m.replaceAll("").trim();     
  }
	
	
	@RequestMapping("/addAirticle")
	public String addAirticle(HttpServletRequest request,HttpServletResponse respons){
		
		return "redirect:/getAllAirticle";
		
	}
	
	@RequestMapping("/getAirticleDetail")
	public String getAirticleDetail(HttpServletRequest request,HttpServletResponse respons){
		
		int airticleId = Integer.parseInt(request.getParameter("airticleId"));
		
		String ip = StringFilter(Tools.getIpAddr(request));
		
		log.info("ip:"+ip+" 查看了文章"+airticleId);
		
		Airticle airticle = new Airticle();
		
		airticle = airticleService.getAirticleDetail(airticleId);
		
		request.setAttribute("airticle", airticle);
		
		return "airticleDetail";
	}
	
	
	
	
}
