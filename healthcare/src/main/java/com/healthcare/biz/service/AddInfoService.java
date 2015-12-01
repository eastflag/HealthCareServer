package com.healthcare.biz.service;

import java.util.List;
import java.util.Map;


public interface AddInfoService {
	
		List<Map<String, Object>> getAddInfoList(String userId);
		List<Map<String, Object>> getQuestAnswerPlay(String userId);
}
