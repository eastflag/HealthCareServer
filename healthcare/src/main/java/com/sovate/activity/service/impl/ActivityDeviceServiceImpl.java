package com.sovate.activity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.biz.mybatis.domain.ActivityDevice;
import com.healthcare.biz.mybatis.persistence.ActivityDeviceMapper;
import com.sovate.activity.service.ActivityDeviceService;

@Service("ActivityDeviceService")
public class ActivityDeviceServiceImpl implements ActivityDeviceService {

	@Autowired
	ActivityDeviceMapper activityDeviceInfoMapper;
	
	@Override
	public List<ActivityDevice> getDevices() {
		// TODO Auto-generated method stub
		
		List<ActivityDevice> list =  activityDeviceInfoMapper.getDevices();
		
		return list;
	}

}
