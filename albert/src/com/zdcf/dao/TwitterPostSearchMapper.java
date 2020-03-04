package com.zdcf.dao;

import com.zdcf.model.TwitterPostSearch;
import com.zdcf.model.TwitterPostSearchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TwitterPostSearchMapper {
    int countByExample(TwitterPostSearchExample example);

    int deleteByExample(TwitterPostSearchExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TwitterPostSearch record);

    int insertSelective(TwitterPostSearch record);

    List<TwitterPostSearch> selectByExample(TwitterPostSearchExample example);

    TwitterPostSearch selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TwitterPostSearch record, @Param("example") TwitterPostSearchExample example);

    int updateByExample(@Param("record") TwitterPostSearch record, @Param("example") TwitterPostSearchExample example);

    int updateByPrimaryKeySelective(TwitterPostSearch record);

    int updateByPrimaryKey(TwitterPostSearch record);
}