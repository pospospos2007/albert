package com.zdcf.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdcf.model.FileExchange;
import com.zdcf.model.GoogleSearchResult;
import com.zdcf.service.FileService;
import com.zdcf.service.GoogleService;
import com.zdcf.tool.ProxyUtil;
import com.zdcf.tool.StringUtil;
import com.zdcf.tool.Tools;

@Controller
@RequestMapping("/google")
public class GoogleAction {

	private static Logger logger = Logger.getLogger(GoogleAction.class);
	
	private static final int pageSize = 10;
	
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private GoogleService googleService;
	
	@RequestMapping("/index")
	public String toGoogleList(HttpServletRequest request,ModelMap model){
		
		String ip = Tools.getNoHTMLString(getIpAddr(request));
		
		logger.info("ip:"+ip+"进入google搜索");
		
		return "google/index";
	}
	
	
	@RequestMapping("/search")
	public String search(HttpServletRequest request,ModelMap model,String wd,Integer start) throws ClientProtocolException, IOException{
		
		String ip = Tools.getNoHTMLString(getIpAddr(request));
		String temp =wd;
		
		if(null==start){
			wd = wd+"&start="+1+"&num=10";
			start =1;
		}else{
			wd = wd+"&start="+start+"&num=10";
		}
		
		List<GoogleSearchResult>  list = googleService.search(wd);
		
		logger.info("ip:"+ip+"搜索了"+temp);
		
		model.addAttribute("list", list);
		model.addAttribute("wd", temp);
		model.addAttribute("start", start);
		
		return "google/search";
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
		FileExchange fileExchange =fileService.getFileExchange(url);
		if(null==fileExchange){
			FileExchange newfileExchange = new FileExchange();
			DefaultHttpClient httpClient = (DefaultHttpClient) ProxyUtil.getHttpClient();
	        OutputStream out = null;
	        InputStream in = null;
			
			String fileExt = FilenameUtils.getExtension(url);
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
            
//            byte[] buffer = new byte[10240];
//            int readLength = 0;
//            while ((readLength=in.read(buffer)) > 0) {
//                byte[] bytes = new byte[readLength];
//                System.arraycopy(buffer, 0, bytes, 0, readLength);
//                out.write(bytes);
//            }
            
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
		return map;
		
	}
	
}
