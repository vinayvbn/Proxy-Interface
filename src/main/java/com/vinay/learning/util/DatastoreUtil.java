package com.vinay.learning.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class DatastoreUtil {


    public static String getCallerName(ProceedingJoinPoint joinPoint) {
        
        return joinPoint.getSignature().getDeclaringTypeName();
                
    }

    public static String getMethodNameWithoutparams(ProceedingJoinPoint joinPoint) {

        return joinPoint.getSignature().getName();
    }

    public static Object[] getMethodArgs(ProceedingJoinPoint joinPoint) {

        return joinPoint.getArgs();
    }

    public static String getCallerReturnName(ProceedingJoinPoint joinPoint) {
        return   ((MethodSignature)joinPoint.getSignature()).getReturnType().getName();
    }
}
