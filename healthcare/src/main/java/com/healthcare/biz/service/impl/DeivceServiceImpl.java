package com.healthcare.biz.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.biz.mybatis.domain.Device;
import com.healthcare.biz.mybatis.persistence.DeviceMapper;
import com.healthcare.biz.service.DeviceService;


@Service("Device")
public class DeivceServiceImpl  implements DeviceService{

	@Autowired
	DeviceMapper deviceMapper;

	@Override
	public List<String> getClassList(Map<String, Object> map) {
		return deviceMapper.getClassList(map);
	}

	@Override
	public List<Device> getStudentList(Map<String, Object> map) {
		return deviceMapper.getStudentList(map);
	}

	@Override
	public List<Device> getRestMacList(Map<String, Object> map) {
		return deviceMapper.getRestMacList(map);
	}

	@Override
	public int getStudentDeviceMappingCnt(String studentId) {
		return deviceMapper.getStudentDeviceMappingCnt(studentId);
	}

	@Override
	public int updateStudentDevice(Map<String, Object> map) {
		return deviceMapper.updateStudentDevice(map);
	}

	@Override
	public int insertStudentDevice(Map<String, Object> map) {
		return deviceMapper.insertStudentDevice(map);
	}

	@Override
	public int deleteStudentDevice(String studentId) {
		return deviceMapper.deleteStudentDevice(studentId);
	}

	@Override
	public int getDeviceListCnt(Device command) {
		return deviceMapper.getDeviceListCnt(command);
	}

	@Override
	public List<Device> getDeviceList(Device command) {
		return deviceMapper.getDeviceList(command);
	}

	@Override
	public int checkMac(String mac) {
		return deviceMapper.checkMac(mac);
	}

	@Override
	public int macCnt(Map<String, Object> map) {
		return deviceMapper.macCnt(map);
	}

	@Override
	public int deviceNameCnt(Map<String, Object> map) {
		return deviceMapper.deviceNameCnt(map);
	}

	@Override
	public int saveDevice(Map<String, Object> map) {
		return deviceMapper.saveDevice(map);
	}

	@Override
	public int addDevice(Map<String, Object> map) {
		return deviceMapper.addDevice(map);
	}

	@Override
	public int deleteDevice(String deviceName) {
		return deviceMapper.deleteDevice(deviceName);
	}

	


}
