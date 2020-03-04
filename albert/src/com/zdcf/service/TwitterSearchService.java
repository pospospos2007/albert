package com.zdcf.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.zdcf.model.TwitterPost;
import com.zdcf.model.TwitterSearchHistory;
import com.zdcf.tool.PageVo;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

public interface TwitterSearchService {

	int insert(TwitterPost record);

	TwitterPost findById(Long id);

	Serializable getListPage(PageVo<Map<String, Object>> pageVo, TwitterSearchHistory tsh);

	Map<String, Object> check(TwitterSearchHistory tsh) throws RuntimeException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException;

}
