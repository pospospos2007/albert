package com.zdcf.tool;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**敏感词过滤器 */
public class SensitiveWordsFilterUtils {

	/**
	 * 读取文件中的敏感词
	 */
	private static String[] getWords(){
		StringBuffer buffer = new StringBuffer();
		InputStream is = null;
		BufferedReader reader = null;
		String words[] = null;
		try{
			is = SensitiveWordsFilterUtils.class.getResourceAsStream("/words.txt");
			reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line+"\r\n");
			}
			words = buffer.toString().replace("\r\n", ";").split(";");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try{
				reader.close();
				is.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return words;
	}
	
	/**
	 * 过滤敏感字符
	 */
	public static String filterWords(String words){
		
		String[] filter = getWords();
		String result = words;
		for(String word : filter){
			if (result.indexOf(word) != -1) {
				String stars = "";
				for (int j = 0; j < word.length(); j++)
					stars += '*';
				result = result.replace(word, stars);
			}
		}
		return result;
	}
}
