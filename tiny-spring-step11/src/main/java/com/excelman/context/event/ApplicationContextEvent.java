package com.excelman.context.event;

import com.excelman.context.ApplicationContext;
import com.excelman.context.ApplicationEvent;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/20 上午10:51
 */

/**
 * 这个类是定义事件的抽象类，所有的事件包括关闭、刷新以及用户自己实现的事件，都需要继承这个类
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * Get the ApplicationContext that the event was raised for
     */
    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}
