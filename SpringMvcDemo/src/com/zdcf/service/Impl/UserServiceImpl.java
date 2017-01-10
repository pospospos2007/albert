package com.zdcf.service.Impl;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
//import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdcf.mapper.UserMapper;
import com.zdcf.model.User;
import com.zdcf.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements  UserService {

	@Resource
	private UserMapper userMapper;
	
	@Override
	public User getUserByIp(String ip) {
		return userMapper.getUserByIp(ip);
	}

	@Override
	public int addUserByIp(String ip) {
		return userMapper.addUserByIp(ip);
	}

	
//	@Test
	public String getZhiHuAirticleList() {
		
		HttpClient client = new DefaultHttpClient();
		 
		 HttpGet get = new HttpGet("http://news-at.zhihu.com/api/4/news/latest");
		 
		 HttpResponse response;
		 
		 String result = null;
		try {
			response = client.execute(get);
			
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			System.out.println(result);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return result;
	}
	
	public String getPwdByName(String userName){
		return userMapper.getPwdByName(userName);
	}
	
	public Integer getUidByName(String userName){
		return userMapper.getUidByName(userName);
	}
	
	public User getUserByName(String userName){
		return userMapper.getUserByName(userName);
	}
	
	public String getNameById(Integer id){
		return userMapper.getNameById(id);
	}

}
