package com.zdcf.search;

import java.util.List;


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

import com.zdcf.search.entity.ChnlZhihuSearch;
import com.zdcf.search.param.ZhihuSearchParam;
import com.zdcf.search.repository.ChnlZhihuRepository;

@Service("chnlZhihuSearchService")
public class ChnlZhihuSearchService extends BaseSearchService implements IChnlZhihuSearchService {
	@Autowired
	private ChnlZhihuRepository chnlZhihuRepository;
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	//前台用户的搜索
	public List<ChnlZhihuSearch> search(ZhihuSearchParam param) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
			    .withQuery(createQuery(param))
			    .withFilter(createFilter(param))
			    .withSort(addSort(param))
			    .withPageable(new PageRequest(param.getI() - 1, param.getS()))
			    .build();
		
		Page<ChnlZhihuSearch> page = elasticsearchTemplate.queryForPage(searchQuery, ChnlZhihuSearch.class);
		
		param.setTotalNum((int)page.getTotalElements());
		
		return page.getContent();
	}
	
	protected QueryBuilder createQuery(ZhihuSearchParam param){
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		
		boolean title = addTitleQuery(param.getTitle(), builder);
		
		if(title){
			return builder;
		}
		
		return QueryBuilders.matchAllQuery();
	}
	
	public void index(ChnlZhihuSearch search){
		chnlZhihuRepository.index(search);
	}
	
	public void del(int id){
		chnlZhihuRepository.delete(id);
	}
	
	public void save(ChnlZhihuSearch search){
		chnlZhihuRepository.save(search);
	}

	protected boolean addTitleQuery(String title, BoolQueryBuilder builder){
		if(StringUtils.isNotBlank(title)){
//			title =title.replaceAll("[^a-zA-Z0-9\\u4E00-\\u9FA5]", "");//只查英文、汉字和数字
			String titleStr =title.replaceAll("[^\u4E00-\u9FA5]", "");//只查汉字;
			for(int i=0;i<titleStr.length();i++){
				builder.must(QueryBuilders.wildcardQuery("title", "*"+titleStr.charAt(i)+"*"));
			}
			
			return true;
		}
		
		return false;
	}
	
	
	private BoolQueryBuilder createFilter(ZhihuSearchParam param){
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
	
		this.addTitleQuery(param.getTitle(), builder);
		//默认查已发布的数据
//		addDraftFlagFilter(param.getDraftFlag()==null?Constants.PostStatus.Approved:param.getDraftFlag(), builder);
		//      addDelFlagFilter(param.getDelFlag()==null?Constants.IS_DELFLAG_TYPE.NORMAL:param.getDelFlag(),builder);
//		addApprvlStatusFilter(param.getApprvlStatus()==null?Constants.ApprvlStatus.Approved:param.getApprvlStatus(), builder);
		
		
		return builder;
	}
	
	private void addIDFilter(int id, BoolQueryBuilder builder){
		if(id != 0){
			builder.must(QueryBuilders.termQuery("id", id));
		}
	}
	
	private BoolQueryBuilder createIDFilter(ZhihuSearchParam param){
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		addIDFilter(param.getId()==null?0:param.getId(), builder);
		
		//默认查询已发布数据
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
