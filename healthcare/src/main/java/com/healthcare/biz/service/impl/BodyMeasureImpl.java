package com.healthcare.biz.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.bean.Bmi;
import com.healthcare.bean.Growth;
import com.healthcare.bean.MeasureHistory;
import com.healthcare.bean.MeasureItem;
import com.healthcare.bean.Smoke;
import com.healthcare.biz.common.SectionCode;
import com.healthcare.biz.mybatis.domain.AverageItem;
import com.healthcare.biz.mybatis.domain.BodyMeasureGrade;
import com.healthcare.biz.mybatis.domain.BodyMeasureSummary;
import com.healthcare.biz.mybatis.domain.StatisticsParma;
import com.healthcare.biz.mybatis.domain.Student;
import com.healthcare.biz.mybatis.persistence.BodyMeasureMapper;
import com.healthcare.biz.mybatis.persistence.HealthAdviserMapper;
import com.healthcare.biz.mybatis.persistence.StatisticsMapper;
import com.healthcare.biz.mybatis.persistence.StudentMapper;
import com.healthcare.biz.service.BodyMeasureService;
import com.healthcare.biz.service.StudentService;
import com.healthcare.common.AES256Util;

@Service("bodyMeasureService")
public class BodyMeasureImpl implements BodyMeasureService {

	@Autowired
	BodyMeasureMapper bodyMeasureMapper;

	@Autowired
	StudentMapper studentMapper;

	@Autowired
	HealthAdviserMapper healthAdviserMapper;

	@Autowired
	StatisticsMapper statisticsMapper;

	
	@Autowired
	private StudentService studentService;

	// TODO : 최신 평균 데이터를 찾는 쿼리도 제공 요망.
	String nationQueryYear = "2012";
	String standardDate = "20120101";
	
	String g_selectedYear = null;

