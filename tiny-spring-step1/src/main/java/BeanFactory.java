import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午2:15
 * @description Bean对象的工厂，可以存放bean定义到map中以及获取
 */
public class BeanFactory {

    /**
     * 采用同步的map实现
     */
    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 获取name对应的BeanDefinition，再获取这个bean封装的object
     * @param name
     * @return
     */
    public Object getBean(String name){
        return beanDefinitionMap.get(name).getBean();
    }

    public void registerBeanDefinition(String name,BeanDefinition beanDefinition){
        beanDefinitionMap.put(name,beanDefinition);
    }
}
