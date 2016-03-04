package com.healthcare.biz.mybatis.domain;

public class ActivityDeviceStudentInfo extends ActivityDevice{
	
	// 사용자 구별 ID
	
	String userId;
	String name;
	
	String schoolYear; //학기년도
	String schoolId;
	String schoolGradeId;
	
	String classNumber;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getSchoolGradeId() {
		return schoolGradeId;
	}

	public void setSchoolGradeId(String schoolGradeId) {
		this.schoolGradeId = schoolGradeId;
	}

	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

	@Override
	public String toString() {
		return "ActivityDeviceStudentInfo [userId=" + userId + ", name=" + name + ", schoolYear=" + schoolYear
				+ ", schoolId=" + schoolId + ", schoolGradeId=" + schoolGradeId + ", classNumber=" + classNumber
				+ ", mac=" + mac + ", alias=" + alias + ", description=" + description + "]";
	}
	
}
