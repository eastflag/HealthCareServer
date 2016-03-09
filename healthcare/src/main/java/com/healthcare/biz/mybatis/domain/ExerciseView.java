package com.healthcare.biz.mybatis.domain;

import java.util.List;

public class ExerciseView {

	private List<Exercise> cart;
	private String calorie;
	private String step;
	private String distance;
	private String speed;
	private String bodyType;
	private String calorieMax;
	private String stepMax;
	private String distanceMax;
	private String speedMax;
	public List<Exercise> getCart() {
		return cart;
	}
	public void setCart(List<Exercise> cart) {
		this.cart = cart;
	}
	public String getCalorie() {
		return calorie;
	}
	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getBodyType() {
		return bodyType;
	}
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	public String getCalorieMax() {
		return calorieMax;
	}
	public void setCalorieMax(String calorieMax) {
		this.calorieMax = calorieMax;
	}
	public String getStepMax() {
		return stepMax;
	}
	public void setStepMax(String stepMax) {
		this.stepMax = stepMax;
	}
	public String getDistanceMax() {
		return distanceMax;
	}
	public void setDistanceMax(String distanceMax) {
		this.distanceMax = distanceMax;
	}
	public String getSpeedMax() {
		return speedMax;
	}
	public void setSpeedMax(String speedMax) {
		this.speedMax = speedMax;
	}
	
}
