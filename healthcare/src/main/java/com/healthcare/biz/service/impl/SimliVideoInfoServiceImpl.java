package com.healthcare.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.biz.mybatis.domain.SimliVideoInfo;
import com.healthcare.biz.mybatis.persistence.SimliVideoInfoMapper;
import com.healthcare.biz.service.SimliVideoInfoService;

@Service("simlivideoInfo")
public class SimliVideoInfoServiceImpl implements SimliVideoInfoService {

	@Autowired
	private SimliVideoInfoMapper simliVideoInfoMapper;
	
	@Override
	public List<SimliVideoInfo> getSimliStuVideoInfoList(String userId) {
		return simliVideoInfoMapper.getSimliStuVideoInfoList(userId);
	}

	

}
