package com.healthcare.biz.mybatis.persistence;

import java.util.List;
import java.util.Map;

import com.healthcare.biz.mybatis.domain.BodyMeasureGrade;
import com.healthcare.biz.mybatis.domain.BodyMeasureSummary;

public interface BodyMeasureMapper {

	List<BodyMeasureSummary> getSummary(Map<String, String> pamp);
	List<BodyMeasureSummary> getSummaryExceptSmoke(Map<String, String> pamp);
	
	String getBeforeMeasureDate(BodyMeasureGrade bodyMeasureGrade);
	
	String getNowSchoolYear(BodyMeasureGrade bodyMeasureGrade);
	
	String getBeforeMeasureGradeId(BodyMeasureGrade bodyMeasureGrade);
	
	String getBeforeSchoolYear(BodyMeasureGrade bodyMeasureGrade);
	
	BodyMeasureGrade getGradeBySection(BodyMeasureGrade bodyMeasureGrade);
	BodyMeasureGrade getBeforeGradeRankingBySection(BodyMeasureGrade bodyMeasureGrade);
	BodyMeasureGrade getSmokerGrade(String ppm);
	
	BodyMeasureGrade getGradeRankingBySection(BodyMeasureGrade bodyMeasureGrade);
	
}
