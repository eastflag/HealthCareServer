package com.healthcare.admin.login;



public class LoginMgr {

	public Login getLogin(String loginId)
    {
		Login login = null;
		try {
			LoginDAO logindao = new LoginDAO();
			login=logindao.getLogin(loginId);
		}catch(Exception se) {
			System.out.println(se.getMessage());
		}
		return login;
   }
}
