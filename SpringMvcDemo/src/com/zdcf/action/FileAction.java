package com.zdcf.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.zdcf.base.Constants;
import com.zdcf.dto.FaceAttributeDTO;
import com.zdcf.dto.FaceDTO;
import com.zdcf.dto.FileDTO;
import com.zdcf.model.Files;
import com.zdcf.model.Images;
import com.zdcf.model.User;
import com.zdcf.service.FileService;
import com.zdcf.service.UserService;
import com.zdcf.tool.PageVo;
import com.zdcf.tool.Tools;
import com.zdcf.tool.UploadFileUtils;
import com.zdcf.tool.UserSessionUtil;
import com.zdcf.tool.WebUtil;

@Controller
@RequestMapping("/file")
public class FileAction implements ServletContextAware{
	
	@Resource
	private FileService fileService;
	
	@Resource
	private UserService userService;
	
	private static final int pageSize = 10;
	
	private ServletContext servletContext; 
	
	private String fileExt="JPG,JPEG,GIF,PNG,PDF,DOC,DOCX,TXT,JPG,XLS,XLSX,jpg,jpeg,gif,png,pdf,doc,docx,txt,jpg,xls,xlsx,torrent";

	private String imageExt="JPG,JPEG,GIF,PNG,JPG,jpg,jpeg,gif,png,bpm,BPM";
	
	private String faceExt="JPG,JPEG,PNG,jpg,jpeg,png,bpm,BPM";
	
	private static Logger logger = Logger.getLogger(FileAction.class);
	private String ip;

	@RequestMapping("/addFile")
    public String addFile(HttpServletRequest request,@RequestParam("discription")String discription,@RequestParam("title")String title,@RequestParam("url")String url,@RequestParam("fileCode")String fileCode,@RequestParam("fileFormat")String fileFormat,ModelMap model){
    	
    	String realcode = request.getSession().getAttribute(Constants.SESSION_IMAGE_CODE).toString();
    	
    	if(null==fileCode||"".equals(fileCode)||!fileCode.equals(realcode)){
			return "redirect:/message/getAllTheme"; 
		}else{
			
			//验证成功后将session的验证码更新，防止再次使用此验证码发送主题
			String valcode  = "";
		       Random rd =  new Random();
		       for(int i=0; i<4; i++)
		           valcode+=rd.nextInt(10);
		       // 把产生的验证码存入到Session中
		       HttpSession  session = request.getSession();
		       session.setAttribute(Constants.SESSION_IMAGE_CODE, valcode);
		}
    	
    	
    	String ip =  Tools.getNoHTMLString(Tools.getIpAddr(request));
		
		User u = UserSessionUtil.currentUser();
		
    	Files file = new Files();
    	
		file.setUserId(u.getId());
		
    	file.setDiscription(Tools.getNoHTMLString(discription));
    	
    	file.setUrl(url);
    	
    	file.setTitle(StringFilter(title));
    	
    	file.setFileFormat(fileFormat);
    	
    	fileService.addFile(file);
    	
    	return "redirect:/file/fileList";
    }
    
    
    @RequestMapping("/addImage")
    public String addImage(HttpServletRequest request,@RequestParam("name")String name,@RequestParam("url")String url,@RequestParam("imageCode")String imageCode,@RequestParam("imageFormat")String imageFormat,ModelMap model){
    	
    	String realcode = request.getSession().getAttribute(Constants.SESSION_IMAGE_CODE).toString();
    	
    	if(null==imageCode||"".equals(imageCode)||!imageCode.equals(realcode)){
			return "redirect:/message/getAllTheme"; 
		}else{
			
			//验证成功后将session的验证码更新，防止再次使用此验证码发送主题
			String valcode  = "";
		       Random rd =  new Random();
		       for(int i=0; i<4; i++)
		           valcode+=rd.nextInt(10);
		       // 把产生的验证码存入到Session中
		       HttpSession  session = request.getSession();
		       session.setAttribute(Constants.SESSION_IMAGE_CODE, valcode);
		}
    	
    	
    	String ip = Tools.getNoHTMLString(Tools.getIpAddr(request));
		
		User u = UserSessionUtil.currentUser();
		
		Images image = new Images();
    	
		image.setUserId(u.getId());
    	
    	image.setUrl(url);
    	
    	image.setName(StringFilter(name));
    	
    	image.setImageFormat(imageFormat);
    	
    	fileService.addImage(image);
    	
    	return "redirect:/file/faceList";
    }
    
    
    @RequestMapping("/addFace")
    public String addFace(HttpServletRequest request,@RequestParam("url")String url,@RequestParam("faceCode")String faceCode,ModelMap model){
    	
    	String realcode = request.getSession().getAttribute(Constants.SESSION_IMAGE_CODE).toString();
    	
    	if(null==faceCode||"".equals(faceCode)||!faceCode.equals(realcode)){
			return "redirect:/file/faceList"; 
		}else{
			
			//验证成功后将session的验证码更新，防止再次使用此验证码发送主题
			String valcode  = "";
		       Random rd =  new Random();
		       for(int i=0; i<4; i++)
		           valcode+=rd.nextInt(10);
		       // 把产生的验证码存入到Session中
		       HttpSession  session = request.getSession();
		       session.setAttribute(Constants.SESSION_IMAGE_CODE, valcode);
		}
    	
    	
    	String ip = Tools.getNoHTMLString(Tools.getIpAddr(request));
		
//    	if(ip.equals("117.38.164.79")||ip.contains("210.195.160")||ip.contains("119.188.94")||ip.contains("119.29.111")){
//    		return "/file/faceListNew";
//    	}
		User u = UserSessionUtil.currentUser();
		
		FaceDTO face = new FaceDTO();
    	
		face.setUserId(u.getId());
    	
    	face.setUrl(url);
    	fileService.addFace(face);
    	int id = fileService.getFace(url).getId();
    	face.setId(id);
//    	String path = "http://"+Tools.getLocalIP()+":8080/SpringMvcDemo/uploadface/"+url;
//    	String path = "http://"+Tools.getServerIp()+":9999/uploadface/"+url;
    	String path = "http://albert6.com:9999/uploadface/"+url;
    	System.out.println(path);
    	face.setUrl(path);
    	fileService.addFaceAttribute(face);
    	
    	return "redirect:/file/faceDetail?id="+id;
    }
    
