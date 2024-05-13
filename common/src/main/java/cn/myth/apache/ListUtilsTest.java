package cn.myth.apache;

import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListUtilsTest {

    @Test
    public void intersection() {
        //intersection：取交集，生成一个新的List
        List<String> list1 = new ArrayList<String>(){{
            add("a");
            add("b");
            add("c");
        }};
        List<String> list2 = new ArrayList<String>(){{
            add("c");
            add("d");
            add("e");
        }};

        //取出交集 并且返回一个新的List
        List<String> intersection = ListUtils.intersection(list1, list2);

        System.out.println(intersection); //[c]

        //这个方法也能取出交集的效果 但是会直接改变list1里面的元素  list2不变
        list1.retainAll(list2);
        System.out.println(list1); // [c]
        System.out.println(list2); //[c, d, e]
    }

    @Test
    public void partition() {
        //partition：切割  把一个大的List切割成多个List
        List<String> list1 = new ArrayList<String>(){{
            add("a");
            add("b");
            add("c");

            add("a");
            add("b");
            add("c");

            add("a");
            add("b");
            add("c");
        }};
        List<List<String>> partition = ListUtils.partition(list1, 4);
        System.out.println(partition); //[[a, b, c, a], [b, c, a, b], [c]]
    }

    @Test
    public void subtract() {
        //subtract：相当于做减法，用第一个List除去第二个list里含有的元素 ，然后生成一个新的list
        List<String> list1 = new ArrayList<String>(){{
            add("a");
            add("b");
            add("c");
        }};
        List<String> list2 = new ArrayList<String>(){{
            add("c");
            add("d");
            add("e");
        }};

        //取出交集 并且返回一个新的List
        List<String> subtract = ListUtils.subtract(list1, list2);
        System.out.println(subtract); //[a,b]
    }

    @Test
    public void sum() {
        //sum：把两个List的元素相加起来    注意：相同的元素不会加两次  生成一个新的List
        List<String> list1 = new ArrayList<String>(){{
            add("a");
            add("b");
            add("c");
        }};
        List<String> list2 = new ArrayList<String>(){{
            add("c");
            add("c");
            add("c");
            add("d");
            add("e");
        }};

        //取出交集 并且返回一个新的List
        List<String> sumlist = ListUtils.sum(list1, list2);
        System.out.println(sumlist); //[a, b, c, c, c, d, e]
    }

    @Test
    public void union() {
        // 不带去重的功能。内部调用的addAll方法，成一个新的List
        List<String> list1 = new ArrayList<String>(){{
            add("a");
            add("b");
            add("c");
        }};
        List<String> list2 = new ArrayList<String>(){{
            add("c");
            add("c");
            add("c");
            add("d");
            add("e");
        }};

        List<String> sumlist = ListUtils.union(list1, list2);
        System.out.println(sumlist); //[a, b, c, c, c, c, d, e]
    }

}
