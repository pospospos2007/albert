package com.zdcf.weibo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.methods.HttpGet;
import com.zdcf.weibo.HttpClientPoolUtil;

/**
 * 用HttpClient下载图片
 * 
 */
public class DownloadPic {
	private String taskname;
	private String foldername;
	private String filename;

	public DownloadPic(String taskname) {
		this.taskname = taskname;
		this.makedatedir();
	}

	public DownloadPic(String taskname, String filename) {
		this.taskname = taskname;
		this.filename = filename + ".jpg";
		this.makedatedir();
	}

	private void makedatedir() {
		File foldfilei = new File("download");
		if (!foldfilei.exists()) {
			foldfilei.mkdir();
		}
		File foldfilea = new File("download" + File.separator + taskname);
		if (!foldfilea.exists()) {
			foldfilea.mkdir();
		}
		String foldernameb = "download" + File.separator + taskname;
		String foldernamec = foldernameb + File.separator + "small";
		File foldfilec = new File(foldernamec);
		if (!foldfilec.exists()) {
			foldfilec.mkdir();
		}
		File foldfiled = new File(foldernameb + File.separator + "middle");
		if (!foldfiled.exists()) {
			foldfiled.mkdir();
		}
		File foldfilee = new File(foldernameb + File.separator + "large");
		if (!foldfilee.exists()) {
			foldfilee.mkdir();
		}
		foldername = foldernameb;
	}

	public void write(String urlsmall, String urlmiddle, String urllarge) throws IOException {
		FileOutputStream outputsmall = null;
		FileOutputStream outputmiddle = null;
		FileOutputStream outputlarge = null;
		if (urlsmall != null && urlsmall.length() > 0) {
			HttpGet getsmall = new HttpGet(urlsmall);
			InputStream is = HttpClientPoolUtil.execute1(getsmall).getContent();
			File smallfile = new File(foldername + File.separator + "small" + File.separator + filename);
			outputsmall = new FileOutputStream(smallfile);
			int inByte;
			while ((inByte = is.read()) != -1)
				outputsmall.write(inByte);
			is.close();
		}
		if (urlmiddle != null && urlmiddle.length() > 0) {
			HttpGet getmiddle = new HttpGet(urlmiddle);
			InputStream is = HttpClientPoolUtil.execute1(getmiddle).getContent();
			File middlefile = new File(foldername + File.separator + "middle" + File.separator + filename);
			outputmiddle = new FileOutputStream(middlefile);
			int inByte;
			while ((inByte = is.read()) != -1)
				outputmiddle.write(inByte);
			is.close();
		}
		if (urllarge != null && urllarge.length() > 0) {
			HttpGet getlarge = new HttpGet(urllarge);
			InputStream is = HttpClientPoolUtil.execute1(getlarge).getContent();
			File largefile = new File(foldername + File.separator + "large" + File.separator + filename);
			outputlarge = new FileOutputStream(largefile);
			int inByte;
			while ((inByte = is.read()) != -1)
				outputlarge.write(inByte);
			is.close();
		}
		if (outputsmall != null) {
			outputsmall.close();
		}
		if (outputmiddle != null) {
			outputmiddle.close();
		}
		if (outputlarge != null) {
			outputlarge.close();
		}
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename + ".jpg";
	}

	public static void main(String[] args) throws IOException {
		String taskname = "taskname";
		String filename = "statusid";
		DownloadPic dt = new DownloadPic(taskname, filename);
		String urlsmall = "http://images.sohu.com/uiue/sohu_logo/beijing2008/2008sohu.gif";
		dt.write(urlsmall, null, null);
	}
}
