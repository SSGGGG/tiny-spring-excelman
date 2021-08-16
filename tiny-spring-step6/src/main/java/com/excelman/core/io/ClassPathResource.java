package com.excelman.core.io;


import com.excelman.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/12 下午7:30
 * 从classpath加载的资源加载器
 */
public class ClassPathResource implements Resource{

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path){
        this(path,(ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader){
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        // 通过类加载器获取路径文件的inputStream
        InputStream is = classLoader.getResourceAsStream(path);
        if(null == is){
            throw new FileNotFoundException("cannot open");
        }
        return is;
    }
}
