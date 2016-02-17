package com.healthcare.biz.mybatis.domain;

import java.util.List;
import com.healthcare.biz.mybatis.domain.SimliQuestion;

public class SimliQnA extends SimliQuestion{

	private List<SimliAnswer> answer;

	public List<SimliAnswer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<SimliAnswer> answer) {
		this.answer = answer;
	}
	
	
}
