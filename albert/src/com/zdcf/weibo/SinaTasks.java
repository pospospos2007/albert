package com.zdcf.weibo;

import java.util.ArrayList;
import java.util.List;

public class SinaTasks {
	private String baseURL;
	private String accesstoken;
	private List<Task> tasks;

	public SinaTasks() {
		this.baseURL="https://api.weibo.com/2/";
		this.accesstoken ="2.00nMzqcCzSxBND29ae6e2313XYGc5D";
		tasks =new ArrayList<Task>();
		Task task =new Task();
		tasks.add(task);
	}

	public List<Task> getSinaTasks1() {
		List<Task> tasklist = new ArrayList<Task>();
		for (Task ts : tasks) {
			if (ts.getType() == 1) {
				tasklist.add(ts);
			}
		}
		return tasklist;
	}

	public List<Task> getSinaTasks2() {
		List<Task> tasklist = new ArrayList<Task>();
		for (Task ts : tasks) {
			if (ts.getType() == 2) {
				tasklist.add(ts);
			}
		}
		return tasklist;
	}

	public List<Task> getSinaTasks3() {
		List<Task> tasklist = new ArrayList<Task>();
		for (Task ts : tasks) {
			if (ts.getType() == 3) {
				tasklist.add(ts);
			}
		}
		return tasklist;
	}

	public String getAccesstoken() {
		return accesstoken;
	}

	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	@Override
	public String toString() {
		return "SinaTasks [accesstoken=" + accesstoken + ", tasks=" + tasks + "]";
	}
}
