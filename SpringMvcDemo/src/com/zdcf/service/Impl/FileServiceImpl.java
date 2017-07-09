package com.zdcf.service.Impl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.SneakyThrows;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;
import com.zdcf.base.Constants;
import com.zdcf.dto.FaceAttributeDTO;
import com.zdcf.dto.FaceDTO;
import com.zdcf.dto.FileDTO;
import com.zdcf.mapper.FileMapper;
import com.zdcf.model.FileExchange;
import com.zdcf.model.Files;
import com.zdcf.model.Images;
import com.zdcf.model.Movie;
import com.zdcf.service.FileService;
import com.zdcf.tool.DateUtil;
import com.zdcf.tool.PageVo;
import com.zdcf.tool.StringUtil;

@Service
@Transactional
public class FileServiceImpl implements  FileService {

	@Autowired
	private FileMapper fileMapper;
	
	@Autowired
	RedisCacheService redisCacheService;
	
	@Autowired    
	private HttpServletRequest request;  
	private static Logger logger = Logger.getLogger(FileServiceImpl.class);
	
	@Override
	public int addFile(Files file) {
		return fileMapper.addFile(file);
	}
	
	@Override
	public int addFileExchange(FileExchange file) {
		fileMapper.addFileExchange(file);
		FileExchange fileExchange = fileMapper.getFileExchangeByOldUrl(file.getOldUrl());
//		redisCacheService.process(fileExchange, Constants.Cache.Type.save);
		return 1;
	}
	
	@Override
	public int addImage(Images image) {
		return fileMapper.addImage(image);
	}
	
	@Override
	public Integer addFace(FaceDTO face) {
		return fileMapper.addFace(face);
	}
	
	@Override
	public String saveImage(MultipartFile file,String path) {
		
		if(file == null || file.getSize() <= 0){
			return null;
		}
		
		String origFileName = file.getOriginalFilename();
		
		String ext = FilenameUtils.getExtension(origFileName);

		String destPath = getImagePath(ext);
		
		String saveFileName = destPath + "." + ext;
		
		saveFile(file, saveFileName,path);
		
		//小图
		/*String iconFileName = destPath + "_0." + ext;
		
		String base = this.getImageSavePath() + File.separator;
		
		//ImageUtil.zoomImg(base + saveFileName, base + iconFileName, ext.toUpperCase());
*/		
		return saveFileName;
	}
	
	private String getImagePath(String ext){
		Date now = new Date();
		String yym = DateUtil.dateToStr(now, "yyMM");
		
		/*String saveFileName = "images" + File.separator + yym + File.separator + now.getDate()
				              + File.separator + now.getHours() + File.separator
				              + now.getMinutes() + File.separator + System.currentTimeMillis();*/
		
		String saveFileName = "images" + "/" + yym + "/" + now.getDate()
	              + "/" + now.getHours() + "/"
	              + now.getMinutes() + "/" + System.currentTimeMillis();
		
		return saveFileName;
	}
	
	
	public void saveFile(MultipartFile file, String destPath,String savePath){
		try {

			logger.debug("saving file for: " + destPath + ", file name is: " + file.getOriginalFilename());

			File destFile = new File(savePath + File.separator + destPath);
			
			logger.debug(destFile.getAbsolutePath());
			
			if (!(destFile.getParentFile().exists())) {
				destFile.mkdirs();
			}

			file.transferTo(destFile);
			logger.debug("saving file succeed for: " + destPath
					+ ", file name is: " + file.getOriginalFilename());

		} catch (IOException ioe) {
			logger.error("error uploading file for: " + destPath
					+ ", file name is: " + file.getOriginalFilename());
			logger.error(ioe);

			throw new RuntimeException("文件保存失败");
		}
	}

	@Override
	public List<FileDTO> fileList() {
		
		return fileMapper.fileList();
	}
	
	@Override
	public List<FaceAttributeDTO> getFaceAttr(int faceId) {
		
		return fileMapper.getFaceAttr(faceId);
	}

	@Override
	public FileDTO getFileDetail(int id) {
		
		
		return fileMapper.getFileById(id);
	}
	
