package cn.myth.lombok.$1_ValAndVar;

import lombok.var;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class VarExample {

    /**
     *  var是在lombok 1.16.12中作为实验性功能引入的。
     *
     *  var的工作原理与val完全相同，只是局部变量没有标记为final。
     *
     *  类型仍然完全是从强制初始值设定项表达式派生的，任何进一步的赋值，尽管现在是合法的（因为变量不再是最终的），都不会被用来确定合适的类型。
     *
     *  例如，var x = "Hello"; x = Color.RED; 不起作用；
     *  x的类型将被推断为java.lang.String，因此，x = Color.RED的赋值将失败。
     *  如果x的类型被推断为java.lang.Object，这段代码就会被编译，但这不是var的工作方式。
     */

    /**
     *  var和val的区别在于：val修饰的局部变量没有被标记为final。
     *
     *  var在lombok 1.16.20中被提升为主包；考虑到JEP 286建立的期望，而lombok对var的处理遵循了这些期望，我们决定提升var，尽管这个特性仍然有争议。
     *
     *  Lombok 1.18.22中的新功能：val被替换为final var。
     */

    @Test
    public void example1() {
        var example = new ArrayList<String>();
        example.add("Myth");
        var foo = example.get(0);
        System.out.println(foo);
    }

    @Test
    public void example2() {
        var map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        map.forEach((key, value) -> System.out.println(key + "->" + value));
    }

    @Test
    public void example3() {
        var str = "Myth";
        str = "123";
        System.out.println(str);
    }

}
