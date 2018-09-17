package com.ssy.jvm.classloader;

class Parent{
    static int a = 3;
    static {
        System.out.println("parent static blocd");
    }
}

class Child extends Parent {
    static int b = 4;
    static {
        System.out.println("child static block");
    }
}
public class MyTest9 {
    static {
        System.out.println("MyTest9 static block");
    }

    public static void main(String[] args) {
        System.out.println(Child.b);
    }
}
