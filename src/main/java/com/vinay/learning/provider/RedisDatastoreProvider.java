package com.vinay.learning.provider;

import com.vinay.learning.datastore.AbstractDatastoreProvider;
import com.vinay.learning.datastore.DataStoreDetailHolder;
import com.vinay.learning.datastore.DataStoreReadHandler;
import com.vinay.learning.datastore.DatastoreOperation;
import com.vinay.learning.util.DatastoreUtil;
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

@Component
public class RedisDatastoreProvider extends AbstractDatastoreProvider implements DataStoreProvider {

    RedisTemplate<Object, Object> defaultRedisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RedisDatastoreProvider.class);
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_EMPTY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    @Autowired
    public RedisDatastoreProvider(@Qualifier("redisTemplate") RedisTemplate<Object, Object> redisTemplate) {
        this.defaultRedisTemplate = redisTemplate;

    }


    @Override
    public Object read(DataStoreDetailHolder detailHolder) throws Throwable {
        defaultRedisTemplate.opsForHash().put("user","1","user1");
        return defaultRedisTemplate.opsForHash().get(detailHolder.getArgs()[0].toString(),detailHolder.getArgs()[1].toString());
    }

    @Override
    public DataStoreDetailHolder buildDetailHolder(ProceedingJoinPoint joinPoint, DatastoreOperation datastoreOperation) {
        DataStoreDetailHolder detailHolder = new DataStoreDetailHolder();
        if (datastoreOperation.equals(DatastoreOperation.READ)) {
            detailHolder.setCallerName(DatastoreUtil.getCallerName(joinPoint));
            detailHolder.setCallerMethodName(DatastoreUtil.getMethodNameWithoutparams(joinPoint));
            detailHolder.setArgs(DatastoreUtil.getMethodArgs(joinPoint));
            detailHolder.setCallerReturnType(DatastoreUtil.getCallerReturnName(joinPoint));
        }

        return detailHolder;
    }
}