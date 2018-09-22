package com.ssy.jvm.classloader;

import java.lang.reflect.Method;

public class MyTest20 {
    public static void main(String[] args) throws Exception {
        MyFirstClassLoader loader1 = new MyFirstClassLoader("loader1");
        MyFirstClassLoader loader2 = new MyFirstClassLoader("loader2");

        Class<?> clazz1 = loader1.loadClass("com.ssy.jvm.classloader.MyPerson");
        Class<?> clazz2 = loader2.loadClass("com.ssy.jvm.classloader.MyPerson");

        System.out.println(clazz1 == clazz2);

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        method.invoke(object1, object2);
    }
}
