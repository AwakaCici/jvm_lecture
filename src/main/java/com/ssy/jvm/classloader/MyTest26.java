package com.ssy.jvm.classloader;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 线程上下文类加载器的一般使用模式（获取 -> 使用 -> 还原）
 * ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
 * try {
 * Thread.currentThread().setContextClassLoader(targetConteextClassLoader);
 * myMethod();
 * } finally {
 * Thread.currentThread().setContextClassLoader(classLoader);
 * }
 * myMethod里面则调用了hread.currentThread().getContextClassLoader()方法来获取当前线程的上下文类加载器来做些事情
 * 如果一个类由A加载，那么这个类依赖的类也由相同的类加载器加载的（如果依赖的类没有被加载的话）
 * ContextClassLoader的意义就是破坏双亲委托机制。
 * <p>
 * 当高层提供了统一的接口让底层实现，同时又要在高层加载（或实例化）底层的类时，就必须要使用线程上下文类加载器来帮助高层的ClassLoader找到并加载类
 */
public class MyTest26 {
    public static void main(String[] args) {
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> it = loader.iterator();
        while (it.hasNext()) {
            Driver driver = it.next();
            System.out.println("class: " + driver.getClass() + ", classLoader: " + driver.getClass().getClassLoader());
        }

        System.out.println("线程上下文类加载器：" + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的类加载器：" + ServiceLoader.class.getClassLoader());
    }
}