	@Override
	public Serializable getFileListPage(
			PageVo<Map<String, Object>> pageVo2) {
		PageVo<Map<String, Object>> pageVo = (PageVo<Map<String, Object>>) pageVo2;
		int offset = pageVo.getCurrentPage() - 1;
		if (offset < 0)
			offset = 0;
		List<Map<String, Object>> commonNewsList = fileMapper
				.getFileListPage( offset * pageVo.getPageSize(),
						pageVo.getPageSize());
		int count = fileMapper.getFileCount(offset* pageVo.getPageSize(), pageVo.getPageSize());
		pageVo.setVoList(commonNewsList);
		pageVo.setRecordCount(count);
		return pageVo;
		
	}
	
	
	@Override
	public Serializable getImageListPage(
			PageVo<Map<String, Object>> pageVo2) {
		PageVo<Map<String, Object>> pageVo = (PageVo<Map<String, Object>>) pageVo2;
		int offset = pageVo.getCurrentPage() - 1;
		if (offset < 0)
			offset = 0;
		List<Map<String, Object>> commonNewsList = fileMapper
				.getImageListPage( offset * pageVo.getPageSize(),
						pageVo.getPageSize());
		int count = fileMapper.getImageCount(offset* pageVo.getPageSize(), pageVo.getPageSize());
		pageVo.setVoList(commonNewsList);
		pageVo.setRecordCount(count);
		return pageVo;
		
	}
	
	@Override
	public Serializable getFaceListPage(
			PageVo<Map<String, Object>> pageVo2) {
		PageVo<Map<String, Object>> pageVo = (PageVo<Map<String, Object>>) pageVo2;
		int offset = pageVo.getCurrentPage() - 1;
		if (offset < 0)
			offset = 0;
		List<Map<String, Object>> commonNewsList = fileMapper
				.getFaceListPage( offset * pageVo.getPageSize(),
						pageVo.getPageSize());
		
		//put people attributes
		for (Map<String, Object> map : commonNewsList) {
			List<FaceAttributeDTO> faceAttr = fileMapper.getFaceAttr(StringUtil.ObjectToIntegerUtil(map.get("id")));
			map.put("faceAttr", faceAttr);
		}
		
		int count = fileMapper.getFaceCount(offset* pageVo.getPageSize(), pageVo.getPageSize());
		pageVo.setVoList(commonNewsList);
		pageVo.setRecordCount(count);
		return pageVo;
		
	}
	
	@Override
	public Integer addFaceAttribute(FaceDTO faceDTO){
		
		HttpRequests httpRequests = new HttpRequests("dd0094b405bf9612a58f181ee3acde3a", "fcmx4kqd0fZzOzQKOFiYcldgE92ECNLx", true, true);
		
		JSONObject result = null;
			
		try {
			
			System.out.println(Charset.forName("UTF-8").name());
		
			PostParameters params = new PostParameters();
			params.setAttribute("glass,pose,gender,age,race,smiling");
			params.setUrl(faceDTO.getUrl());
			result = httpRequests.detectionDetect(params);
			System.out.println(result);
			
			Integer size = result.getJSONArray("face").length();
			
			if(null!=size&&size>0){
				for(int i=0;i<size;i++){
					FaceAttributeDTO attr = new FaceAttributeDTO();
					JSONObject attribute = result.getJSONArray("face").getJSONObject(i).getJSONObject("attribute");
					attr.setAge(Integer.parseInt(attribute.getJSONObject("age").get("value").toString()));
					attr.setGender(attribute.getJSONObject("gender").get("value").toString());
					attr.setGlass(attribute.getJSONObject("glass").get("value").toString());
					attr.setRace(attribute.getJSONObject("race").get("value").toString());
					attr.setFace_id(faceDTO.getId());
					fileMapper.addFaceAttribute(attr);
				}
			}
			
			
		} catch(FaceppParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} 
	
		return 0;
	}

	@Override
	public FaceDTO getFace(String url) {
		return fileMapper.getFace(url);
	}
	
	@Override
	public FaceDTO getFaceById(int id) {
		return fileMapper.getFaceById(id);
	}
	
	@SneakyThrows
	@Override
	public FileExchange getFileExchange(String url){
		//查询redis和数据库是否有此文件
		FileExchange temp = new FileExchange();
		temp.setOldUrl(url);
		
		FileExchange fileExchange = null;
//		FileExchange fileExchange = redisCacheService.get(temp.getCacheKey(), FileExchange.class);
		
		//不在缓存中，去数据库中查询
//		if(fileExchange == null){
//			fileExchange = fileMapper.getFileExchangeByOldUrl(url);
//			if(fileExchange !=null){
//				File tempFile = new File(request.getSession().getServletContext().getRealPath("/")+"uploadfile/"+fileExchange.getNewUrl());
//				if(tempFile.exists()){
//					redisCacheService.process(fileExchange, Constants.Cache.Type.save);
//				}else{
//					System.out.println("----------------------------url="+url);
//					fileMapper.deleteFileExchangeByOldUrl(url);
//					fileExchange =null ;
//				}
//			}
//		}else{
//			File file = new File(request.getSession().getServletContext().getRealPath("/")+"uploadfile/"+fileExchange.getNewUrl());
//			if(!file.exists()){
//				redisCacheService.del(temp.getCacheKey());
//				fileExchange =null ;
//			}
//		}
		
		fileExchange = fileMapper.getFileExchangeByOldUrl(url);
		
		
		return fileExchange;
	}


}
