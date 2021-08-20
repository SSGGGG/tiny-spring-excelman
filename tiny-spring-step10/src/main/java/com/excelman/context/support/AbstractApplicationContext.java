package com.excelman.context.support;

import com.excelman.beans.BeansException;
import com.excelman.beans.factory.ConfigurableListableBeanFactory;
import com.excelman.beans.factory.config.BeanFactoryPostProcessor;
import com.excelman.beans.factory.config.BeanPostProcessor;
import com.excelman.context.ApplicationEvent;
import com.excelman.context.ApplicationListener;
import com.excelman.context.ConfigurableApplicationContext;
import com.excelman.context.event.ApplicationEventMulticaster;
import com.excelman.context.event.ContextClosedEvent;
import com.excelman.context.event.ContextRefreshdEvent;
import com.excelman.context.event.SimpleApplicationEventMulticaster;
import com.excelman.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/13 下午3:44
 */

/**
 * 继承DefaultResourceLoader是为了处理spring.xml配置资源的加载
 * 主要职责：
 * 1. 依赖BeanFactory，将Bean的创建过程整合到refresh()方法中
 * 2. step10新增：实现事件发布者接口，依赖事件广播器类，负责初始化事件发布者、注册事件监听器以及发布事件
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

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

        // step10新增的：关于事件发布以及监听的流程
        // 7.初始化事件广播器类，并添加到单例内存中
        initApplicationEventMulticaster();

        // 8.将spring.xml中加载的监听器注册到事件广播器类中
        registerListeners();

        // 9.发布容器刷新事件
        finishRefresh();
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
     * step10新增：先发布关闭事件
     * 再调用工厂的destroySingletons方法，执行bean的销毁
     */
    @Override
    public void close() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        getBeanFactory().destroySingletons();
    }

    private void initApplicationEventMulticaster(){
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    private void registerListeners(){
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for(ApplicationListener listener : applicationListeners){
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void finishRefresh(){
        publishEvent(new ContextRefreshdEvent(this));
    }

    /**
     * 实现ApplicationEventPublisher接口的方法，调用广播器的发布事件方法
     * @param event the event to publish
     */
    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
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
