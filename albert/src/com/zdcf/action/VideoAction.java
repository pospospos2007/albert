package com.zdcf.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/video")
public class VideoAction {

	
	@RequestMapping("/toVideoList")
	public String toGameList(HttpServletRequest request,HttpServletResponse respons){
		
		return "video/videoList";
	}
	
	@RequestMapping("/superman")
	public String superman(HttpServletRequest request,HttpServletResponse respons){
		
		return "video/superman";
	}
	
	@RequestMapping("/gameofThrones")
	public String gameofThrones(HttpServletRequest request,HttpServletResponse respons){
		
		return "gameofThrones";
	}
	

	
	
	
}
