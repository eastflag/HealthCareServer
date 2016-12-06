package com.healthcare.biz.mybatis.domain;

import org.codehaus.jackson.annotate.JsonIgnore;

public class InbodyInfoVO {
	private String measure_date;
	private int school_id;
	private String school_name;
	private int grade_id;
	private int class_id;
	@JsonIgnore
	private String sex;
	
	private float height;
	private float weight;
	private float bmi;
	private float body_fat_mass;
	private float soft_lean_mass;
	private float bmr;
	private float weight_control;
	private float fat_control;
	private float muscle_control;
	private float neck;
	private float chest;
	private float abd;
	private float hip;
	private float thighl;
	private float thighr;
	private float acl;
	private float acr;
	
	private float total_body_water;
	private float protein_mass;
	private float mineral_mass;
	private float skeletal_muscle_mass;
	private float fat_free_mass;
	private float waist_hip_ratio;
	
	private float target_weight;
	private float protein_max; 
	private float protein_min;
	private float mineral_max;
	private float mineral_min;
	private float body_fat_mass_max;
	private float body_fat_mass_min;
	
	private float heightAverageOfSchool;
	private float weightAverageOfSchool;
	private int totalNumberOfSchool;
	
	private int  heightSchoolRank;
	private int weightSchoolRank;
	
	private int start_int;
	private int end_int;
	private String bodyForm;
	
	
	public String getMeasure_date() {
		return measure_date;
	}
	public void setMeasure_date(String measure_date) {
		this.measure_date = measure_date;
	}
	public int getSchool_id() {
		return school_id;
	}
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
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
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getBmi() {
		return bmi;
	}
	public void setBmi(float bmi) {
		this.bmi = bmi;
	}
	public float getBody_fat_mass() {
		return body_fat_mass;
	}
	public void setBody_fat_mass(float body_fat_mass) {
		this.body_fat_mass = body_fat_mass;
	}
	public float getSoft_lean_mass() {
		return soft_lean_mass;
	}
	public void setSoft_lean_mass(float soft_lean_mass) {
		this.soft_lean_mass = soft_lean_mass;
	}
	public float getBmr() {
		return bmr;
	}
	public void setBmr(float bmr) {
		this.bmr = bmr;
	}
	public float getWeight_control() {
		return weight_control;
	}
	public void setWeight_control(float weight_control) {
		this.weight_control = weight_control;
	}
	public float getFat_control() {
		return fat_control;
	}
	public void setFat_control(float fat_control) {
		this.fat_control = fat_control;
	}
	public float getMuscle_control() {
		return muscle_control;
	}
	public void setMuscle_control(float muscle_control) {
		this.muscle_control = muscle_control;
	}
	public float getNeck() {
		return neck;
	}
	public void setNeck(float neck) {
		this.neck = neck;
	}
	public float getChest() {
		return chest;
	}
	public void setChest(float chest) {
		this.chest = chest;
	}
	public float getAbd() {
		return abd;
	}
	public void setAbd(float abd) {
		this.abd = abd;
	}
	public float getHip() {
		return hip;
	}
	public void setHip(float hip) {
		this.hip = hip;
	}
	public float getThighl() {
		return thighl;
	}
	public void setThighl(float thighl) {
		this.thighl = thighl;
	}
	public float getThighr() {
		return thighr;
	}
	public void setThighr(float thighr) {
		this.thighr = thighr;
	}
	public float getAcl() {
		return acl;
	}
	public void setAcl(float acl) {
		this.acl = acl;
	}
	public float getAcr() {
		return acr;
	}
	public void setAcr(float acr) {
		this.acr = acr;
	}
	public float getTotal_body_water() {
		return total_body_water;
	}
	public void setTotal_body_water(float total_body_water) {
		this.total_body_water = total_body_water;
	}
	public float getProtein_mass() {
		return protein_mass;
	}
	public void setProtein_mass(float protein_mass) {
		this.protein_mass = protein_mass;
	}
	public float getMineral_mass() {
		return mineral_mass;
	}
	public void setMineral_mass(float mineral_mass) {
		this.mineral_mass = mineral_mass;
	}
	public float getSkeletal_muscle_mass() {
		return skeletal_muscle_mass;
	}
	public void setSkeletal_muscle_mass(float skeletal_muscle_mass) {
		this.skeletal_muscle_mass = skeletal_muscle_mass;
	}
	public float getFat_free_mass() {
		return fat_free_mass;
	}
	public void setFat_free_mass(float fat_free_mass) {
		this.fat_free_mass = fat_free_mass;
	}
	public float getWaist_hip_ratio() {
		return waist_hip_ratio;
	}
	public void setWaist_hip_ratio(float waist_hip_ratio) {
		this.waist_hip_ratio = waist_hip_ratio;
	}
	public float getTarget_weight() {
		return target_weight;
	}
	public void setTarget_weight(float target_weight) {
		this.target_weight = target_weight;
	}
	public float getProtein_max() {
		return protein_max;
	}
	public void setProtein_max(float protein_max) {
		this.protein_max = protein_max;
	}
	public float getProtein_min() {
		return protein_min;
	}
	public void setProtein_min(float protein_min) {
		this.protein_min = protein_min;
	}
	public float getMineral_max() {
		return mineral_max;
	}
	public void setMineral_max(float mineral_max) {
		this.mineral_max = mineral_max;
	}
	public float getMineral_min() {
		return mineral_min;
	}
	public void setMineral_min(float mineral_min) {
		this.mineral_min = mineral_min;
	}
	public float getBody_fat_mass_max() {
		return body_fat_mass_max;
	}
	public void setBody_fat_mass_max(float body_fat_mass_max) {
		this.body_fat_mass_max = body_fat_mass_max;
	}
	public float getBody_fat_mass_min() {
		return body_fat_mass_min;
	}
	public void setBody_fat_mass_min(float body_fat_mass_min) {
		this.body_fat_mass_min = body_fat_mass_min;
	}
	public float getHeightAverageOfSchool() {
		return heightAverageOfSchool;
	}
	public void setHeightAverageOfSchool(float heightAverageOfSchool) {
		this.heightAverageOfSchool = heightAverageOfSchool;
	}
	public float getWeightAverageOfSchool() {
		return weightAverageOfSchool;
	}
	public void setWeightAverageOfSchool(float weightAverageOfSchool) {
		this.weightAverageOfSchool = weightAverageOfSchool;
	}
	public int getTotalNumberOfSchool() {
		return totalNumberOfSchool;
	}
	public void setTotalNumberOfSchool(int totalNumberOfSchool) {
		this.totalNumberOfSchool = totalNumberOfSchool;
	}
	public int getHeightSchoolRank() {
		return heightSchoolRank;
	}
	public void setHeightSchoolRank(int heightSchoolRank) {
		this.heightSchoolRank = heightSchoolRank;
	}
	public int getWeightSchoolRank() {
		return weightSchoolRank;
	}
	public void setWeightSchoolRank(int weightSchoolRank) {
		this.weightSchoolRank = weightSchoolRank;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getStart_int() {
		return start_int;
	}
	public void setStart_int(int start_int) {
		this.start_int = start_int;
	}
	public int getEnd_int() {
		return end_int;
	}
	public void setEnd_int(int end_int) {
		this.end_int = end_int;
	}
	public String getBodyForm() {
		return bodyForm;
	}
	public void setBodyForm(String bodyForm) {
		this.bodyForm = bodyForm;
	}

	
}
