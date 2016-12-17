package com.healthcare.biz.mybatis.domain;

public class PredictVO {
	private int student_id;
	private String student_name;
	private String sex;
	private String birthDate;
	private String school_year;
	private String school_id;
	private int grade_id;
	private int class_id;
	private int age;
	private String school_name;
	
	private InbodyInfoVO lastMeasure;
	private InbodyInfoVO beforeMeasure;
	
	private HealthDescriptionVO description;
	
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getSchool_year() {
		return school_year;
	}
	public void setSchool_year(String school_year) {
		this.school_year = school_year;
	}
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public int getGrade_id() {
		return grade_id;
	}
	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public InbodyInfoVO getLastMeasure() {
		return lastMeasure;
	}
	public void setLastMeasure(InbodyInfoVO lastMeasure) {
		this.lastMeasure = lastMeasure;
	}
	public InbodyInfoVO getBeforeMeasure() {
		return beforeMeasure;
	}
	public void setBeforeMeasure(InbodyInfoVO beforeMeasure) {
		this.beforeMeasure = beforeMeasure;
	}
	public HealthDescriptionVO getDescription() {
		return description;
	}
	public void setDescription(HealthDescriptionVO description) {
		this.description = description;
	}
}
