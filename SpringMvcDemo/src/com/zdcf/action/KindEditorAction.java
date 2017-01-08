package com.zdcf.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zdcf.service.FileService;
import com.zdcf.tool.Tools;


@Controller
@RequestMapping("/kindedtor")
public class KindEditorAction{

	private Logger _log = Logger.getLogger(KindEditorAction.class);
	
	private String imageExt="JPG,JPEG,GIF,PNG,JPG,jpg,jpeg,gif,png,bpm,BPM";
	
	@Resource
	private FileService fileService;
	
	@RequestMapping(value = "/file_upload", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody
	Map<String, Object> fileUpload(
			@RequestParam(value = "imgFile", required = false) MultipartFile mutiparFile,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();
		String[] extArr = imageExt.split(",");
		
		String fileExt = FilenameUtils.getExtension(mutiparFile.getOriginalFilename());
		boolean isAllow = false;
		for (String ext : extArr) {
			if(fileExt != null && fileExt.equals(ext)) {
				isAllow = true;
				break;
			}
		}
		if(isAllow) {
			String path = request.getSession().getServletContext().getRealPath("/")+"uploadimage/";
			String idCardPath = fileService.saveImage(mutiparFile,path);
			String imagePath = idCardPath.replaceAll("\\\\", "/");
			String rPath = "http://"+Tools.getServerIp()+":9999/uploadimage/";
			map.put("status", Boolean.TRUE);
			map.put("filePath", imagePath);
			map.put("fileFormat", fileExt);
			map.put("msg", "上传成功");
			map.put("fileName", mutiparFile.getOriginalFilename());
			map.put("error", 0);
			map.put("url", rPath+imagePath);
		} else {
			map.put("status", Boolean.FALSE);
			map.put("msg", "不允许上传此种类型的文件！");
		}
		return map;
	}

}