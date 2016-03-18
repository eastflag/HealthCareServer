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
			@RequestParam(value="exerciseId") String exerciseId) throws Exception {//, required=false
		logger.debug("/exercise/main:"+userId);

		ExerciseMain exercise = new ExerciseMain();


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
		String exerciseIdPrev = "";
		String exerciseIdNext = "";
		String exerciseName = "";
		String exerciseImg = "";
		String calorie = "";
		String step = "";
		String distance = "";
		String rangkingClass = "";
		String rangkingGrade = "";
		String rangkingExercise = "";
		String calorieAverage = "";
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
			Activity avg = exerciseService.getAverage(map2);
			calorieAverage = avg.getCalorie();
			
			// 학년랭킹 가져오기
			map2.put("reg_datetime", activity.getReg_datetime().substring(0, 7));
			map2.put("type", "grade");
			rangkingGrade = exerciseService.getMainRangking(map2);
			
			// 종목랭킹 가져오기
			map2.put("reg_datetime", activity.getReg_datetime().substring(0, 4));
			map2.put("type", "exercise");
			rangkingExercise = exerciseService.getMainRangking(map2);
			
			
		    
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
		exercise.setRangkingExercise(rangkingExercise);
		exercise.setCalorieMax("600"); // 고정
		
		
		// ****************** 추후작업
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
		String averageCnt = "";
		String user = "";
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
			map2.put("bmi_status", bodytype);

			// 저체중
			
			// 정상
			
			// 과체중
			
			// 비만
			
			// 중도비만
			
			// 고도비만
			
			
			Activity avg = exerciseService.getAverage(map2);
			
			
			
			if(averageType.equals("calorie")) {
				user = activity.getCalorie();
				
			} else if(averageType.equals("step")) {
				user = activity.getSteps();
				
			} else if(averageType.equals("distance")) {
				user = activity.getDistance();
				
			}
			
			averageCnt = avg.getCnt();
		}
		
		
		exerciseTab.setBodyType(bodytype);
		exerciseTab.setAverageCnt(averageCnt);
		
		exerciseTab.setUser(user);
		exerciseTab.setAll("238");
		exerciseTab.setBodyType1("215");
		exerciseTab.setBodyType2("265");
		exerciseTab.setBodyType3("270");
		exerciseTab.setBodyType4("200");
		exerciseTab.setBodyType5("222");
		exerciseTab.setBodyType6("256");
		

		exerciseTab.setBodyType1Max("315");
		exerciseTab.setBodyType2Max("319");
		exerciseTab.setBodyType3Max("320");
		exerciseTab.setBodyType4Max("335");
		exerciseTab.setBodyType5Max("350");
		exerciseTab.setBodyType6Max("310");
		
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
