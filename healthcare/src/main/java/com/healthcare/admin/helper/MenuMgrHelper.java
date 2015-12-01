package com.healthcare.admin.helper;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.healthcare.admin.menu.Menu;
import com.healthcare.admin.menu.MenuMgr;
import com.healthcare.admin.menu.Menus;
import com.healthcare.common.Config;
import com.healthcare.common.HttpParameter;
import com.healthcare.common.SessionManager;
import com.healthcare.common.Util;
import com.healthcare.common.WebKeys;
import com.healthcare.common.upload.http.Uploader;

public class MenuMgrHelper {

	private MenuMgr menuMgr = null;
	
	public MenuMgrHelper(){
		try{
			menuMgr = new MenuMgr();
		}catch(Exception e){
        	System.out.println("setContent error"+e.getMessage());
        }
	}
	
	public Menus getMenus(HttpServletRequest req){
		
		String[] pSearchKeys = new String[] {"school_id", "school_year", "school_month"};
		
		SessionManager.setSearchKeys(req, pSearchKeys);
		
		HashMap searchKeys = SessionManager.getSearchKeys(req);
		
		String school_id  = ((String)searchKeys.get("school_id")).trim();
		String school_year  = ((String)searchKeys.get("school_year")).trim();
		String school_month  = ((String)searchKeys.get("school_month")).trim();
		
		Menus menus = null;
		Menu menu = null;
		
		try{
			menu = new Menu();
			
			menu.setSchool_id(school_id);
			menu.setSchool_year(school_year);
			menu.setSchool_month(Util.fulfillSize(school_month, 2));
			
			//menus = menuMgr.getCouponsM(startIndex, count);
			menus = menuMgr.getMenus(menu);
		}catch(Exception e){
        	System.out.println("getMenus error"+e.getMessage());
        }
		
		//SessionManager.initSubOnePageNo(req);
		return menus;
	}
	
	public boolean addMenu(HttpServletRequest req){
		
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		boolean isSuccess = false;
		boolean isExist = false;
		
		Menu menu = null;
		
		try{

			String upUrl = Config.SCHOOL_FOOD_IMG_UPLOAD_DIR;
			String upAliasUrl = Config.SCHOOL_FOOD_IMG_ALIAS_DIR;
			
			Uploader up = new Uploader(); //Upload라이브러리.
			
			up.setUploadDir(upUrl, 2);
			isSuccess = up.doUpload(req, dateFormat.format(new java.util.Date()));
			
			//String orgFileName   = up.getOriginalFName();
	    	String aliasFileName = up.getRenames();
			
			
			String school_id =      up.getParameter("school_id");
			String school_year =    up.getParameter("school_year");
			String school_month =   up.getParameter("school_month");
			String school_date =    up.getParameter("school_date");
			String school_day =     up.getParameter("school_day");
			String lunch_main =     up.getParameter("lunch_main");
			String lunch_detl =     up.getParameter("lunch_detl");
			String lunch_calorie =  up.getParameter("lunch_calorie");
			//2014.05.12 석식 메뉴 제거 관련 주석처리
			/*
			String dinner_main =    up.getParameter("dinner_main");
			String dinner_detl =    up.getParameter("dinner_detl");
			String dinner_calorie = up.getParameter("dinner_calorie");
			*/
			//2014.05.27 영양소 7개 추가.
			String carbohydrate = up.getParameter("carbohydrate");
			String protein = up.getParameter("protein");     
			String fat = up.getParameter("fat");         
			String calcium = up.getParameter("calcium");     
			String vitamin_A = up.getParameter("vitamin_A");   
			String vitamin_C = up.getParameter("vitamin_C");   
			String nutrient_etc = up.getParameter("nutrient_etc");
			
			String use_yn = 		up.getParameter("use_yn");
			String img_url = upAliasUrl + "noimage.gif";
			
			if(isSuccess){
				img_url = upAliasUrl+aliasFileName;
			}
			
			menu = new Menu();
			
			menu.setSchool_id(school_id);
			menu.setSchool_year(school_year);
			menu.setSchool_month(school_month);
			menu.setSchool_date(school_date);
			menu.setSchool_day(school_day);
			menu.setLunch_main(lunch_main);
			menu.setLunch_detl(lunch_detl);
			//menu.setLunch_detl(lunch_detl.replaceAll("(?i)\r\n", "<BR>"));
			menu.setLunch_calorie(lunch_calorie);
			//2014.05.12 석식 메뉴 제거 관련 주석처리
			//menu.setDinner_main(dinner_main);
			//menu.setDinner_detl(dinner_detl);
			//menu.setDinner_detl(dinner_detl.replaceAll("(?i)\r\n", "<BR>"));
			//menu.setDinner_calorie(dinner_calorie);
			
			//2014.05.27 영양소 7개 추가.
			menu.setCarbohydrate(carbohydrate);
			menu.setProtein(protein);
			menu.setFat(fat);
			menu.setCalcium(calcium);
			menu.setVitamin_A(vitamin_A);
			menu.setVitamin_C(vitamin_C);
			menu.setNutrient_etc(nutrient_etc);
			
			menu.setUse_yn(use_yn);
			menu.setImg_url(img_url);
			
			String menuLoginName = (String)req.getSession().getAttribute(WebKeys.UserKey);
			
			menu.setReg_id(menuLoginName);
			menu.setUpd_id(menuLoginName);
			
			isExist = menuMgr.addMenu(menu);
		}catch(Exception e){
        	System.out.println("addMenu error"+e.getMessage());
        }
		
		return isExist;
	}
	
