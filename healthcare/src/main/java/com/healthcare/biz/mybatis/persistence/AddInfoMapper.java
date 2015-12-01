package com.healthcare.biz.mybatis.persistence;

import java.util.List;
import java.util.Map;

public interface AddInfoMapper {

	List<Map<String, Object>> getAddInfoList(Map<String, Object> pamp);
	
	List<Map<String, Object>> getQuestAnswerPlay(Map<String, Object> pamp);
}
