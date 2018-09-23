package com.ssy.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 创建了一个自己定义的类加载器，该例子用的是系统默认的AppClassLoader做为其双亲
 * 用自己定义的方法来加载类，生成类的实例
 */
public class MyFirstClassLoader extends ClassLoader {
    private final String extSuffix = ".class";
    private String classloaderName;
    private String path;

    /**
     * ClassLoader的doc文档中说明 Each instance of ClassLoader has an associated parent class loader.
     * 翻译：每个ClassLoader的实例都会有一个与之关联的父类加载器。
     */
    public MyFirstClassLoader(String classloaderName) {
        // super();可加可不加，会自动调用父类不带参数的构造方法
        // 加上是为了提醒自己这边用的是系统类加载器（引用类加载器）作为MyFirstClassLoader的双亲;
        super();
        this.classloaderName = classloaderName;
    }

    public MyFirstClassLoader(ClassLoader classLoader) {
        super(classLoader);
    }

    public MyFirstClassLoader(String classloaderName, ClassLoader classLoader) {
        // 这边是用自己定义的类加载器作为MyFirstClassLoader的双亲
        super(classLoader);
        this.classloaderName = classloaderName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * ClassLoader类的loadClass方法里会调用findClass方法来加载这个类。
     *
     * @param className 类的二进制名字（ClassLoader类的doc中有解释binary name。类似于java.lang.String）
     * @return 该name所对应的类的Class对象
     */
    @Override
    protected Class<?> findClass(String className) {
        System.out.println("findClass invoked: " + className);
        System.out.println("class loader name: " + this.classloaderName);
        byte[] data = loadClassData(className);
        // 调用父类的defineClass方法来返回该类对应的Class对象
        return this.defineClass(className, data, 0, data.length);
    }

    /**
     * 用自己定义的方法来加载类的二进制文件
     *
     * @param className 类的二进制名字（ClassLoader类的doc中有解释binary className。类似于java.lang.String）
     * @return 加载后的二进制数组
     */
    public byte[] loadClassData(String className) {
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        byte[] data = null;
        // mac系统换成 /
        className = className.replace(".", "/");

        try {
            is = new FileInputStream(new File(this.path + className + this.extSuffix));
            baos = new ByteArrayOutputStream();

            int ch;

            while ((ch = is.read()) != -1) {
                baos.write(ch);
            }

            data = baos.toByteArray();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }

    public static void main(String[] args) throws Exception {
        MyFirstClassLoader loader1 = new MyFirstClassLoader("loader1");
//        loader1.setPath("/Users/ddcc/IdeaProjects/jvm_lecture/out/production/classes/");
        loader1.setPath("/Users/ddcc/Desktop/");
        Class<?> clazz = loader1.loadClass("com.ssy.jvm.classloader.MyTest1");
        System.out.println("class: " + clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println();

        loader1 = null;
        clazz = null;
        object = null;

        System.gc();
        Thread.sleep(200000);

        loader1 = new MyFirstClassLoader("loader1");
        loader1.setPath("/Users/ddcc/Desktop/");
        clazz = loader1.loadClass("com.ssy.jvm.classloader.MyTest1");
        System.out.println("class: " + clazz.hashCode());
        System.out.println(clazz.newInstance());

        System.out.println();

//        MyFirstClassLoader loader2 = new MyFirstClassLoader("loader2", loader1);
//        loader2.setPath("/Users/ddcc/Desktop/");
//        Class<?> clazz2 = loader2.loadClass("com.ssy.jvm.classloader.MyTest1");
//        System.out.println("class : " + clazz2.hashCode());
//        System.out.println(clazz2.newInstance());
//
//        System.out.println();
////        MyFirstClassLoader loader3 = new MyFirstClassLoader("loader3", loader2);
//        MyFirstClassLoader loader3 = new MyFirstClassLoader("loader3");
//        loader3.setPath("/Users/ddcc/Desktop/");
//        Class<?> clazz3 = loader3.loadClass("com.ssy.jvm.classloader.MyTest1");
//        System.out.println("class : " + clazz3.hashCode());
//        System.out.println(clazz3.newInstance());
//
//        System.out.println();
    }

    /**
     * 测试
     *
     * @param classLoader 类加载器
     */
    public void test(ClassLoader classLoader) throws Exception {
        Class<?> clazz = classLoader.loadClass("com.ssy.jvm.classloader.MyTest1");
        System.out.println(clazz.newInstance());
        System.out.println(clazz.getClassLoader());
    }
}
