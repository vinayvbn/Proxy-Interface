package com.vinay.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import com.vinay.learning.annotations.EnableDataStoreClients;
import com.vinay.learning.datastore.UserDataStoreClient;

@SpringBootApplication
@EnableDataStoreClients(values = {"com.vinay.learning.datastore"})
public class ProxyApplication implements CommandLineRunner {
	@Autowired
	@Lazy
	private UserDataStoreClient client;
	
	

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(client.readUser("1", "user"));
		
	}

	
}