import beans.UserService;
import config.BeanDefinition;
import org.junit.Test;
import support.DefaultListableBeanFactory;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午4:22
 */
public class ApiTest {

    @Test
    public void testBeanFactory(){
        // 1. 初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 在bean工厂注册bean的定义
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        // 3. 第一次获取bean：在单例缓存中获取不到，因此获取bean定义，并调用createBean方法创建bean，放入单例的缓存中
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryInfo();

        // 4. 第二次获取bean，直接从单例缓存中获取
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryInfo();

        // 判断两个对象是否相同
        if(userService == userService_singleton)
            System.out.println("是的，它们相等");
    }
}
