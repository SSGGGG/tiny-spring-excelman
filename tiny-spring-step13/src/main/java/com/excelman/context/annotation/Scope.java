package com.excelman.context.annotation;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/24 下午7:58
 */

import java.lang.annotation.*;

/**
 * 定义拦截注解
 * 用途：用于配置作用域的自定义注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";
}
