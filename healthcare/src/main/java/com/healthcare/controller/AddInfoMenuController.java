package com.healthcare.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.healthcare.bean.Result;
import com.healthcare.biz.service.AddInfoService;

@Controller
public class AddInfoMenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(AddInfoMenuController.class);
	
	@Autowired
	private AddInfoService addInfoService;
	
	@RequestMapping(value = {"/addinfo/GetMenuList"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetMenuList(@RequestBody Map<String, String> reqMap)  {
		logger.info("/addinfo/GetMenuList");
		  
		Result<List<Map<String, Object>>> result = new Result<>();
		
		List<Map<String, Object>> list =addInfoService.getAddInfoList((String)reqMap.get("userId"));
		result.setValue(list);
		
		return result; 
	}
	@RequestMapping(value = {"/addinfo/GetPlayList"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetPlayList(@RequestBody Map<String, String> reqMap)  {
		logger.info("/addinfo/GetMenuList");
		  
		Result<List<Map<String, Object>>> result = new Result<>();
		
		List<Map<String, Object>> list =addInfoService.getAddInfoList((String)reqMap.get("userId"));
		result.setValue(list);
		
		return result; 
	}

}
