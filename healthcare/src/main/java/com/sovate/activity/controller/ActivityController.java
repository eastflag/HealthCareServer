package com.sovate.activity.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.healthcare.biz.mybatis.domain.ActivityDevice;
import com.sovate.activity.service.ActivityDeviceService;

import net.sf.json.JSONArray;

@Controller
public class ActivityController {

	private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);
	
	@Autowired
	ActivityDeviceService activityDeviceService;
	
	
	// TODO spring 3.1 version 임으로 jsp를 호출하는 형식을 구현
	@RequestMapping(method=RequestMethod.GET, value="/activity/devices")
	public void getDevices(HttpServletResponse response) throws Exception {
		
		logger.debug("/activity/devices");
//		
//		// 현재 연도 가져오기
//		Calendar cal = Calendar.getInstance();
//		int year = cal.get(cal.YEAR);
//		
//		// 테스트 (2016데이터가 없어서)
//		year = 2015;
//		
//		// 학생 학년 가져오기
//		Student studentInfo = studentService.getStudentByUserId(userId);
//		int grade = Integer.parseInt(studentInfo.getSchoolGradeId());
//		
//		// 학년에 맞는 심리검사 목록 가져오기
//		List<SimliType> simliList = simliService.getSimliTypeList(grade);
		
		List<ActivityDevice> list = activityDeviceService.getDevices();

		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(JSONArray.fromObject(list).toString());
	}
	
}
