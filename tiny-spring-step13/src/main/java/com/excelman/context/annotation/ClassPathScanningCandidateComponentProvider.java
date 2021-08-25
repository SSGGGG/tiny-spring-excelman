package com.excelman.context.annotation;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/24 下午8:08
 */

import cn.hutool.core.util.ClassUtil;
import com.excelman.beans.factory.config.BeanDefinition;
import com.excelman.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 对象扫描装配，依赖XmlBeanDefinitionReader
 * 职责：扫描basePackage下的所有@Componnet类
 */
public class ClassPathScanningCandidateComponentProvider {

    /**
     * 扫描指定路径下的bean，解析出classes信息，扫描到所有@Component注解的bean对象
     * @param basePackage 待扫描的包
     * @return
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage){
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for(Class<?> clazz : classes){
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}
