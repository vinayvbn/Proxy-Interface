package com.vinay.learning.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vinay.learning.datastore.DataStoreDetailHolder;
import com.vinay.learning.datastore.DataStoreProvider;
import com.vinay.learning.datastore.DataStoreReadHandler;
import com.vinay.learning.datastore.DatastoreOperation;

@Component
public class DeafultDataStoreHandler implements DataStoreReadHandler {

	@Autowired
	DataStoreProvider provider;

	@Override
	public Object readData(ProceedingJoinPoint joinPoint) throws Throwable {

		DataStoreDetailHolder detailHolder = buildDetailHolder(joinPoint, DatastoreOperation.READ);
		Object response = provider.read(detailHolder);
		return response;
	}

	public DataStoreDetailHolder buildDetailHolder(final ProceedingJoinPoint joinPoint,
			DatastoreOperation datastoreOperation) throws Throwable {
		DataStoreDetailHolder detailHolder = provider.buildDetailHolder(joinPoint, datastoreOperation);
		detailHolder.setCallerName(DatastoreUtil.getCallerName(joinPoint));
		detailHolder.setCallerMethodName(DatastoreUtil.getMethodNameWithoutparams(joinPoint));
		return detailHolder;
	}

}
