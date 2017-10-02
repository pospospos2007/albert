package com.zdcf.service.Impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdcf.dao.TwitterMediaMapper;
import com.zdcf.dao.TwitterPostMapper;
import com.zdcf.dao.customize.CustomizeTwitterPostMapper;
import com.zdcf.dto.TwitterPostDTO;
import com.zdcf.model.TwitterMedia;
import com.zdcf.model.TwitterMediaExample;
import com.zdcf.model.TwitterPost;
import com.zdcf.model.TwitterUser;
import com.zdcf.service.TwitterPostService;
import com.zdcf.tool.PageVo;
import com.zdcf.tool.StringUtil;

@Service
@Transactional
public class TwitterPostServiceImpl implements  TwitterPostService {

	
	@Autowired
	private TwitterPostMapper twitterPostMapper;
	
	@Autowired
	private CustomizeTwitterPostMapper customizeTwitterPostMapper;
	
	@Autowired
	private TwitterMediaMapper twitterMediaMapper;
	
	public int insert(TwitterPost twitterPost){
		
		int  count = twitterPostMapper.insert(twitterPost);
		
		return count;
	}

	@Override
	public TwitterPost findById(Long id){
		
		return twitterPostMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public Serializable getListPage(
			PageVo<Map<String, Object>> pageVo,TwitterPost tp) {
		int offset = pageVo.getCurrentPage() - 1;
		if (offset < 0)
			offset = 0;
		List<Map<String, Object>> list = customizeTwitterPostMapper
				.getListPage( offset * pageVo.getPageSize(),
						pageVo.getPageSize(),tp);
		int count = customizeTwitterPostMapper.getCount(tp);
		pageVo.setVoList(list);
		pageVo.setRecordCount(count);
		
		return pageVo;
		
	}
	
	protected List<TwitterMedia> getMediaListByPostId(Long id){
		TwitterMediaExample example = new TwitterMediaExample();
		example.createCriteria().andPostIdEqualTo(id);
		return twitterMediaMapper.selectByExample(example);
	}
	
}
