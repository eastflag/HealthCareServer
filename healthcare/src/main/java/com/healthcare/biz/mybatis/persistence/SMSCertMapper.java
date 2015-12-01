package com.healthcare.biz.mybatis.persistence;

import com.healthcare.biz.mybatis.domain.SMSCert;

public interface SMSCertMapper {
	
	SMSCert getSMSCertKeyInfo (String mdn);
	
	void insertSMSCertKeyInfo (SMSCert smsCert);
	
	void deleteSMSCertKeyInfo (String mdn);
}
