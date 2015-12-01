package com.healthcare.bean;

public class SimliVideoListRequest {

	// 비디오userId
	String userId;
	

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "SimliVideoListRequest [userId=" + userId +"]";
	}

}
