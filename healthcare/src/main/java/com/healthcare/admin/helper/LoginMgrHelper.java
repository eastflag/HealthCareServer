package com.healthcare.admin.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.healthcare.admin.login.Login;
import com.healthcare.admin.login.LoginMgr;
import com.healthcare.common.HttpParameter;
import com.healthcare.common.WebKeys;


public class LoginMgrHelper {
	
	private LoginMgr loginMgr = null;
	
	public LoginMgrHelper()
    {
	    try{
			loginMgr = new LoginMgr();
		}catch(Exception e){
        	System.out.println("LoginMgrHelper : cont. error"+e.getMessage());
        }
	}
	
	public void isLogin(HttpServletRequest req, HttpServletResponse res)
    {
    	try{
			String isLogin = (String)req.getSession().getAttribute(WebKeys.UserKey);
            if(isLogin == null || (isLogin != null && isLogin.equals(""))){
            	res.sendRedirect("./admin_login.jsp") ;
            }

		}catch(Exception e){
        	System.out.println("isLogin error"+e.getMessage());
        }
    }
	
	public void setLoginSession(HttpServletRequest req, HttpServletResponse res)
    {
    	try{

			String id      = HttpParameter.getString(req,"id","");
		 	String passwd  = HttpParameter.getString(req,"passwd","");
		 	
			Login login = null;
			login = loginMgr.getLogin(id);

			if( login != null && login.getPassword().equals(passwd) ) {
				req.getSession().setAttribute(WebKeys.UserKey,id);
				req.getSession().setAttribute(WebKeys.SchoolKey,login.getSchoolId());

				res.sendRedirect("./statistics.jsp?pageNo=1&INIT") ;

			} else {
				req.getSession().setAttribute(WebKeys.UserKey,"");
				res.sendRedirect("./admin_login.jsp") ;
			}
		 	
		}catch(Exception e){
        	System.out.println("setLogin error"+e.getMessage());
        }
    }
}
