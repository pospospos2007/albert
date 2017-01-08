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
import com.zdcf.service.AirticleService;
import com.zdcf.service.MessageService;

@Controller
@RequestMapping("/")
public class IndexAction extends BaseAction{

	private static Logger logger = Logger.getLogger(IndexAction.class);
	
	private static final int pageSize = 10;

	@Resource
	private AirticleService airticleService;
	
	@Resource
	private MessageService messageService;
	
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
	
	@RequestMapping("/login")
	public String login(){
		return "index/login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession httpSession){
		httpSession.removeAttribute("username");
		httpSession.removeAttribute("uid");
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
	
	
}
