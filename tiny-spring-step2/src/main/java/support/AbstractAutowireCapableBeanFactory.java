package support;

import config.BeanDefinition;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午4:02
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    /**
     * 创建bean方法的实现：
     * 1. 首先由bean的定义，获取bean对应的class，并进行实例化
     * 2. 接着调用单例bean的addSingleton方法，将当前的bean添加到单例对象的缓存中
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try{
            // todo 这里有个坑，如果这里的bean对象有构造函数的话，怎么办？
            bean = beanDefinition.getBeanClass().newInstance();
        }catch (InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
        addSingleton(beanName,bean);
        return bean;
    }

    // 另一个得到bean定义的抽象方法留给下面的继承类实现
}
