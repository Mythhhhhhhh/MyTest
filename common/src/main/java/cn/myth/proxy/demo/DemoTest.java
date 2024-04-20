package cn.myth.proxy.demo;

import org.junit.Test;

public class DemoTest {

    @Test
    public void serviceProxy() {
        IService serviceA = new ServiceProxy(new ServiceA());//@1
        IService serviceB = new ServiceProxy(new ServiceB()); //@2

        serviceA.m1();
        serviceA.m2();
        serviceA.m3();
        serviceB.m1();
        serviceB.m2();
        serviceB.m3();
    }


}
