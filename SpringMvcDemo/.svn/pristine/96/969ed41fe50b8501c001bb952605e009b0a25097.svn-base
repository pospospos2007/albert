package com.zdcf.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class DateUtil {
	public static final String TIMEFULL = "yyyyMMddHHmmss";
	public static final String TIMEFULL_MS = "yyyyMMddHHmm";
	public static final String TIMEFULL_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String TIMETomm = "yyyyMMddHHmm";
	public static final String TIMEDATE = "yyyyMMdd";
	public static final String TIMEDATE_SHORT = "yyMMdd";
	public static final long ONEDAY = 24L * 60L * 60L * 1000L;
	public static final long THREE_MONTH = 90L * ONEDAY;

	public static final int SECOND = 1000;
	public static final int MINUTE = SECOND * 60;
	public static final int HOUR = MINUTE * 60;
	public static final int DAY = HOUR * 24;

	/**
	 * //日期to字符串
	 * 
	 * @param date
	 * @param strFormat yyyy-MM-dd hh:mm:ss
	 * @return
	 * @throws Exception
	 */
	public static String dateToStr(Date date, String strFormat) {
		String result = null;
		try {
			if (date != null) {
				SimpleDateFormat format = new SimpleDateFormat(strFormat);
				result = format.format(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String dateToStr(Date date) {
		String result = null;
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			result = format.format(date);
		}
		return result;
	}

	/**
	 * 字符串to 日期
	 * 
	 * @param dateStr
	 * @param strFormat
	 * @return
	 * @throws Exception
	 */
	public static Date strToDate(String dateStr, String strFormat){
		Date date = new Date();
		try{
			SimpleDateFormat strToDate = new SimpleDateFormat(strFormat);
			date = strToDate.parse(dateStr);
		}catch(Exception e){
			return null;
		}
		return date;
	}

	public static Date strToDate(String dateStr) {
		Date date = null;
		SimpleDateFormat strToDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = strToDate.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
		return date;
	}

	
	public static Date systime() throws Exception {
		Date date = new Date();
		SimpleDateFormat strToDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = strToDate.parse(formatDateTime(date));
		return date;
	}
	
	
	/*
	 * 将日期格式为YYYY-MM-DD param date 日期
	 */
	public static String format(Date date) {
		if(date == null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/*
	 * 将日期格式为yyyy-MM-DD hh:mm:ss param date 日期时间
	 */
	public static String formatDateTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/*
	 * 将日期格式为yyyy-MM-DD hh:mm:ss param date 日期时间
	 */
	public static Date parseDateTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(date);
		return formatStrToDate(dateStr);
	}

	/*
	 * 将日期格式为hh:mm:ss param date 时间
	 */
	public static String formatTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		return sdf.format(date);
	}

	/*
	 * 将日期格式为YYYY-MM-DD param date 日期
	 */
	public static String format() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	/*
	 * 将日期格式为YYYY-MM-DD param date 日期
	 */
	public static String formatDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	/*
	 * 将日期格式为hh:mm:ss param date 时间
	 */
	public static String formatTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		return sdf.format(new Date());
	}

	/*
	 * 将日期格式为指定格式 日期字符串
	 */
	public static String formatTime(String dateFormatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr);
		return sdf.format(new Date());
	}

	public static String getYYMMDDStr() {
		SimpleDateFormat sdf = new SimpleDateFormat(TIMEDATE_SHORT);
		return sdf.format(new Date());
	}
	
	public static String getYYYYMMDDStr() {
		SimpleDateFormat sdf = new SimpleDateFormat(TIMEDATE);
		return sdf.format(new Date());
	}

	/*
	 * 将日期格式为指定格式 日期字符串
	 */
	public static String formatTime(Date date, String dateFormatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr);
		return sdf.format(date);
	}

	/*
	 * 将日期字符串转化为Date
	 */
	@SuppressWarnings("finally")
	public static Date formatStrToDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return date;
		}
	}

	/*
	 * 将日期字符串转化为Date
	 */
	@SuppressWarnings("finally")
	public static Date formatStrToDate(String dateStr, String exp) {
		SimpleDateFormat sdf = new SimpleDateFormat(exp);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return date;
		}
	}

	/*
	 * 将两位年日期字符串转化为Date
	 */
	@SuppressWarnings("finally")
	public static Date format2YearStrToDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return date;
		}
	}

	/*
	 * 将日期字符串转化为Date
	 */
	@SuppressWarnings("finally")
	public static Date formatLoastModifyDateStrToDate(String dateStr) {
		if (!CheckUtil.checkValue(dateStr) && dateStr.length() > 3)
			return null;
		if (dateStr.endsWith("GMT") || dateStr.endsWith("UTC")) {
			dateStr = dateStr.substring(4, dateStr.length() - 3).trim();
		}
		dateStr = processorDateStr(dateStr);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return date;
		}
	}

	/*
	 * 将日期字符串转化为Date
	 */
	@SuppressWarnings("finally")
	public static Date formatDateTimeStrToDate(String dateStr) {
		if (!CheckUtil.checkValue(dateStr) && dateStr.length() > 3)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return date;
		}
	}

	/*
	 * 将日期字符串转化为Date
	 */
	@SuppressWarnings("finally")
	public static Date formatDateTimeStrToDate2(String dateStr) {
		if (!CheckUtil.checkValue(dateStr) && dateStr.length() > 3)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return date;
		}
	}

	/*
	 * 将日期字符串转化为Date
	 */
	@SuppressWarnings("finally")
	public static Date formatYearStrToDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return date;
		}
	}

	/*
	 * 将日期字符串转化为Date
	 */
	@SuppressWarnings("finally")
	public static Date formatYearStrToDate(String dateStr, String separator) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + separator + "MM");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return date;
		}
	}

	public static String processorDateStr(String dateStr) {
		if (!CheckUtil.checkValue(dateStr)) {
			return null;
		}
		String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		String[] numMonths = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		dateStr = dateStr.replaceAll("\\s", "-");
		for (int i = 0; i < 12; i++) {
			int index = dateStr.indexOf(months[i].trim());
			if (index >= 0) {
				dateStr = dateStr.replaceAll(months[i], numMonths[index]);
				return dateStr;
			}
		}
		return dateStr;
	}

	public static boolean isDate(String value) {
		String regex = "^[0-9]{2,4}[-][0-9]{1,2}[-][0-9]{1,2}$";
		if (value == null) {
			return false;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.find();
	}

	public static Date getDate(String dateStr) {
		if (CheckUtil.checkEmpyt(dateStr)) {
			return new Date(0);
		}
		String regex = "[0-9]{2,4}[-][0-9]{1,2}[-][0-9]{1,2}";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(dateStr);
		if (m.find()) {
			String dStr = m.group();
			return DateUtil.formatStrToDate(dStr);
		}
		return new Date(0);
	}

	public static boolean isDateTime(String value) {
		String regex = "^[0-9]{2,4}[-][0-9]{1,2}[-][0-9]{1,2}\\s*[0-9]{2}[:][0-9]{2}[:][0-9]{2}$";
		if (value == null) {
			return false;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.find();
	}

	/*
	 * 转化为Date
	 */
	@SuppressWarnings("finally")
	public static Date formatDateTimeStrToDate2(Date date, String regex) {
		if (date == null || !CheckUtil.checkEmpyt(regex))
			return date;
		SimpleDateFormat sdf = new SimpleDateFormat(regex);
		// Date date = null;
		// System.out.println("asdfasdfasdf");
		try {
			String dateStr = sdf.format(date);
			// System.out.println(dateStr);
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return date;
		}
	}

	public static boolean isDate(String value, String spe) {
		String regex = "^[0-9]{2,4}[" + spe + "][0-9]{1,2}$";
		if (value == null) {
			return false;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.find();
	}

	/*
	 * 获取日期中的年份
	 */
	public static String getYear(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(date);
	}
	/*
	 * 获取日期中的月份
	 */
	public static String getMonth(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		return sdf.format(date);
	}
	
	private static String getExps(String value, String[] exps, String[] space) {
		// String dateExp = null;
		for (String spa : space) {
			for (String exp : exps) {
				String tempExp = exp.replaceAll("#", spa);
				String[] items = tempExp.split("\\|");
				if (!CheckUtil.checkEmpyt(items) || items.length != 2) {
					continue;
				}
				Pattern p = Pattern.compile(items[0]);
				Matcher m = p.matcher(value);
				if (m.find()) {
					return items[1];
				}
			}
		}
		return null;
	}

	/**
	 * 将日期字符串转换为Date，注，如果没有年份的，则转换出的值为1970年XX月XX日,如果没有月份，则为01月,如果年月都没有,
	 * 则为1970年01月X日 将日期转换为Date,如果日期字符串中没有年，则默认为系统当前年份,如果得出的日期大于当前的年份，则减少一年
	 * @param value 日期字符表达式
	 * @param isReduce 指示是否需需要减小一年,false不减少，true减小
	 */
	public static Date toDate(String value, boolean isReduce) {
		if (!CheckUtil.checkEmpyt(value))
			return null;
		value = value.replaceAll("&nbsp;", " ");
		int sign = 0;
		String[] space = { "-", "/", "." };
		String[] timeSpace = { ":" };
		String[] regexExpsYMD = { "[0-9]{4}[#][0-9]{2}[#][0-9]{2}|yyyy#MM#dd", "[0-9]{4}[#][0-9]{1}[#][0-9]{1}|yyyy#M#d", "[0-9]{4}[#][0-9]{1}[#][0-9]{2}|yyyy#M#dd", "[0-9]{4}[#][0-9]{2}[#][0-9]{1}||yyyy#MM#d",
				"[0-9]{2}[#][0-9]{2}[#][0-9]{2}|yy#MM#dd", "[0-9]{2}[#][0-9]{1}[#][0-9]{1}|yy#M#d", "[0-9]{2}[#][0-9]{1}[#][0-9]{2}|yy#M#dd", "[0-9]{2}[#][0-9]{2}[#][0-9]{1}|yy#MM#d" };
		String[] regexExpsYM = { "[0-9]{4}[#][0-9]{2}|yyyy#MM", "[0-9]{4}[#][0-9]{1}|yyyy#M" };
		String[] regexExpsMd = { "[0-9]{2}[#][0-9]{2}|MM#dd", "[0-9]{2}[#][0-9]{1}|MM#d", "[0-9]{1}[#][0-9]{1}|M#d", "[0-9]{1}[#][0-9]{2}|#M#d" };
		String[] regexExpsD = { "[0-9]{2}|dd", "[0-9]{1}|d" };
		// String[] timeExpHMS = { "\\s[0-9]{2}[:][0-9]{2}[:][0-9]{2}|HH#mm#ss",
		// "\\s?[0-9]{2}[:][0-9]{2}[:][0-9]{1}|HH#mm#s",
		// "\\s?[0-9]{2}[:][0-9]{1}[:][0-9]{2}|HH#m#ss" };
		String[] timeExpHMS = { "\\s[0-9]{2}[:][0-9]{2}[:][0-9]{2}|HH#mm#ss", "\\s?[0-9]{2}[:][0-9]{2}[:][0-9]{1}|HH#mm#s", "\\s?[0-9]{2}[:][0-9]{1}[:][0-9]{2}|HH#m#ss", "\\s?[0-9]{1}[:][0-9]{1}[:][0-9]{2}|H#m#ss",
				"\\s?[0-9]{1}[:][0-9]{1}[:][0-9]{1}|H#m#s", "\\s?[0-9]{1}[:][0-9]{2}[:][0-9]{1}|H#mm#s", "\\s?[0-9]{1}[:][0-9]{2}[:][0-9]{2}|H#mm#ss", "\\s?[0-9]{2}[:][0-9]{1}[:][0-9]{2}|HH#m#ss", "\\s?[0-9]{2}[:][0-9]{2}[:][0-9]{1}|HH#mm#s" };

		String[] timeExpHM = { "\\s[0-9]{2}[:][0-9]{2}|HH#mm" };
		String[] timeExpH = { "\\s[0-9]{2}|HH" };
		String dateExp = getExps(value, regexExpsYMD, space);
		if (dateExp == null)
			dateExp = getExps(value, regexExpsYM, space);
		if (dateExp == null) {
			dateExp = getExps(value, regexExpsMd, space);
			if (dateExp != null) {
				sign = 1;
			}
		}
		if (dateExp == null) {
			dateExp = getExps(value, regexExpsD, space);
			if (dateExp != null) {
				sign = 1;
			}
		}
		String timeExp = getExps(value, timeExpHMS, timeSpace);
		if (timeExp == null)
			timeExp = getExps(value, timeExpHM, timeSpace);
		if (timeExp == null)
			timeExp = getExps(value, timeExpH, timeSpace);
		String exp = "";
		if (CheckUtil.checkEmpyt(dateExp) && CheckUtil.checkEmpyt(timeExp)) {
			exp = dateExp + " " + timeExp;
		} else if (CheckUtil.checkEmpyt(dateExp)) {
			exp = dateExp;
		} else if (CheckUtil.checkEmpyt(timeExp)) {
			exp = timeExp;
		}
		if (CheckUtil.checkEmpyt(exp)) {
			Date date = formatStrToDate(value, exp);
			if (sign != 0) {
				long years = formatStrToDate(getYear(new Date()), "yyyy").getTime() - formatStrToDate(getYear(date), "yyyy").getTime();
				Date curDate = new Date(date.getTime() + years);
				if (isReduce) {
					years = formatStrToDate(getYear(new Date()), "yyyy").getTime() - formatStrToDate(new Integer(new Integer(getYear(date)).intValue() + 1).toString(), "yyyy").getTime();
				} else {
					years = formatStrToDate(getYear(new Date()), "yyyy").getTime() - formatStrToDate(new Integer(new Integer(getYear(date)).intValue()).toString(), "yyyy").getTime();
				}
				return new Date(date.getTime() + 24L * 3600L * 1000L + years);
			}
			return date;
		}
		return null;
	}

	public static Date toZhDate(String dateStr) {
		if (!CheckUtil.checkEmpyt(dateStr)) {
			return null;
		}
		return toZhDate(dateStr, false);
	}

	public static Date toZhDate(String dateStr, boolean isReduce) {
		if (!CheckUtil.checkEmpyt(dateStr)) {
			return null;
		}
		dateStr = dateStr.replaceAll("[\u4e00-\u9fa5]{1,}[-/_=*+.－／—＋。＊][\u4e00-\u9fa5]{1,}", "");
		dateStr = resetDateStr(dateStr);
		dateStr = dateStr.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "").replaceAll("小时", ":").replaceAll("点", ":").replaceAll("时", ":").replaceAll("分钟", ":").replaceAll("分", ":").replaceAll("秒钟", ":").replaceAll("秒", "");
		dateStr = dateStr.replaceAll("[\u4e00-\u9fa5]{1,}", "").replaceAll("::", ":").replaceAll("--", "-").replaceAll("//", "-").replaceAll("/-", "-").replaceAll("-/", "-").replaceAll("/-", "-").replaceAll(":/", ":").replaceAll("/:", ":")
				.replaceAll(":-", ":").replaceAll("-:", ":");
		if (dateStr.endsWith(":") || dateStr.endsWith("-") || dateStr.endsWith("/")) {
			dateStr = dateStr.substring(0, dateStr.length() - 1);
		}
		if (dateStr.startsWith(":") || dateStr.startsWith("-") || dateStr.startsWith("/")) {
			dateStr = dateStr.substring(1, dateStr.length());
		}
		return toDate(dateStr, isReduce);
	}

	public static String resetDateStr(String dateStr) {
		Pattern p = Pattern.compile("[0-9]{0,4}[-年份/_#.;]{0,2}[0-9]{0,2}[-月份/_#.;]{1,2}[0-9]{0,2}[-日天/_#.;]{1,2}[\\s&nbsp; ]{1,}[0-9]{0,2}[:点时小/_#.;]{0,2}[0-9]{0,2}[:分钟/_#.;]{0,2}[0-9]{0,2}[:秒钟/_#.;]{0,2}");
		Matcher m = p.matcher(dateStr);
		String value = dateStr;
		while (m.find()) {
			value = m.group();
			if (value.length() <= 2)
				continue;
			break;

		}
		return value;
	}

	public static void main (String[] args) {

		// \u4e00-\u9fa5表示中文,\uFF00-\uFFFF表示全角 其它表示一些特殊字符.
		// Date date = toDate("2011-11-15 15:32:48", false);
		// Date date2 = toZhDate("2011年11月15日 15点02分36秒");
		// Date date3 = toZhDate("2011年1月");
		// // Date date4 = toZhDate("绩效考核&nbsp;&nbsp;2011-6-13 13:09:47");
		// System.out.println(date3);
		// System.out.println(date2);
		// System.out.println(date);
		// // System.out.println(date4);
		// System.out.println(toDate("11/24 08:46", false));
		// System.out.println(formatDateTime(toDate("11/24 08:46", true)));
		// System.out.println(formatDateTime(toDate("11/24 08:46", false)));
		// // System.out.println(formatDateTime(date));
		// System.out.println(toZhDate("重庆商报 01月06日 13:18"));
		// System.out.println(toZhDate("重庆商报-分社 01月06日 13:18"));
		// System.out.println(toZhDate("2012年01月06日&nbsp;13:57"));
		// //
		// System.out.println(" (重庆商报 01月06日 13:18)".replaceAll("[\u4e00-\u9fa5]{1,}",
		// // ""));
		// System.out.println(toZhDate("2012年01月06日&nbsp;13点57分"));
		// System.out.println(toZhDate("重庆商报*分社 2012年/01月/06日&nbsp;13点:57分:29秒"));
		// System.out.println(toZhDate("重庆商报*分社2012年/01月/06日&nbsp;13点:57分:29秒"));
		// System.out.println(toZhDate("c114.net 2012年/01月/06日&nbsp;13点:57分:29秒"));
		// System.out.println(toZhDate("c114.net 2012年/01月/06日&nbsp;13点:57分:29秒"));
		// System.out.println(toZhDate("四川在线-天府早报 01月06日 10:26"));
		// System.out.println(toZhDate("1/4"));
		// System.out.println(toZhDate("2012-1-10 9:35:35"));
		// System.out.println("2012-1-10 9:35:35");
		// System.out.println(resetDateStr("2012-1-10 9:35:35"));
		// System.out.println(toZhDate("2012-1-10 9:1:33"));
		// System.out.println(toZhDate(" 新浪科技 01月11日 11:37",true));
		System.out.println(DateUtil.formatDateTime(toZhDate(" 北京晚报 04月09日 15:10", false)));
		System.out.println(DateUtil.formatDateTime(toZhDate("2011/12/31 09:51", false)));
		// System.out.println("c114.net.cn.g 2012年01月06日&nbsp;13点:57分:29秒".replaceAll("(^[\\w*\\.*]{2,})",
		// ""));
		// System.out.println(" 比特网chinabyte 01月05日 10:21".replaceAll("(^[\\w*\\.*]{2,})",
		// ""));
		// System.out.println(DateUtil.getDateValue(YEAR_REGEX,
		// " 比特网chinabyte 2012年01月05日 10:21"));
		// List<String> dates = new ArrayList<String>();//
		// dates.add("年份01");
		// dates.add("2012年份");
		// dates.add("2012年");
		// System.out.println(dateStrFilter(dates));
		// Arrays.sort(dates);
		// for (String date : dates) {
		// System.out.println(date);
		// }
		// String date = " 比特网chinabyte 2012-01.05 10:21:36";
		// String date = "c114.net 01月/06日&nbsp;13点:57分:29秒";
		// String date = "1/4";
		// Pattern p =
		// Pattern.compile("[0-9]{0,4}[-年份/_#.;]{0,2}[0-9]{0,2}[-月份/_#.;]{1,2}[0-9]{0,2}[-日天/_#.;]{1,2}[\\s&nbsp;]{0,}[0-9]{0,2}[:点时小/_#.;]{0,2}[0-9]{0,2}[:分钟/_#.;]{0,2}[0-9]{0,2}[:秒钟/_#.;]{0,2}");
		// Matcher m = p.matcher(date);
		// if (m.find()) {
		// System.out.println(m.group());
		// }
		// if (m.find()) {
		// System.out.println(m.group());
		// }
//		System.out.println(sysTimeAddDayNum(2));
//		System.out.println(sysTimeAddDayNum(-2));
		
//		Date start = DateUtil.getBeginingOfTheDay("2015-02-28");
//		System.out.println(start);
//		
//		for(int i = 0; i < 12; i++){
//			Date end = DateUtil.addOneMonthAndOneDayMinusOneSecond(start);
//			System.out.println("start = " + DateUtil.formatDateTime(start) + " end = " + DateUtil.formatDateTime(end));
//			
//			start = DateUtil.getBeginingOfTheDay(end);
//		}
//		
		Date start = DateUtil.getEndOfTheDay("2015-05-10 ");
		System.out.println(DateUtil.naturalDaysDifference(start, DateUtil.now()));
	}

	public static boolean isOneMinuteAgo(Date pastDate){
		Date newDate = DateUtil.addMinutes(pastDate, 1);

		Date now = new Date();

		return now.after(newDate);
	}

	public static Date addMinutes(Date date, int minutesToAdd){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minutesToAdd);

		return new Date(c.getTimeInMillis());
	}

	public static Date addDays(Date date, int daysToAdd){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, daysToAdd);

		return new Date(c.getTimeInMillis());
	}
	
	public static Date addDaysAndMinusOneSecond(Date date, int daysToAdd){
		date = addDays(date, daysToAdd);
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, -1);

		return new Date(c.getTimeInMillis());
	}


	public static long hoursDifference(Date startDate, Date endDate){
		return (endDate.getTime() - startDate.getTime()) / HOUR;
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return 自然天的天数之差
	 */
	public static int naturalDaysDifference(Date startDate, Date endDate){
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);

		int days = (int)((endDate.getTime() - c.getTimeInMillis()) / DAY);
		
		if(days == 0 && (endDate.getTime() > c.getTimeInMillis())){
			days = 1;
		}
		
		return days;
	}

	public static long hoursToNow(Date startDate){
		return hoursDifference(startDate, now());
	}

	public static Date now(){
		return new Date();
	}
	
	public static Date getBeginingOfTheDay(String date){
		if(StringUtils.isBlank(date)){
			return null;
		}
		
		return formatStr2Date(date + " 0:00:00");
	}
	
	public static Date getBeginingOfTheDay(Date date){
		if(date == null){
			return null;
		}
		
		return getBeginingOfTheDay(format(date));
	}
	
	public static Date getEndOfTheDay(String date){
		if(StringUtils.isBlank(date)){
			return null;
		}
		
		return formatStr2Date(date + " 23:59:59");
	}

	public static Date tomorrow(){
		return addDays(now(), 1);
	}

	public static Date addMonths(Date start, int months){
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.MONTH, months);

		return new Date(c.getTimeInMillis());
	}
	
	public static Date addYears(Date start, int years) {
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.YEAR, years);
		return new Date(c.getTimeInMillis());
	}
	
	public static Date addSeconds(Date start, int seconds){
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.SECOND, seconds);

		return new Date(c.getTimeInMillis());
	}
	
	public static Date addHours(Date start, int hours){
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.HOUR_OF_DAY, hours);

		return new Date(c.getTimeInMillis());
	}
	
	public static Date getFirstDayOfMonthByGivenDate(int year, int month){
		Date now = now();
		
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);

		return new Date(c.getTimeInMillis());
	}
	
	public static Date getFirDayOfCurrentMonth(){
		Date now = now();
		
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		
		return getFirstDayOfMonthByGivenDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH));
	}
	
	public static Date getFirDayOfNextMonth(){
		Date now = now();
		
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		
		return getFirstDayOfNextMonthByGivenDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH));
	}
	
	public static Date getFirstDayOfNextMonthByGivenDate(int year, int month){
		Date date = getFirstDayOfMonthByGivenDate(year, month);
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);

		return new Date(c.getTimeInMillis());
	}

	public static Date addOneMonthMinusOneDay(Date start){
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, -1);

		return new Date(c.getTimeInMillis());
	}
	
	public static Date addOneMonthAndOneDayMinusOneSecond(Date start){
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, 1);
		c.add(Calendar.SECOND, -1);

		return new Date(c.getTimeInMillis());
	}
	
	//计算下个月的还款时间，按照自然月的方式处理
	public static Date getLoanTermDueTime(Date start){
		Calendar c = Calendar.getInstance();
		c.setTime(start);
	
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.SECOND, -1);

		return new Date(c.getTimeInMillis());
	}
	
	public static int getLastDayOfCurrentMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		//将日期设置到下个月的第一天
		c.add(Calendar.MONTH, 1); 
		c.set(Calendar.DAY_OF_MONTH, 1);
		//日期间一天（即返回当前月的最后一天）
		c.add(Calendar.DATE, -1);
		
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static boolean isPastStatTime(Date startDate){
		return DateUtil.now().after(startDate);
	}

	public static boolean isBetweenTime(Date startTime, Date dueTime){
		return false;
	}

	public static boolean isPastOverDueTime(Date dueDate){
		Calendar c = Calendar.getInstance();
		c.setTime(dueDate);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.add(Calendar.DATE, 1);

		return DateUtil.now().after(c.getTime());
	}
	
	public static boolean isPastNow(Date dueTime){
		if(dueTime != null){
			return now().after(dueTime);
		}
		return false;
	}
	
	//********************start************************************
	/**
	 * @Title: String2Date 
	 * @Description: { String Date 2 yyyy-MM Date }
	 * @file_name: DateUtil.java
	 * @user:DING
	 */
	//String2Date
	public static Date String2Date(String dateStr) throws Exception {
		Date date = new Date();
		SimpleDateFormat strToDate = new SimpleDateFormat("yyyy-MM");
		date = strToDate.parse(dateStr);
		return date;
	}
	/**
	 * @Title: parseDate 
	 * @Description: { 将日期格式为yyyy-MM-DD hh:mm:ss param date 日期时间  }
	 * @file_name: DateUtil.java
	 * @user:DING
	 */
	public static Date parseDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date);
		return formatStr2Date(dateStr);
	}

	/**
	 * @Title: formatStr2Date 
	 * @Description: { 将日期字符串转化为Date }
	 * @file_name: DateUtil.java
	 * @user:DING
	 */
	@SuppressWarnings("finally")
	public static Date formatStr2Date(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			return date;
		}
	}
	
	/**
	 * @Title: sysTimeAddDayNum 
	 * @Description: { 得到当前时间的前或后天数 时间 }
	 * @file_name: DateUtil.java
	 * @user:DING
	 */
	public static String sysTimeAddDayNum(int DayNum) {
		 SimpleDateFormat format = new   SimpleDateFormat("yyyy-MM-dd"); 

		   Date dd = new Date();

		   Calendar calendar = Calendar.getInstance();

		   calendar.setTime(dd);

		   calendar.add(Calendar.DATE,DayNum); 

		   String newDate = format.format(calendar.getTime() ) ;

		   return 		newDate;
	}
	
	public static  String getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH)+1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                //monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                //monthNow>monthBirth
                age--;
            }
        }
        return age +"";
    } 
	
	
	 //判断是否为同一天  
	public static  boolean getWhetherSameDay(Date date){
		//系统时间
		if(formatTime(now(),TIMEDATE).equals(formatTime(date,TIMEDATE))){
			return true;
		}
		return false;
    } 
	
	
	/**
     * 日期的加减方法
     * 用于在当前的天或者小时或者分钟或者月份的基础上加上或者减去若干小时，分钟，日，月
     * @param currentDay 当前月份的某一天
     * @param day (Calendar.DATE 天 Calendar.HOUR 小时 Calendar.MINUTE 分钟 Calendar.MONTH 月)需要加上的天数或者需要减去的天数，
     *  例如：加上10天：(Calendar.DATE,10）减去十天：(Calendar.DATE,-10)
     * @return 返回加上或者减去的那个日期
     * @author 程熙
     * ${2013-11-19}
     */
    public static Date dayAddAndSub(int currentDay,int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(currentDay, day);
        Date startDate = calendar.getTime();
        return startDate;
    }
	
	
	
	 
	//******************end****************************************
}
