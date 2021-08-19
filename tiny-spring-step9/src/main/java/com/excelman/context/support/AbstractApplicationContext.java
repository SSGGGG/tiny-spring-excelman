package com.excelman.context.support;

import com.excelman.beans.BeansException;
import com.excelman.beans.factory.ConfigurableListableBeanFactory;
import com.excelman.beans.factory.config.BeanFactoryPostProcessor;
import com.excelman.beans.factory.config.BeanPostProcessor;
import com.excelman.context.ConfigurableApplicationContext;
import com.excelman.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/13 下午3:44
 */

/**
 * 继承DefaultResourceLoader是为了处理spring.xml配置资源的加载
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    /**
     * refresh()方法就是整个Spring容器的操作过程
     * step8新增了额关于addBean
     * @throws BeansException
     */
    @Override
    public void refresh() throws BeansException {
        // 1.创建BeanFactory，并从上下文中重新加载BeanDefinition
        refreshBeanFactory();   // 子类实现

        // 2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory(); // 子类实现

        // 3.添加ApplicationContextAwareProcessor，让继承自ApplicationContextAware的Bean对象能感知到所属的ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4.在Bean实例化之前，执行BeanFactoryPostProcessor，对bean的定义BeanDefinition进行修改
        invokeBeanFactoryPostPostProcessors(beanFactory);

        // 5.将配置文件中的BeanPostProcessor添加到bean工厂中，后续调用createBean方法初始化bean的时候再调用注册的BeanPostProcessor的前后置处理方法
        registerBeanPostProcessors(beanFactory);

        // 6.提前实例化单例Bean对象，在这里调用了createBean方法创建bean
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 定义两个抽象方法，职责分担，交给下面的继承类实现
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 实例化bean之前，执行这个方法
     * @param beanFactory
     */
    private void invokeBeanFactoryPostPostProcessors(ConfigurableListableBeanFactory beanFactory){
        // 从这个工厂的BeanDefinition中获取BeanFactoryPostProcessor的beans，遍历这些bean，调用postXxx方法执行前置方法
        Map<String, BeanFactoryPostProcessor> beansOfType = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for(BeanFactoryPostProcessor beanFactoryPostProcessor : beansOfType.values()){
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 将BeanPostProcessor注册到BeanFactory中
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
        // 从BeanFactory中注册的BeanDefinition中查找BeanPostProcessor
        Map<String, BeanPostProcessor> beansOfType = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for(BeanPostProcessor beanPostProcessor : beansOfType.values()){
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    /**
     * 这里得到BeanFactory，并调用实现接口的destroySingletons方法
     * （但其实这个方法的具体实现是交由AbstractBeanFactory继承的父类实现的）
     */
    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }

    @Override
    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return getBeanFactory().getBean(beanName,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name,requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }
}
