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
import com.zdcf.tool.EncodingTool;

@Controller
@RequestMapping("/video")
public class VideoAction {

	private static Logger logger = Logger.getLogger(VideoAction.class);
	
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
