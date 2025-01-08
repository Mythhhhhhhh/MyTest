package cn.myth.design_pattern.creational_pattern.abstract_factory.impl;

import cn.myth.design_pattern.creational_pattern.abstract_factory.CacheService;
import cn.myth.design_pattern.creational_pattern.abstract_factory.RedisUtils;

import java.util.concurrent.TimeUnit;

public class CacheServiceImpl implements CacheService {

    private final RedisUtils redisUtils = new RedisUtils();

    public String get(String key) {
        return redisUtils.get(key);
    }

    public void set(String key, String value) {
        redisUtils.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        redisUtils.set(key, value, timeout, timeUnit);
    }

    public void del(String key) {
        redisUtils.del(key);
    }

}
