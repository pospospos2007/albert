package com.zdcf.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdcf.action.LaboratoryAction.MyThread;
import com.zdcf.base.BaseAction;
import com.zdcf.dto.MessageDTO;
import com.zdcf.dto.ThemeDTO;
import com.zdcf.dto.ZhihuDTO;
import com.zdcf.model.FileExchange;
import com.zdcf.model.Message;
import com.zdcf.model.Theme;
import com.zdcf.model.User;
import com.zdcf.search.IChnlThemeSearchService;
import com.zdcf.search.IChnlZhihuSearchService;
import com.zdcf.search.entity.ChnlZhihuSearch;
//import com.zdcf.search.IChnlZhihuSearchService;
//import com.zdcf.search.entity.ChnlZhihuSearch;
import com.zdcf.search.param.ZhihuSearchParam;
import com.zdcf.service.FileService;
import com.zdcf.service.MessageService;
import com.zdcf.service.RobotService;
import com.zdcf.service.UserService;
import com.zdcf.tool.Const;
import com.zdcf.tool.PageVo;
import com.zdcf.tool.Tools;
import com.zdcf.tool.WebUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/message")
public class MessageAction extends BaseAction{

	private static Logger logger = Logger.getLogger(MessageAction.class);
	private static final int pageSize = 15;

	@Resource
	private MessageService messageService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private RobotService robotService;
	
	@Resource
	private FileService fileService;
	
//	@Autowired
//	private IChnlThemeSearchService themeSearchService;
	
	@Autowired
	private IChnlZhihuSearchService zhihuSearchService;
	
	@RequestMapping("/getAllTheme")
	public String getAllAirticle(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		
		
		PageVo<Map<String, Object>> pageVo = WebUtil.findPageVo(request);
		pageVo.setPageSize(pageSize);
		String currentPage =  "1";
		if(null!=request.getParameter("currentPage")){
			currentPage =request.getParameter("currentPage");
		}
		pageVo.setCurrentPage(Integer.parseInt(currentPage));
		pageVo = (PageVo<Map<String, Object>>) messageService.getThemeListPage(pageVo);
		model.addAttribute("pageView", pageVo);
		
//		if(!isMobile()){
			return "/message/themeListNew";
//		}else{
//			return "mobile/index";
//		}
		
	}
	
	//手机端论坛主题分页
	@RequestMapping("/findNextTheme")
	public @ResponseBody List<HashMap<String, Object>> findNextTheme(int index) throws Exception {
		
			List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

			list = messageService.findNextTheme(index,5);

			return list;
	}
	
	//手机端知乎日报主题分页
	@RequestMapping("/findNextZhihuAirticle")
	public @ResponseBody List<HashMap<String, Object>> findNextZhihuAirticle(int index) throws Exception {
		
			List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

			list = messageService.findNextZhihuAirticle(index,5);

			return list;
	}
	
	@RequestMapping("/toAddTheme")
	public String toAddTheme(ModelMap model,HttpServletRequest request){
		
		return "/message/addThemeNew";
	}
	
	
	
