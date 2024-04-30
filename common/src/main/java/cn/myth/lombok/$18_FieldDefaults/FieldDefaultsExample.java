package cn.myth.lombok.$18_FieldDefaults;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.experimental.PackagePrivate;

@FieldDefaults(makeFinal=true, level= AccessLevel.PRIVATE)
@AllArgsConstructor
public class FieldDefaultsExample {
    // @FieldDefaults注解可以为被注解的类或枚举中的每个字段添加访问修饰符（public, private, 或 protected
    // 它还可以为注释的类或枚举中的每个字段添加final。
    // 使用NonFinal可取消final。

    public final int a;
    int b;
    @NonFinal int c;
    @PackagePrivate int d;

    /**
     *  public final int a;//明确定义的，不受影响
     *  private final int b;//未明确定义的，使用注解的private final
     *  private int c;//指定了NonFinal则只保留private
     *  final int d;//执行了PackagePrivate，表示使用包私有，即default可见修饰符，只保留final
     */


}
