package com.excelman.beans.factory.support;

import com.excelman.beans.BeansException;
import com.excelman.beans.factory.DisposableBean;
import com.excelman.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/11 下午3:33
 * 单例对象的管理，包括了获取和添加
 * 并且负责对DisposableBean的注册以及销毁（但是销毁方法的具体实现交由Adapter实现）
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * Internal marker for a null singleton object:
     * used as marker value for concurrent Maps (which don't support null values).
     */
    protected static final Object NULL_OBJECT = new Object();

    private Map<String,Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 将单例对象放到内存中
     * @param beanName
     * @param singletonObject
     */
    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean){
        disposableBeanMap.put(beanName,bean);
    }

    /**
     * 由接口ConfigurableBeanFactory定义
     */
    public void destroySingletons(){
        Set<String> keySet = this.disposableBeanMap.keySet();
        Object[] disposableBeanNames = keySet.toArray();
        // 注意！！！销毁Bean的顺序是按照先到后销毁的顺序
        for(int i = disposableBeanNames.length-1; i >= 0; i--){
            Object disposableBeanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeanMap.get(disposableBeanName);
            try{
                disposableBean.destroy();
            }catch (Exception e){
                throw new BeansException("Destroy method on bean with name:"+disposableBeanName+" threw an exception");
            }
        }

    }

}
