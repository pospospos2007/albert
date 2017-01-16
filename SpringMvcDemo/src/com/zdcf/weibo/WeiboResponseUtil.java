/**
 * 
 */
package com.zdcf.weibo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.json.JsonException;
import javax.json.JsonObject;


public class WeiboResponseUtil {
	
    private static Map<String,SimpleDateFormat> formatMap = new HashMap<String,SimpleDateFormat>();
    private static final long serialVersionUID = 3519962197957449562L;
    private transient int rateLimitLimit = -1;
    private transient int rateLimitRemaining = -1;
    private transient long rateLimitReset = -1;
    
	public static String withNonBmpStripped(String input) {
		return input.replaceAll("[^\\u0000-\\uFFFF]", "");
	}

	public static void ensureRootNodeNameIs(String rootName, Element elem) throws WeiboException {
		if (!rootName.equals(elem.getNodeName())) {
			throw new WeiboException("Unexpected root node name:" + elem.getNodeName() + ". Expected:" + rootName
					+ ". Check the availability of the Weibo API at http://open.t.sina.com.cn/.");
		}
	}

	public static void ensureRootNodeNameIs(String[] rootNames, Element elem) throws WeiboException {
		String actualRootName = elem.getNodeName();
		for (String rootName : rootNames) {
			if (rootName.equals(actualRootName)) {
				return;
			}
		}
		String expected = "";
		for (int i = 0; i < rootNames.length; i++) {
			if (i != 0) {
				expected += " or ";
			}
			expected += rootNames[i];
		}
		throw new WeiboException("Unexpected root node name:" + elem.getNodeName() + ". Expected:" + expected
				+ ". Check the availability of the Weibo API at http://open.t.sina.com.cn/.");
	}

	public static void ensureRootNodeNameIs(String rootName, Document doc) throws WeiboException {
		Element elem = doc.getDocumentElement();
		if (!rootName.equals(elem.getNodeName())) {
			throw new WeiboException("Unexpected root node name:" + elem.getNodeName() + ". Expected:" + rootName
					+ ". Check the availability of the Weibo API at http://open.t.sina.com.cn/");
		}
	}

	public static boolean isRootNodeNilClasses(Document doc) {
		String root = doc.getDocumentElement().getNodeName();
		return "nil-classes".equals(root) || "nilclasses".equals(root);
	}

	public static String getChildText(String str, Element elem) throws UnsupportedEncodingException {
		//return HTMLEntity.unescape(getTextContent(str, elem));
		return java.net.URLDecoder.decode(getTextContent(str, elem), "UTF-8");
	}

	public static String getTextContent(String str, Element elem) {
		NodeList nodelist = elem.getElementsByTagName(str);
		if (nodelist.getLength() > 0) {
			Node node = nodelist.item(0).getFirstChild();
			if (null != node) {
				String nodeValue = node.getNodeValue();
				return null != nodeValue ? nodeValue : "";
			}
		}
		return "";
	}

	/* modify by sycheng add "".equals(str) */
	public static int getChildInt(String str, Element elem) {
		String str2 = getTextContent(str, elem);
		if (null == str2 || "".equals(str2) || "null".equals(str)) {
			return -1;
		} else {
			return Integer.valueOf(str2);
		}
	}

	public static long getChildLong(String str, Element elem) {
		String str2 = getTextContent(str, elem);
		if (null == str2 || "".equals(str2) || "null".equals(str)) {
			return -1;
		} else {
			return Long.valueOf(str2);
		}
	}

	public static String getString(String name, JsonObject json, boolean decode) {
		String returnValue = null;
		try {
			returnValue = json.getString(name);
			if (decode) {
				try {
					returnValue = URLDecoder.decode(returnValue, "UTF-8");
				} catch (UnsupportedEncodingException ignore) {
				}
			}
		} catch (JsonException ignore) {
			// refresh_url could be missing
		}
		return returnValue;
	}

	public static boolean getChildBoolean(String str, Element elem) {
		String value = getTextContent(str, elem);
		return Boolean.valueOf(value);
	}

	public static Date getChildDate(String str, Element elem) throws WeiboException, UnsupportedEncodingException {
		return getChildDate(str, elem, "EEE MMM d HH:mm:ss z yyyy");
	}

	public static Date getChildDate(String str, Element elem, String format) throws WeiboException, UnsupportedEncodingException {
		return parseDate(getChildText(str, elem), format);
	}

	public static Date parseDate(String str, String format) throws WeiboException {
		if (str == null || "".equals(str)) {
			return null;
		}
		SimpleDateFormat sdf = formatMap.get(format);
		if (null == sdf) {
			sdf = new SimpleDateFormat(format, Locale.ENGLISH);
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			formatMap.put(format, sdf);
		}
		try {
			synchronized (sdf) {
				// SimpleDateFormat is not thread safe
				return sdf.parse(str);
			}
		} catch (ParseException pe) {
			throw new WeiboException("Unexpected format(" + str + ") returned from sina.com.cn");
		}
	}

	public static int getInt(String key, JsonObject json) throws JsonException {
		String str = json.getString(key);
		if (null == str || "".equals(str) || "null".equals(str)) {
			return -1;
		}
		return Integer.parseInt(str);
	}

	public static long getLong(String key, JsonObject json) throws JsonException {
		String str = json.getString(key);
		if (null == str || "".equals(str) || "null".equals(str)) {
			return -1;
		}
		return Long.parseLong(str);
	}

	public static boolean getBoolean(String key, JsonObject json) throws JsonException {
		String str = json.getString(key);
		if (null == str || "".equals(str) || "null".equals(str)) {
			return false;
		}
		return Boolean.valueOf(str);
	}

	public int getRateLimitLimit() {
		return rateLimitLimit;
	}

	public int getRateLimitRemaining() {
		return rateLimitRemaining;
	}

	public long getRateLimitReset() {
		return rateLimitReset;
	}
	
}
