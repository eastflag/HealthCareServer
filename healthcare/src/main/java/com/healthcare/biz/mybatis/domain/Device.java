package com.healthcare.biz.mybatis.domain;

public class Device {

	private String 	curPageNum 			= "";
	private	String	listScale			= "";
	private	String	limitStart			= ""; 
	
	private String studentId;
	private String studentName;
	private String deviceName;
	private String mac;
	
	public String getStudentId() {
		return studentId;
	}
	public String getCurPageNum() {
		return curPageNum;
	}
	public void setCurPageNum(String curPageNum) {
		this.curPageNum = curPageNum;
	}
	public String getListScale() {
		return listScale;
	}
	public void setListScale(String listScale) {
		this.listScale = listScale;
	}
	public String getLimitStart() {
		return limitStart;
	}
	public void setLimitStart(String limitStart) {
		this.limitStart = limitStart;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}

	
	
	
}
