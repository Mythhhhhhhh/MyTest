package cn.myth.guava.collection;

import com.google.common.base.Joiner;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MultiMapTest {

    /**
     *  实现	                    键行为类似	    值行为类似
     *  ArrayListMultimap	    HashMap	        ArrayList
     *  HashMultimap	        HashMap	        HashSet
     *  LinkedListMultimap	    LinkedHashMap	LinkedList
     *  LinkedHashMultimap	    LinkedHashMap	LinkedHashMap
     *  TreeMultimap	        TreeMap	        TreeSet
     *  ImmutableListMultimap	ImmutableMap	ImmutableList
     *  ImmutableSetMultimap	ImmutableMap	ImmutableSet
     *
     *  除了两个不可变形式的实现，其他所有实现都支持null键和null值
     */

    @Test
    public void hash_multi_map() {
        Multimap<String,String> multimap = HashMultimap.create();
        multimap.putAll("lower", Lists.newArrayList("a","b","c","d"));
        multimap.putAll("upper", Lists.newArrayList("A","B","C","D"));
        System.out.println(multimap);//{lower=[a, b, c, d], upper=[A, B, C, D]}

        Map<String, Collection<String>> asMap = multimap.asMap();
        System.out.println("asMap视图：" + Joiner.on(";").withKeyValueSeparator("=").join(asMap));//asMap视图：lower=[a, b, c, d];upper=[A, B, C, D]

        Multiset<String> multisetKey = multimap.keys();
        System.out.println("所有的key:" + Joiner.on(",").join(multisetKey.iterator()));//所有的key:lower,lower,lower,lower,upper,upper,upper,upper

        Set<String> keySet = multimap.keySet();
        System.out.println("不重复的key：" + Joiner.on(",").join(keySet));//不重复的key：lower,upper

        System.out.println("lower:" + Joiner.on(",").join(multimap.get("lower")));//lower:a,b,c,d

        multimap.put("lower","e");
        System.out.println("添加后的lower:" + Joiner.on(",").join(multimap.get("lower")));//添加后的lower:a,b,c,d,e

        System.out.println("upper:" + Joiner.on(",").join(multimap.get("upper")));
        multimap.remove("upper","D");
        System.out.println("移除元素后的upper：" + Joiner.on(",").join(multimap.get("upper")));//移除元素后的upper：A,B,C

        System.out.println("是否包含lower-b:" + multimap.containsEntry("lower","b"));//是否包含lower-b:true
        System.out.println("是否包含lower-b:" + multimap.containsEntry("lower","f"));//是否包含lower-b:false

        System.out.println("是否包含key(upper):" + multimap.containsKey("upper"));//是否包含key(upper):true
        System.out.println("是否包含value(c):" + multimap.containsValue("c"));//是否包含value(c):true

        Collection<Map.Entry<String,String>> collection = multimap.entries();
        System.out.println("MultiMap详情：" + Joiner.on(";").withKeyValueSeparator("=").join(collection));//MultiMap详情：lower=a;lower=b;lower=c;lower=d;lower=e;upper=A;upper=B;upper=C

        Collection<String> values = multimap.values();
        System.out.println("MultiMap所有的value：" + Joiner.on(",").join(values));//MultiMap所有的value：a,b,c,d,e,A,B,C
    }

}
