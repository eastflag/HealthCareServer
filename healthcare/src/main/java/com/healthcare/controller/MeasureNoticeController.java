package com.healthcare.controller;

import java.io.IOException;
import java.util.concurrent.Future;

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

import com.healthcare.bean.GcmRequest;
import com.healthcare.bean.Result;
import com.healthcare.biz.common.ReturnCode;
import com.healthcare.biz.service.CommService;
import com.healthcare.common.CalendarUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MeasureNoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(MeasureNoticeController.class);
	

	@Autowired
	private CommService commService;
	
	Future<String> asynGcm;
	
	
	@RequestMapping(value = {"/measureNotice"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String GetIndex(Model model) {
		logger.info("/measureNotice");
		
		return "/measureNotice/index";
	}
	
	@RequestMapping(value = "/measureNotice/CallGcm", method = {RequestMethod.POST})
	public @ResponseBody Result<?> CallGcm(@RequestBody GcmRequest gcmRequest) {

		Result<String> result = new Result<>();
		logger.info("/measureNotice/CallGcm : " +  gcmRequest);
		
		try {
			
			if (asynGcm == null || asynGcm.isDone()) {
				
				int in_month=0;
				//1~2월은 현재의 이전 년도
				//3월부터 현재 년도
				CalendarUtil calendarUtil = CalendarUtil.getInstance();
				//in_month = calendarUtil.getMonth();
				in_month = calendarUtil.getMonth()+1;  //2015.03.12 연도 수정 month 잘못계산됨 3월인경우 
				gcmRequest.setYear(3<=in_month?String.valueOf(calendarUtil.getYear()):String.valueOf(calendarUtil.getYear()-1));

				asynGcm = commService.SendGcmMeasureNoticePerSchool(gcmRequest);
				result.setValue("Call");
			} else {
				result.setValue("isDoing");
			}
			
			result.setResult(ReturnCode.succeeded);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setResult(ReturnCode.exception);
			result.setErrMsg(e.getMessage());
		}

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
