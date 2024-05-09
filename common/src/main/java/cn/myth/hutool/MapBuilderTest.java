package cn.myth.hutool;

import cn.hutool.core.lang.Console;
import cn.hutool.core.map.BiMap;
import cn.hutool.core.map.MapBuilder;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapBuilderTest {

    @Test
    public void test() {
        Map<String, Object> map = MapBuilder
                .create(new HashMap<String, Object>())
                .put("name", "AAA")
                .put("age", 45)
                .map();
        Console.log(map);

        Map<String, String> map2 = MapBuilder
                .create(new BiMap<>(new HashMap<String, String>()))
                .put("name", "v1")
                .put("age", "v2")
                .put("sex", "v3")
                .map();
        Console.log(map2);
    }
}
