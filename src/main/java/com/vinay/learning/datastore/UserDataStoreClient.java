package com.vinay.learning.datastore;

import com.vinay.learning.spring.DataStoreClient;

public interface UserDataStoreClient extends DataStoreClient {
  public String readUser(String id,String key);
}
