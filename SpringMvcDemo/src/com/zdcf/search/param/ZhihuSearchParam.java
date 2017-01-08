package com.zdcf.search.param;

public class ZhihuSearchParam extends SearchParam {

	
	private String title;
	
	// 把所有参数拼接成参数串
	public String getParamString() {

		StringBuffer sb = new StringBuffer();

		if(null != this.getTitle()){
			sb.append("&title=").append(this.getTitle());
		}
		
		return sb.toString();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
