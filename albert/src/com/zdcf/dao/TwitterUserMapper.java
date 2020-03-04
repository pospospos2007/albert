package com.zdcf.dao;

import com.zdcf.model.TwitterUser;
import com.zdcf.model.TwitterUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TwitterUserMapper {
    int countByExample(TwitterUserExample example);

    int deleteByExample(TwitterUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TwitterUser record);

    int insertSelective(TwitterUser record);

    List<TwitterUser> selectByExample(TwitterUserExample example);

    TwitterUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TwitterUser record, @Param("example") TwitterUserExample example);

    int updateByExample(@Param("record") TwitterUser record, @Param("example") TwitterUserExample example);

    int updateByPrimaryKeySelective(TwitterUser record);

    int updateByPrimaryKey(TwitterUser record);
}