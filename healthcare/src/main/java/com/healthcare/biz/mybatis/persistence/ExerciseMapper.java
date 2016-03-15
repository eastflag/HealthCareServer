package com.healthcare.biz.mybatis.persistence;

import java.util.List;
import java.util.Map;

import com.healthcare.biz.mybatis.domain.Activity;

public interface ExerciseMapper {

	Activity getStudentActivity(Map<String, Object> map);

	List<Activity> getStudentActivityChart(Map<String, Object> map);

	List<Activity> getStudentActivityHistory(Map<String, Object> map);

	int getStudentActivityRestCnt(Map<String, Object> map);

	String getMainRangking(Map<String, Object> mapClass);
}
