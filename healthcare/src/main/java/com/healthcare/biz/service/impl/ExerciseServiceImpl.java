package com.healthcare.biz.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.biz.mybatis.domain.Activity;
import com.healthcare.biz.mybatis.persistence.ExerciseMapper;
import com.healthcare.biz.service.ExerciseService;


@Service("Exercise")
public class ExerciseServiceImpl  implements ExerciseService{

	@Autowired
	ExerciseMapper exerciseMapper;

	@Override
	public Activity getStudentActivity(Map<String, Object> map) {
		return exerciseMapper.getStudentActivity(map);
	}

	@Override
	public List<Activity> getStudentActivityChart(Map<String, Object> map) {
		return exerciseMapper.getStudentActivityChart(map);
	}




}
