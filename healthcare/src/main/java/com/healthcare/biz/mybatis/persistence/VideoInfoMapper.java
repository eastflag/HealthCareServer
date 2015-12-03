package com.healthcare.biz.mybatis.persistence;

import java.util.List;

import com.healthcare.bean.VideoListRequest;
import com.healthcare.biz.mybatis.domain.VideoInfo;

public interface VideoInfoMapper {
	List<VideoInfo> getVideoInfoListByMasterGradeId(String masterGradeId);
	
	List<VideoInfo> getVideoInfoListByInfoType(String schoolGradeId);
	
	List<VideoInfo> getVideoInfoListByMasterGradeIdAndUserId(VideoListRequest videoListRequest);
	
}
