package cn.myth.guava.collection;

import com.google.common.base.Joiner;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

public class TableTest {

    /**
     * 由rowKey+columnKey+value组成 它有两个键，一个值，和一个n行三列的数据表类似，n行取决于Table对对象中存储了多少个数据
     */

    @Test
    public void hash_based_table() {
        Table<String,String,Integer> table = HashBasedTable.create();
        table.put("grade_1","class_1",100);
        table.put("grade_1","class_2",95);
        table.put("grade_1","class_3",80);
        table.put("grade_2","class_1",88);
        table.put("grade_2","class_2",95);
        table.put("grade_2","class_3",99);
        table.put("grade_2","class_3",100);
        System.out.println(table);//{grade_1={class_1=100, class_2=95, class_3=80}, grade_2={class_1=88, class_2=95, class_3=100}}


        //table中的行：grade_1;列：class_1;值：100
        //table中的行：grade_1;列：class_2;值：95
        //table中的行：grade_1;列：class_3;值：80
        //table中的行：grade_2;列：class_1;值：88
        //table中的行：grade_2;列：class_2;值：95
        //table中的行：grade_2;列：class_3;值：100
        Set<Table.Cell<String,String,Integer>> cellSet = table.cellSet();
        cellSet.forEach(cell -> {
            System.out.println("table中的行："+cell.getRowKey()+";列："+cell.getColumnKey()+";值："+cell.getValue());
        });

        //grade1对应的class：class_1=100;class_2=95;class_3=80
        System.out.println("grade1对应的class：" + Joiner.on(";").withKeyValueSeparator("=").join(table.row("grade_1")));

        //class1对应的grade：grade_1=100;grade_2=88
        System.out.println("class1对应的grade：" + Joiner.on(";").withKeyValueSeparator("=").join(table.column("class_1")));

        //所有的grade:grade_1,grade_2
        System.out.println("所有的grade:" + Joiner.on(",").join(table.rowKeySet()));

        //所有的class：class_1,class_2,class_3
        System.out.println("所有的class：" + Joiner.on(",").join(table.columnKeySet()));

        //grade_1行对应的列值：class_1=100;class_2=95;class_3=80
        //grade_2行对应的列值：class_1=88;class_2=95;class_3=100
        Map<String, Map<String,Integer>> rowMap = table.rowMap();
        rowMap.forEach((row,map) -> {
            System.out.println(row +"行对应的列值：" + Joiner.on(";").withKeyValueSeparator("=").join(map));
        });

        //class_1列对应的行值：grade_1=100;grade_2=88
        //class_2列对应的行值：grade_1=95;grade_2=95
        //class_3列对应的行值：grade_1=80;grade_2=100
        Map<String,Map<String,Integer>> columnMap = table.columnMap();
        columnMap.forEach((column,map) -> {
            System.out.println(column +"列对应的行值：" + Joiner.on(";").withKeyValueSeparator("=").join(map));
        });

        //是否包含grade_1 和 class_2:true
        //是否包含grade_1 和 class_2:false
        System.out.println("是否包含grade_1 和 class_2:"+table.contains("grade_1","class_2"));
        table.remove("grade_1","class_2");
        System.out.println("是否包含grade_1 和 class_2:"+table.contains("grade_1","class_2"));

    }
}
