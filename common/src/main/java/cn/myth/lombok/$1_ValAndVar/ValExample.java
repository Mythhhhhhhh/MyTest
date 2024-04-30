package cn.myth.lombok.$1_ValAndVar;

import lombok.val;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class ValExample {

    /**
     *  val是在lombok 0.10中引入的。
     *
     *  您可以使用val作为局部变量声明的类型，而不是实际编写该类型。
     *  执行此操作时，将从初始值设定项表达式推断类型。局部变量也将成为最终变量。
     *  此功能仅适用于局部变量和foreach循环，而不适用于字段。初始值设定项表达式是必需的。
     *
     *  val实际上是一种排序的“类型”，在lombok包中作为一个真正的类存在。
     *  必须导入它才能使val工作（或使用lombok.val作为类型）。
     *  局部变量声明中存在此类型会触发添加final关键字以及复制初始化表达式的类型，从而覆盖“伪”val类型。
     */

    @Test
    public void example1() {
        val example = new ArrayList<String>();
        example.add("Myth");
        val foo = example.get(0);
        System.out.println(foo);
    }

    @Test
    public void example2() {
        val map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        map.forEach((key, value) -> System.out.println(key + "->" + value));
    }

    @Test
    public void example3() {
        val str = "Hello World";
        // str = "Myth"; // 提示final，不可修改
    }


}
