package cn.myth.lombok.$8_Data;

import lombok.Data;
import lombok.NonNull;

@Data(staticConstructor = "of")
public class DataExample<T> {

    /**
     *  @Data = @ToString + @EqualsAndHashCode + @Getter/@Setter + @RequiredArgsConstructor
     *
     *  如果我们需要为某个注解提供更详细的设置，可以在使用@Data注解的基础上使用对应的注解来设置
     *
     *  staticConstructor
     *  类似于@AllArgsConstructor等，@Data同样可以将构造器设置为私有的，同时提供一个static方法用于调用构造器 @Data(staticConstructor = "of")
     */

    private Long id;

    private String name;

    @NonNull
    private T someThing;

    public static void main(String[] args) {
        DataExample<String> example1 = DataExample.of("someThing");
        System.out.println(example1);
        DataExample<Integer> example2 = DataExample.of(2);
        System.out.println(example2);
    }

}
