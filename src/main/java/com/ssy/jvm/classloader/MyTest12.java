package com.ssy.jvm.classloader;

class CL{
    static {
        System.out.println("Class CL");
    }
}
// 调用ClassLoader类的loadClass方法记载一个类，并不是对类对主动使用，因此不会导致类的初始化
public class MyTest12 {
    public static void main(String[] args) throws Exception {
        ClassLoader loader = ClassLoader.getSystemClassLoader();

        Class<?> clazz = loader.loadClass("com.ssy.jvm.classloader.CL");

        System.out.println(clazz);

        System.out.println("-------------");

        clazz = Class.forName("com.ssy.jvm.classloader.CL");

        System.out.println(clazz);
    }
}
