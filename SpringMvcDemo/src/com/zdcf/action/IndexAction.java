package com.zdcf.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.zdcf.base.BaseAction;
import com.zdcf.base.Constants;
import com.zdcf.model.User;
import com.zdcf.service.AirticleService;
import com.zdcf.service.MessageService;
import com.zdcf.service.UserService;
import com.zdcf.tool.Tools;
import com.zdcf.tool.UserSessionUtil;

@Controller
@RequestMapping("/")
public class IndexAction extends BaseAction{

	private static Logger logger = Logger.getLogger(IndexAction.class);
	
	private static final int pageSize = 10;

	@Resource
	private AirticleService airticleService;
	
	@Resource
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		
//		if(!isMobile()){
			return "/index/index";
//		}else{
//			List<HashMap<String, Object>> themelist = new ArrayList<HashMap<String, Object>>();
//			themelist = messageService.findNextTheme(0,10);
//			model.addAttribute("themelist", themelist);
//			
//			List<HashMap<String, Object>> zhihuAirticlelist = new ArrayList<HashMap<String, Object>>();
//			zhihuAirticlelist = messageService.findNextZhihuAirticle(0, 10);
//			model.addAttribute("zhihuAirticlelist", zhihuAirticlelist);
//			
//			return "mobile/index";
//		}
	}
	
	@RequestMapping("/loginvalidate")
	public @ResponseBody Map<String, Object> loginvalidate(@RequestParam(value="username", required=false) String username,@RequestParam(value="password", required=false) String pwd,HttpSession httpSession){
		
		Map<String, Object> map = this.initMapStatus();
		
		if(null==username||null==pwd){
			map.put("status", Boolean.FALSE);
			return map;
		}
			
		User user=userService.getUserByName(username);
		if(user!=null&&user.getPassword()!=null&&pwd.equals(user.getPassword()))
		{
			UserSessionUtil.setUser(user);
			
			map.put("status", Boolean.TRUE);
		}else
			map.put("status", Boolean.FALSE);
		
		return map;
	}
	
	@RequestMapping("/checkUsername")
	public @ResponseBody Map<String, Object> checkUsername(@RequestParam(value="username") String username){
		
		Map<String, Object> map = this.initMapStatus();
		
		if(null==username){
			map.put("status", Boolean.FALSE);
			return map;
		}
		User user=userService.getUserByName(username);
		if(user!=null)
		{
			map.put("status", Boolean.FALSE);
		}else
			map.put("status", Boolean.TRUE);
		
		return map;
	}
	
	@RequestMapping("/login")
	public String login(){
		return "index/login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession httpSession){
		httpSession.removeAttribute(Constants.USER_SESSION_KEY);
		//TODO 删除在登陆后放入的浏览器的cookie
		return "index/login";
	}
	
	
	/**
	 * 国家化语言
	 *
	 * @param modelMap
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "/changeLanguage", method = {RequestMethod.POST,RequestMethod.GET})
	public Map changeLanguage( 
			@RequestParam(
					value="language", required=false) String language,
					HttpServletRequest request,
					HttpServletResponse reponse,
					ModelMap modelMap)  {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("msg","");
		map.put("res","false");
		try {
			request.getSession().setAttribute(Constants.SYS_LANGUAGE_SESSION_KEY, language);
			LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
			if (localeResolver == null) {
				logger.debug("No LocaleResolver found: not in a DispatcherServlet request?");
			}
			LocaleEditor localeEditor = new LocaleEditor();
			localeEditor.setAsText(language);
			localeResolver.setLocale(request, reponse, (Locale) localeEditor.getValue());
			map.put("res", "true");
		}
		catch(Exception e){
			logger.debug("修改系统语言 /changeLanguage.do", e); 
		}
		return map;  
	}
	
	
	/**
	 * 日历
	 */
	@RequestMapping("/calendar")
	public String calendar(){
		return "index/calendar";
	}	
	
	@RequestMapping("/toRegister")
	public String toRegister(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		return "index/register";
	}
	
	@RequestMapping("/register")
	public String register(String username,String password,@RequestParam(
			value="email", required=false) String email,ModelMap model,HttpServletRequest request){
		
		User u=userService.getUserByName(username);
		if(u!=null){
			return "index/login";
		}
		String ip = Tools.getNoHTMLString(getIpAddr(request));
		User user =new User();
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		user.setIp(ip);
		userService.register(user);
		logger.info("ip:"+ip+" 注册了"+username);
		UserSessionUtil.setUser(user);
		return "redirect:/chatroom/toChatroom";
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
	
	public Map<String, Object> initMapStatus() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Boolean.TRUE);
		map.put("msg", "");
		return map;
	}
	
	@RequestMapping("/userInfo")
	public String userInfo(@RequestParam(value="id", required=false)Integer id,ModelMap model){
		if(null!=id){
			User user =userService.getUserById(id);
			model.addAttribute("user", user);
		}
		return "index/userInfo";
	}
	
	
}
