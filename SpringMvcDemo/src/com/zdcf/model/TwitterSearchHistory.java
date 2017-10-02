package com.zdcf.model;

import java.util.Date;

import lombok.Data;

@Data
public class TwitterSearchHistory {
    private Long id;

    private Date searchDate;

    private String searchKey;

    private Integer searchType;

  
}