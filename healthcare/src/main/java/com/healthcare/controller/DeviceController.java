package com.healthcare.controller;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healthcare.biz.mybatis.domain.Device;
import com.healthcare.biz.mybatis.domain.StudentDeviceMapping;
import com.healthcare.biz.service.DeviceService;
import com.healthcare.common.AES256Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class DeviceController {

	private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

	@Autowired
	private DeviceService deviceService;

	private AES256Util aes = new AES256Util();

	/*
	 * 반 목록 가져오기
	 */
	@RequestMapping("/device/getClassList")
	public void getClassList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("/device/getClassList");

		// 현재 연도 가져오기
		Calendar cal = Calendar.getInstance();
		int year = cal.get(cal.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		if (month < 3) { // 개학전
			year--;
		}
		year = 2015;

		String schoolId = request.getParameter("schoolId");
		String gradeId = request.getParameter("gradeId");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("schoolId", schoolId);
		map.put("gradeId", gradeId);

		List<String> classList = deviceService.getClassList(map);

		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(JSONArray.fromObject(classList).toString());
	}

	/*
	 * 학생 목록 가져오기
	 */
	@RequestMapping("/device/getStudentList")
	public void getStudentList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("/device/getStudentList");

		// 현재 연도 가져오기
		Calendar cal = Calendar.getInstance();
		int year = cal.get(cal.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		if (month < 3) { // 개학전
			year--;
		}
		year = 2015;

		String schoolId = request.getParameter("schoolId");
		String gradeId = request.getParameter("gradeId");
		String classId = request.getParameter("classId");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("schoolId", schoolId);
		map.put("gradeId", gradeId);
		map.put("classId", classId);

		List<Device> studentList = deviceService.getStudentList(map);
		if (studentList != null) {
			for (int i = 0; i < studentList.size(); i++) {
				studentList.get(i).setStudentName(aes.decode(studentList.get(i).getStudentName()));
			}
			Collections.sort(studentList, new Comparator<Device>() {

				@Override
				public int compare(Device arg0, Device arg1) {

					return arg0.getStudentName().compareTo(arg1.getStudentName());
				}
			});
		}

		// 남는 mac 가져오기
		List<Device> restMacList = deviceService.getRestMacList(map);

		StudentDeviceMapping sdm = new StudentDeviceMapping();
		sdm.setStudentList(studentList);
		sdm.setRestMacList(restMacList);

		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(JSONObject.fromObject(sdm).toString());
	}

	/*
	 * 학생 기기정보 저장
	 */
	@RequestMapping("/device/saveStudentDevice")
	public void saveStudentDevice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("/device/getStudentList");

		String deviceName = request.getParameter("deviceName");
		String studentId = request.getParameter("studentId");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceName", deviceName);
		map.put("studentId", studentId);

		int cnt = deviceService.getStudentDeviceMappingCnt(studentId);
		int result = 0; // 실패
		if (cnt > 0) {
			result = deviceService.updateStudentDevice(map);
		} else {
			result = deviceService.insertStudentDevice(map);
		}

		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(Integer.toString(result));
	}

	/*
	 * 학생 기기정보 저장
	 */
	@RequestMapping("/device/deleteStudentDevice")
	public void deleteStudentDevice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("/device/getStudentList");

		String studentId = request.getParameter("studentId");

		int result = 0; // 실패
		result = deviceService.deleteStudentDevice(studentId);

		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(Integer.toString(result));
	}

	/*
	 * 디바이스 목록 개수 가져오기
	 */
	@RequestMapping("/device/getDeviceListCnt")
	public void getDeviceListCnt(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("command") Device command) throws Exception {
		logger.debug("/device/getDeviceListCnt");

		int cnt = deviceService.getDeviceListCnt(command);
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(Integer.toString(cnt));
	}

	/*
	 * 디바이스 목록 가져오기
	 */
	@RequestMapping("/device/getDeviceList")
	public void getDeviceList(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("command") Device command) throws Exception {
		logger.debug("/device/getDeviceListCnt");

		int limitStart = (Integer.parseInt(command.getCurPageNum()) - 1)
				* Integer.parseInt(command.getListScale());
		String limitStartStr = Integer.toString(limitStart);
		command.setLimitStart(limitStartStr);
		
		List<Device> list = deviceService.getDeviceList(command);
		
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(JSONArray.fromObject(list).toString());
	}

	/*
	 * mac 중복검사
	 */
	@RequestMapping("/device/checkMac")
	public void checkMac(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("command") Device command) throws Exception {
		logger.debug("/device/checkMac");

		String mac = request.getParameter("mac");
		int cnt = deviceService.checkMac(mac);
		
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(Integer.toString(cnt));
	}

	/*
	 * 디바이스 수정
	 */
	@RequestMapping("/device/saveDevice")
	public void saveDevice(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("command") Device command) throws Exception {
		logger.debug("/device/saveDevice");

		String mac = request.getParameter("mac");
		String deviceNameOld = request.getParameter("deviceNameOld");
		String deviceName = request.getParameter("deviceName");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mac", mac);
		map.put("deviceNameOld", deviceNameOld);
		map.put("deviceName", deviceName);

		int macCnt = deviceService.macCnt(map);
		int deviceNameCnt = deviceService.deviceNameCnt(map);

		int result = -1;
		if(macCnt==0 && deviceNameCnt==0) {
			result = deviceService.saveDevice(map);
		}
		
		
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(Integer.toString(result));
	}

	/*
	 * 디바이스 추가
	 */
	@RequestMapping("/device/addDevice")
	public void addDevice(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("command") Device command) throws Exception {
		logger.debug("/device/addDevice");

		String mac = request.getParameter("mac");
		String deviceName = request.getParameter("deviceName");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mac", mac);
		map.put("deviceName", deviceName);

		int result = deviceService.addDevice(map);
		
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(Integer.toString(result));
	}

	/*
	 * 디바이스 삭제
	 */
	@RequestMapping("/device/deleteDevice")
	public void deleteDevice(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("command") Device command) throws Exception {
		logger.debug("/device/deleteDevice");

		String deviceName = request.getParameter("deviceName");
;

		int result = deviceService.deleteDevice(deviceName);
		
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(Integer.toString(result));
	}
}
