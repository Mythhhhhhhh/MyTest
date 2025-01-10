package cn.myth.design_pattern.creational_pattern.singleton;

public class Singleton_02 {

    private static Singleton_02 instance;

    private Singleton_02() {
    }

    public static synchronized Singleton_02 getInstance()
    {
        if (instance == null)
        {
            instance = new Singleton_02();
        }
        return instance;
    }

}
