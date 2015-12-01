package com.healthcare.admin.statistics;

public class StatisticsMgr {
	
	/**
	 * 통계 데이터 추출
	 * @param statistics
	 * @param startIndex
	 * @param count
	 * @return
	 */
	public Statisticss getStatisticss(Statistics statistics, int startIndex, int count){
		Statisticss statisticss = null;
		
		try{
			StatisticsDAO statisticsDAO = new StatisticsDAO();
			
			statisticss = new Statisticss(statisticsDAO.getStatisticss(statistics, startIndex, count)); 
			statisticss.setTotRowCnt(statisticsDAO.getStatisticssTotRowCnt(statistics));
			
			//System.out.println("######### " + statisticss.getTotRowCnt());
			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		return statisticss;
	}
	
	/**
	 * 통계 데이터 추출 : 평균통계
	 * @param statisticsAVG
	 * @param startIndex
	 * @param count
	 * @return
	 */
	public Statisticss getStatisticssAVG(Statistics statistics, int startIndex, int count){
		Statisticss statisticss = null;

		String[] retVal = new String[2];
		
		try{
			StatisticsDAO statisticsDAO = new StatisticsDAO();
			
			statisticss = new Statisticss(statisticsDAO.getStatisticssAVG(statistics, startIndex, count)); 
			statisticss.setTotRowCnt(statisticsDAO.getStatisticssAVGTotRowCnt(statistics));
			
			//System.out.println("######### " + statisticss.getTotRowCnt());
			
			if (statisticss.getTotRowCnt() > 0) {

				retVal = statisticsDAO.getAllAVG(statistics, "F");
				statisticss.setAllFemaleAvgH(retVal[0]);
				statisticss.setAllFemaleAvgW(retVal[1]);
				
				retVal = statisticsDAO.getAllAVG(statistics, "M");
				statisticss.setAllMaleAvgH(retVal[0]);
				statisticss.setAllMaleAvgW(retVal[1]);
			}
			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		return statisticss;
	}

	public Statisticss getStudentCount(Statistics statistics) {
		Statisticss statisticss = null;
		
		try{
			StatisticsDAO statisticsDAO = new StatisticsDAO();
			
			statisticss = new Statisticss(statisticsDAO.getStudentCount(statistics)); 
			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		return statisticss;
	}
}
