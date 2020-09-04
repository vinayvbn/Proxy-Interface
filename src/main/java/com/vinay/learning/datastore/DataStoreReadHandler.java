package com.vinay.learning.datastore;

import org.aspectj.lang.ProceedingJoinPoint;

public interface DataStoreReadHandler {
	
	Object readData(ProceedingJoinPoint joinPoint) throws Throwable;
}
