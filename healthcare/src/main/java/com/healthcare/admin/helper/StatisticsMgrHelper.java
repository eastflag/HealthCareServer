package com.healthcare.admin.helper;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.healthcare.admin.statistics.Statistics;
import com.healthcare.admin.statistics.StatisticsMgr;
import com.healthcare.admin.statistics.Statisticss;
import com.healthcare.common.Config;
import com.healthcare.common.SessionManager;

/**
 * 통계 데이터
 * @author Administrator
 *
 */
public class StatisticsMgrHelper {

	private StatisticsMgr statisticsMgr = null;
	
	public StatisticsMgrHelper(){
		try{
			statisticsMgr = new StatisticsMgr();
		}catch(Exception e){
        	System.out.println("setContent error"+e.getMessage());
        }
	}
	
	/**
	 * 통계데이터 조회
	 * @param req
	 * @param statistics_type
	 * @return
	 */
	public Statisticss getStatisticss(HttpServletRequest req){
		
		Statisticss statisticss = null;
		Statistics statistics = null;
		
		String[] pSearchKeys = new String[] {"school_id", "stu_sex", "grade_id", "school_name", "stu_class",
				                             "measure_date", "section", "grade_str", "school_id_sel", "contents"};
		
		SessionManager.setSearchKeys(req, pSearchKeys);
		
		HashMap searchKeys = SessionManager.getSearchKeys(req);

		String school_id    = ((String)searchKeys.get("school_id")).trim();
		String school_name  = ((String)searchKeys.get("school_name")).trim();
		String stu_sex      = ((String)searchKeys.get("stu_sex")).trim();
		String grade_id     = ((String)searchKeys.get("grade_id")).trim();
		String measure_date = ((String)searchKeys.get("measure_date")).trim();
		String section      = ((String)searchKeys.get("section")).trim();
		String grade_str    = ((String)searchKeys.get("grade_str")).trim();
		String contents     = ((String)searchKeys.get("contents")).trim();
		String stu_class    = ((String)searchKeys.get("stu_class")).trim();
		
		int startIndex = (SessionManager.getPageNo(req)-1)*Config.NUM_OF_LINE10;
		
		int count = Config.NUM_OF_LINE10;
		
		try{
			statistics = new Statistics();

			statistics.setSchool_id (school_id);
			statistics.setGrade_id (grade_id);
			statistics.setStu_sex (stu_sex);
			statistics.setGrade_str (grade_str);
			statistics.setSection (section);
			statistics.setMeasure_date (measure_date);
			statistics.setContets(contents);
			statistics.setStu_class(stu_class);
			

//			System.out.println("school_id    : " + school_id    );
//			System.out.println("stu_sex      : " + stu_sex      );
//			System.out.println("grade_id     : " + grade_id     );
//			System.out.println("measure_date : " + measure_date );
//			System.out.println("section      : " + section      );
//			System.out.println("grade_str    : " + grade_str    );
//			System.out.println("contents     : " + contents     );
//			System.out.println("stu_class    : " + stu_class    );
//			System.out.println("-------------------------------");
			
			statisticss = statisticsMgr.getStatisticss (statistics, startIndex, count);
			
		}catch(Exception e){
			System.out.println("getStatisticss error"+e.getMessage());
		}
		return statisticss;
	}

	
	/**
	 * 측정학생수조회
	 * @param req
	 * @param statistics_type
	 * @return
	 */
	public Statisticss getStudentCount(HttpServletRequest req){
		
		Statisticss statisticss = null;
		Statistics statistics = null;
		
		String[] pSearchKeys = new String[] {"school_id", "school_name", "measure_date", "section", "school_id_sel"};
		
		SessionManager.setSearchKeys(req, pSearchKeys);
		
		HashMap searchKeys = SessionManager.getSearchKeys(req);

		String school_id    = ((String)searchKeys.get("school_id")).trim();
		String measure_date = ((String)searchKeys.get("measure_date")).trim();
		String section      = ((String)searchKeys.get("section")).trim();
		
		try{
			statistics = new Statistics();

			statistics.setSchool_id (school_id);
			statistics.setSection (section);
			statistics.setMeasure_date (measure_date);
			
			statisticss = statisticsMgr.getStudentCount (statistics);
			
		}catch(Exception e){
			System.out.println("getStatisticss error"+e.getMessage());
		}
		return statisticss;
	}
	
