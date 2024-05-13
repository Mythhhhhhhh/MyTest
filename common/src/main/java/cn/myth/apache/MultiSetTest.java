package cn.myth.apache;

import org.apache.commons.collections4.multiset.HashMultiSet;
import org.junit.Test;

import java.util.Arrays;

public class MultiSetTest {

    @Test
    public void hash_multi_set() {
        // 底层实现原理为HashMap和MutableInteger
        // MultiSet 接口与 Bag 接口的功能有些类似：当你向 Set 中存入重复的数据时，它会记录下该数据出现次数，而非重复存储数据本身。
        // HashMultiSet 比 Bag 『高级』的地方在于，它可以对对象出现的次数作『加减法』：
        HashMultiSet<String> set = new HashMultiSet<>();

        set.add("1");
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("3");
        set.add("3");

        // Set是无序的，但是允许了重复元素的进入，并且记录了总数
        System.out.println(set);//[1:2, 2:1, 3:3]
        System.out.println(set.size());//6

        // 批量添加
        set.add("test", 5);
        System.out.println(set);//[1:2, 2:1, 3:3, test:5]

        // 移除方法
        System.out.println(set.getCount("1"));//2
        set.remove("1");
        // 此移除 一次性只会移除一个
        System.out.println(set.getCount("1"));//1
        // 一次性全部移除N个
        set.remove("3", set.getCount("3"));
        System.out.println(set.getCount("3"));//0 已经全部移除了

        // removeAll 把指定的key，全部移除
        set.removeAll(Arrays.asList("1", "2", "3"));
        System.out.println(set);//[test:5]
    }

    @Test
    public void predicated_multi_set() {
        // PredicatedMultiSet 使用较少
    }


}
