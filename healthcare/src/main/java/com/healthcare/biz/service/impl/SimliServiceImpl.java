package com.healthcare.biz.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.biz.mybatis.domain.SimliAnswer;
import com.healthcare.biz.mybatis.domain.SimliQuestion;
import com.healthcare.biz.mybatis.domain.SimliResult;
import com.healthcare.biz.mybatis.domain.SimliReview;
import com.healthcare.biz.mybatis.domain.SimliReviewAnswer;
import com.healthcare.biz.mybatis.domain.SimliType;
import com.healthcare.biz.mybatis.persistence.SimliMapper;
import com.healthcare.biz.service.SimliService;


@Service("simliType")
public class SimliServiceImpl  implements SimliService{

	private static final Logger logger = LoggerFactory.getLogger(SimliServiceImpl.class);
	
	@Autowired
	SimliMapper simliMapper;

	@Override
	public List<SimliType> getSimliTypeList(int grade) throws SQLException {
		return simliMapper.getSimliTypeList(grade);
	}

	@Override
	public List<SimliQuestion> getSimliQuestionList(String simliId) throws SQLException {
		return simliMapper.getSimliQuestionList(simliId);
	}

	@Override
	public List<SimliAnswer> getSimliAnswer(String questId) throws SQLException {
		return simliMapper.getSimliAnswer(questId);
	}

	int insertSimliStudentResult(Map<String, Object> map) throws SQLException {
		return simliMapper.insertSimliStudentResult(map);
	}

	int updateSimliStudentResult(Map<String, Object> map) throws SQLException {
		return simliMapper.updateSimliStudentResult(map);
	}

	void deleteSimliStudentResult(Map<String, Object> map) throws SQLException {
		simliMapper.deleteSimliStudentResult(map);
	}

	int getSimliResultQuestCnt(Map<String, Object> map) throws SQLException {
		return simliMapper.getSimliResultQuestCnt(map);
	}

	@Override
	public int getSimliResultCnt(Map<String, Object> map) throws SQLException {
		return simliMapper.getSimliResultCnt(map);
	}

	@Override
	public int getSimliResultScore(Map<String, Object> map) throws SQLException {
		return simliMapper.getSimliResultScore(map);
	}

	@Override
	public SimliResult getSimliResult(Map<String, Object> map) throws SQLException {
		return simliMapper.getSimliResult(map);
	}

	@Override
	public List<SimliReview> getSimliQuestionListReview(String simliId) throws SQLException {
		return simliMapper.getSimliQuestionListReview(simliId);
	}

	@Override
	public List<SimliReviewAnswer> getSimliAnswerReview(Map<String, Object> map) throws SQLException {
		return simliMapper.getSimliAnswerReview(map);
	}

	@Override
	public int saveSimliResult(String[] answerList, String userId) {
		int result = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);

		int questCnt = 0; // 문제 수
		int resultSum=0; // 실패여부
		int questSum = 0; // 등록한 답변 수

		try {
			for(int i=0; i<answerList.length; i++) {
				String simliId = answerList[i].substring(0, 6);
				String questId = answerList[i].substring(0, 9);
				questCnt = questionCnt(simliId);
						
				map.put("simliId", simliId);
				map.put("questId", questId);
				map.put("answerId", answerList[i]);
				int cnt = getSimliResultQuestCnt(map);
				
				if(cnt==0) {
					int insert = insertSimliStudentResult(map);
					if(insert==0) { // 실패
						resultSum++;
					} else {
						questSum++;
					}
				} else {
					int update = updateSimliStudentResult(map);
					if(update==0) { // 실패
						resultSum++;
					} else {
						questSum++;
					}
				}
			}

			// 실패한 경우가 있거나 등록된 문제수보다 등록한 답변수가 적으면 실패
			if(resultSum>0 ) {// || questCnt>questSum
				deleteSimliStudentResult(map);
				result = 1; // 실패
			}
		} catch (SQLException e) {
			result = 1;
			e.printStackTrace();
		}
		
		return result;
	}

	private int questionCnt(String simliId) throws SQLException {
		return simliMapper.questionCnt(simliId);
	}




}
