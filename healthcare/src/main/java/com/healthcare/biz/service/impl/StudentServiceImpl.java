package com.healthcare.biz.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.biz.mybatis.domain.Student;
import com.healthcare.biz.mybatis.persistence.StudentMapper;
import com.healthcare.biz.service.StudentService;
import com.healthcare.common.AES256Util;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public List<Student> getStudentListByMdn(String mdn) {
		AES256Util aes = new AES256Util();
		List<Student> list = null;
		try {
			mdn = aes.encode(mdn);
			list = studentMapper.getStudentListByMdn(mdn);
			Student student_new = null;
			
			for(Student item : list) {
				
				student_new = new Student();
				student_new = studentMapper.getSchoolInfoByUserId(item.getUserId());
				
				if (student_new != null) {
					item.setClassNumber(student_new.getClassNumber());
					item.setSchoolGradeId(student_new.getSchoolGradeId());
					item.setSchoolId(student_new.getSchoolId());
				}

				item.setName(aes.decode(item.getName()));
			}
		} catch (InvalidKeyException | UnsupportedEncodingException
				| NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException
				| IllegalBlockSizeException | BadPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return list;
	}
	
	@Override
	public Student getStudentByUserId (String userId) {
		
		AES256Util aes = new AES256Util();

		Student student_org = studentMapper.getStudentByUserId(userId);
		Student student_new = studentMapper.getSchoolInfoByUserId(userId);

		student_org.setClassNumber(student_new.getClassNumber());
		student_org.setSchoolGradeId(student_new.getSchoolGradeId());
		student_org.setSchoolId(student_new.getSchoolId());
		try {
			student_org.setName(aes.decode(student_org.getName()));
		} catch (InvalidKeyException | UnsupportedEncodingException
				| NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return student_org;
	}

}
