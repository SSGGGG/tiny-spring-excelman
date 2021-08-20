package com.excelman.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/12 下午7:28
 * 资源加载的接口定义
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
