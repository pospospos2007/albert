package com.zdcf.weibo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import com.zdcf.mapper.Plugin;
import com.zdcf.weibo.SinaTaskPublicThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@Configuration
//@ComponentScan(basePackages="com.zdcf")
//@ImportResource("classpath:com/zdcf/config/root-context.xml")
public class Config {
	private static Logger logger = LoggerFactory.getLogger(Config.class.getName());
//	@Resource
//	private List<Plugin> pluginList;
	private Plugin plugin;
	@Resource
	private SinaTasks sinaTasks;
	@Resource
	private Timeline tm;
	
	public Config() {
		this.sinaTasks =new SinaTasks();
		this.tm = new Timeline();
	}
	

	public Plugin getPlugin() {
		return plugin;
	}



	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}



	private List<Runnable> getSinaTaskPublicThreads() {
		List<Runnable> runlist = new ArrayList<Runnable>();
		for (Task ts : sinaTasks.getSinaTasks2()) {
			runlist.add(new SinaTaskPublicThread(ts,tm,plugin));
		}
		return runlist;
	}

	public List<Runnable> getAllThreads() {
		List<Runnable> runlist = new ArrayList<Runnable>();
		runlist.addAll(this.getSinaTaskPublicThreads());
		logger.info(runlist.toString());
		return runlist;
	}

	/**
	 * note: call this method will block the current thread and wait for all threads to stop
	 * 
	 * @throws InterruptedException
	 */
	public void getAllThreadsRun() {
		List<Thread> ts = new ArrayList<Thread>();
		for (Runnable t : getAllThreads()) {
			Thread t1 = new Thread(t);
			ts.add(t1);
			t1.start();
		}
		for (Thread t : ts) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		for (Plugin s : pluginList) {
//			s.close();
//		}
	}

	public SinaTasks getSinaTasks() {
		return sinaTasks;
	}

	public void setSinaTasks(SinaTasks sinaTasks) {
		this.sinaTasks = sinaTasks;
	}

	public static void main(String... strings) throws IOException {
		AnnotationConfigApplicationContext ctx =new AnnotationConfigApplicationContext();
		ctx.register(Config.class);
		ctx.refresh();
		Config config = ctx.getBean(Config.class);
		config.getAllThreadsRun();
		//ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "beans.xml" });
		//context.registerShutdownHook();
		//TaskHandle th = context.getBean(TaskHandle.class);
		//th.getAllThreadsRun();
//		HttpClientPoolUtil.shutDown();
		ctx.close();
	}
}