	public Menu getMenu(HttpServletRequest req){
		
		String menu_id  = HttpParameter.getString(req, "MENU_ID");
		
		Menu menu = new Menu();
		
		try{
			menu = menuMgr.getMenu(menu_id);
			//menu.setLunch_detl(menu.getLunch_detl().replaceAll("(?i)<BR>", "\r\n"));
			//menu.setDinner_detl(menu.getDinner_detl().replaceAll("(?i)<BR>", "\r\n"));
		}catch(Exception e){
        	System.out.println("getMenus error"+e.getMessage());
        }
		
		return menu;
	}
	
	public void setMenu(HttpServletRequest req){
		
		Menu menu = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		boolean isSuccess = false;
		
		try{
			String upUrl = Config.SCHOOL_FOOD_IMG_UPLOAD_DIR;
			String upAliasUrl = Config.SCHOOL_FOOD_IMG_ALIAS_DIR;
			
			Uploader up = new Uploader(); //Upload라이브러리.
			
			up.setUploadDir(upUrl, 2);
			isSuccess = up.doUpload(req, dateFormat.format(new java.util.Date()));
			
			String aliasFileName = up.getRenames();
			
			int menu_id = Integer.parseInt(up.getParameter("menu_id"));
			String school_id =      up.getParameter("school_id");
			String school_year =    up.getParameter("school_year");
			String school_month =   up.getParameter("school_month");
			String school_date =    up.getParameter("school_date");
			String school_day =     up.getParameter("school_day");
			String lunch_main =     up.getParameter("lunch_main");
			String lunch_detl =     up.getParameter("lunch_detl");
			String lunch_calorie =  up.getParameter("lunch_calorie");
			//2014.05.12 석식 메뉴 제거 관련 주석처리
			/*
			String dinner_main =    up.getParameter("dinner_main");
			String dinner_detl =    up.getParameter("dinner_detl");
			String dinner_calorie = up.getParameter("dinner_calorie");
			*/
			
			//2014.05.27 영양소 7개 추가.
			String carbohydrate = up.getParameter("carbohydrate");
			String protein = up.getParameter("protein");     
			String fat = up.getParameter("fat");         
			String calcium = up.getParameter("calcium");     
			String vitamin_A = up.getParameter("vitamin_A");   
			String vitamin_C = up.getParameter("vitamin_C");   
			String nutrient_etc = up.getParameter("nutrient_etc");
			
			String use_yn = 		up.getParameter("use_yn");
			String is_file = up.getParameter("is_file");
			String img_url = upAliasUrl + "noimage.gif";
			
			if("Y".equals(is_file)){
				if(isSuccess){
					img_url = upAliasUrl+aliasFileName;
				}
			}else{
				img_url = up.getParameter("img_url_org");
			}
			
			menu = new Menu();
			
			menu.setMenu_id(menu_id);
			menu.setSchool_id(school_id);
			menu.setSchool_year(school_year);
			menu.setSchool_month(school_month);
			menu.setSchool_date(school_date);
			menu.setSchool_day(school_day);
			menu.setLunch_main(lunch_main);
			menu.setLunch_detl(lunch_detl);
			//menu.setLunch_detl(lunch_detl.replaceAll("(?i)\r\n", "<BR>"));
			menu.setLunch_calorie(lunch_calorie);
			//2014.05.12 석식 메뉴 제거 관련 주석처리
			//menu.setDinner_main(dinner_main);
			//menu.setDinner_detl(dinner_detl);
			//menu.setDinner_detl(dinner_detl.replaceAll("(?i)\r\n", "<BR>"));
			//menu.setDinner_calorie(dinner_calorie);
			
			//2014.05.27 영양소 7개 추가.
			menu.setCarbohydrate(carbohydrate);
			menu.setProtein(protein);
			menu.setFat(fat);
			menu.setCalcium(calcium);
			menu.setVitamin_A(vitamin_A);
			menu.setVitamin_C(vitamin_C);
			menu.setNutrient_etc(nutrient_etc);
			
			menu.setImg_url(img_url);
			menu.setUse_yn(use_yn);
			
			String menuLoginName = (String)req.getSession().getAttribute(WebKeys.UserKey);
			menu.setUpd_id(menuLoginName);
			
			menuMgr.setMenu(menu);
			
		}catch(Exception e){
        	System.out.println("setMenu error"+e.getMessage());
        }
	}
}
