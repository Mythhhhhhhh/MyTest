package cn.myth.guava.graph;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import org.junit.Test;

public class MutableGraphTest {

    @Test
    public void mutable_graph() {
        // 创建一个有向图
        MutableGraph<String> graph = GraphBuilder.directed().build();

        // 添加节点
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");

        // 添加边
        graph.putEdge("A", "B");
        graph.putEdge("B", "C");
        graph.putEdge("C", "A");

        // 输出图的信息
        System.out.println("Nodes: " + graph.nodes());//Nodes: [A, B, C]
        System.out.println("Edges: " + graph.edges());//Edges: [<A -> B>, <B -> C>, <C -> A>]
        System.out.println(graph);//isDirected: true, allowsSelfLoops: false, nodes: [A, B, C], edges: [<A -> B>, <B -> C>, <C -> A>]
    }
}
