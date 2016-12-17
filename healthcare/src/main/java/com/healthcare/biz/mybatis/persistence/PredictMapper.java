package com.healthcare.biz.mybatis.persistence;

import java.util.List;

import com.healthcare.biz.mybatis.domain.CharacterHealthVO;
import com.healthcare.biz.mybatis.domain.HealthDescriptionVO;
import com.healthcare.biz.mybatis.domain.InbodyInfoVO;
import com.healthcare.biz.mybatis.domain.PredictVO;

public interface PredictMapper {
	
	PredictVO getStudent(int student_id);

	List<InbodyInfoVO> getMeasureData(int student_id);
	
	InbodyInfoVO getAverage(InbodyInfoVO inInbody);
	
	int getHeightRank(InbodyInfoVO inbody);
	
	int getWeightRank(InbodyInfoVO inbody);
	
	InbodyInfoVO getBMI(InbodyInfoVO inInbody);
	
	HealthDescriptionVO getCharacterHealth(PredictVO predict);
	
	CharacterHealthVO getCharacter(CharacterHealthVO health);
	
	HealthDescriptionVO getLunchCalorie(PredictVO predict);
}
