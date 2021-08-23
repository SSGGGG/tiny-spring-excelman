package com.excelman.beans.factory;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/19 下午3:58
 */

/**
 * 需要提供3个方法：获取对象、对象类型，以及是否是单例对象（若是的话依然放到单例内存中）
 * @param <T>
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();
}
