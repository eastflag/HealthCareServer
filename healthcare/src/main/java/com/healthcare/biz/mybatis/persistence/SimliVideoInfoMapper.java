package com.healthcare.biz.mybatis.persistence;

import java.util.List;

import com.healthcare.biz.mybatis.domain.SimliVideoInfo;

public interface SimliVideoInfoMapper {
	List<SimliVideoInfo> getSimliStuVideoInfoList(String userId);
	
	
}
