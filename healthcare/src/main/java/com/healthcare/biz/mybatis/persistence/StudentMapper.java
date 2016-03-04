package com.healthcare.biz.mybatis.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.healthcare.biz.mybatis.domain.Student;

public interface StudentMapper {
	
	List<Student> getStudentListByMdn(String mdn);
	
	List<Student> getStudentListByYearSchoolGradClass(
			@Param("year") String year, 
			@Param("school_id") String school_id, 
			@Param("grade_id") String grade_id, 
			@Param("class_id") String class_id);
	
	Student getStudentByUserId(String userId);
	
	Student getSchoolInfoByUserId(String userId);

}
