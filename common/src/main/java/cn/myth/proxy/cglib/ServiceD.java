package cn.myth.proxy.cglib;

public class ServiceD {

    public String m1() {
        System.out.println("ServiceD -> 我是m1方法");
        return "ServiceD:m1";
    }

    public String m2() {
        System.out.println("ServiceD -> 我是m2方法");
        return "ServiceD:m1";
    }

}
