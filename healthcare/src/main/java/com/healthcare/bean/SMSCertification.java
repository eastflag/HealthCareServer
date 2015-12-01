package com.healthcare.bean;

public class SMSCertification {
	
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
		return "SMSCertification [mdn=" + mdn + "]";
	}
}
