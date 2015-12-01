package com.healthcare.biz.mybatis.domain;

import java.io.Serializable;

public class BodyMeasureSummary  implements Serializable {

	private static final long serialVersionUID = 2147193102426195881L;

	//사용자 구별 ID
	String userId;
	String name;
	String sex;
	String age;
	String schoolGradeId; //2015.06.01  파람 추가 
	String schoolId; //2015.06.01  파람 추가 
	String classNumber; //2015.06.01  파람 추가 
	
	// 측정 정보
	String measureDate;
	String height;
	String heightStatus;
	
	String weight;
	String weigthStatus;
	
	String percentageOfBodyFat;
	
	String bmi;
	String bmiStatus;
	String bmiGradeId;
	
	// 흡연 관련 수치
	String ppm;
	String cohd;
	String smokeStatus;
	
	// 성장 점수
	String growthGrade;
	
	
	
	//2015.05.26 파람 추가
	
	String schoolYear; //학기년도 
	
	

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
	
	//2015.06.01 파람 추가 == start
	public String getSchoolGradeId(){
		return schoolGradeId;
	}
	public void setSchoolGradeId(String schoolGradeId){
		this.schoolGradeId = schoolGradeId;
	}
	
	public String getSchoolId(){
		return schoolId;
	}
	public void setSchoolId(String schoolId){
		this.schoolId = schoolId;
	}
	
	public String getClassNumber(){
		return classNumber;
	}
	public void setClassNumber(String classNumber){
		this.classNumber = classNumber;
	}
	//2015.06.01 파람 추가 ==end 

	public String getMeasureDate() {
		return measureDate;
	}

	public void setMeasureDate(String measureDate) {
		this.measureDate = measureDate;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getHeightStatus() {
		return heightStatus;
	}

	public void setHeightStatus(String heightStatus) {
		this.heightStatus = heightStatus;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWeigthStatus() {
		return weigthStatus;
	}

	public void setWeigthStatus(String weigthStatus) {
		this.weigthStatus = weigthStatus;
	}

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

	public String getBmiStatus() {
		return bmiStatus;
	}

	public void setBmiStatus(String bmiStatus) {
		this.bmiStatus = bmiStatus;
	}

	public String getBmiGradeId() {
		return bmiGradeId;
	}

	public void setBmiGradeId(String bmiGradeId) {
		this.bmiGradeId = bmiGradeId;
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

	public String getSmokeStatus() {
		return smokeStatus;
	}

	public void setSmokeStatus(String smokeStatus) {
		this.smokeStatus = smokeStatus;
	}

	public String getGrowthGrade() {
		return growthGrade;
	}

	public void setGrowthGrade(String growthGrade) {
		this.growthGrade = growthGrade;
	}

	
	
	//2015.05.26 파람 추가

	public String getSchoolYear() {
		return schoolYear;
	}


	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}


	//2015.05.26 파람 추가
	
	@Override
	public String toString() {
		return "BodyMeasureSummary [userId=" + userId + ", name=" + name
				+ ", sex=" + sex + ", age=" + age + ", schoolGradeId=" + schoolGradeId + ", schoolId=" + schoolId 
				+ ", classNumber=" + classNumber+ ", measureDate="+ measureDate 
				+ ", height=" + height + ", heightStatus="
				+ heightStatus + ", weight=" + weight + ", weigthStatus="
				+ weigthStatus + ", percentageOfBodyFat=" + percentageOfBodyFat
				+ ", bmi=" + bmi + ", bmiStatus=" + bmiStatus + ", bmiGradeId="
				+ bmiGradeId + ", ppm=" + ppm + ", cohd=" + cohd
				+ ", smokeStatus=" + smokeStatus + ", growthGrade="
				+ growthGrade + "schoolYear"+schoolYear+"]";
	}

}
