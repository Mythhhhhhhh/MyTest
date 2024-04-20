package cn.myth.proxy.demo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServiceProxy implements IService {

    //目标对象，被代理的对象
    private IService target;

    @Override
    public void m1() {
        long starTime = System.nanoTime();
        this.target.m1();
        long endTime = System.nanoTime();
        System.out.println(this.target.getClass() + ".m1()方法耗时(纳秒):" +
                (endTime - starTime));
    }

    @Override
    public void m2() {
        long starTime = System.nanoTime();
        this.target.m2();
        long endTime = System.nanoTime();
        System.out.println(this.target.getClass() + ".m2()方法耗时(纳秒):" +
                (endTime - starTime));
    }

    @Override
    public void m3() {
        long starTime = System.nanoTime();
        this.target.m3();
        long endTime = System.nanoTime();
        System.out.println(this.target.getClass() + ".m3()方法耗时(纳秒):" +
                (endTime - starTime));
    }
}
