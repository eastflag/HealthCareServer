package com.healthcare.admin.menu;

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

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.healthcare.common.AES256Util;
import com.healthcare.common.DBConnectionManager;

public class MenuDAO {
	
	private DBConnectionManager pool = null;
	private AES256Util aes = new AES256Util();
	
	public MenuDAO(){
		try{
			pool = DBConnectionManager.getInstance();
		}catch(Exception e){
			System.out.println("Error : DBConnectionManager !!");
		}
	}
	
	public Collection getMenus(Menu menu, int startIndex, int count)throws SQLException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        System.out.println("====================startIndex:"+startIndex);
        System.out.println("====================count:"+count);
        String joinqu = "WHERE 1=1 AND MENU_INFO.SCHOOL_ID = SCHOOL_INFO.SCHOOL_ID \n";
        
        if (menu.getSchool_id() != null && !menu.getSchool_id().equals("")) {
        	joinqu += "AND MENU_INFO.SCHOOL_ID= '"+menu.getSchool_id()+"' \n";
        }
        
        if (menu.getSchool_year() != null && !menu.getSchool_year().equals("")) {
        	joinqu += "AND MENU_INFO.SCHOOL_YEAR= '"+menu.getSchool_year()+"' \n";
        }
        
        if (menu.getSchool_month() != null && !menu.getSchool_month().equals("")) {
        	joinqu += "AND MENU_INFO.SCHOOL_MONTH= '"+menu.getSchool_month()+"' \n";
        }
        
        StringBuffer qr =new StringBuffer()
			.append(" SELECT ")
			.append(      "MENU_INFO.MENU_ID, \n")
			.append(      "MENU_INFO.SCHOOL_ID, \n")
			.append(      "MENU_INFO.SCHOOL_YEAR, \n")
			.append(      "MENU_INFO.SCHOOL_MONTH, \n")
			.append(      "MENU_INFO.SCHOOL_DATE, \n")
			.append(      "MENU_INFO.SCHOOL_DAY, \n")
			.append(      "MENU_INFO.LUNCH_MAIN, \n")
			.append(      "MENU_INFO.LUNCH_DETL, \n")
			.append(      "MENU_INFO.LUNCH_CALORIE, \n")
			//2014.05.12 석식 메뉴 제거 관련 주석처리
			/*
			.append(      "MENU_INFO.DINNER_MAIN, \n")
			.append(      "MENU_INFO.DINNER_DETL, \n")
			.append(      "MENU_INFO.DINNER_CALORIE, \n")
			*/
			//2014.05.27 영양소 7개 추가.
			.append("MENU_INFO.carbohydrate,\n")
			.append("MENU_INFO.protein,\n")
			.append("MENU_INFO.fat,\n")
			.append("MENU_INFO.calcium,\n")
			.append("MENU_INFO.vitamin_A,\n")
			.append("MENU_INFO.vitamin_C,\n")
			.append("MENU_INFO.nutrient_etc,\n")
			
			.append(      "MENU_INFO.IMG_URL, \n")
			.append(      "MENU_INFO.REG_DATE, \n")
			.append(      "MENU_INFO.REG_ID, \n")
			.append(      "MENU_INFO.UPD_DATE, \n")
			.append(      "MENU_INFO.UPD_ID, \n")
			.append(      "MENU_INFO.USE_YN, \n")
			.append(      "SCHOOL_INFO.SCHOOL_NAME \n")
			.append("   FROM MENU_INFO, SCHOOL_INFO \n")
			.append(joinqu)
			.append(" ORDER BY MENU_INFO.MENU_ID DESC ");
		String queryStr = qr.toString();
		System.out.println(queryStr);
		
	    ArrayList menus = new ArrayList();
	    
