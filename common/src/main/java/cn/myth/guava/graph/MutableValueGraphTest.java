package cn.myth.guava.graph;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import org.junit.Test;

public class MutableValueGraphTest {

    @Test
    public void mutable_value_graph() {
        // 创建一个带有权重的图
        MutableValueGraph<String, Integer> graph = ValueGraphBuilder.directed().build();

        // 添加带权重的边
        graph.putEdgeValue("A", "B", 4);
        graph.putEdgeValue("B", "C", 3);
        graph.putEdgeValue("C", "A", 2);

        // 获取并输出边的权重
        System.out.println("Weight from A to B: " + graph.edgeValueOrDefault("A", "B", 0)); //Weight from A to B: 4

        System.out.println(graph);//isDirected: true, allowsSelfLoops: false, nodes: [A, B, C], edges: {<A -> B>=4, <B -> C>=3, <C -> A>=2}

    }
}
