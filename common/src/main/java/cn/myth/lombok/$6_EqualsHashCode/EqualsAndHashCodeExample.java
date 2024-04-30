package cn.myth.lombok.$6_EqualsHashCode;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class EqualsAndHashCodeExample {

    /**
     *  默认情况下生成的equals和hashCode会使用所有的非 static 属性，换言之，调用equals方法进行比较时，所有属性都相等才能返回true
     *  有时候我们仅希望比较某些字段(比如主键）@EqualsAndHashCode(onlyExplicitlyIncluded = true) @EqualsAndHashCode.Include; 排除某些字段 @EqualsAndHashCode.Exclude
     *  如果要将@EqualsAndHashCode应用于子类，通常需要考虑父类的equals和hashCode方法 -> @EqualsAndHashCode(callSuper = true)
     */

    private Long id;

    private String name;

}
