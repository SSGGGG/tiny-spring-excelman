package com.excelman.context.event;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/20 上午11:00
 */

import com.excelman.context.ApplicationEvent;
import com.excelman.context.ApplicationListener;

/**
 * 事件广播器的定义
 * 职责：注册监听器和删除监听器，以及广播发布事件
 */
public interface ApplicationEventMulticaster {

    /**
     * Add a listener to be notified of all events
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 最终推送事件消息会经过这个multicastEvent()方法来处理哪个监听器该接收事件
     * Multicast the given application event to appropriate listeners.
     * @param event the event to multicast
     */
    void multicastEvent(ApplicationEvent event);
}
