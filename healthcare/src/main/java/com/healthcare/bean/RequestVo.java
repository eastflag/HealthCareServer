package com.healthcare.bean;

public class RequestVo {
	
	String userId;
	
	String selYear; // 연간 Data 조회를 위해 선택된 연도
	
	// 향후 보안을 위해 사용을 한다.
	String token;



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSelYear() {
		return selYear;
	}

	public void setSelYear(String selYear) {
		this.selYear = selYear;
	}

	@Override
	public String toString() {
		return "RequestVo [userId=" + userId + ", token=" + token + ", selYear=" + selYear + "]";
	}


	
}
