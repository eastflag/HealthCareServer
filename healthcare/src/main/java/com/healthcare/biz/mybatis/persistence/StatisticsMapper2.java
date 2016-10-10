package com.healthcare.biz.mybatis.persistence;

import java.util.List;

import com.healthcare.biz.mybatis.domain.GcmVO;


public interface StatisticsMapper2 {
	List<GcmVO> getMdnList();
	int updateStudent(GcmVO gcm);
	int selectGadianInfo(GcmVO gcm);
	int updateGadianInfo(GcmVO gcm);
	int deleteGadianInfo(GcmVO gcm);
	int insertGadianMappingInfo(GcmVO gcm);
	int insertGadianInfo(GcmVO gcm);
}
