package com.zdcf.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zdcf.model.Movie;

/**
  * @author l
  * 定义sql映射的接口，使用注解指明方法要执行的SQL
	*/
	public interface MovieMapper {

	@Insert("insert into hdwan_res (`name`,`torrent`,`metadata`,`img`,`add_time`,`update_time`,`review_num`) values(#{name},#{torrent},#{metadata},#{img},now(),now(),0)")
	public void addMovie(Movie movie);

//	@Select("select * from airticle where airticle_id=#{airticleId}")
//	public Airticle getAirticleDetail(int airticleId);
	
	@Select("select 1 from hdwan_res where name=#{name}")
	public Integer getMoviebyName(String  name);
	
	@Select("select * from hdwan_res where name=#{name}")
	public Movie getMovieDetailbyName(String  name);
	
	@Select("select id as id,"
			+ "name as name,"
			+ "torrent as torrent,"
			+ "metadata as metadata,"
			+ "img as img,"
			+ "add_time as addTime,"
			+ "update_time as updateTime,"
			+ "review_num as reviewNum from hdwan_res where id=#{id}")
	public Movie getMovieById(int id);
	
	@Update("update hdwan_res set review_num=review_num+1 where id=#{id}")
	public void updateMovieReviewNum(int id);

	
}
