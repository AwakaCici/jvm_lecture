package com.ssy.jvm.classloader;

public class MyTest4 {
    public static void main(String[] args) {
        MyParent4[] myParent4 = new MyParent4[1];
        System.out.println(myParent4.getClass());
        MyParent4[][] myParent4s = new MyParent4[1][1];
        System.out.println(myParent4s.getClass());
        System.out.println("=======");

        int[] ints = new int[1];
        System.out.println(ints.getClass());
        System.out.println(ints.getClass().getSuperclass());
    }
}

class MyParent4 {
    static {
        System.out.println("MyParent4 static block");
    }
}
