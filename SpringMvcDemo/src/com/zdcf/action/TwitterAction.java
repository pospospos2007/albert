package com.zdcf.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdcf.base.Constants;
import com.zdcf.model.FileExchange;
import com.zdcf.model.TwitterPost;
import com.zdcf.model.TwitterSearchHistory;
import com.zdcf.model.User;
import com.zdcf.service.FileService;
import com.zdcf.service.TwitterMediaService;
import com.zdcf.service.TwitterPostService;
import com.zdcf.service.TwitterSearchService;
import com.zdcf.service.TwitterUserService;
import com.zdcf.tool.PageVo;
import com.zdcf.tool.ProxyUtil;
import com.zdcf.tool.StringUtil;
import com.zdcf.tool.Tools;
import com.zdcf.tool.UserSessionUtil;
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
	
	@Autowired
	private TwitterSearchService twitterSearchService;
	
	@Autowired
	private FileService fileService;
	
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
	
	/**
	 * 检查是否调用超限，是否未登录，是否今天已经搜索过这个内容和用户，如都不满足，调用推特接口更新数据,增加搜索记录或者只更新数据
	 * @param request
	 * @param model
	 * @param key
	 * @param searchType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/check")
	public Map<String, Object> check(HttpServletRequest request,ModelMap model,String searchKey,Integer searchType){
		Map<String, Object> map =this.initMapStatus();
		String ip = Tools.getNoHTMLString(getIpAddr(request));
		logger.info("ip:"+ip+"搜索了"+(Constants.TWITTER_SEARCH_TYPE.SEARCH_USER.equals(searchType) ? "用户：":"内容：")+searchKey);
//		User u = UserSessionUtil.currentUser();
//		if(null==u){
//			map.put("status", Boolean.FALSE);
//			map.put("msg", "请登录后搜索！");
//			return map;
//		}
		if(StringUtil.isEmpty(searchKey)){
			map.put("status", Boolean.FALSE);
			map.put("msg", "请输入搜索内容！");
			return map;
		}
		TwitterSearchHistory tsh = new TwitterSearchHistory();
		tsh.setSearchKey(searchKey.trim());
		tsh.setSearchType(searchType);
		long startTime = System.currentTimeMillis();//获取当前时间
		try{
			Map<String, Object> resultMap =twitterSearchService.check(tsh);
			map.put("status", resultMap.get("status"));
			map.put("msg",resultMap.get("msg"));
			map.put("searchId",resultMap.get("searchId"));
		}catch(Exception e){
			map.put("status", Boolean.FALSE);
			map.put("msg","系统错误,请联系Albert："+e.getMessage());
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		logger.info("程序运行时间："+(endTime-startTime)+"ms");
	    return map;  
	}
	
	@RequestMapping("/search")
	public String search(HttpServletRequest request,ModelMap model,Long searchId,String searchKey,Integer searchType){
		
//		User u = UserSessionUtil.currentUser();
//		if(null==u){
//			return "index/login";
//		}
		
		TwitterSearchHistory tsh = new TwitterSearchHistory();
		tsh.setId(searchId);
		tsh.setSearchType(searchType);
		tsh.setSearchKey(searchKey);

		PageVo<Map<String, Object>> pageVo = WebUtil.findPageVo(request);
		pageVo.setPageSize(pageSize);
		String currentPage =  "1";
		if(null!=request.getParameter("currentPage")){
			currentPage =request.getParameter("currentPage");
		}
		pageVo.setCurrentPage(Integer.parseInt(currentPage));
		pageVo = (PageVo<Map<String, Object>>) twitterSearchService.getListPage(pageVo,tsh);
		model.addAttribute("pageView", pageVo);
		model.addAttribute("tsh", tsh);
		
		return "twitter/search";
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
	
	
	@ResponseBody
	@RequestMapping("/fileExchange")
	public Map<String, Object> fileExchange(String url,HttpServletRequest request) throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		FileExchange fileExchange =null;
		try{
			fileExchange =fileService.getFileExchange(url);
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		 
		if(null==fileExchange){
			FileExchange newfileExchange = new FileExchange();
			DefaultHttpClient httpClient = (DefaultHttpClient) ProxyUtil.getHttpClient();
	        OutputStream out = null;
	        InputStream in = null;
			
			String fileExt = FilenameUtils.getExtension(url);
			if(StringUtil.isEmpty(fileExt)){
				fileExt ="png";
			}
			String newUrl = UUID.randomUUID().toString().replaceAll("-", "")+"."+fileExt;
			newfileExchange.setOldUrl(url);
			newfileExchange.setNewUrl(newUrl);
			
			HttpGet httpGet = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            in = entity.getContent();

            long length = entity.getContentLength();
            if (length <= 0) {
                System.out.println("下载文件不存在！");
            }

            File file = new File(request.getSession().getServletContext().getRealPath("/")+"uploadfile/"+newUrl);
            if(!file.exists()){
                file.createNewFile();
            }
            
            out = new FileOutputStream(file);  
            
            IOUtils.copy(in, out);
            
            out.flush();
            
            //避免资源泄露,分开关闭
            try{
	            if(in != null){
	                in.close();
	            }
            }catch(Exception e){
            	e.printStackTrace();
            }
            
            try{
            	if(out != null){
                    out.close();
                }
            }catch(Exception e){
            	e.printStackTrace();
            }
            
            
			
			fileService.addFileExchange(newfileExchange);
			map.put("url", newfileExchange.getNewUrl());
		}else{
			map.put("url", fileExchange.getNewUrl());
		}
		logger.info(map.get("url"));
		return map;
		
	}
	
}
