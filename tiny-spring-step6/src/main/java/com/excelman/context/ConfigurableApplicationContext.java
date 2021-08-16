package com.excelman.context;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/13 下午3:35
 */

import com.excelman.beans.BeansException;

/**
 * 这个类继承自ApplicationContext，并提供了refresh这个核心方法
 * 接下来也是需要在上下文的实现中完成刷新容器的操作
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
