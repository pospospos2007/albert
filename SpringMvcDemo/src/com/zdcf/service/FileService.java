package com.zdcf.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.zdcf.dto.FaceAttributeDTO;
import com.zdcf.dto.FaceDTO;
import com.zdcf.dto.FileDTO;
import com.zdcf.model.FileExchange;
import com.zdcf.model.Files;
import com.zdcf.model.Images;
import com.zdcf.tool.PageVo;

public interface FileService {

	public int addFile(Files file);
	
	public String saveImage(MultipartFile file,String path);
	
	public List<FileDTO> fileList();
	
	public FileDTO getFileDetail(int id);
	
	public FaceDTO getFace(String url);

	Serializable getFileListPage(PageVo<Map<String, Object>> pageVo2);

	int addImage(Images image);

	Serializable getImageListPage(PageVo<Map<String, Object>> pageVo2);

	Integer addFace(FaceDTO faceDTO);

	Serializable getFaceListPage(PageVo<Map<String, Object>> pageVo2);

	Integer addFaceAttribute(FaceDTO faceDTO);

	FaceDTO getFaceById(int id);

	List<FaceAttributeDTO> getFaceAttr(int faceId);

	int addFileExchange(FileExchange file);

	FileExchange getFileExchange(String url);

}
