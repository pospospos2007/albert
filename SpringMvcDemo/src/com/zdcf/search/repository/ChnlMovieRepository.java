package com.zdcf.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.zdcf.search.entity.ChnlMovieSearch;

public interface ChnlMovieRepository extends ElasticsearchRepository<ChnlMovieSearch, Integer> {

}
