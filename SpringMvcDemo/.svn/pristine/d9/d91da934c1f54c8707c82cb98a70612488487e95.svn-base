package com.zdcf.search.entity;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class SearchBase {
	@Id
	private Integer id;
    
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private Integer reviewNum;
    
    @Field(type = FieldType.Date, index = FieldIndex.not_analyzed, store = true)
    private Date addTime;
    
    @Field(type = FieldType.Date, index = FieldIndex.not_analyzed, store = true)
    private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReviewNum() {
		return reviewNum;
	}

	public void setReviewNum(Integer reviewNum) {
		this.reviewNum = reviewNum;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
    
}
