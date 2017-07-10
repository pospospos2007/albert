package com.zdcf.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdcf.base.BaseAction;

@Controller
@RequestMapping("/")
public class DruidAction extends BaseAction {

	
	@RequestMapping("/druid")
	public String getAllAirticle(HttpServletRequest request,HttpServletResponse response){
		return "redirect:/druid/index.html";
	}
	
	
}
