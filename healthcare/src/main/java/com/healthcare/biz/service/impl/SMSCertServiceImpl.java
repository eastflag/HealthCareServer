package com.healthcare.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.biz.mybatis.domain.SMSCert;
import com.healthcare.biz.mybatis.persistence.SMSCertMapper;
import com.healthcare.biz.service.SMSCertService;

@Service("smsCertService")
public class SMSCertServiceImpl implements SMSCertService {

	@Autowired
	private SMSCertMapper smsCertMapper;

	@Override
	public SMSCert getSMSCertKeyInfo (String mdn) {

		return smsCertMapper.getSMSCertKeyInfo(mdn);
	};

	@Override
	@Transactional
	public void insertSMSCertKeyInfo (SMSCert smsCert) {

		smsCertMapper.insertSMSCertKeyInfo (smsCert);
	}
	
	@Override
	@Transactional
	public void deleteSMSCertKeyInfo (String mdn) {

		smsCertMapper.deleteSMSCertKeyInfo (mdn);
	}
}
