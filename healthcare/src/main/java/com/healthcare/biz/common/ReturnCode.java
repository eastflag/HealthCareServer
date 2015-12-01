package com.healthcare.biz.common;

public class ReturnCode {
	public final static String succeeded = "0"; 
	public final static String failed = "1";
	public final static String systemError = "2";
	public final static String exception = "3";
	
	// 사용자가 없음.
	public final static String notExistUser = "4";
	
	public final static String exceptSmokeUser = "5";
	
	public final static String notExistMeasureInfo = "6";
	
	

	public final static String strSMSCertFail = "휴대전화번호 인증에 실패하였습니다.";
	public final static String strSMSCertKeyGenFail = "휴대전화번호 인증번호 생성에 실패하였습니다.";
	public final static String strNotExistUser = "등록된 휴대전화번호가 아닙니다.";
}
