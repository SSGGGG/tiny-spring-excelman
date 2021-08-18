package com.excelman.context;

import com.excelman.beans.factory.ListableBeanFactory;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/13 下午3:22
 */

/**
 * context是本次实现上下文功能新增的服务包
 * 继承于ListableBeanFactory，也就继承了BeanFactory的方法，比如一些getBean的方法
 * 另外ApplicationContext本身是Central接口，但是目前还不需要添加一些获取ID和父类上下文，所以暂时没有接口方法的定义
 */
public interface ApplicationContext extends ListableBeanFactory {
}
