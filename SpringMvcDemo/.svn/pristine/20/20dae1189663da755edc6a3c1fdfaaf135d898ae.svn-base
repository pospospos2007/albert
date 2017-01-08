package com.zdcf.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import com.zdcf.base.Constants;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getTorrent() {
		return torrent;
	}

	public void setTorrent(byte[] torrent) {
		this.torrent = torrent;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Integer getSubject() {
		return subject;
	}

	public void setSubject(Integer subject) {
		this.subject = subject;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDurl() {
		return durl;
	}

	public void setDurl(String durl) {
		this.durl = durl;
	}

	public void addDurl(String durl){
		this.durls.add(durl);
	}

	public void addTorrent(byte[] torrent){
		this.torrents.add(ArrayUtils.toObject(torrent));
	}
	
	public List<String> getDurls(){
		return durls;
	}
	
	public List<Byte[]> getTorrents(){
		return torrents;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String getCacheKey() {
		return Constants.Cache.Movie+getId();
	}
	
}
