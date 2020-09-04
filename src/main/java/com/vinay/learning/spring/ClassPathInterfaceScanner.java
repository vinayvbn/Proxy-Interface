package com.vinay.learning.spring;

import java.io.IOException;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.ClassUtils;

public class ClassPathInterfaceScanner extends ClassPathScanningCandidateComponentProvider {
	ClassPathInterfaceScanner() {

		super(false);
	}
	@Override
	protected String resolveBasePackage(String basePackage) {
		// TODO Auto-generated method stub
		return ClassUtils.convertClassNameToResourcePath(this.getEnvironment().resolveRequiredPlaceholders(basePackage));
	}
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return beanDefinition.getMetadata().isInterface();
		
	}
	@Override
	protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
		return metadataReader.getClassMetadata().isInterface();
	}
}