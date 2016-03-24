package com.healthcare.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healthcare.biz.mybatis.domain.Activity;
import com.healthcare.biz.mybatis.domain.BodyMeasureSummary;
import com.healthcare.biz.mybatis.domain.Exercise;
import com.healthcare.biz.mybatis.domain.ExerciseHistory;
import com.healthcare.biz.mybatis.domain.ExerciseMain;
import com.healthcare.biz.mybatis.domain.ExerciseTab;
import com.healthcare.biz.mybatis.domain.ExerciseView;
import com.healthcare.biz.service.BodyMeasureService;
import com.healthcare.biz.service.ExerciseService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class ExerciseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExerciseController.class);
	
	@Autowired
	private ExerciseService exerciseService;
	@Autowired
	private BodyMeasureService bodyMeasureService;

	String contentsURL = "http://210.127.55.205/psychology_contents/workrate/";
	
	/*
	 * 메인화면
	 * */
	@RequestMapping("/exercise/main")
	public void exerciseMain(HttpServletResponse response,
			@RequestParam(value="userId", required=true) String userId,
			@RequestParam(value="exerciseId", required=false) String exerciseId) throws Exception {//, required=false
		logger.debug("/exercise/main:"+userId);

		ExerciseMain exercise = new ExerciseMain();


	    // 학생정보 가져오기
		BodyMeasureSummary vo = bodyMeasureService.getSummary(userId);
		
		// 운동 가져오기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("exerciseId", exerciseId);
		Activity activity = exerciseService.getStudentActivity(map);
		
		String acitivityDate = "";
		String exerciseIdPrev = "";
		String exerciseIdNext = "";
		String exerciseName = "";
		String exerciseImg = "";
		String calorie = "";
		String step = "";
		String distance = "";
		String rangkingClass = "";
		String rangkingGrade = "";
		String calorieAverage = "";
		String bodytype = "";
		if(activity!=null) {
			//요일 가져오기
			acitivityDate = activity.getReg_datetime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse(acitivityDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int dayNum = cal.get(Calendar.DAY_OF_WEEK);
			String dayofweek = ""; 
		    switch(dayNum){
		        case 1:
		        	dayofweek = "일";
		        case 2:
		        	dayofweek = "월";
		        case 3:
		        	dayofweek = "화";
		        case 4:
		        	dayofweek = "수";
		        case 5:
		        	dayofweek = "목";
		        case 6:
		        	dayofweek = "금";
		        case 7:
		        	dayofweek = "토";
		             
		    }
		    if(!dayofweek.equals("")) {
			    acitivityDate += "("+dayofweek+")";
		    }
		    acitivityDate = acitivityDate.replaceAll("-", ".");
		    
		    // 값 넣기
		    exerciseId = activity.getRegist_id();
		    exerciseIdPrev = activity.getRegist_id_prev();
		    exerciseIdNext = activity.getRegist_id_next();
		    exerciseName = activity.getSports_name();
		    exerciseImg = contentsURL+activity.getLarge_image_path();
		    calorie = activity.getCalorie();
		    step = activity.getSteps();
		    distance = String.format("%.1f", Float.parseFloat(activity.getDistance())/1000);
		    bodytype = activity.getBmi_status();
		    
		    // 반랭킹 가져오기
			Map<String, Object> map2 = new HashMap<String, Object>();

			int year =  cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+1;
			if(month<3) { // 개학전
				year--;
			}
			year = 2015; // 테스트테스트
			map2.put("userId", userId);
			map2.put("school_year", year);
			map2.put("school_id", vo.getSchoolId());
			map2.put("grade_id", vo.getSchoolGradeId());
			map2.put("class", vo.getClassNumber());
			map2.put("reg_datetime", activity.getReg_datetime());
			map2.put("sports_id", activity.getSports_id());
			map2.put("type", "class");
			
			rangkingClass = exerciseService.getMainRangking(map2);
			
			// 반평균 가져오기
			map2.put("bmi_status", bodytype);
			map2.put("avg_type", "calorie");
			Activity avg = exerciseService.getAverage(map2);
			calorieAverage = avg.getAvg();
			
			// 학년랭킹 가져오기
			map2.put("reg_datetime", activity.getReg_datetime().substring(0, 7));
			map2.put("type", "grade");
			rangkingGrade = exerciseService.getMainRangking(map2);
					
		    
		}
		
	    // 결과 값 넣기
		exercise.setExerciseId(exerciseId);
		exercise.setExerciseIdPrev(exerciseIdPrev);
		exercise.setExerciseIdNext(exerciseIdNext);
		exercise.setExerciseDate(acitivityDate);
		exercise.setExerciseName(exerciseName);
		exercise.setExerciseImg(exerciseImg);
		exercise.setCalorie(calorie);
		exercise.setStep(step);
		exercise.setDistance(distance);
		exercise.setBodyType(bodytype);
		exercise.setRangkingClass(rangkingClass);
		exercise.setRangkingGrade(rangkingGrade);
		exercise.setCalorieMax("600"); // 고정
		exercise.setCalorieAverage(calorieAverage);
		
		
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(JSONObject.fromObject(exercise).toString());
	}
	
	/*
	 * 상세보기화면
	 * */
	@RequestMapping("/exercise/view")
	public void exerciseView(HttpServletResponse response,
			@RequestParam(value="userId") String userId,
			@RequestParam(value="exerciseId") String exerciseId) throws Exception {//, required=false
		logger.debug("/exercise/view:"+userId);

		ExerciseView exerciseView= new ExerciseView();
		List<Exercise> chart = new ArrayList<Exercise>();
		Exercise exercise = new Exercise();

	    // 체형 가져오기
		BodyMeasureSummary vo = bodyMeasureService.getSummary(userId);
		logger.debug(vo.toString());
		String bodytype = "";
		if(vo.getBmiStatus().length()==4) {
			bodytype = vo.getBmiStatus().substring(0, 2);
		}else if (vo.getBmiStatus().length()==5) {
			bodytype = vo.getBmiStatus().substring(0, 3);
		}else if (vo.getBmiStatus().length()==6) {
			bodytype = vo.getBmiStatus().substring(0, 4);
		}
		
		int calorie = 0;
		int step = 0;
		int distance = 0;
		int cnt = 0;
		
		// 운동 차트 가져오기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("exerciseId", exerciseId);
		List<Activity> activityChart = exerciseService.getStudentActivityChart(map);
		if(activityChart!=null && activityChart.size()>0) {
			for(int i=activityChart.size()-1; i>=0; i--) {
				exercise = new Exercise();
				exercise.setDate(activityChart.get(i).getReg_datetime());
				exercise.setExerciseName(activityChart.get(i).getSports_name());
				exercise.setCalorie(activityChart.get(i).getCalorie());
				exercise.setStep(activityChart.get(i).getSteps());
				exercise.setDistance(String.format("%.1f",Float.parseFloat(activityChart.get(i).getDistance())/1000));
				chart.add(exercise);

				calorie += Integer.parseInt(activityChart.get(i).getCalorie());
				step += Integer.parseInt(activityChart.get(i).getSteps());
				distance += Integer.parseInt(activityChart.get(i).getDistance());
				cnt++;
			}
		}
		exerciseView.setChart(chart);
		
		exerciseView.setBodyType(bodytype);
		

		exerciseView.setCalorieMax("600"); // 고정
		exerciseView.setStepMax("5000"); // 고정
		exerciseView.setDistanceMax("20.0"); //고정


		exerciseView.setCalorie(Integer.toString(calorie/cnt));
		exerciseView.setStep(Integer.toString(step/cnt));
		exerciseView.setDistance(String.format("%.1f", (float)(distance/cnt)/1000));
		

		
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(JSONObject.fromObject(exerciseView).toString());
	}
	
	/*
	 * 상세보기 탭화면
	 * */
	@RequestMapping("/exercise/tab")
	public void exerciseTab(HttpServletResponse response,
			@RequestParam(value="userId") String userId,
			@RequestParam(value="exerciseId") String exerciseId,
			@RequestParam(value="averageType") String averageType,
			@RequestParam(value="groupType") String groupType) throws Exception {//, required=false
		logger.debug("/exercise/tab:"+userId);

		ExerciseTab exerciseTab= new ExerciseTab();

	    // 체형 가져오기
		BodyMeasureSummary vo = bodyMeasureService.getSummary(userId);
		String bodytype = "";
		if(vo.getBmiStatus().length()==4) {
			bodytype = vo.getBmiStatus().substring(0, 2);
		}else if (vo.getBmiStatus().length()==5) {
			bodytype = vo.getBmiStatus().substring(0, 3);
		}else if (vo.getBmiStatus().length()==6) {
			bodytype = vo.getBmiStatus().substring(0, 4);
		}

		// 운동 가져오기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("exerciseId", exerciseId);
		Activity activity = exerciseService.getStudentActivity(map);

		String acitivityDate = "";
		int averageCnt = 0;
		int bodytype0 = 0;
		int bodytype1 = 0;
		int bodytype2 = 0;
		int bodytype3 = 0;
		int bodytype4 = 0;
		int bodytype5 = 0;
		int bodytype6 = 0;

		String max = "0";
		String user = "0";
		if(activity!=null) {
			acitivityDate = activity.getReg_datetime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse(acitivityDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
		    // 평균 가져오기
			Map<String, Object> map2 = new HashMap<String, Object>();

			int year =  cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+1;
			if(month<3) { // 개학전
				year--;
			}
			year = 2015; // 테스트테스트
			map2.put("userId", userId);
			map2.put("school_year", year);
			map2.put("school_id", vo.getSchoolId());
			map2.put("grade_id", vo.getSchoolGradeId());
			map2.put("class", vo.getClassNumber());
			map2.put("reg_datetime", activity.getReg_datetime());
			map2.put("sports_id", activity.getSports_id());
			map2.put("type", groupType);
			map2.put("avg_type", averageType);

			// 저체중
			map2.put("bmi_status", "저체중");
			Activity avgBodyType1 = exerciseService.getAverage(map2);
			averageCnt += Integer.parseInt(avgBodyType1.getCnt());
			bodytype1 = Integer.parseInt(avgBodyType1.getAvg());
			bodytype0 += bodytype1; 
			
			// 정상
			map2.put("bmi_status", "정상");
			Activity avgBodyType2 = exerciseService.getAverage(map2);
			averageCnt += Integer.parseInt(avgBodyType2.getCnt());
			bodytype2 = Integer.parseInt(avgBodyType2.getAvg());
			bodytype0 += bodytype2;
			
			// 과체중
			map2.put("bmi_status", "과체중");
			Activity avgBodyType3 = exerciseService.getAverage(map2);
			averageCnt += Integer.parseInt(avgBodyType3.getCnt());
			bodytype3 = Integer.parseInt(avgBodyType3.getAvg());
			bodytype0 += bodytype3;
			
			// 비만
			map2.put("bmi_status", "비만");
			Activity avgBodyType4 = exerciseService.getAverage(map2);
			averageCnt += Integer.parseInt(avgBodyType4.getCnt());
			bodytype4 = Integer.parseInt(avgBodyType4.getAvg());
			bodytype0 += bodytype4;
			
			// 중도비만
			map2.put("bmi_status", "중도비만");
			Activity avgBodyType5 = exerciseService.getAverage(map2);
			averageCnt += Integer.parseInt(avgBodyType5.getCnt());
			bodytype5 = Integer.parseInt(avgBodyType5.getAvg());
			bodytype0 += bodytype5;
			
			// 고도비만
			map2.put("bmi_status", "고도비만");
			Activity avgBodyType6 = exerciseService.getAverage(map2);
			averageCnt += Integer.parseInt(avgBodyType6.getCnt());
			bodytype6 = Integer.parseInt(avgBodyType6.getAvg());
			bodytype0 += bodytype6;
			
			bodytype0 = bodytype0/averageCnt;

			if(averageType.equals("calorie")) {
				max = "600";
				user = activity.getCalorie();
			} else if(averageType.equals("step")) {
				max = "5000";
				user = activity.getSteps();
			} else if(averageType.equals("distance")) {
				max = "20.0";
				user = activity.getDistance();
			}
			
			
		}
		
		
		exerciseTab.setBodyType(bodytype);
		exerciseTab.setAverageCnt(Integer.toString(averageCnt));
		
		if(averageType.equals("distance")) {
			exerciseTab.setUser(String.format("%.1f", Float.parseFloat(user)/1000));		
			exerciseTab.setAll(String.format("%.1f", (float)bodytype0/1000));
			exerciseTab.setBodyType1(String.format("%.1f", (float)bodytype1/1000));
			exerciseTab.setBodyType2(String.format("%.1f", (float)bodytype2/1000));
			exerciseTab.setBodyType3(String.format("%.1f", (float)bodytype3/1000));
			exerciseTab.setBodyType4(String.format("%.1f", (float)bodytype4/1000));
			exerciseTab.setBodyType5(String.format("%.1f", (float)bodytype5/1000));
			exerciseTab.setBodyType6(String.format("%.1f", (float)bodytype6/1000));
		} else {
			exerciseTab.setUser(user);		
			exerciseTab.setAll(Integer.toString(bodytype0));
			exerciseTab.setBodyType1(Integer.toString(bodytype1));
			exerciseTab.setBodyType2(Integer.toString(bodytype2));
			exerciseTab.setBodyType3(Integer.toString(bodytype3));
			exerciseTab.setBodyType4(Integer.toString(bodytype4));
			exerciseTab.setBodyType5(Integer.toString(bodytype5));
			exerciseTab.setBodyType6(Integer.toString(bodytype6));
		}
		

		exerciseTab.setBodyType1Max(max);
		exerciseTab.setBodyType2Max(max);
		exerciseTab.setBodyType3Max(max);
		exerciseTab.setBodyType4Max(max);
		exerciseTab.setBodyType5Max(max);
		exerciseTab.setBodyType6Max(max);
		
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(JSONObject.fromObject(exerciseTab).toString());
	}
	
	/*
	 * 히스토리화면
	 * */
	@RequestMapping("/exercise/history")
	public void exerciseHistory(HttpServletResponse response,
			@RequestParam(value="userId") String userId,
			@RequestParam(value="exerciseId") String exerciseId) throws Exception {//, required=false
		logger.debug("/exercise/history:"+userId);

		ExerciseHistory history = new ExerciseHistory();
		List<Exercise> list = new ArrayList<Exercise>();
		Exercise exercise = new Exercise();

		// 운동 차트 가져오기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("exerciseId", exerciseId);
		String regist_id_last = "";
		List<Activity> activityHistory = exerciseService.getStudentActivityHistory(map);
		if(activityHistory!=null && activityHistory.size()>0) {
			for(int i=0; i<activityHistory.size(); i++) {
				exercise = new Exercise();
				exercise.setExerciseId(activityHistory.get(i).getRegist_id());
				exercise.setDate(activityHistory.get(i).getReg_datetime());
				exercise.setExerciseName(activityHistory.get(i).getSports_name());
				exercise.setImg(contentsURL+activityHistory.get(i).getSmall_image_path());
				exercise.setCalorie(activityHistory.get(i).getCalorie());
				exercise.setStep(activityHistory.get(i).getSteps());
				exercise.setDistance(String.format("%.1f", Float.parseFloat(activityHistory.get(i).getDistance())/1000));
				list.add(exercise);
				
				if(i==activityHistory.size()-1) {
					regist_id_last = activityHistory.get(i).getRegist_id();
				}
			}
		}
		history.setHistory(list);
		
		// 다음 차트 있나 가져오기
		String nextYN = "N";
		if(!regist_id_last.equals("")) {
			map.put("exerciseId", regist_id_last);
			int cnt = exerciseService.getStudentActivityRestCnt(map);
			if(cnt>0) {
				nextYN = "Y";
			}
		}
		
		
		history.setNextYN(nextYN);
		
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(JSONObject.fromObject(history).toString());
	}
}
