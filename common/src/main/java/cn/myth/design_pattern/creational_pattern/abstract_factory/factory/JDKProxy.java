package cn.myth.design_pattern.creational_pattern.abstract_factory.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JDKProxy {

    /*
     * 这里的抽象工厂的创建和获取方式，会采用代理类的方式进行实现。所被代理的类就是目前的Redis操作方法类，让这个类在不需要任何修改下，就可以实现调用集群A和集群B的数据服务。
     * 并且这里还有一点非常重要，由于集群A和集群B在部分方法提供上是不同的，因此需要做一个接口适配，而这个适配类就相当于工厂中的工厂，用于创建把不同的服务抽象为统一的接口做相同的业务。
     */


    @SuppressWarnings("unchecked")
    public static <T> T getProxy(Class<T> interfaceClass, ICacheAdapter cacheAdapter) throws Exception {
        InvocationHandler handler = new JDKInvocationHandler(cacheAdapter);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] classes = interfaceClass.getInterfaces();
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{classes[0]}, handler);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getProxy2(Class<T> interfaceClass, ICacheAdapter cacheAdapter) throws Exception {
        InvocationHandler handler = new JDKInvocationHandler(cacheAdapter);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{interfaceClass}, handler);
    }


    /**
     * 用来创建targetInterface接口的代理对象
     *
     * @param target 需要被代理的对象
     * @param targetInterface 被代理的接口
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProxyByTargetObject(Object target, Class<T> targetInterface, ICacheAdapter cacheAdapter) {
        if (!targetInterface.isInterface()) {
            throw new IllegalStateException("targetInterface必须是接口类型！");
        } else if (!targetInterface.isAssignableFrom(target.getClass()))  {
            throw new IllegalStateException("targetInterface必须是接口的实现类！");
        }
        InvocationHandler handler = new JDKInvocationHandler(cacheAdapter);
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), handler);
    }

}
