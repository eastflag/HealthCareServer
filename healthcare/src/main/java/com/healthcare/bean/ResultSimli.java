package com.healthcare.bean;

public class ResultSimli <E> {
	
	String result;
	String errMsg = "";
	E value = null;
	String userId = "";
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	@Override
	public String toString() {
		return "Result [result=" + result + ", errMsg=" + errMsg + ", userId" + userId + ", value="
				+ value + "]";
	}
	
}
