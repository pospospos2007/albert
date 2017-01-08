package com.zdcf.search.param;

public class MovieSearchParam extends SearchParam {

	
	private String name;
	
	// 把所有参数拼接成参数串
	public String getParamString() {

		StringBuffer sb = new StringBuffer();

		if(null != this.getName()){
			sb.append("&name=").append(this.getName());
		}
		
		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
