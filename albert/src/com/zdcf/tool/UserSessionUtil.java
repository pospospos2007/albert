package com.zdcf.tool;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zdcf.base.Constants;
import com.zdcf.model.User;

public class UserSessionUtil {
	public static final int currentUserId(){
		return currentUser().getId();
	}
	
	public static final String currentUserName(){
		return "";
	}

	public static final Locale currentLocale(){
		return Locale.CHINESE;
	}

	public static User currentUser() {
		User user = null;
		Object obj = getObjectFromSession(Constants.USER_SESSION_KEY);
		if (obj != null) {
			user = (User) obj;
		} 

		return user;
	}
	
	
	public static Object getObjectByKey(String key) {
		return getObjectFromSession(key);
	}
	
	
	private static Object getObjectFromSession(String attributeKey) {
		HttpSession session = getSession();
		if (session != null) {
			return session.getAttribute(attributeKey);
		}

		return null;
	}

	public static void setUser(User user) {
		addSessionAttribute(Constants.USER_SESSION_KEY, user);
	}
	
	
	public static void setObjectToSession(Object obj,String key) {
		addSessionAttribute(key, obj);
	}
	

	private static void addSessionAttribute(String attributeKey, Object object) {
		HttpSession session = getSession();
		if (session != null) {
			session.setAttribute(attributeKey, object);
		}
	}
	private static HttpSession getSession() {
		HttpServletRequest request = getRequest();

		if (request != null) {
			return request.getSession();
		}

		return null;
	}

	private static HttpServletRequest getRequest(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		return request;
	}

	public static void updateUser(User user) {
		if(currentUser() != null){
			addSessionAttribute(Constants.USER_SESSION_KEY, user);
		}
	}

	//当前用户是否登录
	public static boolean getCurrentUserIsLogin(){
		return UserSessionUtil.currentUser()==null?false:true;
	}
	
	//获取环境语言
	public static String getSysLanguage(){
        Locale locale = LocaleContextHolder.getLocale(); 
		if(locale != null){
			return locale.getLanguage() + "_" + locale.getCountry();
		} else {
			return Constants.LOCALE_LANGUAGE.en_US;
		}
	}
	
	
	
}
