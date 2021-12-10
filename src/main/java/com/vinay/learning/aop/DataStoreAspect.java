package com.vinay.learning.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataStoreAspect {

	@Autowired
	private DeafultDataStoreHandler handler;
	
	@Around("DataStorePointCuts.readPoint()")
	public Object readData(final ProceedingJoinPoint pjp) throws Throwable {
		Object data = new String(pjp.getSignature().getName());
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		ms.getMethod().getAnnotations();
		pjp.getArgs();
		return handler.readData(pjp);

	}

}
