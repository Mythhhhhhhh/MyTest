package cn.myth.design_pattern.creational_pattern.abstract_factory.factory.impl;

import cn.myth.design_pattern.creational_pattern.abstract_factory.factory.ICacheAdapter;
import cn.myth.design_pattern.creational_pattern.abstract_factory.matter.EGM;

import java.util.concurrent.TimeUnit;

public class EGMCacheAdapter implements ICacheAdapter {

    private final EGM egm = new EGM();

    public String get(String key) {
        return egm.gain(key);
    }

    public void set(String key, String value) {
        egm.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        egm.setEx(key, value, timeout, timeUnit);
    }

    public void del(String key) {
        egm.delete(key);
    }
}
