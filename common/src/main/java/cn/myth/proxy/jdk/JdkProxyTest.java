package cn.myth.proxy.jdk;

import cn.myth.proxy.demo.IService;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  Proxy使用注意
 *  1. jdk中的Proxy只能为接口生成代理类，如果你想给某个类创建代理类，那么Proxy是无能为力的，此时需要我们用到下面要说的cglib了。
 *  2. Proxy类中提供的几个常用的静态方法需要掌握
 *  3. 通过Proxy创建代理对象，当调用代理对象任意方法时候，会被InvocationHandler接口中的invoke方法进行处理，这个接口内容是关键
 */
public class JdkProxyTest {

    /**
     *  方式一
     *  1.调用Proxy.getProxyClass方法获取代理类的Class对象
     *  2.使用InvocationHandler接口创建代理类的处理器
     *  3.通过代理类和InvocationHandler创建代理对象
     *  4.上面已经创建好代理对象了，接着我们就可以使用代理对象了
     */

    @Test
    public void m1() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
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
        IService proxyService = proxyClass.getConstructor(InvocationHandler.class).newInstance(invocationHandler);
        // 4.调用代理方法
        proxyService.m1();
        proxyService.m2();
        proxyService.m3();

    }

    /**
     *  方式二 更简单的方式
     *  1.使用InvocationHandler接口创建代理类的处理器
     *  2.使用Proxy类的静态方法newProxyInstance直接创建代理对象
     *  3.使用代理对象
     */

    @Test
    public void m2() {
        // 1.创建代理类的处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler，被调用的方法是：" + method.getName());
                return null;
            }
        };
        // 2.创建代理实例
        IService proxyService = (IService)
                Proxy.newProxyInstance(IService.class.getClassLoader(), new Class[]{IService.class}, invocationHandler);
        // 3.调用代理方法
        proxyService.m1();
        proxyService.m2();
        proxyService.m3();
    }
}
