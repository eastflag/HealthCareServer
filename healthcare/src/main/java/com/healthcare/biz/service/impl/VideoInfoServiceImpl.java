package com.healthcare.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.biz.mybatis.domain.VideoInfo;
import com.healthcare.biz.mybatis.persistence.VideoInfoMapper;
import com.healthcare.biz.service.VideoInfoService;

@Service("videoInfo")
public class VideoInfoServiceImpl implements VideoInfoService {

	@Autowired
	private VideoInfoMapper videoInfoMapper;
	
	@Override
	public List<VideoInfo> getVideoInfoListByMasterGradeId(String masterGradeId) {
		return videoInfoMapper.getVideoInfoListByMasterGradeId(masterGradeId);
	}

	@Override
	public List<VideoInfo> getVideoInfoListByInfoType(String infoType) {
		return videoInfoMapper.getVideoInfoListByInfoType(infoType);
	}

}
