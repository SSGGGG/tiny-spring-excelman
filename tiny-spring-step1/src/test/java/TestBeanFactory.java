import bean.UserService;
import org.junit.Test;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午2:27
 * 测试Bean的创建工厂
 */
public class TestBeanFactory {

    @Test
    public void testFactory(){
        // 1. 初始化工厂
        BeanFactory beanFactory = new BeanFactory();

        // 2. 注册bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        // 3. 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
