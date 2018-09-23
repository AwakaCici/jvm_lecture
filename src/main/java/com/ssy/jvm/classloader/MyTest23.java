package com.ssy.jvm.classloader;

/**
 * 在运行期，一个Java类是由该类的完全限定名（binary name，二进制名）
 * 和用于加载该类的定义类加载器（defining loader）所共同决定的。
 * 如果同样名字（即相同的完全限定名）的类是由两个不同的加载器所加载，那么这些类就是不同的。
 * 即便.class文件的字节码完全一样，并且从相同的位置加载亦是如此。
 */

import sun.misc.Launcher;

/**
 * 在Oracle的Hotspot实现中，系统属性sun.boot.class.path如果修改错了，则会运行出错，提示：
 * Error occurred durring initialization of VM
 * java/lang/NoClassDefFoundError: java/lang/Object
 */
public class MyTest23 {
    public static void main(String[] args) {
        // 启动类加载器加载路径
        System.out.println(System.getProperty("sun.boot.class.path"));
        // 扩展类加载器加载路径。
        System.out.println(System.getProperty("java.ext.dirs"));
        // 应用类加载器加载路径。
        System.out.println(System.getProperty("java.class.path"));

        /*
            内建于JVM中的启动类加载器会加载java.lang.ClassLoader以及其他的Java平台类
            当JVM启动时，一块特殊的机器码会运行，它会加载扩展类加载器与系统类加载器，
            这块特殊的机器码叫做启动类加载器（Bootstrap）

            启动类加载器并不是java类，而其他的加载器则都是java类
            启动类加载器是特定于平台的机器指令，它负责开启整个加载过程。

            所有类加载器（除了启动类加载器）都被实现为Java类，不过，总归要有一个组件来加载第一个Java类加载器
            从而让整个加载过程能够顺利进行下去，加载第一个纯Java类加载器就是启动类加载器的职责

            启动类加载器还会负责加载供JRE正常运行所需要的基本组件，这包括java.util与java.lang包中的类等等。
         */
        System.out.println(ClassLoader.class.getClassLoader());
        // 扩展类加载器与系统类加载器也是由启动类加载器所加载的。
        // 这边打出的Launcher类的类加载器可以说明系统类加载器和启动类加载器的类加载器，
        // 因为扩展类加载器和应用类加载器都是Launcher类内部的静态类，MySample例子可以知道，其内部类也是由加载Launcher的加载器进行加载的
        System.out.println(Launcher.class.getClassLoader());

        System.out.println("-------------------------------");
        // 看一下getSystemClassLoader方法的DOC，
        // 其中说明如果指定java.system.class.loader，那么会让系统类加载器指向我们自己定义的加载器
        ClassLoader.getSystemClassLoader();
        // 在默认情况下，这个是没有被定义的。该属性是没有被定义的，系统类加载器默认会指向AppClassLoader
        System.out.println(System.getProperty("java.system.class.loader"));
        System.out.println(MyTest23.class.getClassLoader());
        // 成为了系统类加载器的自定义加载器内部其实还是系统类加载器进行加载的。文档里也有说。
        System.out.println(MyFirstClassLoader.class.getClassLoader());
        // 现在让我们自己定义的加载器成为系统类加载器，
        // 我们自己定义的类加载器必须定义一个public的一个ClassLoader参数的构造方法（Doc中说的）给系统调用的
        // MyFirstClassLoader中加一个构造方法 super(classLoader)
        // java -Djava.system.class.loader=com.ssy.jvm.classloader.MyFirstClassLoader com.ssy.jvm.classloader.MyTest23
        System.out.println(ClassLoader.getSystemClassLoader());
        // 上面那一行，在控制台上执行就会变成我们自己定义的类加载器了
    }
}
