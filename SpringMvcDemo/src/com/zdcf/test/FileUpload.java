package com.zdcf.test;

import java.io.File;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.aliyun.oss.OSSClient;

public class FileUpload {
	
	public static void main(String[] args){


        try {
            uploadOSS();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public static void uploadOSS() throws ClientProtocolException, IOException{
//		http://barlolo.oss-cn-hongkong.aliyuncs.com/IMG_0008%5B1%5D.JPG
//        HttpPost httpPost = new HttpPost("http://127.0.0.1:7001/test/autonavi/shanghai/api/attachment/oss/");
//        HttpPost httpPost = new HttpPost("http://barlolo.oss-cn-hongkong.aliyuncs.com/");
//        
//        httpPost.addHeader("key","12");
//        httpPost.addHeader("user","123");
//        httpPost.addHeader("method","123");
//        httpPost.addHeader("filename",new String("黄山［哈哈］.jpg".getBytes("UTF-8"),"ISO-8859-1"));
//        httpPost.addHeader("type","01");
//
//        FileEntity  reqEntity = new FileEntity(new File("C:/1.jpg"));  
//
//        httpPost.setEntity(reqEntity);
//
//        HttpClient client = new DefaultHttpClient();
//        HttpResponse response = client.execute(httpPost);
//
//        System.out.println(response);
        
     // endpoint以杭州为例，其它region请按实际情况填写
        String endpoint = "http://barlolo.img-cn-hongkong.aliyuncs.com/";
//        String key = Constant.bashFilePath + timeDate + timeStamp +"/"+fileName;
        // accessKey请登录https://ak-console.aliyun.com/#/查看
        String accessKeyId = "LTAIDYqPtix8XiOG";
        String accessKeySecret = "Oz8swP6Xgx4b2Jgaqs11DV30XumbdG";
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件
        ossClient.putObject("<yourBucketName>", "1.jpg", new File("C:/1.jpg"));
        // 关闭client
        ossClient.shutdown();
    }
}
