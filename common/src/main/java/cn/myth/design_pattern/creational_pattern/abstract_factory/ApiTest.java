package cn.myth.design_pattern.creational_pattern.abstract_factory;

import cn.myth.design_pattern.creational_pattern.abstract_factory.factory.JDKProxy;
import cn.myth.design_pattern.creational_pattern.abstract_factory.factory.impl.EGMCacheAdapter;
import cn.myth.design_pattern.creational_pattern.abstract_factory.factory.impl.IIRCacheAdapter;
import cn.myth.design_pattern.creational_pattern.abstract_factory.impl.CacheServiceImpl;
import org.junit.Test;

public class ApiTest {

    /* Redis单机模拟 */
    @Test
    public void test_CacheService_single() {
        CacheService cacheService = new CacheServiceImpl();
        cacheService.set("user_name_01", "test");
        String val01 = cacheService.get("user_name_01");
        System.out.println("测试结果：" + val01);
    }

    /*
     * 抽象工厂模式与工厂方法模式虽然主要意图都是为了解决，接口选择问题。但在实现上，抽象工厂是一个中心工厂，创建其他工厂的模式
     *
     * 抽象工厂模式，所要解决的问题就是在一个产品族，存在多个不同类型的产品(Redis集群、操作系统)情况下，接口选择的问题
     * 当你知道什么场景下何时可以被抽象工程优化代码，那么你的代码层级结构以及满足业务需求上，都可以得到很好的完成功能实现并提升扩展性和优雅度
     * 这个设计模式满足了；单一职责、开闭原则、解耦等优点，但如果说随着业务的不断拓展，可能会造成类实现上的复杂度。但也可以说算不上缺点，因为可以随着其他设计方式的引入和代理类以及自动生成加载的方式降低此项缺点。
     */

    @Test
    public void test_CacheService() throws Exception {
        CacheService proxy_EGM = JDKProxy.getProxy(CacheServiceImpl.class, new EGMCacheAdapter());
        proxy_EGM.set("user_name_01", "Myth");
        String val01 = proxy_EGM.get("user_name_01");
        System.out.println("测试结果：" + val01);

        CacheService proxy_IIR = JDKProxy.getProxy(CacheServiceImpl.class, new IIRCacheAdapter());
        proxy_IIR.set("user_name_01", "Myth01");
        String val02 = proxy_IIR.get("user_name_01");
        System.out.println("测试结果：" + val02);
    }

    @Test
    public void test_CacheService2() throws Exception {
        CacheService proxy_EGM = JDKProxy.getProxy2(CacheService.class, new EGMCacheAdapter());
        proxy_EGM.set("user_name_01", "Myth");
        String val01 = proxy_EGM.get("user_name_01");
        System.out.println("测试结果：" + val01);

        CacheService proxy_IIR = JDKProxy.getProxy2(CacheService.class, new IIRCacheAdapter());
        proxy_IIR.set("user_name_01", "Myth02");
        String val02 = proxy_IIR.get("user_name_01");
        System.out.println("测试结果：" + val02);
    }

    @Test
    public void test_CacheService_targetObject() throws Exception {
        CacheService proxy_EGM = JDKProxy.getProxyByTargetObject(new CacheServiceImpl(), CacheService.class, new EGMCacheAdapter());
        proxy_EGM.set("user_name_01", "Myth");
        String val01 = proxy_EGM.get("user_name_01");
        System.out.println("测试结果：" + val01);

        CacheService proxy_IIR = JDKProxy.getProxyByTargetObject(new CacheServiceImpl(), CacheService.class, new IIRCacheAdapter());
        proxy_IIR.set("user_name_01", "Myth_targetObject");
        String val02 = proxy_IIR.get("user_name_01");
        System.out.println("测试结果：" + val02);
    }
}
