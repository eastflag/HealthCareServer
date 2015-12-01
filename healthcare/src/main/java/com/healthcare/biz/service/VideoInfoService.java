package com.healthcare.biz.service;

import java.util.List;

import com.healthcare.biz.mybatis.domain.VideoInfo;

public interface VideoInfoService {
	List<VideoInfo> getVideoInfoListByMasterGradeId(String masterGradeId);
	
	List<VideoInfo> getVideoInfoListByInfoType(String infoType);
}
