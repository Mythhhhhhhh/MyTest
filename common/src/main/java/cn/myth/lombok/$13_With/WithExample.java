package cn.myth.lombok.$13_With;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.With;

//@With
public class WithExample {

    /**
     *  为只读类提供修改属性的方法
     */

    @With(AccessLevel.PROTECTED)
    @NonNull
    private final String name;

    @With
    private final int age;

    public WithExample(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     *    protected WithExample withName(@NonNull String name) {
     *         if (name == null) {
     *             throw new NullPointerException("name is marked non-null but is null");
     *         } else {
     *             return this.name == name ? this : new WithExample(name, this.age);
     *         }
     *     }
     *
     *     public WithExample withAge(int age) {
     *         return this.age == age ? this : new WithExample(this.name, age);
     *     }
     *
     *  需要注意的是，Lombok生成的withXXX方法处理逻辑用的是==(不是equals)比较属性值，如果与原始属性一样，就返回原始对象(this),
     *  否则创建新对象
     *
     *  withXXX方法是构造器创建新对象，因此@With标记的类必须有一个包含所有属性的构造器(可以用@AllArgsConstructor创建)
     */


}