	/**
	 * 통게데이터 엑셀
	 * @param req
	 * @param statistics_type
	 * @param totRowCnt
	 * @return
	 */
	public Statisticss getStatisticssExcel(HttpServletRequest req, int totRowCnt){
		
		Statisticss statisticss = null;
		Statistics statistics = null;
		
		HashMap searchKeys = SessionManager.getSearchKeys(req);

		String school_id    = ((String)searchKeys.get("school_id")).trim();
		String stu_sex      = ((String)searchKeys.get("stu_sex")).trim();
		String grade_id     = ((String)searchKeys.get("grade_id")).trim();
		String measure_date = ((String)searchKeys.get("measure_date")).trim();
		String section      = ((String)searchKeys.get("section")).trim();
		String grade_str    = ((String)searchKeys.get("grade_str")).trim();
		String contents     = ((String)searchKeys.get("contents")).trim();
		String stu_class    = ((String)searchKeys.get("stu_class")).trim();
		
		int startIndex = 0;
		
		try{
			statistics = new Statistics();

			statistics.setSchool_id (school_id);
			statistics.setGrade_id (grade_id);
			statistics.setStu_sex (stu_sex);
			statistics.setGrade_str (grade_str);
			statistics.setSection (section);
			statistics.setMeasure_date (measure_date);
			statistics.setContets(contents);
			statistics.setStu_class(stu_class);

//			System.out.println("school_id    : " + school_id    );
//			System.out.println("stu_sex      : " + stu_sex      );
//			System.out.println("grade_id     : " + grade_id     );
//			System.out.println("measure_date : " + measure_date );
//			System.out.println("section      : " + section      );
//			System.out.println("grade_str    : " + grade_str    );
//			System.out.println("contents     : " + contents     );
//			System.out.println("stu_class    : " + stu_class    );
//			System.out.println("-------------------------------");
			
			statisticss = statisticsMgr.getStatisticss(statistics, startIndex, totRowCnt);
			
		}catch(Exception e){
			System.out.println("getStatisticss error"+e.getMessage());
		}
		return statisticss;
	}

	
	/**
	 * 통계데이터 조회 : 평균통계
	 * @param req
	 * @param statistics_type
	 * @return
	 */
	public Statisticss getStatisticssAVG(HttpServletRequest req){
		
		Statisticss statisticss = null;
		Statistics statistics = null;
		
		String[] pSearchKeys = new String[] {"school_id", "grade_id", "school_name", "stu_class",
				                             "measure_date", "section", "school_id_sel"};
		
		SessionManager.setSearchKeys(req, pSearchKeys);
		
		HashMap searchKeys = SessionManager.getSearchKeys(req);

		String school_id    = ((String)searchKeys.get("school_id")).trim();
		String school_name  = ((String)searchKeys.get("school_name")).trim();
		String grade_id     = ((String)searchKeys.get("grade_id")).trim();
		String measure_date = ((String)searchKeys.get("measure_date")).trim();
		String section      = ((String)searchKeys.get("section")).trim();
		String stu_class    = ((String)searchKeys.get("stu_class")).trim();
		
		int startIndex = (SessionManager.getPageNo(req)-1)*Config.NUM_OF_LINE20;
		
		int count = Config.NUM_OF_LINE20;
		
		try{
			statistics = new Statistics();

			statistics.setSchool_id (school_id);
			statistics.setGrade_id (grade_id);
			statistics.setSection (section);
			statistics.setMeasure_date (measure_date);
			statistics.setStu_class(stu_class);
			

//			System.out.println("school_id    : " + school_id    );
//			System.out.println("grade_id     : " + grade_id     );
//			System.out.println("measure_date : " + measure_date );
//			System.out.println("section      : " + section      );
//			System.out.println("stu_class    : " + stu_class    );
//			System.out.println("-------------------------------");
			
			statisticss = statisticsMgr.getStatisticssAVG (statistics, startIndex, count);
			
		}catch(Exception e){
			System.out.println("getStatisticss error"+e.getMessage());
		}
		return statisticss;
	}
	
	/**
	 * 통게데이터 엑셀 : 평균통계
	 * @param req
	 * @param statistics_type
	 * @param totRowCnt
	 * @return
	 */
	public Statisticss getStatisticssAVGExcel(HttpServletRequest req, int totRowCnt){
		
		Statisticss statisticss = null;
		Statistics statistics = null;
		
		HashMap searchKeys = SessionManager.getSearchKeys(req);

		String school_id    = ((String)searchKeys.get("school_id")).trim();
		String school_name  = ((String)searchKeys.get("school_name")).trim();
		String grade_id     = ((String)searchKeys.get("grade_id")).trim();
		String measure_date = ((String)searchKeys.get("measure_date")).trim();
		String section      = ((String)searchKeys.get("section")).trim();
		String stu_class    = ((String)searchKeys.get("stu_class")).trim();
		
		int startIndex = 0;
		
		try{
			statistics = new Statistics();

			statistics.setSchool_id (school_id);
			statistics.setGrade_id (grade_id);
			statistics.setSection (section);
			statistics.setMeasure_date (measure_date);
			statistics.setStu_class(stu_class);

//			System.out.println("school_id    : " + school_id    );
//			System.out.println("grade_id     : " + grade_id     );
//			System.out.println("measure_date : " + measure_date );
//			System.out.println("section      : " + section      );
//			System.out.println("stu_class    : " + stu_class    );
//			System.out.println("-------------------------------");
			
			statisticss = statisticsMgr.getStatisticssAVG(statistics, startIndex, totRowCnt);
			
		}catch(Exception e){
			System.out.println("getStatisticss error"+e.getMessage());
		}
		return statisticss;
	}
}
