package com.excelman.context.event;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/20 上午10:56
 */

/**
 * 事件类：负责监听刷新的动作
 */
public class ContextRefreshdEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshdEvent(Object source) {
        super(source);
    }
}
