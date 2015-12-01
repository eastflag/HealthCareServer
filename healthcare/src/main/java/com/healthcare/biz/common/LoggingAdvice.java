package com.healthcare.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class LoggingAdvice {
	@Pointcut("execution(* com.healthcare.biz.service.impl.Comm*.*(..))")
	//@Pointcut("execution(* com.healthcare.controller.ServiceController.GetRestApiList(..))")
	public void beforePointcut(){}
	
	@Before("beforePointcut()")
	public void beforeLog(JoinPoint jp){
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		if (args.length > 0) {
			System.out.println("[사전 처리] " + method + 
					"() 메소드 ARGS 정보 : " + args[0].toString());
		} else {
			System.out.println("[사전 처리] " + method + "()");
		}		
	}
	
	public void afterLog(JoinPoint jp, Object returnObj){
		String method = jp.getSignature().getName();
		System.out.println("[사후 처리] " + method + 
			"() 메소드 리턴 값 : " + returnObj.toString());
	}
	
	public void exceptionLog(JoinPoint jp, Exception exceptObj){
		String method = jp.getSignature().getName();
		System.out.println("[예외 처리] " + method + 
			"() 메소드에서 발생된 예외 메시지 : " + exceptObj.getMessage());
	}
	
}
