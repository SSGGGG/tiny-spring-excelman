package com.excelman.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/12 下午8:07
 * ResourceLoader接口的默认实现类
 */
public class DefaultResourceLoader implements ResourceLoader{

    /**
     * 对三种不同类型的资源处理器进行包装，达到设计模式的作用，不会让外部调用知道过多的细节
     * @param location
     * @return
     */
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location,"location cannot be null");
        if(location.startsWith(CLASSPATH_URL_PREFIX)){
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        else{
            try{
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
