package cn.myth.lombok.$10_Builder;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Builder
@Getter
@ToString
public class BuilderExample {

    /**
     *  利用@Builder可以为类创建一个“创建器”，利用这个创建器可以创建对象
     *
     *  @Builder会为类创建 一个包含所有非静态属性的构造器 和 一个静态内部类xxxBuilder，这个内部类包含所有外部类的非静态属性
     *  并且可以利用这个内部类的一系列方法来一步步生成外部类的对象
     *
     *  @Builder.Default 可以给属性设置默认值
     *
     *  @Singular 用于集合类型
     */

    private Long id;

    private String name;

    @Builder.Default
    private Integer age = 18;

    @Singular
    private List<String> descriptions;

    public static void main(String[] args) {
        BuilderExample example = BuilderExample.builder()
                .id(1L)
                .name("xxx")
                .description("hello")
                .descriptions(Arrays.asList("item1", "item2", "item3"))
                .description("world")
                .build();
        System.out.println(example);
    }



}
