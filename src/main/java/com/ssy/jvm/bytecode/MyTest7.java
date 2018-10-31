package com.ssy.jvm.bytecode;

import java.util.Date;

public class MyTest7 {
    public static void main(String[] args) {
        Anaimal anaimal = new Anaimal();
        Anaimal dog = new Dog();

        anaimal.test("hello");//animal str
        dog.test(new Date());//dog date
    }
}

class Anaimal {
    public void test(String string) {
        System.out.println("animal str");
    }

    public void test(Date date) {
        System.out.println("anaimal date");
    }
}

class Dog extends Anaimal {
    @Override
    public void test(Date date) {
        System.out.println("dog date");
    }

    @Override
    public void test(String string) {
        System.out.println("dog str");
    }
}