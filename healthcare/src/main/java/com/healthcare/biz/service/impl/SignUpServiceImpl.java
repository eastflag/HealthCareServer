package com.healthcare.biz.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.biz.mybatis.domain.SignUp;
import com.healthcare.biz.mybatis.persistence.SignUpMapper;
import com.healthcare.biz.service.SignUpService;
import com.healthcare.common.AES256Util;

@Service("signUpService")
public class SignUpServiceImpl implements SignUpService {

	@Autowired
	private SignUpMapper signUpMapper;

	@Override
	public SignUp getUser(String mdn) {
		AES256Util aes = new AES256Util();
		SignUp signup = null;
		try {
			mdn = aes.encode(mdn);
			
			signup = signUpMapper.getSignUpByMdn(mdn);
			signup.setMdn(aes.decode(signup.getMdn()));
			
		} catch (InvalidKeyException | UnsupportedEncodingException
				| NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return signup;
	};


	@Override
	@Transactional
	public void updateSignUp(SignUp signUp) {

		AES256Util aes = new AES256Util();
		try {
			signUp.setMdn(aes.encode(signUp.getMdn()));
		} catch (InvalidKeyException | UnsupportedEncodingException
				| NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException
				| IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		signUpMapper.updateSignUp(signUp);
		
	}
}
