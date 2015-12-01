package com.healthcare.admin.school;

import java.sql.SQLException;


public class SchoolMgr {

	/**
	 * Select-Option 용
	 * @return
	 */
	public Schools getListSchool(){
		Schools schools = null;
		
		try{
			SchoolDAO schoolDAO = new SchoolDAO();
			
			schools = new Schools(schoolDAO.getListSchool());			
			schools.setTotRowCnt(schools.getSchools().size());
			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		
		return schools;
	}
	
	/**
	 * 학교 정보 추가
	 * @param school
	 */
	public void addSchool(School school){
		try{
			SchoolDAO schoolDAO = new SchoolDAO();
			schoolDAO.addSchool(school);
			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
	}
	
	/**
	 * 학교 목록 조회
	 * @param school_name
	 * @param section
	 * @param startIndex
	 * @param count
	 * @return
	 */
	public Schools getSchools(String school_name, String section, int startIndex, int count){
		Schools schools = null;
		
		try{
			SchoolDAO schoolDAO = new SchoolDAO();
			
			schools = new Schools(schoolDAO.getSchools(school_name, section, startIndex, count));
			
			schools.setTotRowCnt(schoolDAO.getSchoolsTotRowCnt (school_name, section));
			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		return schools;
	}
	
	/**
	 * 학교 정보 조회
	 * @param school_id
	 * @return
	 */
	public School getSchool(String school_id){
		School school = new School();
		
		try{
			SchoolDAO schoolDAO = new SchoolDAO();
			school = schoolDAO.getSchool(school_id);
		}catch(Exception se){
			System.out.println(se.getMessage());
		}
		return school;
	}
	
	/**
	 * 학교 정보 수정
	 * @param school
	 */
	public void setSchool(School school){
		try {
			SchoolDAO schoolDAO = new SchoolDAO();
			schoolDAO.setSchool(school);
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
	}
	
	/**
	 * 관리자 계정이 없는 학교 목록 
	 * @return schools
	 */
	public Schools getNotExistAdminSchoolList(){
		Schools schools = null;
		
		try{
			SchoolDAO schoolDAO = new SchoolDAO();
			
			schools = new Schools(schoolDAO.getNotExistAdminSchoolList());			
			schools.setTotRowCnt(schools.getSchools().size());
			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		
		return schools;
	}
}
