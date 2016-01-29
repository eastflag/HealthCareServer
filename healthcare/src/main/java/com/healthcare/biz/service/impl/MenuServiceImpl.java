package com.healthcare.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.biz.mybatis.domain.MenuData;
import com.healthcare.biz.mybatis.persistence.MenuMapper;
import com.healthcare.biz.service.MenuService;
import com.healthcare.common.AES256Util;


@Service("menu")
public class MenuServiceImpl  implements MenuService{

	@Autowired
	MenuMapper menuMapper;

	@Override
	public int getMenuData(MenuData menuData) {
		return menuMapper.getMenuData(menuData);
	}

	@Override
	public void insertMenu(MenuData menuData) {
		menuMapper.insertMenu(menuData);
	}



}
