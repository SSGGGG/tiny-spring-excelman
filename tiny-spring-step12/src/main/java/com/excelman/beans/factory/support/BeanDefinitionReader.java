package com.excelman.beans.factory.support;

import com.excelman.core.io.Resource;
import com.excelman.core.io.ResourceLoader;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/12 下午8:14
 * Bean定义读取的接口
 * 这是一个简单的bean定义的接口，包括了：getRegistry()、getResourceLoader()和三个加载Bean定义的方法
 × 其中前两个方法留给实现的抽象类来实现，三个加载bean定义的方法留给具体的实现类实现
 */
public interface BeanDefinitionReader {

    /**
     * 下面两个方法交给抽象类实现
     * @return
     */

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    /**
     * 下面三个方法交给具体实现类来实现
     * @param resource
     */

    void loadBeanDefinitions(Resource resource);

    void loadBeanDefinitions(Resource... resources);

    void loadBeanDefinitions(String location);

    void loadBeanDefinitions(String... locations);
}
