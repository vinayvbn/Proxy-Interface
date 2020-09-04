package com.vinay.learning.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataStoreAspect {
	
	
	
	@Around("DataStorePointCuts.readPoint()")
	public Object readData(final ProceedingJoinPoint pjp) throws Throwable {
		Object data = new String(pjp.getSignature().getName());
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		ms.getMethod().getAnnotations();
		pjp.getArgs();
		return data + (String) pjp.getArgs()[0];

	}

}
