package nil.ed.sample.algorithm.core.graph;

import java.util.Map;

public interface IGraph {
    double getShortestPath(Object from, Object to);

    /**
     * 获得最短路径
     * @param from 起始点
     * @return 到每个点的成本
     */
    Map<Object, Double> getShortestPaths(Object from);

    /**
     * 添加有向边
     * @param from 起始点
     * @param weight 权重
     * @param to 终止点
     */
    void addEdgeWithDirection(Object from, double weight, Object to);

    /**
     * 添加无向边
     * @param from 起始点
     * @param weight 权重
     * @param to 终止点
     */
    void addEdgeWithoutDirection(Object from, double weight, Object to);

    /**
     * 计算入度
     * @param node 结点
     * @return 入度
     */
    int getInDegree(Object node);

    /**
     * 计算出度
     * @param node 结点
     * @return 出度
     */
    int getOutDegree(Object node);

    /**
     * 获取所有结点的度的统计值
     * @return 所有度的统计
     */
    int[][] getDegreeStatistics();
}
