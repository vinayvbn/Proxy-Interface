package com.vinay.learning.provider;

import com.vinay.learning.datastore.DataStoreDetailHolder;
import com.vinay.learning.datastore.DatastoreOperation;
import org.aspectj.lang.ProceedingJoinPoint;

public interface DataStoreProvider {

	Object read(DataStoreDetailHolder detailHolder) throws Throwable;

    DataStoreDetailHolder buildDetailHolder(ProceedingJoinPoint joinPoint, DatastoreOperation datastoreOperation);
}
