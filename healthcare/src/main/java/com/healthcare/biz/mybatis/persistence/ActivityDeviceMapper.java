package com.healthcare.biz.mybatis.persistence;

import java.util.List;

import com.healthcare.biz.mybatis.domain.ActivityDevice;

public interface ActivityDeviceMapper {
	List<ActivityDevice> getDevices();
}
