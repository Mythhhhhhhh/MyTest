package cn.myth.lombok.$7_Constructor;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

//@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor(staticName = "of")
@ToString
public class ConstructorExample {

    /**
     *  @NoArgsConstructor
     *  生成一个空的构造器 ，如果有final字段，将产生一个编译器错误，除非使用 @NoArgsConstructor(force = true)，那么所有字段将被初始化为 0/false/null
     *
     *  @RequiredArgsConstructor
     *  为“需要的属性”(指用final或@NonNull修饰且没有初始化的属性)生成一个用于初始化的构造器
     *
     *  @AllArgsConstructor
     *  会为所有属性生成一个构造器
     *
     *  staticName
     *  上边的构造器都提供另外一种形式--将构造器本身定义为private，并提供一个static方法进行调用-静态工厂方法
     *   --> @AllArgsConstructor(staticName = "of")
     */

    private Long Id;

    private String name;

    public static void main(String[] args) {
        ConstructorExample test = ConstructorExample.of(1L, "test");
        System.out.println(test);
    }

}
