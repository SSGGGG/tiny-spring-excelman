package support;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午3:42
 */
public interface BeanFactory {

    Object getBean(String beanName);

    Object getBean(String beanName, Object... args);

}
