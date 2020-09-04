package com.vinay.learning.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.vinay.learning.spring.DataStoreClientRegistrar;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(DataStoreClientRegistrar.class)
public @interface EnableDataStoreClients {

	String[] values();

}
