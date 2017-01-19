package com.zdcf.model;

public class TwitterMedia {
    private Long id;

    private String mediaUrl;

    private Long postId;

    private String videoInfoUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl == null ? null : mediaUrl.trim();
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getVideoInfoUrl() {
        return videoInfoUrl;
    }

    public void setVideoInfoUrl(String videoInfoUrl) {
        this.videoInfoUrl = videoInfoUrl == null ? null : videoInfoUrl.trim();
    }
}