	    try {
       		con = pool.getConnection();
        	pstmt = con.prepareStatement(queryStr);

        	rs = pstmt.executeQuery();

        	//while (rs.next())
    		// startIndex 파라메타 수만큼 Skip한다.
			while (startIndex-- > 0 && rs.next());
        	while (count-- > 0 && rs.next())
        	{
        		menu = new Menu();
				
				menu.setMenu_id(rs.getInt("MENU_ID"));
				menu.setSchool_id(rs.getString("SCHOOL_ID"));
				menu.setSchool_year(rs.getString("SCHOOL_YEAR"));
				menu.setSchool_month(rs.getString("SCHOOL_MONTH"));
				menu.setSchool_date(rs.getString("SCHOOL_DATE"));
				menu.setSchool_day(rs.getString("SCHOOL_DAY"));
				menu.setLunch_main(rs.getString("LUNCH_MAIN"));
				menu.setLunch_detl(rs.getString("LUNCH_DETL"));
				menu.setLunch_calorie(rs.getString("LUNCH_CALORIE"));
				//2014.05.12 석식 메뉴 제거 관련 주석처리
				/*
				menu.setDinner_main(rs.getString("DINNER_MAIN"));
				menu.setDinner_detl(rs.getString("DINNER_DETL"));
				menu.setDinner_calorie(rs.getString("DINNER_CALORIE"));
				*/
				//2014.05.27 영양소 7개 추가.
				menu.setCarbohydrate(rs.getString("carbohydrate"));
				menu.setProtein(rs.getString("protein"));
				menu.setFat(rs.getString("fat"));
				menu.setCalcium(rs.getString("calcium"));
				menu.setVitamin_A(rs.getString("vitamin_A"));
				menu.setVitamin_C(rs.getString("vitamin_C"));
				menu.setNutrient_etc(rs.getString("nutrient_etc"));
				
				menu.setImg_url(rs.getString("IMG_URL"));
				menu.setReg_date(rs.getString("REG_DATE"));
				menu.setReg_id(rs.getString("REG_ID"));
				menu.setUpd_date(rs.getString("UPD_DATE"));
				menu.setUpd_id(rs.getString("UPD_ID"));
				menu.setUse_yn(rs.getString("USE_YN"));
				menu.setSchool_name(aes.decode(rs.getString("SCHOOL_NAME")));

				menus.add(menu);
            }
        }catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
        
