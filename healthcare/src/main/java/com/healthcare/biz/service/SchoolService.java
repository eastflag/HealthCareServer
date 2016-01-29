package com.healthcare.biz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.healthcare.biz.mybatis.domain.SchoolInfo;


public interface SchoolService {
	
		ArrayList<SchoolInfo> getSchoolList();
}
