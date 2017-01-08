package com.zdcf.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zdcf.dto.FaceAttributeDTO;
import com.zdcf.dto.FaceDTO;
import com.zdcf.dto.FileDTO;
import com.zdcf.model.FileExchange;
import com.zdcf.model.Files;
import com.zdcf.model.Images;

/**
  * @author gacl
  * 定义sql映射的接口，使用注解指明方法要执行的SQL
  */
public interface FileMapper {

    @Select("select f.id id,f.title title,f.url url,f.user_id userId,f.discription discription,f.add_time addTime,f.file_format fileFormat from t_files f where id=#{id}")
    public FileDTO getFileById(int id);
    
    @Select("select f.id id,f.new_url newUrl,f.old_url oldUrl,f.add_time addTime from t_file_exchange f where id=#{id}")
    public FileExchange getFileExchangeById(int id);
    
    @Select("select f.id id,f.new_url newUrl,f.old_url oldUrl,f.add_time addTime from t_file_exchange f where f.old_url=#{oldUrl}")
    public FileExchange getFileExchangeByOldUrl(String oldUrl);
    
    @Delete("delete from t_file_exchange f where f.old_url=#{oldUrl}")
    public FileExchange deleteFileExchangeByOldUrl(String oldUrl);
    
    @Select("select f.id id,f.url url,f.user_id userId,f.add_time addTime from t_face f where f.id=#{id}")
    public FaceDTO getFaceById(int id);
    
    @Select("select f.id id,f.url url,f.user_id userId,f.add_time addTime from t_face f where url=#{url}")
    public FaceDTO getFace(String url);
	
    @Insert("insert into t_files(url,user_id,title,discription,file_format,add_time) values (#{url},#{userId},#{title},#{discription},#{fileFormat},now())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 
	public int addFile(Files file);
    
    @Insert("insert into t_images(url,user_id,name,image_format,add_time) values (#{url},#{userId},#{name},#{imageFormat},now())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 
	public int addImage(Images images);
    
    @Insert("insert into t_face(url,user_id,add_time) values (#{url},#{userId},now())")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 
	public int addFace(FaceDTO face);
    
    @Insert("insert into t_file_exchange(old_url,new_url,add_time) values (#{oldUrl},#{newUrl},now())")
	public int addFileExchange(FileExchange fileExchange);
    
    @Insert("insert into t_face_attribute(gender,glass,age,race,face_id) values (#{gender},#{glass},#{age},#{race},#{face_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 
	public int addFaceAttribute(FaceAttributeDTO faceAttributeDTO);
    
    @Select("select * from t_files ")
    public List<FileDTO> fileList();
    
    @Select("select * from t_face_attribute where face_id=#{faceId}")
    public List<FaceAttributeDTO> getFaceAttr(int faceId);
    
    @Select("select f.id as id,f.title as title,f.add_time as addTime " +
			"from t_files f order by f.add_time desc LIMIT #{offset}, #{pagesize}")
    public List<Map<String, Object>> getFileListPage(
		@Param("offset") int offset, @Param("pagesize") int pagesize);

	@Select("SELECT COUNT(*) from t_files f  ")
	public int getFileCount(@Param("offset") int offset, @Param("pagesize") int pagesize);
	
	
	@Select("select i.id as id,i.name as name,i.url as url,i.add_time as addTime " +
			"from t_images i order by i.add_time desc LIMIT #{offset}, #{pagesize}")
    public List<Map<String, Object>> getImageListPage(
		@Param("offset") int offset, @Param("pagesize") int pagesize);

	@Select("SELECT COUNT(*) from t_images i  ")
	public int getImageCount(@Param("offset") int offset, @Param("pagesize") int pagesize);
	
	
	@Select("select f.id as id,f.url as url,f.add_time as addTime,f.user_id as userId " +
			"from t_face f order by f.add_time desc LIMIT #{offset}, #{pagesize}")
    public List<Map<String, Object>> getFaceListPage(
		@Param("offset") int offset, @Param("pagesize") int pagesize);

	@Select("SELECT COUNT(*) from t_face f  ")
	public int getFaceCount(@Param("offset") int offset, @Param("pagesize") int pagesize);
    
}
