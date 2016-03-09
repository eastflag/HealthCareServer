package com.healthcare.biz.mybatis.domain;

import java.util.List;

public class Exercise {

	private String date;
	private String exercise;
	private String exerciseId;
	private String name;
	private String img;
	private int time;
	private int calorie;
	private int step;
	private float distance;
	
	
	
	public String getExerciseId() {
		return exerciseId;
	}
	public void setExerciseId(String exerciseId) {
		this.exerciseId = exerciseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getExercise() {
		return exercise;
	}
	public void setExercise(String exercise) {
		this.exercise = exercise;
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
	
	
}
