package com.healthcare.biz.service;

import java.util.List;
import java.util.Map;

import com.healthcare.bean.Food;

public interface FoodService {

	List<Map<String, Object>> getEveryFoodList(String userId);

	List<Map<String, Object>> getAlgMainList(String userId);

	List<Map<String, Object>> searchAlg(String search_alg);

	int getAlgSelectedCnt(Food reqFood);

	int insertTempAlg(Food reqFood);

	List<Map<String, Object>> getStuSelectedAlg(String userId);

	int updateTempAlg(Food reqFood);

	int saveStuAlg(Food reqFood);

	void deleteTempAlg(String userId);

	List<Map<String, Object>> getBreakfastCate(String userId);

	int insertBkstSetInput(Food reqFood);

	int updateBkstSetInput(Food reqFood);

	Food getAgeGroupInfo(String age);

	Food getStuBkstInfo(String userId);

	String getBmiStandAmt(String bmi_status);

	Food getDinnerInfo(String menu_id);

	List<Map<String, Object>> getDinnerMenuList(Food reqFood);

	Food getCalMinMax();
	
	Food getCalMinMax2(int age);

	Food getAgeMinMax();

	int cntSetInfo(Food reqFood);

	List<Map<String, Object>> getDinnerMenuList2(Food calInfo);

	List<Food> getDinnerListAll();

	void getAlgMapping(Map<String, Object> map);
}
