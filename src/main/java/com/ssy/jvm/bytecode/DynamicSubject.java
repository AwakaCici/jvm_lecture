package com.ssy.jvm.bytecode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicSubject implements InvocationHandler {
    Object realsub;

    public DynamicSubject(Object object) {
        this.realsub = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke method : " + method);
        method.invoke(realsub, args);
        System.out.println("after invoke method : " + method);
        return null;
    }
}
