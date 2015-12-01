package com.healthcare.admin.statistics;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.healthcare.common.AES256Util;
import com.healthcare.common.DBConnectionManager;

public class StatisticsDAO {
	
	private DBConnectionManager pool = null;
	private AES256Util aes = new AES256Util();
	
	public StatisticsDAO(){
		try{
			pool = DBConnectionManager.getInstance();
		}catch(Exception e){
			System.out.println("Error : DBConnectionManager !!");
		}
	}

	/**
	 * 통계 데이터 추출
	 * @param statistics
	 * @param startIndex
	 * @param count
	 * @return
	 * @throws SQLException
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	@SuppressWarnings("unchecked")
	public Collection getStatisticss(Statistics statistics, int startIndex, int count) throws SQLException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String joinqu = "";
        String joinqu1 = "";
        String joinqu2 = "";

        boolean isBMIQuery = false;
        boolean isExistSchoolId = true;
        boolean isExistMD = true;
        boolean isExistSection = true;
        String pContentsType = "LIST";

        // 필수
        if (statistics.getSchool_id() != null && !statistics.getSchool_id().equals("") && !statistics.getSchool_id().equals(",")) {
			joinqu += " AND B.SCHOOL_ID = '" + statistics.getSchool_id() + "' \n";
			joinqu1 += " AND S.SCHOOL_ID = '" + statistics.getSchool_id() + "' \n";
		//} else {
		//	isExistSchoolId = false;
		}
        
        if (statistics.getMeasure_date() != null && !statistics.getMeasure_date().equals("")) {
			joinqu += " AND B.MEASURE_DATE='" + statistics.getMeasure_date() + "' \n";
			joinqu2 += " AND B.MEASURE_DATE='" + statistics.getMeasure_date() + "' \n";
			
			String measure_date = statistics.getMeasure_date();
			int measure_year = Integer.parseInt(measure_date.substring(0, 4));
			String measure_month = measure_date.substring(4, 6);
			
			if(measure_month.equals("01") || measure_month.equals("02")) {
				measure_year--;
			}
			joinqu1 += " and S.School_Year= '"+Integer.toString(measure_year)+"'";
		} else {
			isExistMD = false;
		}
        
        if (statistics.getSection() != null && !statistics.getSection().equals("")) {
        	
        	if (statistics.getSection().equals("BMI")) {
        		isBMIQuery = true;
        	} else if (statistics.getSection().equals("SMOKE")) {
        		isBMIQuery = false;
        	}
        	
		} else {
			isExistSection = false;
		}
        
        if (statistics.getContets() != null && !statistics.getContets().equals("")) {
        	pContentsType = statistics.getContets();
		}
        
        // 선택
        if (statistics.getGrade_id() != null && !statistics.getGrade_id().equals("")) {
			joinqu += " AND B.GRADE_ID = '" + statistics.getGrade_id() + "' \n";
			joinqu1 += " AND S.GRADE_ID = '" + statistics.getGrade_id() + "' \n";
		}
        
        if (statistics.getStu_class() != null && !statistics.getStu_class().equals("")) {
			joinqu += " AND B.CLASS = '" + statistics.getStu_class() + "' \n";
			joinqu1 += " AND S.CLASS = '" + statistics.getStu_class() + "' \n";
		}
        
        if (statistics.getStu_sex() != null && !statistics.getStu_sex().equals("")) {
			joinqu += " AND A.STU_SEX = '" + statistics.getStu_sex() + "' \n";
			joinqu1 += " AND A.STU_SEX = '" + statistics.getStu_sex() + "' \n";
		}
                
        if (statistics.getGrade_str() != null && !statistics.getGrade_str().equals("")) {
        	
        	if (isBMIQuery) {
    			joinqu += " AND E.SRANDARD_GRADE LIKE '" + statistics.getGrade_str() + "%' \n";
        	} else {
    			joinqu += " AND D.DISCRIPTION = '" + statistics.getGrade_str() + "' \n";
        	}
		}
        
        
        
        //신장체중 학생명단
        StringBuffer qrBmi = new StringBuffer()
	        .append("SELECT DATE_FORMAT(B.MEASURE_DATE, '%Y년 %m월') AS YEARMONTH,                      \n")
	        .append("       B.SCHOOL_ID,                                                                \n")
	        .append("       G.SCHOOL_NAME,                                                              \n")
	        .append("       B.GRADE_ID,                                                                 \n")
	        .append("       SUBSTR(H.DISCRIPTION, INSTR(H.DISCRIPTION, ' ')) AS SCHHOL_GRADE_STR,       \n")
	        .append("       B.CLASS,                                                                    \n")
	        .append("       A.STU_NAME,                                                                 \n")
	        .append("       A.STU_SEX,                                                                  \n")
	        .append("       TRUNCATE(C.HEIGHT, 1) AS HEIGHT,                                            \n")
	        .append("       TRUNCATE(C.WEIGHT, 1) AS WEIGHT,                                            \n")
	        .append("       TRUNCATE(C.BMI, 1)     AS DATA,                                             \n")
	        .append("       SUBSTR(E.SRANDARD_GRADE,  1, INSTR(E.SRANDARD_GRADE, ' ') - 1) AS GRADE_STR \n")
	        .append("  FROM STUDENT_INFO A,                                                             \n")
	        .append("       BODY_MEASURE_PLAN B,                                                        \n")
	        .append("       INBODY_INFO C,                                                              \n")
	        .append("       SCHOOL_INFO G,                                                              \n")
	        .append("       MS_GRADE_DATA E,                                                            \n")
	        .append("       STANDARD_DATA F,                                                            \n")
	        .append("       SCHOOL_GRADE H                                                              \n")
	        .append(" WHERE 1 = 1                                                                       \n")
	        .append("   AND A.STUDENT_ID=B.STUDENT_ID                                                   \n")
	        .append("   AND B.INBODY_SEQ=C.INBODY_SEQ                                                   \n")
	        .append("   AND B.SCHOOL_ID=G.SCHOOL_ID                                                     \n")
	        .append("   AND B.GRADE_ID = E.GRADE_ID                                                     \n")
	        .append("   AND B.GRADE_ID=H.GRADE_ID                                                       \n")
	        .append("   AND A.STU_SEX=E.SEX                                                             \n")
	        .append("   AND E.MS_GRADE_ID = F.MS_GRADE_ID                                               \n")
	        .append("   AND (C.BMI >= F.START_INT AND C.BMI < F.END_INT)                                   \n")
	        .append("   AND E.SECTION='BMI'                                                             \n")
	        .append("   AND G.SCHOOL_ID NOT IN ( '2', '5', '6')                                     \n")
			.append(joinqu)
	        .append("   union                                    \n")
	        .append(" select '-' as YEARMONTH,  \n")
	        .append(" S.School_ID, \n")
	        .append(" G.School_Name, \n")
	        .append(" S.Grade_ID, \n")
	        .append(" SUBSTR(H.DISCRIPTION, INSTR(H.DISCRIPTION, ' ')) AS SCHHOL_GRADE_STR, \n")
	        .append(" S.Class, \n")
	        .append(" A.STU_Name, \n")
	        .append(" A.stu_sex, \n")
	        .append(" '' as HEIGHT, \n")
	        .append(" '' as WEIGHT, \n")
	        .append(" '' as DATA, \n")
	        .append(" '' as GRADE_STR \n")
	        .append(" from school_register S \n")
	        .append(" join STUDENT_INFO A on S.Student_ID = A.Student_ID \n")
	        .append(" left outer join BODY_MEASURE_PLAN B on S.Student_ID = B.Student_ID ")
			.append(joinqu2)
	        .append(" join SCHOOL_INFO G on S.school_id=G.school_id \n")
	        .append(" join SCHOOL_GRADE H on S.Grade_ID = H.Grade_ID \n")
	        .append(" where S.SCHOOL_ID NOT IN ( '2', '5', '6') and (B.MEASURE_DATE is null || B.INBODY_SEQ is null) \n")
			.append(joinqu1)
			.append("  ORDER BY SCHOOL_NAME desc ,GRADE_ID, CLASS, STU_SEX, STU_NAME              \n");
        // 
        String queryStrBmi = qrBmi.toString();
        

        //신장체중 학생분포
        StringBuffer qrBmiCount = new StringBuffer()
        	.append("SELECT *,                        													\n")
        	.append("		round(((COUNT/stu_cnt)*100),2) as percentage                         		\n")
        	.append(" 		from (                      												\n")
        	.append("SELECT DATE_FORMAT(B.MEASURE_DATE, '%Y년 %m월') AS YEARMONTH,                      	\n")
	        .append("       B.SCHOOL_ID,                                                                \n")
	        .append("       G.SCHOOL_NAME,                                                              \n")
	        .append("       B.GRADE_ID,                                                                 \n")
	        .append("       SUBSTR(H.DISCRIPTION, INSTR(H.DISCRIPTION, ' ')) AS SCHHOL_GRADE_STR,       \n")
	        .append("       F.START_INT,                                                                \n")
	        .append("       A.STU_SEX,                                                                  \n")
	        .append("       COUNT(*) AS COUNT,                                                          \n")
	        .append("       SUBSTR(E.SRANDARD_GRADE,  1, INSTR(E.SRANDARD_GRADE, ' ') - 1) AS GRADE_STR, \n")
	        .append("       (select count(*) from BODY_MEASURE_PLAN where school_id=B.School_ID and grade_id=B.GRADE_ID and  MEASURE_DATE=B.MEASURE_DATE) as stu_cnt \n")
	        .append("  FROM STUDENT_INFO A,                                                             \n")
	        .append("       BODY_MEASURE_PLAN B,                                                        \n")
	        .append("       INBODY_INFO C,                                                              \n")
	        .append("       SCHOOL_INFO G,                                                              \n")
	        .append("       MS_GRADE_DATA E,                                                            \n")
	        .append("       STANDARD_DATA F,                                                            \n")
	        .append("       SCHOOL_GRADE H                                                              \n")
	        .append(" WHERE 1 = 1                                                                       \n")
	        .append("   AND A.STUDENT_ID=B.STUDENT_ID                                                   \n")
	        .append("   AND B.INBODY_SEQ=C.INBODY_SEQ                                                   \n")
	        .append("   AND B.SCHOOL_ID=G.SCHOOL_ID                                                     \n")
	        .append("   AND B.GRADE_ID = E.GRADE_ID                                                     \n")
	        .append("   AND B.GRADE_ID=H.GRADE_ID                                                       \n")
	        .append("   AND A.STU_SEX=E.SEX                                                             \n")
	        .append("   AND E.MS_GRADE_ID = F.MS_GRADE_ID                                               \n")
	        .append("   AND (C.BMI >= F.START_INT AND C.BMI < F.END_INT)                                   \n")
	        .append("   AND E.SECTION='BMI'                                                             \n")
	        .append("   AND G.SCHOOL_ID NOT IN ( '2', '5', '6')                                     \n")
			.append(joinqu)
			.append(" GROUP BY B.SCHOOL_ID, B.GRADE_ID, A.STU_SEX, GRADE_STR                            \n")
			.append(" ORDER BY G.SCHOOL_NAME, B.GRADE_ID, A.STU_SEX, F.START_INT                        \n")
			.append(" ) Z                       \n");
        String queryStrBmiCount = qrBmiCount.toString();
        
        // 흡연 학생명단
        StringBuffer qrSmoke = new StringBuffer()
	        .append("SELECT DATE_FORMAT(B.MEASURE_DATE, '%Y년 %m월') AS YEARMONTH,                \n")
	        .append("       B.SCHOOL_ID,                                                          \n")
	        .append("       G.SCHOOL_NAME,                                                        \n")
	        .append("       B.GRADE_ID,                                                           \n")
	        .append("       SUBSTR(H.DISCRIPTION, INSTR(H.DISCRIPTION, ' ')) AS SCHHOL_GRADE_STR, \n")
	        .append("       B.CLASS,                                                              \n")
	        .append("       A.STU_NAME,                                                           \n")
	        .append("       A.STU_SEX,                                                            \n")
	        .append("       '-' AS HEIGHT,                                                        \n")
	        .append("       '-' AS WEIGHT,                                                        \n")
	        .append("       C.PPM AS DATA,                                                        \n")
	        .append("       D.DISCRIPTION  AS GRADE_STR                                           \n")
	        .append("  FROM STUDENT_INFO A,                                                       \n")
	        .append("       BODY_MEASURE_PLAN B,                                                  \n")
	        .append("       SMOKE_INFO C,                                                         \n")
	        .append("       SMOKE_DATA D,                                                         \n")
	        .append("       SCHOOL_INFO G,                                                        \n")
	        .append("       SCHOOL_GRADE H                                                        \n")
	        .append(" WHERE 1 = 1                                                                 \n")
	        .append("   AND A.STUDENT_ID=B.STUDENT_ID                                             \n")
	        .append("   AND B.SMOKE_SEQ=C.SMOKE_SEQ                                               \n")
	        .append("   AND B.SCHOOL_ID=G.SCHOOL_ID                                               \n")
	        .append("   AND B.GRADE_ID=H.GRADE_ID                                                 \n")
	        .append("   AND (C.PPM BETWEEN D.START_INT AND D.END_INT)                             \n")
	        .append("   AND G.SCHOOL_ID NOT IN ( '2', '5', '6')                                     \n")
			.append(joinqu)
	        .append("   union                                    \n")
	        .append(" select '-' as YEARMONTH,  \n")
	        .append(" S.School_ID, \n")
	        .append(" G.School_Name, \n")
	        .append(" S.Grade_ID, \n")
	        .append(" SUBSTR(H.DISCRIPTION, INSTR(H.DISCRIPTION, ' ')) AS SCHHOL_GRADE_STR, \n")
	        .append(" S.Class, \n")
	        .append(" A.STU_Name, \n")
	        .append(" A.stu_sex, \n")
	        .append(" '' as HEIGHT, \n")
	        .append(" '' as WEIGHT, \n")
	        .append(" '' as DATA, \n")
	        .append(" '' as GRADE_STR \n")
	        .append(" from school_register S \n")
	        .append(" join STUDENT_INFO A on S.Student_ID = A.Student_ID \n")
	        .append(" left outer join BODY_MEASURE_PLAN B on S.Student_ID = B.Student_ID ")
			.append(joinqu2)
	        .append(" join SCHOOL_INFO G on S.school_id=G.school_id \n")
	        .append(" join SCHOOL_GRADE H on S.Grade_ID = H.Grade_ID \n")
	        .append(" where S.SCHOOL_ID NOT IN ( '2', '5', '6') and (B.MEASURE_DATE is null || B.SMOKE_SEQ is null) \n")
			.append(joinqu1)
	        .append("   and S.grade_id not in ('1','2','3','4') \n")
			.append(" ORDER BY SCHOOL_NAME desc , GRADE_ID, CLASS, STU_SEX, STU_NAME          \n");
	    String queryStrSmoke = qrSmoke.toString();
        
	    //흡연 학생분포
        StringBuffer qrSmokeCount = new StringBuffer()
	    	.append("SELECT *,                        													\n")
	    	.append("		round(((COUNT/stu_cnt)*100),2) as percentage                         		\n")
	    	.append(" 		from (                      												\n")
	        .append("SELECT DATE_FORMAT(B.MEASURE_DATE, '%Y년 %m월') AS YEARMONTH,                \n")
	        .append("       B.SCHOOL_ID,                                                          \n")
	        .append("       G.SCHOOL_NAME,                                                        \n")
	        .append("       B.GRADE_ID,                                                           \n")
	        .append("       SUBSTR(H.DISCRIPTION, INSTR(H.DISCRIPTION, ' ')) AS SCHHOL_GRADE_STR, \n")
	        .append("       A.STU_SEX,                                                            \n")
	        .append("       D.START_INT,                                                          \n")
	        .append("       COUNT(*) AS COUNT,                                                    \n")
	        .append("       D.DISCRIPTION  AS GRADE_STR,                                           \n")
	        .append("       (select count(*) from BODY_MEASURE_PLAN where school_id=B.School_ID and grade_id=B.GRADE_ID and  MEASURE_DATE=B.MEASURE_DATE) as stu_cnt \n")
	        .append("  FROM STUDENT_INFO A,                                                       \n")
	        .append("       BODY_MEASURE_PLAN B,                                                  \n")
	        .append("       SMOKE_INFO C,                                                         \n")
	        .append("       SMOKE_DATA D,                                                         \n")
	        .append("       SCHOOL_INFO G,                                                        \n")
	        .append("       SCHOOL_GRADE H                                                        \n")
	        .append(" WHERE 1 = 1                                                                 \n")
	        .append("   AND A.STUDENT_ID=B.STUDENT_ID                                             \n")
	        .append("   AND B.SMOKE_SEQ=C.SMOKE_SEQ                                               \n")
	        .append("   AND B.SCHOOL_ID=G.SCHOOL_ID                                               \n")
	        .append("   AND B.GRADE_ID=H.GRADE_ID                                                 \n")
	        .append("   AND (C.PPM BETWEEN D.START_INT AND D.END_INT)                             \n")
	        .append("   AND G.SCHOOL_ID NOT IN ( '2', '5', '6')                                     \n")
			.append(joinqu)
			.append(" GROUP BY B.SCHOOL_ID, B.GRADE_ID, A.STU_SEX, GRADE_STR                      \n")
			.append(" ORDER BY G.SCHOOL_NAME, B.GRADE_ID, A.STU_SEX,D.START_INT                   \n")
			.append(" ) Z                  \n");
	    String queryStrSmokeCount = qrSmokeCount.toString();

        ArrayList statisticss = new ArrayList();
        ArrayList statisticss_result = new ArrayList();
        
        if (isExistSchoolId && isExistMD && isExistSection) {
	        try {
	        	
	        	con = pool.getConnection();
	        	

        		if ("LIST".equals(pContentsType)) {

    	        	if (isBMIQuery) {
		            	pstmt = con.prepareStatement(queryStrBmi);
		            	System.out.println("#### queryStrBmi \n" + queryStrBmi);
    	        	} else {
		            	pstmt = con.prepareStatement(queryStrSmoke);
		            	System.out.println("#### queryStrSmoke \n" + queryStrSmoke);
    	        	}
    	        	
    	        	rs = pstmt.executeQuery();
    	        	
    	    		// startIndex 파라메타 수만큼 Skip한다.
    				//while (startIndex-- > 0 && rs.next());
    	        	//while (count-- > 0 && rs.next())
        	        	while (rs.next())
    	        	{
    	        		statistics = new Statistics();
    	
    	        		statistics.setYearMonth       (rs.getString("YEARMONTH"))       ;
    	        		statistics.setSchool_id       (rs.getString("SCHOOL_ID"))       ;
    	        		statistics.setSchool_name     (aes.decode(rs.getString("SCHOOL_NAME")))     ;
    	        		statistics.setGrade_id        (rs.getString("GRADE_ID"))        ;
    	        		statistics.setSchool_class    (rs.getString("CLASS"))           ;
    	        		statistics.setStu_name        (aes.decode(rs.getString("STU_NAME")))        ;
    	        		statistics.setStu_sex         (rs.getString("STU_SEX"))         ;
    	        		statistics.setHeight_str      (rs.getString("HEIGHT"))          ;
    	        		statistics.setWeight_str      (rs.getString("WEIGHT"))          ;
    	        		statistics.setData            (rs.getString("DATA"))            ;
    	        		statistics.setGrade_str       (rs.getString("GRADE_STR"))       ;
    	        		statistics.setSchhol_grade_str(rs.getString("SCHHOL_GRADE_STR"));
    	        		
    					statisticss.add(statistics);
    	        	}
        	        	final int size = statisticss.size();
        	        
        	        Collections.sort(statisticss, new Comparator<Statistics>() {

						@Override
						public int compare(Statistics arg0, Statistics arg1) {
							
							int ret = 0;
							
							for(int i=0; i<size; i++) {
								if(arg0.getSchool_name().compareTo(arg1.getSchool_name())==0) {
									if(arg0.getGrade_id().compareTo(arg1.getGrade_id())==0) {
										if(arg0.getSchool_class().compareTo(arg1.getSchool_class())==0) {
											if(arg0.getStu_sex().compareTo(arg1.getStu_sex())==0) {
													ret = arg0.getStu_name().compareTo(arg1.getStu_name());
											}
										}
									}
								}
							}
							
							return ret;
						}
					});
        	        
        	        int fori = startIndex+count;
        	        if(size < startIndex+count ){
        	        	fori = size;
        	        }
        	        for(int i=startIndex; i<fori; i++) {
    	        		statisticss_result.add(statisticss.get(i));    
        	        }
        	        statisticss = statisticss_result;
        	        
    	        	
        		} else {

    	        	if (isBMIQuery) {
		            	pstmt = con.prepareStatement(queryStrBmiCount);
		            	System.out.println("#### queryStrBmiCount \n" + queryStrBmiCount);
    	        	} else {
		            	pstmt = con.prepareStatement(queryStrSmokeCount);
		            	System.out.println("#### queryStrSmokeCount \n" + queryStrSmokeCount);
    	        	}
    	        	
    	        	rs = pstmt.executeQuery();
    	        	
    	    		// startIndex 파라메타 수만큼 Skip한다.
    				while (startIndex-- > 0 && rs.next());
    	        	while (count-- > 0 && rs.next())
    	        	{
    	        		statistics = new Statistics();
    	
    	        		statistics.setYearMonth       (rs.getString("YEARMONTH"))       ;
    	        		statistics.setSchool_id       (rs.getString("SCHOOL_ID"))       ;
    	        		statistics.setSchool_name     (aes.decode(rs.getString("SCHOOL_NAME")))     ;
    	        		statistics.setGrade_id        (rs.getString("GRADE_ID"))        ;
    	        		statistics.setStu_sex         (rs.getString("STU_SEX"))         ;
    	        		statistics.setCount           (rs.getInt   ("COUNT"))           ;
    	        		statistics.setGrade_str       (rs.getString("GRADE_STR"))       ;
    	        		statistics.setSchhol_grade_str(rs.getString("SCHHOL_GRADE_STR"));
    	        		statistics.setPercentage(rs.getString("percentage"));
    	        		
    					statisticss.add(statistics);
    	        	}
        		}
        		
	        }catch(SQLException se) {
				System.out.println(se);
			}finally {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con != null) con.close();
			}
	    	
	    }
        return statisticss;
	}
	

	public int getStatisticssTotRowCnt(Statistics statistics) throws SQLException{
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String joinqu = "";
        String joinqu1 = "";
        String joinqu2 = "";

        boolean isBMIQuery = false;
        boolean isExistSchoolId = true;
        boolean isExistMD = true;
        boolean isExistSection = true;
        String pContentsType = "LIST";

        // 필수
        if (statistics.getSchool_id() != null && !statistics.getSchool_id().equals("") && !statistics.getSchool_id().equals(",")) {
			joinqu += " AND B.SCHOOL_ID = '" + statistics.getSchool_id() + "' \n";
			joinqu1 += " AND S.SCHOOL_ID = '" + statistics.getSchool_id() + "' \n";
		//} else {
		//	isExistSchoolId = false;
		}
        if (statistics.getMeasure_date() != null && !statistics.getMeasure_date().equals("")) {
			joinqu += " AND B.MEASURE_DATE='" + statistics.getMeasure_date() + "' \n";
			joinqu2 += " AND B.MEASURE_DATE='" + statistics.getMeasure_date() + "' \n";
			
			String measure_date = statistics.getMeasure_date();
			int measure_year = Integer.parseInt(measure_date.substring(0, 4));
			String measure_month = measure_date.substring(4, 6);
			
			if(measure_month.equals("01") || measure_month.equals("02")) {
				measure_year--;
			}
			joinqu1 += " and S.School_Year= '"+Integer.toString(measure_year)+"'";
		} else {
			isExistMD = false;
		}
        
        if (statistics.getSection() != null && !statistics.getSection().equals("")) {
        	
        	if (statistics.getSection().equals("BMI")) {
        		isBMIQuery = true;
        	} else if (statistics.getSection().equals("SMOKE")) {
        		isBMIQuery = false;
        	}
        	
		} else {
			isExistSection = false;
		}
        
        if (statistics.getContets() != null && !statistics.getContets().equals("")) {
        	pContentsType = statistics.getContets();
		}
        
        // 선택
        if (statistics.getGrade_id() != null && !statistics.getGrade_id().equals("")) {
			joinqu += " AND B.GRADE_ID = '" + statistics.getGrade_id() + "' \n";
			joinqu1 += " AND S.GRADE_ID = '" + statistics.getGrade_id() + "' \n";
		}
        
        if (statistics.getStu_class() != null && !statistics.getStu_class().equals("")) {
			joinqu += " AND B.CLASS = '" + statistics.getStu_class() + "' \n";
			joinqu1 += " AND S.CLASS = '" + statistics.getStu_class() + "' \n";
		}
        
        if (statistics.getStu_sex() != null && !statistics.getStu_sex().equals("")) {
			joinqu += " AND A.STU_SEX = '" + statistics.getStu_sex() + "' \n";
			joinqu1 += " AND A.STU_SEX = '" + statistics.getStu_sex() + "' \n";
		}
                
        if (statistics.getGrade_str() != null && !statistics.getGrade_str().equals("")) {
        	
        	if (isBMIQuery) {
    			joinqu += " AND E.SRANDARD_GRADE LIKE '" + statistics.getGrade_str() + "%' \n";
        	} else {
    			joinqu += " AND D.DISCRIPTION = '" + statistics.getGrade_str() + "' \n";
        	}
		}
        
        StringBuffer qrBmi = new StringBuffer()
	        .append("SELECT COUNT(*) AS cnt from (                                                      \n")
	        .append("  select A.student_id FROM STUDENT_INFO A,                                                             \n")
	        .append("       BODY_MEASURE_PLAN B,                                                        \n")
	        .append("       INBODY_INFO C,                                                              \n")
	        .append("       SCHOOL_INFO G,                                                              \n")
	        .append("       MS_GRADE_DATA E,                                                            \n")
	        .append("       STANDARD_DATA F,                                                            \n")
	        .append("       SCHOOL_GRADE H                                                              \n")
	        .append(" WHERE 1 = 1                                                                       \n")
	        .append("   AND A.STUDENT_ID=B.STUDENT_ID                                                   \n")
	        .append("   AND B.INBODY_SEQ=C.INBODY_SEQ                                                   \n")
	        .append("   AND B.SCHOOL_ID=G.SCHOOL_ID                                                     \n")
	        .append("   AND B.GRADE_ID = E.GRADE_ID                                                     \n")
	        .append("   AND B.GRADE_ID=H.GRADE_ID                                                       \n")
	        .append("   AND A.STU_SEX=E.SEX                                                             \n")
	        .append("   AND E.MS_GRADE_ID = F.MS_GRADE_ID                                               \n")
	        .append("   AND (C.BMI >= F.START_INT AND C.BMI < F.END_INT)                                   \n")
	        .append("   AND E.SECTION='BMI'                                                             \n")
	        .append("   AND G.SCHOOL_ID NOT IN ( '2', '5', '6')                                     \n")
			.append(joinqu)
        .append("   union                                    \n")
        .append(" select A.student_id  \n")
        .append(" from school_register S \n")
        .append(" join STUDENT_INFO A on S.Student_ID = A.Student_ID \n")
        .append(" left outer join BODY_MEASURE_PLAN B on S.Student_ID = B.Student_ID ")
		.append(joinqu2)
        .append(" join SCHOOL_INFO G on S.school_id=G.school_id \n")
        .append(" join SCHOOL_GRADE H on S.Grade_ID = H.Grade_ID \n")
        .append(" where S.SCHOOL_ID NOT IN ( '2', '5', '6') and (B.MEASURE_DATE is null || B.INBODY_SEQ is null) \n")
		.append(joinqu1)
        .append(" ) Z \n");
        String queryStrBmi = qrBmi.toString();
        

        StringBuffer qrBmiCount = new StringBuffer()
        	.append("SELECT COUNT(*) AS cnt  FROM (                                                     \n")
	        .append("SELECT DATE_FORMAT(B.MEASURE_DATE, '%Y년 %m월') AS YEARMONTH,                      \n")
	        .append("       B.SCHOOL_ID,                                                                \n")
	        .append("       G.SCHOOL_NAME,                                                              \n")
	        .append("       B.GRADE_ID,                                                                 \n")
	        .append("       SUBSTR(H.DISCRIPTION, INSTR(H.DISCRIPTION, ' ')) AS SCHHOL_GRADE_STR,       \n")
	        .append("       F.START_INT,                                                                \n")
	        .append("       A.STU_SEX,                                                                  \n")
	        .append("       COUNT(*) AS COUNT,                                                          \n")
	        .append("       SUBSTR(E.SRANDARD_GRADE,  1, INSTR(E.SRANDARD_GRADE, ' ') - 1) AS GRADE_STR \n")
	        .append("  FROM STUDENT_INFO A,                                                             \n")
	        .append("       BODY_MEASURE_PLAN B,                                                        \n")
	        .append("       INBODY_INFO C,                                                              \n")
	        .append("       SCHOOL_INFO G,                                                              \n")
	        .append("       MS_GRADE_DATA E,                                                            \n")
	        .append("       STANDARD_DATA F,                                                            \n")
	        .append("       SCHOOL_GRADE H                                                              \n")
	        .append(" WHERE 1 = 1                                                                       \n")
	        .append("   AND A.STUDENT_ID=B.STUDENT_ID                                                   \n")
	        .append("   AND B.INBODY_SEQ=C.INBODY_SEQ                                                   \n")
	        .append("   AND B.SCHOOL_ID=G.SCHOOL_ID                                                     \n")
	        .append("   AND B.GRADE_ID = E.GRADE_ID                                                     \n")
	        .append("   AND B.GRADE_ID=H.GRADE_ID                                                       \n")
	        .append("   AND A.STU_SEX=E.SEX                                                             \n")
	        .append("   AND E.MS_GRADE_ID = F.MS_GRADE_ID                                               \n")
	        .append("   AND (C.BMI >= F.START_INT AND C.BMI < F.END_INT)                                   \n")
	        .append("   AND E.SECTION='BMI'                                                             \n")
	        .append("   AND G.SCHOOL_ID NOT IN ( '2', '5', '6')                                     \n")
			.append(joinqu)
			.append(" GROUP BY B.SCHOOL_ID, B.GRADE_ID, A.STU_SEX, GRADE_STR ) BASE_T                   \n");
        String queryStrBmiCount = qrBmiCount.toString();
        
        StringBuffer qrSmoke = new StringBuffer()
	        .append("SELECT COUNT(*) AS cnt  from(                                                     \n")
	        .append("  select A.student_id FROM STUDENT_INFO A,                                                       \n")
	        .append("       BODY_MEASURE_PLAN B,                                                  \n")
	        .append("       SMOKE_INFO C,                                                         \n")
	        .append("       SMOKE_DATA D,                                                         \n")
	        .append("       SCHOOL_INFO G,                                                        \n")
	        .append("       SCHOOL_GRADE H                                                        \n")
	        .append(" WHERE 1 = 1                                                                 \n")
	        .append("   AND A.STUDENT_ID=B.STUDENT_ID                                             \n")
	        .append("   AND B.SMOKE_SEQ=C.SMOKE_SEQ                                               \n")
	        .append("   AND B.SCHOOL_ID=G.SCHOOL_ID                                               \n")
	        .append("   AND B.GRADE_ID=H.GRADE_ID                                                 \n")
	        .append("   AND (C.PPM BETWEEN D.START_INT AND D.END_INT)                             \n")
	        .append("   AND G.SCHOOL_ID NOT IN ( '2', '5', '6')                                     \n")
			.append(joinqu)
        .append("   union                                    \n")
        .append(" select A.student_id  \n")
        .append(" from school_register S \n")
        .append(" join STUDENT_INFO A on S.Student_ID = A.Student_ID \n")
        .append(" left outer join BODY_MEASURE_PLAN B on S.Student_ID = B.Student_ID ")
		.append(joinqu2)
        .append(" join SCHOOL_INFO G on S.school_id=G.school_id \n")
        .append(" join SCHOOL_GRADE H on S.Grade_ID = H.Grade_ID \n")
        .append(" where S.SCHOOL_ID NOT IN ( '2', '5', '6') and (B.MEASURE_DATE is null || B.SMOKE_SEQ is null) \n")
		.append(joinqu1)
        .append("   and S.grade_id not in ('1','2','3','4') \n")
        .append(" ) Z \n");
	    String queryStrSmoke = qrSmoke.toString();
        
        StringBuffer qrSmokeCount = new StringBuffer()
    		.append("SELECT COUNT(*) AS cnt  FROM (                                               \n")
	        .append("SELECT DATE_FORMAT(B.MEASURE_DATE, '%Y년 %m월') AS YEARMONTH,                \n")
	        .append("       B.SCHOOL_ID,                                                          \n")
	        .append("       G.SCHOOL_NAME,                                                        \n")
	        .append("       B.GRADE_ID,                                                           \n")
	        .append("       SUBSTR(H.DISCRIPTION, INSTR(H.DISCRIPTION, ' ')) AS SCHHOL_GRADE_STR, \n")
	        .append("       A.STU_SEX,                                                            \n")
	        .append("       D.START_INT,                                                          \n")
	        .append("       COUNT(*) AS COUNT,                                                    \n")
	        .append("       D.DISCRIPTION  AS GRADE_STR                                           \n")
	        .append("  FROM STUDENT_INFO A,                                                       \n")
	        .append("       BODY_MEASURE_PLAN B,                                                  \n")
	        .append("       SMOKE_INFO C,                                                         \n")
	        .append("       SMOKE_DATA D,                                                         \n")
	        .append("       SCHOOL_INFO G,                                                        \n")
	        .append("       SCHOOL_GRADE H                                                        \n")
	        .append(" WHERE 1 = 1                                                                 \n")
	        .append("   AND A.STUDENT_ID=B.STUDENT_ID                                             \n")
	        .append("   AND B.SMOKE_SEQ=C.SMOKE_SEQ                                               \n")
	        .append("   AND B.SCHOOL_ID=G.SCHOOL_ID                                               \n")
	        .append("   AND B.GRADE_ID=H.GRADE_ID                                                 \n")
	        .append("   AND (C.PPM BETWEEN D.START_INT AND D.END_INT)                             \n")
	        .append("   AND G.SCHOOL_ID NOT IN ( '2', '5', '6')                                     \n")
			.append(joinqu)
			.append(" GROUP BY B.SCHOOL_ID, B.GRADE_ID, A.STU_SEX, GRADE_STR ) BASE_T             \n");
	    String queryStrSmokeCount = qrSmokeCount.toString();
        
	    int totRowCnt = 0;
        if (isExistSchoolId && isExistMD && isExistSection) {
	        try {
	        	
	        	con = pool.getConnection();
	        	

        		if ("LIST".equals(pContentsType)) {

    	        	if (isBMIQuery) {
		            	pstmt = con.prepareStatement(queryStrBmi);
		            	System.out.println("#### queryStrBmi \n" + queryStrBmi);
    	        	} else {
		            	pstmt = con.prepareStatement(queryStrSmoke);
		            	System.out.println("#### queryStrSmoke \n" + queryStrSmoke);
    	        	}
    	        	
    	        	rs = pstmt.executeQuery();
    	        	
    	        	if (rs.next())
    	        	{
    	        		totRowCnt = rs.getInt("cnt");
    	        	}
    	        	
        		} else {

    	        	if (isBMIQuery) {
		            	pstmt = con.prepareStatement(queryStrBmiCount);
		            	System.out.println("#### queryStrBmiCount \n" + queryStrBmiCount);
    	        	} else {
		            	pstmt = con.prepareStatement(queryStrSmokeCount);
		            	System.out.println("#### queryStrSmokeCount \n" + queryStrSmokeCount);
    	        	}
    	        	
    	        	rs = pstmt.executeQuery();
    	        	
    	        	if (rs.next())
    	        	{
    	        		totRowCnt = rs.getInt("cnt");
    	        	}
        		}
        		
	        }catch(SQLException se) {
				System.out.println(se);
			}finally {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con != null) con.close();
			}
	    	
	    }
        return totRowCnt;
	}
	
	
	
	
	
	


	/**
	 * 통계 데이터 추출 : 평균
	 * @param statistics
	 * @param startIndex
	 * @param count
	 * @return
	 * @throws SQLException
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	@SuppressWarnings("unchecked")
	public Collection getStatisticssAVG(Statistics statistics, int startIndex, int count) throws SQLException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String joinquSel = "";
        String joinqu = "";

        boolean isExistSection = true;

        if (statistics.getSection() != null && !statistics.getSection().equals("")) {
        	
        	if (statistics.getSection().equals("ALL_EL")) { // 초 전체
        		
        		joinquSel += "       '-' AS GRADE_ID, \n";
        		joinquSel += "       '-' AS SCHHOL_GRADE_STR, \n";
        		joinquSel += "       '-' AS CLASS, \n";
        		joinqu += "   AND G.SECTION = 'E'  \n";
        		joinqu += " GROUP BY B.SCHOOL_ID, A.STU_SEX  \n";
        		joinqu += " ORDER BY G.SCHOOL_NAME , A.STU_SEX  \n";
        		
        	} else if (statistics.getSection().equals("ALL_MD")) { // 중 전체

        		joinquSel += "       '-' AS GRADE_ID, \n";
        		joinquSel += "       '-' AS SCHHOL_GRADE_STR, \n";
        		joinquSel += "       '-' AS CLASS, \n";
        		joinqu += "   AND G.SECTION = 'M'  \n";
        		joinqu += " GROUP BY B.SCHOOL_ID, A.STU_SEX  \n";
        		joinqu += " ORDER BY G.SCHOOL_NAME , A.STU_SEX  \n";
        		
        	} else if (statistics.getSection().equals("ALL_HI")) { // 초 전체

        		joinquSel += "       '-' AS GRADE_ID, \n";
        		joinquSel += "       '-' AS SCHHOL_GRADE_STR, \n";
        		joinquSel += "       '-' AS CLASS, \n";
        		joinqu += "   AND G.SECTION = 'H'  \n";
        		joinqu += " GROUP BY B.SCHOOL_ID, A.STU_SEX  \n";
        		joinqu += " ORDER BY G.SCHOOL_NAME , A.STU_SEX  \n";
        		
        	} else if (statistics.getSection().equals("SCHOOL")) { // 학교

        		joinquSel += "       B.GRADE_ID AS GRADE_ID, \n";
        		joinquSel += "       SUBSTR(H.DISCRIPTION, INSTR(H.DISCRIPTION, ' ')) AS SCHHOL_GRADE_STR, \n";
        		joinquSel += "       '-' AS CLASS, \n";
        		joinqu += "   AND G.SCHOOL_ID = '" + statistics.getSchool_id() + "'  \n";
        		joinqu += " GROUP BY B.SCHOOL_ID, B.GRADE_ID, A.STU_SEX  \n";
        		joinqu += " ORDER BY G.SCHOOL_NAME , B.GRADE_ID, A.STU_SEX  \n";
        		
        	} else if (statistics.getSection().equals("GRADE")) { // 학년

        		joinquSel += "       B.GRADE_ID AS GRADE_ID, \n";
        		joinquSel += "       SUBSTR(H.DISCRIPTION, INSTR(H.DISCRIPTION, ' ')) AS SCHHOL_GRADE_STR, \n";
        		joinquSel += "       B.CLASS AS CLASS, \n";
        		joinqu += "   AND G.SCHOOL_ID = '" + statistics.getSchool_id() + "'  \n";
        		joinqu += "   AND B.GRADE_ID = '" + statistics.getGrade_id() + "'  \n";
        		joinqu += " GROUP BY B.SCHOOL_ID, B.GRADE_ID, B.CLASS, A.STU_SEX  \n";
        		joinqu += " ORDER BY G.SCHOOL_NAME , B.GRADE_ID, B.CLASS, A.STU_SEX  \n";
        		
        	} else if (statistics.getSection().equals("CLASS")) { // 학년

        		joinquSel += "       B.GRADE_ID AS GRADE_ID, \n";
        		joinquSel += "       SUBSTR(H.DISCRIPTION, INSTR(H.DISCRIPTION, ' ')) AS SCHHOL_GRADE_STR, \n";
        		joinquSel += "       B.CLASS AS CLASS, \n";
        		joinqu += "   AND G.SCHOOL_ID = '" + statistics.getSchool_id() + "'  \n";
        		joinqu += "   AND B.GRADE_ID = '" + statistics.getGrade_id() + "'  \n";
        		joinqu += "   AND B.CLASS = '" + statistics.getStu_class() + "'  \n";
        		joinqu += " GROUP BY B.SCHOOL_ID, B.GRADE_ID, B.CLASS, A.STU_SEX  \n";
        		joinqu += " ORDER BY G.SCHOOL_NAME , B.GRADE_ID, B.CLASS, A.STU_SEX  \n";
        		
        	}
        	
		} else {
			isExistSection = false;
		}
        
        StringBuffer qrBmi = new StringBuffer()
        	.append("SELECT DATE_FORMAT(B.MEASURE_DATE, '%Y년 %m월') AS YEARMONTH,       \n")
    		.append("       B.SCHOOL_ID,                                                 \n")
    		.append("       G.SCHOOL_NAME,                                               \n")
    		.append("       A.STU_SEX,                                                   \n")
			.append(joinquSel)
    		.append("       TRUNCATE(AVG(C.HEIGHT), 1) AS HEIGHT,                        \n")
    		.append("       TRUNCATE(AVG(C.WEIGHT), 1) AS WEIGHT                         \n")
    		.append("  FROM STUDENT_INFO A,                                              \n")
    		.append("       BODY_MEASURE_PLAN B,                                         \n")
    		.append("       INBODY_INFO C,                                               \n")
    		.append("       SCHOOL_INFO G,                                               \n")
    		.append("       SCHOOL_GRADE H                                               \n")
    		.append(" WHERE 1 = 1                                                        \n")
    		.append("   AND A.STUDENT_ID=B.STUDENT_ID                                    \n")
    		.append("   AND B.INBODY_SEQ=C.INBODY_SEQ                                    \n")
    		.append("   AND B.SCHOOL_ID=G.SCHOOL_ID                                      \n")
    		.append("   AND B.GRADE_ID=H.GRADE_ID                                        \n")
    		.append("   AND G.SCHOOL_ID NOT IN ( '2', '5', '6')                      \n")
    		.append("   AND B.MEASURE_DATE='" + statistics.getMeasure_date() + "'        \n")
			.append(joinqu);
        String queryStrBmi = qrBmi.toString();
        

        ArrayList statisticss = new ArrayList();
        ArrayList statisticss_result = new ArrayList();
        
        if (isExistSection) {
	        try {
	        	
	        	con = pool.getConnection();

            	pstmt = con.prepareStatement(queryStrBmi);
            	System.out.println("#### queryStrBmi \n" + queryStrBmi);
	        	
	        	rs = pstmt.executeQuery();
	        	
	    		// startIndex 파라메타 수만큼 Skip한다.
				//while (startIndex-- > 0 && rs.next());
	        	//while (count-- > 0 && rs.next())
		        	while (rs.next())
	        	{
	        		statistics = new Statistics();
	
	        		statistics.setYearMonth       (rs.getString("YEARMONTH"))       ;
	        		statistics.setSchool_id       (rs.getString("SCHOOL_ID"))       ;
	        		statistics.setSchool_name     (aes.decode(rs.getString("SCHOOL_NAME")))     ;
	        		statistics.setGrade_id        (rs.getString("GRADE_ID"))        ;
	        		statistics.setSchool_class    (rs.getString("CLASS"))           ;
	        		statistics.setStu_sex         (rs.getString("STU_SEX"))         ;
	        		statistics.setHeight_str      (rs.getString("HEIGHT"))          ;
	        		statistics.setWeight_str      (rs.getString("WEIGHT"))          ;
	        		statistics.setSchhol_grade_str(rs.getString("SCHHOL_GRADE_STR"));
	        		
					statisticss.add(statistics);
	        	}
	        	final int size = statisticss.size();
    	        
		        Collections.sort(statisticss, new Comparator<Statistics>() {
	
					@Override
					public int compare(Statistics arg0, Statistics arg1) {
						
						int ret = 0;
						
						for(int i=0; i<size; i++) {

							ret = arg0.getSchool_name().compareTo(arg1.getSchool_name());
//							if(arg0.getSchool_name().compareTo(arg1.getSchool_name())==0) {
//								if(arg0.getGrade_id().compareTo(arg1.getGrade_id())==0) {
//									if(arg0.getSchool_class().compareTo(arg1.getSchool_class())==0) {
//										ret = arg0.getStu_sex().compareTo(arg1.getStu_sex());
//									}
//								}
//							}
						}
						
						return ret;
					}
				});
		        
		        int fori = startIndex+count;
		        if(size < startIndex+count ){
		        	fori = size;
		        }
		        for(int i=startIndex; i<fori; i++) {
	        		statisticss_result.add(statisticss.get(i));    
		        }
		        statisticss = statisticss_result;
	        	
	        }catch(SQLException se) {
				System.out.println(se);
			}finally {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con != null) con.close();
			}
	    	
	    }
        return statisticss;
	}
	

	public int getStatisticssAVGTotRowCnt(Statistics statistics) throws SQLException{
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String joinquSel = "";
        String joinqu = "";

        boolean isExistSection = true;

        if (statistics.getSection() != null && !statistics.getSection().equals("")) {
        	
        	if (statistics.getSection().equals("ALL_EL")) { // 초 전체
        		
        		joinquSel += "       '-' AS GRADE_ID, \n";
        		joinquSel += "       '-' AS SCHHOL_GRADE_STR, \n";
        		joinquSel += "       '-' AS CLASS, \n";
        		joinqu += "   AND G.SECTION = 'E'  \n";
        		joinqu += " GROUP BY B.SCHOOL_ID, A.STU_SEX  \n";
        		
        	} else if (statistics.getSection().equals("ALL_MD")) { // 중 전체

        		joinquSel += "       '-' AS GRADE_ID, \n";
        		joinquSel += "       '-' AS SCHHOL_GRADE_STR, \n";
        		joinquSel += "       '-' AS CLASS, \n";
        		joinqu += "   AND G.SECTION = 'M'  \n";
        		joinqu += " GROUP BY B.SCHOOL_ID, A.STU_SEX  \n";
        		
        	} else if (statistics.getSection().equals("ALL_HI")) { // 초 전체

        		joinquSel += "       '-' AS GRADE_ID, \n";
        		joinquSel += "       '-' AS SCHHOL_GRADE_STR, \n";
        		joinquSel += "       '-' AS CLASS, \n";
        		joinqu += "   AND G.SECTION = 'H'  \n";
        		joinqu += " GROUP BY B.SCHOOL_ID, A.STU_SEX  \n";
        		
        	} else if (statistics.getSection().equals("SCHOOL")) { // 학교

        		joinquSel += "       B.GRADE_ID AS GRADE_ID, \n";
        		joinquSel += "       SUBSTR(H.DISCRIPTION, INSTR(H.DISCRIPTION, ' ')) AS SCHHOL_GRADE_STR, \n";
        		joinquSel += "       '-' AS CLASS, \n";
        		joinqu += "   AND G.SCHOOL_ID = '" + statistics.getSchool_id() + "'  \n";
        		joinqu += " GROUP BY B.SCHOOL_ID, B.GRADE_ID, A.STU_SEX  \n";
        		
        	} else if (statistics.getSection().equals("GRADE")) { // 학년

        		joinquSel += "       B.GRADE_ID AS GRADE_ID, \n";
        		joinquSel += "       SUBSTR(H.DISCRIPTION, INSTR(H.DISCRIPTION, ' ')) AS SCHHOL_GRADE_STR, \n";
        		joinquSel += "       B.CLASS AS CLASS, \n";
        		joinqu += "   AND G.SCHOOL_ID = '" + statistics.getSchool_id() + "'  \n";
        		joinqu += "   AND B.GRADE_ID = '" + statistics.getGrade_id() + "'  \n";
        		joinqu += " GROUP BY B.SCHOOL_ID, B.GRADE_ID, B.CLASS, A.STU_SEX  \n";
        		
        	} else if (statistics.getSection().equals("CLASS")) { // 학년

        		joinquSel += "       B.GRADE_ID AS GRADE_ID, \n";
        		joinquSel += "       SUBSTR(H.DISCRIPTION, INSTR(H.DISCRIPTION, ' ')) AS SCHHOL_GRADE_STR, \n";
        		joinquSel += "       B.CLASS AS CLASS, \n";
        		joinqu += "   AND G.SCHOOL_ID = '" + statistics.getSchool_id() + "'  \n";
        		joinqu += "   AND B.GRADE_ID = '" + statistics.getGrade_id() + "'  \n";
        		joinqu += "   AND B.CLASS = '" + statistics.getStu_class() + "'  \n";
        		joinqu += " GROUP BY B.SCHOOL_ID, B.GRADE_ID, B.CLASS, A.STU_SEX  \n";
        		
        	}
        	
		} else {
			isExistSection = false;
		}

        StringBuffer qrBmi = new StringBuffer()
    		.append("SELECT COUNT(*) AS cnt FROM (       \n")
    		.append("SELECT DATE_FORMAT(B.MEASURE_DATE, '%Y년 %m월') AS YEARMONTH,       \n")
    		.append("       B.SCHOOL_ID,                                                 \n")
    		.append("       G.SCHOOL_NAME,                                               \n")
    		.append("       A.STU_SEX,                                                   \n")
			.append(joinquSel)
    		.append("       TRUNCATE(AVG(C.HEIGHT), 1) AS HEIGHT,                        \n")
    		.append("       TRUNCATE(AVG(C.WEIGHT), 1) AS WEIGHT                         \n")
    		.append("  FROM STUDENT_INFO A,                                              \n")
    		.append("       BODY_MEASURE_PLAN B,                                         \n")
    		.append("       INBODY_INFO C,                                               \n")
    		.append("       SCHOOL_INFO G,                                               \n")
    		.append("       SCHOOL_GRADE H                                               \n")
    		.append(" WHERE 1 = 1                                                        \n")
    		.append("   AND A.STUDENT_ID=B.STUDENT_ID                                    \n")
    		.append("   AND B.INBODY_SEQ=C.INBODY_SEQ                                    \n")
    		.append("   AND B.SCHOOL_ID=G.SCHOOL_ID                                      \n")
    		.append("   AND B.GRADE_ID=H.GRADE_ID                                        \n")
    		.append("   AND G.SCHOOL_ID NOT IN ( '2', '5', '6')                      \n")
    		.append("   AND B.MEASURE_DATE='" + statistics.getMeasure_date() + "'        \n")
			.append(joinqu)
    		.append("  )  TT  \n");
        String queryStrBmi = qrBmi.toString();
                
	    int totRowCnt = 0;
        if (isExistSection) {
	        try {
	        	
	        	con = pool.getConnection();
	        	

            	pstmt = con.prepareStatement(queryStrBmi);
            	System.out.println("#### queryStrBmi \n" + queryStrBmi);
	        	
	        	rs = pstmt.executeQuery();
	        	
	        	if (rs.next())
	        	{
	        		totRowCnt = rs.getInt("cnt");
	        	}
	        }catch(SQLException se) {
				System.out.println(se);
			}finally {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con != null) con.close();
			}
	    	
	    }
        return totRowCnt;
	}
	

	/**
	 * 통계 데이터 추출 : 평균
	 * @param statistics
	 * @param startIndex
	 * @param count
	 * @return
	 * @throws SQLException
	 */
	public String[] getAllAVG(Statistics statistics, String stu_sex) throws SQLException{
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String joinqu = "";
        
        String[] retVal= new String[2];
        retVal[0] = "0.0";
        retVal[1] = "0.0";

        boolean isExistSection = true;

        if (statistics.getSection() != null && !statistics.getSection().equals("")) {
        	
        	if (statistics.getSection().equals("ALL_EL")) { // 초 전체
        		
        		joinqu += "   AND G.SECTION = 'E'  \n";
        		joinqu += "   AND A.STU_SEX = '" + stu_sex + "'  \n";
        		
        	} else if (statistics.getSection().equals("ALL_MD")) { // 중 전체

        		joinqu += "   AND G.SECTION = 'M'  \n";
        		joinqu += "   AND A.STU_SEX = '" + stu_sex + "'  \n";
        		
        	} else if (statistics.getSection().equals("ALL_HI")) { // 초 전체

        		joinqu += "   AND G.SECTION = 'H'  \n";
        		joinqu += "   AND A.STU_SEX = '" + stu_sex + "'  \n";
        		
        	} else if (statistics.getSection().equals("SCHOOL")) { // 학교

        		joinqu += "   AND G.SCHOOL_ID = '" + statistics.getSchool_id() + "'  \n";
        		joinqu += "   AND A.STU_SEX = '" + stu_sex + "'  \n";
        		
        	} else if (statistics.getSection().equals("GRADE")) { // 학년

        		joinqu += "   AND G.SCHOOL_ID = '" + statistics.getSchool_id() + "'  \n";
        		joinqu += "   AND B.GRADE_ID = '" + statistics.getGrade_id() + "'  \n";
        		joinqu += "   AND A.STU_SEX = '" + stu_sex + "'  \n";
        		
        	} else if (statistics.getSection().equals("CLASS")) { // 학년

        		joinqu += "   AND G.SCHOOL_ID = '" + statistics.getSchool_id() + "'  \n";
        		joinqu += "   AND B.GRADE_ID = '" + statistics.getGrade_id() + "'  \n";
        		joinqu += "   AND B.CLASS = '" + statistics.getStu_class() + "'  \n";
        		joinqu += "   AND A.STU_SEX = '" + stu_sex + "'  \n";
        		
        	}
        	
		} else {
			isExistSection = false;
		}
        
        StringBuffer qrBmi = new StringBuffer()
        	.append("SELECT DATE_FORMAT(B.MEASURE_DATE, '%Y년 %m월') AS YEARMONTH,       \n")
    		.append("       A.STU_SEX,                                                   \n")
    		.append("       TRUNCATE(AVG(C.HEIGHT), 1) AS HEIGHT,                        \n")
    		.append("       TRUNCATE(AVG(C.WEIGHT), 1) AS WEIGHT                         \n")
    		.append("  FROM STUDENT_INFO A,                                              \n")
    		.append("       BODY_MEASURE_PLAN B,                                         \n")
    		.append("       INBODY_INFO C,                                               \n")
    		.append("       SCHOOL_INFO G,                                               \n")
    		.append("       SCHOOL_GRADE H                                               \n")
    		.append(" WHERE 1 = 1                                                        \n")
    		.append("   AND A.STUDENT_ID=B.STUDENT_ID                                    \n")
    		.append("   AND B.INBODY_SEQ=C.INBODY_SEQ                                    \n")
    		.append("   AND B.SCHOOL_ID=G.SCHOOL_ID                                      \n")
    		.append("   AND B.GRADE_ID=H.GRADE_ID                                        \n")
    		.append("   AND G.SCHOOL_ID NOT IN ( '2', '5', '6')                      \n")
    		.append("   AND B.MEASURE_DATE='" + statistics.getMeasure_date() + "'        \n")
			.append(joinqu);
        String queryStrBmi = qrBmi.toString();
        
                
        ArrayList statisticss = new ArrayList();
        
        if (isExistSection) {
	        try {
	        	
	        	con = pool.getConnection();

            	pstmt = con.prepareStatement(queryStrBmi);
            	System.out.println("#### queryStrBmi \n" + queryStrBmi);
	        	
	        	rs = pstmt.executeQuery();
	        	
	    		// startIndex 파라메타 수만큼 Skip한다.
	        	if (rs.next()) {
	        		retVal[0] = rs.getString("HEIGHT");
	        		retVal[1] = rs.getString("WEIGHT");
	        	}
	        	
	        }catch(SQLException se) {
				System.out.println(se);
			}finally {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con != null) con.close();
			}
	    	
	    }

