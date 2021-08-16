package com.excelman.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.excelman.beans.BeansException;
import com.excelman.beans.PropertyValue;
import com.excelman.beans.PropertyValues;
import com.excelman.beans.factory.config.AutowireCapableBeanFactory;
import com.excelman.beans.factory.config.BeanDefinition;
import com.excelman.beans.factory.config.BeanPostProcessor;
import com.excelman.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午4:02
 * 这个抽象类负责创建bean
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    /**
     * 注入实例化的策略
     */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 创建bean方法的实现：
     * 1. 首先由bean的定义，获取bean对应的class，并进行实例化
     * 2. 给bean填充属性
     * 3. step6新增的：调用初始化方法，执行BeanPostProcessor的前置和后置处理
     * 4. 接着调用单例bean的addSingleton方法，将当前的bean添加到单例对象的缓存中
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        // 实例化bean对象
        Object bean = null;
        try{
            bean = createBeanInstance(beanDefinition,beanName,args);
            // 给bean填充属性
            applyPropertyValues(beanName,bean,beanDefinition);
            // 执行bean的初始化方法和BeanPostProcessor的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        }catch (Exception e){
            e.printStackTrace();
        }
        // 添加到单例缓存中
        addSingleton(beanName,bean);
        return bean;
    }

    /**
     * 根据bean的定义获取class，再根据class获取它的构造函数
     * 循环遍历构造函数集合与入参的信息的匹配情况：这里只对比数量（实际源码还要对比入参的类型）
     * 最后调用实例化策略的方法，进行实例化对象
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object[] args){
        Class beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        Constructor constructorToUse = null;
        for(Constructor ctor:declaredConstructors){
            if(null != args && ctor.getParameterTypes().length == args.length){
                constructorToUse = ctor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition,beanName,constructorToUse,args);
    }

    /**
     * Bean属性填充，为当前这个BeanDefinition对应的bean属性填充
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        try{
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for(PropertyValue propertyValue : propertyValues.getPropertyValues()){
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                // value类型是bean的引用类型的情况
                if(value instanceof BeanReference){
                    // 当前对象依赖value对象
                    BeanReference beanReference = (BeanReference) value;
                    // 通过递归getBean方法，获取单例容器中的bean
                    // todo 这里没有解决循环依赖的问题
                    value = getBean(beanReference.getBeanName());
                }
                // 填充bean的属性
                BeanUtil.setFieldValue(bean,name,value);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 执行初始化bean，调用BeanPostProcessor Before和After进行初始化的前后置处理
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @return
     */
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition){
        // 1.执行BeanPostProcessor Before处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean,beanName);

        // 2.待完成内容：invokeInitMethods
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 3.执行BeanPostProcessor After处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean,beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {

    }

    /**
     * 初始化前置处理
     */
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        // 遍历所有的前置处理并执行
        for(BeanPostProcessor processor : getBeanPostProcessorList()){
            Object current = processor.postProcessBeforeInitialization(result,beanName);
            if(null == current) return result;
            result = current;
        }
        return result;
    }

    /**
     * 初始化后置处理
     */
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        // 遍历所有的后置处理并执行
        for(BeanPostProcessor processor : getBeanPostProcessorList()){
            Object current = processor.postProcessAfterInitialization(result,beanName);
            if(null == current) return result;
            result = current;
        }
        return result;
    }

    /**
     * getter/setter
     * @return
     */
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    // 另一个得到bean定义的抽象方法留给下面的继承类实现
}
