/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午2:14
 * @description 用于定义Bean实例化对象
 */
public class BeanDefinition {

    /**
     * 目前的bean定义中，只有一个Object用于存放bean对象
     * 其他的属性可以参考Spring源码进行补充
     * 后续的工作中也会不断的补充
     */
    private Object bean;

    public BeanDefinition(Object bean){
        this.bean = bean;
    }

    public Object getBean(){
        return bean;
    }
}
