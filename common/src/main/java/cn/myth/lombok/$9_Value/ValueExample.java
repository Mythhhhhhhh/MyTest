package cn.myth.lombok.$9_Value;

import lombok.Value;

@Value
public class ValueExample {

    /**
     *  用@Value可以创建一些“只读”性质的类型
     *  -> final class ValueExample 类会被final修饰
     *     private final Long id; 非static属性会被private final修饰
     *     private final String name;
     *
     *  @Value = @ToString + @EqualsAndHashCode + @AllArgsConstructor + @Getter + @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
     *
     *  通常，在进行Web编程时，我们可以利用@Value来创建DTO，因为这些DTO类用于传递数据，他们的属性在初始化后就不应该进行修改
     */


    Long id;

    String name;

}
