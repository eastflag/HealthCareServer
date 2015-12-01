package com.healthcare.admin.login;

import java.io.Serializable;

public class Login implements Serializable {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String loginId;
    private String password;
    private String schoolId;
    private String adminInfo;

    /**
	* Constructor
	*/
    public Login(  	String loginId,
					String password,
					String schoolId,
					String adminInfo)
    {
		this.loginId = loginId;
		this.password = password;
		this.schoolId = schoolId;
		this.adminInfo = adminInfo;
    }
    public Login() {
    }

    public String getLoginId() {	return 	this.loginId; }
    public void   setLoginId(String loginId) { this.loginId = loginId; }

    public String getPassword() {	return 	this.password; }
    public void   setPassword(String password) { this.password = password; }

    public String getSchoolId() {	return 	this.schoolId; }
    public void   setRoleId(String schoolId) { this.schoolId = schoolId; }
    
	public String getAdminInfo() {  return adminInfo; }
	public void setAdminInfo(String adminInfo) { this.adminInfo = adminInfo; }

}