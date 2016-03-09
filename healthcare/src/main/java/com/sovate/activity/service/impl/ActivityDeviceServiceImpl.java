package com.sovate.activity.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.biz.mybatis.domain.ActivityDevice;
import com.healthcare.biz.mybatis.domain.ActivityDeviceStudentInfo;
import com.healthcare.biz.mybatis.domain.ActivityWorkRate;
import com.healthcare.biz.mybatis.persistence.ActivityDeviceMapper;
import com.healthcare.common.AES256Util;
import com.sovate.activity.service.ActivityDeviceService;

@Service("ActivityDeviceService")
public class ActivityDeviceServiceImpl implements ActivityDeviceService {

	private AES256Util aes = new AES256Util();
	
	@Autowired
	ActivityDeviceMapper activityDeviceInfoMapper;
	
	
	
	@Override
	public List<ActivityDevice> getDevices() {
		// TODO Auto-generated method stub
		
		List<ActivityDevice> list =  activityDeviceInfoMapper.getDevices();
		
		return list;
	}
	

	@Override
	public List<ActivityDeviceStudentInfo> getDevicesStudentMap(String year, String school_id, String grade_id, String class_id) {
		// TODO Auto-generated method stub
		
		List<ActivityDeviceStudentInfo> list = 
				activityDeviceInfoMapper.getDevicesStudentMap(year, school_id, grade_id, class_id);
		
		for(ActivityDeviceStudentInfo item : list){
			
			String userName =  item.getUserName();
			if(userName != null && userName.length() > 0){
			
				try {
					item.setUserName(aes.decode(userName));
				} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException
						| NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException
						| BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}


	@Override
	public int insertWorkrate(ActivityWorkRate workrate) {
		// TODO Auto-generated method stub
		
		return activityDeviceInfoMapper.insertWorkrate(workrate);
	}

}
