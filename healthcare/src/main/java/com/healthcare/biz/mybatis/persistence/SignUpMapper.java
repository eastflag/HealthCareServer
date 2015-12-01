package com.healthcare.biz.mybatis.persistence;

import com.healthcare.biz.mybatis.domain.SignUp;

public interface SignUpMapper {
	
	SignUp getSignUpByMdn(String mdn);
	
	void updateSignUp(SignUp signUp);

}
