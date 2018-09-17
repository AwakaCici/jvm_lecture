package com.ssy.jvm.classloader;

public class MyTest7 {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("java.lang.String");
        // getClassLoader获取类加载器，如果是启动类加载器实现的则会返回空（因为是C++实现的）。
        System.out.println(clazz.getClassLoader());

        Class<?> clazz2 = Class.forName("com.ssy.jvm.classloader.C");
        System.out.println(clazz2.getClassLoader());
    }
}

class C {

}
