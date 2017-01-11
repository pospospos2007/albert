package com.zdcf.tool;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @description：文件上传通用处理工具类
 * @author 懂得
 * @version 创建时间：2016年8月19日  上午11:19:57
 */
public class UploadFileUtils {

	/**
	 * 上传文件存放服务器
	 * @param request
	 * @param dir
	 * @return
	 */
	public static String getServerSaveDir(HttpServletRequest request, String dir){
		StringBuffer uploadPath = new StringBuffer(request.getSession().getServletContext().getRealPath("uploadimage"));
		uploadPath.append(File.separator);
		uploadPath.append(dir);
		uploadPath.append(File.separator);
		uploadPath.append(DateUtil.dateToStr(new Date(), "yyyyMMdd"));
		File file = new File(uploadPath.toString());
		if ( !file.exists() ) {
			file.mkdirs();
		}
		return file.getPath();
	}
	
	/**
	 * 文件重命名
	 * @param file
	 * @return
	 */
	public static String rename( MultipartFile file ) {
		//获取原始文件名  
		String fileName = file.getOriginalFilename();
		//新文件名称，不设置时默认为原文件名
        String newFileName = new Date().getTime()+(new Random().nextInt(9999-1000+1)+1000)+fileName.substring(fileName.lastIndexOf('.'));
		return newFileName;
	} 
	
	/**
	 * 文件保存路径
	 * @param request 
	 * @param serverSaveDir
	 * @param newFileName
	 * @return
	 */
	public static String getSavaDir(HttpServletRequest request,String serverSaveDir, String newFileName){
		StringBuffer savaPath = new StringBuffer();
		//文件存放路径
		savaPath.append(serverSaveDir);
		savaPath.append(File.separator);
		//文件名称
		savaPath.append(newFileName);
		
		//将绝对路径"\"替换成"/"
		String savaFilePath = savaPath.toString().replaceAll("\\\\", "/");
		//查询"/tizi"最后一个字母的位置
		int index =savaFilePath.lastIndexOf(request.getSession().getServletContext().getContextPath())+request.getSession().getServletContext().getContextPath().length();
		//文件保存路径
		String filePath = savaFilePath.substring(index+1,savaFilePath.length());
		return filePath;
	}
	
	/**
	 * 
	 * @param request 网页请求
	 * @param avatar_file  avatar_file(源文件)
	 * @param avatar_data avatar_data(裁剪参数JSON
	 * @param dir 地址名称
	 * @return 
	 */
    public static Map<String, Object> Upload(HttpServletRequest request, MultipartFile avatar_file, String avatar_data, String dir){
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	//获取服务器的实际路径
    	String serverSaveDir = getServerSaveDir(request, dir);
    	//生成文件名称
    	String newFileName = rename(avatar_file);
    	
        //先把用户上传到原图保存到服务器上  
        File targetFile = new File(serverSaveDir, newFileName);
        boolean flag = false;
        try{
        	//创建JSONObject对象
            JSONObject joData = (JSONObject) JSONObject.parse(avatar_data);
            // 用户经过剪辑后的图片的大小  
            float x = joData.getFloatValue("x");
            float y = joData.getFloatValue("y");
            float w =  joData.getFloatValue("width");
            float h =  joData.getFloatValue("height");
            float r = joData.getFloatValue("rotate");
        	
            //将文件剪辑并上传到服务器上和本地文件中
            if(!targetFile.exists()){  
                targetFile.mkdirs(); 
                //获取文件流，可以进行处理
                InputStream is = avatar_file.getInputStream();
                //旋转后剪裁图片
                ImageUtils.cutAndRotateImage(is, targetFile, (int)x,(int)y,(int)w,(int)h,(int)r); 
                //关闭该流并释放与该流关联的所有系统资源。
                is.close();
                flag = true;
            }  
        }catch(Exception e){
            e.printStackTrace();  
        }
        returnMap.put("savaPath", getSavaDir(request, serverSaveDir, newFileName));
        returnMap.put("flag", flag);
		return returnMap;
    }
}