        return menus;
	}
	
	public int getMenuTotRowCnt(Menu menu) throws SQLException{
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs   = null;
        String joinqu = "WHERE 1=1 \n";
        
        if (menu.getSchool_id() != null && !menu.getSchool_id().equals("")) {
        	joinqu += "AND MENU_INFO.SCHOOL_ID= '"+menu.getSchool_id()+"' \n";
        }
        
        if (menu.getSchool_year() != null && !menu.getSchool_year().equals("")) {
        	joinqu += "AND MENU_INFO.SCHOOL_YEAR= '"+menu.getSchool_year()+"' \n";
        }
        
        if (menu.getSchool_month() != null && !menu.getSchool_month().equals("")) {
        	joinqu += "AND MENU_INFO.SCHOOL_MONTH= '"+menu.getSchool_month()+"' \n";
        }
        
		StringBuffer qr =new StringBuffer()
			.append(" SELECT COUNT(*) AS cnt  ")
			.append("   FROM MENU_INFO ")
			.append(joinqu);
		String queryStr = qr.toString();

		int total_count = 0;
		try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(queryStr);
			rs = pstmt.executeQuery();
			rs.next();
			total_count = rs.getInt("cnt");
		}catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
		return total_count;
    }
	
	public void addMenu(Menu menu) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		StringBuffer qr =new StringBuffer()
		.append(" INSERT INTO MENU_INFO \n")
		.append(" (SCHOOL_ID,                  \n")
		.append(" SCHOOL_YEAR,                  \n")
		.append(" SCHOOL_MONTH,                  \n")
		.append(" SCHOOL_DATE,             \n")
		.append(" SCHOOL_DAY,             \n")
		.append(" LUNCH_MAIN,               \n")
		.append(" LUNCH_DETL,             \n")
		.append(" LUNCH_CALORIE,               \n")
		//2014.05.12 석식 메뉴 제거 관련 주석처리
		/*
		.append(" DINNER_MAIN,               \n")
		.append(" DINNER_DETL,               \n")
		.append(" DINNER_CALORIE,               \n")
		*/
		//2014.05.27 영양소 7개 추가.
		.append("carbohydrate,\n")
		.append("protein,\n")
		.append("fat,\n")
		.append("calcium,\n")
		.append("vitamin_A,\n")
		.append("vitamin_C,\n")
		.append("nutrient_etc,\n")
			
		.append(" IMG_URL,               \n")
		.append(" REG_DATE,               \n")
		.append(" REG_ID,               \n")
		.append(" UPD_DATE,               \n")
		.append(" UPD_ID,                  \n")
		.append(" USE_YN)                  \n")
		//2014.05.12 석식 메뉴 제거 관련 주석처리
		//.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?,NOW(),?,?)");
		.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?,NOW(),?,?)");
		
		String queryStr = qr.toString();
		
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(queryStr);
			
			int j = 0;                                                         
	        
			pstmt.setString(++j, menu.getSchool_id());
			pstmt.setString(++j, menu.getSchool_year());
			pstmt.setString(++j, menu.getSchool_month());
			pstmt.setString(++j, menu.getSchool_date());
			pstmt.setString(++j, menu.getSchool_day());
			pstmt.setString(++j, menu.getLunch_main());
			pstmt.setString(++j, menu.getLunch_detl());
			pstmt.setString(++j, menu.getLunch_calorie());
			//2014.05.12 석식 메뉴 제거 관련 주석처리
			/*
			pstmt.setString(++j, menu.getDinner_main());
			pstmt.setString(++j, menu.getDinner_detl());
			pstmt.setString(++j, menu.getDinner_calorie());
			*/
			//2014.05.27 영양소 7개 추가.
			pstmt.setString(++j, menu.getCarbohydrate());
			pstmt.setString(++j, menu.getProtein());
			pstmt.setString(++j, menu.getFat());
			pstmt.setString(++j, menu.getCalcium());
			pstmt.setString(++j, menu.getVitamin_A());
			pstmt.setString(++j, menu.getVitamin_C());
			pstmt.setString(++j, menu.getNutrient_etc());
			
			pstmt.setString(++j, menu.getImg_url());
			pstmt.setString(++j, menu.getReg_id());
			pstmt.setString(++j, menu.getUpd_id());
			pstmt.setString(++j, menu.getUse_yn());
			                                                                   
			pstmt.executeUpdate(); 
			
		}catch(SQLException se){
			System.out.println(se);
		}finally {
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
	}
	
	public Menu getMenu(String menu_id)throws SQLException{
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Menu menu = null;
        String joinqu = "";
        
        if (menu_id != null && !menu_id.equals("")) {
        	joinqu += "WHERE MENU_ID= '"+menu_id+"'";
        }
        
        StringBuffer qr =new StringBuffer()
			.append(" SELECT ")
			.append(      "MENU_ID, \n")
			.append(      "SCHOOL_ID, \n")
			.append(      "SCHOOL_YEAR, \n")
			.append(      "SCHOOL_MONTH, \n")
			.append(      "SCHOOL_DATE, \n")
			.append(      "SCHOOL_DAY, \n")
			.append(      "LUNCH_MAIN, \n")
			.append(      "LUNCH_DETL, \n")
			.append(      "LUNCH_CALORIE, \n")
			//2014.05.12 석식 메뉴 제거 관련 주석처리
			/*
			.append(      "DINNER_MAIN, \n")
			.append(      "DINNER_DETL, \n")
			.append(      "DINNER_CALORIE, \n")
			*/
			//2014.05.27 영양소 7개 추가.
			.append(      "carbohydrate,\n")
			.append(      "protein,\n")
			.append(      "fat,\n")
			.append(      "calcium,\n")
			.append(      "vitamin_A,\n")
			.append(      "vitamin_C,\n")
			.append(      "nutrient_etc,\n")
			
			.append(      "IMG_URL, \n")
			.append(      "REG_DATE, \n")
			.append(      "REG_ID, \n")
			.append(      "UPD_DATE, \n")
			.append(      "UPD_ID, \n")
			.append(      "USE_YN \n")
			.append("   FROM MENU_INFO \n")
			.append(joinqu);
		String queryStr = qr.toString();
	    
	    try {
       		con = pool.getConnection();
        	pstmt = con.prepareStatement(queryStr);

        	rs = pstmt.executeQuery();

        	while (rs.next())
        	{
        		menu = new Menu();
				menu.setMenu_id(rs.getInt("MENU_ID"));
				menu.setSchool_id(rs.getString("SCHOOL_ID"));
				menu.setSchool_year(rs.getString("SCHOOL_YEAR"));
				menu.setSchool_month(rs.getString("SCHOOL_MONTH"));
				menu.setSchool_date(rs.getString("SCHOOL_DATE"));
				menu.setSchool_day(rs.getString("SCHOOL_DAY"));
				menu.setLunch_main(rs.getString("LUNCH_MAIN"));
				menu.setLunch_detl(rs.getString("LUNCH_DETL"));
				menu.setLunch_calorie(rs.getString("LUNCH_CALORIE"));
				//2014.05.12 석식 메뉴 제거 관련 주석처리
				/*
				menu.setDinner_main(rs.getString("DINNER_MAIN"));
				menu.setDinner_detl(rs.getString("DINNER_DETL"));
				menu.setDinner_calorie(rs.getString("DINNER_CALORIE"));
				*/
				//2014.05.27 영양소 7개 추가.
				menu.setCarbohydrate(rs.getString("carbohydrate"));
				menu.setProtein(rs.getString("protein"));
				menu.setFat(rs.getString("fat"));
				menu.setCalcium(rs.getString("calcium"));
				menu.setVitamin_A(rs.getString("vitamin_A"));
				menu.setVitamin_C(rs.getString("vitamin_C"));
				menu.setNutrient_etc(rs.getString("nutrient_etc"));
				
				menu.setImg_url(rs.getString("IMG_URL"));
				menu.setReg_date(rs.getString("REG_DATE"));
				menu.setReg_id(rs.getString("REG_ID"));
				menu.setUpd_date(rs.getString("UPD_DATE"));
				menu.setUpd_id(rs.getString("UPD_ID"));
				menu.setUse_yn(rs.getString("USE_YN"));
            }
        }catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
        
        return menu;
	}
	
	public void setMenu(Menu menu) throws SQLException{
		Connection con = null;
        PreparedStatement pstmt = null;

		StringBuffer qr0 =new StringBuffer()
			.append(" UPDATE MENU_INFO SET \n")
			.append(   " SCHOOL_ID       = ?,")
			.append(   " SCHOOL_YEAR       = ?,")
			.append(   " SCHOOL_MONTH       = ?,")
			.append(   " SCHOOL_DATE       = ?,")
			.append(   " SCHOOL_DAY       = ?,")
			.append(   " LUNCH_MAIN   = ?,")
			.append(   " LUNCH_DETL   = ?,")
			.append(   " LUNCH_CALORIE   = ?,")
			//2014.05.12 석식 메뉴 제거 관련 주석처리
			/*
			.append(   " DINNER_MAIN      = ?,")
			.append(   " DINNER_DETL  = ?,")
			.append(   " DINNER_CALORIE      = ?,")
			*/
			//2014.05.27 영양소 7개 추가.
			.append(   " carbohydrate = ?,")
			.append(   " protein = ?,")
			.append(   " fat = ?,")
			.append(   " calcium = ?,")
			.append(   " vitamin_A = ?,")
			.append(   " vitamin_C = ?,")
			.append(   " nutrient_etc = ?,")
			
			.append(   " IMG_URL      = ?,")
			.append(   " UPD_DATE      = NOW(),")
			.append(   " UPD_ID        = ?,")
			.append(   " USE_YN        = ?")
			.append(" WHERE MENU_ID = ? ");
		String queryStr = qr0.toString();

        try {
			con = pool.getConnection();
			pstmt = con.prepareStatement(queryStr);

			int i = 0;
			pstmt.setString(++i, menu.getSchool_id        ());
			pstmt.setString(++i, menu.getSchool_year      ());
			pstmt.setString(++i, menu.getSchool_month     ());
			pstmt.setString(++i, menu.getSchool_date      ());
			pstmt.setString(++i, menu.getSchool_day       ());
			pstmt.setString(++i, menu.getLunch_main       ());
			pstmt.setString(++i, menu.getLunch_detl       ());
			pstmt.setString(++i, menu.getLunch_calorie    ());
			//2014.05.12 석식 메뉴 제거 관련 주석처리
			/*
			pstmt.setString(++i, menu.getDinner_main      ());
			pstmt.setString(++i, menu.getDinner_detl      ());
			pstmt.setString(++i, menu.getDinner_calorie   ());
			*/
			//2014.05.27 영양소 7개 추가.
			pstmt.setString(++i, menu.getCarbohydrate     ());
			pstmt.setString(++i, menu.getProtein          ());
			pstmt.setString(++i, menu.getFat              ());
			pstmt.setString(++i, menu.getCalcium          ());
			pstmt.setString(++i, menu.getVitamin_A        ());
			pstmt.setString(++i, menu.getVitamin_C        ());
			pstmt.setString(++i, menu.getNutrient_etc     ());
			
			pstmt.setString(++i, menu.getImg_url          ());
			pstmt.setString(++i, menu.getUpd_id           ());
			pstmt.setString(++i, menu.getUse_yn           ());
			pstmt.setInt   (++i, menu.getMenu_id          ());

			int resultCount = pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
	}
	
	public boolean getSameMenu(String school_id, String school_year, String school_month, String school_date)throws SQLException{
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isExist = false;
        String joinqu = "WHERE school_id= '"+school_id+"' AND school_year= '"+school_year+"' AND school_month= '"+school_month+"' AND school_date= '"+school_date+"'";
        
        
        StringBuffer qr =new StringBuffer()
			.append(" SELECT ")
			.append(      "MENU_ID \n")
			.append("   FROM MENU_INFO \n")
			.append(joinqu);
		String queryStr = qr.toString();
	    
	    try {
       		con = pool.getConnection();
        	pstmt = con.prepareStatement(queryStr);

        	rs = pstmt.executeQuery();
        	while (rs.next())
        	{
        		if(0 != rs.getInt("MENU_ID")){
        			isExist = true;
        		}
            }
        }catch(SQLException se) {
			System.out.println(se);
		}finally {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
		}
	    
        return isExist;
	}
}
