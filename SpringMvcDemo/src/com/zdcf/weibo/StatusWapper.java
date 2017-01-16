package com.zdcf.weibo;

import java.util.List;

import com.zdcf.model.WeiboStatus;

public class StatusWapper {

	private List<WeiboStatus> statuses;

	private long previousCursor;

	private long nextCursor;

	private long totalNumber;

	private String hasvisible;

	public StatusWapper(List<WeiboStatus> statuses, long previousCursor, long nextCursor, long totalNumber, String hasvisible) {
		this.statuses = statuses;
		this.previousCursor = previousCursor;
		this.nextCursor = nextCursor;
		this.totalNumber = totalNumber;
		this.hasvisible = hasvisible;
	}

	public List<WeiboStatus> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<WeiboStatus> statuses) {
		this.statuses = statuses;
	}

	public long getPreviousCursor() {
		return previousCursor;
	}

	public void setPreviousCursor(long previousCursor) {
		this.previousCursor = previousCursor;
	}

	public long getNextCursor() {
		return nextCursor;
	}

	public void setNextCursor(long nextCursor) {
		this.nextCursor = nextCursor;
	}

	public long getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getHasvisible() {
		return hasvisible;
	}

	public void setHasvisible(String hasvisible) {
		this.hasvisible = hasvisible;
	}

}
