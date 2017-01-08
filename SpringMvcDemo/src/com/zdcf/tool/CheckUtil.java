package com.zdcf.tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CheckUtil {

	public static boolean checkEmpyt(List value) {
		return value != null && value.size() > 0;
	}

	public static boolean checkEmpyt(Object[] values) {
		return values != null && values.length > 0;
	}

	public static boolean checkEmpyt(Map value) {
		return value != null && value.size() > 0;
	}

	public static boolean checkEmpyt(Set set) {
		return set != null && set.size() > 0;
	}

	public static boolean checkValue(Double value) {
		return value != null;
	}

	public static boolean checkEmpyt(String... values) {
		return values != null && values.length > 0;
	}

	public static boolean checkEmpyt(String value) {
		return value != null && !value.equals("")&&!value.equalsIgnoreCase("null");
	}

	public static boolean checkIsEmpyt(String value) {
		return value == null || value.equals("")||value.equalsIgnoreCase("null");
	}
	
	public static boolean checkEmpyt(String value1, String value2) {
		return checkEmpyt(value1) && checkEmpyt(value2);
	}

	public static boolean checkEmpyt(String value1, String value2, String value3) {
		return checkEmpyt(value1, value2) && checkEmpyt(value3);
	}

	public static boolean checkValue(InputStreamReader value) {
		return value != null;
	}

	public static boolean checkValue(BufferedReader value) {
		return value != null;
	}

	public static boolean checkValue(String[][] values) {
		return values != null;
	}

	public static boolean checkValue(int[][] values) {
		return values != null;
	}

	public static boolean checkValue(byte[] values) {
		return values != null;
	}

	public static boolean checkValue(Node value) {
		return value != null;
	}

	public static boolean checkValue(StringBuffer value) {
		return value != null;
	}

	public static boolean checkValue(Date value) {
		return value != null;
	}

	public static boolean checkValue(Integer value1, Integer value2) {
		return value1 != null && value2 != null;
	}

	public static boolean checkValue(Integer value) {
		return value != null;
	}

	public static boolean checkValue(Runnable value) {
		return value != null;
	}

	public static boolean checkValue(ExecutorService value) {
		return value != null;
	}

	public static boolean checkValue(Queue value) {
		return value != null;
	}

	public static boolean checkValue(NodeList value) {
		return value != null;
	}

	public static boolean checkValue(String[] values) {
		return values != null;
	}

	public static boolean checkValue(String[] values1, String[] values2) {
		return checkValue(values1) && checkValue(values2);
	}

	public static boolean checkValue(Map.Entry value) {
		return value != null;
	}

	public static boolean checkValue(Set value) {
		return value != null;
	}

	public static boolean checkValue(Document value) {
		return value != null;
	}

	public static boolean checkValue(Map value) {
		return value != null;
	}

	public static boolean checkValue(List value) {
		return value != null;
	}

	public static boolean checkValue(List value1, List value2) {
		return checkValue(value1) && checkValue(value2);
	}

	public static boolean checkValue(Long value) {
		return value != null;
	}

	public static boolean checkValue(Long value1, Long value2) {
		return checkValue(value1) && checkValue(value2);
	}

	public static boolean checkValue(Long value1, Long value2, Long value3) {
		return checkValue(value1, value2) && checkValue(value3);
	}

	public static boolean checkValue(String value) {
		return value != null;
	}

	public static boolean checkValue(String value1, String value2) {
		return checkValue(value1) && checkValue(value2);
	}

	public static boolean checkValue(String value1, String value2, String value3) {
		return checkValue(value1, value2) && checkValue(value3);
	}

	public static boolean checkValue(String value1, String value2, String value3, String value4) {
		return checkValue(value1, value2) && checkValue(value3, value4);
	}

	public static boolean CheckPositiveNumber(Long value) {
		return checkValue(value) && value >= 0;
	}

	public static boolean CheckPositiveNumber(Long value1, Long value2) {
		return CheckPositiveNumber(value1) && CheckPositiveNumber(value2);
	}

	public static boolean CheckPositiveNumber(int value) {
		return value >= 0;
	}

	public static boolean CheckPositiveNumber(int value1, int value2) {
		return CheckPositiveNumber(value1) && CheckPositiveNumber(value2);
	}

	public static boolean isNumber(String value) {
		if (!checkValue(value)) {
			return false;
		}
		Pattern p = Pattern.compile("^[0-9]{1,10}$");
		Matcher m = p.matcher(value);
		return m.find();
	}
	
	public static boolean checkChineseName(String value) {
		if(StringUtils.isBlank(value)){
			return false;
		}
       Pattern p=Pattern.compile("[\u4e00-\u9fa5]");
       Matcher m=p.matcher(value);
       if(m.find()){
    	   if(value.length() > 1 && value.length() < 8){
    		   return true;
    	   }
	   }
       
       return false;
	}
	
	public static String checkIdCardNum(String idCardNum, String birthdayString) {
	        String errorInfo = "";// 记录错误信息
	        String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4",
	                "3", "2" };
	        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
	                "9", "10", "5", "8", "4", "2" };
	        String Ai = "";
	        // ================ 号码的长度 15位或18位 ================
	        if (idCardNum.length() != 15 && idCardNum.length() != 18) {
	            errorInfo = "身份证号码长度应该为15位或18位。";
	            return errorInfo;
	        }
	        // =======================(end)========================

	        // ================ 数字 除最后以为都为数字 ================
	        if (idCardNum.length() == 18) {
	            Ai = idCardNum.substring(0, 17);
	        } else if (idCardNum.length() == 15) {
	            Ai = idCardNum.substring(0, 6) + "19" + idCardNum.substring(6, 15);
	        }
	        if (isNumeric(Ai) == false) {
	            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
	            return errorInfo;
	        }
	        // =======================(end)========================

	        // ================ 出生年月是否有效 ================
	        String strYear = Ai.substring(6, 10);// 年份
	        String strMonth = Ai.substring(10, 12);// 月份
	        String strDay = Ai.substring(12, 14);// 月份
	        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
	            errorInfo = "身份证生日无效。";
	            return errorInfo;
	        }
	        GregorianCalendar gc = new GregorianCalendar();
	        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
	                    || (gc.getTime().getTime() - s.parse(
	                            strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
	                errorInfo = "身份证生日不在有效范围。";
	                return errorInfo;
	            }
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        } catch (java.text.ParseException e) {
	            e.printStackTrace();
	        }
	        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
	            errorInfo = "身份证月份无效";
	            return errorInfo;
	        }
	        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
	            errorInfo = "身份证日期无效";
	            return errorInfo;
	        }
	        
	        if(StringUtils.isNotBlank(birthdayString) && !StringUtils.equals(strYear + "-" + strMonth + "-" + strDay, birthdayString)){
	            errorInfo = "身份证日期和选择的出生日期不符";
	            return errorInfo;
	        }
	        // =====================(end)=====================

	        // ================ 地区码时候有效 ================
	        Hashtable h = GetAreaCode();
	        if (h.get(Ai.substring(0, 2)) == null) {
	            errorInfo = "身份证地区编码错误。";
	            return errorInfo;
	        }
	        // ==============================================

	        // ================ 判断最后一位的值 ================
	        int TotalmulAiWi = 0;
	        for (int i = 0; i < 17; i++) {
	            TotalmulAiWi = TotalmulAiWi
	                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
	                    * Integer.parseInt(Wi[i]);
	        }
	        int modValue = TotalmulAiWi % 11;
	        String strVerifyCode = ValCodeArr[modValue];
	        Ai = Ai + strVerifyCode;

	        if (idCardNum.length() == 18) {
	            if (Ai.equals(idCardNum) == false) {
	                errorInfo = "身份证无效，不是合法的身份证号码";
	                return errorInfo;
	            }
	        } else {
	            return "";
	        }
	        // =====================(end)=====================
	        return "";
	}
	
	
	
	
	public static String checkIdCardNum(String idCardNum) {
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4",
                "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2" };
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (idCardNum.length() != 15 && idCardNum.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            return errorInfo;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (idCardNum.length() == 18) {
            Ai = idCardNum.substring(0, 17);
        } else if (idCardNum.length() == 15) {
            Ai = idCardNum.substring(0, 6) + "19" + idCardNum.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return errorInfo;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "身份证生日无效。";
            return errorInfo;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                            strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "身份证生日不在有效范围。";
                return errorInfo;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份证月份无效";
            return errorInfo;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份证日期无效";
            return errorInfo;
        }
        // =====================(end)=====================

        // ================ 地区码时候有效 ================
        Hashtable h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";
            return errorInfo;
        }
        // ==============================================

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (idCardNum.length() == 18) {
            if (Ai.equals(idCardNum) == false) {
                errorInfo = "身份证无效，不是合法的身份证号码";
                return errorInfo;
            }
        } else {
            return "";
        }
        // =====================(end)=====================
        return "";
}
	
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * 功能：判断字符串是否为数字
     * 
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：判断字符串是否为日期格式
     * 
     * @param str
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
	
	public static boolean CheckDateFormat(String dateStr) {
		if (!checkValue(dateStr)) {
			return false;
		}
		Pattern p = Pattern.compile("^[1-2][0-9]{3}[-][0-9]{1,2}[-][0-9]{1,2}$");
		Matcher m = p.matcher(dateStr.trim());
		if (m.find()) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(checkIdCardNum("150404198909160310", "1989-9-16"));
	}
	
}
