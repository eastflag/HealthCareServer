package com.healthcare.biz.mybatis.domain;

import java.util.List;

public class StudentDeviceMapping {

	private List<Device> studentList;
	private List<Device> restMacList;
	
	public List<Device> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Device> studentList) {
		this.studentList = studentList;
	}
	public List<Device> getRestMacList() {
		return restMacList;
	}
	public void setRestMacList(List<Device> restMacList) {
		this.restMacList = restMacList;
	}
	
	
	
}
