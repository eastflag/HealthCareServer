package com.healthcare.biz.mybatis.domain;

import java.util.List;

public class SimliQuestion {

	private String questId;
	private String content;
	private String video;
	private String videoC;
	private String img;

	private List<SimliAnswer> answer;

	public List<SimliAnswer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<SimliAnswer> answer) {
		this.answer = answer;
	}
	
	public String getQuestId() {
		return questId;
	}
	public void setQuestId(String questId) {
		this.questId = questId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getVideoC() {
		return videoC;
	}
	public void setVideoC(String videoC) {
		this.videoC = videoC;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	

	
	
}
