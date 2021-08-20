package com.excelman.context.event;

import com.excelman.beans.BeansException;
import com.excelman.beans.factory.BeanFactory;
import com.excelman.beans.factory.BeanFactoryAware;
import com.excelman.context.ApplicationEvent;
import com.excelman.context.ApplicationListener;
import com.excelman.util.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/20 上午11:14
 */

/**
 * 该类的职责：
 * 负责通用方法（添加和删除监听器）的处理，以及添加getApplicationListeners和supportsEvent的方法处理
 *
 * 接口定义的具体发布事件multicastEvent()方法，由继承的类实现，在该方法中，调用本类实现的getApplicationListeners方法获取event对应的监听器，并调用监听器的处理事件方法
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    /**
     * 实现自BeanFactory接口的方法
     * todo 这个怎么使用，有什么目的？
     * @param beanFactory
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * 提取符合广播事件中的监听处理器，具体过滤规则由supportsEvent()方法实现
     * @param event 将要广播的事件
     * @return 符合的监听处理器
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event){
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for(ApplicationListener<ApplicationEvent> listener:applicationListeners){
            if(supportsEvent(listener,event))
                allListeners.add(listener);
        }
        return allListeners;
    }

    /**
     * 具体判断监听器是否对当前事件感兴趣
     * （若监听器绑定的事件类型是当前事件类型的父类或者本类，则表示感兴趣）
     * ！！！这里使用到了反射机制！！！
     * @return
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event){
        // 1. 首先获取当前监听器的类class
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();

        // 2. 判断是否是CGLIB创建的类对象，若是的话，实际的class是superClass
        Class<?> targetClass = ClassUtils.isCglibProxyCLass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;

        // 3. 获取当前监听器的实现接口（即ApplicationListener）
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        // 4. 获取接口的绑定泛型类型
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];

        // 5. 获取类名
        String className = actualTypeArgument.getTypeName();

        // 6. 加载获取Event的具体类
        Class<?> eventClass;
        try{
            eventClass = Class.forName(className);
        }catch (ClassNotFoundException e) {
            throw new BeansException("beanName "+className+"找不到");
        }

        // 判断监听器绑定的Event类型是否是当前事件的父类或者是本类
        return eventClass.isAssignableFrom(event.getClass());
    }
}
