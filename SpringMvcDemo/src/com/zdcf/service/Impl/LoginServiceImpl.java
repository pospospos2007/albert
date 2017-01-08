//package com.zdcf.service.Impl;
//
//
//import mapper.LoginMapper;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Isolation;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import po.Staff;
//import service.LoginService;
//@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=5)
//@Service("loginservice")
//public class LoginServiceImpl implements LoginService{
//	@Autowired
//	UserMapper userMapper;
//	
//	public String getpwdbyname(String name) {
//		Staff s=userMapper.getpwdbyname(name);
//		if(s!=null)
//		return s.getPassword();
//		else
//		return null;
//	}
//	public Long getUidbyname(String name) {
//		Staff s=userMapper.getpwdbyname(name);
//		if(s!=null)
//			return (long) s.getStaff_id();
//			else
//			return null;
//	}
//	public String getnamebyid(long id) {
//		Staff s=userMapper.getnamebyid(id);
//		if(s!=null)
//			return s.getUsername();
//			else
//			return null;
//	}
//	
//	
//
//}
