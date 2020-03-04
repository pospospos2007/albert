package com.zdcf.mapper;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Options;

import com.zdcf.model.Airticle;
import com.zdcf.model.User;

/**
  * @author gacl
  * 定义sql映射的接口，使用注解指明方法要执行的SQL
  */
public interface UserMapper {
    
    @Select("select password from t_user where username=#{username}")
    public String getPwdByName(String username);
    
    @Select("select id from t_user where username=#{username}")
    public Integer getUidByName(String username);
    
    @Select("select id ,username ,password ,avatar,email,ip,auto_login_code as autoLoginCode,register_time as registerTime  from t_user where username=#{username}")
    public User getUserByName(String username);
    
    @Select("select username from t_user where id=#{id}")
    public String getNameById(int id);
    
    @Select("select id ,username ,password ,avatar,email,ip,auto_login_code as autoLoginCode,register_time as registerTime  from t_user where id=#{id}")
    public User getUserById(int id);
	
    @Insert("insert into t_user(username,password,ip,register_time,email,avatar,auto_login_code) values (#{username},#{password},#{ip},now(),#{email},#{avatar},#{autoLoginCode})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 
	public int addUser(User user);
    
    @Update("update t_user set avatar=#{avatar} where id=#{id}")
    public int updateAvatar(User user);
    
}
