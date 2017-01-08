/**
 * action基础类
 * add by yuhaibin 2012-9-2
 */
package com.zdcf.base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zdcf.model.FileExchange;
import com.zdcf.model.Movie;
import com.zdcf.service.FileService;
import com.zdcf.service.ICacheService;
import com.zdcf.tool.HttpRequestDeviceUtils;

/**
 * 基础类
 * 
 */
public class BaseAction  {
	
	@Autowired
	private ICacheService cacheService;
	
	@Autowired
	private FileService fileService;
	/**
	   * 方法描述:取得request的请求
	   * 创建人：zhangsg  
	   * 创建时间：2014-9-2 晚上19:30:05
	   * @throws
	   */
	public HttpServletRequest getRequest() {
		 return  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	} 
	
	/**
	 * 判断用户是否为手机登陆用户
	 * @return true 表示是手机登陆用户
	 */
	public boolean isMobile() {
		return HttpRequestDeviceUtils.isMobile(getRequest());
	}
	/**
	 * 根据用户session中存储的是否为手机的客户端来确定返回的字符串
	 * @param returnStr 
	 * @return 如果为手机就返回 returnStr+"Mobile" 否则返回 returnStr
	 */
	public String returnIsMobileStr(String returnStr) {
	
		if (isMobile())
			return returnStr+"Mobile";
		else 
			return returnStr;
		
	}
	
	
	
}
