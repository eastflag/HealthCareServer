package com.healthcare.biz.service;

import java.util.List;
import java.util.Map;

import com.healthcare.biz.mybatis.domain.Activity;
import com.healthcare.biz.mybatis.domain.Device;


public interface DeviceService {

	List<String> getClassList(Map<String, Object> map);

	List<Device> getStudentList(Map<String, Object> map);

	List<Device> getRestMacList(Map<String, Object> map);

	int getStudentDeviceMappingCnt(String studentId);

	int updateStudentDevice(Map<String, Object> map);

	int insertStudentDevice(Map<String, Object> map);

	int deleteStudentDevice(String studentId);

	int getDeviceListCnt(Device command);

	List<Device> getDeviceList(Device command);

	int checkMac(String mac);

	int macCnt(Map<String, Object> map);

	int deviceNameCnt(Map<String, Object> map);

	int saveDevice(Map<String, Object> map);

	int addDevice(Map<String, Object> map);

	int deleteDevice(String deviceName);
	
}
