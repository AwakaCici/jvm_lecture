package com.ssy.jvm.classloader;

import java.lang.reflect.Constructor;

public class MyTest17 {
    public static void main(String[] args) throws Exception {
        MyFirstClassLoader loader1 = new MyFirstClassLoader("loader1");

        Class<?> clazz = loader1.loadClass("com.ssy.jvm.classloader.MySample");
        System.out.println("class: " + clazz.hashCode());

        // 这种方法只能调用不带参数的构造方法，若需要调用带参数的构造方法(例如一个参数是String,int)如下：
//        Constructor constructor = clazz.getConstructor(String.class, int.class);
//        Object instance = constructor.newInstance("abc", 1);

        // 如果注释掉该行，那么并不会实例化MySample对象，即MySample构造方法不会被调用
        // 因此不会实例化MyCat对象，即没有对MyCat对象进行主动使用，这里就不会加载MyCat对Class（不一定，通过TraceClassLoading看）
         Object object = clazz.newInstance();


    }
}
