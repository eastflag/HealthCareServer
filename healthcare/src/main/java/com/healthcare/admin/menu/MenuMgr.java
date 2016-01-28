package com.healthcare.admin.menu;

public class MenuMgr {

	public Menus getMenus(Menu menu, int startIndex, int count){
		Menus menus = null;
		
		try{
			MenuDAO menuDAO = new MenuDAO();

			menus = new Menus(menuDAO.getMenus(menu, startIndex, count)
					, menuDAO.getMenuTotRowCnt(menu));
			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		
		return menus;
	}
	
	public boolean addMenu(Menu menu){
		boolean isExist = true;
		
		try{
			MenuDAO menuDAO = new MenuDAO();
			
			if(menu.getImg_url().equals("") || menu.getImg_url() == null){
				//TODO default image url
				//menu.setImg_url("");
			}
			
			if(!menuDAO.getSameMenu(menu.getSchool_id(), menu.getSchool_year(), menu.getSchool_month(), menu.getSchool_date())){
				menuDAO.addMenu(menu);
				isExist = false;
			}
			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		return isExist;
	}
	
	public Menu getMenu(String menu_id){
		Menu menu = new Menu();
		try{
			MenuDAO menuDAO = new MenuDAO();
			menu = menuDAO.getMenu(menu_id);
			
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		return menu;
	}
	
	public void setMenu(Menu menu){
		try {
			MenuDAO menuDAO = new MenuDAO();
			menuDAO.setMenu(menu);
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
	}
}
