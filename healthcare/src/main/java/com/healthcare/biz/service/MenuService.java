package com.healthcare.biz.service;

import com.healthcare.biz.mybatis.domain.MenuData;


public interface MenuService {

	int getMenuData(MenuData menuData);

	void insertMenu(MenuData menuData);
	
}