	public   String StringFilter(String   str)   throws   PatternSyntaxException   {     
        // 只允许字母和数字       
        // String   regEx  =  "[^a-zA-Z0-9]";                     
           // 清除掉所有特殊字符  
		  String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}‘；：”“’。，、？]";  
		  Pattern   p   =   Pattern.compile(regEx);     
		  Matcher   m   =   p.matcher(str);     
		  return   m.replaceAll("").trim();     
	}
	
	
	@RequestMapping(value ="/addTheme", method = RequestMethod.POST)
	public String addAirticle(HttpServletRequest request,HttpServletResponse response,@RequestParam("theme")String theme,@RequestParam("content") String content,ModelMap model,@RequestParam("code") String code) throws UnsupportedEncodingException{
		
		String realcode = request.getSession().getAttribute(Const.SESSION_IMAGE_CODE).toString();
		
		if(null==code||"".equals(code)||!code.equals(realcode)){
			return "redirect:/message/getAllTheme"; 
		}else{
			
			//验证成功后将session的验证码更新，防止再次使用此验证码发送主题
			String valcode  = "";
		       Random rd =  new Random();
		       for(int i=0; i<4; i++)
		           valcode+=rd.nextInt(10);
		       // 把产生的验证码存入到Session中
		       HttpSession  session = request.getSession();
		       session.setAttribute(Const.SESSION_IMAGE_CODE, valcode);
		}
		
		String ip = Tools.getNoHTMLString(StringFilter(getIpAddr(request)));
		
		User u = userService.getUserByIp(ip);
		
		Theme th = new Theme();
		
		th.setTheme(StringFilter(theme));
		
		if(null!=u){
			th.setUserId(u.getId());
		}else{
			userService.addUserByIp(ip);
			int userId = userService.getUserByIp(ip).getId();
			th.setUserId(userId);
		}
		
		th.setContent(content);
		
		logger.info("ip:"+ip+" 发表了一个主题");
		
		messageService.addTheme(th);
		
		return "redirect:/message/getAllTheme";
	}
	
	@RequestMapping("/getThemeDetail")
	public String getAirticleDetail(@RequestParam("id")int id,ModelMap model,HttpServletRequest request) throws UnsupportedEncodingException{
		
		String ip = StringFilter(getIpAddr(request));
		
		ThemeDTO theme = messageService.getThemeById(id);
		
		logger.info("ip:"+ip+" 查看了主题:"+theme.getTheme());
		
		model.addAttribute("theme", theme);
		
		List<MessageDTO> messages = messageService.getMessagesByThemeId(id);
		
		model.addAttribute("messages", messages);
		
//		if(!isMobile()){
			return "message/themeDetailNew";
//		}else{
//			return "mobile/themeDetail";
//		}
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
	
	
	@RequestMapping("/addMessage")
	public String addMessage(HttpServletRequest request,HttpServletResponse response,@RequestParam("message")String message,@RequestParam("themeId")int themeId,@RequestParam("messageCode")String messageCode) throws UnsupportedEncodingException{
		
		String realcode = request.getSession().getAttribute(Const.SESSION_IMAGE_CODE).toString();
		
		if(null==messageCode||"".equals(messageCode)||!messageCode.equals(realcode)){
			return "redirect:/message/getThemeDetail?id="+themeId;
		}else{
			//验证成功后将session的验证码更新，防止再次使用此验证码发送主题
			String valcode  = "";
	       Random rd =  new Random();
	       for(int i=0; i<4; i++)
	           valcode+=rd.nextInt(10);
	       // 把产生的验证码存入到Session中
	       HttpSession  session = request.getSession();
	       session.setAttribute(Const.SESSION_IMAGE_CODE, valcode);
		}
		
		String ip = Tools.getNoHTMLString(getIpAddr(request));
		
		User u = userService.getUserByIp(ip);
		
		Message me = new Message();
		
		int userIdToRobot;
		
		if(null!=u){
			me.setUserId(u.getId());
			userIdToRobot = u.getId();
		}else{
			userService.addUserByIp(ip);
			int userId = userService.getUserByIp(ip).getId();
			me.setUserId(userId);
			userIdToRobot = userId;
		}
		
		me.setMessage(message);
		
		me.setThemeId(themeId);
		
		logger.info("ip:"+ip+" 回复了一个帖子");
		
		messageService.addMessage(me);
		
		if(themeId==62788){
			JSONObject jsonObject = null;
			String result = robotService.getAnswerFromRobot(Tools.getNoHTMLString(message),userIdToRobot);
			jsonObject = JSONObject.fromObject(result);
			String answer = "@"+ip+" ";
			if(null!=jsonObject.get("text")){
				answer += jsonObject.get("text").toString();
			}
			if(null!=jsonObject.get("url")){
				answer+=" <a target='_blank' href='"+jsonObject.get("url").toString()+"'>网页链接</a> ";
			}
			
			Message robotMe = new Message();
			
			robotMe.setMessage(answer);
			
			robotMe.setThemeId(themeId);
			
			robotMe.setUserId(11);
			
			logger.info("robot回复了一个帖子:"+answer);
			
			messageService.addMessage(robotMe);
		}
		
		return "redirect:/message/getThemeDetail?id="+themeId;
	}
	
	
	/**
	 * 该方法是用来生成图形验证的.
	 */
	@RequestMapping(value = "/tuXingYanZhengMa")
	public void getImg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	       // 告知浏览当作图片处理
	       response.setContentType("image/jpeg");
	       // 告诉浏览器不缓存
	       response.setHeader("pragma", "no-cache");
	       response.setHeader("cache-control", "no-cache");
	       response.setHeader("expires", "0");
	       // 产生由4位数字构成的验证码
	       int length = 4;
	       String valcode  = "";
	       Random rd =  new Random();
	       for(int i=0; i<length; i++)
	           valcode+=rd.nextInt(10);
	       // 把产生的验证码存入到Session中
	       HttpSession  session = request.getSession();
	       session.setAttribute(Const.SESSION_IMAGE_CODE, valcode);
	       // 产生图片
	       int width = 100;
	       int height = 30;
	       BufferedImage img = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
	       // 获取一个Graphics
	       Graphics g = img.getGraphics();
	       // 填充背景色
	       g.setColor(Color.WHITE);
	       g.fillRect(0, 0, width, height);
	       // 填充干扰线50
	       for(int i=0; i<50; i++){
	           g.setColor(new Color(rd.nextInt(100)+155,rd.nextInt(100)+155,rd.nextInt(100)+155));
	           g.drawLine(rd.nextInt(width), rd.nextInt(height),rd.nextInt(width), rd.nextInt(height));
	       }
	       // 绘制边框
	       g.setColor(Color.GRAY);
	       g.drawRect(0, 0, width-1, height-1);
	       // 绘制验证码
	       Font[] fonts = {new Font("隶书",Font.BOLD,28),new Font("楷体",Font.BOLD,28),new Font("宋体",Font.BOLD,28),new Font("幼圆",Font.BOLD,18)};
	       for(int i=0; i<length; i++){
	           g.setColor(new Color(rd.nextInt(150),rd.nextInt(150),rd.nextInt(150)));
	           g.setFont(fonts[rd.nextInt(fonts.length)]);
	           g.drawString(valcode.charAt(i)+"", width/valcode.length()*i+2, 28);
	       }
	       // 输出图像
	       g.dispose();
	       ImageIO.write(img, "jpeg", response.getOutputStream());

	}


	public static int getPagesize() {
		return pageSize;
	}
	
	
	@RequestMapping("/getArticleFromZhihu")
	public String getArticleFromZhihu(ZhihuSearchParam searchParam,HttpServletRequest request,ModelMap model) throws IOException{
		
		
//		PageVo<Map<String, Object>> pageVo = WebUtil.findPageVo(request);
//		pageVo.setPageSize(pageSize);
//		String currentPage =  "1";
//		if(null!=request.getParameter("currentPage")){
//			currentPage =request.getParameter("currentPage");
//		}
//		pageVo.setCurrentPage(Integer.parseInt(currentPage));
//		pageVo = (PageVo<Map<String, Object>>) messageService.getZhihuArticleListPage(pageVo);
//		model.addAttribute("pageView", pageVo);
		
		String currentPage =  "1";
		if(null!=request.getParameter("currentPage")){
			currentPage =request.getParameter("currentPage");
		}
		
		searchParam.setI(Integer.valueOf(currentPage));
		
		List<ChnlZhihuSearch> list = zhihuSearchService.search(searchParam);
		for (ChnlZhihuSearch chnlZhihuSearch : list) {
			FileExchange fileExchange =fileService.getFileExchange(chnlZhihuSearch.getImages());
			if(null==fileExchange){
				FileExchange newfileExchange = new FileExchange();
				HttpClient client = new HttpClient();
				GetMethod get = new GetMethod(chnlZhihuSearch.getImages());
				String fileExt = FilenameUtils.getExtension(chnlZhihuSearch.getImages());
				String newUrl = UUID.randomUUID().toString().replaceAll("-", "")+"."+fileExt;
				newfileExchange.setOldUrl(chnlZhihuSearch.getImages());
				newfileExchange.setNewUrl(newUrl);
				
				File storeFile = new File(request.getSession().getServletContext().getRealPath("/")+"uploadfile/"+newUrl);
				FileOutputStream output = null;
				try{
				    client.executeMethod(get);
				    output = new FileOutputStream(storeFile);
				    output.write(get.getResponseBody());
				    output.close();
				}catch (HttpException e)
				{
				    e.printStackTrace();
				}
				//存储redis和数据库
				fileService.addFileExchange(newfileExchange);
				chnlZhihuSearch.setImages(newfileExchange.getNewUrl());
			}else{
				chnlZhihuSearch.setImages( fileExchange.getNewUrl());
			}
		}
		
		model.addAttribute("list", list);
		
		model.addAttribute("searchParam", searchParam);
		
		return "/message/zhihuAiticleList";
	}
	
	
	@RequestMapping("/getZhihuArticleDetail")
	public String getZhihuArticleDetail(@RequestParam("id")int id,ModelMap model,HttpServletRequest request) throws UnsupportedEncodingException{
		
		String ip = StringFilter(getIpAddr(request));
		
		ZhihuDTO zhihu = messageService.getZhihuDetailById(id);
		
		logger.info("ip:"+ip+" 查看了知乎日报的文章：《"+zhihu.getTitle()+"》");
		
		model.addAttribute("zhihu", zhihu);
		
//		if(!isMobile()){
			return "/message/zhihuArticleDetailforPC";
//		}else{
//			return "/message/zhihuArticleDetail";
//			
//		}
	}
	
	@ResponseBody
	@RequestMapping("/queryKeyWord")
	public  Map<String, Object> mutiThreads(ZhihuSearchParam searchParam){
		
			searchParam.setS(5);
			Map<String, Object> map = new HashMap<String, Object>();
			List<ChnlZhihuSearch> list = zhihuSearchService.search(searchParam);
			
			map.put("list", list);
			
			return map;
	}
	 
	
	
	
}
