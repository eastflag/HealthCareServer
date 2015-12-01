package com.healthcare.biz.mybatis.domain;

import java.io.Serializable;

public class Student implements Serializable {

	private static final long serialVersionUID = -6174248721288771121L;

	// 사용자 구별 ID
	String userId;

	// 신상 정보
	String name;
	String sex;
	String age;
	String birthDate;
	
	String classNumber;
	String schoolGradeId;
	String schoolId;
	
	// 최신 측정일
	String measureDate;
	
	//학기년도 
	String schoolYear; //학기년도
	String address;

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

	public String getSchoolGradeId() {
		return schoolGradeId;
	}

	public void setSchoolGradeId(String schoolGradeId) {
		this.schoolGradeId = schoolGradeId;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getMeasureDate() {
		return measureDate;
	}

	public void setMeasureDate(String measureDate) {
		this.measureDate = measureDate;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void settSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [userId=" + userId + ", name=" + name + ", sex=" + sex
				+ ", age=" + age + ", birthDate=" + birthDate
				+ ", classNumber=" + classNumber + ", schoolGradeId="
				+ schoolGradeId + ", schoolId=" + schoolId + ", measureDate="
				+ measureDate + ", address=" + address + "]";
	}

}
