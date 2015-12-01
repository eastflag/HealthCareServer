package com.healthcare.biz.service;

import com.healthcare.biz.mybatis.domain.SMSCert;

public interface SMSCertService {

	public abstract SMSCert getSMSCertKeyInfo (String mdn);
	
	public abstract void insertSMSCertKeyInfo (SMSCert smsCert);
	
	public abstract void deleteSMSCertKeyInfo (String mdn);

}