package nil.ed.sample.algorithm.core.graph;

import nil.ed.sample.algorithm.core.util.ArrayHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * 矩阵实现的图
 *
 * @author lidelin
 */
public class FixedMatrixGraph implements IGraph{
    /**
     * 成本矩阵
     */
    private double[][] graph;

    /**
     * 和对象的映射
     */
    private Map<Object, Integer> mapper;

    /**
     * 当前使用的方阵的大小
     */
    private int size;

    /*
    容量
     */
    private int capacity;

    /*
    矩阵额外增长的大小
     */
    private int incStep;

    public FixedMatrixGraph(int capacity, int incStep) {
        this.incStep = incStep;
        this.capacity = capacity;
        this.mapper = new HashMap<>(capacity, 1);
        initGraph(capacity);
    }

    @Override
    public void addEdgeWithDirection(Object from, double weight, Object to) {
        if (from.equals(to)){
            weight = 0;
        }

        int fromIndex = addNode(from);
        int toIndex = addNode(to);

        graph[fromIndex][toIndex] = weight;
    }

    @Override
    public void addEdgeWithoutDirection(Object from, double weight, Object to) {
        addEdgeWithDirection(from, weight, to);
        addEdgeWithDirection(to, weight, from);
    }

    @Override
    public Map<Object, Double> getShortestPaths(Object from) {
        double[] dp = new double[size];
        boolean[] visitedMark = new boolean[size];
        int fromIndex = mapper.get(from);

        for (int i = 0; i < size; ++i){
            dp[i] = graph[fromIndex][i];
        }
        /*
        Dijkstra 算法
         */
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> (int)(dp[a]-dp[b]));
        queue.add(0);
        while (!queue.isEmpty()){
            int cur = queue.poll();
            if (!visitedMark[cur]){
                /*
                邻居
                 */
                for (int i = 0; i < size;++i){
                    if (Double.isFinite(graph[cur][i])){
                        dp[i] = Math.min(dp[cur] + graph[cur][i], dp[i]);
                        queue.add(i);
                    }
                }
                visitedMark[cur] = true;
            }
        }


        for (int i = 0; i < dp.length; ++i){
            System.out.printf("(%s, %.2f , %s) ", getNode(fromIndex), dp[i], getNode(i));
        }
        return null;
    }

    @Override
    public double getShortestPath(Object from, Object to) {

        return 0;
    }

    /**
     * 添加结点并且返回编号
     * @param node 需要添加的结点对象
     * @return 结点对应的编号
     */
    private int addNode(Object node){
        Integer index = mapper.get(node);
        /*
        判断该结点是否已经存在
         */
        if (Objects.isNull(index)) {
            ensureCapacity(size + 1);
            index = size;
            mapper.put(node, index);
            graph[size][size] = 0;
            ++size;
        }
        return index;
    }

    private void ensureCapacity(int expectSize){
        double[][] backup = graph;
        if (expectSize >= capacity){
            initGraph(expectSize + incStep);
            /*
            拷贝原数组的值
             */
            for (int i = 0; i < size; ++i){
                System.arraycopy(backup[i], 0, graph[i], 0, size);
            }
        }
    }

    private void initGraph(int capacity){
        this.capacity = capacity;
        graph = new double[capacity][capacity];
        for (int i = 0; i < capacity; ++i){
            for (int j = 0; j < capacity; ++j){
                graph[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }

    private Object getNode(int i){
        return mapper.entrySet().stream().filter(entry -> Objects.equals(entry.getValue(), i))
                .findFirst().map(Map.Entry::getKey).orElseThrow(IllegalStateException::new);
    }

    public static void main(String[] args) {
        FixedMatrixGraph matrixGraph = new FixedMatrixGraph(6, 1);
        matrixGraph.addEdgeWithoutDirection('u',5,'w');
        matrixGraph.addEdgeWithoutDirection('u',2,'v');
        matrixGraph.addEdgeWithoutDirection('u',1,'x');
        matrixGraph.addEdgeWithoutDirection('v',3,'w');
        matrixGraph.addEdgeWithoutDirection('v',2,'x');
        matrixGraph.addEdgeWithoutDirection('w',3,'x');
        matrixGraph.addEdgeWithoutDirection('w',1,'y');
        matrixGraph.addEdgeWithoutDirection('w',5,'z');
        matrixGraph.addEdgeWithoutDirection('x',1,'y');
        matrixGraph.addEdgeWithoutDirection('y',2,'z');

        matrixGraph.getShortestPaths('u');
    }
}
