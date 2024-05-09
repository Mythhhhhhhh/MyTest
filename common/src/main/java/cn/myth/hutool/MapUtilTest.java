package cn.myth.hutool;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.map.MapUtil;
import org.junit.Test;

import java.util.*;

public class MapUtilTest {

    @Test
    public void of() {
        Map<Object, Object> colorMap = MapUtil.of(new String[][] {
                {"RED", "#FF0000"},
                {"GREEN", "#00FF00"},
                {"BLUE", "#0000FF"}
        });
        Console.log(colorMap);

        Map<String, Integer> pairmap = MapUtil.of(
                new Pair[]{
                        Pair.of("key1", 1),
                        Pair.of("key2", 2)
                });
        Console.log(pairmap);

        Map<String, String> build = MapUtil.builder(new HashMap<String, String>())
                .put("k1", "v1")
                .put("k2", "v2")
                .put("k3", "v3")
                .build();
        Console.log(build);

    }

    /**
     * 行列互转
     */
    @Test
    public void map_list() {
        Map<Object, Object> map1 = MapUtil.of(new String[][] {
                {"a", "1"}, {"b", "1"}, {"c", "1"}
        });
        Map<Object, Object> map2 = MapUtil.of(new String[][] {
                {"a", "2"}, {"b", "2"}
        });
        Map<Object, Object> map3 = MapUtil.of(new String[][] {
                {"a", "3"}, {"b", "3"}
        });
        Map<Object, Object> map4 = MapUtil.of("a", "4");
        /* ---------------- */
        ArrayList<Map<Object, Object>> list = ListUtil.toList(map1, map2, map3, map4);
        Console.log(list);
        // toListMap 行转列，合并相同的键，值合并为列表，将Map列表中相同key的值组成列表做为Map的value
        Map<Object, List<Object>> listMap = MapUtil.toListMap(list);
        Console.log("ListMap:\n{}", listMap);
        // toMapList 列转行。将Map中值列表分别按照其位置与key组成新的map
        List<Map<Object, Object>> mapList = MapUtil.toMapList(listMap);
        Console.log("MapList:\n{}", mapList);
    }

    /**
     * inverse或者reverse方法，Map的键和值互换
     */
    @Test
    public void inverse_reverse() {
        Map<Object, Object> map = MapUtil.of(new String[][] {
                {"a", "1"}, {"b", "2"}, {"c", "3"}
        });
        Map<Object, Object> inverse = MapUtil.inverse(map);
        Console.log(inverse);
        Map<Object, Object> reverse = MapUtil.reverse(inverse);
        Console.log(reverse);
    }

    /**
     * sort(Map<K, V> map)： 排序已有Map，Key有序的Map，使用默认Key排序方式（字母顺序）
     * sort(Map<K, V> map, Comparator<? super K> comparator)：排序已有Map，Key有序的Map
     * sortByValue(Map<K, V> map, boolean isDesc)：按照值排序，可选是否倒序
     */
    @Test
    public void sort() {
        Map<String, String> map = MapUtil.builder(new HashMap<String, String>())
                .put("b", "2")
                .put("a", "1")
                .put("c", "3")
                .build();

        Map<String, String> map1 = MapUtil.sort(map);
        Console.log(map1);
        Map<String, String> map2 = MapUtil.sortByValue(map, false);
        Console.log(map2);
        Map<String, String> map3 = MapUtil.sortByValue(map, true);
        Console.log(map3);
    }

    /**
     * 将Map按照给定的分隔符转换为字符串，一般用于签名
     * join: 将map转成字符串
     * sortJoin: 根据参数排序后拼接为字符串，常用于签名
     * joinIgnoreNull: 将map转成字符串，忽略null的键和值
     */
    @Test
    public void join() {
        Map<String, String> map = MapUtil.builder(new LinkedHashMap<String, String>())
                .put("key1", "value1")
                .put("key3", "value3")
                .put("key2", "value2")
                .build();
        Console.log(map); // {key1=value1, key3=value3, key2=value2}

        String join1 = MapUtil.sortJoin(map, ",", "-", false);
        Console.log(join1);// key1-value1,key2-value2,key3-value3

        String join2 = MapUtil.sortJoin(map, ",", "-", false, "123456");
        Console.log(join2); //key1-value1,key2-value2,key3-value3123456

        String join3 = MapUtil.join(map, ",", "-", true);
        Console.log(join3); // key1-value1,key3-value3,key2-value2
    }

    /**
     * filter(Map<K, V> map, K... keys)： 过滤Map保留指定键值对，如果键不存在跳过
     * filter(Map<K, V> map, Filter<Entry<K, V>> filter)：自定义过滤行为
     * map(Map<K, V> map, BiFunction<K, V, R> biFunction)：通过biFunction自定义一个规则，此规则将原Map中的元素转换成新的元素，生成新的Map返回
     */
    @Test
    public void filter() {
        Map<String, Integer> map = MapUtil.builder(new LinkedHashMap<String, Integer>())
                .put("key1", 1)
                .put("key3", 3)
                .put("key2", 2)
                .build();
        Map<String, Integer> filter1 = MapUtil.filter(map, "key1", "key2");
        Console.log(filter1);

        Map<String, Integer> filter2 = MapUtil.filter(map,
                t -> t.getValue() % 2 == 0
        );
        Console.log(filter2);
    }

    @Test
    public void get() {
        Map<String, Object> map = MapUtil.builder(new LinkedHashMap<String, Object>())
                .put("key1", 1)
                .put("key3", "3")
                .put("key2", DateTime.now())
                .build();
        int v1 = MapUtil.getInt(map, "key1");
        Date v2 = MapUtil.getDate(map, "key2");
        String v3 = MapUtil.getStr(map, "key3");
        Console.log("{},{},{}", v1, v2, v3);
    }
}
