package com.vinay.learning.aop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataStoreHandlerFactory {
	
	@Autowired
	private DeafultDataStoreHandler handler;
	
	@Autowired(required = false)
	private List<AnnotatedHandler> annotatedHandler;




}
