package cn.myth.apache_common;

import org.apache.commons.collections4.list.FixedSizeList;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.junit.Test;

import java.util.Arrays;

/**
 * 有限制的集合
 * 继承自Collection，提供了一些列的有用的实现
 */
public class BoundedCollection {

    @Test
    public void fixed_size() {
        // FixedSizeList：固定长度大小的List
        FixedSizeList<String> list = FixedSizeList.fixedSizeList(Arrays.asList("1", "2", "3"));

        System.out.println(list);//[1, 2, 3]
        System.out.println(list.size());//3

        //list.remove("1");//java.lang.UnsupportedOperationException: List is fixed size
        //list.add("1");//java.lang.UnsupportedOperationException: List is fixed size

        // 虽然不能增加和删除 但可以改
        list.set(2, "Myth");
        System.out.println(list);//[1, 2, Myth]
        System.out.println(list.get(2));

        //BoundedCollection提供的两个方法
        list.isFull(); //如果是FixedSizeList 永远返回true 因为大小是固定的
        list.maxSize(); //值同size()方法
    }

    @Test
    public void unmodifiable_bounded_collection() {
        // UnmodifiableBoundedCollection 不能修改的List
    }

    @Test
    public void circular_fifo_queue() {
        // CircularFifoQueue 环形的先进先出队列
        CircularFifoQueue<String> queue = new CircularFifoQueue<>(3);

        // 这个size和maxSize就有差异化了
        System.out.println(queue.size());//0
        System.out.println(queue.maxSize());//3

        queue.add("1");
        queue.add("2");
        queue.add("3");

        // 虽然长度是3 但是因为循环的特性 再添加一个并不会报错
        queue.add("4");
        System.out.println(queue);//[2, 3, 4]

        // 继续添加 就会把前面的继续挤出来 满员了以后，符合先进先出的原则
        queue.add("5");
        queue.add("6");
        System.out.println(queue);//[4, 5, 6]
    }


}
