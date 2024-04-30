package cn.myth.lombok.$2_NonNull;

import lombok.NonNull;
import org.junit.Test;

public class NonNullExample {

    /**
     *  @NonNull在lombok v0.11.10中被引入。
     *
     *  你可以在一个记录组件上使用@NonNull，或者一个方法或构造函数的参数。lombok将为你生成一个null检查语句。
     *
     *  Lombok始终将字段上通常命名为@NonNull的各种注释视为一个信号，以便在Lombok为您生成整个方法或构造函数时生成null检查，例如通过@Data。但是，对参数或记录组件使用lombok自己的@lombok.NonNull会导致在该方法的顶部插入null检查。
     *
     *  null检查看起来像if (param == null) throw new NullPointerException("param is marked @NonNull but is null"); 并将被插入到方法的最顶端。对于构造函数，null检查将被插入到任何明确的this()或super()调用之后。对于记录组件，null检查将被插入到 "紧凑构造函数 "中（完全没有参数列表的构造函数），如果你没有构造函数，它将被生成。如果你已经写出了长形式的记录构造函数（参数与你的组件完全匹配），那么什么也不会发生–你将不得不注释这个长形式构造函数的参数。
     *
     *  如果顶部已经有一个null检查，则不会产生额外的null检查。
     */

    private String name;


    public void setName(@NonNull String name) {
        System.out.println(name);
        this.name = name;
    }


    @Test
    public void example1() {
        setName("Myth");
        setName(null);
        // java.lang.NullPointerException: name is marked non-null but is null
    }

}
