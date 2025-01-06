package cn.myth.apache_common;

import org.apache.commons.collections4.SetUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SetUtilsTest {

    @Test
    public void difference() {
        //difference：找到两个set之间的不同元素
        //返回的是第一个set里有的，但是第二个set里没有的元素们
        Set<String> set1 = new HashSet<String>(){{
            add("a");
            add("b");
            add("c");
        }};
        Set<String> set2 = new HashSet<String>(){{
            add("c");
            add("d");
            add("e");
        }};

        SetUtils.SetView<String> difference = SetUtils.difference(set1, set2);
        System.out.println(difference); //[a,b]

        System.out.println(SetUtils.difference(set2, set1));//[d, e]

        Set<String> strings = difference.toSet();
        System.out.println(strings); //[a,b]
    }

    @Test
    public void disjunction() {
        //加强版
        //会返回第一个set和第二个有差异的所有元素们
        Set<String> set1 = new HashSet<String>(){{
            add("a");
            add("b");
            add("c");
        }};
        Set<String> set2 = new HashSet<String>(){{
            add("c");
            add("d");
            add("e");
        }};

        SetUtils.SetView<String> difference = SetUtils.disjunction(set1, set2);
        System.out.println(difference); //[a, b, d, e]

        Set<String> strings = difference.toSet();
        System.out.println(strings); //[a, b, d, e]
    }

    @Test
    public void union() {
        Set<String> set1 = new HashSet<String>(){{
            add("a");
            add("b");
            add("c");
        }};
        Set<String> set2 = new HashSet<String>(){{
            add("c");
            add("d");
            add("e");
        }};

        SetUtils.SetView<String> union = SetUtils.union(set1, set2);
        System.out.println(union); //[a, b, c, d, e]
    }

    @Test
    public void intersection() {
        Set<String> set1 = new HashSet<String>(){{
            add("a");
            add("b");
            add("c");
        }};
        Set<String> set2 = new HashSet<String>(){{
            add("c");
            add("d");
            add("e");
        }};

        SetUtils.SetView<String> union = SetUtils.intersection(set1, set2);
        System.out.println(union); //[c]
    }

    @Test
    public void is_equal_set() {
        // 判断两个set里面的元素是否都一样（长度一样、元素一样）
    }
}
