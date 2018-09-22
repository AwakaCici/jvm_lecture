package com.ssy.jvm.classloader;

public class MyTest18_1 {
    public static void main(String[] args) throws Exception {
        MyFirstClassLoader loader1 = new MyFirstClassLoader("loader1");
        loader1.setPath("/Users/ddcc/Desktop/");

        Class<?> clazz = loader1.loadClass("com.ssy.jvm.classloader.MyTest1");

        System.out.println("class: " + clazz.hashCode());
        System.out.println("class loader: " + clazz.getClassLoader());
    }
}
