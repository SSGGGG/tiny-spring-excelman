import bean.PropertyValue;
import bean.PropertyValues;
import config.BeanDefinition;
import config.BeanReference;
import dao.UserDao;
import service.UserService;
import support.AbstractAutowireCapableBeanFactory;
import support.DefaultListableBeanFactory;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/12 下午4:01
 */
public class Test {

    @org.junit.Test
    public void test_BeanFactory(){
        // 1. 创建bean工厂
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        // 2. 注入UserDao到容器中
        factory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));

        // 3. 注入UserService以及它的PropertyValues到容器中
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid","id1"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
        factory.registerBeanDefinition("userService",new BeanDefinition(UserService.class,propertyValues));

        // 4. 获取UserService的bean，测试方法是否成功注入属性
        UserService userService = (UserService) factory.getBean("userService");
        userService.queryUserInfo();
    }
}
