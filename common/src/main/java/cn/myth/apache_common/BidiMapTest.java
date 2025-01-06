package cn.myth.apache_common;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.collections4.bidimap.DualLinkedHashBidiMap;
import org.apache.commons.collections4.bidimap.DualTreeBidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.junit.Test;

/**
 * 双重Map
 * 使用双向映射，可以使用值查找键，并且可以使用键轻松查找值。（自然，它可以根据key移除，也可以根据value移除）
 * public interface BidiMap<K, V> extends IterableMap<K, V> {}
 * 也是个普通的Map。继承IterableMap增加了一种迭代方式。
 */
public class BidiMapTest {

    @Test
    public void dual_hash_bidi_map() {
        // 底层维护两个HashMap，一个正向，一个逆向来达到效果的。
        BidiMap<String, String> map = new DualHashBidiMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        //多出来的一种遍历方式
        MapIterator<String, String> it = map.mapIterator();
        while (it.hasNext()) {
            it.next(); //此句话必须调用  返回的是key，效果同getKey，但必须调用
            // String key = it.next();
            // String value = it.getValue();
            System.out.println(it.getKey() + "---" + it.getValue());
        }

        //根据value拿key
        System.out.println(map.get("key1")); //value1
        //这个方法是Map接口的
        System.out.println(map.getKey("value1")); //key1
        System.out.println(map.getOrDefault("k", "defaultValue")); //defaultValue
        //返回一个逆序的视图  注意是视图
        BidiMap<String, String> inverseMap = map.inverseBidiMap();

        //根据key删除
        inverseMap.remove("key1");
        //根据value删除
        inverseMap.removeValue("key2");

        System.out.println(map); //{key1=value1, key3=value3}
        System.out.println(inverseMap); //{value1=key1, value3=key3}
    }

    @Test
    public void dual_linked_hash_bidi_map() {
        // DualLinkedHashBidiMap
        // 底层采用两个LinkedHashMap存储，其余同上
        BidiMap<String, String> map = new DualLinkedHashBidiMap<>();
    }

    @Test
    public void dual_tree_bidi_map() {
        // DualTreeBidiMap
        // 底层采用两个TreeMap存储，其余同上
        // 它不要求key和value都是实现了比较器接口的，但是自己可以自定义比较器接口传进去
        BidiMap<String, String> map = new DualTreeBidiMap<>();
    }


    @Test
    public void tree_bidi_map() {
        //  TreeBidiMap采用是红黑树：Node。
        //  一个node就是put的一个键值对，这样子来实现双端的Map，底层的原理和上面的不一样。
        //  这样的好处：可以最大程度的节约存储空间，从而提高效率。
        //  使用起来基本同上
        //  此Map要求key和value必须必须必须都实现了比较器接口
        BidiMap<String, String> map = new TreeBidiMap<>();
    }




}
