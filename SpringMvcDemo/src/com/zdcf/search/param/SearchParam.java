package com.zdcf.search.param;

import com.zdcf.tool.Pagination;

public abstract class SearchParam extends Pagination {
	private Integer id;
	private Integer sort;//排序
	
	// 把所有参数拼接成参数串
	public abstract String getParamString();

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
