package com.zdcf.service;

import com.zdcf.model.TwitterUser;

public interface TwitterUserService {

	int insert(TwitterUser twitterUser);

	TwitterUser findById(Long id);

}
