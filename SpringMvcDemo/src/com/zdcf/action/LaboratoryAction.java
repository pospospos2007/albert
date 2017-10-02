package com.zdcf.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdcf.tool.Tools;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/lab")
public class LaboratoryAction {
	
	private volatile  int ticket=100;

	@RequestMapping("/toLab")
	public String toLab(HttpServletRequest request,HttpServletResponse respons) throws UnsupportedEncodingException{
		
		String ip = Tools.getNoHTMLString(Tools.getIpAddr(request));
		
		
		log.info("ip:"+ip+" 进入实验室");
		
		
		
		return "lab/lab";
	}
	
	@RequestMapping("/toDraw")
	public String toDraw(HttpServletRequest request,HttpServletResponse respons){
		
		String ip = Tools.getNoHTMLString(Tools.getIpAddr(request));
		
		log.info("ip:"+ip+" 进入多呗绘画板");
		
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
