package com.excelman.context;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/20 上午11:06
 */

import java.util.EventListener;

/**
 * Interface to be implemented by application event listeners.
 * Based on the standard <code>java.util.EventListener</code> interface
 * for the Observer design pattern.
 * 定义事件监听器
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);
}
