package com.zdcf.service.Impl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.SneakyThrows;
import net.sf.json.JSONObject;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.net.ssl.SSLException;


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
		
		
//		File file = new File("http://albert6.com:9999/uploadfile/1417f4fe7feb4ea098ac2cc182cd0fcb.jpg");
		byte[] buff = getTorrent(faceDTO.getUrl());
//				getBytesFromFile(file);
		String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "7CpGyFJALa6Nhcrek_A2eKiZ_q4yUY5o");
        map.put("api_secret", "fBLwEEhBoRbTMt0LeWP7H8C0TOblCc8Q");
		map.put("return_landmark", "1");
        map.put("return_attributes", "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
        byteMap.put("image_file", buff);
        try{
            byte[] bacd = post(url, map, byteMap);
            String str = new String(bacd);
            JSONObject result = JSONObject.fromObject(str); 
            Integer size = result.getJSONArray("faces").size();
            System.out.println(str);
            
			if(null!=size&&size>0){
			for(int i=0;i<size;i++){
				FaceAttributeDTO attr = new FaceAttributeDTO();
				JSONObject attribute = result.getJSONArray("faces").getJSONObject(i).getJSONObject("attributes");
				attr.setAge(Integer.parseInt(attribute.getJSONObject("age").get("value").toString()));
				attr.setGender(attribute.getJSONObject("gender").get("value").toString());
				attr.setGlass(attribute.getJSONObject("glass").get("value").toString());
//				attr.setRace(attribute.getJSONObject("race").get("value").toString());
				attr.setFace_id(faceDTO.getId());
				fileMapper.addFaceAttribute(attr);
			}
		}
            
        }catch (Exception e) {
        	e.printStackTrace();
		}
        
		
//		HttpRequests httpRequests = new HttpRequests("dd0094b405bf9612a58f181ee3acde3a", "fcmx4kqd0fZzOzQKOFiYcldgE92ECNLx", true, true);
//		2ZAirmxOJJraICXGcME0DKm_Geueb9
//		7n_UojpEYoV2S29_aquYettTXcBq9Qnm
//		JSONObject result = null;
//			
//		try {
//			
//			System.out.println(Charset.forName("UTF-8").name());
//		
//			PostParameters params = new PostParameters();
//			params.setAttribute("glass,pose,gender,age,race,smiling");
//			params.setUrl(faceDTO.getUrl());
//			result = httpRequests.detectionDetect(params);
//			System.out.println(result);
//			
//			Integer size = result.getJSONArray("face").length();
//			
//			if(null!=size&&size>0){
//				for(int i=0;i<size;i++){
//					FaceAttributeDTO attr = new FaceAttributeDTO();
//					JSONObject attribute = result.getJSONArray("face").getJSONObject(i).getJSONObject("attribute");
//					attr.setAge(Integer.parseInt(attribute.getJSONObject("age").get("value").toString()));
//					attr.setGender(attribute.getJSONObject("gender").get("value").toString());
//					attr.setGlass(attribute.getJSONObject("glass").get("value").toString());
//					attr.setRace(attribute.getJSONObject("race").get("value").toString());
//					attr.setFace_id(faceDTO.getId());
//					fileMapper.addFaceAttribute(attr);
//				}
//			}
//			
//			
//		} catch(FaceppParseException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//		} 
	
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
	
	
	private final static int CONNECT_TIME_OUT = 30000;
    private final static int READ_OUT_TIME = 50000;
    private static String boundaryString = getBoundary();
    protected static byte[] post(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap) throws Exception {
        HttpURLConnection conne;
        URL url1 = new URL(url);
        conne = (HttpURLConnection) url1.openConnection();
        conne.setDoOutput(true);
        conne.setUseCaches(false);
        conne.setRequestMethod("POST");
        conne.setConnectTimeout(CONNECT_TIME_OUT);
        conne.setReadTimeout(READ_OUT_TIME);
        conne.setRequestProperty("accept", "*/*");
        conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
        conne.setRequestProperty("connection", "Keep-Alive");
        conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
        DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
        Iterator iter = map.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String, String> entry = (Map.Entry) iter.next();
            String key = entry.getKey();
            String value = entry.getValue();
            obos.writeBytes("--" + boundaryString + "\r\n");
            obos.writeBytes("Content-Disposition: form-data; name=\"" + key
                    + "\"\r\n");
            obos.writeBytes("\r\n");
            obos.writeBytes(value + "\r\n");
        }
        if(fileMap != null && fileMap.size() > 0){
            Iterator fileIter = fileMap.entrySet().iterator();
            while(fileIter.hasNext()){
                Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
                obos.writeBytes("--" + boundaryString + "\r\n");
                obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
                        + "\"; filename=\"" + encode(" ") + "\"\r\n");
                obos.writeBytes("\r\n");
                obos.write(fileEntry.getValue());
                obos.writeBytes("\r\n");
            }
        }
        obos.writeBytes("--" + boundaryString + "--" + "\r\n");
        obos.writeBytes("\r\n");
        obos.flush();
        obos.close();
        InputStream ins = null;
        int code = conne.getResponseCode();
        try{
            if(code == 200){
                ins = conne.getInputStream();
            }else{
                ins = conne.getErrorStream();
            }
        }catch (SSLException e){
            e.printStackTrace();
            return new byte[0];
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        int len;
        while((len = ins.read(buff)) != -1){
            baos.write(buff, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        ins.close();
        return bytes;
    }
    private static String getBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 32; ++i) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
        }
        return sb.toString();
    }
    private static String encode(String value) throws Exception{
        return URLEncoder.encode(value, "UTF-8");
    }
    
//    public static byte[] getBytesFromFile(File f) {
//        if (f == null) {
//            return null;
//        }
//        try {
//            FileInputStream stream = new FileInputStream(f);
//            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
//            byte[] b = new byte[1000];
//            int n;
//            while ((n = stream.read(b)) != -1)
//                out.write(b, 0, n);
//            stream.close();
//            out.close();
//            return out.toByteArray();
//        } catch (IOException e) {
//        }
//        return null;
//    }

    public byte[] getTorrent(String href) {
		Response response=null;
		try {
			response = getConnect(href).ignoreContentType(true).execute();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return response.bodyAsBytes();
	}
    
    private Connection getConnect(String url) {
		return Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36").timeout(100000);
	}


}
