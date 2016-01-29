package com.healthcare.biz.mybatis.persistence;

import java.util.ArrayList;

import com.healthcare.biz.mybatis.domain.SchoolInfo;

public interface SchoolMapper {

	ArrayList<SchoolInfo> getSchoolList();
}
