package com.excelman.util;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/12 下午7:38
 * 获取类加载器
 */
public class ClassUtils {

    /**
     * 获取当前线程的类加载器，若为空的话，则获取当前类的类加载器
     * @return
     */
    public static ClassLoader getDefaultClassLoader(){
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        }catch (Throwable ex){
            // Cannot access thread context ClassLoader
        }
        if(null == cl){
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

}
