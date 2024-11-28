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
     *
     *  通过Proxy.newProxyInstance方法来实例化动态生成的代理类，而这个方法中的参数分别代表的意义是：
     *  1.ClassLoader loader：被代理类的类加载器
     *  2.Class<?>[]：被代理类实现的接口
     *  3.InvocationHandler h：实现指定接口InvocationHandler的实现类
     *  需要这三个参数的原因是：需要通过与代理类相同的类加载器去加载动态生成的代理类，
     *  同时代理类需要实现与被代理类相同的接口，最后需要通过实现指定接口InvocationHandler的实现类来完成代理调用方法的功能
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


    /*
     * 要扩展一个类有常见的两种方式，继承父类或实现接口。这两种方式都允许我们对方法的逻辑进行增强，
     * 但现在不是由我们自己来重写方法，而是想办法让jvm去调用InvocationHandler接口中的invoke方法，
     * 也就是说代理类需要和两个东西关联在一起：
     *  - 被代理类
     *  - InvocationHandler
     * 而jdk处理这个问题的方式就是选择继承父类Proxy，并把InvocationHandler存在父类的对象中：
     *
     *  public class Proxy implements java.io.Serializable {
     *      protected InvocationHandler h;
     *      protected Proxy(InvocationHandler h) {
     *          Objects.requireNonNull(h);
     *          this.h = h;
     *      }
     *      //...
     *   }
     *
     * 通过父类Proxy的构造方法，保存了创建代理对象过程中传进来的InvocationHandler的实例，使用protected修饰保证了它可以在子类中被访问和使用。
     * 但是同时，因为Java是单继承的，因此在继承了Proxy后，只能通过实现目标接口的方式来实现方法的扩展，达到我们增强目标方法逻辑的目的。
     *
     * 为什么动态代理类要继承Proxy类？--继承Proxy类可以减少代码的冗余度
     * 动态代理类只是做了一个转发，调用的还是被代理类的方法。如果我们将被代理类的属性和方法都写在动态代理类中，
     * 而在调用方法时依旧是通过转发，那么这些被继承的属性和方法实际上是没有用到的，这对于内存空间来说是一种浪费。
     *
     */
}
