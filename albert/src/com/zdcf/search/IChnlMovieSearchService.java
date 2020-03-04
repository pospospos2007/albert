package com.zdcf.search;

import java.util.List;

import com.zdcf.search.entity.ChnlMovieSearch;
import com.zdcf.search.param.MovieSearchParam;

public interface IChnlMovieSearchService {
	List<ChnlMovieSearch> search(MovieSearchParam param);
	
	void save(ChnlMovieSearch search);
	
	void index(ChnlMovieSearch search);
	
	void del(int id);
}
