package com.ssy.jvm.classloader;

public class MyTest18 {
    public static void main(String[] args) {
        // 启动类加载器加载路径
        System.out.println(System.getProperty("sun.boot.class.path"));
        // 扩展类加载器加载路径。指定修改属性：java -Djava.ext.dirs=./
        System.out.println(System.getProperty("java.ext.dirs"));
        // 应用类加载器加载路径。
        System.out.println(System.getProperty("java.class.path"));
    }
}
