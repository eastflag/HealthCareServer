package com.healthcare.biz.mybatis.domain;

public class MeasureNotice {

	String userId;
	String name;
	String mdn;
	String registrationId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMdn() {
		return mdn;
	}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	@Override
	public String toString() {
		return "MeasureNotice [userId=" + userId + ", name=" + name + ", mdn="
				+ mdn + ", registrationId=" + registrationId + "]";
	}
	
	
	
}
