package com.healthcare.biz.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.biz.mybatis.domain.SchoolInfo;
import com.healthcare.biz.mybatis.persistence.SchoolMapper;
import com.healthcare.biz.service.SchoolService;
import com.healthcare.common.AES256Util;


@Service("school")
public class SchoolServiceImpl  implements SchoolService{

	private AES256Util aes = new AES256Util();
	
	@Autowired
	SchoolMapper schoolMapper;

	@Override
	public ArrayList<SchoolInfo> getSchoolList() {
		ArrayList<SchoolInfo> list = schoolMapper.getSchoolList();
		
		if(list!=null && list.size()>0) {
			for(int i=0; i<list.size(); i++) {
				try {
					list.get(i).setName(aes.decode(list.get(i).getName()));
		        }catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
			
		return list;
	}


}
