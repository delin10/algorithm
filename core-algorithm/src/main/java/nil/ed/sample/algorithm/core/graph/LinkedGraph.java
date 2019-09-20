package nil.ed.sample.algorithm.core.graph;

import nil.ed.sample.algorithm.core.util.ArrayHelper;

import java.util.*;

/**
 * 邻接链表表示
 */
public class LinkedGraph implements IGraph {
    /**
     * 和对象的映射
     */
    private Map<Object, Integer> mapper;
    /*
    顶点列表
     */
    private List<Vertex> vertexList;

    public LinkedGraph() {
        mapper = new HashMap<>();
        vertexList = new ArrayList<>();
    }

    @Override
    public double getShortestPath(Object from, Object to) {
        return 0;
    }

    @Override
    public Map<Object, Double> getShortestPaths(Object from) {
        return null;
    }

    @Override
    public void addEdgeWithDirection(Object from, double weight, Object to) {
        int fromIndex = getVertexIndexOrAdd(from),toIndex = getVertexIndexOrAdd(to);
        Vertex fromVertex = vertexList.get(fromIndex);
        fromVertex.edges.add(new Edge(toIndex, weight));
    }

    @Override
    public void addEdgeWithoutDirection(Object from, double weight, Object to) {
        int fromIndex = getVertexIndexOrAdd(from),toIndex = getVertexIndexOrAdd(to);
        Vertex fromVertex = vertexList.get(fromIndex);
        fromVertex.edges.add(new Edge(toIndex, weight));
        Vertex toVertex = vertexList.get(toIndex);
        toVertex.edges.add(new Edge(fromIndex, weight));
    }

    @Override
    public int getInDegree(Object node) {
        int index = mapper.get(node), degree = 0;
        for (Vertex vertex : vertexList){
            for (Edge edge : vertex.edges){
                if (vertexList.get(edge.toId).name.equals(node)){
                    degree++;
                }
            }
        }
        return degree;
    }

    @Override
    public int getOutDegree(Object node) {
        int index = mapper.get(node);
        return vertexList.get(index).edges.size();
    }

    @Override
    public int[][] getDegreeStatistics() {
        int[][] degrees = new int[vertexList.size()][2];
        for (Vertex vertex : vertexList){
            int vIndex = mapper.get(vertex.name);
            for (Edge edge : vertex.edges){
                degrees[vIndex][0]++;
                int toVertexIndex = mapper.get(vertexList.get(edge.toId).name);
                degrees[toVertexIndex][1]++;
            }
        }
        return degrees;
    }

    /**
     * 添加结点并且返回编号
     * @param node 需要添加的结点对象
     * @return 结点对应的编号
     */
    private int getVertexIndexOrAdd(Object node){
        Integer index = mapper.get(node);
        /*
        判断该结点是否已经存在
         */
        if (Objects.isNull(index)) {
            int size = vertexList.size();
            index = vertexList.size();
            mapper.put(node, index);
            vertexList.add(new Vertex(node));
        }
        return index;
    }

    private class Vertex{
        private Object name;
        private List<Edge> edges;

        public Vertex(Object name) {
            this.name = name;
            edges = new LinkedList<>();
        }
    }

    private class Edge{
        private int toId;
        private double weight;

        public Edge(int toId, double weight) {
            this.toId = toId;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {

    }

    public static void testDegreeOperation(){
        IGraph graph = new LinkedGraph();

        graph.addEdgeWithoutDirection("1",1,"2");
        graph.addEdgeWithoutDirection("1",1,"3");
        graph.addEdgeWithoutDirection("2",1,"6");
        graph.addEdgeWithoutDirection("2",1,"5");
        graph.addEdgeWithoutDirection("2",1,"3");
        graph.addEdgeWithoutDirection("2",1,"4");
        graph.addEdgeWithoutDirection("3",1,"4");
        graph.addEdgeWithoutDirection("3",1,"5");
        ArrayHelper.printArray(graph.getDegreeStatistics());

        for (int i = 1; i < 7; ++i){
            System.out.println(graph.getOutDegree(i+""));
            System.out.println(graph.getInDegree(i+""));
        }
    }
}
