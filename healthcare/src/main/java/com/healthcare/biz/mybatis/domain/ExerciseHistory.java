package com.healthcare.biz.mybatis.domain;

import java.util.ArrayList;
import java.util.List;

public class ExerciseHistory {

	private List<Exercise> history;
	private String nextYN;
	
	public List<Exercise> getHistory() {
		return history;
	}
	public void setHistory(List<Exercise> history) {
		this.history = history;
	}
	public String getNextYN() {
		return nextYN;
	}
	public void setNextYN(String nextYN) {
		this.nextYN = nextYN;
	}
	
}
