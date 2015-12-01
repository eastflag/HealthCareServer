package com.healthcare.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.healthcare.admin.board.Board;
import com.healthcare.bean.Result;
import com.healthcare.biz.common.ReturnCode;
import com.healthcare.biz.service.NoticeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class NoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService noticeService;
	
	
	
	@RequestMapping(value = {"/notice"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String GetNotice() {
		logger.info("/notice");
		  
		return "/notice/index";
	}
	
	@RequestMapping(value = {"/notice/GetCount"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetCount() {
		logger.info("/notice/getCount");
		  
		Result<String> result = new Result<>();
		int count = noticeService.getNoticeCount();
		
		if (count > 0) {
			result.setValue(Integer.toString(count));
			result.setResult(ReturnCode.succeeded);
		} else {
			result.setResult(ReturnCode.failed);
		}
		
		return result;
	}
	
	@RequestMapping(value = {"/notice/GetList"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetList() {
		logger.info("/notice/GetList");
		  
		Result<List<Board>> result = new Result<>();
		
		List<Board> list = noticeService.getNoticeList();
		result.setValue(list);
		return result;
	}
	
	@RequestMapping(value = {"/setQuestion"}, method = {RequestMethod.POST})
	public @ResponseBody Result<?> setQuestion(@RequestBody Board board) {
	//public @ResponseBody Result<?> setQuestion(HttpServletRequest request) {
		logger.info("/setQuestion");
		
		Result<String> result = new Result<>();
		try{
			noticeService.setQuestion(board);
			result.setValue("true");
			result.setResult(ReturnCode.succeeded);
		}catch(Exception e){
			result.setValue("false");
			result.setResult(ReturnCode.failed);
			e.printStackTrace();
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
