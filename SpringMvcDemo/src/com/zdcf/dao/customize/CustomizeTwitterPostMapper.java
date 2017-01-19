package com.zdcf.dao.customize;

import com.zdcf.model.TwitterPost;
import com.zdcf.model.TwitterPostExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CustomizeTwitterPostMapper {
	List<Map<String, Object>> getListPage(@Param("offset") int offset, @Param("pagesize") int pagesize,@Param("twitterPost")TwitterPost twitterPost);
	
	int getCount(@Param("twitterPost")TwitterPost twitterPost);
}