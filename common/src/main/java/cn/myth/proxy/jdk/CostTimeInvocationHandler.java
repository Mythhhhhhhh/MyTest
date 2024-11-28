package cn.myth.proxy.jdk;

import cn.myth.proxy.demo.IService;
import cn.myth.proxy.demo.ServiceA;
import cn.myth.proxy.demo.ServiceB;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  通过jdk动态代理实现一个通用的代理，解决统计所有接口方法耗时的问题。
 */
public class CostTimeInvocationHandler implements InvocationHandler {

    private final Object target;

    public CostTimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        Object result = method.invoke(this.target, args);
        long endTime = System.nanoTime();
        System.out.println(this.target.getClass() + "->" + method.getName() +  "方法耗时(纳秒)");
        return result;
    }


    /**
     * 用来创建targetInterface接口的代理对象
     *
     * @param target 需要被代理的对象
     * @param targetInterface 被代理的接口
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Object target, Class<T> targetInterface) {
        if (!targetInterface.isInterface()) {
            throw new IllegalStateException("targetInterface必须是接口类型！");
        } else if (!targetInterface.isAssignableFrom(target.getClass()))  {
            throw new IllegalStateException("targetInterface必须是接口的实现类！");
        }
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new CostTimeInvocationHandler(target));
    }

    public static void main(String[] args) {
        // 输出变量
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        IService serviceA = CostTimeInvocationHandler.createProxy(new ServiceA(), IService.class);
        IService serviceB = CostTimeInvocationHandler.createProxy(new ServiceB(), IService.class);

        System.out.println(serviceA.getClass());

        serviceA.m1();
        serviceA.m2();
        serviceA.m3();
        serviceB.m1();
        serviceB.m2();
        serviceB.m3();
    }

}
