package com.zdcf.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

@Controller
@RequestMapping("/")
public class UserAction {

	private static Logger logger = Logger.getLogger(UserAction.class);
	
	@Resource
	private UserMapper mapper;


//	@RequestMapping("/getUserList")
//	public String getAllUser(HttpServletRequest request){
//		
//		List<User> users = new ArrayList<User>();
//		
//		users = mapper.getUserList();
//		
//		//System.out.println("user="+users.get(0).getUserName());
//	
//		request.setAttribute("users", users);
//
//		return "/userList";
//	}
	
	@RequestMapping("/goForFun")
	public String goForFun(HttpServletRequest request){
		
		return "/randomBooks";
	}
	
	@RequestMapping("/goForMusic")
	public String goForMusic(HttpServletRequest request){
		
		return "/musicRedio";
	}
	
	@RequestMapping("/goForBlog")
	public String goForBlog(HttpServletRequest request){
		
		return "/myBlogIndex";
	}

	
	
	
	
}
