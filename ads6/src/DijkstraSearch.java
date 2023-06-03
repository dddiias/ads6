import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private WeightedGraph<V> graph;
    private V source;
    private Map<V, Double> distTo;
    private Map<V, V> edgeTo;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        this.graph = graph;
        this.source = source;
        this.distTo = new HashMap<>();
        this.edgeTo = new HashMap<>();

        dijkstra();
    }

    private void dijkstra() {
        Queue<VertexDistPair> priorityQueue = new PriorityQueue<>();
        for (V vertex : graph.getVertices()) {
            distTo.put(vertex, Double.POSITIVE_INFINITY);
        }

        distTo.put(source, 0.0);
        priorityQueue.add(new VertexDistPair(source, 0.0));

        while (!priorityQueue.isEmpty()) {
            VertexDistPair pair = priorityQueue.poll();
            V vertex = pair.getVertex();
            double distance = pair.getDistance();

            if (distance > distTo.get(vertex)) {
                continue;
            }

            for (Map.Entry<V, Double> entry : graph.getAdjacentVertices(vertex).entrySet()) {
                V adjacentVertex = entry.getKey();
                double weight = entry.getValue();

                double newDistance = distTo.get(vertex) + weight;

                if (newDistance < distTo.get(adjacentVertex)) {
                    distTo.put(adjacentVertex, newDistance);
                    edgeTo.put(adjacentVertex, vertex);
                    priorityQueue.add(new VertexDistPair(adjacentVertex, newDistance));
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V destination) {
        return distTo.containsKey(destination);
    }

    @Override
    public Iterable<V> pathTo(V destination) {
        if (!hasPathTo(destination)) {
            return null;
        }

        List<V> path = new ArrayList<>();
        V vertex = destination;

        while (vertex != null) {
            path.add(vertex);
            vertex = edgeTo.get(vertex);
        }

        Collections.reverse(path);
        return path;
    }

    private class VertexDistPair implements Comparable<VertexDistPair> {
        private V vertex;
        private double distance;

        public VertexDistPair(V vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public V getVertex() {
            return vertex;
        }

        public double getDistance() {
            return distance;
        }

        @Override
        public int compareTo(VertexDistPair other) {
            return Double.compare(this.distance, other.distance);
        }
    }
}