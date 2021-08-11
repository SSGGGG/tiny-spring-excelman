import bean.UserService;
import config.BeanDefinition;
import org.junit.Test;
import support.DefaultListableBeanFactory;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午7:37
 */
public class ApiTest {

    @Test
    public void testBeanFactory(){
        // 1.创建BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        // 3.调用getBean()方法获取bean对应的对象object并输出
        UserService userService = (UserService) beanFactory.getBean("userService", "excelman");
        userService.queryInfo();

        // 4.重新获取新的object并输出
        // 输出结果还是上一个，说明了获取到的是跟上个一样的单例实例
        UserService userServiceNew = (UserService) beanFactory.getBean("userService","sgcoder");
        userServiceNew.queryInfo();
    }
}
