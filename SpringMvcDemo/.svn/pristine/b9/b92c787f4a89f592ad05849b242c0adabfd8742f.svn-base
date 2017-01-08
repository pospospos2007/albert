package com.zdcf.service;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.zdcf.base.Constants;
import com.zdcf.base.InfoChangeEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseService {
	private static String INFO_CHANGE_QUEUE = "InfoChangeQueue";
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private JmsTemplate jmsTemplate;

	protected String toJSON(Object object){
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	protected <T> T toObject(String json, Class<T> t){
		try {
			return mapper.readValue(json, t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	protected <T> List<T> toList(String json, Class<T> t){
		try {
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, t); 
			return mapper.readValue(json, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	protected void sendMessage(final Object object, final Constants.Cache.Type type){
		jmsTemplate.send(INFO_CHANGE_QUEUE, new MessageCreator(){
		      public ObjectMessage createMessage(Session session) throws JMSException {  
		    	  ObjectMessage message = session.createObjectMessage();
		    	  
		    	  InfoChangeEvent event = new InfoChangeEvent();
		    	  event.setObject(object);
		    	  event.setType(type);
		    	  message.setObject(event);
		    	  return message;
	            }  
		});
	}
	
//	protected List<String> getImagesByRefId(int refId){
//		
//		ChnlImageExample example = new ChnlImageExample();
//		example.createCriteria().andRefIdEqualTo(refId)
//		                .andChnlEqualTo(Constants.Chnl.House);
//		example.setOrderByClause(" display_order asc");
//		
//		List<ChnlImage> list = chnlImageMapper.selectByExample(example);
//		
//		List<String> imageList = new ArrayList<String>();
//		
//		for(ChnlImage image : list){
//			imageList.add(image.getPath());
//		}
//		
//		return imageList;
//	}
}
