package com.healthcare.biz.mybatis.domain;

import java.util.List;

public class ExerciseView {

	private List<Exercise> cart;
	private int calorie;
	private int step;
	private float distance;
	private int speed;
	private String bodyType;
	private int calorieMax;
	private int stepMax;
	private float distanceMax;
	private int speedMax;
	
	
	
	public int getCalorieMax() {
		return calorieMax;
	}
	public void setCalorieMax(int calorieMax) {
		this.calorieMax = calorieMax;
	}
	public int getStepMax() {
		return stepMax;
	}
	public void setStepMax(int stepMax) {
		this.stepMax = stepMax;
	}
	public float getDistanceMax() {
		return distanceMax;
	}
	public void setDistanceMax(float distanceMax) {
		this.distanceMax = distanceMax;
	}
	public int getSpeedMax() {
		return speedMax;
	}
	public void setSpeedMax(int speedMax) {
		this.speedMax = speedMax;
	}
	public List<Exercise> getCart() {
		return cart;
	}
	public void setCart(List<Exercise> cart) {
		this.cart = cart;
	}
	public int getCalorie() {
		return calorie;
	}
	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public String getBodyType() {
		return bodyType;
	}
	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	
}
