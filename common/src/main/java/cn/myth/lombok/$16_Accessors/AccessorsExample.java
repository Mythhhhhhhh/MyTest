package cn.myth.lombok.$16_Accessors;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
@Getter
@Setter
@ToString
public class AccessorsExample {

    /**
     *  fluent属性：不写默认false，当该值为true时，对应字段的getter方法前面就没有get，setter方法前面就没有set
     *
     *  prefix属性：该属性是一个字符串数组，默认值为空，该数组有值的时候，表示忽略字段对应的前缀，生成对应的getter和setter方法
     *
     *  chain属性：通过chain属性，我们可以控制是否生成返回当前对象的访问器方法，以支持链式调用
     */

    private Long id;

    private String name;


    public static void main(String[] args) {
        AccessorsExample example = new AccessorsExample()
                .id(1L)
                .name("hello");

        System.out.println(example);
    }


}
