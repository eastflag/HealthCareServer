package com.healthcare.biz.mybatis.domain;

import java.util.List;

public class ExerciseView {

	private List<Exercise> chart;
	private String calorie;
	private String step;
	private String distance;
	private String bodyType;
	private String calorieMax;
	private String stepMax;
	private String distanceMax;

	
	
	public List<Exercise> getChart() {
		return chart;
	}
	public void setChart(List<Exercise> chart) {
		this.chart = chart;
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
}
