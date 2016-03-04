package com.healthcare.biz.service;

import java.util.List;

import com.healthcare.biz.mybatis.domain.Student;

public interface StudentService {
	List<Student> getStudentListByMdn(String mdn);

	Student getStudentByUserId(String userId);
	
	List<Student> getStudentList(
			String year, 
			String school_id, 
			String grade_id, 
			String class_id);
}
