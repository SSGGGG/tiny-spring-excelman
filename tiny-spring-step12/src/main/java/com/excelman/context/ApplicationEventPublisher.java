package com.excelman.context;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/20 下午3:01
 */

/**
 * 事件发布者的定义
 */
public interface ApplicationEventPublisher {

    /**
     * Notify all listeners registered with this application of an application
     * event. Events may be framework events (such as RequestHandledEvent)
     * or application-specific events.
     * @param event the event to publish
     */
    void publishEvent(ApplicationEvent event);
}
