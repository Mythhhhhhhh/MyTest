package cn.myth.proxy.cglib;

import cn.myth.proxy.demo.ServiceA;
import net.sf.cglib.proxy.*;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 *  CGLIB <a href="https://github.com/cglib/cglib">...</a>
 *  </p>
 *  本质上它是通过动态的生成一个子类去覆盖所要代理的类（非final修饰的类和方法）
 *  Enhancer可能是CGLIB中最常用的一个类，和jdk中的Proxy不同的是，Enhancer既能够代理普通的class，也能够代理接口
 *  Enhancer创建一个被代理对象的子类并且拦截所有的方法调用（包括从Object中继承的toString和hashCode方法）
 *  Enhancer不能够拦截final方法，例如Object.getClass()方法，这是由于Java final方法语义决定的
 *  基于同样的道理，Enhancer也不能对final类进行代理操作
 *  <p>
 *  spring已将第三方cglib jar包中所有的类集成到spring自己的jar包中
 */
public class CglibProxyTest {

    /**
     *  1.拦截所有方法
     */
    @Test
    public void test1() {
        // 使用Enhancer来给某个类创建代理类，步骤
        // 1.创建Enhancer对象
        Enhancer enhancer = new Enhancer();
        // 2.通过setSuperclass来设置父类型，即需要给哪个类创建代理类
        enhancer.setSuperclass(ServiceA.class);
        // 3.设置回调，需要实现org.springframework.cglib.proxy.Callback(或net.sf.cglib.proxy.Callback)接口
        //   此处我们使用的是MethodInterceptor，也是一个接口，实现了Callback接口
        //   当调用代理对象的任何方法的时候，都会被MethodInterceptor接口的invoke方法处理
        enhancer.setCallback(new MethodInterceptor() {
            /**
             * 代理对象拦截器
             * @param o 代理对象
             * @param method 被代理的类的方法
             * @param objects 调用方法传递的参数
             * @param methodProxy 方法代理对象
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("调用方法：" + method);
                // 可以调用MethodProxy的invokeSuper调用被代理类的方法
                Object result = methodProxy.invokeSuper(o, objects);
                return result;
            }
        });
        // 4.获取代理对象，调用enhance.create方法获取代理对象，这个方法返回的是Object类型的，所以需要强转一下
        ServiceA proxy = (ServiceA) enhancer.create();
        // 5.调用代理对象的方法
        proxy.m1();
        proxy.m2();
    }

    /**
     *  2.拦截所有方法 m1和m2方法都被拦截器拦截
     */
    @Test
    public void test2() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ServiceC.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("调用方法：" + method);
                Object result = methodProxy.invokeSuper(o, objects);
                return result;
            }
        });
        ServiceC proxy = (ServiceC) enhancer.create();
        proxy.m1();
    }

    // 3.拦截所有方法并返回固定值（FixedValue）
    @Test
    public void test3() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ServiceD.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Myth";
            }
        });
        ServiceD proxy = (ServiceD) enhancer.create();
        System.out.println(proxy.m1());
        System.out.println(proxy.m2());
        System.out.println(proxy.toString());
    }

    // 4.直接放行，不做任何操作（NoOp.INSTANCE）
    @Test
    public void test4() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ServiceD.class);
        enhancer.setCallback(NoOp.INSTANCE);
        ServiceD proxy = (ServiceD) enhancer.create();
        System.out.println(proxy.m1());
        System.out.println(proxy.m2());
    }

    // 5.不同的方法使用不同的拦截器（CallbackFilter）
    @Test
    public void test5() {
        // 1. 以insert开头的方法需要统计方法耗时
        // 2. 以get开头的的方法直接返回固定字符串
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ServiceE.class);
        // 创建2个Callback
        Callback[] callbacks = {
                // 这个用来拦截所有insert开头的方法
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        long starTime = System.nanoTime();
                        Object result = methodProxy.invokeSuper(o, objects);
                        long endTime = System.nanoTime();
                        System.out.println(method + "，耗时(纳秒):" + (endTime -
                                starTime));
                        return result;
                    }
                },
                // 下面这个用来拦截所有get开头的方法，返回固定值的
                new FixedValue() {
                    @Override
                    public Object loadObject() throws Exception {
                        return "Myth";
                    }
                }
        };

//        enhancer.setCallbackFilter(new CallbackFilter() {
//            @Override
//            public int accept(Method method) {
//                return 0;
//            }
//        });

        // 调用enhancer的setCallbacks传递Callback数组
        enhancer.setCallbacks(callbacks);
        /**
         *  设置过滤器CallbackFilter
         *  CallbackFilter用来判断调用方法的时候使用callbacks数组中的哪个Callback来处理当前方法
         *  返回的是callbacks数组的下标
         */
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                // 获取当前调用的方法的名称
                String methodName = method.getName();
                return methodName.startsWith("insert") ? 0 : 1;
            }
        });

        ServiceE proxy = (ServiceE) enhancer.create();
        System.out.println("---------------");
        proxy.insert1();
        System.out.println("---------------");
        proxy.insert2();
        System.out.println("---------------");
        System.out.println(proxy.get1());
        System.out.println("---------------");
        System.out.println(proxy.get2());
    }

    // 6.对案例5的优化（CallbackHelper）
    @Test
    public void test6() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ServiceE.class);
        // 创建2个Callback
        Callback costTimeCallback = (MethodInterceptor) (Object o, Method method,
                                                         Object[] objects, MethodProxy methodProxy) -> {
            long starTime = System.nanoTime();
            Object result = methodProxy.invokeSuper(o, objects);
            long endTime = System.nanoTime();
            System.out.println(method + "，耗时(纳秒):" + (endTime - starTime));
            return result;
        };
        // 下面这个用来拦截所有get开头的方法，返回固定值的
        Callback fixdValueCallback = (FixedValue) () -> "Myth";

        CallbackHelper callbackHelper = new CallbackHelper(ServiceE.class, null) {
            @Override
            protected Object getCallback(Method method) {
                return method.getName().startsWith("insert") ? costTimeCallback : fixdValueCallback;
            }
        };

        //  调用enhancer的setCallbacks传递Callback数组
        enhancer.setCallbacks(callbackHelper.getCallbacks());
        //  设置CallbackFilter,用来判断某个方法具体走哪个Callback
        enhancer.setCallbackFilter(callbackHelper);

        ServiceE proxy = (ServiceE) enhancer.create();
        System.out.println("---------------");
        proxy.insert1();
        System.out.println("---------------");
        proxy.insert2();
        System.out.println("---------------");
        System.out.println(proxy.get1());
        System.out.println("---------------");
        System.out.println(proxy.get2());

    }


}
