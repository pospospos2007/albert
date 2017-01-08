package com.zdcf.tool;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pagination {
	
	private static final int DEFAULT_PER_PAGE = 15;
	
	public static final Pagination ALL = new Pagination(0, Integer.MAX_VALUE);
	public static final Pagination UNIQUE = new Pagination(0, 1);
	
	@JsonProperty
	protected int i = 1; //当前页
	protected int s = DEFAULT_PER_PAGE;//每页显示数量
	protected int firstResult = 0;
	
	private int firstPage;
	private int lastPage;
	@JsonProperty
	private int pageCount;
	private int previous;
	private int next;
	private boolean hasPervious;
	private boolean hasNext;
	@JsonProperty
	private int totalNum = 0;
	
	public Pagination(){
		this(1, DEFAULT_PER_PAGE);
	}
	
	public Pagination(int i, int s){
		this.i = i;
		this.s = s;
		this.firstResult = (i - 1) * s;
	}
	
	public int getFirstResult() {
		return firstResult;
	}
	
	public int getS() {
		return s;
	}
	
	public void setS(int s) {
		this.s = s ;
		if(s > 100){
			s = DEFAULT_PER_PAGE;
		}
		firstResult = (i - 1) * s;
	}
	
	public void setI(int i) {
		if(i <= 0){
			i = 1;
		}
		this.i = i;
		firstResult = (i - 1) * s;
	}
	
	public int getI() {
		return i;
	}

	public int getTotalNum() {
		return totalNum;
	}
	
	public String getParamString(){
		StringBuffer sb = new StringBuffer();
		sb.append("s=").append(getS());
		sb.append("&i=").append(getI());
		
		return sb.toString();
	}
	
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
		hasPervious = false;
		hasNext = false;
		
		pageCount = totalNum % s != 0? 
				           totalNum / s + 1 : totalNum / s;
		
		i = hasPageNumber(i)? i : 1;
		
		firstPage = Integer.valueOf(1);
		lastPage = pageCount;
		setPrevious();
		setNext();
	}
	

	protected void setPrevious() {
		if (i > 1){
			hasPervious =true;
			previous = i - 1;
		}
	}

	protected void setNext() {
		if (i < pageCount){
			hasNext = true;
			next = i + 1;
		}
	}

	public boolean hasPageNumber(int pageNumber) {
		return pageNumber >= 1 && pageNumber <= pageCount;
	}

	public Integer getFirstPage() {
		return firstPage;
	}

	public Integer getLastPage() {
		return lastPage;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public Integer getPrevious() {
		return previous;
	}

	public Integer getNext() {
		return next;
	}

	public Boolean isHasPervious() {
		return hasPervious;
	}

	public Boolean isHasNext() {
		return hasNext;
	}
	
	
	//public  limit #{page.firstResult,jdbcType=INTEGER}, #{page.s,jdbcType=INTEGER}
	public String getLimit(){
		return " limit "+this.getFirstResult() +","+this.getS();
	}
	
}
