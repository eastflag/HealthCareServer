package com.healthcare.admin.adminManage;

public class AdminManage {

	private static final long serialVersionUID = 1L;
	
	private String admin_id;
	private String school_id;
	private String admin_pass;
	private String admin_info;
	private String school_name;
	private String use_yn;
	private int admin_seq;
	
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getAdmin_pass() {
		return admin_pass;
	}
	public void setAdmin_pass(String admin_pass) {
		this.admin_pass = admin_pass;
	}
	public String getAdmin_info() {
		return admin_info;
	}
	public void setAdmin_info(String admin_info) {
		this.admin_info = admin_info;
	}
	public int getAdmin_seq() {
		return admin_seq;
	}
	public void setAdmin_seq(int admin_seq) {
		this.admin_seq = admin_seq;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	
}
