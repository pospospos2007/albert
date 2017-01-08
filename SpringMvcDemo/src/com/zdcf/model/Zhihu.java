package com.zdcf.model;

import java.util.Date;

import com.zdcf.base.Constants;

public class Zhihu extends Cacheable{

	private String title;
	
	private String content;
	
	private String images;
	
	private String js;
	
	private String css;
	
	public String getJs() {
		return js;
	}

	public void setJs(String js) {
		this.js = js;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	@Override
	public String getCacheKey() {
		return Constants.Cache.Zhihu+getId();
	}
	
	

}
