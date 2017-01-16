package com.zdcf.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.zdcf.dto.MessageDTO;
import com.zdcf.dto.ThemeDTO;
import com.zdcf.dto.ZhihuDTO;
import com.zdcf.model.Airticle;
import com.zdcf.model.Message;
import com.zdcf.model.Theme;
import com.zdcf.model.Zhihu;

/**
  * @author l
  * 定义sql映射的接口，使用注解指明方法要执行的SQL
  */
public interface MessageMapper {

   
	
	@SelectProvider(type = MessageSqlProvider.class, method = "getMessagesByThemeId")
	public List<MessageDTO> getMessagesByThemeId(int id);
	
	@SelectProvider(type = MessageSqlProvider.class, method = "getThemeList")
	public List<ThemeDTO> getThemeList();

	@Insert("insert into t_theme(theme,content,user_id,add_time,update_time) values (#{theme},#{content},#{userId},now(),now())")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 
	public int addTheme(Theme theme);
	
	@Select("select t.id as id,t.theme as theme," +
			"t.content as content,DATE_FORMAT(t.add_time,'%Y-%m-%d %h:%i') as addTime,t.user_id as userId,u.username as username " +
			"from t_theme t left join t_user u on u.id=t.user_id order by t.update_time desc LIMIT #{index}, #{count}")
	public List<HashMap<String, Object>> findNextTheme(@Param("index") int index,@Param("count") int count);
	
	@Insert("insert into t_zhihu(id,title,content,add_time,images,css,js,update_time,review_num) values (#{id},#{title},#{content},now(),#{images},#{css},#{js},now(),0)")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 
	public int addZhihuAirticle(Zhihu zhihu);
	
	@Insert("insert into t_message(message,user_id,theme_id,add_time) values (#{message},#{userId},#{themeId},now())")
	public int addMessage(Message message);
	
	@Update("update  t_theme set update_time=now() where id=#{id}")
	public int updateThemeTime(int id);
	
	@Select("select t.id id,"
			+ "t.theme theme,"
			+ "t.content content,"
			+ "t.add_time addTime,"
			+ "t.user_id userId,"
			+ "u.ip as ip,"
			+ "u.username as username,"
			+ "u.avatar as avatar,"
			+ "u.email as email  "
			+ "from t_theme t left join t_user u on t.user_id=u.id  where t.id=#{id}")
	public ThemeDTO getThemeById(int id);
	
	@Select("select t.id id,"
			+ "t.title title,"
			+ "t.content content,"
			+ "t.add_time addTime,"
			+ "t.images as images,"
			+ "t.css as css,"
			+ "t.js as js,"
			+ "t.update_time as updateTime,"
			+ "t.review_num as reviewNum from t_zhihu t  where t.id=#{id}")
	public ZhihuDTO getZhihuDetailById(int id);
	
	@Select("select t.id id,"
			+ "t.title title,"
			+ "t.content content,"
			+ "t.add_time addTime,"
			+ "t.images as images,"
			+ "t.css as css,"
			+ "t.js as js,"
			+ "t.update_time as updateTime,"
			+ "t.review_num as reviewNum from t_zhihu t ")
	public List<Zhihu> getAllZhihu();
	
	@Select("select * from t_theme where theme=#{theme}")
	public Theme getThemeByTheme(String theme);
	
	@Select("select t.id as id,t.theme as theme," +
				"t.content as content,t.add_time as addTime,t.user_id as userId,u.username as username " +
				"from t_theme t left join t_user u on u.id=t.user_id order by t.update_time desc LIMIT #{offset}, #{pagesize}")
	public List<Map<String, Object>> getThemeListPage(
			@Param("offset") int offset, @Param("pagesize") int pagesize);
	
	@Select("SELECT COUNT(t.id) from t_theme t")
	public int getThemeCount(@Param("offset") int offset, @Param("pagesize") int pagesize);
	
	@Update("set names utf8mb4")
    public void setCharsetToUtf8mb4();
	
	@Select("select t.id as id,t.title as title," +
			"t.content as content,t.add_time as addTime,t.images as images " +
			"from t_zhihu t  order by t.add_time desc LIMIT #{offset}, #{pagesize}")
	public List<Map<String, Object>> getZhihuArticleListPage(
			@Param("offset") int offset, @Param("pagesize") int pagesize);
	
	@Select("SELECT COUNT(t.id) from t_zhihu t  ")
	public int getZhihuArticleCount(@Param("offset") int offset, @Param("pagesize") int pagesize);
	
	@Select("select t.id as id,t.title as title," +
			"t.content as content,DATE_FORMAT(t.add_time,'%Y-%m-%d %h:%i') as addTime,t.images as images " +
			"from t_zhihu t  order by t.add_time desc LIMIT #{index},#{count}")
	public List<HashMap<String, Object>> findNextZhihuAirticle(@Param("index") int index,@Param("count") int count);

	
	
	

	
}
