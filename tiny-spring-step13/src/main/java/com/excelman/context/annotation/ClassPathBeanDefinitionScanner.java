package com.excelman.context.annotation;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/24 下午8:19
 */

import cn.hutool.core.util.StrUtil;
import com.excelman.beans.factory.config.BeanDefinition;
import com.excelman.beans.factory.support.BeanDefinitionRegistry;
import com.excelman.stereotype.Component;

import java.util.Set;

/**
 * 职责：依赖BeanDefinitionRegistry，扫描指定的package，将扫描的BeanDefinition进行注册
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider{

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 获取到扫描的类信息后，还需要获取Bean的作用域和类名（如果不配置类名，基本就是首字母缩写）
     * @param basePackages 扫描的包路径
     */
    public void doScan(String... basePackages){
        for(String backPackage : basePackages){
            // 获取扫描的类信息
            Set<BeanDefinition> candidates = findCandidateComponents(backPackage);
            for(BeanDefinition beanDefinition : candidates){
                // 解析Bean的作用域
                String scope = resolveBeanScope(beanDefinition);
                if(StrUtil.isNotEmpty(scope)){
                    beanDefinition.setScope(scope);
                }
                // 注册BeanDefinition
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }
    }

    /**
     * 解析获取BeanDefinition对应Bean的Scope注解
     * @param beanDefinition
     * @return
     */
    private String resolveBeanScope(BeanDefinition beanDefinition){
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope annotation = beanClass.getAnnotation(Scope.class);
        if(null != annotation) return annotation.value();
        return StrUtil.EMPTY;
    }

    /**
     * 获取注解Component的value，若为空，则beanName为首字母小写
     */
    private String determineBeanName(BeanDefinition beanDefinition){
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if(StrUtil.isEmpty(value)){
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }

}
