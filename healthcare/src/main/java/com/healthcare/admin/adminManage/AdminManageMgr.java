package com.healthcare.admin.adminManage;


public class AdminManageMgr {
	
	private AdminManageDAO adminManageDAO = null;
	
	/*
	 * 계정 사용여부관련 구분자
	 * 사용할경우 = use_yn_word를 삭제
	 * 사용안할경우 = use_yn_word를 DB입력
	 * 조회시 use_yn_word를 삭제
	 * */
	private String use_yn_word= "h0j9k8";
	//구분자의 문자열 길이
	private int use_yn_len = 6;
	
	public AdminManageMgr(){
		try{
			adminManageDAO = new AdminManageDAO();
		}catch(Exception e){
			System.out.println("AdminManageMgr : cont. error"+e.getMessage());
		}
	}

	/**
	 * 계정 목록 조회 <BR>
	 * @param adminManage
	 * @throws Exception
	 */
	public AdminManages getAdminManages(AdminManage adminManage){
		AdminManages adminManages = null;
		
		try{
			
			adminManages = new AdminManages(adminManageDAO.getAdminManages(adminManage), adminManageDAO.getAdminManageTotRowCnt(adminManage));
			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		
		return adminManages;
	}
	
	/**
	 * 계정 단일 조회 <BR>
	 * @param admin_id
	 * @throws Exception
	 */
	public AdminManage getAdminManage(String admin_id){
		AdminManage adminManage = null;
		
		try{
			adminManage =  adminManageDAO.getAdminManage(admin_id);
			if(searchWord(adminManage.getAdmin_pass(), use_yn_word, use_yn_len)){
				adminManage.setAdmin_pass(removeWord(adminManage.getAdmin_pass(), use_yn_word, use_yn_len));
				adminManage.setUse_yn("n");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return adminManage;
	}
	
	/**
	 * 계정 등록 <BR>
	 * @param adminManage
	 * @throws Exception
	 */
	public void addAdminManage(AdminManage adminManage){
		try{
			if("n".equals(adminManage.getUse_yn())){
				if(!searchWord(adminManage.getAdmin_pass(), use_yn_word, use_yn_len)){
					adminManage.setAdmin_pass(attachWord(adminManage.getAdmin_pass(), use_yn_word));
				}
			}else{
				if(searchWord(adminManage.getAdmin_pass(), use_yn_word, use_yn_len)){
					adminManage.setAdmin_pass(removeWord(adminManage.getAdmin_pass(), use_yn_word, use_yn_len));
				}
			}
			adminManageDAO.addAdminManage(adminManage);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 계정 정보 수정 <BR>
	 * 아이디가 같을경우의 쿼리와 구분
	 * @param adminManage
	 */
	public void setAdminManage(AdminManage adminManage){
		try{
			//String admin_id = adminManage.getAdmin_id();
			
			if("n".equals(adminManage.getUse_yn())){
				if(!searchWord(adminManage.getAdmin_pass(), use_yn_word, use_yn_len)){
					adminManage.setAdmin_pass(attachWord(adminManage.getAdmin_pass(), use_yn_word));
				}
			}else{
				if(searchWord(adminManage.getAdmin_pass(), use_yn_word, use_yn_len)){
					adminManage.setAdmin_pass(removeWord(adminManage.getAdmin_pass(), use_yn_word, use_yn_len));
				}
			}
			
//			AdminManage checkAdminManage = new AdminManage();
//			checkAdminManage = adminManageDAO.getAdminManage(admin_id);
//			
//			if(null != checkAdminManage.getAdmin_id()){
//				adminManageDAO.setAdminManageSameId(adminManage);
//			}else{
//				adminManageDAO.setAdminManage(adminManage);
//			}
			adminManageDAO.setAdminManage(adminManage);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 단어붙이기 <BR>
	 *
	 * @param orginalStr
	 * @param addWord
	 */
	public String attachWord(String orginalStr, String addWord){
		return (new StringBuffer(orginalStr).append(addWord)).toString();
	}
	
	/**
	 * 단어삭제 <BR>
	 *
	 * @param orginalStr
	 * @param searchStr
	 * @param searchStrLen
	 */
	public String removeWord(String orginalStr, String searchStr, int searchStrLen){
		StringBuffer sb = new StringBuffer(orginalStr);
		int orgStrlen = sb.length();
		
		return (sb.substring(0, orgStrlen-searchStrLen)).toString();
	}
	
	/**
	 * 단어검색 <BR>
	 *
	 * @param orginalStr
	 * @param searchStr
	 * @param searchStrLen
	 */
	public Boolean searchWord(String orginalStr, String searchStr, int searchStrLen){
		StringBuffer sb = new StringBuffer(orginalStr);
		int orgStrlen = sb.length();
		String transStr = "";
		
		if(searchStrLen>=orgStrlen) return false;
		
		transStr = orginalStr.substring(orgStrlen-searchStrLen, orgStrlen);
		
		return transStr.equals(searchStr);
	}
}
