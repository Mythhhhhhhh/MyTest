package cn.myth.apache;

import org.apache.commons.collections4.MultiMapUtils;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * 多值Map
 * 一个key可对应多个值，内部的数据结构逻辑交给它去维护。
 * 我们平时使用的Map<String,List<Long>>这种数据结构，就可以被这种代替，使用起来非常方便
 */
public class MultiValuedMapTest {

    @Test
    public void array_list_valued_hash_map() {
        // 见名之意，values采用ArrayList来存储
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        map.put("key1", "v1");
        System.out.println(map); //{key1=[v1]}
        map.put("key1", "v11");
        System.out.println(map); //{key1=[v1, v11]}

        map.put("key2", "v2");
        Collection<String> values = map.values();
        System.out.println(values);//[v1, v11, v2]

        // 移除values
        map.removeMapping("key1", "v11");
        // 移除key
        map.remove("key2");
        System.out.println(map); //{key1=[v1]}

        // 一次性放多个value
        map.putAll("key2", Arrays.asList("v2", "v22", "v222"));
        System.out.println(map); //{key1=[v1], key2=[v2, v22, v222]}

        //get方法  返回List
        Collection<String> collection = map.get("key2");
        MultiSet<String> keys = map.keys();
        Set<String> strings = map.keySet();
        System.out.println(keys); //[key1:1, key2:3] //后面的数字表示对应的value的数量
        System.out.println(strings); //[key1, key2]
        System.out.println(map.size()); //4 注意此处的size,是所有value的size 不是key的
        System.out.println(collection); //[v2, v22, v222]
    }

    @Test
    public void hash_set_valued_hash_map() {
        // 基本用法同上，但是很显然values用set去存储。那就无序，但是去重了
        MultiValuedMap<String, String> map = new HashSetValuedHashMap<>();
    }

    // MultiMapUtils提供一些基础工具方法：emptyMultiValuedMap、unmodifiableMultiValuedMap、newListValuedHashMap、getValuesAsSet、getValuesAsList等等
}
