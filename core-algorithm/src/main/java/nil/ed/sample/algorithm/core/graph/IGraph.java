package nil.ed.sample.algorithm.core.graph;

import java.util.Map;

public interface IGraph {
    double getShortestPath(Object from, Object to);

    Map<Object, Double> getShortestPaths(Object from);

    void addEdgeWithDirection(Object from, double weight, Object to);

    void addEdgeWithoutDirection(Object from, double weight, Object to);
}
