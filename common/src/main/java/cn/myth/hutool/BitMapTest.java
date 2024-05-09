package cn.myth.hutool;

import cn.hutool.bloomfilter.bitMap.BitMap;
import cn.hutool.core.lang.Console;
import cn.hutool.core.map.BiMap;
import cn.hutool.core.map.MapUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 双向查找Map
 * BiMap要求key和value都不能重复（非强制要求），
 * 如果key重复了，后加入的键值对会覆盖之前的键值对，
 * 如果value重复了，则会按照不确定的顺序覆盖key，这完全取决于map实现。比如HashMap无序（按照hash顺序），
 * 则谁覆盖谁和hash算法有关；如果是LinkedHashMap，则有序，是后加入的覆盖先加入的。
 */
public class BitMapTest {

    @Test
    public void test() {
        BiMap<String, Integer> biMap = new BiMap<>(new HashMap<>());
        biMap.put("a", 1);
        biMap.put("b", 2);
        biMap.put("c", 3);
        Console.log(biMap.get("a"));
        Console.log(biMap.get("b"));
        Console.log(biMap.get("c"));

        Console.log(biMap.getKey(1));
        Console.log(biMap.getKey(2));
        Console.log(biMap.getKey(3));
    }
}
