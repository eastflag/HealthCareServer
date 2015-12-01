package com.healthcare.admin.helper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.healthcare.admin.adminManage.AdminManage;
import com.healthcare.admin.adminManage.AdminManageMgr;
import com.healthcare.admin.adminManage.AdminManages;
import com.healthcare.common.HttpParameter;

public class AdminManageMgrHelper {

	private AdminManageMgr adminManageMgr = null;
	
	public AdminManageMgrHelper()
	{
		try{
			adminManageMgr = new AdminManageMgr();
		}catch(Exception e){
			System.out.println("AdminManageMgrHelper : cont. error"+e.getMessage());
		}
	}
	
	/**
	 * 계정 목록 조회 <BR>
	 * @param Request
	 * @throws Exception
	 */
	public AdminManages getAdminManages(HttpServletRequest req){
		AdminManages adminManages = null;
		AdminManage adminManage = null;
		try{
			adminManage = new AdminManage();
			adminManages = adminManageMgr.getAdminManages(adminManage);
			
		}catch(Exception e){
			System.out.println("getMeasureManagers error"+e.getMessage());
		}
		return adminManages;
	}
	
	/**
	 * 계정 아이디 중복 체크 <BR>
	 * @param Request
	 * @throws Exception
	 */
	public Map<String, Object> adminIdDuplicationChk(HttpServletRequest req){
		Map<String, Object> rmap = null;
		AdminManage adminManage = null;
		String duplicationChk = "N";
		try{
			rmap = new HashMap<String, Object>();
			
			String admin_id = HttpParameter.getString(req,"admin_id");
			
			
			adminManage = adminManageMgr.getAdminManage(admin_id);
			
			if(null != adminManage.getAdmin_id()){
				if(admin_id.equals(adminManage.getAdmin_id())){
					duplicationChk = "Y";
				}
			}
			
			rmap.put("duplicationChk", duplicationChk);
			
		}catch(Exception e){
			System.out.println("adminIdDuplicationChk error"+e.getMessage());
		}
		return rmap;
	}
	
	/**
	 * 계정 등록 <BR>
	 * @param Request
	 * @throws Exception
	 */
	public void addAdminManage(HttpServletRequest req){
		AdminManage adminManage = null;
		try{
			String admin_id     = HttpParameter.getString(req, "admin_id");
			String school_id    = HttpParameter.getString(req, "school_id");
			String admin_pass   = HttpParameter.getString(req, "admin_pass");
			String admin_info   = HttpParameter.getString(req, "admin_info");
			String use_yn       = HttpParameter.getString(req, "use_yn");
			
			adminManage = new AdminManage();
			
			adminManage.setAdmin_id(admin_id);
			adminManage.setSchool_id(school_id);
			adminManage.setAdmin_pass(admin_pass);
			adminManage.setAdmin_info(admin_info);
			adminManage.setUse_yn(use_yn);
			
			adminManageMgr.addAdminManage(adminManage);
			
		}catch(Exception e){
			System.out.println("addAdminManage error"+e.getMessage());
		}
	}
	
	/**
	 * 계정 수정 <BR>
	 * @param Request
	 * @throws Exception
	 */
	public void setAdminManage(HttpServletRequest req){
		AdminManage adminManage = null;
		try{
			String admin_id     = HttpParameter.getString(req, "admin_id");
			String school_id     = HttpParameter.getString(req, "school_id");
			String admin_pass     = HttpParameter.getString(req, "admin_pass");
			String admin_info     = HttpParameter.getString(req, "admin_info");
			String use_yn       = HttpParameter.getString(req, "use_yn");
			
			adminManage = new AdminManage();
			
			adminManage.setAdmin_id(admin_id);
			adminManage.setSchool_id(school_id);
			adminManage.setAdmin_pass(admin_pass);
			adminManage.setAdmin_info(admin_info);
			adminManage.setUse_yn(use_yn);
			
			adminManageMgr.setAdminManage(adminManage);
			
		}catch(Exception e){
			System.out.println("setAdminManage error"+e.getMessage());
		}
	}
	
	/**
	 * 계정 단일 조회 <BR>
	 * @param Request
	 * @throws Exception
	 */
	public AdminManage getAdminManage(HttpServletRequest req){
		AdminManage adminManage = null;
		try{
			String admin_id = HttpParameter.getString(req,"admin_id");
			
			adminManage = adminManageMgr.getAdminManage(admin_id);
		}catch(Exception e){
			System.out.println("getAdminManage error"+e.getMessage());
		}
		return adminManage;
	}
}

