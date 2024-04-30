package cn.myth.lombok.$19_Delegate;

import lombok.experimental.Delegate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DelegateExample {

    // @Delegate可以让你的类使用其他类的方法，而不需要自己写代码
    //  在Java中，我们通常会创建一些包含大量方法的类。
    //  这些方法可能需要调用其他类中的方法，或者可能需要将一些常见的逻辑委托给其他类。
    //  在这种情况下，我们可以使用lombok的@Delegate注解来将方法的实现委托给其他类。

    private interface SimpleCollection {
        boolean add(String item);
        //boolean remove(Object item);
    }

    @Delegate(types = SimpleCollection.class)
    private final Collection<String> collection = new ArrayList<>();

    @Delegate
    private final Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        DelegateExample example= new DelegateExample();
        example.add("item1");//实际上加到collection中去了
        example.put("key1", "value1");
    }



}
