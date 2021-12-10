package com.vinay.learning.datastore;

import lombok.Data;

@Data
public class DataStoreDetailHolder {

    String callerName;
    String  callerMethodName;
    String callerReturnType;
    Object[] args ;


}
