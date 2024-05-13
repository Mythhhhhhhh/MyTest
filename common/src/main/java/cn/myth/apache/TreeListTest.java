package cn.myth.apache;

import org.apache.commons.collections4.list.TreeList;
import org.junit.Test;

import java.util.List;

public class TreeListTest {

    @Test
    public void tree_list() {
        // TreeList实现了优化的快速插入和删除任何索引的列表。
        // 这个列表内部实现利用树结构,确保所有的插入和删除都是O(log n)。
        List<String> list = new TreeList<>();
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");
        System.out.println(list); //[a, b, b, c]
    }
}
