package com.zdcf.model;

import java.util.Date;

import lombok.Data;

@Data
public class TwitterPost {
    private Long id;

    private String text;

    private Long userId;

    private Date createAt;

    private Integer postType;

    
}