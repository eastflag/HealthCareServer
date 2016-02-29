package com.healthcare.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healthcare.biz.mybatis.domain.SimliAnswer;
import com.healthcare.biz.mybatis.domain.SimliQuestion;
import com.healthcare.biz.mybatis.domain.SimliResult;
import com.healthcare.biz.mybatis.domain.SimliReview;
import com.healthcare.biz.mybatis.domain.SimliReviewAnswer;
import com.healthcare.biz.mybatis.domain.SimliType;
import com.healthcare.biz.mybatis.domain.Student;
import com.healthcare.biz.service.SimliService;
import com.healthcare.biz.service.StudentService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ActivityController {

	private static final Logger logger = LoggerFactory.getLogger(SimliController.class);
	
}
