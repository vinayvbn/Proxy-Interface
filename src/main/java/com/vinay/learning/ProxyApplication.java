package com.vinay.learning;

import com.vinay.learning.annotations.EnableDataStoreClients;
import com.vinay.learning.datastore.UserDataStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

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
		System.out.println(client.readUser("user", "1"));
		
	}
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {


		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setUsePool(true);
		connectionFactory.setHostName("localhost");
		connectionFactory.setPort(6379);

		return connectionFactory;
	}
	@Bean
	public RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}


}