package com.healthcare.biz.service;

import java.util.List;

import com.healthcare.biz.mybatis.domain.SimliVideoInfo;

public interface SimliVideoInfoService {
	List<SimliVideoInfo> getSimliStuVideoInfoList(String userId);
}
