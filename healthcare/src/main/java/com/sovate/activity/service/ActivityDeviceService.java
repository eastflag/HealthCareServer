package com.sovate.activity.service;

import java.util.List;

import com.healthcare.biz.mybatis.domain.ActivityDevice;
import com.healthcare.biz.mybatis.domain.ActivityDeviceStudentInfo;
import com.healthcare.biz.mybatis.domain.ActivitySchoolInfo;
import com.healthcare.biz.mybatis.domain.ActivitySport;
import com.healthcare.biz.mybatis.domain.ActivityWorkRate;
import com.sovate.activity.bean.ActivityBaseInfo;

public interface ActivityDeviceService {
	
	List<ActivityDevice> getDevices();
	
	List<ActivityDeviceStudentInfo> getDevicesStudentMap(
			String year, 
			String school_id, 
			String grade_id, 
			String class_id);
	
	int insertWorkrate(ActivityWorkRate workrate);
	
	List<ActivitySchoolInfo> getSchoolInfo();
	
	List<ActivitySport> getSportInfo();
	
	ActivityBaseInfo getActivityBaseInfo();
	
}
