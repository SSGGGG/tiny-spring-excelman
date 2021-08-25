package com.excelman.aop.framework;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/23 上午11:23
 */

/**
 * 代理的定义接口
 * 用途：用于获取代理类，具体实现代理的方式有JDK方式、CGLIB方式
 */
public interface AopProxy {

    Object getProxy();
}
