package com.excelman.context.event;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/20 下午3:28
 */

import com.excelman.beans.factory.BeanFactory;
import com.excelman.context.ApplicationEvent;
import com.excelman.context.ApplicationListener;

/**
 * ApplicationEventMulticaster的简单实现
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for(final ApplicationListener listener: getApplicationListeners(event)){
            listener.onApplicationEvent(event);
        }
    }
}
