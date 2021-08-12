package config;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/12 下午3:47
 * Bean的引用类型
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
