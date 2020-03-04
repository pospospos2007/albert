package com.zdcf.test;
import java.nio.charset.Charset;
import java.util.ArrayList;


import com.zdcf.dto.FaceAttributeDTO;


public class Test {

	public static void main(String[] args) {
//		HttpRequests httpRequests = new HttpRequests("dd0094b405bf9612a58f181ee3acde3a", "fcmx4kqd0fZzOzQKOFiYcldgE92ECNLx", true, true);
//		JSONObject result = null;
//		
//		try {
//			
//			System.out.println(Charset.forName("UTF-8").name());
//		
//			PostParameters params = new PostParameters();
//			params.setAttribute("glass,pose,gender,age,race,smiling");
//			params.setUrl("http://120.76.44.191:9999/uploadface/images/1606/25/15/25/1466839506881.jpg");
//			result = httpRequests.detectionDetect(params);
//			
//			Integer size = result.getJSONArray("face").length();
//			
//			if(null!=size&&size>0){
//				for(int i=0;i<size;i++){
//					FaceAttributeDTO attr = new FaceAttributeDTO();
//					JSONObject attribute = result.getJSONArray("face").getJSONObject(i).getJSONObject("attribute");
//					System.out.println(Integer.parseInt(attribute.getJSONObject("age").get("value").toString()));
//					System.out.println(attribute.getJSONObject("gender").get("value").toString());
//					System.out.println(attribute.getJSONObject("glass").get("value").toString());
//					System.out.println(attribute.getJSONObject("race").get("value").toString());
//				}
//			}
//		
//			
//		} catch(FaceppParseException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//		} 
	}
}
