package com.healthcare.biz.service;

import com.healthcare.biz.mybatis.domain.SignUp;

public interface SignUpService {

	public abstract SignUp getUser(String mdn);
	
	public abstract void updateSignUp(SignUp signUp);

}