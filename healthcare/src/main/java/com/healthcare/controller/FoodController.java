package com.healthcare.controller;

import java.text.DecimalFormat;
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

import com.healthcare.bean.Food;
import com.healthcare.bean.RequestVo;
import com.healthcare.bean.Result;
import com.healthcare.biz.common.ReturnCode;
import com.healthcare.biz.mybatis.domain.BodyMeasureSummary;
import com.healthcare.biz.service.BodyMeasureService;
import com.healthcare.biz.service.FoodService;

@Controller
public class FoodController {
	
	private static final Logger logger = LoggerFactory.getLogger(FoodController.class);
	
	@Autowired
	private FoodService foodService;
	@Autowired
	private BodyMeasureService bodyMeasureService;
	
	@RequestMapping(value = {"/food/GetList"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> GetList(@RequestBody Map<String, Object> reqMap)  {
		logger.info("/food/GetList");
		  
		Result<List<Map<String, Object>>> result = new Result<>();
		// 알레르기 세팅 임시저장 삭제
		//foodService.deleteTempAlg((String)reqMap.get("userId"));
		List<Map<String, Object>> list = foodService.getEveryFoodList((String)reqMap.get("userId"));
		result.setValue(list);
		
		return result;
	}
	
	@RequestMapping(value = {"/food/getBreakfastCate"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> getBreakfastCate(@RequestBody Map<String, Object> reqMap)  {
		logger.info("/food/getBreakfastCate");
		 
		System.out.println(">>> getBreakfastCate userId:"+(String)reqMap.get("userId"));
		Result<List<Map<String, Object>>> result = new Result<>();
		List<Map<String, Object>> list = foodService.getBreakfastCate((String)reqMap.get("userId"));
		result.setValue(list);
		
		return result;
	}
	
	@RequestMapping(value = {"/food/getDinnerMenuList"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> getDinnerMenuList(@RequestBody Food reqFood)  {
		logger.info("/food/getDinnerMenuList");

		String cal = reqFood.getCal();
		System.out.println(">>> getDinnerMenuList cal:"+cal+" userid:"+reqFood.getUserId());
		Result<List<Map<String, Object>>> result = new Result<>();
		List<Map<String, Object>> list = foodService.getDinnerMenuList(reqFood);
		result.setValue(list);
		
		return result;
	}
	
	@RequestMapping(value = {"/food/getDinnerInfo"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> getDinnerInfo(@RequestBody Map<String, Object> reqMap)  {
		logger.info("/food/getDinnerInfo");

		String menu_id = (String)reqMap.get("menu_id");
		System.out.println(">>> getDinnerInfo menu_id:"+menu_id);
		Result<Food> result = new Result<>();
		Food info = foodService.getDinnerInfo(menu_id);
		result.setValue(info);
		
		return result;
	}
	
	@RequestMapping(value = {"/food/getStudentDinnerInfo"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> getStudentDinnerInfo(@RequestBody Food reqFood){
		logger.info("/food/getStudentDinnerInfo: RequestVo" + reqFood);

		Result<Food> result = new Result<>();
		
		BodyMeasureSummary vo = bodyMeasureService.getSummary(reqFood.getUserId());

		String age = vo.getAge();
		String height = vo.getHeight();
		String weight = vo.getWeight();
		String bmi = vo.getBmi();
		String bmi_status = vo.getBmiStatus();
		float cal = 0;
		float carbohy = 0;
		float protein = 0;
		float fat = 0;
		float vitamin_a = 0;
		float vitamin_c = 0;
		float calcium = 0;
		
		System.out.println(">>> age:"+age);
		Food ageMinMax = foodService.getAgeMinMax();
		if(Integer.parseInt(age) < Integer.parseInt(ageMinMax.getMin_age())) {
			age = ageMinMax.getMin_age();
		}else if(Integer.parseInt(ageMinMax.getMax_age()) < Integer.parseInt(age)) {
			age = ageMinMax.getMax_age();
		}		
		Food food = foodService.getAgeGroupInfo(age);
		
		if(food!=null) {

			cal 		= Float.parseFloat(food.getCal());
			carbohy 	= Float.parseFloat(food.getCarbohy());
			protein 	= Float.parseFloat(food.getProtein());
			fat 		= Float.parseFloat(food.getFat());
			vitamin_a 	= Float.parseFloat(food.getVitamin_a());
			vitamin_c 	= Float.parseFloat(food.getVitamin_c());
			calcium 	= Float.parseFloat(food.getCalcium());

			float bksk_stand_amt = 1;
			String bkst_id = ""; 
			Food stuBkstInfo = foodService.getStuBkstInfo((String)reqFood.getUserId());
			if(stuBkstInfo!=null){
				bkst_id = (String) stuBkstInfo.getBkst_id();
				bksk_stand_amt = Float.parseFloat(stuBkstInfo.getBksk_stand_amt());
			}
			System.out.println(">>> bkst_id:"+bkst_id+", bksk_stand_amt:"+bksk_stand_amt);
			
			float bmi_stand_amt = 1;
			bmi_status = bmi_status.substring(0,bmi_status.length()-1);
			bmi_status = bmi_status.trim();
			System.out.println(">>> bmi_status:"+bmi_status);
			
			bmi_stand_amt = Float.parseFloat(foodService.getBmiStandAmt(bmi_status));
			System.out.println(">>> bmi_stand_amt:"+bmi_stand_amt);
			
			cal 		= cal * bksk_stand_amt * bmi_stand_amt;
			carbohy 	= carbohy * bksk_stand_amt * bmi_stand_amt;
			protein 	= protein * bksk_stand_amt * bmi_stand_amt;
			fat 		= fat * bksk_stand_amt * bmi_stand_amt;
			vitamin_a 	= vitamin_a * bksk_stand_amt * bmi_stand_amt;
			vitamin_c 	= vitamin_c * bksk_stand_amt * bmi_stand_amt;
			calcium 	= calcium * bksk_stand_amt * bmi_stand_amt;
			
			Food calMinMax = foodService.getCalMinMax();
			
			float re_cal = cal;
			float mul = 1;
			if(cal < Float.parseFloat(calMinMax.getMin_cal())) {
				re_cal = Float.parseFloat(calMinMax.getMin_cal())+10;
			} else if(cal > Float.parseFloat(calMinMax.getMax_cal())){
				re_cal = Float.parseFloat(calMinMax.getMax_cal())-10;
			}
			mul = re_cal/cal;
			System.out.println(">>> cal:"+cal + "re_cal:"+re_cal + "mul:"+mul);
			cal 		= re_cal;
			carbohy 	= carbohy * mul;
			protein 	= protein * mul;
			fat 		= fat * mul;
			vitamin_a 	= vitamin_a * mul;
			vitamin_c 	= vitamin_c * mul;
			calcium 	= calcium * mul;
			

			DecimalFormat format = new DecimalFormat(".#");
			
			// 신체 정보 set
			food.setHeight((String)height);
			food.setWeight(vo.getWeight());
			food.setBmi(vo.getBmi());
			food.setCal(format.format(cal));
			food.setCarbohy(format.format(carbohy));
			food.setProtein(format.format(protein));
			food.setFat(format.format(fat));
			food.setVitamin_a(format.format(vitamin_a));
			food.setVitamin_c(format.format(vitamin_c));
			food.setCalcium(format.format(calcium));

			
			result.setValue(food);
			
			
			result.setResult(ReturnCode.succeeded);

		} else {
			result.setResult(ReturnCode.notExistMeasureInfo);
			
		}
		
		return result;
	}
	
	@RequestMapping(value = {"/food/getAlgMainList"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> getAlgMainList(@RequestBody Map<String, Object> reqMap)  {
		logger.info("/food/getAlgMainList");
		  
		Result<List<Map<String, Object>>> result = new Result<>();
		
		List<Map<String, Object>> list = foodService.getAlgMainList((String)reqMap.get("userId"));
		result.setValue(list);
		
		return result;
	}
	
	@RequestMapping(value = {"/food/searchAlg"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> searchAlg(@RequestBody Map<String, Object> reqMap)  {
		logger.info("/food/searchAlg");
		  
		Result<List<Map<String, Object>>> result = new Result<>();
		
		List<Map<String, Object>> list = foodService.searchAlg((String)reqMap.get("searchAlg"));
		result.setValue(list);
		
		return result;
	}
	
	@RequestMapping(value = {"/food/getStuSelectedAlg"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> getStuSelectedAlg(@RequestBody Map<String, Object> reqMap)  {
		logger.info("/food/searchAlg"+reqMap);
		  
		Result<List<Map<String, Object>>> result = new Result<>();
		
		List<Map<String, Object>> list = foodService.getStuSelectedAlg((String)reqMap.get("userId"));
		result.setValue(list);
		
		return result;
	}
	
	@RequestMapping(value = {"/food/cntSetInfo"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> chkSetInfo(@RequestBody Food reqFood)  {
		logger.info("/food/selectAlg"+reqFood);
		  
		Result<String> result = new Result<>();
		
		int isResult = -1;

		System.out.println("reqFood >>> userid:"+reqFood.getUserId());
		
		// 해당 학생의 알러지 아이디가 있는지 카운트
		int setCnt = foodService.cntSetInfo(reqFood);

		System.out.println(">>> cntSetInfo setCnt"+setCnt);
		isResult = setCnt;
		
		
		// 설정 수
		result.setValue(Integer.toString(isResult));
		
		return result;
	}
	
	@RequestMapping(value = {"/food/selectAlg"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> selectAlg(@RequestBody Food reqFood)  {
		logger.info("/food/selectAlg"+reqFood);
		  
		Result<String> result = new Result<>();
		
		int isResult = -1;

		System.out.println("reqFood >>> alg_id:"+reqFood.getAlg_id()+", alg_state:"+reqFood.getAlg_state()+", userId:"+reqFood.getUserId());
		
		// 해당 학생의 알러지 아이디가 있는지 카운트
		int algCnt = foodService.getAlgSelectedCnt(reqFood);

		System.out.println(">>> selectAlg algCnt"+algCnt);
		if(algCnt==0) { // 없을 시 INSERT
			isResult  = foodService.insertTempAlg(reqFood);
		} else { // 있으면 UPDATE
			isResult  = foodService.updateTempAlg(reqFood);
		}
		
		System.out.println(">>> selectAlg isResult"+isResult);
		// 1 성공 -1 실패
		result.setValue(Integer.toString(isResult));
		
		return result;
	}
	
	@RequestMapping(value = {"/food/saveStuAlg"}, method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Result<?> saveStuAlg(@RequestBody Food reqFood)  {
		logger.info("/food/saveStuAlg");
		  
		Result<String> result = new Result<>();
		
		System.out.println(">>> userId:"+reqFood.getUserId()+", bkst_id:"+reqFood.getBkst_id());
		
		int isResult = -1;
		
		String bkst_id = ""; 
		Food stuBkstInfo = foodService.getStuBkstInfo((String)reqFood.getUserId());
		if(stuBkstInfo!=null){
			bkst_id = (String) stuBkstInfo.getBkst_id();
		}
		System.out.println(">>> bkst_id:"+bkst_id);
		
		if(bkst_id=="" || bkst_id == null) { // 저장되어 있는 아침 설정 값이 없을 때
			isResult = foodService.insertBkstSetInput(reqFood);  
		} else if(bkst_id.equals(reqFood.getBkst_id())){ // 기존 설정되어 있는 값과 동일 할 때
			isResult = 0;
		} else { // 기존 값과 동일하지 않을 때
			isResult = foodService.updateBkstSetInput(reqFood);
		}
		System.out.println(">>> saveStuAlg bkst isResult"+isResult);
		
		if(isResult>=0) {
			isResult  = foodService.saveStuAlg(reqFood);
		}
		
		System.out.println(">>> saveStuAlg alg isResult"+isResult);
		// -1 실패, update한 row수
		result.setValue(Integer.toString(isResult));
		
		return result;
	}

}
