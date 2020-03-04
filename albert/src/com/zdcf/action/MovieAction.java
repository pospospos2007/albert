package com.zdcf.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdcf.model.FileExchange;
import com.zdcf.model.Movie;
import com.zdcf.search.IChnlMovieSearchService;
import com.zdcf.search.entity.ChnlMovieSearch;
import com.zdcf.search.param.MovieSearchParam;
import com.zdcf.service.FileService;
import com.zdcf.service.MovieService;
import com.zdcf.tool.Tools;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/movie")
public class MovieAction {

	@Autowired
	private IChnlMovieSearchService movieSearchService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private MovieService movieService;

	@RequestMapping("/toMovieList")
	public String toMovieList(MovieSearchParam searchParam,HttpServletRequest request,ModelMap model) throws IOException{
		
		String ip = Tools.getNoHTMLString(getIpAddr(request));
		
		log.info("ip:"+ip+" 进入电影列表");
		
		String currentPage =  "1";
		if(null!=request.getParameter("currentPage")){
			currentPage =request.getParameter("currentPage");
		}
		
		searchParam.setI(Integer.valueOf(currentPage));
		
//		List<ChnlMovieSearch> list = null;
		List<ChnlMovieSearch> list = movieSearchService.search(searchParam);
		
//		for (ChnlMovieSearch chnlMovieSearch : list) {
//			
//			FileExchange fileExchange =fileService.getFileExchange(chnlMovieSearch.getImg());
//			if(null==fileExchange){
//				FileExchange newfileExchange = new FileExchange();
//				HttpClient client = new HttpClient();
//				GetMethod get = new GetMethod(chnlMovieSearch.getImg());
//				String fileExt = FilenameUtils.getExtension(chnlMovieSearch.getImg());
//				String newUrl = UUID.randomUUID().toString().replaceAll("-", "")+"."+fileExt;
//				newfileExchange.setOldUrl(chnlMovieSearch.getImg());
//				newfileExchange.setNewUrl(newUrl);
//				
//				File storeFile = new File(request.getSession().getServletContext().getRealPath("/")+"uploadfile/"+newUrl);
//				FileOutputStream output = null;
//				try{
//				    client.executeMethod(get);
//				    output = new FileOutputStream(storeFile);
//				    output.write(get.getResponseBody());
//				    output.close();
//				}catch (HttpException e)
//				{
//				    e.printStackTrace();
//				}
//				//存储redis和数据库
//				fileService.addFileExchange(newfileExchange);
//				chnlMovieSearch.setImg(newfileExchange.getNewUrl());
//			}else{
//				chnlMovieSearch.setImg( fileExchange.getNewUrl());
//			}
//		}
		
		model.addAttribute("list", list);
		
		model.addAttribute("searchParam", searchParam);
		return "movie/movieList";
	}
	
	@RequestMapping("/getMovieDetail")
	public String getMovieDetail(@RequestParam("id")int id,ModelMap model,HttpServletRequest request) throws UnsupportedEncodingException{
		
		String ip = getIpAddr(request);
		
		Movie movie = movieService.getMovieById(id);
		
		log.info("ip:"+ip+" 查看了电影：《"+movie.getName()+"》");
		
		model.addAttribute("movie", movie);
		
		return "/movie/movieDetail";
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
	
	
	@ResponseBody
	@RequestMapping("/queryKeyWord")
	public  Map<String, Object> mutiThreads(MovieSearchParam searchParam){
		
			searchParam.setS(5);
			Map<String, Object> map = new HashMap<String, Object>();
//			List<ChnlMovieSearch> list = null;
			List<ChnlMovieSearch> list = movieSearchService.search(searchParam);
			
			map.put("list", list);
			
			return map;
	}

	@ResponseBody
	@RequestMapping("/fileExchange")
	public Map<String, Object> fileExchange(String url,HttpServletRequest request) throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		FileExchange fileExchange =fileService.getFileExchange(url);
		if(null==fileExchange){
			FileExchange newfileExchange = new FileExchange();
			HttpClient client = new HttpClient();
			GetMethod get = new GetMethod(url);
			
			String fileExt = FilenameUtils.getExtension(url);
			String newUrl = UUID.randomUUID().toString().replaceAll("-", "")+"."+fileExt;
			newfileExchange.setOldUrl(url);
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
			map.put("url", newfileExchange.getNewUrl());
		}else{
			map.put("url", fileExchange.getNewUrl());
		}
		return map;
		
	}
	
	
	
}
