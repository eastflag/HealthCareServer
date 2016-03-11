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
import com.healthcare.biz.mybatis.domain.ActivitySchoolInfo;
import com.healthcare.biz.mybatis.domain.ActivitySport;
import com.healthcare.biz.mybatis.domain.ActivityWorkRate;
import com.healthcare.biz.mybatis.persistence.ActivityDeviceMapper;
import com.healthcare.common.AES256Util;
import com.sovate.activity.bean.ActivityBaseInfo;
import com.sovate.activity.bean.ActivityGrade;
import com.sovate.activity.bean.ActivitySchool;
import com.sovate.activity.common.SchoolUtil;
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


	@Override
	public List<ActivitySchoolInfo> getSchoolInfo() {
		
		List<ActivitySchoolInfo> list = activityDeviceInfoMapper.getSchoolInfo();
		
		for(ActivitySchoolInfo item : list){
			
			String name =  item.getSchoolName();
			if(name != null && name.length() > 0){
			
				try {
					item.setSchoolName(aes.decode(name));
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
	public List<ActivitySport> getSportInfo() {

		return activityDeviceInfoMapper.getSportInfo();
	}


	// 개별 쿼리를 통합한 쿼리
	@Override
	public ActivityBaseInfo getActivityBaseInfo() {
		
		ActivityBaseInfo baseInfo = new ActivityBaseInfo();
		
		List<ActivitySchoolInfo> list = getSchoolInfo();
		
		for(ActivitySchoolInfo item : list){
			
			String name =  item.getSchoolName();
			
			ActivitySchool activitySchool = baseInfo.getMapSchool().get(name);
			
			if(activitySchool == null) {
				activitySchool = new ActivitySchool();
				activitySchool.setId(item.getSchoolId());
				activitySchool.setName(item.getSchoolName());
				activitySchool.setSection(item.getSection());
				activitySchool.setId(item.getSchoolId());
				
				baseInfo.getMapSchool().put(item.getSchoolName(), activitySchool);
			} 

			ActivityGrade activityGrade = new ActivityGrade();
			activityGrade.setId(item.getGradeId());
			
			// TODO DB에서 해당 부분을 처리 하도록 수정 요망.
			activityGrade.setName(SchoolUtil.getShoolGradeName(item.getGradeId()));
			
			activityGrade.setClassCount(item.getClassCnt());
			activitySchool.getListGrade().add(activityGrade);
			
			
		}
		
		baseInfo.setListSport(getSportInfo());
		
		return baseInfo;
	}

}
