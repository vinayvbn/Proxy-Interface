package com.vinay.learning.aop;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class DataStorePointCuts {
	
	@Pointcut(value ="execution(public * com.vinay.learning.spring.DataStoreClient+.read*(..))")
	public void readPoint() {
		
	}

}
