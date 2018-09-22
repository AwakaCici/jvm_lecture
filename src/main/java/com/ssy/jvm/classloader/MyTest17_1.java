package com.ssy.jvm.classloader;

public class MyTest17_1 {
    public static void main(String[] args) throws Exception {
        MyFirstClassLoader loader1 = new MyFirstClassLoader("loader1");
        loader1.setPath("/Users/ddcc/Desktop/");

        Class<?> clazz = loader1.loadClass("com.ssy.jvm.classloader.MySample");
        System.out.println("class: " + clazz.hashCode());

        // 如果注释掉该行，那么并不会实例化MySample对象，即MySample构造方法不会被调用
        // 因此不会实例化MyCat对象，即没有对MyCat对象进行主动使用，这里就不会加载MyCat对Class（不一定，通过TraceClassLoading看）
        Object object = clazz.newInstance();


    }
}
