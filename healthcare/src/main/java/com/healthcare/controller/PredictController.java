package com.healthcare.controller;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.healthcare.bean.Result;
import com.healthcare.biz.mybatis.domain.InbodyInfoVO;
import com.healthcare.biz.mybatis.domain.PredictVO;
import com.healthcare.biz.mybatis.persistence.PredictMapper;
import com.healthcare.common.AES256Util;

@Controller
public class PredictController {
	@Autowired
	private PredictMapper predictMapper;
	
	@RequestMapping("/getPredict")
	public @ResponseBody Result<?> getPredict(@RequestParam int student_id) {
		Result<PredictVO> result = new Result<PredictVO>();
		
		try {
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
			
			result.setResult("0");
			result.setValue(predict);
			
			return result;
		} catch (IOException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			result.setResult("500");
			result.setErrMsg("internal Error");
			
			return result;
		}
	}
}
