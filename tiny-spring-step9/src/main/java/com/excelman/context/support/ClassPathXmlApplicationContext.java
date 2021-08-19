package com.excelman.context.support;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/13 下午5:01
 */

import com.excelman.beans.BeansException;

/**
 * 具体实现类，给用户提供的应用上下文方法
 * 在继承了层层的抽象类的功能分离实现后，在此类的实现中就简单多了
 *
 * 主要职责是：对继承抽象类中方法的调用和提供配置文件地址信息
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    private String[] configLocations;

    public ClassPathXmlApplicationContext(){}

    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException{
        this.configLocations = configLocations;
        // 执行刷新操作：创建新的BeanFactory，重新加载Context上下文的bean定义（其中上下文locations在本类中定义）
        // 并调用BeanFactoryPostProcessor，注册BeanPostProcessor，接着初始化Bean
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
