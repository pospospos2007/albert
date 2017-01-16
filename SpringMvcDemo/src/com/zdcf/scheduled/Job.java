package com.zdcf.scheduled;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zdcf.dto.ZhihuDTO;
import com.zdcf.mapper.Plugin;
import com.zdcf.model.Zhihu;
import com.zdcf.service.MessageService;
import com.zdcf.service.MovieService;
import com.zdcf.service.RobotService;
import com.zdcf.tool.PageInfo;
import com.zdcf.weibo.Config;

import net.sf.json.JSONObject;


@Component("Job")
@Transactional
public class Job {
	
	private static final Log log = LogFactory
			.getLog(Job.class);
	
	@Autowired
	private RobotService robotService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private Plugin plugin;
	

	
//	@Scheduled(cron = "1 0/1 * * * ? ")
	@Scheduled(cron = "0 0 8,20 * * ? ")
	public void execute() {
		
		JSONObject jsonObject = null;
		
		String result = robotService.getZhiHuAirticleList();
		
		jsonObject = JSONObject.fromObject(result);
		
		List<Map<String,Object>>  stories = (List<Map<String, Object>>) jsonObject.get("stories");
		
		for(int i=0;i<stories.size();i++){
			
			Zhihu  zhihuAirticle = new Zhihu();
			
			zhihuAirticle.setTitle(stories.get(i).get("title").toString().replaceAll("\\\\", "/"));
			
			log.info("插入："+zhihuAirticle.getTitle());
			
			int id = Integer.parseInt(stories.get(i).get("id").toString());
			
			zhihuAirticle.setId(id);
			
			ZhihuDTO zhihu = messageService.getZhihuDetailById(id);
			
			if(null!=zhihu){
				continue;
			}
			
			JSONObject detailObject = null;
			
			detailObject =  JSONObject.fromObject(robotService.getZhiHuAirticleDetail(stories.get(i).get("id").toString()));
			
			String content = detailObject.get("body").toString().replaceAll("\\\\", "/");

			zhihuAirticle.setCss(detailObject.get("css").toString().replaceAll("\\\\", "/").replace("\"", "").replace("[", "").replace("]", ""));
			
			zhihuAirticle.setJs(detailObject.get("js").toString().replaceAll("\\\\", "/").replace("\"", "").replace("[", "").replace("]", ""));
			
			zhihuAirticle.setImages(detailObject.get("image").toString().replaceAll("\\\\", "/"));
			
			zhihuAirticle.setContent(content);
			
			
			try {
				messageService.addZhihuAirticle(zhihuAirticle);
			} catch (Exception e) {
				log.error("无法插入此数据");
			}
			
			
		}
		
	}
	
	
	@Scheduled(cron = "1 0/1 * * * ? ")
//	@Test
	public void addWeibo(){
//		AnnotationConfigApplicationContext ctx =new AnnotationConfigApplicationContext();
//		ctx.register(Config.class);
//		ctx.refresh();
//		Config config = ctx.getBean(Config.class);
		
		Config config =new Config();
		config.setPlugin(plugin);
		config.getAllThreadsRun();
//		HttpClientPoolUtil.shutDown();
//		ctx.close();
	}
	
	
//	@Scheduled(cron = "1 37 0/1 * * ? ")
//	public void addMovie(){
//		
//		for(int i=1;i<192;i++){
//			System.out.println("页数："+i);
//			PageInfo page = movieService.buildPage(i);//max 192
//			movieService.getPage(page);
//		}
//	}
		
//	@Scheduled(cron = "1 22 0/1 * * ? ")
	@Scheduled(cron = "0 0 0 * * ? ")
	public void addNewMovie(){
		
		System.out.println("获取新电影：");
		PageInfo page = movieService.buildPage(1);//max 192
		movieService.getPage(page);
		
	}
	
	
//	@Scheduled(cron = "1 46 0/1 * * ? ")
//	public void saveAll(){
//		messageService.storeAllZhihu();
//	}

//	@Scheduled(cron = "1 17 0/1 * * ? ")
//	public void execute() throws ParseException {
//		
//		for(int m=0;m<1;m--){
//		DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
//		Date dateIndex = dateFormat2.parse("20151208");
//		--i;
//		Date day = DateUtil.addDays(dateIndex, -1+i);
//		String dateString = DateUtil.dateToStr(day, DateUtil.TIMEDATE);
//		System.out.println("日报："+dateString+":");
//		
//		JSONObject jsonObject = null;
//		
//		String result = robotService.getZhiHuAirticleListBefore(dateString);
//		
//		jsonObject = JSONObject.fromObject(result);
//		
//		List<Map<String,Object>>  stories = (List<Map<String, Object>>) jsonObject.get("stories");
//		
//		for(int i=0;i<stories.size();i++){
//			
//			try {
//				
//			Zhihu  zhihuAirticle = new Zhihu();
//			
//			zhihuAirticle.setTitle(stories.get(i).get("title").toString().replaceAll("\\\\", "/"));
//			
//			int id = Integer.parseInt(stories.get(i).get("id").toString());
//			
//			zhihuAirticle.setId(id);
//			
//			ZhihuDTO zhihu = messageService.getZhihuDetailById(id);
//			
//			if(null!=zhihu){
//				continue;
//			}
//			
//			JSONObject detailObject = null;
//			
//			detailObject =  JSONObject.fromObject(robotService.getZhiHuAirticleDetail(stories.get(i).get("id").toString()));
//			
//			String content = detailObject.get("body").toString().replaceAll("\\\\", "/");
//
//			zhihuAirticle.setCss(detailObject.get("css").toString().replaceAll("\\\\", "/").replace("\"", "").replace("[", "").replace("]", ""));
//			
//			zhihuAirticle.setJs(detailObject.get("js").toString().replaceAll("\\\\", "/").replace("\"", "").replace("[", "").replace("]", ""));
//			
//			zhihuAirticle.setImages(detailObject.get("image").toString().replaceAll("\\\\", "/"));
//			
//			zhihuAirticle.setContent(content);
//			
//			
//				messageService.addZhihuAirticle(zhihuAirticle);
//			} catch (Exception e) {
//				log.error("无法插入此数据");
//			}
//			
//			
//		}
//		
//		
//		
//	}
//	}
}
