package com.sovate.activity.service;

import java.util.List;

import com.healthcare.biz.mybatis.domain.ActivityDevice;

public interface ActivityDeviceService {
	
	List<ActivityDevice> getDevices();
}
