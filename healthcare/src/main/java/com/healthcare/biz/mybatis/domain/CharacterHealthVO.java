package com.healthcare.biz.mybatis.domain;

public class CharacterHealthVO {
	private int character_health_description_id;
	private int grade_id;
	private String height;
	private String bmi;
	private String dc_1;
	private String dc_2;
	public int getCharacter_health_description_id() {
		return character_health_description_id;
	}
	public void setCharacter_health_description_id(int character_health_description_id) {
		this.character_health_description_id = character_health_description_id;
	}
	public int getGrade_id() {
		return grade_id;
	}
	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getBmi() {
		return bmi;
	}
	public void setBmi(String bmi) {
		this.bmi = bmi;
	}
	public String getDc_1() {
		return dc_1;
	}
	public void setDc_1(String dc_1) {
		this.dc_1 = dc_1;
	}
	public String getDc_2() {
		return dc_2;
	}
	public void setDc_2(String dc_2) {
		this.dc_2 = dc_2;
	}
	

}
