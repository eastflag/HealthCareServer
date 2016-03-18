package com.healthcare.biz.service;

import java.util.List;
import java.util.Map;

import com.healthcare.biz.mybatis.domain.Activity;


public interface ExerciseService {

	Activity getStudentActivity(Map<String, Object> map);

	List<Activity> getStudentActivityChart(Map<String, Object> map);

	List<Activity> getStudentActivityHistory(Map<String, Object> map);

	int getStudentActivityRestCnt(Map<String, Object> map);

	String getMainRangking(Map<String, Object> rankingMap);

	Activity getAverage(Map<String, Object> map);
	
}
