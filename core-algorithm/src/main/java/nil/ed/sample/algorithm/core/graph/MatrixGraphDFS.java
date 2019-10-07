package nil.ed.sample.algorithm.core.graph;

import java.util.Scanner;

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
public class MatrixGraphDFS {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine();
        int[][] graph = new int[n][n];
        int from, to;
        while (true){
            String input = in.nextLine();
            if (input.equals("$")){
                break;
            }
            String[] edge = input.split(",");
            from = Integer.parseInt(edge[0]);
            to = Integer.parseInt(edge[1]);
            graph[from][to] = 1;
        }
        dfs(graph, m);
    }

    private static void dfs(int[][] graph, int entryVertex){
        boolean[] visited = new boolean[graph.length];
        dfs(graph, visited, entryVertex);
    }

    private static void dfs(int[][] graph, boolean[] visited, int curVertex){
        if (visited[curVertex]){
            return;
        }
        System.out.print(curVertex + " ");
        visited[curVertex] = true;
        for (int i = 0; i < visited.length; ++i){
            if (graph[curVertex][i] == 1 && !visited[i]){
                dfs(graph, visited, i);
            }
        }
    }
}
