package com.excelman.test.event;

import com.excelman.context.ApplicationListener;
import com.excelman.context.event.ContextClosedEvent;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/20 下午4:10
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("执行close操作");
    }
}
