package cn.myth.guava.collection;

import com.google.common.base.Joiner;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

public class BiMapTest {

    /**
     *
     *  键–值实现	    值–键实现	    对应的BiMap实现
     *  HashMap	        HashMap	        HashBiMap
     *  ImmutableMap	ImmutableMap	ImmutableBiMap
     *  EnumMap	        EnumMap	        EnumBiMap
     *  EnumMap	        HashMap	        EnumHashBiMap
     */

    @Test
    public void bi_map() {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.putAll(ImmutableMap.of("a","1","b","2","c","3","d","4","e","5"));
        System.out.println(biMap);//{a=1, b=2, c=3, d=4, e=5}

        System.out.println("所有的值：" + Joiner.on(",").join(biMap.values()));//所有的值：1,2,3,4,5
        System.out.println("转换后所有的值：" + Joiner.on(",").join(biMap.inverse().values()));//转换后所有的值：a,b,c,d,e

        String v = biMap.put("a","10");

        System.out.println("替换的值：" + v);// 替换的值：1
        System.out.println("所有的值：" + Joiner.on(",").join(biMap.values()));//所有的值：10,2,3,4,5

        //试图将一个key映射到已经存在的值上，会抛异常
        //biMap.put("B","2");//java.lang.IllegalArgumentException: value already present: 2

        //强值将一个key映射到已经存在的值上，会将原来的key覆盖掉
        biMap.forcePut("B","2");
        System.out.println(biMap);//{a=10, c=3, d=4, e=5, B=2}
    }
}
