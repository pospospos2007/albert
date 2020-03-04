package com.zdcf.dao;

import com.zdcf.model.TwitterMedia;
import com.zdcf.model.TwitterMediaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TwitterMediaMapper {
    int countByExample(TwitterMediaExample example);

    int deleteByExample(TwitterMediaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TwitterMedia record);

    int insertSelective(TwitterMedia record);

    List<TwitterMedia> selectByExample(TwitterMediaExample example);

    TwitterMedia selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TwitterMedia record, @Param("example") TwitterMediaExample example);

    int updateByExample(@Param("record") TwitterMedia record, @Param("example") TwitterMediaExample example);

    int updateByPrimaryKeySelective(TwitterMedia record);

    int updateByPrimaryKey(TwitterMedia record);
}