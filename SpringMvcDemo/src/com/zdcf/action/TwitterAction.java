package com.zdcf.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdcf.base.Constants;
import com.zdcf.model.TwitterPost;
import com.zdcf.service.TwitterMediaService;
import com.zdcf.service.TwitterPostService;
import com.zdcf.service.TwitterUserService;
import com.zdcf.tool.PageVo;
import com.zdcf.tool.Tools;
import com.zdcf.tool.WebUtil;

@Controller
@RequestMapping("/twitter")
public class TwitterAction {

	private static Logger logger = Logger.getLogger(TwitterAction.class);
	
	private static final int pageSize = 10;
	
	@Autowired
	private TwitterPostService twitterPostService;
	
	@Autowired
	private TwitterUserService twitterUserService;
	
	@Autowired
	private TwitterMediaService twitterMediaService;
	
	@RequestMapping("/index")
	public String toGameList(HttpServletRequest request,ModelMap model){
		
		String ip = Tools.getNoHTMLString(getIpAddr(request));
		
		logger.info("ip:"+ip+"进入推特网页端主页");
		TwitterPost tp =new TwitterPost();
		tp.setPostType(Constants.TWITTER_POST_TYPE.MY_POST);
		PageVo<Map<String, Object>> pageVo = WebUtil.findPageVo(request);
		pageVo.setPageSize(pageSize);
		String currentPage =  "1";
		if(null!=request.getParameter("currentPage")){
			currentPage =request.getParameter("currentPage");
		}
		pageVo.setCurrentPage(Integer.parseInt(currentPage));
		pageVo = (PageVo<Map<String, Object>>) twitterPostService.getListPage(pageVo,tp);
		model.addAttribute("pageView", pageVo);
		
		return "twitter/index";
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