    @RequestMapping("/imageList")
    public String imageList(ModelMap model,HttpServletRequest request){
    	
//    	List<FileDTO> files = new ArrayList<FileDTO>();
//		
//    	files = fileService.fileList();
//		
//		model.addAttribute("files", files);
//    	
//    	return "/file/fileList";
    	
    	PageVo<Map<String, Object>> pageVo = WebUtil.findPageVo(request);
    	
		pageVo.setPageSize(pageSize);
		String currentPage =  "1";
		if(null!=request.getParameter("currentPage")){
			currentPage =request.getParameter("currentPage");
		}
		pageVo.setCurrentPage(Integer.parseInt(currentPage));
		pageVo = (PageVo<Map<String, Object>>) fileService.getImageListPage(pageVo);
		model.addAttribute("pageView", pageVo);
		
		return "/file/imageList";
    }
    
    @RequestMapping("/faceList")
    public String faceList(ModelMap model,HttpServletRequest request){
    	
    	PageVo<Map<String, Object>> pageVo = WebUtil.findPageVo(request);
    	
		pageVo.setPageSize(pageSize);
		String currentPage =  "1";
		if(null!=request.getParameter("currentPage")){
			currentPage =request.getParameter("currentPage");
		}
		pageVo.setCurrentPage(Integer.parseInt(currentPage));
		pageVo = (PageVo<Map<String, Object>>) fileService.getFaceListPage(pageVo);
		model.addAttribute("pageView", pageVo);
		
		return "/file/faceListNew";
    }
    
    
    @RequestMapping("/fileList")
    public String fileList(ModelMap model,HttpServletRequest request){
    	
//    	List<FileDTO> files = new ArrayList<FileDTO>();
//		
//    	files = fileService.fileList();
//		
//		model.addAttribute("files", files);
//    	
//    	return "/file/fileList";
    	
    	PageVo<Map<String, Object>> pageVo = WebUtil.findPageVo(request);
    	
		pageVo.setPageSize(pageSize);
		String currentPage =  "1";
		if(null!=request.getParameter("currentPage")){
			currentPage =request.getParameter("currentPage");
		}
		pageVo.setCurrentPage(Integer.parseInt(currentPage));
		pageVo = (PageVo<Map<String, Object>>) fileService.getFileListPage(pageVo);
		model.addAttribute("pageView", pageVo);
		
		

		return "/file/fileList";
    }
    
    @RequestMapping("/toAddFile")
    public String toAddFile(){
    	
    	User u = UserSessionUtil.currentUser();
		if(null==u){
			return "index/login";
		}
		
    	return "/file/addFile";
    }
    
    
    @RequestMapping("/toUploadImage")
    public String toUploadImage(){
    	
    	User u = UserSessionUtil.currentUser();
		if(null==u){
			return "index/login";
		}
		
    	return "/file/addImage";
    }
    
