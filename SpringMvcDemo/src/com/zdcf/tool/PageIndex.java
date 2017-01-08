package com.zdcf.tool;

import java.io.Serializable;

public class PageIndex implements Serializable{
	private static final long serialVersionUID = -956277890632601252L;
	private int startIndex;
	private int endIndex;
	
	public PageIndex(int startindex, int endIndex) {
		this.startIndex = startindex;
		this.endIndex = endIndex;
	}
	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public static PageIndex getPageIndex(int viewpagecount, int currentPage, int totalpage){
		int startpage = currentPage-(viewpagecount%2==0? viewpagecount/2-1 : viewpagecount/2);
		int endpage = currentPage+viewpagecount/2;
			if(startpage<1){
				startpage = 1;
				if(totalpage>=viewpagecount) endpage = viewpagecount;
				else endpage = totalpage;
			}
			if(endpage>totalpage){
				endpage = totalpage;
				if((endpage-viewpagecount)>0) startpage = endpage-viewpagecount+1;
				else startpage = 1;
			}
			return new PageIndex(startpage, endpage);		
	}
}
