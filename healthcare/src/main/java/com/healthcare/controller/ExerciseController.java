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
		logger.debug(vo.toString());
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
		if(activity!=null) {
			//요일 가져오기
			acitivityDate = activity.getReg_datetime();
			if(acitivityDate!=null && !acitivityDate.equals("")) {
				acitivityDate = acitivityDate.substring(0,10);
			}
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
		    acitivityDate.replaceAll("-", ".");
		    
		    // 값 넣기
		    exerciseId = activity.getRegist_id();
		    exerciseIdPrev = activity.getRegist_id_prev();
		    exerciseIdNext = activity.getRegist_id_next();
		    exerciseName = activity.getSports_name();
		    exerciseImg = contentsURL+activity.getLarge_image_path();
		    calorie = activity.getCalorie();
		    step = activity.getSteps();
		    distance = activity.getDistance();
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
		
		
		// ****************** 추후작업
		exercise.setRangkingClass("3");
		exercise.setRangkingGrade("50");
		exercise.setRangkingExercise("250");
		exercise.setCalorieAverage("260");
		exercise.setCalorieMax("300");

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
		
		// 운동 차트 가져오기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("exerciseId", exerciseId);
		List<Activity> activityChart = exerciseService.getStudentActivityChart(map);
		if(activityChart!=null && activityChart.size()>0) {
			logger.debug("-------------activityChart not null!!!!");
			logger.debug("----------size:"+activityChart.size());
			for(int i=activityChart.size()-1; i>=0; i--) {
				logger.debug("-------------------i:"+i);
				exercise = new Exercise();
				exercise.setDate(activityChart.get(i).getReg_datetime());
				exercise.setExerciseIdName(activityChart.get(i).getSports_name());
				exercise.setCalorie(activityChart.get(i).getCalorie());
				exercise.setStep(activityChart.get(i).getSteps());
				exercise.setDistance(activityChart.get(i).getDistance());
				chart.add(exercise);
			}
		}
		exerciseView.setChart(chart);
		
		
		
		
		exerciseView.setCalorie("240");
		exerciseView.setStep("600");
		exerciseView.setDistance("5.05");
		exerciseView.setSpeed("66");
		exerciseView.setBodyType(bodytype);
		exerciseView.setCalorieMax("500");
		exerciseView.setStepMax("900");
		exerciseView.setSpeedMax("60");
		exerciseView.setDistanceMax("70.05");

		
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
		
		exerciseTab.setAverageType("calorie");
		exerciseTab.setGroupType("class");
		exerciseTab.setAverageCnt("30");
		
		exerciseTab.setUser("240");
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
			@RequestParam(value="userId") String userId) throws Exception {//, required=false
		logger.debug("/exercise/history:"+userId);

		ExerciseHistory history = new ExerciseHistory();
		List<Exercise> list = new ArrayList<Exercise>();
		Exercise exercise = new Exercise();

		exercise.setExerciseId("20160310123");
		exercise.setDate("2016.03.10");
		exercise.setExercise("축구");
		exercise.setImg(contentsURL+"soccer.png");
		exercise.setTime("50");
		exercise.setCalorie("450");
		exercise.setStep("650");
		exercise.setDistance("5.05");
		list.add(exercise);

		exercise.setExerciseId("20160310123");
		exercise.setDate("2016.03.03");
		exercise.setExercise("농구");
		exercise.setImg(contentsURL+"soccer.png");
		exercise.setTime("50");
		exercise.setCalorie("550");
		exercise.setStep("650");
		exercise.setDistance("5.05");
		list.add(exercise);

		exercise.setExerciseId("20160310123");
		exercise.setDate("2016.03.02");
		exercise.setExercise("배구");
		exercise.setImg(contentsURL+"soccer.png");
		exercise.setTime("50");
		exercise.setCalorie("650");
		exercise.setStep("650");
		exercise.setDistance("5.05");
		list.add(exercise);

		exercise.setExerciseId("20160310123");
		exercise.setDate("2016.02.26");
		exercise.setExercise("축구");
		exercise.setImg(contentsURL+"soccer.png");
		exercise.setTime("50");
		exercise.setCalorie("250");
		exercise.setStep("650");
		exercise.setDistance("5.05");
		list.add(exercise);

		exercise.setExerciseId("20160310123");
		exercise.setDate("2016.02.23");
		exercise.setExercise("축구");
		exercise.setImg(contentsURL+"soccer.png");
		exercise.setTime("50");
		exercise.setCalorie("350");
		exercise.setStep("650");
		exercise.setDistance("5.05");
		list.add(exercise);

		history.setHistory(list);
		history.setNextYN("Y");
		
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(JSONArray.fromObject(history).toString());
	}
}
