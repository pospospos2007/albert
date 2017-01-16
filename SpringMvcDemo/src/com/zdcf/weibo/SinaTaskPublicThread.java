package com.zdcf.weibo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zdcf.mapper.Plugin;
import com.zdcf.model.WeiboStatus;
import com.zdcf.service.Impl.RobotServiceImpl;
import com.zdcf.tool.SpringBeanFactoryUtils;
import com.zdcf.weibo.GetPublicTlAll;
import com.zdcf.weibo.Timeline;
import com.zdcf.weibo.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SinaTaskPublicThread implements Runnable {
	private Task t;
	private List<Plugin> pluginList;
	private GetPublicTlAll getpublictl;
	
	private Plugin plugin;
	
	private static Logger logger = LoggerFactory.getLogger(SinaTaskPublicThread.class.getName());

	public SinaTaskPublicThread(Task t,Timeline tm,Plugin plugin) {
		this.plugin = plugin;
		this.t = t;
//		this.pluginList = pluginList;
		this.getpublictl = new GetPublicTlAll(tm,t);
	}

	public void run() {
		getpublictl.setT(t);
		List<WeiboStatus> st = new ArrayList<WeiboStatus>();
		do {
			try {
				st = getpublictl.returnall();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (st != null && !st.isEmpty()) {
				this.save(st);
			}
			logger.info("save count : " + getpublictl.getCount());
			// sleep
			try {
				logger.info("sleep---------------");
				Thread.sleep(t.getMinute() * 60 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (t.getCount() == -1 ? true : getpublictl.getCount() <= t.getCount());
		logger.info("task finished");
	}

	private void save(List<WeiboStatus> statuslist) {
		for (WeiboStatus st : statuslist) {
			plugin.saveWeibo(t, st);
			plugin.saveUser(t, st.getUser());
		}
	}

	public Task getT() {
		return t;
	}

	public void setT(Task t) {
		this.t = t;
	}

	public List<Plugin> getPluginList() {
		return pluginList;
	}

	public void setPluginList(List<Plugin> pluginList) {
		this.pluginList = pluginList;
	}

	public GetPublicTlAll getGetpublictl() {
		return getpublictl;
	}

	public void setGetpublictl(GetPublicTlAll getpublictl) {
		this.getpublictl = getpublictl;
	}
	
}
