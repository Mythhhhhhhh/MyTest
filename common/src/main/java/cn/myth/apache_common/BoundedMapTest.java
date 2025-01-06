package cn.myth.apache_common;

import org.apache.commons.collections4.map.FixedSizeMap;
import org.apache.commons.collections4.map.LRUMap;
import org.apache.commons.collections4.map.SingletonMap;
import org.junit.Test;

import java.util.HashMap;

public class BoundedMapTest {

    @Test
    public void fixed_size_map() {
        FixedSizeMap<String, String> m = FixedSizeMap.fixedSizeMap(new HashMap<String, String>() {{
            put("k1", "v1");
            put("k2", "v2");
            put("k3", "v3");
        }});

        System.out.println(m); //{k1=v1, k2=v2, k3=v3}
        System.out.println(m.size()); //3

        //不能再往里面添加数据了
        //m.put("aaa", "aaa");// java.lang.IllegalArgumentException: Cannot put new key/value pair - Map is fixed size

        //在没有改变长度的情况下 是可以修改的
        m.put("k1", "v11");
        System.out.println(m); //{k1=v11, k2=v2, k3=v3}
    }

    @Test
    public void fixed_size_sorted_map() {
        // FixedSizeSortedMap 区别：底层采用SortedMap
    }

    @Test
    public void LRU_map() {
        // LRUMap 底层是LRU算法
        // LRU算法的设计原则是：如果一个数据在最近一段时间没有被访问到，那么在将来它被访问的可能性也很小。
        // 也就是说，当限定的空间已存满数据时，应当把最久没有被访问到的数据淘汰。
        LRUMap<Object, Object> map = new LRUMap<>(3);

        System.out.println(map); //{}
        System.out.println(map.size()); //0
        System.out.println(map.maxSize()); //3
        System.out.println(map.isFull()); //false

        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");

        System.out.println(map); //{1=a, 2=b, 3=c}
        System.out.println(map.size()); //3
        System.out.println(map.maxSize()); //3
        System.out.println(map.isFull()); //true


        // 如果我们都没有get使用过 那就从后往前挤出来吧
        map.put("4", "d");
        map.put("5", "e");
        System.out.println(map); //{3=c, 4=d, 5=e}
        System.out.println(map.size()); //3
        System.out.println(map.maxSize()); //3
        System.out.println(map.isFull()); //true

        //我此处多次使用'3'这个key 就不会被挤出来
        map.get("3");
        map.get("3");

        map.put("6", "f");
        map.put("7", "g");
        System.out.println(map); //{3=c, 6=f, 7=g}
        System.out.println(map.size()); //3
        System.out.println(map.maxSize()); //3
        System.out.println(map.isFull()); //true
    }

    @Test
    public void singleton_map() {
        SingletonMap<String, String> map = new SingletonMap<>();

        System.out.println(map); //{null=null}
        //size已经是1了
        System.out.println(map.size()); //1
        System.out.println(map.maxSize()); //1

        //哪怕一个都没有 也不能设置值
        //map.put("one","one"); //Cannot put new key/value pair - Map is fixed size singleton

        //虽然不能再放key 但可以改值
        map.setValue("v1"); //{null=v1}
        System.out.println(map);

        //一般建议在构造的时候，就给key和value赋值  如下：
        map = new SingletonMap<>("k1","v1");
        System.out.println(map); //{v1=k2}
    }
}
