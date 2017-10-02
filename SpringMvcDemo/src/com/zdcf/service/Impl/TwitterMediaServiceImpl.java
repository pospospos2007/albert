package com.zdcf.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdcf.dao.TwitterMediaMapper;
import com.zdcf.model.TwitterMedia;
import com.zdcf.service.TwitterMediaService;

@Service
@Transactional
public class TwitterMediaServiceImpl implements  TwitterMediaService {

	
	@Autowired
	private TwitterMediaMapper twitterMediaMapper;
	
	public int insert(TwitterMedia record){
		
		int  count = twitterMediaMapper.insert(record);
		
		return count;
	}
	
	public TwitterMedia findById(Long id){
		
		return twitterMediaMapper.selectByPrimaryKey(id);
	}

	
}
