package com.healthcare.biz.service.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.android.gcm.server.http.Datastore;
import com.google.android.gcm.server.http.GCMHttpSender;
import com.healthcare.bean.GcmRequest;
import com.healthcare.biz.mybatis.domain.MeasureNotice;
import com.healthcare.biz.mybatis.persistence.MeasureNoticeMapper;
import com.healthcare.biz.service.CommService;
import com.healthcare.controller.HomeController;

@Service("commService")
public class CommServiceImpl implements CommService {
	
	@Autowired
	MeasureNoticeMapper measureNoticeMapper;
	
	@Value("${service.push.updateMsg}")
	String servicePushUpdateMsg;
	
	@Value("${service.push.eventMsg}")
	String servicePushEventMsg;
	
	private Sender sender;
	private static final Logger logger = LoggerFactory
			.getLogger(CommServiceImpl.class);

	@Override
	public String SendGcm(String registrationId, String msg) throws IOException {
		
		Datastore.register(registrationId);
		
		Message message = new Message.Builder().addData("msg", msg).build();
		
		GCMHttpSender gcmHttpSender = new GCMHttpSender();
		gcmHttpSender.send(message);
		
		Datastore.unregister(registrationId);
		
		// Datastore.getDevices().clear();
		
		return null;
	}
	
	/**
	 * push 전송을 위한 service
	 * 
	 * [pushType]
	 * 정보 업데이트 = '1'
	 * 이벤트 알림 = '2'
	 * 
	 * @param schoolId
	 * @param pushType
	 * @return Future<String>
	 */
	@Override
	@Async
	public Future<String> SendGcmMeasureNoticePerSchool(GcmRequest gcmRequest)
			throws IOException {
		this.sender = new Sender("AIzaSyB2TTsHaCHrj9kufCgwl675kMrKcuslyrI");
		String result = "";
		String msg = "";
		ArrayList<MeasureNotice> list = gcmRequest.getPushType().equals("1")? measureNoticeMapper.getMeasureNoticeListPerSchool(gcmRequest) : measureNoticeMapper.getMeasureNoticeListPerSchoolNoOverlap(gcmRequest);
		
		for (MeasureNotice item : list) {
			
			msg = gcmRequest.getPushType().equals("1")?servicePushUpdateMsg : servicePushEventMsg;
			Message.Builder builder = new Message.Builder();
			builder.addData("msg", URLEncoder.encode(msg, "UTF-8"));
			Message message = builder.build();
			
		//	Result result1 = sender.send(message, item.getRegistrationId(), 5); //GCM 메시지 보내는부분  2015.03.05
			Result result1 = sender.send(message, item.getRegistrationId(), 0); //GCM 메시지 보내는부분  2015.08.19 다시보내는 횟수 0
			logger.info(" ::: push 전송 데이터 \nMdn::  "+item.getMdn()+"  , RegId::  "+ item.getRegistrationId()+"   \n push 완료");
			
		}
		
		return new AsyncResult<>(result);
	}
		
}
