package com.ssy.jvm.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class JarHellProblem {
    /**
     * 用这部分代码可以很好的解决jar冲突的问题。
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String resourceName = "java/lang/String.class";

        Enumeration<URL> urls = classLoader.getResources(resourceName);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url);
        }

    }
}
