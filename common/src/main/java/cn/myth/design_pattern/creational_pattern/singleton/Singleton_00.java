package cn.myth.design_pattern.creational_pattern.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 静态类
 */
public class Singleton_00 {

    public static Map<String,String> cache = new ConcurrentHashMap<String, String>();

}
