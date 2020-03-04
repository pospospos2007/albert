package com.zdcf.search.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.zdcf.base.Constants;

/**
 * 
 * @author Li
 *
 */

@Document(indexName = "chnl", type = "theme")
public class ChnlThemeSearch extends SearchBase {

	@Field(
			type = FieldType.String,
			index = FieldIndex.analyzed,
			searchAnalyzer = "standard",
			analyzer = "standard",
			store = true
			)
	private String theme;
	

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

}
