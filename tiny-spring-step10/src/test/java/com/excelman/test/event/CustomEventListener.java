package com.excelman.test.event;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/20 下午4:03
 */

import com.excelman.context.ApplicationListener;

import java.util.Date;

/**
 * 自定义监听器
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    /**
     * 监听器收到消息后，调用该方法
     */
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到："+event.getSource() + "消息；时间："+new Date());
        System.out.println("消息："+event.getId() + "," +event.getMessage());
    }
}