        return retVal;
	}

	public Collection getStudentCount(Statistics statistics) throws SQLException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String joinqu = "";

        boolean isBMIQuery = false;
        boolean isExistSchoolId = true;
        boolean isExistMD = true;
        boolean isExistSection = true;
        String pContentsType = "LIST";

        // 필수
        if (statistics.getSchool_id() != null && !statistics.getSchool_id().equals("") ) {
			joinqu += " AND B.SCHOOL_ID = '" + statistics.getSchool_id() + "' \n";
		} else {
			isExistSchoolId = false;
		}
        
        if (statistics.getMeasure_date() != null && !statistics.getMeasure_date().equals("")) {
			joinqu += " AND B.MEASURE_DATE='" + statistics.getMeasure_date() + "' \n";
		} else {
			isExistMD = false;
		}
        
        if (statistics.getSection() != null && !statistics.getSection().equals("")) {
        	
        	if (statistics.getSection().equals("BMI")) {
        		isBMIQuery = true;
        	} else if (statistics.getSection().equals("SMOKE")) {
        		isBMIQuery = false;
        	}
        	
		} else {
			isExistSection = false;
		}

        StringBuffer qrBmiCount = new StringBuffer()
        	.append("SELECT DATE_FORMAT(B.MEASURE_DATE, '%Y년 %m월') AS YEARMONTH,                      	\n")
	        .append("       B.SCHOOL_ID,                                                                \n")
	        .append("       G.SCHOOL_NAME,                                                              \n")
	        .append("       B.GRADE_ID,                                                                 \n")
	        .append("       SUBSTR(H.DISCRIPTION, INSTR(H.DISCRIPTION, ' ')) AS SCHHOL_GRADE_STR,       \n")
	        .append("       count(*) as COUNT,              	\n")
	        .append("       sum(case A.STU_SEX when 'M' then 1 else 0 end) AS COUNT_M,              	\n")
	        .append("       sum(case A.STU_SEX when 'F' then 1 else 0 end) AS COUNT_F                   \n")
	        .append("  FROM STUDENT_INFO A,                                                             \n")
	        .append("       BODY_MEASURE_PLAN B,                                                        \n")
	        .append("       INBODY_INFO C,                                                              \n")
	        .append("       SCHOOL_INFO G,                                                              \n")
	        .append("       MS_GRADE_DATA E,                                                            \n")
	        .append("       STANDARD_DATA F,                                                            \n")
	        .append("       SCHOOL_GRADE H                                                              \n")
	        .append(" WHERE 1 = 1                                                                       \n")
	        .append("   AND A.STUDENT_ID=B.STUDENT_ID                                                   \n")
	        .append("   AND B.INBODY_SEQ=C.INBODY_SEQ                                                   \n")
	        .append("   AND B.SCHOOL_ID=G.SCHOOL_ID                                                     \n")
	        .append("   AND B.GRADE_ID = E.GRADE_ID                                                     \n")
	        .append("   AND B.GRADE_ID=H.GRADE_ID                                                       \n")
	        .append("   AND A.STU_SEX=E.SEX                                                             \n")
	        .append("   AND E.MS_GRADE_ID = F.MS_GRADE_ID                                               \n")
	        .append("   AND (C.BMI >= F.START_INT AND C.BMI < F.END_INT)                                   \n")
	        .append("   AND E.SECTION='BMI'                                                             \n")
	        .append("   AND G.SCHOOL_ID NOT IN ( '2', '5', '6')                                     \n")
			.append(joinqu)
			.append(" GROUP BY B.SCHOOL_ID, B.GRADE_ID                            \n")
			.append(" ORDER BY B.GRADE_ID                       \n");
        String queryStrBmiCount = qrBmiCount.toString();
                
        StringBuffer qrSmokeCount = new StringBuffer()
	        .append("SELECT DATE_FORMAT(B.MEASURE_DATE, '%Y년 %m월') AS YEARMONTH,                \n")
	        .append("       B.SCHOOL_ID,                                                          \n")
	        .append("       G.SCHOOL_NAME,                                                        \n")
	        .append("       B.GRADE_ID,                                                           \n")
	        .append("       SUBSTR(H.DISCRIPTION, INSTR(H.DISCRIPTION, ' ')) AS SCHHOL_GRADE_STR, \n")
	        .append("       count(*) as COUNT,              	\n")
	        .append("       sum(case A.STU_SEX when 'M' then 1 else 0 end) AS COUNT_M,              	\n")
	        .append("       sum(case A.STU_SEX when 'F' then 1 else 0 end) AS COUNT_F                   \n")
	        .append("  FROM STUDENT_INFO A,                                                       \n")
	        .append("       BODY_MEASURE_PLAN B,                                                  \n")
	        .append("       SMOKE_INFO C,                                                         \n")
	        .append("       SMOKE_DATA D,                                                         \n")
	        .append("       SCHOOL_INFO G,                                                        \n")
	        .append("       SCHOOL_GRADE H                                                        \n")
	        .append(" WHERE 1 = 1                                                                 \n")
	        .append("   AND A.STUDENT_ID=B.STUDENT_ID                                             \n")
	        .append("   AND B.SMOKE_SEQ=C.SMOKE_SEQ                                               \n")
	        .append("   AND B.SCHOOL_ID=G.SCHOOL_ID                                               \n")
	        .append("   AND B.GRADE_ID=H.GRADE_ID                                                 \n")
	        .append("   AND (C.PPM BETWEEN D.START_INT AND D.END_INT)                             \n")
	        .append("   AND G.SCHOOL_ID NOT IN ( '2', '5', '6')                                     \n")
			.append(joinqu)
			.append(" GROUP BY B.SCHOOL_ID, B.GRADE_ID                      \n")
			.append(" ORDER BY B.GRADE_ID                   \n");
	    String queryStrSmokeCount = qrSmokeCount.toString();
        
        ArrayList statisticss = new ArrayList();
        
        if (isExistSchoolId && isExistMD && isExistSection) {
	        try {
	        	
	        	con = pool.getConnection();
	        	
	        	if (isBMIQuery) {
	            	pstmt = con.prepareStatement(queryStrBmiCount);
	            	System.out.println("#### queryStrBmiCount \n" + queryStrBmiCount);
	        	} else {
	            	pstmt = con.prepareStatement(queryStrSmokeCount);
	            	System.out.println("#### queryStrSmokeCount \n" + queryStrSmokeCount);
	        	}
	        	
	        	rs = pstmt.executeQuery();
	        	
	    		// startIndex 파라메타 수만큼 Skip한다.
	        	while (rs.next())
	        	{
	        		statistics = new Statistics();

	        		statistics.setSchhol_grade_str(rs.getString("SCHHOL_GRADE_STR"));
	        		statistics.setCount_m(rs.getInt("COUNT_M"));
	        		statistics.setCount_f(rs.getInt("COUNT_F"));
	        		statistics.setCount           (rs.getInt   ("COUNT"))           ;
	        		
					statisticss.add(statistics);
	        	}
        		
	        }catch(SQLException se) {
				System.out.println(se);
			}finally {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con != null) con.close();
			}
	    	
	    }
        return statisticss;
	}
	
}
