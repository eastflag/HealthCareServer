package com.healthcare.biz.mybatis.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityWorkRate {

	DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH");
	
	String userId = "";
	String mac = "";
	String sportId = "";
	Date collectDt = new Date();
	String steps = "";
	String calorie = "";
	String distance = "";
	String bmiStatus = "";
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getSportId() {
		return sportId;
	}
	public void setSportId(String sportId) {
		this.sportId = sportId;
	}
	public Date getCollectDt() {
		return collectDt;
	}
	public void setCollectDt(Date collectDt) {
		this.collectDt = collectDt;
	}
	public String getSteps() {
		return steps;
	}
	public void setSteps(String steps) {
		this.steps = steps;
	}
	public String getCalorie() {
		return calorie;
	}
	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getBmiStatus() {
		return bmiStatus;
	}
	public void setBmiStatus(String bmiStatus) {
		this.bmiStatus = bmiStatus;
	}
	@Override
	public String toString() {
		return "ActivityWorkRate [sdFormat=" + sdFormat + ", userId=" + userId + ", mac=" + mac + ", sportId=" + sportId
				+ ", collectDt=" + collectDt + ", steps=" + steps + ", calorie=" + calorie + ", distance=" + distance
				+ ", bmiStatus=" + bmiStatus + "]";
	}
}
