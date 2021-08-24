package com.excelman.beans.factory;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/19 上午9:51
 */

/**
 * 标记类接口，实现该接口可以被Spring容器感知
 * 标注：在Spring中有特别多类似这样的标记接口的设计方式，可以方便统一摘取出属于此类接口的实现类，通常会有instanceof一起判断使用
 */
public interface Aware {
}
