package com.excelman.test;

import com.excelman.context.support.ClassPathXmlApplicationContext;
import com.excelman.test.common.MyBeanPostProcessor;
import com.excelman.test.bean.UserService;
import com.excelman.beans.factory.support.DefaultListableBeanFactory;
import com.excelman.beans.factory.xml.XmlBeanDefinitionReader;
import com.excelman.test.common.MyBeanFactoryPostProcessor;
import org.junit.Test;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/16 上午10:28
 */
public class ApiTest {

    @Test
    public void test_beanFactoryPostProcessorAndBeanPostProcessor(){
        // 1. 首先初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 通过xml读取器，读取配置文件，并注册bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 在BeanDefinition加载完成 & Bean的实例化之前，修改BeanDefinition的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean实例化之后，扩展，修改Bean属性的信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5. 获取bean，调用测试方法
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    @Test
    public void test_xmlReader(){
        // 1. 创建ClassPathXMLApplicationContext对象，构造方法调用refresh()方法，该方法集成了所有的步骤
        // 包括：创建BeanFactory，通过xml读取器读取配置并注册bean，调用BeanFactoryPostProcessor，注册BeanPostProcessor，初始化创建bean
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:springTestXml.xml");

        // 因为context的最顶层父类继承于BeanFactory，因此具有创建bean的功能
        UserService userService = (UserService) context.getBean("userService");
        userService.queryUserInfo();
    }

}
