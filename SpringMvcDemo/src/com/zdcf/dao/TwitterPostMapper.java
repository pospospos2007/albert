package com.zdcf.dao;

import com.zdcf.model.TwitterPost;
import com.zdcf.model.TwitterPostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TwitterPostMapper {
    int countByExample(TwitterPostExample example);

    int deleteByExample(TwitterPostExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TwitterPost record);

    int insertSelective(TwitterPost record);

    List<TwitterPost> selectByExample(TwitterPostExample example);

    TwitterPost selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TwitterPost record, @Param("example") TwitterPostExample example);

    int updateByExample(@Param("record") TwitterPost record, @Param("example") TwitterPostExample example);

    int updateByPrimaryKeySelective(TwitterPost record);

    int updateByPrimaryKey(TwitterPost record);
}