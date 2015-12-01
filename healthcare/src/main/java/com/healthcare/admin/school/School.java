package com.healthcare.admin.school;

import java.io.Serializable;

public class School implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String school_id;
	private String school_name;
	private String section;
	
	public School(){}

	public String getSchool_id() {
		return school_id;
	}

	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}	
}
