/**
 * 
 */
package com.zdcf.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zdcf.dto.FileDTO;
import com.zdcf.model.WeiboStatus;
import com.zdcf.model.WeiboUser;
import com.zdcf.weibo.Task;

/**
 * @author Administrator
 * note: session is singleton and must be synclized
 */
public interface Plugin {

	@Insert("insert into weibo_status(`id`,`mlevel`,`createdAt`,`mid`,`idstr`,`text`,`favorited`,`truncated`,`thumbnailPic`,`bmiddlePic`,`originalPic`,`geo`)"
			+ " values(#{st.id},#{st.mlevel},#{st.createdAt},#{st.mid},#{st.idstr},#{st.text},#{st.favorited},#{st.truncated},#{st.thumbnailPic},#{st.bmiddlePic},#{st.originalPic},#{st.geo})")
	public void saveWeibo(@Param("t") Task t,@Param("st") WeiboStatus st);

	@Insert("insert into weibo_user(`id`,`city`) values(#{user.id},#{user.city})")
	public void saveUser(@Param("t") Task t,@Param("user")  WeiboUser user);
	
	@Select("select id from weibo_status where id=#{id}")
    public Long getWeiboPostById(@Param("id")Long id);
	
	@Select("select id from weibo_user where id=#{id}")
    public Long getWeiboUserById(@Param("id")Long id);
}
