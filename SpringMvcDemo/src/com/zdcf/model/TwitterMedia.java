package com.zdcf.model;

import lombok.Data;

@Data
public class TwitterMedia {
    private Long id;

    private String mediaUrl;

    private Long postId;

    private String videoInfoUrl;

    private Integer width;

    private Integer height;

   
}