package cn.myth.design_pattern.creational_pattern.abstract_factory.factory.impl;

import cn.myth.design_pattern.creational_pattern.abstract_factory.factory.ICacheAdapter;
import cn.myth.design_pattern.creational_pattern.abstract_factory.matter.IIR;

import java.util.concurrent.TimeUnit;

public class IIRCacheAdapter implements ICacheAdapter {

    private final IIR iir = new IIR();

    public String get(String key) {
        return iir.get(key);
    }

    public void set(String key, String value) {
        iir.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        iir.setExpire(key, value, timeout, timeUnit);
    }

    public void del(String key) {
        iir.del(key);
    }

}
