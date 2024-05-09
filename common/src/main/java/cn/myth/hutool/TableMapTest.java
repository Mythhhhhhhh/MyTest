package cn.myth.hutool;

import cn.hutool.core.lang.Console;
import cn.hutool.core.map.TableMap;
import org.junit.Test;


/**
 * 可重复键值Map
 */
public class TableMapTest {

    @Test
    public void test() {
        TableMap<String, Integer> tableMap = new TableMap<>();
        tableMap.put("aaa", 111);
        tableMap.put("bbb", 222);

        Console.log(tableMap.get("aaa"));// 111
        Console.log(tableMap.get("bbb"));// 222

        Console.log(tableMap.getKey(111));// aaa
        Console.log(tableMap.getKey(222));// bbb

        Console.log(tableMap.getValues("aaa"));// [111]
        Console.log(tableMap.getKeys(111));// [aaa]

    }
}
