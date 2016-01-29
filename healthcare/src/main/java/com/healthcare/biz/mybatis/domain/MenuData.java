package com.healthcare.biz.mybatis.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuData {

	DateFormat sdFormat = new SimpleDateFormat("yyyy.MM.dd");
	
	Date menuDate = null;
	String menuContent = "";
	
	String calorie = "";
	String carbohydrate = "";
	String protein = "";
	String fat = "";
	String vitaminA = "";
	String thiamine = "";
	String riboflavin = "";
	String vitaminC = "";
	String calcium = "";
	String iron = "";
	String schoolId = "";
	String year = "";
	String month = "";
	String date = "";
	String day = ""; // 요일

	public MenuData()
	{
		
	}
	
	public MenuData(String menuData)
	{
		menuContent = menuData;
	}

	public Date getMenuDate() {
		return menuDate;
	}
	
	public void SetMenuDate(String dateString)
	{
		try {
			menuDate = sdFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setMenuDate(Date menuDate) {
		this.menuDate = menuDate;
	}

	public String getMenuContent() {
		return menuContent;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setMenuContent(String menuContent) {
		this.menuContent = menuContent;
	}

	public DateFormat getSdFormat() {
		return sdFormat;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public void setSdFormat(DateFormat sdFormat) {
		this.sdFormat = sdFormat;
	}

	public String getCalorie() {
		return calorie;
	}

	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}

	public String getProtein() {
		return protein;
	}

	public void setProtein(String protein) {
		this.protein = protein;
	}

	public String getCarbohydrate() {
		return carbohydrate;
	}

	public void setCarbohydrate(String carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	public String getFat() {
		return fat;
	}

	public void setFat(String fat) {
		this.fat = fat;
	}

	public String getVitaminA() {
		return vitaminA;
	}

	public void setVitaminA(String vitaminA) {
		this.vitaminA = vitaminA;
	}

	public String getThiamine() {
		return thiamine;
	}

	public void setThiamine(String thiamine) {
		this.thiamine = thiamine;
	}

	public String getRiboflavin() {
		return riboflavin;
	}

	public void setRiboflavin(String riboflavin) {
		this.riboflavin = riboflavin;
	}

	public String getVitaminC() {
		return vitaminC;
	}

	public void setVitaminC(String vitaminC) {
		this.vitaminC = vitaminC;
	}

	public String getCalcium() {
		return calcium;
	}

	public void setCalcium(String calcium) {
		this.calcium = calcium;
	}

	public String getIron() {
		return iron;
	}

	public void setIron(String iron) {
		this.iron = iron;
	}

	@Override
	public String toString() {
		return "MenuData [year=" + year + ", month=" + month + ",date=" + date + ",day=" + day + ", schoolId=" + schoolId
				+ ", menuContent=" + menuContent
				+ ", calorie=" + calorie + ", carbohydrate=" + carbohydrate + ", protein=" + protein + ", fat=" + fat
				+ ", vitaminA=" + vitaminA + ", thiamine=" + thiamine + ", riboflavin=" + riboflavin + ", vitaminC="
				+ vitaminC + ", calcium=" + calcium + ", iron=" + iron + "]\n";
	}

}
