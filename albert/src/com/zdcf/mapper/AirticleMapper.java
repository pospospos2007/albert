package com.zdcf.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import com.zdcf.model.Airticle;

/**
  * @author l
  * 定义sql映射的接口，使用注解指明方法要执行的SQL
	*/
	public interface AirticleMapper {



		@SelectProvider(type = AirticleSqlProvider.class, method = "getAirticleList")
	public List<Airticle> getAirticleList();

	@Insert("insert into airticle(title,content,insert_time) values (#{title},#{content},now())")
	public void addAirticle(Airticle airticle);

	@Select("select * from airticle where airticle_id=#{airticleId}")
	public Airticle getAirticleDetail(int airticleId);

	
}
