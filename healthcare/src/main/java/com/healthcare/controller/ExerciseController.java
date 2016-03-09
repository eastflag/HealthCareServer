package com.healthcare.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healthcare.biz.mybatis.domain.Exercise;
import com.healthcare.biz.mybatis.domain.ExerciseHistory;
import com.healthcare.biz.mybatis.domain.ExerciseMain;
import com.healthcare.biz.mybatis.domain.ExerciseTab;
import com.healthcare.biz.mybatis.domain.ExerciseView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class ExerciseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExerciseController.class);
	
	//@Autowired
	//private ExerciseService ExerciseService;

	String contentsURL = "http://210.127.55.205/exercise_contents/";
	
	/*
	 * 메인화면
	 * */
	@RequestMapping("/exercise/main")
	public void exerciseMain(HttpServletResponse response,
			@RequestParam(value="userId") String userId,
			@RequestParam(value="exerciseId") String exerciseId) throws Exception {//, required=false
		logger.debug("/exercise/main:"+userId);
		
		ExerciseMain exercise = new ExerciseMain();

		exercise.setExerciseId("201603101234");
		exercise.setExerciseIdPrev("201603091234");
		exercise.setExerciseIdNext("");
		exercise.setExerciseDate("2016.03.10(목)");
		exercise.setExerciseName("축구");
		exercise.setExerciseImg(contentsURL+"soccer.png");
		exercise.setCalorie(240);
		exercise.setStep(600);
		exercise.setDistance((float) 5.05);
		exercise.setBodyType("정상");
		exercise.setRangkingClass(3);
		exercise.setRangkingGrade(50);
		exercise.setRangkingExercise(250);
		exercise.setCalorieAverage(260);
		exercise.setCalorieMax(300);
		
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
		List<Exercise> cart = new ArrayList<Exercise>();
		Exercise exercise = new Exercise();
		
		
		exercise.setDate("2/21");
		exercise.setExercise("축구");
		exercise.setCalorie(280);
		exercise.setStep(600);
		exercise.setDistance((float) 5.05);
		cart.add(exercise);

		exercise.setDate("2/28");
		exercise.setExercise("농구");
		exercise.setCalorie(300);
		exercise.setStep(700);
		exercise.setDistance((float) 6.05);
		cart.add(exercise);

		exercise.setDate("3/2");
		exercise.setExercise("배구");
		exercise.setCalorie(250);
		exercise.setStep(650);
		exercise.setDistance((float) 4.05);
		cart.add(exercise);

		exercise.setDate("3/10");
		exercise.setExercise("축구");
		exercise.setCalorie(450);
		exercise.setStep(650);
		exercise.setDistance((float) 5.05);
		cart.add(exercise);
		
		exerciseView.setCart(cart);
		
		exerciseView.setCalorie(240);
		exerciseView.setStep(600);
		exerciseView.setDistance((float) 5.05);
		exerciseView.setSpeed(66);
		exerciseView.setBodyType("정상");
		exerciseView.setCalorieMax(500);
		exerciseView.setStepMax(900);
		exerciseView.setSpeedMax(60);
		exerciseView.setDistanceMax((float) 70.05);
		
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
		exerciseTab.setAverageCnt(30);
		
		exerciseTab.setUser(240);
		exerciseTab.setAll(238);
		exerciseTab.setBodyType1(215);
		exerciseTab.setBodyType2(265);
		exerciseTab.setBodyType3(270);
		exerciseTab.setBodyType4(200);
		exerciseTab.setBodyType5(222);
		exerciseTab.setBodyType6(256);
		

		exerciseTab.setBodyType1Max(315);
		exerciseTab.setBodyType2Max(319);
		exerciseTab.setBodyType3Max(320);
		exerciseTab.setBodyType4Max(335);
		exerciseTab.setBodyType5Max(350);
		exerciseTab.setBodyType6Max(310);
		
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
		exercise.setTime(50);
		exercise.setCalorie(450);
		exercise.setStep(650);
		exercise.setDistance((float) 5.05);
		list.add(exercise);

		exercise.setExerciseId("20160310123");
		exercise.setDate("2016.03.03");
		exercise.setExercise("농구");
		exercise.setImg(contentsURL+"soccer.png");
		exercise.setTime(50);
		exercise.setCalorie(550);
		exercise.setStep(650);
		exercise.setDistance((float) 5.05);
		list.add(exercise);

		exercise.setExerciseId("20160310123");
		exercise.setDate("2016.03.02");
		exercise.setExercise("배구");
		exercise.setImg(contentsURL+"soccer.png");
		exercise.setTime(50);
		exercise.setCalorie(650);
		exercise.setStep(650);
		exercise.setDistance((float) 5.05);
		list.add(exercise);

		exercise.setExerciseId("20160310123");
		exercise.setDate("2016.02.26");
		exercise.setExercise("축구");
		exercise.setImg(contentsURL+"soccer.png");
		exercise.setTime(50);
		exercise.setCalorie(250);
		exercise.setStep(650);
		exercise.setDistance((float) 5.05);
		list.add(exercise);

		exercise.setExerciseId("20160310123");
		exercise.setDate("2016.02.23");
		exercise.setExercise("축구");
		exercise.setImg(contentsURL+"soccer.png");
		exercise.setTime(50);
		exercise.setCalorie(350);
		exercise.setStep(650);
		exercise.setDistance((float) 5.05);
		list.add(exercise);

		history.setHistory(list);
		history.setNextYN("Y");
		
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().write(JSONArray.fromObject(history).toString());
	}
}
