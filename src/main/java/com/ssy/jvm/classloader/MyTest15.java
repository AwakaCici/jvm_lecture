package com.ssy.jvm.classloader;

public class MyTest15 {
    public static void main(String[] args) {
        String[] strings = new String[2];
        System.out.println(strings.getClass().getClassLoader());
    }
}