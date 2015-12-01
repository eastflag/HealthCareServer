package com.healthcare.biz.mybatis.persistence;

import java.util.List;

import com.healthcare.biz.mybatis.domain.Student;

public interface StudentMapper {
	
	List<Student> getStudentListByMdn(String mdn);
	
	Student getStudentByUserId(String userId);
	
	Student getSchoolInfoByUserId(String userId);

}
