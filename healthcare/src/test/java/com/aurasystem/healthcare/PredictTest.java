package com.aurasystem.healthcare;

import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.jasper.runtime.ServletResponseWrapperInclude;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.healthcare.biz.mybatis.domain.BodyMeasureGrade;
import com.healthcare.biz.mybatis.domain.CharacterHealthVO;
import com.healthcare.biz.mybatis.domain.HealthDescriptionVO;
import com.healthcare.biz.mybatis.domain.InbodyInfoVO;
import com.healthcare.biz.mybatis.domain.PredictVO;
import com.healthcare.biz.mybatis.persistence.BodyMeasureMapper;
import com.healthcare.biz.mybatis.persistence.PredictMapper;
import com.healthcare.common.AES256Util;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./src/main/webapp/WEB-INF/spring/root-context.xml"})
public class PredictTest {
	@Autowired
	private PredictMapper predictMapper;
	@Autowired
	private BodyMeasureMapper bodyMeasureMapper;
	
	//@Ignore
	@Test
	public void getStudent() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		int student_id = 188;
		AES256Util aes = new AES256Util();
		PredictVO predict = predictMapper.getStudent(student_id);
		predict.setStudent_name(aes.decode(predict.getStudent_name()));
		predict.setSchool_name(aes.decode(predict.getSchool_name()));
		
		List<InbodyInfoVO> measureList = predictMapper.getMeasureData(student_id);
		
		//최근 데이터 세팅
		if(measureList != null) {
			//학교 이름 디코딩
			measureList.get(0).setSchool_name(aes.decode(measureList.get(0).getSchool_name()));
			
			measureList.get(0).setSex(predict.getSex());
			//평균 구하기
			InbodyInfoVO inInbody = predictMapper.getAverage(measureList.get(0));
			measureList.get(0).setHeightAverageOfSchool(inInbody.getHeightAverageOfSchool());
			measureList.get(0).setWeightAverageOfSchool(inInbody.getWeightAverageOfSchool());
			measureList.get(0).setTotalNumberOfSchool(inInbody.getTotalNumberOfSchool());
			//랭킹 구하기
			int heightRank = predictMapper.getHeightRank(measureList.get(0));
			int weightRank = predictMapper.getWeightRank(measureList.get(0));
			measureList.get(0).setHeightSchoolRank(heightRank);
			measureList.get(0).setWeightSchoolRank(weightRank);
			//BMI 구하기
			inInbody = predictMapper.getBMI(measureList.get(0));
			measureList.get(0).setStart_int(inInbody.getStart_int());
			measureList.get(0).setEnd_int(inInbody.getEnd_int());
			measureList.get(0).setBodyForm(inInbody.getBodyForm());
			
			predict.setLastMeasure(measureList.get(0));
		}
		
		//직전 데이터 세팅
		if(measureList != null && measureList.size() == 2) {
			//학교 이름 디코딩
			measureList.get(1).setSchool_name(aes.decode(measureList.get(1).getSchool_name()));
			
			measureList.get(1).setSex(predict.getSex());
			//평균 구하기
			InbodyInfoVO inInbody = predictMapper.getAverage(measureList.get(1));
			measureList.get(1).setHeightAverageOfSchool(inInbody.getHeightAverageOfSchool());
			measureList.get(1).setWeightAverageOfSchool(inInbody.getWeightAverageOfSchool());
			measureList.get(1).setTotalNumberOfSchool(inInbody.getTotalNumberOfSchool());
			//랭킹 구하기
			int heightRank = predictMapper.getHeightRank(measureList.get(1));
			int weightRank = predictMapper.getWeightRank(measureList.get(1));
			measureList.get(1).setHeightSchoolRank(heightRank);
			measureList.get(1).setWeightSchoolRank(weightRank);
			
			//BMI 구하기
			inInbody = predictMapper.getBMI(measureList.get(1));
			measureList.get(1).setStart_int(inInbody.getStart_int());
			measureList.get(1).setEnd_int(inInbody.getEnd_int());
			measureList.get(1).setBodyForm(inInbody.getBodyForm());
			
			predict.setBeforeMeasure(measureList.get(1));
		}
		
		//8가지 데이터 구하기
		if(measureList != null) {
			HealthDescriptionVO descriptionVO = new HealthDescriptionVO();
			
			//6가지 데이터 구하기
			descriptionVO = predictMapper.getCharacterHealth(predict);
			
			//Height desc 구하기
			BodyMeasureGrade grade = new BodyMeasureGrade();
			grade.setSchoolGradeId(String.valueOf(predict.getGrade_id()));
			grade.setSex(predict.getSex());
			grade.setSection("HEIGHT");
			grade.setYear("2012");
			grade.setValue(String.valueOf(measureList.get(0).getHeight()));
			BodyMeasureGrade heightGrade = bodyMeasureMapper.getGradeBySection(grade); 
			//heightGrade.getGradeDesc(); //표준이하, 표준, 표준이상
			
			//dc_1, dc_2 구하기
			CharacterHealthVO health = new CharacterHealthVO();
			health.setGrade_id(predict.getGrade_id());
			health.setHeight(heightGrade.getGradeDesc());
			health.setBmi(measureList.get(0).getBodyForm());
			CharacterHealthVO outHealth = predictMapper.getCharacter(health);
			
			//최종 평가 구하기
			descriptionVO.setBodyTotalEvaluation(outHealth.getDc_1() + " " + outHealth.getDc_2());
			
			//점심식단 구하기
			HealthDescriptionVO calory = predictMapper.getLunchCalorie(predict);
			if(calory != null) {
				descriptionVO.setSchoolCalorie(calory.getSchoolCalorie());
			} else {
				descriptionVO.setSchoolCalorie(800);
			}
			
			predict.setDescription(descriptionVO);
		}
		
		//객체를 json으로 변환
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new FileWriter("c:\\test\\test.json"), predict);
/*		JSONObject json = new JSONObject();
		
		try {

			FileWriter file = new FileWriter("c:\\test\\test.json");
			file.write(json.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

}
