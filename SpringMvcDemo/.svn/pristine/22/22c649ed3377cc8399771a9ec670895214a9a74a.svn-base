package com.zdcf.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zdcf.dto.MessageDTO;
import com.zdcf.dto.ThemeDTO;
import com.zdcf.dto.ZhihuDTO;
import com.zdcf.model.Airticle;
import com.zdcf.model.Message;
import com.zdcf.model.Theme;
import com.zdcf.model.Zhihu;
import com.zdcf.tool.PageVo;

public interface MessageService {
	
	public List<ThemeDTO> getThemeList();
	
	public Serializable getThemeListPage(PageVo<Map<String, Object>> page);
	
	public int addTheme(Theme theme);
	
	public ThemeDTO getThemeById(int id);
	
	public List<MessageDTO> getMessagesByThemeId(int id);
	
	public int addMessage(Message message);
	
	public Theme getThemeByTheme(String theme);
	
	public int addZhihuAirticle(Zhihu zhihu) throws Exception;

	Serializable getZhihuArticleListPage(PageVo<Map<String, Object>> pageVo2);

	ZhihuDTO getZhihuDetailById(int id);

	public List<HashMap<String, Object>> findNextTheme(int index,int count);

	List<HashMap<String, Object>> findNextZhihuAirticle(int index,int count);

	void storeAllZhihu();

}
