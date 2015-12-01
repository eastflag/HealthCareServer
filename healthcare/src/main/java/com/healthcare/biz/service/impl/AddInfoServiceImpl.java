package com.healthcare.biz.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.biz.service.AddInfoService;
import com.healthcare.common.AES256Util;
import com.healthcare.biz.mybatis.persistence.AddInfoMapper;


@Service("addinfo")
public class AddInfoServiceImpl  implements AddInfoService{
	
	@Autowired
	AddInfoMapper addinfoMapper;



public List<Map<String, Object>> getAddInfoList(String userId){
	
	AES256Util aes = new AES256Util();

	List<Map<String, Object>> list =null;

	
	return list;
}
public List<Map<String, Object>> getQuestAnswerPlay(String userId){
List<Map<String, Object>> list =null;

	
	return list;
	
	}
}
