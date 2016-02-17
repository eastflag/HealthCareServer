package com.healthcare.biz.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

	@Override
	public int insertSimliStudentResult(Map<String, Object> map) throws SQLException {
		return simliMapper.insertSimliStudentResult(map);
	}

	@Override
	public int updateSimliStudentResult(Map<String, Object> map) throws SQLException {
		return simliMapper.updateSimliStudentResult(map);
	}

	@Override
	public void deleteSimliStudentResult(Map<String, Object> map) throws SQLException {
		simliMapper.deleteSimliStudentResult(map);
	}

	@Override
	public int getSimliResultQuestCnt(Map<String, Object> map) throws SQLException {
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



}
