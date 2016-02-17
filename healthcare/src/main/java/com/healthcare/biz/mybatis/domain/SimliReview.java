package com.healthcare.biz.mybatis.domain;

import java.util.List;

public class SimliReview {

	private String questId;
	private String content;

	private List<SimliReviewAnswer> answer;

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

	public List<SimliReviewAnswer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<SimliReviewAnswer> answer) {
		this.answer = answer;
	}


	
	
}
