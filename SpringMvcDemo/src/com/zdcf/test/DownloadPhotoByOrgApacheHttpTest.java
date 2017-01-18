package com.zdcf.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.zdcf.tool.ProxyUtil;

public class DownloadPhotoByOrgApacheHttpTest
{
	 public static void main(String args[])  {
		String remoteFileName="http://pbs.twimg.com/media/C2bm-eXXcAAd1sc.jpg";
		String localFileName="D:/test1.jpg";
		
        DefaultHttpClient httpClient = (DefaultHttpClient) ProxyUtil.getHttpClient();
        OutputStream out = null;
        InputStream in = null;
        
        try {
            HttpGet httpGet = new HttpGet(remoteFileName);

            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            in = entity.getContent();

            long length = entity.getContentLength();
            if (length <= 0) {
                System.out.println("下载文件不存在！");
                return;
            }

            File file = new File(localFileName);
            if(!file.exists()){
                file.createNewFile();
            }
            
            out = new FileOutputStream(file);  
            byte[] buffer = new byte[4096];
            int readLength = 0;
            while ((readLength=in.read(buffer)) > 0) {
                byte[] bytes = new byte[readLength];
                System.arraycopy(buffer, 0, bytes, 0, readLength);
                out.write(bytes);
            }
            
            out.flush();
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try {
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
