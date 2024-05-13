package cn.myth.apache;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.BagUtils;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bag.TreeBag;
import org.junit.Test;

import java.util.Iterator;

/**
 * Bag继承自Collection接口，定义了一个集合，该集合会记录对象在集合中出现的次数。
 */
public class BagTest {

    @Test
    public void hash_bag() {
        Bag<String> hashBag = new HashBag<>();
        hashBag.add("k1");
        hashBag.add("k2");
        hashBag.add("k3", 3); //一次性放置多个元素
        System.out.println(hashBag);// [1:k1,1:k2,3:k3]

        // 获得包中元素迭代器 遍历
        Iterator<String> iterator = hashBag.iterator();
        System.out.println("包中元素为");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("包中元素个数为：" + hashBag.size());
        //下面两个特有的方法 使用起来较为方便
        System.out.println("包中entity1个数为：" + hashBag.getCount("k1"));
        System.out.println("包中entity2个数为：" + hashBag.getCount("k2"));
        System.out.println("包中entity3个数为：" + hashBag.getCount("k3"));
        System.out.println("去重后个数为：" + hashBag.uniqueSet().size());


        Bag<Object> objects = BagUtils.emptyBag();
        // 对应的BagUtils，能提供BagUtils.EMPTY_BAG、synchronizedBag、unmodifiableBag 等同步编程、只读的快捷方法
    }

    @Test
    public void tree_bag() {
        // TreeBag使用TreeMap作为数据存储，用法与HashBag类似，只是TreeBag会使用自然顺序对元素进行排序。
        Bag<String> treeBag = new TreeBag<>();
        treeBag.add("b");
        treeBag.add("c");
        treeBag.add("a", 3);
        System.out.println(treeBag); //[3:a,1:b,1:c]

        Iterator<String> iterator = treeBag.iterator();
        System.out.println("包中元素为");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
