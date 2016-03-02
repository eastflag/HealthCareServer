package com.healthcare.biz.mybatis.persistence;

import java.util.List;
import java.util.Map;

import com.healthcare.bean.Food;

public interface FoodMapper {

	List<Map<String, Object>> getEveryFoodList(Map<String, Object> pamp);
	Map<String, Object> getMySchoolInfo(String userId);
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
	List<Map<String, Object>> getDinnerMenuList2(Food calInfo);
	Food getCalMinMax();
	Food getCalMinMax2(int age);
	Food getAgeMinMax();
	int cntSetInfo(Food reqFood);
	List<Food> getDinnerListAll();
	void getAlgMapping(Map<String, Object> map);
}
