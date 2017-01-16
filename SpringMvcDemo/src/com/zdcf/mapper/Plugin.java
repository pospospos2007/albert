/**
 * 
 */
package com.zdcf.mapper;

import org.apache.ibatis.annotations.Insert;

import com.zdcf.model.WeiboStatus;
import com.zdcf.model.WeiboUser;
import com.zdcf.weibo.Task;

/**
 * @author Administrator
 * note: session is singleton and must be synclized
 */
public interface Plugin {

	@Insert("insert into weibo_status(`mlevel`) values(#{mlevel})")
	public void saveWeibo(Task t, WeiboStatus st);

	@Insert("insert into weibo_user(`city`) values(#{city})")
	public void saveUser(Task t, WeiboUser user);

}
