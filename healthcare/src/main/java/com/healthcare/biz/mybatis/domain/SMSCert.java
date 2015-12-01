package com.healthcare.biz.mybatis.domain;

import java.io.Serializable;

public class SMSCert implements Serializable {
	
	private static final long serialVersionUID = -4761111582877811512L;
	
	String mdn;
	String certificationNumber;
	
	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	public String getCertificationNumber() {
		return certificationNumber;
	}

	public void setCertificationNumber(String certificationNumber) {
		this.certificationNumber = certificationNumber;
	}

	@Override
	public String toString() {
		return "SMSCert [mdn=" + mdn + ", certificationNumber=" + certificationNumber
				+ "]";
	}
}