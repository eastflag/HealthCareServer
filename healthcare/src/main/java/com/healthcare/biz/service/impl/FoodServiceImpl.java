package com.healthcare.biz.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.bean.Food;
import com.healthcare.biz.mybatis.domain.Student;
import com.healthcare.biz.mybatis.persistence.FoodMapper;
import com.healthcare.biz.service.FoodService;
import com.healthcare.common.AES256Util;
import com.healthcare.common.Util;

@Service("food")
public class FoodServiceImpl implements FoodService{

	@Autowired
	FoodMapper foodMapper;
	
	public List<Map<String, Object>> getEveryFoodList(String userId){
		
		AES256Util aes = new AES256Util();
		
		Map<String, Object> pmap = Util.before5Day();
		Map<String, Object> sqlMap = foodMapper.getMySchoolInfo(userId);
		
		pmap.put("school_id", sqlMap.get("SCHOOL_ID"));
		pmap.put("section", sqlMap.get("SECTION"));
		
		List<Map<String, Object>> list =foodMapper.getEveryFoodList(pmap);

		for(Map<String, Object> item : list) {
			try {
				item.put("school_name", aes.decode((String) item.get("school_name")));
			} catch (InvalidKeyException | UnsupportedEncodingException
					| NoSuchAlgorithmException | NoSuchPaddingException
					| InvalidAlgorithmParameterException
					| IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> getAlgMainList(String userId) {

		List<Map<String, Object>> list =foodMapper.getAlgMainList(userId);
		
		return list;
	}

	@Override
	public List<Map<String, Object>> searchAlg(String search_alg) {

		List<Map<String, Object>> list =foodMapper.searchAlg(search_alg);
		
		return list;
	}

	@Override
	public int getAlgSelectedCnt(Food reqFood) {
		int isResult = foodMapper.getAlgSelectedCnt(reqFood);
		return isResult;
	}

	@Override
	public int insertTempAlg(Food reqFood) {
		int isResult = foodMapper.insertTempAlg(reqFood);
		return isResult;
	}

	@Override
	public List<Map<String, Object>> getStuSelectedAlg(String userId) {
		List<Map<String, Object>> list =foodMapper.getStuSelectedAlg(userId);
		
		return list;
	}

	@Override
	public int updateTempAlg(Food reqFood) {
		int isResult = foodMapper.updateTempAlg(reqFood);
		return isResult;
	}

	@Override
	public int saveStuAlg(Food reqFood) {
		int isResult = foodMapper.saveStuAlg(reqFood);
		return isResult;
	}

	@Override
	public void deleteTempAlg(String userId) {
		foodMapper.deleteTempAlg(userId);
	}

	@Override
	public List<Map<String, Object>> getBreakfastCate(String userId) {
		List<Map<String, Object>> list =foodMapper.getBreakfastCate(userId);
		
		return list;
	}

	@Override
	public int insertBkstSetInput(Food reqFood) {
		int result = foodMapper.insertBkstSetInput(reqFood);
		return result;
	}

	@Override
	public int updateBkstSetInput(Food reqFood) {
		int result = foodMapper.updateBkstSetInput(reqFood);
		return result;
	}

	@Override
	public Food getAgeGroupInfo(String age) {
		Food food = foodMapper.getAgeGroupInfo(age);
		return food;
	}

	@Override
	public Food getStuBkstInfo(String userId) {
		Food food = foodMapper.getStuBkstInfo(userId);
		return food;
	}

	@Override
	public String getBmiStandAmt(String bmi_status) {
		String bmi_stand_amt = foodMapper.getBmiStandAmt(bmi_status);
		return bmi_stand_amt;
	}

	@Override
	public List<Map<String, Object>> getDinnerMenuList(Food reqFood) {
		return foodMapper.getDinnerMenuList(reqFood);
	}

	@Override
	public Food getDinnerInfo(String menu_id) {
		return foodMapper.getDinnerInfo(menu_id);
	}

	@Override
	public Food getCalMinMax() {
		return foodMapper.getCalMinMax();
	}

	@Override
	public Food getAgeMinMax() {
		return foodMapper.getAgeMinMax();
	}

	@Override
	public int cntSetInfo(Food reqFood) {
		return foodMapper.cntSetInfo(reqFood);
	}
}
