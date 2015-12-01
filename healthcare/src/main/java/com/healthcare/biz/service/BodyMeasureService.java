package com.healthcare.biz.service;

import java.util.List;

import com.healthcare.bean.Bmi;
import com.healthcare.bean.Growth;
import com.healthcare.bean.MeasureHistory;
import com.healthcare.bean.Smoke;
import com.healthcare.biz.mybatis.domain.AverageItem;
import com.healthcare.biz.mybatis.domain.BodyMeasureGrade;
import com.healthcare.biz.mybatis.domain.BodyMeasureSummary;

public interface BodyMeasureService {
	BodyMeasureSummary getSummary(String userId);
	
	BodyMeasureGrade getHeightMeasureGrade(String userId);
	BodyMeasureGrade getWeightMeasureGrade(String userId);

	MeasureHistory getHeightMeasureHistory(String userId, String historyCount);
	MeasureHistory getWeightMeasureHistory(String userId, String historyCount);
	
	MeasureHistory getHeightMeasureHistoryYear(String userId, String selectedYear);
	MeasureHistory getWeightMeasureHistoryYear(String userId, String selectedYear);
	MeasureHistory getBmiMeasureHistoryYear(String userId, String selectedYear);
	
	
	Bmi getBmi(String userId);
	Growth getGrowth(String userId);
	Smoke getSmoke(String userId);
	
	List<AverageItem> getHeightAveragePerClass(String userId);
	List<AverageItem> getWeightAveragePerClass(String userId);
	List<AverageItem> getBmiAveragePerClass(String userId);
	
	List<AverageItem> getHeightAveragePerSchool(String userId);
	List<AverageItem> getWeightAveragePerSchool(String userId);
	List<AverageItem> getBmiAveragePerSchool(String userId);
	
	// Util
	String getRecentMeasureDate(String userId);
}
