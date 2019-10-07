package nil.ed.sample.algorithm.core.graph;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * 题目描述：
 * 图的深度优先遍历
 * 输入：
 * 第一行是两个数字：n,m
 * n表示图顶点的个数
 * m表示深度遍历开始的顶点
 *
 * 接着是多行以逗号分隔的边，输入$结束
 * 示例：
 * 输入：
 * 5 0
 * 0,1
 * 0,2
 * 1,3
 * 2,3
 * 3,4
 * $
 * 输出：0 1 3 4 2
 *
 * @author delin10
 */
public class LinkedGraphDFS {

    public static void main(String[] args) {
        /*
5 0
0,1
0,2
1,3
2,3
3,4
$
         */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine();
        Vertex[] graph = new Vertex[n];
        IntStream.range(0, n).forEach(i -> graph[i] = new Vertex(i));
        int from, to;
        while (true){
            String input = in.nextLine();
            if ("$".equals(input)){
                break;
            }
            String[] edge = input.split(",");
            from = Integer.parseInt(edge[0]);
            to = Integer.parseInt(edge[1]);
            graph[from].linkAfter(new Vertex(to));
        }
        dfs(graph, m);
    }

    private static void dfs(Vertex[] graph, int entryVertex){
        boolean[] visited = new boolean[graph.length];
        dfs(graph, visited, entryVertex);
    }

    private static void dfs(Vertex[] graph, boolean[] visited, int curVertex){
        if (visited[curVertex]){
            return;
        }
        System.out.print(curVertex + " ");
        visited[curVertex] = true;
        for (Vertex cursor = graph[curVertex].edge; cursor != null; cursor = cursor.edge){
            if (!visited[cursor.id]){
                dfs(graph, visited, cursor.id);
            }
        }
    }

    static class Vertex{
        private int id;
        private Vertex edge;

        Vertex(int id) {
            this.id = id;
        }

        void linkAfter(Vertex edge){
            if (this.edge == null){
                this.edge = edge;
                return;
            }

            edge.edge = this.edge;
            this.edge = edge;
        }
    }
}
