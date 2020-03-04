package com.zdcf.search;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import com.zdcf.search.param.SearchParam;

public class BaseSearchService {
	
//	@Autowired
//	protected ElasticsearchTemplate elasticsearchTemplate;

	//排序
	protected SortBuilder addSort(SearchParam param){
		Integer sort = param.getSort();
		
//		if(null==sort){
//			return SortBuilders.fieldSort("lastRefresh").order(SortOrder.DESC);
//		}else if(Constants.Sort.nearest == sort){
//			return SortBuilders.geoDistanceSort("geoPoint").point(param.getLat(), param.getLon()).order(SortOrder.ASC);
//		
//		} else if(Constants.Sort.pageView == sort){
//			return SortBuilders.fieldSort("reviewNum").order(SortOrder.DESC);
//		
//		} else if(Constants.Sort.score == sort){
//			return SortBuilders.fieldSort("avgScore").order(SortOrder.DESC);
//		
//		} else if(Constants.Sort.priceAsc == sort){
//			return SortBuilders.fieldSort("price").order(SortOrder.ASC);
//		
//		} else if(Constants.Sort.priceDesc == sort){
//			return SortBuilders.fieldSort("price").order(SortOrder.DESC);
//		
//		}
		
		return SortBuilders.fieldSort("addTime").order(SortOrder.DESC);
	}
	
}
