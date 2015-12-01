package com.healthcare.biz.mybatis.persistence;

import java.util.List;

import com.healthcare.biz.mybatis.domain.AverageItem;
import com.healthcare.biz.mybatis.domain.StatisticsParma;


public interface StatisticsMapper {
	List<AverageItem> getAverageList(StatisticsParma statisticsParma);


	
	AverageItem getAveragePerClass(StatisticsParma statisticsParma);
	AverageItem getAveragePerSchool(StatisticsParma statisticsParma);
	AverageItem getAveragePerLocal(StatisticsParma statisticsParma);
	AverageItem getAveragePerNation(StatisticsParma statisticsParma);
	String  getBMIIsNormal(StatisticsParma statisticsParma);
}
