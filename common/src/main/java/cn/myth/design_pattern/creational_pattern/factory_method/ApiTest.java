package cn.myth.design_pattern.creational_pattern.factory_method;

import cn.myth.design_pattern.creational_pattern.factory_method.story.ICommodity;
import org.junit.Test;

import java.util.HashMap;

public class ApiTest {

    /*
     * 工厂方法模式
     * 工厂模式又称工厂方法模式，是一种创建型设计模式，其在父类中提供一个创建对象的方法， 允许子类决定实例化对象的类型
     * 这种设计模式也是 Java 开发中最常见的一种模式，它的主要意图是定义一个创建对象的接口，让其子类自己决定实例化哪一个工厂类，工厂模式使其创建过程延迟到子类进行
     * 简单说就是为了提供代码结构的扩展性，屏蔽每一个功能类中的具体实现逻辑。让外部可以更加简单的只是知道调用即可
     * 优点：
     *  1.避免创建者与具体的产品逻辑耦合
     *  2.满足单一职责，每一个业务逻辑实现都在所属自己的类中完成
     *  3.满足开闭原则，无需更改使用调用方就可以在程序中引入新的产品类型
     * 问题：
     *  如果有非常多的奖品类型，那么实现的子类会极速扩张
     */


    @Test
    public void test_commodity() throws Exception {
        StoreFactory storeFactory = new StoreFactory();

        // 1. 优惠券
        ICommodity commodityService_1 = storeFactory.getCommodityService(1);
        commodityService_1.sendCommodity("10001", "EGM1023938910232121323432", "791098764902132", null);

        // 2. 实物商品
        ICommodity commodityService_2 = storeFactory.getCommodityService(2);
        commodityService_2.sendCommodity("10001","9820198721311","1023000020112221113",new HashMap<String, String>() {{
            put("consigneeUserName", "谢飞机");
            put("consigneeUserPhone", "15200292123");
            put("consigneeUserAddress", "吉林省.长春市.双阳区.XX街道.檀溪苑小区.#18-2109");
        }});

        // 3. 第三方兑换卡(爱奇艺)
        ICommodity commodityService_3 = storeFactory.getCommodityService(3);
        commodityService_3.sendCommodity("10001","AQY1xjkUodl8LO975GdfrYUio",null,null);
    }
}
