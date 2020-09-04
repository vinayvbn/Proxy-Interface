package com.vinay.learning.aop;

import java.lang.annotation.Annotation;

public interface AnnotatedHandler extends DataStoreHandler {
	
	Class<? extends Annotation> supports();

}
