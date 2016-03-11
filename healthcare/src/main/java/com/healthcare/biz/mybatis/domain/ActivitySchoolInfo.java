package com.healthcare.biz.mybatis.domain;

public class ActivitySchoolInfo {

	String schoolYear = "";
	String schoolId  = "";
	String gradeId  = "";
	String classCnt  = "";
	String schoolName  = "";
	String section  = "";
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getClassCnt() {
		return classCnt;
	}
	public void setClassCnt(String classCnt) {
		this.classCnt = classCnt;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	@Override
	public String toString() {
		return "ActivitySchoolInfo [schoolYear=" + schoolYear + ", schoolId=" + schoolId + ", gradeId=" + gradeId
				+ ", classCnt=" + classCnt + ", schoolName=" + schoolName + ", section=" + section + "]";
	}

	
}
