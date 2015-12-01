package com.healthcare.biz.service;

import java.io.IOException;
import java.util.concurrent.Future;

import com.healthcare.bean.GcmRequest;

public interface CommService {
	public abstract String SendGcm(String registrationId, String msg) throws IOException;
	
	public abstract Future<String> SendGcmMeasureNoticePerSchool(GcmRequest gcmRequest) throws IOException;
}
