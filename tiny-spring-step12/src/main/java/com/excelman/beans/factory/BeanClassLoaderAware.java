package com.excelman.beans.factory;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/19 上午9:54
 */

/**
 * Callback that allows a bean to be aware of the bean{@link ClassLoader class loader};
 * that is, the class loader used by the present bean factory to load bean classes.
 * 实现此接口，就能感知到所属的ClassLoader
 */
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader);

}