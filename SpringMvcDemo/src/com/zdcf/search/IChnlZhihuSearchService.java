package com.zdcf.search;

import java.util.List;

import com.zdcf.search.entity.ChnlZhihuSearch;
import com.zdcf.search.param.ZhihuSearchParam;

public interface IChnlZhihuSearchService {
	List<ChnlZhihuSearch> search(ZhihuSearchParam param);
	
//	ChnlFoodSearch searchById(ZhihuSearchParam param);
	
	void save(ChnlZhihuSearch search);
	
	void index(ChnlZhihuSearch search);
	
	void del(int id);
}
