package com.zdcf.weibo;

import java.util.ArrayList;
import java.util.List;

public class Task {
	private String name ;
	private int type ;
	private List<String> usernames;
	private List<String> keywords;
	private int minute;
	private int count; // how many weibos will be downloaded,-1 means no limit
	private boolean downloadPicture;

	public Task() {
		this.name="public";
		this.type=2;
		this.usernames =new ArrayList<String>();
		this.usernames.add("public");
		this.keywords =new ArrayList<String>();
		this.keywords.add(".*");
		this.minute=1;
		this.count=100;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<String> getUsernames() {
		return usernames;
	}

	public void setUsernames(List<String> usernames) {
		this.usernames = usernames;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public boolean isDownloadPicture() {
		return downloadPicture;
	}

	public void setDownloadPicture(boolean downloadPicture) {
		this.downloadPicture = downloadPicture;
	}

	@Override
	public String toString() {
		return "Task [name=" + name + ", type=" + type + ", usernames=" + usernames + ", keywords=" + keywords + ", minute=" + minute + ", count=" + count + "]";
	}
}
