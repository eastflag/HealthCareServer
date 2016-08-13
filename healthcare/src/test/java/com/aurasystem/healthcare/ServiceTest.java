package com.aurasystem.healthcare;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.healthcare.bean.Growth;
import com.healthcare.bean.MeasureHistory;
import com.healthcare.biz.mybatis.domain.AverageItem;
import com.healthcare.biz.mybatis.domain.BodyMeasureGrade;
import com.healthcare.biz.mybatis.domain.BodyMeasureSummary;
import com.healthcare.biz.mybatis.domain.SignUp;
import com.healthcare.biz.mybatis.domain.Student;
import com.healthcare.biz.mybatis.persistence.StatisticsMapper2;
import com.healthcare.biz.service.BodyMeasureService;
import com.healthcare.biz.service.SignUpService;
import com.healthcare.biz.service.StudentService;
import com.healthcare.common.AES256Util;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ServiceTest {
	@Autowired
	SignUpService signUpService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	BodyMeasureService bodyMeasureService;
	
	@Autowired
	StatisticsMapper2 stat2;
	

	@Ignore
	@Test
	public void testSignUp() throws IOException {
		
		SignUp vo = signUpService.getUser("01040239227");
		
		if (vo == null) {
			System.out.println("사용자가 존재하지 않는다.");
		} else {
			vo.setRegistrationId("123456");
			signUpService.updateSignUp(vo);
		}
		
		System.out.println("testSignUp");
	}
	
	@Ignore
	@Test
	public void testStudent() throws IOException {
		
		
		List<Student> list = studentService.getStudentListByMdn("01037788036");
		
		System.out.println(list);
		System.out.println("testStudent");
	}

	@Ignore
	@Test
	public void testgetSummary() throws IOException {
		
		BodyMeasureSummary vo =  bodyMeasureService.getSummary("589");
		
		System.out.println(vo);
		System.out.println("testStudent");
	}
	
	
	public void getTimeStamp()  {
		
		long startTime = System.currentTimeMillis();
		
		System.out.println( new Timestamp(startTime));
	}

	@Ignore
	@Test
	public void testgetMeasureGrade() throws IOException {
		
		BodyMeasureGrade height =  bodyMeasureService.getHeightMeasureGrade("589");
		
		System.out.println("height : " + height);
		
		BodyMeasureGrade weight =  bodyMeasureService.getWeightMeasureGrade("589");
		
		System.out.println("weight : " + weight);
	}
	
	@Ignore
	@Test
	public void testgetHeight() throws IOException {
		
		List<AverageItem> list = bodyMeasureService.getHeightAveragePerClass("589");
		
		System.out.println("MeasureHistory : " + list);
		
		
	}
	
	@Ignore
	@Test
	public void testgetMeasureHistory() throws IOException {
		
		MeasureHistory vo = bodyMeasureService.getHeightMeasureHistory("589", "4");
		
		System.out.println("MeasureHistory : " + vo);
		
		
	}
	
	@Ignore
	@Test
	public void testGetScore() {
		
		Growth vo = bodyMeasureService.getGrowth("394");
		
		System.out.println("Growth : " + vo);
		
	}

	@Ignore
	@Test
	public void testGetRankingOfHeightPerClass() {
		List<AverageItem> list = bodyMeasureService.getHeightAveragePerClass("1111");
		System.out.println("list : " + list);
	}
	
	@Test
	public void getMdnList() {
		List<SignUp> mdnList = stat2.getMdnList();
		
		try {
			File f = new File("c:\\temp\\out.txt");
			BufferedWriter out = new BufferedWriter(new FileWriter(f));

			AES256Util aes = new AES256Util();
			for(SignUp signup: mdnList) {
				String mdn = aes.decode(signup.getMdn());
				out.write(mdn + "\r\n");
				//System.out.println(mdn);
			}
			
			out.close();
		} catch(Exception e) {
			
		}
	}
}
