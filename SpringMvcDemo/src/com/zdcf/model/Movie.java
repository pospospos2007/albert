package com.zdcf.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import com.zdcf.base.Constants;

@Data
public class Movie extends Cacheable{

	private String name;//资源名称
	
	private byte[] torrent;//torrent文件
	
	private String desc;//描述信息
	
	private String metadata;//元数据
	
	private Double rate;//评分
	
	private Integer subject;//主题id
	
	private String url;//明细地址
	
	private String durl;//备用下载地址
	
	private String img;
	
	private List<String> durls = new ArrayList<>();
	private List<Byte[]> torrents = new ArrayList<>();

	@Override
	public String getCacheKey() {
		return Constants.Cache.Movie+getId();
	}
	
}
