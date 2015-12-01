package com.healthcare.bean;

public class Smoke {
	String gradeId = "";
	String gradeString = "";
	
	String ppm = "0";
	String cohd = "0";
	String schoolGradeId = "0";
	
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
	public String getPpm() {
		return ppm;
	}
	public void setPpm(String ppm) {
		this.ppm = ppm;
	}
	public String getCohd() {
		return cohd;
	}
	public void setCohd(String cohd) {
		this.cohd = cohd;
	}
	public String getSchoolGradeId() {
		return schoolGradeId;
	}
	public void setSchoolGradeId(String schoolGradeId) {
		this.schoolGradeId = schoolGradeId;
	}
	@Override
	public String toString() {
		return "Smoke [gradeId=" + gradeId + ", gradeString=" + gradeString
				+ ", ppm=" + ppm + ", cohd=" + cohd + ", schoolGradeId=" + schoolGradeId + "]";
	}
	
}
