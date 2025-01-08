package cn.myth.design_pattern.creational_pattern.builder;

import org.junit.Test;

public class ApiTest {

    /*
     * 建造者模式主要解决的问题是在软件系统中，有时候面临着"一个复杂对象"的创建工作，其通常由各个部分的子对象用一定的过程构成；
     * 由于需求的变化，这个复杂对象的各个部分经常面临着重大的变化，但是将它们组合在一起的过程却相对稳定。
     *
     * 当：一些基本物料不会变，而其组合经常变化的时候，就可以选择这样的设计模式来构建代码
     *
     * 此设计模式满足了单一职责原则以及可复用的技术、建造者独立、易扩展、便于控制细节风险。
     * 但同时当出现特别多的物料以及很多的组合后，类的不断扩展也会造成难以维护的问题。
     * 但这种设计结构模型可以把重复的内容抽象到数据库中，按照需要配置。这样就可以减少代码中大量的重复
     */

    @Test
    public void test_Builder() {
        Builder builder = new Builder();

        // 豪华欧式
        System.out.println(builder.levelOne(132.52D).getDetail());

        // 轻奢田园
        System.out.println(builder.levelTwo(98.25D).getDetail());

        // 现代简约
        System.out.println(builder.levelThree(85.43D).getDetail());
    }

}
