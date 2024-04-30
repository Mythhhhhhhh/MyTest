package cn.myth.lombok.$21_UtilityClass;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UtilityClassExample {

    // 针对工具类的注解
    //通过用@UtilityClass标记你的类，lombok会自动生成一个抛出异常的私有构造函数，将你添加的任何显式构造函数标记为错误，并将该类标记为final。
    //实用工具类的所有成员都被自动标记为静态。甚至是字段和内部类。

    private final int CONSTANT = 5;

    public int addSomething(int in) {
        return in + CONSTANT;
    }

}
/**
 * public final class UtilityClassExample {
 *
 *
 *   private static final int CONSTANT = 5;
 *
 *   private UtilityClassExample() {
 *
 *
 *     throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
 *   }
 *
 *   public static int addSomething(int in) {
 *
 *
 *     return in + CONSTANT;
 *   }
 * }
 */