package com.healthcare.admin.helper;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.healthcare.admin.school.School;
import com.healthcare.admin.school.SchoolMgr;
import com.healthcare.admin.school.Schools;
import com.healthcare.common.Config;
import com.healthcare.common.HttpParameter;
import com.healthcare.common.SessionManager;

public class SchoolMgrHelper {

	private SchoolMgr schoolMgr = null;
	
	public SchoolMgrHelper(){
		try{
			schoolMgr = new SchoolMgr();
		}catch(Exception e){
        	System.out.println("setContent error"+e.getMessage());
        }
	}
	
	// Select-Option용
	public Schools getListSchool(HttpServletRequest req){
		Schools schools = null;
		
		try{
			schools = schoolMgr.getListSchool();
		}catch(Exception e){
        	System.out.println("getEmNotices error"+e.getMessage());
        }
		
		return schools;
	}
	

	/**
	 * 학교 정보 추가
	 * @param req
	 */
	public void addSchool(HttpServletRequest req){
		School school  = null;
		
		try{
			
			//String school_id       = HttpParameter.getString(req, "school_id");
			String school_name     = HttpParameter.getString(req, "school_name");
			String section         = HttpParameter.getString(req, "section");
			String address         = HttpParameter.getString(req, "address");
			
			school = new School();
			//school.setSchool_id       (school_id      );
			school.setSchool_name     (school_name    );
			school.setSection         (section        );
			school.setAddress		  (address);
			
			schoolMgr.addSchool(school);
		}catch(Exception e){
        	System.out.println("addSchool error"+e.getMessage());
        }
	}
	
	public Schools getSchools(HttpServletRequest req){
		
		Schools schools = null;
		
		String[] pSearchKeys = new String[] {"school_name", "section"};
		
		SessionManager.setSearchKeys(req, pSearchKeys);
		
		HashMap searchKeys = SessionManager.getSearchKeys(req);

		String school_name  = ((String)searchKeys.get("school_name")).trim();
		String section  = ((String)searchKeys.get("section")).trim();
		
		int startIndex = (SessionManager.getPageNo(req)-1)*Config.NUM_OF_LINE10;
		
		int count = Config.NUM_OF_LINE10;
		
		try{			
			schools = schoolMgr.getSchools(school_name, section, startIndex, count);
			
		}catch(Exception e){
			System.out.println("getSchools error"+e.getMessage());
		}
		return schools;
	}
	
	/**
	 * 학교 정보 조회
	 * @param req
	 * @return
	 */
	public School getSchool(HttpServletRequest req){
		
		String school_id  = HttpParameter.getString(req, "school_id");
		
		School school = new School();
		
		try{
			school = schoolMgr.getSchool(school_id);
			
		}catch(Exception e){
        	System.out.println("getSchool error"+e.getMessage());
        }
		
		return school;
	}
	
	/**
	 * 학교 정보 수정
	 * @param req
	 */
	public void setSchool(HttpServletRequest req){
		
		School school = null;
		
		try{
			String school_id       = HttpParameter.getString(req, "school_id");
			String school_name     = HttpParameter.getString(req, "school_name");
			String section         = HttpParameter.getString(req, "section");
			String address         = HttpParameter.getString(req, "address");
			
			school = new School();
			school.setSchool_id       (school_id      );
			school.setSchool_name     (school_name    );
			school.setSection         (section        );
			school.setAddress		  (address		  );
			
			schoolMgr.setSchool(school);
			
		}catch(Exception e){
        	System.out.println("setSchool error"+e.getMessage());
        }
	}
	
	/**
	 * 관리자 계정이 없는 학교 목록 
	 * @param req
	 */
	public Schools getNotExistAdminSchoolList(HttpServletRequest req){
		
		Schools schools = null;
		
		try{			
			schools = schoolMgr.getNotExistAdminSchoolList();
		}catch(Exception e){
			System.out.println("getSchools error"+e.getMessage());
		}
		return schools;
	}
}
