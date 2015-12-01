package com.aurasystem.healthcare;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.healthcare.biz.service.CommService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CommServiceTest {
	@Autowired
	CommService commService;
	
	
	public void testSendGcm() throws IOException {
		String registrationId = "APA91bHg5yNl2IeWDnf0bW5SC9OKb4SEpq-WHBAUSsdw4FDtuwdMo0PFUK2RPnRVwDGrwxg4_0XxqBnVhHOcTn4FjcC9WhSHksmnCDICMP3Qt_NtQKti3DTmuGzWKYwjLKfZQFEy75whgDa0Yz90X5M-U1BVa9FWFQ";
		String msg = "메시지 발송";
		
		commService.SendGcm(registrationId, msg);
		
		System.out.println("send gcm");
	}
	
	@Test
	public void testSendGcmMeasureNoticePerSchool() throws IOException {
		//commService.SendGcmMeasureNoticePerSchool("4","1");
		
		System.out.println("Call Gcm");
	}

}
