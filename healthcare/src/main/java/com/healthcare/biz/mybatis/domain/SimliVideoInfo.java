package com.healthcare.biz.mybatis.domain;

import java.io.Serializable;

public class SimliVideoInfo implements Serializable {
	
	private static final long serialVersionUID = 2028192438262741373L;
	
	String thumbnailUrl = "";
	String videoUrl = "";
	
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	
	
}
