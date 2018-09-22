package com.ssy.jvm.classloader;

import com.sun.crypto.provider.AESKeyGenerator;

public class MyTest19 {
    /**
     * 扩展类加载器演示，一般扩展类加载器是用来加密的
     * 我们可以用 java -Djava.ext.dirs=./ xxx.xxx.MyTest19 来指定系统属性目录
     * @param args
     */
    public static void main(String[] args) {
        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();

        System.out.println(aesKeyGenerator.getClass().getClassLoader());
        System.out.println(MyTest19.class.getClassLoader());
    }
}
