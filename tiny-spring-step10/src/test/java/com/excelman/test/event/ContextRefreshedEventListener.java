package com.excelman.test.event;

import com.excelman.context.ApplicationListener;
import com.excelman.context.event.ContextClosedEvent;
import com.excelman.context.event.ContextRefreshdEvent;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/20 下午4:09
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshdEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshdEvent event) {
        System.out.println("执行refresh操作");
    }
}
