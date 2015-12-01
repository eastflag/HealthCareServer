package com.healthcare.admin.menu;

import java.io.Serializable;

public class Menu implements Serializable{

	private static final long serialVersionUID = 1L;
	private int menu_id;
	private String school_id;
	private String school_year;
	private String school_month;
	private String school_date;
	private String school_day;
	private String lunch_main;
	private String lunch_detl;
	private String lunch_calorie;
	//2014.05.12 석식 메뉴 제거 관련 주석처리
	/*
	private String dinner_main;
	private String dinner_detl;
	private String dinner_calorie;
	*/
	private String img_url;
	private String reg_date;
	private String reg_id;
	private String upd_date;
	private String upd_id;
	private String use_yn;
	private String school_name;
	//2014.05.27 영양소 7개 추가.
	private String carbohydrate;
	private String protein;
	private String fat;
	private String calcium;
	private String vitamin_A;
	private String vitamin_C;
	private String nutrient_etc;
	
	public Menu(){}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public String getSchool_id() {
		return school_id;
	}

	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}

	public String getSchool_year() {
		return school_year;
	}

	public void setSchool_year(String school_year) {
		this.school_year = school_year;
	}

	public String getSchool_month() {
		return school_month;
	}

	public void setSchool_month(String school_month) {
		this.school_month = school_month;
	}

	public String getSchool_date() {
		return school_date;
	}

	public void setSchool_date(String school_date) {
		this.school_date = school_date;
	}

	public String getSchool_day() {
		return school_day;
	}

	public void setSchool_day(String school_day) {
		this.school_day = school_day;
	}

	public String getLunch_main() {
		return lunch_main;
	}

	public void setLunch_main(String lunch_main) {
		this.lunch_main = lunch_main;
	}

	public String getLunch_detl() {
		return lunch_detl;
	}

	public void setLunch_detl(String lunch_detl) {
		this.lunch_detl = lunch_detl;
	}

	public String getLunch_calorie() {
		return lunch_calorie;
	}

	public void setLunch_calorie(String lunch_calorie) {
		this.lunch_calorie = lunch_calorie;
	}
	//2014.05.12 석식 메뉴 제거 관련 주석처리
	/*
	public String getDinner_main() {
		return dinner_main;
	}

	public void setDinner_main(String dinner_main) {
		this.dinner_main = dinner_main;
	}

	public String getDinner_detl() {
		return dinner_detl;
	}

	public void setDinner_detl(String dinner_detl) {
		this.dinner_detl = dinner_detl;
	}

	public String getDinner_calorie() {
		return dinner_calorie;
	}

	public void setDinner_calorie(String dinner_calorie) {
		this.dinner_calorie = dinner_calorie;
	}
	 */
	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public String getUpd_date() {
		return upd_date;
	}

	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}

	public String getUpd_id() {
		return upd_id;
	}

	public void setUpd_id(String upd_id) {
		this.upd_id = upd_id;
	}

	public String getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	//2014.05.27 영양소 7개 추가.

	public String getCarbohydrate() {
		return carbohydrate;
	}

	public void setCarbohydrate(String carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	public String getProtein() {
		return protein;
	}

	public void setProtein(String protein) {
		this.protein = protein;
	}

	public String getFat() {
		return fat;
	}

	public void setFat(String fat) {
		this.fat = fat;
	}

	public String getCalcium() {
		return calcium;
	}

	public void setCalcium(String calcium) {
		this.calcium = calcium;
	}

	public String getVitamin_A() {
		return vitamin_A;
	}

	public void setVitamin_A(String vitamin_A) {
		this.vitamin_A = vitamin_A;
	}

	public String getVitamin_C() {
		return vitamin_C;
	}

	public void setVitamin_C(String vitamin_C) {
		this.vitamin_C = vitamin_C;
	}

	public String getNutrient_etc() {
		return nutrient_etc;
	}

	public void setNutrient_etc(String nutrient_etc) {
		this.nutrient_etc = nutrient_etc;
	}
	
}
