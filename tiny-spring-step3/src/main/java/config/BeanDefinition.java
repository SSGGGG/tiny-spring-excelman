package config;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午3:27
 * @description bean的定义
 */
public class BeanDefinition {

    /**
     * 这里采用Class来定义一个对象，这样的话就可以把bean的实例化操作放到容器中处理
     */
    private Class beanClass;

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
