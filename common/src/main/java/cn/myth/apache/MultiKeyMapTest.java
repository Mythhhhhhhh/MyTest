package cn.myth.apache;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.junit.Test;

/**
 * 多键Map
 */
public class MultiKeyMapTest {

    @Test
    public void multi_key_map() {
        //  MultiKeyMap底层采用MultiKey作为普通Map的key，采用HashedMap存储
        MultiKeyMap<String, Integer> multiKeyMap = new MultiKeyMap<>();
        multiKeyMap.put("A", "a", 1);
        multiKeyMap.put("B", "b", 2);
        multiKeyMap.put("C", "c", 3);
        System.out.println(multiKeyMap); // {MultiKey[C, c]=3, MultiKey[B, b]=2, MultiKey[A, a]=1}
        System.out.println(multiKeyMap.get("A")); //null
        System.out.println(multiKeyMap.get("a")); //null
        System.out.println(multiKeyMap.get("A", "a")); //1
    }

    @Test
    public void multi_key() {
        // MultiKey功能很简单：装载多个key的一个对象
        MultiKey<String> multiKey = new MultiKey<>("A", "a");
        System.out.println(multiKey); //MultiKey[A, a]

        MultiKey<String> key1 = new MultiKey<>("a", "b", "c");
        MultiKey<String> key2 = new MultiKey<>("A", "B", "C", "D");
        MultiKeyMap<String, Integer> multiKeyMap = new MultiKeyMap<>();
        multiKeyMap.put(key1, 1);
        multiKeyMap.put(key2, 2);
        System.out.println(multiKeyMap); //{MultiKey[A, B, C, D]=2, MultiKey[a, b, c]=1}
        System.out.println(multiKeyMap.get(key1));//1

    }

}
