package support;

import config.BeanDefinition;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午3:42
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory{

    /**
     * 对BeanFactory接口的实现
     * 1. 首先获取beanName对应的单例bean
     * 2. 如果获取不到的话，先获取对应的BeanDefinition定义，然后再创建bean
     * (其中得到bean定义以及创建bean的方法，由继承的子类实现)
     * @param beanName
     * @return
     */
    @Override
    public Object getBean(String beanName){
        // 调用父类单例的方法，根据beanName获取单例bean
        Object bean = getSingleton(beanName);
        if(null != bean){
            return bean;
        }
        // 若获取不到，则调用父类单例的方法获取beanDefinition，并调用createBean创建bean
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName,beanDefinition);
    }

    /**
     * 各司其职：定义两个抽象方法，给下面继承的类实现
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);
}
