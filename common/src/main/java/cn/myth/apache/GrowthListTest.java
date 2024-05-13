package cn.myth.apache;

import org.apache.commons.collections4.list.GrowthList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GrowthListTest {

    @Test
    public void growth_list() {
        List<String> src = new ArrayList<>();
        src.add("11");
        src.add("22");
        src = GrowthList.growthList(src);
        System.out.println(src);//[11, 22]

        // 经过GrowthList.growthList一修饰后  这个list能够最大程度的避免空数组越界问题  有时候还是挺有用的
        // 索引超出，自动增长
        src.set(4, "44");
        System.out.println(src); //[11, 22, null, null, 44]
    }

}
