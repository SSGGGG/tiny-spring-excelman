package com.excelman.stereotype;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/24 下午8:00
 */

import java.lang.annotation.*;

/**
 * 定义拦截注解
 * 用途：用于配置类作为bean
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    // 指定value的bean
    String value() default "";
}
