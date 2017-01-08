package com.zdcf.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
public class DownloadPhotoTest
{
    public static void main(String args[]) throws IOException,FileNotFoundException
    {
	HttpClient client = new HttpClient();
	GetMethod get = new GetMethod(
	"http://img.hdwan.net/2015/05/p2235597645.jpg");

	File storeFile = new File("D:/test1.jpg");
	FileOutputStream output = null;
	try
	{
	    client.executeMethod(get);
	    output = new FileOutputStream(storeFile);
	    output.write(get.getResponseBody());
	    output.close();
	}
	catch (HttpException e)
	{
	    e.printStackTrace();
	}
	    }
	}
