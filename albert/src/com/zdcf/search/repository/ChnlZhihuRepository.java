package com.zdcf.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.zdcf.search.entity.ChnlZhihuSearch;

public interface ChnlZhihuRepository extends ElasticsearchRepository<ChnlZhihuSearch, Integer> {

}
