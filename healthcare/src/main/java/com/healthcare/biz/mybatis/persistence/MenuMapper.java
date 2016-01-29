package com.healthcare.biz.mybatis.persistence;

import com.healthcare.biz.mybatis.domain.MenuData;

public interface MenuMapper {

	int getMenuData(MenuData menuData);

	void insertMenu(MenuData menuData);
}
