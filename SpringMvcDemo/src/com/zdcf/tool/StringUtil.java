package com.zdcf.tool;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringUtil {
	private static final String IMG_ICON_SMALL = "1";
	private static final String IMG_ICON_MEDIUM = "2";
	private static final String IMG_ICON_BIG = "3";

	public static String urlEncode(String source) {
		try{
			return URLEncoder.encode(source, "UTF-8");
		}catch(Exception e){
			return null;
		}
	}

	
	public static void appStringBuffer(StringBuffer sf){
		if(StringUtil.notEmpty(sf.toString())){
			sf.append("/");
		}
	}
	
	

	public static boolean isMobile(String mobileNum) {
		if(StringUtils.isBlank(mobileNum)){
			return false;
		}
        //全是字母
		boolean matches = Pattern.matches("(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\\d{8})", mobileNum);
		if (!matches) {
			return false;
		}
		
		return true;
	}
	
	public static boolean checkBankCard(String cardId) {  
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));  
        if(bit == 'N'){  
            return false;  
        }  
        return cardId.charAt(cardId.length() - 1) == bit;  
  }  
   
  /** 
   * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位 
   * @param nonCheckCodeCardId 
   * @return 
   */  
  public static char getBankCardCheckCode(String nonCheckCodeCardId){  
      if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0  
              || !nonCheckCodeCardId.matches("\\d+")) {  
       //如果传的不是数据返回N  
          return 'N';  
      }  
      char[] chs = nonCheckCodeCardId.trim().toCharArray();  
      int luhmSum = 0;  
      for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {  
          int k = chs[i] - '0';  
          if(j % 2 == 0) {  
              k *= 2;  
              k = k / 10 + k % 10;  
          }  
          luhmSum += k;             
      }  
      return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');  
  }  
	
	public static boolean isPayPassword(String password) {
		if (StringUtils.isBlank(password)) {
			return false;
		}
		
		if(password.length() < 6 || password.length() > 12){
			return false;
		}
		
		return true;
	}

	public static boolean isPassword(String password) {
		if (StringUtils.isBlank(password)) {
			return false;
		}

		/*boolean matches = Pattern.matches("[a-zA-Z0-9_]{6,16}", password);
		if (matches) {
			return true;
		}*/

		boolean matches = password.length()<6 || password.length() > 16;
		if (matches) {
			return false;
		}
		//全是数字
		  matches = Pattern.matches("[0-9]*", password);
		  if (matches) {
			return false;
	        }
		//是否只包含数字，不包含字符串
		  matches = Pattern.matches("(.*?)\\d(.*?)", password);
		  if (!matches) {
			  return false;
		  }
		
       /* //全是字母
		matches = Pattern.matches("[A-Za-z]*", password);
		if (matches) {
			return false;
		}
		//全是数字
		matches = Pattern.matches("[0-9]*", password);
		if (matches) {
			return false;
		}
		//只能是数字和字母
		 matches = Pattern.matches("[A-Za-z0-9]*", password);
		if (!matches) {
			return false;
		}*/

		return true;
	}
	
	
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    if(StringUtils.isBlank(str)){
	    	return false;
	    }
	    
	    if(!pattern.matcher(str).matches()){
	    	return false;
	    }
	    
	    return true;
	 } 
	
	/**
	 * @param 用户输入字符串
	 * @return 过滤了非法字符的串
	 */
	public static String filterString(String source) {
		return "";
	}

	public static void main(String[] args) {
		System.out.println(isMobile("19210572067"));
	}

	public static String changePlaceholder(String source) {
		return "abc";
	}

	/**
	 * 方法名称: notEmpty 方法描述: 检测字符串是否不为空(null,"","null") 返回类型：
	 * 不为空则返回true，否则返回false 创建人：zhangsg 创建时间：2014-9-9 晚上19:30:05
	 * 
	 * @throws
	 */
	public static boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s);
	}

	/**
	 * 方法名称: isEmpty 方法描述: 检测字符串是否为空(null,"","null") 返回类型： 为空则返回true，不否则返回false
	 * 创建人：zhangsg 创建时间：2014-9-9 晚上19:30:05
	 * 
	 * @throws
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s) || "null".equals(s);
	}

	/**
	 * 方法名称: str2StrArray 方法描述: 字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串 返回类型： 创建人：zhangsg 创建时间：2014-9-9 晚上19:30:05
	 * @throws
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 方法名称: str2StrArray 方法描述: 用默认的分隔符(,)将字符串转换为字符串数组 返回类型： 字符串 创建人：zhangsg
	 * 创建时间：2014-9-9 晚上19:30:05
	 * 
	 * @throws
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	public static String nullSafeToString(Object obj) {
		if (obj == null) {
			return null;
		}

		return obj.toString();
	}

	public static String leftPad(String value, int totalLength) {
		return StringUtils.leftPad(value, totalLength, "0");
	}
	
	public static String rightPad(String value, int totalLength) {
		return StringUtils.rightPad(value, totalLength, "0");
	}

	public static String leftPadForPrincipleEntry(String value) {
		return StringUtils.leftPad(value, 4, "0");
	}

	public static String getIconImgBig(String img) {
		String[] nameSuffix = getIconImgNameAndSuffix(img);
		return nameSuffix[0] + IMG_ICON_BIG + "." + nameSuffix[1];
	}

	public static String getIconImgMedium(String img) {
		String[] nameSuffix = getIconImgNameAndSuffix(img);
		return nameSuffix[0] + IMG_ICON_MEDIUM + "." + nameSuffix[1];
	}

	public static String getIconImgSmall(String img) {
		String[] nameSuffix = getIconImgNameAndSuffix(img);
		return nameSuffix[0] + IMG_ICON_SMALL + "." + nameSuffix[1];
	}

	private static String[] getIconImgNameAndSuffix(String img) {
		return img.split("\\.");
	}

	public static char ascii2Char(int ASCII) {
		return (char) ASCII;
	}

	public static int char2ASCII(char c) {
		return (int) c;
	}

	/**
	 * @Title: ObjectToDoubleUtil
	 * @Description: { Object to Double }
	 * @file_name: LoanInvestQueryService.java
	 * @user:DING
	 */
	public static double ObjectToDoubleUtil(Object obj) {
		if (obj != null) {
			return Double.parseDouble(obj.toString());
		}
		return 0;
	}

	/**
	 * @Title: ObjectToDoubleUtil
	 * @Description: { Object to Integer }
	 * @file_name: LoanInvestQueryService.java
	 * @user:DING
	 */
	public static Integer ObjectToIntegerUtil(Object obj) {
		if (obj != null) {
			return Integer.valueOf(obj.toString());
		}
		return 0;
	}

	/**
	 * @Title: ObjectToDoubleUtil
	 * @Description: { Object to Integer }
	 * @file_name: LoanInvestQueryService.java
	 * @user:DING
	 */
	public static Float ObjectToFloatUtil(Object obj) {
		if (obj != null) {
			return Float.parseFloat( ObjectToStringUtil(obj) ); 
		}
		return null;
	}

	
	/**
	 * @Title: ObjectToDoubleUtil
	 * @Description: { Object to Integer }
	 * @file_name: LoanInvestQueryService.java
	 * @user:DING
	 */
	public static boolean ObjectToBooleanUtil(Object obj) {
		if (obj != null && "true".equalsIgnoreCase(obj.toString())) {
			return true; 
		}
		return false;
	}
	
	
	
	/**
	 * @Title: ObjectToDoubleUtil
	 * @Description: { Object to String }
	 * @file_name: LoanInvestQueryService.java
	 * @user:DING
	 */
	public static String ObjectToStringUtil(Object obj) {
		if (obj != null) {
			return obj.toString();
		}
		return null;
	}

	/**
	 * @Title: ObjectToDoubleUtil
	 * @Description: { Object to Date }
	 * @file_name: LoanInvestQueryService.java
	 * @user:DING
	 */
	public static Date ObjectToDateUtil(Object obj) {
		if (obj != null) {
			return DateUtil.formatDateTimeStrToDate(obj.toString());
		}
		return null;
	}

	
	/**
	 * @Title: ObjectToDoubleUtil
	 * @Description: { Object to Date }
	 * @file_name: LoanInvestQueryService.java
	 * @user:DING
	 */
	public static int IntegerTointUtil(Integer obj) {
		if (obj != null) {
			return obj; 
		}
		return 0;
	}
	
	
	
	/**
	 * @Title: ObjectToShortUtil
	 * @Description: { Object to String }
	 * @file_name: LoanInvestQueryService.java
	 * @user:DING
	 */
	public static Short ObjectToShortUtil(Object obj) {
		if (obj != null) {
			return new Short(ObjectToStringUtil(obj));
		}
		return null;
	}
	
	/**
	 * @Title: IntegerToShortUtil
	 * @Description: { Object to Integer }
	 * @file_name: LoanInvestQueryService.java
	 * @user:DING
	 */
	public static Short IntegerToShortUtil(Integer obj) {
		if (obj != null) {
			return Short.parseShort(""+obj);
		}
		return null;
	}

	
	/**
	 * @Title: doubleBigDecimal
	 * @Description: { 百分比 }
	 * @file_name: StringUtil.java
	 * @user:DING
	 */
	public static double getEpercentage(double obj) {
		BigDecimal b = new BigDecimal(obj * 100);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	
	
	/**
	 * @Title: doubleBigDecimal
	 * @Description: { 百分比 }
	 * @file_name: StringUtil.java
	 * @user:DING
	 */
	public static double getYieldRate(double obj) {
		BigDecimal b = new BigDecimal(obj);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	// 根据年月取得 月的天数
	public static int getDayByYearAndMonth(int year, int month) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM");
		Calendar rightNow = Calendar.getInstance();
		try {
			rightNow.setTime(simpleDate.parse(year + "-" + month));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);// 根据年月 获取月份天数
	}
	
	public static String getMaskedNickName(String nickName) {
		if(StringUtils.isBlank(nickName)){
			return "";
		}
		
		int length = nickName.length();
		String first = nickName.substring(0, 1);
		String last = nickName.substring(length - 1);
		
		return first + "***" + last;
	}
	
	public static String getMaskedRealName(String realName){
		if(StringUtils.isNotBlank(realName)){
			int length = realName.length();
			StringBuffer displayMaskedName = new StringBuffer();
			for (int i = 0; i < length - 1; i++) {
				displayMaskedName.append("*");
			}
			displayMaskedName.append(realName.substring(length - 1));
			return displayMaskedName.toString();
		}
		
		return "";
	}

	
	public static String getMaskedNickNameSt(String nickName){
		if(StringUtils.isNotBlank(nickName)){
			int length = nickName.length();
			StringBuffer displayMaskedName = new StringBuffer();
			if(length>1){
				displayMaskedName.append(nickName.substring(0,1));
				for (int i = 0; i < length-1; i++) {
					displayMaskedName.append("*");
				}
			}else{
				return nickName;
			}
			return displayMaskedName.toString();
		}
		return "";
	}
	
	
	
	public static String getShortBankCardNum(String bankCardNum) {
		if(StringUtils.isBlank(bankCardNum)){
			return "";
		}
		int length = bankCardNum.length();
		int lengthxin = length-5;
		int zhengs  = lengthxin/4;
		
		StringBuffer sbf = new StringBuffer();
		for(int i=0;i<zhengs;i++){
			sbf.append("**** ");
		}
		
		int yushu = lengthxin%4;
		if(yushu!=0){
			for(int i=0;i<yushu;i++){
				sbf.append("*");
			}
		}
		String first = bankCardNum.substring(0, 2)+"** ";
		String last = " *"+bankCardNum.substring(length - 3);
		return first + sbf.toString()+ last;
	}
	
	//String to UpperCase
	public static String str2UpperCase(String str) {
		if(StringUtils.isBlank(str)){
			return "";
		}
		return str.toUpperCase();
	}
	
	
	  //String to LowerCase
		public static String str2LowerCase(String str) {
			if(StringUtils.isBlank(str)){
				return "";
			}
			return str.toLowerCase();
		}
	
		
		
		
		
		/**
		 * 转换返回值类型为UTF-8格式.
		 * @param is
		 * @return
		 */
		public static String convertStreamToString(InputStream is) {    
	        StringBuilder sb1 = new StringBuilder();    
	        byte[] bytes = new byte[4096];  
	        int size = 0;  
	        
	        try {    
	        	while ((size = is.read(bytes)) > 0) {  
	                String str = new String(bytes, 0, size, "UTF-8");  
	                sb1.append(str);  
	            }  
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        } finally {    
	            try {    
	                is.close();    
	            } catch (IOException e) {    
	               e.printStackTrace();    
	            }    
	        }    
	        return sb1.toString();    
	    }
		
		//字符过滤
		public static String charactersFilter(String st,String seqNum){
			    StringBuffer sbf = new StringBuffer();
			    if(st.contains("<")){
			    	 sbf.append(st.substring(0,st.indexOf("<")));
						String[] stt =  st.split("<");
				        for(String s : stt){
				        	if(s.contains(">")){
				        		String[] stts = s.split(">");
				        		if(stts.length==2){
				        			sbf.append(stts[1]);
				        			
				        			String sa = stts[0];
				        			if(sa.contains("href") && StringUtils.isEmpty(seqNum)){
				        				String[] sb = sa.split("/");
				        				seqNum = sb[sb.length-1];
				        				System.out.println(seqNum);
				        			}
				        		}
				        	}
				        }
			    }else{
			    	sbf.append(st);
			    }
			     return sbf.toString();
		}
		
		
		
		
    //字符过滤
		public static String charactersFilter(String st){
			    StringBuffer sbf = new StringBuffer();
			    if(st.contains("<")){
			    	 sbf.append(st.substring(0,st.indexOf("<")));
						String[] stt =  st.split("<");
				        for(String s : stt){
				        	if(s.contains(">")){
				        		String[] stts = s.split(">");
				        		if(stts.length==2){
				        			sbf.append(stts[1]);
				        		}
				        	}
				        }
			    }else{
			    	sbf.append(st);
			    }
			     return sbf.toString();
		}
		
		//IOS 在获取图片路径时 需要把 含有"\" 转换成"/"
		public static String replaceAvatarImg(String avatarImg){
			if(StringUtil.notEmpty(avatarImg)){
				if(avatarImg.contains("\\")){
					avatarImg = avatarImg.replace("\\", "/");
				}
			}
			return avatarImg;
		}
		
		
		public static String setStringLike(String st){
			if(StringUtil.notEmpty(st)){
				return "'%"+st+"%'";
			}
			return st;
		}	
		
		
		//究极去重方式
		public static String[] array_unique(String[] a) {
			Set<String> set = new HashSet<String>();
			set.addAll(Arrays.asList(a));
			return set.toArray(new String[0]);
			}
}
