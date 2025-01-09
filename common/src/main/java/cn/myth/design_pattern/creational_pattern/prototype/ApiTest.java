package cn.myth.design_pattern.creational_pattern.prototype;

import org.junit.Test;

public class ApiTest {

    /*
     * 原型模式的使用频率确实不是很高。如果有一些特殊场景需要使用到，也可以按照此设计模式进行优化。
     * 另外原型设计模式的优点包括；便于通过克隆方式创建复杂对象、也可以避免重复做初始化操作、不需要与类中所属的其他类耦合等。
     * 但也有一些缺点如果对象中包括了循环引用的克隆，以及类中深度使用对象的克隆，都会使此模式变得异常麻烦。
     */


    @Test
    public void test_QuestionBank() throws CloneNotSupportedException {
        QuestionBankController questionBankController = new QuestionBankController();
        System.out.println(questionBankController.createPaper("花花", "1000001921032"));
        System.out.println(questionBankController.createPaper("豆豆", "1000001921051"));
        System.out.println(questionBankController.createPaper("大宝", "1000001921987"));
    }

}
