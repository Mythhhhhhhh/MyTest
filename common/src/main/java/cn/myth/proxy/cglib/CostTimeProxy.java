package cn.myth.proxy.cglib;

import cn.myth.proxy.demo.ServiceA;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *  通过Cglib动态代理实现通用的统计任意类方法耗时代理类
 */
public class CostTimeProxy implements MethodInterceptor {

    // 目标对象
    private final Object target;

    public CostTimeProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long starTime = System.nanoTime();
        // 调用被代理对象（即target）的方法，获取结果
        Object result = methodProxy.invoke(target, objects);
        // Object result = methodProxy.invokeSuper(o, objects);
        long endTime = System.nanoTime();
        System.out.println(method + "，耗时(纳秒)：" + (endTime - starTime));
        return result;
    }

    /**
     * 创建任意类的代理对象
     *
     * @param target
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(T target) {
        CostTimeProxy costTimeProxy = new CostTimeProxy(target);
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(costTimeProxy);
        enhancer.setSuperclass(target.getClass());
        return (T) enhancer.create();
    }

    public static void main(String[] args) {
        ServiceA serviceA = CostTimeProxy.createProxy(new ServiceA());
        serviceA.m1();

        ServiceC serviceC = CostTimeProxy.createProxy(new ServiceC());
        serviceC.m1();
    }
}
