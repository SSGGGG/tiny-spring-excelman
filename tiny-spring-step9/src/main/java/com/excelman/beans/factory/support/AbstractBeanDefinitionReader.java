package com.excelman.beans.factory.support;

import com.excelman.core.io.DefaultResourceLoader;
import com.excelman.core.io.ResourceLoader;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/12 下午8:23
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    /**
     * 抽象实现类
     * 实现接口的前两个方法，并提供构造函数，将bean的定义注册类和资源加载器，注入到类中
     * 这样具体的实现类，就可以通过资源加载器加载bean信息，并通过定义注册类将bean注册到容器中
     * （以前我们的操作是通过单元测试，调用BeanDefinitionRegistry完成bean的注册，现在可以直接放到xml文件中）
     */

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    /**
     * 这里将注册接口
     * @param registry
     */
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry){
        this(registry,new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry,ResourceLoader resourceLoader){
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
