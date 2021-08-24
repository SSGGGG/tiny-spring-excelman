package com.excelman.beans.factory;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/17 上午10:33
 */

/**
 * 初始化的接口定义
 */
public interface InitializingBean {

    /**
     * Bean处理了属性填充后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
