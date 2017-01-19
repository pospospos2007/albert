package com.zdcf.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdcf.dao.TwitterUserMapper;
import com.zdcf.model.TwitterUser;
import com.zdcf.service.TwitterUserService;

@Service
@Transactional
public class TwitterUserServiceImpl implements  TwitterUserService {

	
	@Autowired
	private TwitterUserMapper twitterUserMapper;
	
	@Override
	public int insert(TwitterUser twitterUser){
		
		int  count = twitterUserMapper.insert(twitterUser);
		
		return count;
	}
	
	@Override
	public TwitterUser findById(Long id){
		
		return twitterUserMapper.selectByPrimaryKey(id);
	}
	
}
