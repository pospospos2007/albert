package com.zdcf.model;

import java.util.Date;

public class TwitterSearchHistory {
    private Integer id;

    private Date searchDate;

    private String searchKey;

    private Integer searcherType;

    private String searchResult;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey == null ? null : searchKey.trim();
    }

    public Integer getSearcherType() {
        return searcherType;
    }

    public void setSearcherType(Integer searcherType) {
        this.searcherType = searcherType;
    }

    public String getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(String searchResult) {
        this.searchResult = searchResult == null ? null : searchResult.trim();
    }
}