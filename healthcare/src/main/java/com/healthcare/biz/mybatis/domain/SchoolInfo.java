package com.healthcare.biz.mybatis.domain;


public class SchoolInfo {

	private String schoolId;
	private String name;
	private String section;
	private String sido;
	private String code;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	
	@Override
	public String toString() {
		return "SchoolInfo [schoolId=" + schoolId + ", name=" + name + ", section=" + section + ", sido=" + sido
				+ ", code=" + code + "]";
	}
	
}
