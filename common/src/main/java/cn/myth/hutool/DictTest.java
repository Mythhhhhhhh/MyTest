package cn.myth.hutool;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Date;

/**
 * Dict继承HashMap，其key为String类型，value为Object类型
 */
public class DictTest {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class DictDemo {
        private long id;
        private String name;
        private Date time;
    }

    @Test
    public void test1() {
        Dict dict = Dict.create()
                .set("key1", 1)
                .set("key2", 1000L)
                .set("key3", DateTime.now());
        Console.log(dict);
        Console.log(dict.getInt("key1"));
        Console.log(dict.getLong("key2"));
        Console.log(dict.getDate("key3"));
    }

    /**
     * 将PO对象转为Dict
     */
    @Test
    public void parseTest() {
        DictDemo demo = new DictDemo(1L, "parseTest", DateTime.now());
        Dict dict = Dict.parse(demo);
        Console.log(dict);
    }

    /**
     * 过滤Map保留指定键值对，如果键不存在跳过
     */
    @Test
    public void filterTest() {
        DictDemo demo = new DictDemo(1L, "parseTest", DateTime.now());
        Dict dict = Dict.parse(demo);
        Dict filter = dict.filter("id", "time");
        Console.log(filter);
    }



}
