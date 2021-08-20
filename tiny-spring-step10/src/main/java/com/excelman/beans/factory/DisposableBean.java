package com.excelman.beans.factory;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/17 上午10:35
 */

/**
 * 销毁bean的接口定义
 */
public interface DisposableBean {

    void destroy() throws Exception;
}
