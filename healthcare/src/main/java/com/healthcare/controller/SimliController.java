package com.healthcare.controller;

import java.util.Calendar;
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

import com.healthcare.biz.mybatis.domain.SimliAnswer;
import com.healthcare.biz.mybatis.domain.SimliQuestion;
import com.healthcare.biz.mybatis.domain.SimliResult;
import com.healthcare.biz.mybatis.domain.SimliReview;
import com.healthcare.biz.mybatis.domain.SimliReviewAnswer;
import com.healthcare.biz.mybatis.domain.SimliType;
import com.healthcare.biz.mybatis.domain.Student;
import com.healthcare.biz.service.SimliService;
import com.healthcare.biz.service.StudentService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SimliController {
	
	private static final Logger logger = LoggerFactory.getLogger(SimliController.class);

	@Autowired
	private SimliService simliService;
	@Autowired
	private StudentService studentService;

	/*
	 * 심리 목록 가져오기
	 * */
	@RequestMapping("/simli/type_list")
	public void getSimliTypeList(HttpServletResponse response,
			@RequestParam(value="userId") String userId) throws Exception {//, required=false
		logger.debug("/simli/list userId:"+userId);
		
		// 현재 연도 가져오기
		Calendar cal = Calendar.getInstance();
		int year = cal.get(cal.YEAR);
		
		// 테스트 (2016데이터가 없어서)
		year = 2015;
		
		// 학생 학년 가져오기
		Student studentInfo = studentService.getStudentByUserId(userId);
		int grade = Integer.parseInt(studentInfo.getSchoolGradeId());
		
		// 학년에 맞는 심리검사 목록 가져오기
		List<SimliType> simliList = simliService.getSimliTypeList(grade);

		response.setContentType("charset=UTF-8");
		response.getWriter().write(JSONArray.fromObject(simliList).toString());
	}

	/*
	 * 심리 문제 가져오기
	 * */
	@RequestMapping("/simli/qna_list")
	public void getSimliQnAList(HttpServletResponse response,
			@RequestParam(value="simliId") String simliId) throws Exception {//, required=false
		logger.debug("/simli/qna_list:"+simliId);
		
		// 심리검사 문제 가져오기
		List<SimliQuestion> questionList = simliService.getSimliQuestionList(simliId);
		
		// 가져온 심리검사 목록에 답변 목록 가져와 넣기
		if(questionList!=null && questionList.size()>0) {
			for(int i=0; i<questionList.size(); i++) {
				// 문제 ID를 가져온다
				String questId = questionList.get(i).getQuestId();
				// 해당 문제의 답변을 가져온다
				List<SimliAnswer> answerList = simliService.getSimliAnswer(questId);
				// 답변을 qna 리스트에 넣는다
				questionList.get(i).setAnswer(answerList);
			}
		}

		response.setContentType("charset=UTF-8");
		response.getWriter().write(JSONArray.fromObject(questionList).toString());
	}
	

	/*
	 * 심리 결과 등록하기
	 * */
	@RequestMapping("/simli/insert_simli_result")
	public void insertSimliResult(HttpServletResponse response,
			@RequestParam(value="userId") String userId,
			@RequestParam(value="answer") String answer) throws Exception {//, required=false
		logger.debug("/simli/insert_simli_result: userId"+userId);
		
		int result = 0;
		
		// 답변 등록하기
		if(answer!=null && !answer.equals("")) {
			String[] answerList = answer.split(",");
			int sum=0;

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			
			for(int i=0; i<answerList.length; i++) {
				String simliId = answerList[i].substring(0, 6);
				String questId = answerList[i].substring(0, 9);

				map.put("simliId", simliId);
				map.put("questId", questId);
				map.put("answerId", answerList[i]);
				int cnt = simliService.getSimliResultQuestCnt(map);
				
				if(cnt==0) {
					int insert = simliService.insertSimliStudentResult(map);
					if(insert==0) {
						sum++;
					}
				} else {
					int update = simliService.updateSimliStudentResult(map);
					if(update==0) {
						sum++;
					}
				}
			}
			if(sum>0) {
				simliService.deleteSimliStudentResult(map);
				result = 1; // 실패
			}
		}
		

		response.setContentType("charset=UTF-8");
		response.getWriter().write(Integer.toString(result));
	}

	/*
	 * 심리검사 완료 체크 (심리 문제 하위 메뉴) 
	 * */
	@RequestMapping("/simli/simli_result_check")
	public void getSimliResultcheck(HttpServletResponse response,
			@RequestParam(value="userId") String userId,
			@RequestParam(value="simliId") String simliId) throws Exception {//, required=false
		logger.debug("/simli/simli_sub:"+simliId);
		

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("simliId", simliId);
		
		// 심리검사 완료 여부 
		int cnt = simliService.getSimliResultCnt(map);
		
		String result = "X";
		if(cnt>0) {
			result = "O";
		}

		// 결과 O:심리검사 완료, X:심리검사 안 함 
		response.setContentType("charset=UTF-8");
		response.getWriter().write(result);
	}

	/*
	 * 심리검사 결과 보기
	 * */
	@RequestMapping("/simli/simli_result")
	public void getSimliResult(HttpServletResponse response,
			@RequestParam(value="userId") String userId,
			@RequestParam(value="simliId") String simliId) throws Exception {//, required=false
		logger.debug("/simli/simli_sub:"+simliId);
		

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("simliId", simliId);
		
		// 심리검사 결과
		int score = simliService.getSimliResultScore(map);
		
		map.put("score", score);
		
		SimliResult result = simliService.getSimliResult(map);

		// 결과 O:심리검사 완료, X:심리검사 안 함 
		response.setContentType("charset=UTF-8");
		response.getWriter().write(JSONObject.fromObject(result).toString());
	}

	/*
	 * 심리검사 다시 보기
	 * */
	@RequestMapping("/simli/simli_review")
	public void getSimliReview(HttpServletResponse response,
			@RequestParam(value="userId") String userId,
			@RequestParam(value="simliId") String simliId) throws Exception {//, required=false
		logger.debug("/simli/simli_sub:"+simliId);
		

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("simliId", simliId);

		// 심리검사 문제 가져오기
		List<SimliReview> SimliReview = simliService.getSimliQuestionListReview(simliId);
		
		// 가져온 심리검사 목록에 답변 목록 가져와 넣기
		if(SimliReview!=null && SimliReview.size()>0) {
			for(int i=0; i<SimliReview.size(); i++) {
				// 문제 ID를 가져온다
				String questId = SimliReview.get(i).getQuestId();
				map.put("questId", questId);
				// 해당 문제의 답변을 가져온다
				List<SimliReviewAnswer> answerList = simliService.getSimliAnswerReview(map);
				// 답변을 qna 리스트에 넣는다
				SimliReview.get(i).setAnswer(answerList);
			}
		}		
		
		
		response.setContentType("charset=UTF-8");
		response.getWriter().write(JSONArray.fromObject(SimliReview).toString());
	}
	
	
}
