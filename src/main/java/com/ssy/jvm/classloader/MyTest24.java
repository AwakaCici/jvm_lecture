package com.ssy.jvm.classloader;

/**
 * 当前类加载器 Current ClassLoader
 * 每个类都会尝试用它自己的加载器（即加载自身的加载器）来尝试加载这个类依赖的其他类
 * 例如ClassX引用了ClassY那么加载X的类加载器就会去加载Y（前提是Y没有被加载）
 *
 * 线程上下文类加载器Context ClassLoader
 *  线程上下文类加载器是从JDK1.2开始引入的。类Thread中getContextClassLoader()与setContextClassLoader(ClassLoader cl)
 *  分别用来设置和获取上下文类加载器
 *  如果没有通过setContextClassLoader(ClassLoader cl)进行设值的话，线程将继承其父线程的类加载器
 *  Java运行时的初始线程的上下文加载器是系统类加载器，在线程中运行的代码可以通过该类加载器来加载类与资源。
 *
 *  SPI（Service Provider Interface）
 *
 *  线程上下文类加载器的重要性：
 *  父ClassLoader可以使用当前线程的Thread.currentThread().getContextClassLoader()所指定的classloader加载的类
 *  这就改变类父ClassLoader或是其他没有父子关系的ClassLoader加载类的情况，即改变了双亲委托模型。
 *
 *  线程上下文类加载器就是Current Context ClassLoader
 *
 *  在双亲委托模型下，类加载器是由下至上的，但是对于SPI来说，有些接口是由Java核心库提供的，而Java核心库是由启动类加载器加载的，而这些
 *  接口的实现却来自于不同的jar包（厂商），Java的启动类加载器是不会加载其他来源的jar包的，这样传统的双亲委托机制就不满足需求
 *  而通过当前线程设制相应的上下文加载器，就可以由设置的上下文类加载器来实现对接口实现类的加载
 */
public class MyTest24 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Thread.class.getClassLoader());
        ClassLoader.getSystemClassLoader();
    }
}
