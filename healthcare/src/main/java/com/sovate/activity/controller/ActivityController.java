package com.sovate.activity.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.healthcare.biz.mybatis.domain.ActivityDevice;
import com.healthcare.biz.mybatis.domain.ActivityDeviceStudentInfo;
import com.healthcare.biz.mybatis.domain.ActivityWorkRate;
import com.sovate.activity.bean.ActivityBaseInfo;
import com.sovate.activity.service.ActivityDeviceService;
import com.sovate.common.util.HttptUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

@Controller
public class ActivityController {

	private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);
	
	@Autowired
	ActivityDeviceService activityDeviceService;
	
	
	// TODO spring 3.1 version 임으로 response에 직접 내용 처리
	@RequestMapping(method=RequestMethod.GET, value="/activity/devices")
	public void getDevices(HttpServletResponse response) throws Exception {
		
		logger.debug("/activity/devices");
		
		List<ActivityDevice> list = activityDeviceService.getDevices();

		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(JSONArray.fromObject(list).toString());
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/activity/devices/{schoolID}/{gradeID}/{classID}")
	public void getDevicesStudentMap(HttpServletResponse response, 
			@PathVariable("schoolID") String schoolID, 
			@PathVariable("gradeID") String gradeID,
			@PathVariable("classID") String classID
			) throws Exception {
		
		logger.debug("/activity/devices");
		
		List<ActivityDeviceStudentInfo> list = 
				activityDeviceService.getDevicesStudentMap("2015", schoolID, gradeID, classID);

		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(JSONArray.fromObject(list).toString());
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/activity/student/workrate")
	public void postStudentWorkrate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.debug("/activity/student/workrate");
		// 학생 ID, 칼로리, 걸음수, 이동거리
		//request.get
		
		//List<ActivityDevice> list = activityDeviceService.getDevices();
		
		String body = HttptUtil.getBody(request);
		
		logger.debug(body);
		
		//System.out.println(body);
		
		
		JSONParser parser = new JSONParser();
		
		
		JSONObject jsonObj = (JSONObject) parser.parse(body);

		
		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH");
		Date collectDt = null;
		try {
			
			collectDt = sdFormat.parse((String)jsonObj.get("collectDt"));
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ActivityWorkRate workrate = new ActivityWorkRate();
		
		workrate.setUserId((String)jsonObj.get("userId"));
		workrate.setMac((String)jsonObj.get("mac"));
		workrate.setSportId((String)jsonObj.get("sportId"));
		workrate.setSteps((String)jsonObj.get("steps"));
		workrate.setCalorie((String)jsonObj.get("calorie"));
		workrate.setDistance((String)jsonObj.get("distance"));
		workrate.setCollectDt(collectDt);
		
		int recordCnt = activityDeviceService.insertWorkrate(workrate);
		System.out.println(recordCnt);

		// 생성 완료 코드
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write("");
		if(recordCnt == 1){
			response.setStatus(HttpServletResponse.SC_CREATED);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/activity/baseInfo")
	public void getBaseInfo(HttpServletResponse response) throws Exception {
		
		logger.debug("/activity/baseInfo");
		
		ActivityBaseInfo info = activityDeviceService.getActivityBaseInfo();
		
		Gson gson = new Gson();
				
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(gson.toJson(info));
	}
	
}
