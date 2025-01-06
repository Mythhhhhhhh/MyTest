package cn.myth.apache_common;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MapUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MapUtilsTest {

    @Test
    public void map_utils() {
        Map<String, String> map = new HashMap<>();

        System.out.println(MapUtils.emptyIfNull(map));

        IterableMap<String, String> itMap = MapUtils.fixedSizeMap(map);// 固定长度
    }

    @Test
    public void invert_map() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        //反转  对调key和value
        Map<String, String> invertMap = MapUtils.invertMap(map);
        System.out.println(map); //{key1=value1, key2=value2, key3=value3}
        System.out.println(invertMap); //{value2=key2, value1=key1, value3=key3}
    }

    @Test
    public void iterable_map() {
        // 构建一个iterableMap，然后方便遍历、删除等等
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        IterableMap<String, String> iterableMap = MapUtils.iterableMap(map);
        MapIterator<String, String> it = iterableMap.mapIterator();
        while (it.hasNext()){
            it.next();
            String key = it.getKey();
            if(key.equals("key2")){
                it.remove();
            }
        }
        System.out.println(iterableMap); //{key1=value1, key3=value3}
        //我们发现这样对itMap进行删除  原来的Map也会达到同样的效果
        System.out.println(map); // {key1=value1, key3=value3}
    }

    @Test
    public void populate_map() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");

        //序列化 根据提供的values，按照后面规则把key都生成出来然后直接放进去
        MapUtils.populateMap(map, Arrays.asList("a", "b", "c"), e -> "key-" + e);
        System.out.println(map); //{key1=value1, key-a=a, key-c=c, key-b=b}
        //可以在上面的理论上 对value进行进一步操作  不能采用map.values() 否则由于并发修改异常
        // MapUtils.populateMap(map, map.values(), e -> e, e -> "value-" + e); //java.util.ConcurrentModificationException
        MapUtils.populateMap(map, Arrays.asList("a", "b", "c"), e -> e, e -> "value-" + e); //java.util.ConcurrentModificationException

        System.out.println(map); //{key1=value1, key-a=a, a=value-a, b=value-b, c=value-c, key-c=c, key-b=b}
    }

    @Test
    public void to_properties() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value2");

        Properties properties = MapUtils.toProperties(map);
        System.out.println(properties); //{key3=value2, key2=value2, key1=value1}
    }
}
