package com.vinay.learning.datastore;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinay.learning.aop.DataStoreHandler;

@Component
public class RedisDatastoreProvider extends AbstractDatastoreProvider implements DataStoreProvider {

	RedisTemplate<Object, Object> defaultRedisTemplate;
	RedisTemplate<Object, Object> gzipEnabledRedisTemplate;
	private static final Logger logger = LoggerFactory.getLogger(RedisDatastoreProvider.class);
	private static ObjectMapper mapper;
	static {
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	// private final Pattern pattern =
	// Pattern.compile(DatastoreConstants.REGEX_EXP_TIMESTAMP);

	@Autowired
	public RedisDatastoreProvider(@Qualifier("redisTemplate") RedisTemplate<Object, Object> redisTemplate,
			@Qualifier("gzipRedisTemplate") RedisTemplate<Object, Object> gzipEnabledRedisTemplate) {
		this.defaultRedisTemplate = redisTemplate;
		this.gzipEnabledRedisTemplate = gzipEnabledRedisTemplate;
	}

	@Override
	public Object read(DataStoreReadHandler handler) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	


	
	
}