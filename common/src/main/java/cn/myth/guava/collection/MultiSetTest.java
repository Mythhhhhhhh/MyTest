package cn.myth.guava.collection;

import com.google.common.base.Joiner;
import com.google.common.collect.*;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiSetTest {

    /**
     * Multiset可以多次添加相等元素，集合[set]概念的延伸，它的元素可以重复出现…与集合[set]相同而与元组[tuple]相反的是，
     * Multiset元素的顺序是无关紧要的：Multiset {a, a, b}和{a, b, a}是相等的
     * 可以用两种方式看待Multiset：
     *  没有元素顺序限制的ArrayList
     *  Map，键为元素，值为计数
     */
    @Test
    public void hash_multi_set() {
        Multiset<String> multiset = HashMultiset.create();
        List<String> list = Lists.newArrayList("a","b","c","d","a","c","d","a","d","a");
        multiset.addAll(list);

        System.out.println(multiset);//[a x 4, b, c x 2, d x 3]

        System.out.println("a的个数：" + multiset.count("a"));//a的个数：4
        System.out.println("multiset的个数：" + multiset.size());//multiset的个数：10

        Set<String> set = multiset.elementSet();
        System.out.println("不重复元素："+ Joiner.on(",").join(set));//不重复元素：a,b,c,d

        Iterator<String> iterator = multiset.iterator();
        System.out.println("multiset元素："+Joiner.on(",").join(iterator));//multiset元素：a,a,a,a,b,c,c,d,d,d

        Set<Multiset.Entry<String>> entrySet =  multiset.entrySet();
        Map<String,Integer> setMap = Maps.newHashMap();
        entrySet.forEach(e -> {
            setMap.put(e.getElement(),e.getCount());
        });
        System.out.println("元素详情："+Joiner.on(";").withKeyValueSeparator("=").join(setMap));//元素详情：a=4;b=1;c=2;d=3

        multiset.remove("a",2);
        System.out.println("删除a后，a的个数："+multiset.count("a"));//删除a后，a的个数：2

        System.out.println("是否包含List："+multiset.containsAll(Lists.newArrayList("a","c")));//是否包含List：true
        System.out.println("是否包含List："+multiset.containsAll(Lists.newArrayList("a","c","e")));//是否包含List：false
    }

    @Test
    public void sorted_multi_set() {
        // SortedMultiset是Multiset 接口的变种，它支持高效地获取指定范围的子集
        // SortedMultiset默认是排好序的，是按元素来进行排序的而不是元素的个数
        SortedMultiset<String> sortedMultiset = TreeMultiset.create();
        sortedMultiset.setCount("c",5);
        sortedMultiset.setCount("a",3);
        sortedMultiset.setCount("b",2);
        System.out.println(sortedMultiset);//[a x 3, b x 2, c x 5]
        //获取第一个元素
        System.out.println(sortedMultiset.firstEntry().getElement()); //a
        //获了最后一个元素
        System.out.println(sortedMultiset.lastEntry().getElement());//c
        //获取子集
        SortedMultiset<String> subMultiset = sortedMultiset.subMultiset("a", BoundType.OPEN,"b",BoundType.CLOSED);
        System.out.println(subMultiset);//[b x 2]
    }

}
