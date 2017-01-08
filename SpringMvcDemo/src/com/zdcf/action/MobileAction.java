package com.zdcf.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zdcf.mapper.AirticleMapper;
import com.zdcf.mapper.UserMapper;
import com.zdcf.model.Airticle;
import com.zdcf.model.User;
import com.zdcf.service.AirticleService;
import com.zdcf.service.MessageService;
import com.zdcf.tool.EncodingTool;
import com.zdcf.tool.PageVo;
import com.zdcf.tool.Tools;
import com.zdcf.tool.WebUtil;
import com.zdcf.base.BaseAction;

@Controller
@RequestMapping("/mobile")
public class MobileAction extends BaseAction{

	private static Logger logger = Logger.getLogger(MobileAction.class);
	
	@Resource
	private AirticleService airticleService;
	
	@Resource
	private MessageService messageService;

	//跳转“我的账户”
	@RequestMapping("/account")
	public String account(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		return "mobile/user_n";
	}
	
	//跳转“更多”
	@RequestMapping("/more")
	public String more(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		return "mobile/log_more";
	}
	
	//跳转“联系我”
	@RequestMapping("/contact")
	public String contact(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		return "mobile/contact";
	}
	
	
}
