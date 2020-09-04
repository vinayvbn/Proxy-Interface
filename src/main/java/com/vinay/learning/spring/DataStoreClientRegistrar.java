package com.vinay.learning.spring;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.type.AnnotationMetadata;

import com.vinay.learning.annotations.EnableDataStoreClients;

public class DataStoreClientRegistrar  extends ProxyBeanDefinitonRegistrar{
	
	 @Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		
		registerDataStoreClients(importingClassMetadata,registry );
	}

	private void registerDataStoreClients(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
		Map<String, Object> values = metadata.getAnnotationAttributes(EnableDataStoreClients.class.getName(),true);
		setProxyPerInterface(true);
		String[] annotatedpkgs= (String[]) values.get("values");
		Collection<String> basepackages = new HashSet<>(Arrays.asList(annotatedpkgs));
		parseAndRegister(registry, Collections.EMPTY_LIST, Collections.EMPTY_LIST, basepackages, "abstractDataStoreTarget", null);
		
		
	}

}
