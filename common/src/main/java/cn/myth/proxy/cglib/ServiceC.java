package cn.myth.proxy.cglib;

public class ServiceC {

    public void m1() {
        System.out.println("ServiceC -> 我是m1方法");
        this.m2();
    }

    public void m2() {
        System.out.println("ServiceC -> 我是m2方法");
    }

}
