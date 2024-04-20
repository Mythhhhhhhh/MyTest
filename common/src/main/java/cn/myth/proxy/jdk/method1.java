package cn.myth.proxy.jdk;

import cn.myth.proxy.demo.IService;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class method1 {

    /**
     *  方式一
     *  1.调用Proxy.getProxyClass方法获取代理类的Class对象
     *  2.使用InvocationHandler接口创建代理类的处理器
     *  3.通过代理类和InvocationHandler创建代理对象
     *  4.上面已经创建好代理对象了，接着我们就可以使用代理对象了
     */

    @Test
    public void m1() {
        // 1.获取接口对应的代理类
        Class<IService> proxyClass = (Class<IService>)
                Proxy.getProxyClass(IService.class.getClassLoader(), IService.class);
        // 2.创建代理类的处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler，被调用的方法是：" + method.getName());
                return null;
            }
        };
        // 3.创建代理实例
    }

}
