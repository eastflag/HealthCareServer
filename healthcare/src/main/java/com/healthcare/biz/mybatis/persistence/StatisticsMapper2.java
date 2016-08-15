package com.healthcare.biz.mybatis.persistence;

import java.util.List;

import com.healthcare.biz.mybatis.domain.GcmVO;


public interface StatisticsMapper2 {
	List<GcmVO> getMdnList();
}
