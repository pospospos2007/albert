package com.zdcf.tool;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class WebUtil implements IGlobalsKeys
{

	private static final long serialVersionUID = -5325889677001839303L;
	public static String PAGE_SAVE_LOCATION;
	public static String PAGE_SAVING_ACTION_LIST_KEY = "PAGE_SAVING_ACTION_LIST_KEY";
	public static String ORDER_STR_SAVING_ACTION_LIST_KEY = "ORDER_STR_SAVING_ACTION_LIST_KEY";
	
	private static Log logger = LogFactory.getLog(WebUtil.class);
	
	/**
	 * 加载分页默认配置
	 */
	public static PageVo findPageVo(HttpServletRequest request)
	{
		try
		{

			Object o = request.getParameter("PAGE_NO_KEY");
			if (o != null && (o instanceof String))
			{
//				int recordCount = Integer.parseInt((String) o);
				
				String PAGE_RECORDCOUNT_KEY = request.getParameter("PAGE_RECORDCOUNT_KEY");
				int recordCount = Integer.parseInt(PAGE_RECORDCOUNT_KEY);
				return createPageVo(request, recordCount);
			}
			else
			{
				PageVo pageVo = new PageVo();
				pageVo.setRecordCount(0);
				pageVo.setPageSize(10);
				pageVo.setCurrentPage(1);
				pageVo.rePageCount();
				return pageVo;
			}
		}
		catch (NumberFormatException e)
		{
			logger.error("格式化页码异常",e);
		}
		return null;
	}

	/**
	 * 创建pagevo对象
	 * 
	 * @param request
	 * @param recordCount
	 * @return
	 */
	public static PageVo createPageVo(HttpServletRequest request, int recordCount)
	{
		PageVo pageVo = new PageVo();
		int pageNo = 0;
		int pageSize = 10; // 默认10条记录
		if (request.getParameter("PAGE_NO_KEY") != null)
			try
			{
				String s = request.getParameter("PAGE_NO_KEY");
				pageNo = Integer.parseInt(s);
			}
			catch (Exception e)
			{
				// getIlog().error("error in createPageVo: ", e);
			}
		if (request.getParameter("PAGE_SIZE_KEY") != null)
			try
			{
				String s = request.getParameter("PAGE_SIZE_KEY");
				pageSize = Integer.parseInt(s);
			}
			catch (Exception e)
			{
				// getIlog().error("error in createPageVo: ", e);
			}
		pageVo.setRecordCount(recordCount);
		pageVo.setPageSize(pageSize);
		pageVo.setCurrentPage(pageNo);

		return pageVo;
	}
	/**
	 * 处理金额显示千分位的问题
	 * @param moneyValueStr
	 * @return
	 */
	public static String parseMoney(String moneyValueStr){
        DecimalFormat a = new DecimalFormat("");
        Double dd = Double.parseDouble(moneyValueStr);
        String pattern="' '###,###.##' ';''###,###.##''";
        a.applyPattern(pattern);
        return a.format(dd).trim(); 
	}
	
	/**
	 * 改变URL协议，并返回使用默认端口的URL字符串（不包含查询串）
	 * @param url
	 * @return
	 */
	public static String changeSchemeWithDefaultPort(String strUrl, String newScheme) {
		try {
			URL urlObj = new URL(strUrl);
			int port = (urlObj.getPort() == urlObj.getDefaultPort()) ? -1 : urlObj.getPort();
			urlObj = new URL(newScheme, urlObj.getHost(), port, urlObj.getPath());
			return urlObj.toURI().toString();
		} catch (MalformedURLException e) {
			return strUrl;
		} catch (URISyntaxException e) {
			return strUrl;
		}
	}
	
	/**
	 * 若一个URL使用了默认端口，则移除端口号（不包含查询串）
	 * @param strUrl
	 * @return
	 */
	public static String removeDefaultPort(String strUrl) {
		try {
			URL urlObj = new URL(strUrl);
			if (urlObj.getPort() == urlObj.getDefaultPort()) {
				urlObj = new URL(urlObj.getProtocol(), urlObj.getHost(), -1, urlObj.getPath());
			}
			return urlObj.toURI().toString();
		} catch (MalformedURLException e) {
			return strUrl;
		} catch (URISyntaxException e) {
			return strUrl;
		}
	}

	public static void writeStr(HttpServletResponse response,String str) throws IOException {
		response.setContentType("html/text");
			response.getWriter().write(str);
			response.getWriter().flush();
			response.getWriter().close();
	}
	
	
	
	
}
