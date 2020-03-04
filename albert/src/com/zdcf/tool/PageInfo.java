package com.zdcf.tool;


import java.io.Serializable;
import java.util.List;

public class PageInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
    /**
	 * 分页处理
	 */
	protected int totalRecord; //总记录数
	protected int curPageNo; //当前页码
	protected int totalPage; //总页码
	protected int pageSize; //每页记录数
	protected List<?> dataList;//查询记录
	
	public PageInfo(int curPageNo, int pageSize) {
		this.curPageNo = curPageNo;
		this.pageSize = pageSize;
	}
	
	public PageInfo(){
		
	}
	
	public List<?> getDataList() {
		return dataList;
	}
	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getCurPageNo() {
		return curPageNo;
	}
	public void setCurPageNo(int curPageNo) {
		this.curPageNo = curPageNo;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getStart(){
		return pageSize*(curPageNo-1);
	}

	public PageInfo next(){
		this.curPageNo++;
		return this;
	}

}

