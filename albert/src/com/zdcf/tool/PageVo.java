package com.zdcf.tool;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * 
 * 
 */
public class PageVo<E> implements Serializable {

	public static <T> PageVo<T> create(int currentPage) {
		PageVo<T> page = new PageVo<T>();
		page.setCurrentPage(currentPage < 1 ? 1 : currentPage);

		return page;
	}

	public static <T> PageVo<T> create() {
		PageVo<T> page = new PageVo<T>();
		page.setCurrentPage(1);

		return page;
	}

	private static final long serialVersionUID = -4861421206255705029L;

	private static int DEFAULT_PAGE_SIZE = 20; // 默认分页记录数
	private int recordCount; // 总记录数
	private int pageSize; // 页面记录条数
	private int pageCount; // 总页数
	private int currentPage; // 当前页码
	private List<E> voList; // 数据记录List
	private PageIndex pageIndex;
	private int pagecode = 5;
	private String orderby; // 排序值
	private int ascORdesc; // 升序降序 0 升序 1 降序

	public PageVo() {
		recordCount = -1;
		pageCount = -1;

		pageSize = DEFAULT_PAGE_SIZE;
		currentPage = -1;
	}

	public List<E> getVoList() {
		return voList;
	}

	public void setVoList(List<E> voList) {
		this.voList = voList;
	}

	/**
	 * @return 当前页码
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 设置所需的页码
	 * 
	 * @param currentPage
	 *            页码。第一页就是1，第二页就是2
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
		this.pageIndex = PageIndex.getPageIndex(pagecode, currentPage,
				pageCount);
	}

	public int getPageSize() {
		return pageSize < 1 ? DEFAULT_PAGE_SIZE : pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
		rePageCount();
	}

	public void rePageCount() {
		if (pageSize * (currentPage - 1) > recordCount)
			setCurrentPage((int) Math.ceil((double) recordCount
					/ (double) pageSize));
		if (pageSize == 0)
			setPageSize(DEFAULT_PAGE_SIZE);
		setPageCount((int) Math.ceil((double) recordCount / (double) pageSize));
	}

	public PageIndex getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(PageIndex pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPagecode() {
		return pagecode;
	}

	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public int getAscORdesc() {
		return ascORdesc;
	}

	public void setAscORdesc(int ascORdesc) {
		this.ascORdesc = ascORdesc;
	}
}
