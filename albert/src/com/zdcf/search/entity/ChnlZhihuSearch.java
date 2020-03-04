package com.zdcf.search.entity;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "chnl", type = "zhihu")
public class ChnlZhihuSearch extends SearchBase{

	@Field(
			type = FieldType.String,
			index = FieldIndex.analyzed,
			searchAnalyzer = "standard",
			analyzer = "standard",
			store = true
			)
	private String title;
	
	@Field(type = FieldType.String, index = FieldIndex.no, store = true)
	private String images;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
}
