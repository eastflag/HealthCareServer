package com.healthcare.admin.statistics;

import java.io.Serializable;

public class Statistics implements Serializable{

	private static final long serialVersionUID = 2147193102426195881L;	

	private String yearMonth;
	private String school_id;
	private String school_name;
	private String grade_id;
	private String school_class;
	private String stu_name;
	private String stu_sex;
	private String data;
	private String grade_str;
	private String schhol_grade_str;

	private String height_str;
	private String weight_str;
	private String stu_class;
	private String percentage;
	

	private int count;
	private int count_m;
	private int count_f;

	private String section; // BMI / SMOKE : O
	private String measure_date; // 측정일 : M
	private String contets; // 통계 내용 : M 명단 / 카운트
	

	private String searchLevel; // 광명 / 학교 / 학년 / 반 : O
	
	public Statistics(){}

	public int getCount_m() {
		return count_m;
	}

	public void setCount_m(int count_m) {
		this.count_m = count_m;
	}

	public int getCount_f() {
		return count_f;
	}

	public void setCount_f(int count_f) {
		this.count_f = count_f;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public String getSchool_id() {
		return school_id;
	}

	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

	public String getGrade_id() {
		return grade_id;
	}

	public void setGrade_id(String grade_id) {
		this.grade_id = grade_id;
	}

	public String getSchool_class() {
		return school_class;
	}

	public void setSchool_class(String school_class) {
		this.school_class = school_class;
	}

	public String getStu_name() {
		return stu_name;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public String getStu_sex() {
		return stu_sex;
	}

	public void setStu_sex(String stu_sex) {
		this.stu_sex = stu_sex;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getGrade_str() {
		return grade_str;
	}

	public void setGrade_str(String grade_str) {
		this.grade_str = grade_str;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getSchhol_grade_str() {
		return schhol_grade_str;
	}

	public void setSchhol_grade_str(String schhol_grade_str) {
		this.schhol_grade_str = schhol_grade_str;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getMeasure_date() {
		return measure_date;
	}

	public void setMeasure_date(String measure_date) {
		this.measure_date = measure_date;
	}

	public String getContets() {
		return contets;
	}

	public void setContets(String contets) {
		this.contets = contets;
	}
	
	public String getHeight_str() {
		return height_str;
	}

	public void setHeight_str(String height_str) {
		this.height_str = height_str;
	}

	public String getWeight_str() {
		return weight_str;
	}

	public void setWeight_str(String weight_str) {
		this.weight_str = weight_str;
	}

	public String getSearchLevel() {
		return searchLevel;
	}

	public void setSearchLevel(String searchLevel) {
		this.searchLevel = searchLevel;
	}
	

	public String getStu_class() {
		return stu_class;
	}

	public void setStu_class(String stu_class) {
		this.stu_class = stu_class;
	}

	@Override
	public String toString() {
		return "Statistics [yearMonth=" + yearMonth + ", school_id=" + school_id
				+ ", school_name=" + school_name + ", grade_id=" + grade_id + ", school_class=" + school_class
				+ ", stu_name=" + stu_name + ", stu_sex=" + stu_sex + ", data=" + data + ", stu_class=" + stu_class
				+ ", weight_str=" + weight_str + ", height_str=" + height_str + ", searchLevel=" + searchLevel
				+ ", grade_str=" + grade_str + ", schhol_grade_str=" + schhol_grade_str + ", contets=" + contets + "]";
	}
}
