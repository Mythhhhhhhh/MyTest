package cn.myth.lombok.$5_ToString;

import lombok.ToString;

/**
 *  任何类定义都可以用@ToString注释，以使lombok生成ToString()方法的实现。默认情况下，它将按顺序打印您的类名以及每个字段，并用逗号分隔。
 */
@ToString
public class ToStringExample {

    private Long id;

    private String name;

    // 1.如果不要输出属性名 -> @ToString(includeFieldNames = false)
    // 2.如果不希望打印某些信息 -> @ToString(exclude = {"id"})
    //    或在不希望输出的属性上使用 @ToString.Exclude
    // 3.如果只希望输出某些属性 @ToString(includeFieldNames = false, onlyExplicitlyIncluded = true)
    //    希望输出的属性上使用 @ToString.Include
    // 4.@ToString(callSuper = true) 继承父类的属性输出
    // 5.修改输出的属性名称 -> @ToString.Include(name = "x")
    // 6.修改输出属性的顺序 -> @ToString.Include(rank = 0), rank越大输出越靠前
    // 7.@ToString(doNotUseGetters = true) 接访问该字段并获取其值
}
