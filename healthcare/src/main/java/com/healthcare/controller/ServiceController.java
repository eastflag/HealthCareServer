package com.healthcare.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.healthcare.bean.Bmi;
import com.healthcare.bean.Growth;
import com.healthcare.bean.MeasureHistory;
import com.healthcare.bean.MeasureItem;
import com.healthcare.bean.RequestVo;
import com.healthcare.bean.Result;
import com.healthcare.bean.Smoke;
import com.healthcare.biz.common.ReturnCode;
import com.healthcare.biz.mybatis.domain.AverageItem;
import com.healthcare.biz.mybatis.domain.BodyMeasureGrade;
import com.healthcare.biz.mybatis.domain.BodyMeasureSummary;
import com.healthcare.biz.mybatis.domain.Student;
import com.healthcare.biz.service.BodyMeasureService;
import com.healthcare.biz.service.StudentService;

@Controller
public class ServiceController {

	private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);
	
	@Autowired
	private BodyMeasureService bodyMeasureService;
	@Autowired
	private StudentService studentService;
	
	
	@RequestMapping(value = "/GetRestApiList", method = {RequestMethod.GET, RequestMethod.POST})
	public String GetRestApiList(Model model) {
		logger.info("/GetRestApiList");
		
		
		return "restApiList";
	}
	
	// 
	
	@RequestMapping(value = "/GetBodyMeasureSummary", method = {RequestMethod.POST})
	public @ResponseBody Result<?> GetBodyMeasureSummary(@RequestBody RequestVo requestVo) {

		Result<BodyMeasureSummary> result = new Result<>();
		
		logger.info("/GetBodyMeasureSummary : " +  requestVo);
		
		BodyMeasureSummary vo = bodyMeasureService.getSummary(requestVo.getUserId());
		
		if (vo != null) {
			result.setValue(vo);
			result.setResult(ReturnCode.succeeded);
		} else  {
			result.setResult(ReturnCode.notExistMeasureInfo);
		}
		
		return result;
	}
	
	
	
	@RequestMapping(value = "/GetHeight", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetHeight(@RequestBody RequestVo	requstVo) {
		logger.info("/GetHeight : RequestVo" + requstVo);
		Result<MeasureItem> result = new Result<>();
		
		MeasureItem vo = new MeasureItem();
		
		BodyMeasureGrade bodyMeasureGrade = bodyMeasureService.getHeightMeasureGrade(requstVo.getUserId());
		if (bodyMeasureGrade != null) {
			vo.setValue(bodyMeasureGrade.getValue());
			vo.setBeforeValue(bodyMeasureGrade.getBeforeValue());
			vo.setGradeId(bodyMeasureGrade.getGradeId());
			vo.setGradeString(bodyMeasureGrade.getGradeDesc());
			vo.setSchoolGrade(bodyMeasureGrade.getSchoolGrade());
			vo.setBeforeSchoolGrade(bodyMeasureGrade.getBeforeSchoolGrade());
			vo.setTotalNumberOfStudent(bodyMeasureGrade.getTotalNumberOfStudent());
			vo.setAverageOfClass(bodyMeasureGrade.getAverageOfClass());
			vo.setAverageOfSchool(bodyMeasureGrade.getAverageOfSchool());
			System.out.println(" ::: bodyMeasureGrade getAverageOfSchool :::=> " + bodyMeasureGrade.getAverageOfSchool());
			vo.setAverageOfLocal(bodyMeasureGrade.getAverageOfLocal());
			System.out.println(" ::: bodyMeasureGrade getAverageOfLocal :::=> " + bodyMeasureGrade.getAverageOfLocal());
			vo.setAverageOfNation(bodyMeasureGrade.getAverageOfNation());
			System.out.println(" ::: bodyMeasureGrade  getAverageOfNation :::=> " + bodyMeasureGrade.getAverageOfNation());
		}
		
		
		result.setValue(vo);
		result.setResult(ReturnCode.succeeded);
		
		return result;
	}

	@RequestMapping(value = "/GetWeight", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetWeight(@RequestBody RequestVo	requstVo) {
		logger.info("/GetWeight : RequestVo" + requstVo);

		Result<MeasureItem> result = new Result<>();
		MeasureItem vo = new MeasureItem();
		
		BodyMeasureGrade bodyMeasureGrade = bodyMeasureService.getWeightMeasureGrade(requstVo.getUserId());
		if (bodyMeasureGrade != null) {
			vo.setValue(bodyMeasureGrade.getValue());
			vo.setBeforeValue(bodyMeasureGrade.getBeforeValue());
			vo.setGradeId(bodyMeasureGrade.getGradeId());
			vo.setGradeString(bodyMeasureGrade.getGradeDesc());
			vo.setSchoolGrade(bodyMeasureGrade.getSchoolGrade());
			vo.setBeforeSchoolGrade(bodyMeasureGrade.getBeforeSchoolGrade());
			vo.setTotalNumberOfStudent(bodyMeasureGrade.getTotalNumberOfStudent());
			vo.setAverageOfClass(bodyMeasureGrade.getAverageOfClass());
			vo.setAverageOfSchool(bodyMeasureGrade.getAverageOfSchool());
			vo.setAverageOfLocal(bodyMeasureGrade.getAverageOfLocal());
			vo.setAverageOfNation(bodyMeasureGrade.getAverageOfNation());
		}
		
		result.setValue(vo);		
		result.setResult(ReturnCode.succeeded);
		
		return result;
	}
	
	@RequestMapping(value = "/GetHeightHistory", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetHeightHistory(@RequestBody RequestVo	requstVo) {
		logger.info("/GetHeightHistory : RequestVo" + requstVo);

		Result<MeasureHistory> result = new Result<>();
		
		MeasureHistory vo = bodyMeasureService.getHeightMeasureHistory(requstVo.getUserId(), "4");
				
		System.out.println(" == 측정이력 ==> " + vo.toString());
		result.setValue(vo);
		
		result.setResult(ReturnCode.succeeded);
		
		return result;
	}
	
	/**
	 * 연간 측정 이력 조회
	 * @param requstVo
	 * @return
	 */
	@RequestMapping(value = "/GetHeightHistoryYear", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetHeightHistoryYear(@RequestBody RequestVo	requstVo) {
		logger.info("/GetHeightHistoryYear : RequestVo" + requstVo);

		Result<MeasureHistory> result = new Result<>();
		
		MeasureHistory vo = bodyMeasureService.getHeightMeasureHistoryYear(requstVo.getUserId(), requstVo.getSelYear());
				
	
		result.setValue(vo);
		
		result.setResult(ReturnCode.succeeded);
		
		return result;
	}
	
	
	@RequestMapping(value = "/GetWeightHistory", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetWeightHistory(@RequestBody RequestVo	requstVo) {
		logger.info("/GetWeightHistory : RequestVo" + requstVo);
		
		Result<MeasureHistory> result = new Result<>();
		
		MeasureHistory vo = bodyMeasureService.getWeightMeasureHistory(requstVo.getUserId(), "4");
				
		result.setValue(vo);
		
		result.setResult(ReturnCode.succeeded);
		
		return result;
	}

	/**
	 * 연간 측정 이력 조회
	 * @param requstVo
	 * @return
	 */
	@RequestMapping(value = "/GetWeightHistoryYear", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetWeightHistoryYear(@RequestBody RequestVo	requstVo) {
		logger.info("/GetWeightHistoryYear : RequestVo" + requstVo);
		
		Result<MeasureHistory> result = new Result<>();
		
		MeasureHistory vo = bodyMeasureService.getWeightMeasureHistoryYear(requstVo.getUserId(), requstVo.getSelYear());
				
		result.setValue(vo);
		
		result.setResult(ReturnCode.succeeded);
		
		return result;
	}
	
	@RequestMapping(value = "/GetBmi", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetBmi(@RequestBody RequestVo	requstVo) {
		
		logger.info("/GetBmi : RequestVo" + requstVo);
				
		Result<Bmi> result = new Result<>();
		
		Bmi vo = bodyMeasureService.getBmi(requstVo.getUserId());

		result.setValue(vo);
		result.setResult(ReturnCode.succeeded);
		
		return result;
	}

	/**
	 * 연간 측정 이력 조회
	 * @param requstVo
	 * @return
	 */
	@RequestMapping(value = "/GetBmiHistoryYear", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetBmiHistoryYear(@RequestBody RequestVo	requstVo) {
		logger.info("/GetBmiHistoryYear : RequestVo" + requstVo);
		
		Result<MeasureHistory> result = new Result<>();

		MeasureHistory vo = bodyMeasureService.getBmiMeasureHistoryYear(requstVo.getUserId(), requstVo.getSelYear());

		result.setValue(vo);
		
		result.setResult(ReturnCode.succeeded);

		logger.info("/GetBmiHistoryYear : vo" + vo);
		
		return result;
	}
	
	@RequestMapping(value = "/GetSmoke", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetSmoke(@RequestBody RequestVo	requstVo) {
		logger.info("/GetSmoke : RequestVo" + requstVo);
		Result<Smoke> result = new Result<>();
		
		Smoke vo = bodyMeasureService.getSmoke(requstVo.getUserId());
		Student student =  studentService.getStudentByUserId(requstVo.getUserId());
		
		result.setSchoolGradeId(student.getSchoolGradeId());
		
		result.setValue(vo);
		result.setResult(ReturnCode.succeeded);
		
		return result;
	}
	
	@RequestMapping(value = "/GetRankingOfHeightPerClass", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetRankingOfHeightPerClass(@RequestBody RequestVo requstVo) {
		logger.info("/GetRankingOfHeightPerClass : RequestVo" + requstVo);

		Result<List<AverageItem>> result = new Result<>();
		List<AverageItem> list = bodyMeasureService.getHeightAveragePerClass(requstVo.getUserId());
		
		if (list != null) {
			result.setValue(list);
			result.setResult(ReturnCode.succeeded);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/GetRankingOfWeightPerClass", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetRankingOfWeightPerClass(@RequestBody RequestVo requstVo) {
		logger.info("/GetRankingOfWeightPerClass : RequestVo" + requstVo);

		Result<List<AverageItem>> result = new Result<>();
		List<AverageItem> list = bodyMeasureService.getWeightAveragePerClass(requstVo.getUserId());
		
		if (list != null) {
			result.setValue(list);
			result.setResult(ReturnCode.succeeded);
		}

		return result;
	}

	@RequestMapping(value = "/GetRankingOfBmiPerClass", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetRankingOfBmiPerClass(@RequestBody RequestVo requstVo) {
		logger.info("/GetRankingOfBmiPerClass : RequestVo" + requstVo);

		Result<List<AverageItem>> result = new Result<>();
		List<AverageItem> list = bodyMeasureService.getBmiAveragePerClass(requstVo.getUserId());
		
		if (list != null) {
			result.setValue(list);
			result.setResult(ReturnCode.succeeded);
		}

		return result;
	}

	@RequestMapping(value = "/GetRankingOfHeightPerSchool", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetRankingOfHeightPerSchool(@RequestBody RequestVo requstVo) {
		logger.info("/GetRankingOfHeightPerClass : RequestVo" + requstVo);

		Result<List<AverageItem>> result = new Result<>();
		List<AverageItem> list = bodyMeasureService.getHeightAveragePerSchool(requstVo.getUserId());
		
		if (list != null) {
			result.setValue(list);
			result.setResult(ReturnCode.succeeded);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/GetRankingOfWeightPerSchool", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetRankingOfWeightPerSchool(@RequestBody RequestVo requstVo) {
		logger.info("/GetRankingOfWeightPerClass : RequestVo" + requstVo);

		Result<List<AverageItem>> result = new Result<>();
		List<AverageItem> list = bodyMeasureService.getWeightAveragePerSchool(requstVo.getUserId());
		
		if (list != null) {
			result.setValue(list);
			result.setResult(ReturnCode.succeeded);
		}

		return result;
	}

	@RequestMapping(value = "/GetRankingOfBmiPerSchool", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> School(@RequestBody RequestVo requstVo) {
		logger.info("/GetRankingOfBmiPerClass : RequestVo" + requstVo);

		Result<List<AverageItem>> result = new Result<>();
		List<AverageItem> list = bodyMeasureService.getBmiAveragePerSchool(requstVo.getUserId());
		
		if (list != null) {
			result.setValue(list);
			result.setResult(ReturnCode.succeeded);
		}

		return result;
	}

	
	@RequestMapping(value = "/GetScore", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetScore(@RequestBody RequestVo	requstVo) {
		logger.info("/GetScore : RequestVo" + requstVo);
		Result<Growth> result = new Result<>();
		
		Growth vo = bodyMeasureService.getGrowth(requstVo.getUserId());
		
		result.setValue(vo);		
		result.setResult(ReturnCode.succeeded);
		
		return result;
	}
	
	
	@ExceptionHandler(Exception.class)
	public @ResponseBody Result<?> handleException(Exception exception) {

		logger.error(exception.getLocalizedMessage());
		Result<?> result = new Result<>();
		
		result.setResult(ReturnCode.exception);
		result.setErrMsg(exception.getMessage());
		
		
		return result;
	}
	
		

		
		
}
