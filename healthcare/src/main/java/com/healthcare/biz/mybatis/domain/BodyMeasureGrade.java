package com.healthcare.biz.mybatis.domain;

import java.io.Serializable;

public class BodyMeasureGrade implements Serializable {

	private static final long serialVersionUID = -637157262549127683L;
	
	// user ID
	String userId;
	
	// grade ID
	String gradeId;
	
	// grade 설명
	String gradeDesc;
	
	// section : BMI, Height, Weight
	String section;
	
	// value : 측정값
	String value;
	
	// beforeValue : 이전 측정값
	String beforeValue;
	
	// 학교 ID
	String schoolId;
		
	// 학년 Grade ID
	String schoolGradeId;
	
	// 성별
	String sex;
	
	// 년도 : 2013
	String year;
	
	// 년도/월 : 20130911
	String measureDate;
	
	// 년도/월 : 20130911
	String beforeMeasureDate;
	
	// 학교내 학년별 순위
	String schoolGrade = "0";
	
	// 이전 학교내 학년별 순위
	String beforeSchoolGrade = "0";
	
	// 학교내 학년별 전체 학생 수
	String totalNumberOfStudent = "0";
	
	// 나라 평균 ( 표준 평균)
	String averageOfNation;
	
	// 지역 평균
	String averageOfLocal;
	
	// 학교 평균
	String averageOfSchool;
	
	// 반 평균
	String averageOfClass;
	

	
	
	
	// 요청 object와 걸과 object가 동일해서 요청 시 인자 갯수 확인을 위해 사용
	public Boolean requestValidation() {
		if (	(section != null && section.length() > 0) &&
				(value != null && value.length() > 0) &&
				(schoolGradeId != null && schoolGradeId.length() > 0) &&
				(sex != null && sex.length() > 0) &&
				(year != null && year.length() > 0) 
				)
		{
			return true;
		}
		
		return false;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getGradeId() {
		return gradeId;
	}


	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}


	public String getGradeDesc() {
		return gradeDesc;
	}


	public void setGradeDesc(String gradeDesc) {
		this.gradeDesc = gradeDesc;
	}


	public String getSection() {
		return section;
	}


	public void setSection(String section) {
		this.section = section;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
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


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getSchoolGrade() {
		return schoolGrade;
	}


	public void setSchoolGrade(String schoolGrade) {
		this.schoolGrade = schoolGrade;
	}


	public String getTotalNumberOfStudent() {
		return totalNumberOfStudent;
	}


	public void setTotalNumberOfStudent(String totalNumberOfStudent) {
		this.totalNumberOfStudent = totalNumberOfStudent;
	}


	public String getMeasureDate() {
		return measureDate;
	}


	public void setMeasureDate(String measureDate) {
		this.measureDate = measureDate;
	}

	public String getAverageOfNation() {
		return averageOfNation;
	}


	public void setAverageOfNation(String averageOfNation) {
		this.averageOfNation = averageOfNation;
	}


	public String getAverageOfLocal() {
		return averageOfLocal;
	}


	public void setAverageOfLocal(String averageOfLocal) {
		this.averageOfLocal = averageOfLocal;
	}


	public String getAverageOfSchool() {
		return averageOfSchool;
	}


	public void setAverageOfSchool(String averageOfSchool) {
		System.out.println("averageOfSchool setting ::  "+averageOfSchool);
		this.averageOfSchool = averageOfSchool;
	}


	public String getAverageOfClass() {
		return averageOfClass;
	}


	public void setAverageOfClass(String averageOfClass) {
		this.averageOfClass = averageOfClass;
	}

	
	
	public String getBeforeMeasureDate() {
		return beforeMeasureDate;
	}


	public void setBeforeMeasureDate(String beforeMeasureDate) {
		this.beforeMeasureDate = beforeMeasureDate;
	}


	public String getBeforeSchoolGrade() {
		return beforeSchoolGrade;
	}


	public void setBeforeSchoolGrade(String beforeSchoolGrade) {
		this.beforeSchoolGrade = beforeSchoolGrade;
	}

	

	public String getBeforeValue() {
		return beforeValue;
	}


	public void setBeforeValue(String beforeValue) {
		this.beforeValue = beforeValue;
	}


	@Override
	public String toString() {
		
		return "BodyMeasureGrade [userId=" + userId + ", gradeId=" + gradeId
				+ ", gradeDesc=" + gradeDesc + ", section=" + section
				+ ", value=" + value + ", beforeValue=" + beforeValue + ", schoolId=" + schoolId
				+ ", schoolGradeId=" + schoolGradeId + ", sex=" + sex
				+ ", year=" + year + ", measureDate=" + measureDate
				+ ", schoolGrade=" + schoolGrade + ", beforeSchoolGrade=" + beforeSchoolGrade + ", totalNumberOfStudent="
				+ totalNumberOfStudent + ", averageOfNation=" + averageOfNation
				+ ", averageOfLocal=" + averageOfLocal + ", averageOfSchool="
				+ averageOfSchool + ", averageOfClass=" + averageOfClass + "]";
	}


}
