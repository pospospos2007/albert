package com.zdcf.scheduled;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import com.zdcf.dto.Message;

/**
 * 服务器启动时执行的Servlet
 * @author liwenqiang
 *
 */
public class Global extends HttpServlet implements ServletContextListener {

	private static final long serialVersionUID = -8680344467984908901L;
	
	private Timer clearTimer =null;		//清空聊天记录用Timer
	
	/**
	 * 服务器停止时执行该事件
	 */
    public void contextDestroyed(ServletContextEvent sce) {
    	clearTimer.cancel();
    }

    /**
     * 服务器启动时进行初始化
     */
    public void contextInitialized(ServletContextEvent sce) {
    	//获取XML配置文件中是否使用线程安全的容器保存消息记录
//    	boolean isSynch = sce.getServletContext().getInitParameter("isSynch").equals("1")?true:false;		//获取是否线程同步的设置
    	
    	//在Application中创建出消息集合
//    	Map<String, ConcurrentLinkedQueue<Message>> msglist = null;
//
//		if(isSynch){
//			msglist = new Hashtable<String, ConcurrentLinkedQueue<Message>>();
//		}else {
//			msglist = new HashMap<String, ConcurrentLinkedQueue<Message>>();
//		}
//		sce.getServletContext().setAttribute("totalmsglist", msglist);
		
		//在Application中创建出在线用户集合
//		List<String> userlist = null;
//		if(isSynch){
//			userlist = new Vector<String>();
//		}else {
//			userlist = new ArrayList<String>();
//		}
//		sce.getServletContext().setAttribute("userlist", userlist);
		
		//获取XML配置文件中每隔多久执行任务
		int task = Integer.parseInt(sce.getServletContext().getInitParameter("refreshTimeInterval"));
		if(task > 0){
			clearTimer = new Timer();
			clearTimer.schedule(ScheduleJob.getInstance(), new Date(), task);
		}
    }
    
    
    static class ScheduleJob extends TimerTask  {
    	private static ScheduleJob obj = null;
    	public static TimerTask getInstance(){
    		if(obj == null){
    			obj = new ScheduleJob();
    		}
    		return obj;
    	}
    	
		@Override
		public void run() {
			System.out.println("test1111111111");
		}
    	
    }
}
