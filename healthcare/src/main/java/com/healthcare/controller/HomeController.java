package com.healthcare.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.healthcare.bean.Result;
import com.healthcare.bean.ResultSimli;
import com.healthcare.bean.SMSCertification;
import com.healthcare.bean.SimliVideoListRequest;
import com.healthcare.bean.VideoListRequest;
import com.healthcare.biz.common.ReturnCode;
import com.healthcare.biz.common.ServiceInfo;
import com.healthcare.biz.mybatis.domain.SignUp;
import com.healthcare.biz.mybatis.domain.SimliVideoInfo;
import com.healthcare.biz.mybatis.domain.Student;
import com.healthcare.biz.mybatis.domain.VideoInfo;
import com.healthcare.biz.service.BodyMeasureService;
import com.healthcare.biz.service.SMSCertService;
import com.healthcare.biz.service.SignUpService;
import com.healthcare.biz.service.SimliVideoInfoService;
import com.healthcare.biz.service.StudentService;
import com.healthcare.biz.service.VideoInfoService;
import com.healthcare.common.CertificationKeyGenerator;
import com.healthcare.common.Util;

/**
 * Handles requests for the application home page.
 */
@Controller
//@SessionAttributes("g_mdn")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private SignUpService signUpService;
	
	@Autowired
	private SMSCertService smsCertService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private VideoInfoService videoInfoService;
	
	@Autowired
	private SimliVideoInfoService simliVideoInfoService;
	
	
	@Autowired
	private BodyMeasureService bodyMeasureService;
	
	@Autowired
	ServiceInfo serviceInfo;
	
	/**
	 * 초기화면 : test용 페이지 : WEB
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String GetIndex(Model model) {
		logger.info("index");
		
		return "index";
	}
	
	/**
	 * 컨텐츠 화면 및 정보 요청 : APP
	 * @param p
	 * @param ver
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {"/front-views/view"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String GetFrontView(@RequestParam(value="p", required=false) String p,  
					            @RequestParam(value="ver", required=false) String ver,
					            @RequestParam(value="userId", required=false) String userId,  
			                   Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("/front-views/view"+p);
		model.addAttribute("p", p);
		
		Student student = null;
		String isUpdate = "N";
		
		if(Util.doFindMobileDevice("android", request) && !(serviceInfo.getServiceVer().equals(ver))){
			isUpdate = "Y";
		} else {
			student = studentService.getStudentByUserId(userId);
			
			if (student != null) {
				request.setAttribute("userName", student.getName());
				request.setAttribute("userSex", student.getSex());
			}
		}
		
		request.setAttribute("isUpdate", isUpdate);
		response.addCookie(new Cookie("isUpdate", isUpdate));
		
		if (p != null && "more".equals(p) && "N".equals(isUpdate)) {
			return "/front-views/more"; // 더보기 화면
		}else if (p != null && "food".equals(p) && "N".equals(isUpdate)) {
			return "/front-views/food"; // 더보기 화면
		} else if (p != null && "add_info".equals(p) && "N".equals(isUpdate)) {
			//System.out.println("AddInfo");
			return "/front-views/addinfo_menu"; // 더보기 화면
		}else if (p != null && "add_play".equals(p) && "N".equals(isUpdate)) {
			//System.out.println("AddInfo");
			return "/front-views/addinfo_play"; // play 화면
		}else {
			return "/front-views/index";
		}
	}
	
	/**
	 * 컨텐츠 화면 및 정보 요청 : WEB
	 * @param p
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/front-views/view_web"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String GetFrontViewW(@RequestParam(value="p", required=false) String p,  Model model) {
		logger.info("/front-views/view_web");
		model.addAttribute("p", p);
		
		return "/front-views/index_web";
	}
	
	/**
	 * 서비스 기본 정보 조회
	 * @return
	 */
	@RequestMapping(value = "/GetServiceInfo", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetServiceInfo() {
		logger.info("/GetServiceInfo : " );
		
		Result<ServiceInfo> result = new Result<>();
		
		result.setResult(ReturnCode.succeeded);
		result.setValue(serviceInfo);

		return result;
	}

	/**
	 * 로그인 : APP
	 * @param signUp
	 * @return
	 */
	@RequestMapping(value = "/SignUp", method = {RequestMethod.POST})
	public @ResponseBody Result<?> SignUp(@RequestBody SignUp signUp) {

		Result<SignUp> result = new Result<>();
		
		logger.info("/SignUp : " +  signUp);
		
		SignUp user = signUpService.getUser(signUp.getMdn());
		
		if (user != null) {
			
			signUpService.updateSignUp(signUp);
			
			result.setValue(signUp);
			result.setResult(ReturnCode.succeeded);
			
		} else {
			result.setResult(ReturnCode.notExistUser);
		}

		result.setValue(signUp);
		
		return result;
	}
	
	/**
	 * 자녀 목록 조회
	 * @param signUp
	 * @return
	 */
	@RequestMapping(value = "/GetStudent", method = {RequestMethod.POST})
	public @ResponseBody Result<?> GetStudent(@RequestBody SignUp signUp) {
		
		Result<List<Student>> result = new Result<>();
		
		logger.info("/GetStudent : " +  signUp);
		
		List<Student> list = studentService.getStudentListByMdn(signUp.getMdn());
		
		for(Student item : list) {
			
			String measureDate = bodyMeasureService.getRecentMeasureDate(item.getUserId());
			
			if (measureDate != null) {
				item.setMeasureDate(measureDate);
			}
		}
		
		result.setValue(list);
		result.setResult(ReturnCode.succeeded);

		return result;
	}

	/**
	 * 동영상 목록 조회
	 * @param videoListRequest
	 * @return
	 */
	@RequestMapping(value = "/GetVideoList", method = {RequestMethod.POST})
	public @ResponseBody Result<?> GetVideoList(@RequestBody VideoListRequest videoListRequest) {

		Result<List<VideoInfo>> result = new Result<>();
		
		logger.info("/GetVideoList : " +  videoListRequest);
		
		List<VideoInfo> list = null;
		
		if (videoListRequest.getMasterGradeId() != null && videoListRequest.getMasterGradeId().length() > 0) {
			list = videoInfoService.getVideoInfoListByMasterGradeId(videoListRequest.getMasterGradeId());
		} else if (videoListRequest.getSchoolGradeId() != null && videoListRequest.getSchoolGradeId().length() > 0) {
			// TODO : school ID로 분별하여 처리 요망.
			String infoType = "초등학교";
			list = videoInfoService.getVideoInfoListByInfoType(infoType);
		}
		
		result.setValue(list);
		result.setResult(ReturnCode.succeeded);

		return result;
		
	}
	
	/**
	 * 동영상 목록 조회
	 * @param simliVideoListRequest
	 * @return
	 */
	@RequestMapping(value = "/GetSimliVideoList", method = {RequestMethod.POST})
	public @ResponseBody ResultSimli<?> GetSimliVideoList(@RequestBody SimliVideoListRequest simliVideoListRequest) {

		ResultSimli<List<SimliVideoInfo>>  result1 = new ResultSimli<>();
		
		logger.info("/GetSimliVideoList : " +  simliVideoListRequest);
		
		List<SimliVideoInfo> list = null;
		
		
			list = simliVideoInfoService.getSimliStuVideoInfoList(simliVideoListRequest.getUserId());
		
			result1.setValue(list);
			result1.setResult(ReturnCode.succeeded);

		return result1;
		
	}
	
	/**
	 * 심리검사 동영상 목록 조회
	 * @param videoListRequest
	 * @return
	 */
	@RequestMapping(value = "/GetSimliVideoList1", method = {RequestMethod.POST})
	public @ResponseBody Result<?> GetSimliVideoList1(@RequestBody VideoListRequest videoListRequest) {

		Result<List<VideoInfo>> result = new Result<>();
		
		logger.info("/GetSimliVideoList : " +  videoListRequest);
		
		List<VideoInfo> list = null;
		
		if (videoListRequest.getMasterGradeId() != null && videoListRequest.getMasterGradeId().length() > 0) {
			list = videoInfoService.getVideoInfoListByMasterGradeId(videoListRequest.getMasterGradeId());
		} else if (videoListRequest.getSchoolGradeId() != null && videoListRequest.getSchoolGradeId().length() > 0) {
			// TODO : school ID로 분별하여 처리 요망.
			String infoType = "초등학교";
			list = videoInfoService.getVideoInfoListByInfoType(infoType);
		}
		
		result.setValue(list);
		result.setResult(ReturnCode.succeeded);

		return result;
		
	}
	
	
	@RequestMapping(value = "/UploadContent", method = {RequestMethod.POST})
	public @ResponseBody Result<?> UploadContent(@RequestParam("file")MultipartFile file, 
			@RequestParam("mdn")String mdn) throws IOException {
	
		
		Result<String> result = new Result<>();
		
		logger.info("/UploadContent : " +  file.getOriginalFilename() + ", size : " + file.getSize() +
				", mdn : " + mdn);
		
		String path = "\\upload";
		

		byte fileData[] = file.getBytes();

		try(FileOutputStream fos = new FileOutputStream(path + "\\" + file.getOriginalFilename())){
			fos.write(fileData);
		};

		

		result.setValue("");
		result.setResult(ReturnCode.succeeded);

		return result;
	}
	
	/**
	 * 공지사항 화면으로 이동 : WEB
	 * @param p
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/front-views/notice_web"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String GetNoticeView(@RequestParam(value="p", required=false) String p,  Model model) {
		logger.info("/front-views/notice_web");
		model.addAttribute("p", p);
		
		return "/front-views/notice_web";
	}

	/**
	 * Q&A 등록 화면으로 이동 : WEB
	 * @param p
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/front-views/question_web"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String GetQuestionView(@RequestParam(value="p", required=false) String p,  Model model) {
		logger.info("/front-views/question_web");
		model.addAttribute("p", p);
		
		return "/front-views/question_web";
	}
	

	/**
	 * 로그인 여부 체크 : WEB
	 * @param g_mdn
	 * @return
	 */
	@RequestMapping(value = "/GetIsLogin", method = {RequestMethod.POST})
	//public @ResponseBody Result<?> GetIsLogin(@ModelAttribute("g_mdn") String g_mdn) {
	public @ResponseBody Result<?> GetIsLogin(HttpServletRequest request) {

		String g_mdn = (String)request.getSession().getAttribute("g_mdn");
		logger.info("/front-views/checkIsLogin : g_mdn = " + g_mdn);

		Result<String> result = new Result<>();

		String isLogIn = "N";

		if (g_mdn != null && g_mdn.length() > 0) { // 로그인
			isLogIn = "Y";
		} 

		result.setValue(isLogIn);
		result.setResult(ReturnCode.succeeded);
		
		return result;
	}


	/**
	 * SMS 인증 : APP
	 * 현재는 USIM으로부터 MDN을 읽어 요청하므로, 별다른 기능을 수행하지는 않음
	 * @param smsCertification
	 * @return
	 */
	@RequestMapping(value = "/SMSCertification", method = {RequestMethod.POST})
	public @ResponseBody Result<?> SMSCertification(@RequestBody SMSCertification smsCertification) {

		logger.info("/SMSCertification : " +  smsCertification);
		
		Result<SMSCertification> result = new Result<>();

		// TODO : SMS 전송 모듈
		
		smsCertification.setCertificationNumber("0000");
		
		result.setResult(ReturnCode.succeeded);
		result.setValue(smsCertification);
		
		// 서비스 테스트
		//result.setCertificationNumber(basicService.getTest());

		return result;
	}
	
	/**
	 * SMS 인증 : WEB
	 * @param smsCertification
	 * @return
	 */
	@RequestMapping(value = "/SMSCertReq", method = {RequestMethod.POST})
	public @ResponseBody Result<?> SMSCertReq(@RequestBody SMSCertification smsCertification, Model model, HttpServletRequest request) {

		logger.info("/SMSCertReq : " +  smsCertification);
		

		/*
		 * 1. mdn과 인증키 조회
		 * 2. 없으면 에러 처리 코드 리턴
		 * 3. 있으면 인증키 삭제 후 -> 정상 처리 코드 리턴
		 */

		boolean isSMSCert = false;
		String tmpRetCode = ReturnCode.succeeded;
		String tmpRetErrMsg = "";
		
		try {
			
			isSMSCert = CertificationKeyGenerator.newInstance().isCorrectCertifiKey (smsCertService, 
					 Util.makePhoneNumber(smsCertification.getMdn()), 
                     smsCertification.getCertificationNumber());
			
			if (isSMSCert) {
				request.getSession().setAttribute("g_mdn", smsCertification.getMdn());
				//model.addAttribute("g_mdn", smsCertification.getMdn()); // SessionAttribute 저장
			} else {
				tmpRetCode = ReturnCode.failed;
				tmpRetErrMsg = ReturnCode.strSMSCertFail;
			}
		} catch (Exception e) {
			tmpRetCode = ReturnCode.exception;
			tmpRetErrMsg = ReturnCode.strSMSCertFail;
		}

		Result<SMSCertification> result = new Result<>();
		result.setResult(tmpRetCode);
		result.setErrMsg(tmpRetErrMsg);
		result.setValue(smsCertification);
		
		return result;
	}

	/**
	 * SMS 인증키 생성 : WEB
	 * @param smsCertification
	 * @return
	 */
	@RequestMapping(value = "/SMSCertKeyReq", method = {RequestMethod.POST})
	public @ResponseBody Result<?> SMSCertKeyReq(@RequestBody SMSCertification smsCertification) {

		logger.info("/SMSCertKeyReq : " +  smsCertification);
		smsCertification.toString();
		
		Result<SMSCertification> result = new Result<>();

		/*
		 * 1. mdn의 등록여부 확인
		 * 2. mdn이 미등록자이면 에러 처리 코드 리턴
		 * 3. mdn이 등록자이면 인증키 생성 후 저장 -> 정상 처리 코드 리턴
		 */
		
		SignUp user = signUpService.getUser(smsCertification.getMdn());

		if (user != null) { // 등록된 사용자

			try {
				//인증키 삭제&생성 루틴 실행
				CertificationKeyGenerator.newInstance().tempKeyGenerator(smsCertService, Util.makePhoneNumber(smsCertification.getMdn()));

				result.setResult(ReturnCode.succeeded);
				result.setValue(smsCertification);
				
			} catch (Exception e) {
				//System.out.println(e);
				result.setResult(ReturnCode.exception);
				result.setErrMsg(ReturnCode.strSMSCertKeyGenFail);
				result.setValue(smsCertification);
			}
			
		} else { // 비등록 사용자
			result.setResult(ReturnCode.notExistUser);
			result.setErrMsg(ReturnCode.strNotExistUser);
		}
		
		return result;
	}
	
	/**
	 * popup 여부 확인
	 * 현재시간이 properties에 지정된 시작일과 마지막일 안에 있을경우 Y
	 * 팝업 게시 여부 또한 properties에 Y,N으로 지정됨
	 * @return
	 */
	@RequestMapping(value = "/CheckEvent", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Result<?> CheckEvent() {

		logger.info("/CheckEvent");
		
		Result<String> result = new Result<>();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmm");
		Date dateObj = null;
        
        long nowDate = 0;
        long startDate = 0;
        long lastDate = 0;
        
        try {
        	dateObj = new Date();
        	nowDate = dateObj.getTime();
        	startDate = format.parse(serviceInfo.getServicePopupStrartDate()).getTime();
        	lastDate = format.parse(serviceInfo.getServicePopupEndDate()).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //현재 시간이 지정된 기간 내 일경우 true
        //게시 여부가 N일경우 기간 내에 있어도 false
        //게시 여부 Y이며 현재시간이 기간 내에 있을경우에만 true
        if(startDate<nowDate && nowDate<lastDate){
        	result.setResult(serviceInfo.getServicePopupYN().equals("Y")?ReturnCode.succeeded:ReturnCode.failed);
        //false
        }else{
        	result.setResult(ReturnCode.failed);
        }
        logger.info("servicePopupYN : [ "+result.getResult()+" ]");
        
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
