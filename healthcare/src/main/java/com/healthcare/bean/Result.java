package com.healthcare.bean;

public class Result <E>{
	
	String result;
	String errMsg = "";
	E value = null;
	String schoolGradeId = "";
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public E getValue() {
		return value;
	}
	public void setValue(E value) {
		this.value = value;
	}
	public String getSchoolGradeId() {
		return schoolGradeId;
	}
	public void setSchoolGradeId(String schoolGradeId) {
		this.schoolGradeId = schoolGradeId;
	}
	
	@Override
	public String toString() {
		return "Result [result=" + result + ", errMsg=" + errMsg + ", schoolGradeId=" + schoolGradeId + ", value="
				+ value + "]";
	}
	

	
	
	
}
