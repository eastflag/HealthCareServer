package com.healthcare.biz.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.healthcare.biz.mybatis.domain.ActivityDevice;
import com.healthcare.biz.mybatis.domain.ActivityDeviceStudentInfo;

public interface ActivityDeviceMapper {
	List<ActivityDevice> getDevices();
	
	List<ActivityDeviceStudentInfo> getDevicesStudentMap(
			@Param("year") String year, 
			@Param("school_id") String school_id, 
			@Param("grade_id") String grade_id, 
			@Param("class_id") String class_id);
}
