package com.zdcf.dao;

import com.zdcf.model.TwitterSearchHistory;
import com.zdcf.model.TwitterSearchHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TwitterSearchHistoryMapper {
    int countByExample(TwitterSearchHistoryExample example);

    int deleteByExample(TwitterSearchHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TwitterSearchHistory record);

    int insertSelective(TwitterSearchHistory record);

    List<TwitterSearchHistory> selectByExampleWithBLOBs(TwitterSearchHistoryExample example);

    List<TwitterSearchHistory> selectByExample(TwitterSearchHistoryExample example);

    TwitterSearchHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TwitterSearchHistory record, @Param("example") TwitterSearchHistoryExample example);

    int updateByExampleWithBLOBs(@Param("record") TwitterSearchHistory record, @Param("example") TwitterSearchHistoryExample example);

    int updateByExample(@Param("record") TwitterSearchHistory record, @Param("example") TwitterSearchHistoryExample example);

    int updateByPrimaryKeySelective(TwitterSearchHistory record);

    int updateByPrimaryKeyWithBLOBs(TwitterSearchHistory record);

    int updateByPrimaryKey(TwitterSearchHistory record);
}