package com.healthcare.bean;

public class Growth {
	String gradeId = "";
	String gradeString = "";
	
	String score = "0";
	
	String description = "";
	String eatingHabits = "";
	String exercise = "";
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeString() {
		return gradeString;
	}
	public void setGradeString(String gradeString) {
		this.gradeString = gradeString;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEatingHabits() {
		return eatingHabits;
	}
	public void setEatingHabits(String eatingHabits) {
		this.eatingHabits = eatingHabits;
	}
	public String getExercise() {
		return exercise;
	}
	public void setExercise(String exercise) {
		this.exercise = exercise;
	}
	@Override
	public String toString() {
		return "Growth [gradeId=" + gradeId + ", gradeString=" + gradeString
				+ ", score=" + score + ", description=" + description
				+ ", eatingHabits=" + eatingHabits + ", exercise=" + exercise
				+ "]";
	}
}
