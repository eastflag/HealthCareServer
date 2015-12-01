package com.healthcare.bean;

public class Bmi {
	String percentageOfBodyFat = "0";
	String bmi = "0";
	
	String gradeString = "";
	String gradeId = "";
	public String getPercentageOfBodyFat() {
		return percentageOfBodyFat;
	}
	public void setPercentageOfBodyFat(String percentageOfBodyFat) {
		this.percentageOfBodyFat = percentageOfBodyFat;
	}
	public String getBmi() {
		return bmi;
	}
	public void setBmi(String bmi) {
		this.bmi = bmi;
	}
	public String getGradeString() {
		return gradeString;
	}
	public void setGradeString(String gradeString) {
		this.gradeString = gradeString;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	@Override
	public String toString() {
		return "Bmi [percentageOfBodyFat=" + percentageOfBodyFat + ", bmi="
				+ bmi + ", gradeString=" + gradeString + ", gradeId=" + gradeId
				+ "]";
	}
	
	
	
	
	
}
