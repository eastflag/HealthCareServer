package com.healthcare.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;

public class TimeCheckAdvice {
	
	public Object timeCheck(ProceedingJoinPoint pjp) throws Throwable{
		Object obj = null;		
		long start = System.currentTimeMillis();		
		obj = pjp.proceed();		
		long end = System.currentTimeMillis();
		
		String method = pjp.getSignature().getName();
		System.out.println(method + "() 메소드 수행에 걸린 시간 : " + 
				(end - start) + "(ms)초");
		
		return obj;
	}
}
