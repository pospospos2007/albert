package com.zdcf.dao.customize;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zdcf.model.TwitterSearchHistory;

public interface CustomizeTwitterSearchHistoryMapper {
	
	List<Map<String, Object>> getListPage(@Param("offset") int offset, @Param("pagesize") int pagesize,@Param("twitterSearchHistory")TwitterSearchHistory twitterSearchHistory);
	
	int getCount(@Param("twitterSearchHistory")TwitterSearchHistory twitterSearchHistory);
}