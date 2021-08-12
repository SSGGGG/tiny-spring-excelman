package support;

import bean.PropertyValue;
import bean.PropertyValues;
import cn.hutool.core.bean.BeanUtil;
import config.BeanDefinition;
import config.BeanReference;
import strategy.CglibSubclassingInstantiationStrategy;
import strategy.InstantiationStrategy;

import java.lang.reflect.Constructor;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午4:02
 * 这个抽象类只负责createBean的实现
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    /**
     * 注入实例化的策略
     */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 创建bean方法的实现：
     * 1. 首先由bean的定义，获取bean对应的class，并进行实例化
     * 2. 给bean填充属性
     * 3. 接着调用单例bean的addSingleton方法，将当前的bean添加到单例对象的缓存中
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        // 实例化bean对象
        Object bean = null;
        try{
            bean = createBeanInstance(beanDefinition,beanName,args);
            // 给bean填充属性
            applyPropertyValues(beanName,bean,beanDefinition);
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
