package cn.myth.design_pattern.creational_pattern.singleton;

/**
 * 懒汉模式(线程不安全) 空时加载
 */
public class Singleton_01 {

    private static Singleton_01 instance;

    private Singleton_01() {
    }

    public static Singleton_01 getInstance() {
        if (instance == null)
        {
            instance = new Singleton_01();
        }
        return instance;
    }


}
