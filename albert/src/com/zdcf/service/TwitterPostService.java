package com.zdcf.service;

import java.io.Serializable;
import java.util.Map;

import com.zdcf.model.TwitterPost;
import com.zdcf.tool.PageVo;

public interface TwitterPostService {

	int insert(TwitterPost record);

	TwitterPost findById(Long id);

	Serializable getListPage(PageVo<Map<String, Object>> pageVo, TwitterPost tp);

}
