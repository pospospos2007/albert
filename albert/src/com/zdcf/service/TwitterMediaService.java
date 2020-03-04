package com.zdcf.service;

import com.zdcf.model.TwitterMedia;

public interface TwitterMediaService {

	int insert(TwitterMedia record);
	
	TwitterMedia findById(Long id);
}
