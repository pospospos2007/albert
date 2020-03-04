package com.zdcf.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zdcf.base.InfoChangeEvent;
import com.zdcf.model.Cacheable;
import com.zdcf.search.IIndexService;
import com.zdcf.service.ICacheService;

public class InfoChangeListener implements MessageListener {
	private static final Logger logger = Logger.getLogger(InfoChangeListener.class);
	
	@Autowired
	private ICacheService cacheService;
	
	@Autowired
	private IIndexService indexService;
	
	//在基础信息变动时得到通知，更新Redis缓存和ES index
    public void onMessage(Message message) {
        ObjectMessage objMessage = (ObjectMessage) message;
        
        try {
        	logger.info("接受到信息，开始存储cache");
        	InfoChangeEvent msg = (InfoChangeEvent)objMessage.getObject();
            Cacheable obj = (Cacheable)msg.getObject();
            
            cacheService.process(obj, msg.getType());
            indexService.process(obj, msg.getType());
            
        } catch (JMSException e) {
        	e.printStackTrace();
        	logger.error("failed to process cache/ES for object", e);
        }
    }
}