	// common function
	// 흡연 측정 하지 않은 사용자 요약 정보 제공 : 흡연의 데이터는 ''로 처리함.
	List<BodyMeasureSummary> getSummaryCheckMeasureSmoke(String userId, Student student) {

		List<BodyMeasureSummary> list = null;
		AES256Util aes = new AES256Util();

		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("userId", userId);
		pmap.put("selYear", g_selectedYear);

		// school_grade : code 체계를 따른다.
		// TODO : 기준 정보는 초기 로딩 시에 쿼리를 해온 것으로 처리를 하는 것이 좋을듯
		// 1 ~ 4 학년은 흡연 측정을 하지 않는다.
		
		if (Integer.parseInt(student.getSchoolGradeId()) >= 1
				&& Integer.parseInt(student.getSchoolGradeId()) <= 4) {
			
			list = bodyMeasureMapper.getSummaryExceptSmoke(pmap);
		
		} else {
		
			list = bodyMeasureMapper.getSummary(pmap);
		}

		if (list != null && list.size() > 0) {
			for(int i=0; i<list.size(); i++) {
				try {
					list.get(i).setName(aes.decode(list.get(i).getName()));
				} catch (InvalidKeyException | UnsupportedEncodingException
						| NoSuchAlgorithmException | NoSuchPaddingException
						| InvalidAlgorithmParameterException
						| IllegalBlockSizeException | BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	private BodyMeasureGrade getMeasureGrade(String userId, String section) {

		this.g_selectedYear = null;
		
		Student student = studentService.getStudentByUserId(userId);
		
		if (student != null) {

			List<BodyMeasureSummary> list = getSummaryCheckMeasureSmoke(userId,
					student);

			if (list != null && list.size() > 0) {

				BodyMeasureSummary summaryVo = list.get(0);

				BodyMeasureGrade resultVo = new BodyMeasureGrade();
				// 고정 변수
				// 학생 정보
				resultVo.setUserId(student.getUserId());
				resultVo.setSex(student.getSex());
				resultVo.setSchoolId(student.getSchoolId());
				resultVo.setSchoolGradeId(student.getSchoolGradeId());
				resultVo.setYear(this.nationQueryYear);

				// 측정 정보
				resultVo.setSection(section);
				resultVo.setMeasureDate(summaryVo.getMeasureDate());
				
				resultVo.setBeforeMeasureDate(bodyMeasureMapper.getBeforeMeasureDate(resultVo));
				
				if (SectionCode.Height.equals(section)) {
					resultVo.setValue(summaryVo.getHeight());
				} else if (SectionCode.Weight.equals(section)) {
					resultVo.setValue(summaryVo.getWeight());
				}

				if (resultVo.requestValidation()) {
					BodyMeasureGrade gradeVo = bodyMeasureMapper
							.getGradeBySection(resultVo);

					if (gradeVo != null) {
						BodyMeasureGrade RankingVo = bodyMeasureMapper
								.getGradeRankingBySection(resultVo);

						resultVo.setGradeId(gradeVo.getGradeId());
						resultVo.setGradeDesc(gradeVo.getGradeDesc());

						resultVo.setSchoolGrade(RankingVo.getSchoolGrade());
						resultVo.setTotalNumberOfStudent(RankingVo
								.getTotalNumberOfStudent());
						
						BodyMeasureGrade beforeRankingVo = bodyMeasureMapper.getBeforeGradeRankingBySection(resultVo);
						
						/*
						 * 이전 측정값 : beforeValue
						 * 이전 학교내 학년별 순위 : beforeSchoolGrade
						 * 
						 * 이전 데이터가 없다면 최신데이터 값이 들어갑니다.
						 * */
						if(beforeRankingVo != null){
							if(beforeRankingVo.getBeforeSchoolGrade() == null || "".equals(beforeRankingVo.getBeforeSchoolGrade())){
								resultVo.setBeforeSchoolGrade(resultVo.getSchoolGrade());
							}else{
								resultVo.setBeforeSchoolGrade(beforeRankingVo.getBeforeSchoolGrade());
							}
							
							if(beforeRankingVo.getBeforeValue() == null  || "".equals(beforeRankingVo.getBeforeValue())){
								resultVo.setBeforeValue(resultVo.getValue());
							}else{
								resultVo.setBeforeValue(beforeRankingVo.getBeforeValue());
							}
						}else{
							resultVo.setBeforeSchoolGrade(resultVo.getSchoolGrade());
							resultVo.setBeforeValue(resultVo.getValue());
						}
						
						StatisticsParma param = new StatisticsParma();
						param.setSex(resultVo.getSex());
						param.setSchoolId(resultVo.getSchoolId());
						param.setSchoolGradeId(resultVo.getSchoolGradeId());
						param.setClassNumber(student.getClassNumber());
						param.setSection(resultVo.getSection());
						param.setMeasureDate(resultVo.getMeasureDate());
						
						AverageItem item = statisticsMapper
								.getAveragePerClass(param);
						if (item != null) {
							resultVo.setAverageOfClass(item.getValue());
						}

						item = statisticsMapper.getAveragePerSchool(param);
						if (item != null) {
							resultVo.setAverageOfSchool(item.getValue());
							System.out.println(" ::: 이번달 학교 평균 ==>" + resultVo.getAverageOfSchool() );//+ "value 값 ::: " + item.);
						}
						
						item = statisticsMapper.getAveragePerLocal(param);
						if(item != null){
							resultVo.setAverageOfLocal(item.getValue());
						}

						param.setMeasureDate(this.standardDate);
						item = statisticsMapper.getAveragePerNation(param);
						if (item != null) {
							resultVo.setAverageOfNation(item.getValue());
						}

						return resultVo;
					}else{
						System.out.println("===측정 데이터 NULL===  " + section );
					}
				}
			}
		}
		return null;
	}

	@Override
	public BodyMeasureSummary getSummary(String userId) {

		this.g_selectedYear = null;
		
		Student student = studentService.getStudentByUserId(userId);

		if (student != null) {

			List<BodyMeasureSummary> list = getSummaryCheckMeasureSmoke(userId,
					student);

			if (list != null && list.size() > 0) {

				BodyMeasureSummary summaryVo = list.get(0);

				BodyMeasureGrade requstVo = new BodyMeasureGrade();

				// 고정 변수
				requstVo.setSchoolGradeId(student.getSchoolGradeId());
				requstVo.setSex(student.getSex());
				requstVo.setYear(this.nationQueryYear);

				// height
				requstVo.setSection(SectionCode.Height);
				requstVo.setValue(summaryVo.getHeight());

				if (requstVo.requestValidation()) {
					BodyMeasureGrade resultVo = bodyMeasureMapper
							.getGradeBySection(requstVo);

					if (resultVo != null) {
						summaryVo.setHeightStatus(resultVo.getGradeDesc());
					}
				}

				// weight
				requstVo.setSection(SectionCode.Weight);
				requstVo.setValue(summaryVo.getWeight());

				if (requstVo.requestValidation()) {
					BodyMeasureGrade resultVo = bodyMeasureMapper
							.getGradeBySection(requstVo);

					if (resultVo != null) {
						summaryVo.setWeigthStatus(resultVo.getGradeDesc());
					}
				}

				// bmi
				requstVo.setSection(SectionCode.BMI);
				requstVo.setValue(summaryVo.getBmi());

				if (requstVo.requestValidation()) {
					BodyMeasureGrade resultVo = bodyMeasureMapper
							.getGradeBySection(requstVo);

					if (resultVo != null) {
						
						System.out.println(" BMI 지수 NULL getSummary "+resultVo.getGradeDesc());
						
						summaryVo.setBmiStatus(resultVo.getGradeDesc());
						summaryVo.setBmiGradeId(resultVo.getGradeId());
					}else{
						summaryVo.setBmiStatus("등급구분없음 ");
						System.out.println(" BMI 지수 분류 값없음--- getSummary Bm GradeId ::: " + summaryVo.getBmiGradeId()   );
					}
				}else{
					System.out.println(" === BodyMeasureGrade Null =====  ");
				}

				// smoker
				BodyMeasureGrade resultVo = bodyMeasureMapper
						.getSmokerGrade(summaryVo.getPpm());
				if (resultVo != null) {
					summaryVo.setSmokeStatus(resultVo.getGradeDesc());
				}

				return summaryVo;
			}
		}
		return null;
	}

	@Override
	public BodyMeasureGrade getHeightMeasureGrade(String userId) {

		return getMeasureGrade(userId, SectionCode.Height);
	}

	@Override
	public BodyMeasureGrade getWeightMeasureGrade(String userId) {

		return getMeasureGrade(userId, SectionCode.Weight);
	}

	@Override
	public Bmi getBmi(String userId) {

		this.g_selectedYear = null;

		Student student = studentService.getStudentByUserId(userId);
		System.out.print("=== BMI List Get 000===");
		
		if (student != null) {

			List<BodyMeasureSummary> list = getSummaryCheckMeasureSmoke(userId, student);

			if (list != null && list.size() > 0) {

				BodyMeasureSummary summaryVo = list.get(0);

				BodyMeasureGrade resultVo = new BodyMeasureGrade();
				// 고정 변수
				// 학생 정보
				resultVo.setUserId(student.getUserId());
				resultVo.setSex(student.getSex());
				resultVo.setSchoolId(student.getSchoolId());
				resultVo.setSchoolGradeId(student.getSchoolGradeId());
				resultVo.setYear(this.nationQueryYear);

				// 측정 정보
				resultVo.setSection(SectionCode.BMI);
				resultVo.setMeasureDate(summaryVo.getMeasureDate());

				resultVo.setValue(summaryVo.getBmi());

				if (resultVo.requestValidation()) {

					BodyMeasureGrade gradeVo = bodyMeasureMapper.getGradeBySection(resultVo);

					if (gradeVo != null) {
						Bmi bmi = new Bmi();

						bmi.setGradeId(gradeVo.getGradeId());
						bmi.setGradeString(gradeVo.getGradeDesc());

						bmi.setBmi(summaryVo.getBmi());
						bmi.setPercentageOfBodyFat(summaryVo.getPercentageOfBodyFat());
						return bmi;
					}else{
						System.out.println("=== BMI NULL 111 ===");
					}
				}else{
					System.out.println("=== BMI NULL 2222===");
				}
			}else{
				System.out.println("=== BMI List NULL  3333 ===");
			}
			
		}else{
			System.out.print("=== BMI List NULL  4444 ===");
		}
		return null;
	}

	@Override
	public Growth getGrowth(String userId) {
		
		this.g_selectedYear = null;
		
		Student student = studentService.getStudentByUserId(userId);
		if (student != null) {

			List<BodyMeasureSummary> list = getSummaryCheckMeasureSmoke(userId,
					student);

			if (list != null && list.size() > 0) {

				BodyMeasureSummary summaryVo = list.get(0);

				BodyMeasureGrade resultVo = new BodyMeasureGrade();
				// 고정 변수
				// 학생 정보
				resultVo.setUserId(student.getUserId());
				resultVo.setSex(student.getSex());
				resultVo.setSchoolId(student.getSchoolId());
				resultVo.setSchoolGradeId(student.getSchoolGradeId());
				resultVo.setYear(this.nationQueryYear);

				// 측정 정보
				resultVo.setSection(SectionCode.BMI);
				resultVo.setMeasureDate(summaryVo.getMeasureDate());
				resultVo.setValue(summaryVo.getBmi());

				if (resultVo.requestValidation()) {
					BodyMeasureGrade gradeVo = bodyMeasureMapper
							.getGradeBySection(resultVo);

					if (gradeVo != null) {
						Growth growth = new Growth();

						growth.setGradeId(gradeVo.getGradeId());
						growth.setGradeString(gradeVo.getGradeDesc());

						growth.setScore(summaryVo.getGrowthGrade());

						growth.setDescription(healthAdviserMapper
								.getHealthDeacription(gradeVo.getGradeId()));
						growth.setEatingHabits(healthAdviserMapper
								.getEatingDescription(gradeVo.getGradeId()));
						growth.setExercise(healthAdviserMapper
								.getExerciseDescription(gradeVo.getGradeId()));

						return growth;
					}
				}
			}
		}
		return null;
	}

	@Override
	public Smoke getSmoke(String userId) {

		this.g_selectedYear = null;
		
		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("userId", userId);
		pmap.put("selYear", null);
		
		List<BodyMeasureSummary> list = bodyMeasureMapper.getSummary(pmap);

		if (list != null && list.size() > 0) {

			BodyMeasureSummary summaryVo = list.get(0);
			BodyMeasureGrade gradeVo = bodyMeasureMapper
					.getSmokerGrade(summaryVo.getPpm());

			if (gradeVo != null) {
				Smoke smoke = new Smoke();

				smoke.setGradeId(gradeVo.getGradeId());
				smoke.setGradeString(gradeVo.getGradeDesc());

				smoke.setCohd(summaryVo.getCohd());
				smoke.setPpm(summaryVo.getPpm());

				return smoke;
			}
		}
		return null;
	}

private List<AverageItem> getAveragePerClass(String userId, String section) {
		
	System.out.println("getAveragePerClass start ");
	
		Student student = studentService.getStudentByUserId(userId);
		if (student != null) {

			List<BodyMeasureSummary> list = getSummaryCheckMeasureSmoke(userId,
					student);

			if (list != null && list.size() > 0) {

				BodyMeasureSummary summaryVo = list.get(0);
				StatisticsParma param = new StatisticsParma();

				param.setSex(student.getSex());
				param.setSchoolId(student.getSchoolId());
				param.setSchoolGradeId(student.getSchoolGradeId());
				param.setSection(section);
				param.setMeasureDate(summaryVo.getMeasureDate());
				param.setScale(StatisticsParma.scaleClass);
				
				List<AverageItem> averageItemsList = statisticsMapper.getAverageList(param);
				//가장 최근 이전의 측정 날짜를 구하기위한 vo생성
				BodyMeasureGrade bodyMeasureGrade = new BodyMeasureGrade();
				bodyMeasureGrade.setMeasureDate(summaryVo.getMeasureDate());
				bodyMeasureGrade.setUserId(summaryVo.getUserId());
				//이전 측정 날짜를 찾은 후 대입
				param.setMeasureDate(bodyMeasureMapper.getBeforeMeasureDate(bodyMeasureGrade));
				List<AverageItem> beforeAverageItemsList = statisticsMapper.getAverageList(param);

				if(0 < averageItemsList.size()){
					for(int i=0;i<averageItemsList.size();i++){
						//이전 데이터가 있으면 같은 반을 찾아서 이전 랭킹 대입
						if(0 < beforeAverageItemsList.size()){
							for(int j=0;j<beforeAverageItemsList.size();j++){
								if(beforeAverageItemsList.get(j).getName().equals(averageItemsList.get(i).getName())){
									averageItemsList.get(i).setBeforeRanking(beforeAverageItemsList.get(j).getRanking());
								}
							}
						}

						//이전 데이터가 없으면 현재 랭킹 대입
						if("".equals(averageItemsList.get(i).getBeforeRanking())){
							averageItemsList.get(i).setBeforeRanking(averageItemsList.get(i).getRanking());
						}
						
						if(!param.getScale().equals("") && param.getScale()!=null && param.getScale().equals("school")) {
							AES256Util aes = new AES256Util();
							try {
								averageItemsList.get(i).setName(aes.decode(averageItemsList.get(i).getName()));
							} catch (InvalidKeyException
									| UnsupportedEncodingException
									| NoSuchAlgorithmException
									| NoSuchPaddingException
									| InvalidAlgorithmParameterException
									| IllegalBlockSizeException
									| BadPaddingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				
				return averageItemsList;
			}
		}

		return null;
	}
	
	private List<AverageItem> getAveragePerSchool(String userId, String section) {
		
		System.out.println("getAveragePerSchool start ");
		Student student = studentService.getStudentByUserId(userId);
		if (student != null) {

			List<BodyMeasureSummary> list = getSummaryCheckMeasureSmoke(userId, student);

			if (list != null && list.size() > 0) {

				BodyMeasureSummary summaryVo = list.get(0);
				StatisticsParma param = new StatisticsParma();

				param.setSex(student.getSex());
				param.setSchoolId(student.getSchoolId());
				param.setSchoolGradeId(student.getSchoolGradeId());
				param.setSection(section);
				param.setMeasureDate(summaryVo.getMeasureDate());
				//param.setScale(StatisticsParma.scaleSchool); //랭킹과 history 구분  tharaud 20150522				
				param.setScale(StatisticsParma.scaleSchoolLank);
				
				List<AverageItem> averageItemsList = statisticsMapper.getAverageList(param);
				//가장 최근 이전의 측정 날짜를 구하기위한 vo생성
				System.out.println(  "학교 평균 학생ID ::: "+summaryVo.getUserId()
						 							+"  의 가장최근 이전 측정 날짜 ::: summaryVo.getMeasureDate()" 
											+ summaryVo.getMeasureDate());
				BodyMeasureGrade bodyMeasureGrade = new BodyMeasureGrade();
				bodyMeasureGrade.setMeasureDate(summaryVo.getMeasureDate());
				bodyMeasureGrade.setUserId(summaryVo.getUserId());
				//이전 측정 날짜를 찾은 후 대입
				//가장 최근 이전의 측정 날짜를 구하기위한 vo생성
				System.out.println(  ":: 이전 측정 날짜를 찾은 후 대입 :: " +bodyMeasureMapper.getBeforeMeasureDate(bodyMeasureGrade));
				param.setMeasureDate(bodyMeasureMapper.getBeforeMeasureDate(bodyMeasureGrade));
				param.setScale(StatisticsParma.scaleSchoolLank);
				System.out.println(  " ::: 이전 측정날짜 찾은 후 파라미터 ::: "+param);
				List<AverageItem> beforeAverageItemsList = statisticsMapper.getAverageList(param);

				if(0 < averageItemsList.size()){
					for(int i=0;i<averageItemsList.size();i++){
						//이전 데이터가 있으면 같은 학교를 찾아서 이전 랭킹 대입
						if(0 < beforeAverageItemsList.size()){
							for(int j=0;j<beforeAverageItemsList.size();j++){
								if(beforeAverageItemsList.get(j).getName().equals(averageItemsList.get(i).getName())){
									averageItemsList.get(i).setBeforeRanking(beforeAverageItemsList.get(j).getRanking());
								}
							}
						}
						//이전 데이터가 없으면 현재 랭킹 대입
						if("".equals(averageItemsList.get(i).getBeforeRanking())){
							averageItemsList.get(i).setBeforeRanking(averageItemsList.get(i).getRanking());
						}
						
						//if(!param.getScale().equals("") && param.getScale()!=null && param.getScale().equals("school")) {
						if(!param.getScale().equals("") && param.getScale()!=null && param.getScale().equals("schoolLank")) {
							AES256Util aes = new AES256Util();
							try {
								System.out.println(i+"순위  :::  랭킹 학교 이름 ::: "+aes.decode(averageItemsList.get(i).getName()));
								averageItemsList.get(i).setName(aes.decode(averageItemsList.get(i).getName()));
								
							} catch (InvalidKeyException
									| UnsupportedEncodingException
									| NoSuchAlgorithmException
									| NoSuchPaddingException
									| InvalidAlgorithmParameterException
									| IllegalBlockSizeException
									| BadPaddingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}

				return averageItemsList;
			}
		}

		return null;
	}

	@Override
	public List<AverageItem> getHeightAveragePerClass(String userId) {

		return getAveragePerClass(userId, SectionCode.Height);
	}

	@Override
	public List<AverageItem> getWeightAveragePerClass(String userId) {

		return getAveragePerClass(userId, SectionCode.Weight);
	}

	@Override
	public List<AverageItem> getBmiAveragePerClass(String userId) {

		return getAveragePerClass(userId, SectionCode.BMI);
	}
	
	@Override
	public List<AverageItem> getHeightAveragePerSchool(String userId) {

		return getAveragePerSchool(userId, SectionCode.Height);
	}

	@Override
	public List<AverageItem> getWeightAveragePerSchool(String userId) {

		return getAveragePerSchool(userId, SectionCode.Weight);
	}

	@Override
	public List<AverageItem> getBmiAveragePerSchool(String userId) {

		return getAveragePerSchool(userId, SectionCode.BMI);
	}

	private MeasureHistory getMeasureHistory(String userId, String historyCount, String section) {
		
		System.out.println(" ====getMeasureHistory start ==== "  );
		
		String msGradeString = "";
		Student student = studentService.getStudentByUserId(userId);
		
		

		System.out.println(" ====getMeasureHistory start ==== "  );

		if (student != null) {

			List<BodyMeasureSummary> list = getSummaryCheckMeasureSmoke(userId, student);

			if (student != null && list != null && list.size() > 0) {

				StatisticsParma param = new StatisticsParma();

				param.setSex(student.getSex());
				param.setSchoolId(student.getSchoolId());
				param.setSchoolGradeId(student.getSchoolGradeId());
				param.setClassNumber(student.getClassNumber());
				param.setSchoolYear(student.getSchoolYear()); // 2015.05.26 학기년도 추가 
				param.setSection(section);
				
			
				param.setMeasureDate(this.standardDate);

				MeasureHistory vo = new MeasureHistory();
				
				System.out.println(" ::: 섹션코드 ::: " +section );
				
				System.out.println(" ::: 전역 섹션코드 ::: " +  SectionCode.Height);

				if (section.trim().equals(SectionCode.Height) || section.equals(SectionCode.Weight)) {
					
					System.out.println(" :::::: 섹션 코드 신장, 체중일때 :::::: " + param.toString());
					 
					AverageItem itemNation = statisticsMapper.getAveragePerNation(param);

					vo.setStandardMin(itemNation.getValue());
					vo.setStandardMax(itemNation.getValue());
				}	else{
					System.out.println(" :::::: 섹션 코드 신장, 체중 아닐 때  :::::: " + param.toString());
				}

		
				// TODO : Exception 처리 요망 및, 쿼리문에 기간 설정하여 처리 하도록 수정 요망.
				int count = Integer.parseInt(historyCount);

				for (int i = 0; i < count && i < list.size(); i++) {
					MeasureItem item = new MeasureItem();

					if (section.equals(SectionCode.Height)) {
						System.out.println(i +  "번째 Height ::: "+list.get(i).getHeight());
						
						item.setValue(list.get(i).getHeight());
						System.out.println("22222 ");
						
					} else if (section.equals(SectionCode.Weight)) {
						System.out.println(i +  "번째 getWeight ::: "+list.get(i).getWeight());
						
						item.setValue(list.get(i).getWeight());
						
					} else if (section.equals(SectionCode.BMI)) {
						
						System.out.println(i +  "번째 BMI ::: "+list.get(i).getWeight());
						item.setValue(list.get(i).getBmi());

						item.setPercentageOfBodyFat(list.get(i).getPercentageOfBodyFat());

						param.setBmi(list.get(i).getBmi());
						msGradeString = statisticsMapper.getBMIIsNormal(param);

						item.setMsGradeString(msGradeString);
					}
				
					//System.out.println(" ::: 학기년도 ::: => "+list.get(i).getSchoolYear() + "  ,:: 학년 ::: " +list.get(i).getGradeId());
					//System.out.println(" ::: 학기년도 ::: => "+list.get(i).getSchoolYear());
					
					//현재 학기년도,학교 ID , 학년 , 반 이  다를때 Exception 처리  start   ====2015.06.01
					//
						System.out.println("aaaa " + "파람 학교코드   ::: "+param.getSchoolId().trim() +"  :::: i 값 :::: " +i);
						
//						if(!(param.getSchoolId().trim().equals("1")) && !(param.getSchoolId().trim().equals("2")) && !(param.getSchoolId().trim().equals("5"))  &&
//							!(param.getSchoolId().trim().equals("6")) && !(param.getSchoolId().trim().equals("19"))){
//					
							if(!(param.getSchoolId().trim().equals(list.get(i).getSchoolId().trim()))){
								param.setSchoolId(list.get(i).getSchoolId());
								System.out.println("aaa" + " 리스트 파람 학교코드  ::: ");
								System.out.println("aaa" + " 리스트 파람 학교코드  ::: " +list.get(i).getSchoolId().trim());
							}
						
//						}
								
							System.out.println("cccc " + "파람 학기년도  ::: "+param.getSchoolYear().trim() );
							
							
							
							
							if(!(param.getSchoolYear().trim().equals(list.get(i).getSchoolYear().trim()))){
								param.setSchoolYear(list.get(i).getSchoolYear());  //학기년도 추가 
								System.out.println("ccc  " + " 리스트 파람 학기년도  ::: " +list.get(i).getSchoolYear());
							}
						
					
					
						System.out.println("bbbb " + "파람 학년  ::: "+param.getSchoolGradeId().trim() );
						
						if(list.get(i).getSchoolGradeId().trim() !=null){
						
						if(!(param.getSchoolGradeId().trim().equals(list.get(i).getSchoolGradeId().trim()))){
							param.setSchoolGradeId(list.get(i).getSchoolGradeId());
							System.out.println("bbb  " + " 리스트 파람 학년  ::: " +list.get(i).getSchoolGradeId());
						}
						}
						
						
				
						
						System.out.println("dddd " + "파람 반   ::: "+param.getClassNumber().trim() );
					
						
						
						if(!(param.getClassNumber().trim().equals(list.get(i).getClassNumber().trim()))){
							param.setClassNumber(list.get(i).getClassNumber()); 
							System.out.println("ddd  " + " 리스트 파람 반  ::: " +list.get(i).getClassNumber());
						}
					
					//현재 학기년도,학교 ID , 학년 , 반 이  다를때 Exception 처리  end  ====2015.06.01
										
					param.setMeasureDate(list.get(i).getMeasureDate());
					
					

					param.setScale(StatisticsParma.scaleClass);
					System.out.println(" ::: scaleClass ::: "+param.toString());
					List<AverageItem> listClassHistory = statisticsMapper.getAverageList(param);

					
					//System.out.println( "   ::: listClassHistory :::: => "  + listClassHistory.iterator());
					
					param.setScale(StatisticsParma.scaleSchool);
					System.out.println( "::: scaleSchool:: Param :::  " +param.toString());
					List<AverageItem> listSchoolHistory = statisticsMapper.getAverageList(param);
					//AverageItem schoolStr = new AverageItem();
				//	for( int ii=0; ii<listSchoolHistory.size();ii++){
					System.out.println( i+ " 번째 ");
						
//						  System.out.println(" 111 학교 데이터" +i+"번째  ::  ==>  ::: name ::: "+listSchoolHistory.get(i).getName() +" ,::: value: ::: "+listSchoolHistory.get(i).getValue() + 
//						 "  ,:::ranking ::: "+listSchoolHistory.get(i).getRanking()+"  ,::: schoolGrade ::: "+listSchoolHistory.get(i).getSchoolGrade()+
//						 " , ::: beforeRanking :::: "+listSchoolHistory.get(i).getBeforeRanking()+ "  , ::: measureDate ::: "+listSchoolHistory.get(i).getMeasureDate()
//								  + "  , ::: schooYear ::: "+listSchoolHistory.get(i).getSchoolYear());
						
				//	}
					
//					
//					Iterator<AverageItem> iterator1 = listSchoolHistory.iterator();
//					
//				    while (iterator1.hasNext()) {
//				    	AverageItem schoolStr = (AverageItem) iterator1.next();
//				        System.out.println(" ::: name ::: "+schoolStr.getName()+" ,::: value: ::: "+schoolStr.getValue() + 
//				        							 "  ,:::ranking ::: "+schoolStr.getRanking()+"  ,::: schoolGrade ::: "+schoolStr.getSchoolGrade()+
//				        							 " , ::: beforeRanking :::: "+schoolStr.getBeforeRanking()+ " ::: measureDate ::: "+schoolStr.getMeasureDate());
//				    }
//				    
//					System.out.println( "   ::: listSchoolHistory 1 :::: => "  + listSchoolHistory.listIterator(0).toString());
					
					//System.out.println(" ::: 111 History ::: " +listSchoolHistory.toString() );

					param.setScale(StatisticsParma.scaleLocal);
					System.out.println(  " ::::  scaleLocal :: :" +param.toString());
					List<AverageItem> listLocalHistory = statisticsMapper.getAverageList(param);
					
					
					//System.out.println( "   ::: listLocalHistory :::: => "  + listLocalHistory.iterator());

					item.setDate(list.get(i).getMeasureDate());
					item.setAverageOfClass(listClassHistory.get(0).getValue());
					item.setAverageOfSchool(listSchoolHistory.get(0).getValue());
					item.setAverageOfLocal(listLocalHistory.get(0).getValue());
					System.out.println(" ::: MeasureItem :::: " + item.toString());
					vo.getList().add(item);
					
					System.out.println(" ::: MeasureHistory ::: "+vo.toString());
				}

				return vo;
			}
		}

		return null;
	}
	
	@Override
	public MeasureHistory getHeightMeasureHistory(String userId, String historyCount) {
		
		this.g_selectedYear = null;
		return getMeasureHistory(userId, historyCount, SectionCode.Height);
	}

	@Override
	public MeasureHistory getWeightMeasureHistory(String userId, String historyCount) {
		
		this.g_selectedYear = null;
		return getMeasureHistory(userId, historyCount, SectionCode.Weight);
	}

	@Override
	public MeasureHistory getHeightMeasureHistoryYear(String userId, String selectedYear) {

		this.g_selectedYear = selectedYear;
		return getMeasureHistory(userId, "12", SectionCode.Height);
	}

	@Override
	public MeasureHistory getWeightMeasureHistoryYear(String userId, String selectedYear) {
		
		this.g_selectedYear = selectedYear;
		return getMeasureHistory(userId, "12", SectionCode.Weight);
	}

	@Override
	public MeasureHistory getBmiMeasureHistoryYear(String userId, String selectedYear) {
		
		this.g_selectedYear = selectedYear;
		return getMeasureHistory(userId, "12", SectionCode.BMI);
	}

	@Override
	public String getRecentMeasureDate(String userId) {

		this.g_selectedYear = null;

		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("userId", userId);
		pmap.put("selYear", null);
		
		List<BodyMeasureSummary> list = bodyMeasureMapper.getSummaryExceptSmoke(pmap);

		if (list != null && list.size() > 0) {

			BodyMeasureSummary summaryVo = list.get(0);
			return summaryVo.getMeasureDate();
		}
		return null;
	}

}
