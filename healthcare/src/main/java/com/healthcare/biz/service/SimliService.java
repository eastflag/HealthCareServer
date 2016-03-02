package com.healthcare.biz.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.healthcare.biz.mybatis.domain.SimliAnswer;
import com.healthcare.biz.mybatis.domain.SimliQuestion;
import com.healthcare.biz.mybatis.domain.SimliResult;
import com.healthcare.biz.mybatis.domain.SimliReview;
import com.healthcare.biz.mybatis.domain.SimliReviewAnswer;
import com.healthcare.biz.mybatis.domain.SimliType;


public interface SimliService {

	List<SimliType> getSimliTypeList(int grade) throws SQLException;

	List<SimliQuestion> getSimliQuestionList(String simliId) throws SQLException;

	List<SimliAnswer> getSimliAnswer(String questId) throws SQLException;

	int getSimliResultCnt(Map<String, Object> map) throws SQLException;

	int getSimliResultScore(Map<String, Object> map) throws SQLException;

	SimliResult getSimliResult(Map<String, Object> map) throws SQLException;

	List<SimliReview> getSimliQuestionListReview(String simliId) throws SQLException;

	List<SimliReviewAnswer> getSimliAnswerReview(Map<String, Object> map) throws SQLException;

	int saveSimliResult(String[] answerList, String userId);

}
