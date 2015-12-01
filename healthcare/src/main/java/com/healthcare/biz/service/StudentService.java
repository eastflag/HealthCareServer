package com.healthcare.biz.service;

import java.util.List;

import com.healthcare.biz.mybatis.domain.Student;

public interface StudentService {
	List<Student> getStudentListByMdn(String mdn);

	Student getStudentByUserId(String userId);
}
