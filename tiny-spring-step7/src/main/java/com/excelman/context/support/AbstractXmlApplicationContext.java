package com.excelman.context.support;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/13 下午4:51
 */

import com.excelman.beans.factory.support.DefaultListableBeanFactory;
import com.excelman.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 上下文中对配置信息的加载
 * 这个类负责具体实现：依赖BeanDefinitionReader的实现类，加载配置文件中bean的定义
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = getConfigLocations();
        if(null != locations){
            reader.loadBeanDefinitions(locations);
        }
    }

    /**
     * 这个方法留给下面继承类的目的是：为了从入口上下文类，拿到配置信息的地址描述
     * @return
     */
    protected abstract String[] getConfigLocations();
}
