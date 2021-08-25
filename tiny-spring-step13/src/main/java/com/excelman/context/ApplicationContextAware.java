package com.excelman.context;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/19 上午10:00
 */

import com.excelman.beans.BeansException;
import com.excelman.beans.factory.Aware;

/**
 * Interface to be implemented by any object that wishes to be notified of the {@link ApplicationContext} that it runs in.
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
