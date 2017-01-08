package com.zdcf.service.Impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdcf.base.Constants;
import com.zdcf.dto.MessageDTO;
import com.zdcf.dto.ThemeDTO;
import com.zdcf.dto.ZhihuDTO;
import com.zdcf.mapper.MessageMapper;
import com.zdcf.model.Message;
import com.zdcf.model.Theme;
import com.zdcf.model.Zhihu;
import com.zdcf.service.BaseService;
import com.zdcf.service.MessageService;
import com.zdcf.tool.PageVo;

@Service
@Transactional
public class MessageServiceImpl  extends BaseService implements  MessageService {
	
	private static Logger logger = Logger.getLogger(MessageServiceImpl.class);
	
	@Resource
	private MessageMapper messageMapper;
	
	@Autowired
	RedisCacheService redisCacheService;
	
	

	@Override
	public List<ThemeDTO> getThemeList(){
		
		List<ThemeDTO> list = messageMapper.getThemeList();
		
		return list;
		
	}



	@Override
	public int addTheme(Theme theme) {
		
		return messageMapper.addTheme(theme);
		
	}
	
	@Override
	public int addZhihuAirticle(Zhihu zhihu) throws Exception{
		
		Date now =new Date();
		
		zhihu.setAddTime(now);
		zhihu.setUpdateTime(now);
		zhihu.setReviewNum(0);
		
		int count = messageMapper.addZhihuAirticle(zhihu);
		
		if(count ==1){
			this.sendMessage(zhihu, Constants.Cache.Type.save);
		}
		
		return count;
		
	}
	


	@Override
	public ThemeDTO getThemeById(int id) {
		return messageMapper.getThemeById(id);
	}
	
	@Override
	public ZhihuDTO getZhihuDetailById(int id) {
		
		Zhihu temp = new Zhihu();
		temp.setId(id);
		
		Zhihu zhihu = redisCacheService.get(temp.getCacheKey(), Zhihu.class);
		
		//不在缓存中，去数据库中查询
		if(zhihu == null){
			zhihu = messageMapper.getZhihuDetailById(id);
			if(zhihu !=null){
				super.sendMessage(zhihu, Constants.Cache.Type.update);
			}
		} 
		
		ZhihuDTO zhihuDTO = new ZhihuDTO();
		
		if(zhihu != null){
			BeanUtils.copyProperties(zhihu,zhihuDTO );
			return zhihuDTO;
		}else{
			return null;
		}
		
	}
	
	@Override
	public void storeAllZhihu() {
		
		List<Zhihu> list= messageMapper.getAllZhihu();
		
		for(int i=0;i<list.size();i++){
			
			this.sendMessage(list.get(i), Constants.Cache.Type.save);
		}
		
	}



	@Override
	public List<MessageDTO> getMessagesByThemeId(int id) {
		
		return messageMapper.getMessagesByThemeId(id);
	}



	@Override
	public int addMessage(Message message) {
		
		messageMapper.updateThemeTime(message.getThemeId());
		
		return messageMapper.addMessage(message);
	}



	@Override
	public Theme getThemeByTheme(String theme) {
		
		return messageMapper.getThemeByTheme(theme);
	}



	@Override
	public Serializable getThemeListPage(
			PageVo<Map<String, Object>> pageVo2) {
		PageVo<Map<String, Object>> pageVo = (PageVo<Map<String, Object>>) pageVo2;
		int offset = pageVo.getCurrentPage() - 1;
		if (offset < 0)
			offset = 0;
		List<Map<String, Object>> commonNewsList = messageMapper
				.getThemeListPage( offset * pageVo.getPageSize(),
						pageVo.getPageSize());
		int count = messageMapper.getThemeCount(offset* pageVo.getPageSize(), pageVo.getPageSize());
		pageVo.setVoList(commonNewsList);
		pageVo.setRecordCount(count);
		
//		try{
//			ZhihuDTO zhihu = new ZhihuDTO();
//			zhihu.setContent("this is s test2");
//			zhihu.setId(2);
//			zhihu.setCss("11");
//			zhihu.setImages("22");
//			zhihu.setTitle("11");
//			zhihu.setJs("1");
//			this.sendMessage(zhihu, Constants.Cache.Type.save);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		int  zhihu1  =redisCacheService.getInt("zhihu:1");
//		
//		System.out.println("count:"+zhihu1);
		
		
		return pageVo;
		
	}
	
	
	@Override
	public Serializable getZhihuArticleListPage(
			PageVo<Map<String, Object>> pageVo2) {
		PageVo<Map<String, Object>> pageVo = (PageVo<Map<String, Object>>) pageVo2;
		int offset = pageVo.getCurrentPage() - 1;
		if (offset < 0)
			offset = 0;
		List<Map<String, Object>> commonNewsList = messageMapper
				.getZhihuArticleListPage( offset * pageVo.getPageSize(),
						pageVo.getPageSize());
		int count = messageMapper.getZhihuArticleCount(offset* pageVo.getPageSize(), pageVo.getPageSize());
		pageVo.setVoList(commonNewsList);
		pageVo.setRecordCount(count);
		return pageVo;
		
	}
	
	@Override
	public List<HashMap<String, Object>> findNextTheme(int index,int count){
		
		List<HashMap<String, Object>> starLoanlist = messageMapper.findNextTheme(index,count);
		
		return starLoanlist;
		
	}
	
	@Override
	public List<HashMap<String, Object>> findNextZhihuAirticle(int index,int count){
		
		List<HashMap<String, Object>> starLoanlist = messageMapper.findNextZhihuAirticle(index,count);
		
		return starLoanlist;
		
	}
	

}
