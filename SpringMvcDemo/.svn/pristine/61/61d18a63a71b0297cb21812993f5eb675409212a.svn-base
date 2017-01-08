package com.zdcf.search;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.zdcf.search.entity.ChnlThemeSearch;
import com.zdcf.search.param.ThemeSearchParam;
import com.zdcf.search.repository.ChnlThemeRepository;

@Service()
public class ChnlThemeSearchService extends BaseSearchService implements IChnlThemeSearchService {
	@Autowired
	private ChnlThemeRepository chnlThemeRepository;
	
//	@Resource(name="elasticsearchTemplate") // run一下，好
//	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Autowired 
	private ElasticsearchTemplate elasticsearchTemplate;
	
	//前台用户的搜索  我对这个编译器不怎么熟悉，平时用的idea，这个类在哪，定位一下。。。在哪什么？这个是eclipse啊
	public List<ChnlThemeSearch> search(ThemeSearchParam param) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
			    .withQuery(createQuery(param))
			    .withFilter(createFilter(param))
			    .withSort(addSort(param))
			    .withPageable(new PageRequest(param.getI() - 1, param.getS()))
			    .build();
		
		Page<ChnlThemeSearch> page = elasticsearchTemplate.queryForPage(searchQuery, ChnlThemeSearch.class);
		
		param.setTotalNum((int)page.getTotalElements());
		
		return page.getContent();
	}
	
	protected QueryBuilder createQuery(ThemeSearchParam param){
//		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		
//		boolean title = addTitleQuery(param.getTitle(), builder);
//		
//		if(title){
//			return builder;
//		}
		
		return QueryBuilders.matchAllQuery();
	}
	
	public void index(ChnlThemeSearch search){
		chnlThemeRepository.index(search);
	}
	
	public void del(int id){
		chnlThemeRepository.delete(id);
	}
	
	public void save(ChnlThemeSearch search){
		chnlThemeRepository.save(search);
	}

	protected boolean addThemeQuery(String theme, BoolQueryBuilder builder){
		if(StringUtils.isNotBlank(theme)){
			builder.must(QueryBuilders.wildcardQuery("theme", "*"+theme+"*"));
			
			return true;
		}
		
		return false;
	}
	
	
	private BoolQueryBuilder createFilter(ThemeSearchParam param){
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
	
		this.addThemeQuery(param.getTheme(), builder);
		//默认查已发布的数据
//		addDraftFlagFilter(param.getDraftFlag()==null?Constants.PostStatus.Approved:param.getDraftFlag(), builder);
		//      addDelFlagFilter(param.getDelFlag()==null?Constants.IS_DELFLAG_TYPE.NORMAL:param.getDelFlag(),builder);
//		addApprvlStatusFilter(param.getApprvlStatus()==null?Constants.ApprvlStatus.Approved:param.getApprvlStatus(), builder);
		
		
		return builder;
	}
	
	

	//查询单个餐饮
//	public ChnlFoodSearch searchById(ZhihuSearchParam param) {
//		SearchQuery searchQuery = new NativeSearchQueryBuilder()
//			    .withFilter(createIDFilter(param))
//			    .withSort(addSort(param))
//			    .withPageable(new PageRequest(param.getI()-1, param.getS()))
//			    .build();
//
//		Page<ChnlFoodSearch> page = elasticsearchTemplate.queryForPage(searchQuery, ChnlFoodSearch.class);
//		
//		param.setTotalNum((int)page.getTotalElements());
//		
//		if(null!=page&&page.getTotalElements()>0){
//			return page.getContent().get(0);
//		}else{
//			return null;
//		}
//		
//	}
}
