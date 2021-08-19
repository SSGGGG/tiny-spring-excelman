package com.excelman.test;

import cn.hutool.core.io.IoUtil;
import com.excelman.beans.factory.xml.XmlBeanDefinitionReader;
import com.excelman.core.io.DefaultResourceLoader;
import com.excelman.core.io.Resource;
import org.junit.Before;
import org.junit.Test;
import com.excelman.beans.factory.support.DefaultListableBeanFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/13 上午9:41
 */
public class ApiTest {

    private DefaultResourceLoader resourceLoader;

    @Before
    public void init(){
        resourceLoader = new DefaultResourceLoader();
    }

    /**
     * 以下三个方法，测试三种不同方式的资源加载
     * @throws IOException
     */

    @Test
    public void test_load_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_load_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_load_url(){
        // ...
    }

    /**
     * 以下方法的目的是：测试xml文件的加载和解析，并注入bean到容器中
     */
    @Test
    public void test_xml_parse(){
        // 1.创建注册类
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        // 2.将注册类注入到xmlBean读取器中，并读取指定的xml文件，将bean注入到注册类容器中
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 3.调用注册类从BeanFactory继承下来的getBaen方法获取bean，具体创建bean的过程在AbstractBeanFactory实现
        UserService userService = factory.getBean("userService",UserService.class);
        userService.queryUserInfo();
    }

}