    @RequestMapping("/toAddFace")
    public String toAddFace(){
    	
    	User u = UserSessionUtil.currentUser();
		if(null==u){
			return "index/login";
		}
    	return "/file/addFace";
    }
    
    
    @RequestMapping("/fileDetail")
    public String fileDetail(@RequestParam("fileId")int fileId,HttpServletRequest request,ModelMap model){
    	
		String ip = Tools.getNoHTMLString(Tools.getIpAddr(request));
		
		FileDTO file = fileService.getFileDetail(fileId);
		
		System.out.println(file.toString());
		
		model.addAttribute("file",file);
		
    	return "/file/fileDetail";
    }
    
    @RequestMapping("/faceDetail")
    public String faceDetail(@RequestParam("id")int id,HttpServletRequest request,ModelMap model){
    	
//		String ip = Tools.getNoHTMLString(Tools.getIpAddr(request));
		
		FaceDTO face = fileService.getFaceById(id);
		
		List<FaceAttributeDTO> faceAttrs = fileService.getFaceAttr(id);
		
		model.addAttribute("face",face);
		
		model.addAttribute("faceAttrs",faceAttrs);
		
    	return "/file/faceDetail";
    }
    
    /**
	 * 文件上传 
	 * @param mutiparFile
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/uploadImg",method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String,Object> uploadImg(HttpServletRequest request,@RequestParam("file")MultipartFile mutiparFile,ModelMap model){
		Map<String,Object> map = this.initMapStatus();
		String[] extArr = fileExt.split(",");
		try{
			String fileExt = FilenameUtils.getExtension(mutiparFile.getOriginalFilename());
			boolean isAllow = false;
			for (String ext : extArr) {
				if(fileExt != null && fileExt.equals(ext)) {
					isAllow = true;
					break;
				}
			}
			if(isAllow) {
				String path = request.getSession().getServletContext().getRealPath("/")+"uploadfile/";
				String idCardPath = fileService.saveImage(mutiparFile,path);
				String imagePath = idCardPath.replaceAll("\\\\", "/");
				map.put("status", Boolean.TRUE);
				map.put("filePath", imagePath);
				map.put("fileFormat", fileExt);
				map.put("msg", "上传成功");
				map.put("fileName", mutiparFile.getOriginalFilename());
			} else {
				map.put("status", Boolean.FALSE);
				map.put("msg", "不允许上传此种类型的文件！");
			}
			return map;
		}catch(Exception e){
			logger.error(e);
			map.put("status", Boolean.FALSE);
			map.put("msg", "上传出错！");
		}
		return map;
	}
	
	
	 /**
		 * 图片上传 
		 * @param mutiparFile
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/uploadImage",method = {RequestMethod.GET,RequestMethod.POST})
		public @ResponseBody Map<String,Object> uploadImage(HttpServletRequest request,@RequestParam("file")MultipartFile mutiparFile,ModelMap model){
			Map<String,Object> map = this.initMapStatus();
			String[] extArr = imageExt.split(",");
			try{
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
					map.put("status", Boolean.TRUE);
					map.put("filePath", imagePath);
					map.put("fileFormat", fileExt);
					map.put("msg", "上传成功");
					map.put("fileName", mutiparFile.getOriginalFilename());
				} else {
					map.put("status", Boolean.FALSE);
					map.put("msg", "不允许上传此种类型的文件！");
				}
				return map;
			}catch(Exception e){
				logger.error(e);
				map.put("status", Boolean.FALSE);
				map.put("msg", "上传出错！");
			}
			return map;
		}
		
		 /**
		 * 人像上传 
		 * @param mutiparFile
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/uploadFace",method = {RequestMethod.GET,RequestMethod.POST})
		public @ResponseBody Map<String,Object> uploadFace(HttpServletRequest request,@RequestParam("file")MultipartFile mutiparFile,ModelMap model){
			Map<String,Object> map = this.initMapStatus();
			String[] extArr = imageExt.split(",");
			try{
				String fileExt = FilenameUtils.getExtension(mutiparFile.getOriginalFilename());
				boolean isAllow = false;
				for (String ext : extArr) {
					if(fileExt != null && fileExt.equals(ext)) {
						isAllow = true;
						break;
					}
				}
				if(isAllow) {
					String path = request.getSession().getServletContext().getRealPath("/")+"uploadface/";
					String idCardPath = fileService.saveImage(mutiparFile,path);
					String imagePath = idCardPath.replaceAll("\\\\", "/");
					map.put("status", Boolean.TRUE);
					map.put("filePath", imagePath);
					map.put("fileFormat", fileExt);
					map.put("msg", "上传成功");
					map.put("fileName", mutiparFile.getOriginalFilename());
				} else {
					map.put("status", Boolean.FALSE);
					map.put("msg", "不允许上传此种类型的文件！");
				}
				return map;
			}catch(Exception e){
				logger.error(e);
				map.put("status", Boolean.FALSE);
				map.put("msg", "上传出错！");
			}
			return map;
		}
	
	public Map<String, Object> initMapStatus() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", Boolean.TRUE);
		map.put("msg", "");
		
		return map;
	}
	
	
	@RequestMapping(value = "/downloadFile")
	public void downloadFile(@RequestParam("id")Integer id,HttpServletResponse response,HttpServletResponse request,
            ModelMap model){
		try {
		FileDTO f = fileService.getFileDetail(id);
		 //获取网站部署路径(通过ServletContext对象)，用于确定下载文件位置，从而实现下载  
        String path = servletContext.getRealPath("/");  
  
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
        response.setContentType("multipart/form-data");  
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
        response.setHeader("Content-Disposition", "attachment;fileName=download."+f.getFileFormat());  
        ServletOutputStream out;  
        //通过文件路径获得File对象(假如此路径中有一个download.pdf文件)  
        File file = new File(path +"uploadfile/"+ f.getUrl());  
  
          
            FileInputStream inputStream = new FileInputStream(file);  
  
            //3.通过response获取ServletOutputStream对象(out)  
            out = response.getOutputStream();  
  
            int b = 0;  
            //创建缓冲区
            byte buffer[] = new byte[1024];
             int len = 0;
             //循环将输入流中的内容读取到缓冲区当中
             while((len=inputStream.read(buffer))>0){
             //输出缓冲区的内容到浏览器，实现文件下载
             out.write(buffer, 0, len);
             }
            inputStream.close();  
            out.close();  
            out.flush();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
		
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	public   String StringFilter(String   str)   throws   PatternSyntaxException   {     
        // 只允许字母和数字       
        // String   regEx  =  "[^a-zA-Z0-9]";                     
           // 清除掉所有特殊字符  
		  String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}‘；：”“’。，、？]";  
		  Pattern   p   =   Pattern.compile(regEx);     
		  Matcher   m   =   p.matcher(str);     
		  return   m.replaceAll("").trim();     
	}

	@RequestMapping("/toMusicVisual")
    public String toMusicVisual(HttpServletRequest request){
		String ip = Tools.getNoHTMLString(StringFilter(getIpAddr(request)));
		logger.info("ip:"+ip+" 访问了音乐可视化 ");
    	return "/file/musicVisual";
    }
	
	public String getIpAddr(HttpServletRequest request) { 
		String ip = request.getHeader("X-Real-IP");
		if (null != ip && !"".equals(ip.trim())
				&& !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (null != ip && !"".equals(ip.trim())
				&& !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		return request.getRemoteAddr();
   } 
	@RequestMapping(value = "/uploadAvatar",method = RequestMethod.POST, produces="application/json;charset=utf-8") 
	@ResponseBody
	public Map<String, Object> uploadHeadPortrait(MultipartFile avatar_file,String avatar_src,String avatar_data, HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		if (!avatar_file.isEmpty()) {
			try{
		        //判断文件的MIMEtype
		        String type = avatar_file.getContentType();
		        if(type == null || !type.toLowerCase().startsWith("image/")){
		        	json = this.setJson(false, "不支持的文件类型，仅支持图片!", null);
		        	return  json;
		        }
				//头像存放文件
				String dir = "avator";
				Map<String, Object> returnMap = UploadFileUtils.Upload(request,avatar_file,avatar_data,dir);
				//返回的布尔型参数的值为true，如果字符串参数不为null，是相等的，忽略大小写字符串“true”。
				if (Boolean.parseBoolean(returnMap.get("flag").toString()) == true) {
					User user =UserSessionUtil.currentUser();
					user.setAvatar(returnMap.get("savaPath").toString());
					userService.updateAvatar(user);
					json = this.setJson(true, "上传成功!", returnMap.get("savaPath").toString());
					System.out.println("存放路径："+returnMap.get("savaPath").toString());
					return json;
				} 
			}catch(Exception e){
				logger.error("ImageUploadController.uploadHeadPortrait", e);
				json = this.setJson(false, "上传失败，出现异常："+e.getMessage(), null);
				return json;
			}
		}
    	json = this.setJson(false, "不支持的文件类型，仅支持图片!", null);
    	return  json;
	}
	protected Map<String, Object> setJson(boolean success, String message, Object entity){
		Map<String,Object> json = new HashMap<String,Object>();
		json.put("success", success);
		json.put("message", message);
		json.put("entity", entity);
		return json;
	}
	
}
