import java.util.*;

public class WeightedGraph<V> {
    private Map<V, Map<V, Double>> adjacencyList;
    private boolean isDirected;

    public WeightedGraph(boolean isDirected) {
        this.isDirected = isDirected;
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(V vertex) {
        adjacencyList.put(vertex, new HashMap<>());
    }

    public void addEdge(V source, V destination, double weight) {
        if (!adjacencyList.containsKey(source)) {
            addVertex(source);
        }
        if (!adjacencyList.containsKey(destination)) {
            addVertex(destination);
        }

        adjacencyList.get(source).put(destination, weight);

        if (!isDirected) {
            adjacencyList.get(destination).put(source, weight);
        }
    }

    public Set<V> getVertices() {
        return adjacencyList.keySet();
    }

    public Map<V, Double> getAdjacentVertices(V vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptyMap());
    }

    public double getWeight(V source, V destination) {
        return adjacencyList.getOrDefault(source, Collections.emptyMap()).getOrDefault(destination, Double.POSITIVE_INFINITY);
    }
}