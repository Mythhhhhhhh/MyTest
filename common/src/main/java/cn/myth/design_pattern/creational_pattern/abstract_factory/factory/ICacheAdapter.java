package cn.myth.design_pattern.creational_pattern.abstract_factory.factory;

import java.util.concurrent.TimeUnit;

/**
 * 适配接口
 * 分别包装两个集群中差异化的接口名称。EGMCacheAdapter、IIRCacheAdapter
 */
public interface ICacheAdapter {

    String get(String key);

    void set(String key, String value);

    void set(String key, String value, long timeout, TimeUnit timeUnit);

    void del(String key);

}
