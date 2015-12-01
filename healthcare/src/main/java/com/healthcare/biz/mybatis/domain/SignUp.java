package com.healthcare.biz.mybatis.domain;

import java.io.Serializable;

public class SignUp implements Serializable {
	
	private static final long serialVersionUID = -4761111582877811512L;
	
	String mdn;
	String registrationId;
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
		return "SignUp [mdn=" + mdn + ", registrationId=" + registrationId
				+ "]";
	}
}
