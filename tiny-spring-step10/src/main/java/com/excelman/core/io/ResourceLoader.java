package com.excelman.core.io;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/12 下午8:05
 * 包装资源加载器：按照资源加载的不同方式，把这些方式集中到同意的类服务下进行处理
 * 外部用户只需要传一个地址即可
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
