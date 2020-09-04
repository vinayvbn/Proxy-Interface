package com.vinay.learning.datastore;

public interface DataStoreProvider {

	Object read(DataStoreDetailHolder detailHolder) throws Throwable;
}
