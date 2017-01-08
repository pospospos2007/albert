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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/lab")
public class LaboratoryAction {
	
	private volatile  int ticket=100;

	private static Logger logger = Logger.getLogger(LaboratoryAction.class);
	
	@RequestMapping("/toLab")
	public String toLab(HttpServletRequest request,HttpServletResponse respons) throws UnsupportedEncodingException{
		
		String ip = Tools.getNoHTMLString(Tools.getIpAddr(request));
		
		
		logger.info("ip:"+ip+" 进入实验室");
		
		
		
		return "lab/lab";
	}
	
	@RequestMapping("/toDraw")
	public String toDraw(HttpServletRequest request,HttpServletResponse respons){
		
		String ip = Tools.getNoHTMLString(Tools.getIpAddr(request));
		
		logger.info("ip:"+ip+" 进入多呗绘画板");
		
		return "lab/draw";
	}
	
	@ResponseBody
	@RequestMapping("/mutiThreads")
	public  Map<String, Object> mutiThreads(){
		Map<String, Object> map = new HashMap<String, Object>();
		 MyThread mt = new MyThread();
		 new Thread(mt).start();
		map.put("msg", (ticket));
//		System.out.println(ticket--);
		 return map;
	}
	
	
	class MyThread implements Runnable
	{
//	    private int ticket = 10;
		
	    public void run()
	    {
//	        for (int i = 0; i < 20; i++)
//	        {
	            if (ticket > 0)
	            {
	                System.out.println("卖票：ticket" + ticket--);
	            }
//	        }
	    }
	}

	
	
	
	
}
