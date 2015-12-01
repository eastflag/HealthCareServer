package com.healthcare.biz.mybatis.persistence;

public interface HealthAdviserMapper {
	String getHealthDeacription(String bimGradeId);
	String getEatingDescription(String bimGradeId);
	String getExerciseDescription(String bimGradeId);

}
