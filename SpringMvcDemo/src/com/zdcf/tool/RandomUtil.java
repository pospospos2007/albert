package com.zdcf.tool;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

public class RandomUtil {
	private static final int offset = 9803803; // offset为固定值，避免被猜到种子来源（和密码学中的加salt有点类似）
	private static final int MAX_CODE = 1000000;
	
	public static String code() {
		long seed = System.currentTimeMillis() + offset;
		SecureRandom secureRandom;
		
		String code = "";
		try {
			secureRandom = SecureRandom.getInstance("SHA1PRNG");
			
			secureRandom.setSeed(seed);
			int random = 0;
			while(random <= 100000){
				random = secureRandom.nextInt(MAX_CODE);
			}
			
			code = String.valueOf(random);
			
		} catch (NoSuchAlgorithmException e) {
			
		}
		
		return code;
	}
	
	
	//获取 合同编号
	public static String contractNum() {
		long seed = System.currentTimeMillis() + offset;
		SecureRandom secureRandom;
		
		String code = "";
		try {
			secureRandom = SecureRandom.getInstance("SHA1PRNG");
			
			secureRandom.setSeed(seed);
			int random = 0;
			while(random <= 100000){
				random = secureRandom.nextInt(MAX_CODE);
			}
			
			code = String.valueOf(random);
			
		} catch (NoSuchAlgorithmException e) {
			
		}
		
		return code;
	}
	
	//获取服务单编号
//	public static String serviceNum(){
//		long time = DateUtil.now().getTime();
//		String randomNum = contractNum();
//		
//		return time + randomNum;
//	}
	
	//获取微信需要的随机字符串
	public static String getNonceStr() {
        return UUID.randomUUID().toString();
    }
	
	//时间戳
	public static String getTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
