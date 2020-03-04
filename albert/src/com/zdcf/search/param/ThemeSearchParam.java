package com.zdcf.search.param;

public class ThemeSearchParam extends SearchParam {

	
	private String theme;
	
	// 把所有参数拼接成参数串
	public String getParamString() {

		StringBuffer sb = new StringBuffer();

		if(null != this.getTheme()){
			sb.append("&theme=").append(this.getTheme());
		}
		
		return sb.toString();
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

}
