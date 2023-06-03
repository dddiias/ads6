import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private WeightedGraph<V> graph;
    private V source;
    private Map<V, Boolean> visited;
    private Map<V, V> edgeTo;

    public BreadthFirstSearch(WeightedGraph<V> graph, V source) {
        this.graph = graph;
        this.source = source;
        this.visited = new HashMap<>();
        this.edgeTo = new HashMap<>();

        bfs(source);
    }

    private void bfs(V source) {
        Queue<V> queue = new LinkedList<>();
        visited.put(source, true);
        queue.add(source);

        while (!queue.isEmpty()) {
            V vertex = queue.poll();

            for (Map.Entry<V, Double> entry : graph.getAdjacentVertices(vertex).entrySet()) {
                V adjacentVertex = entry.getKey();

                if (!visited.containsKey(adjacentVertex)) {
                    visited.put(adjacentVertex, true);
                    edgeTo.put(adjacentVertex, vertex);
                    queue.add(adjacentVertex);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V destination) {
        return visited.containsKey(destination);
    }

    @Override
    public Iterable<V> pathTo(V destination) {
        if (!hasPathTo(destination)) {
            return null;
        }

        Stack<V> path = new Stack<>();
        V vertex = destination;

        while (vertex != null) {
            path.push(vertex);
            vertex = edgeTo.get(vertex);
        }

        List<V> pathList = new ArrayList<>();
        while (!path.isEmpty()) {
            pathList.add(path.pop());
        }

        return pathList;
    }
}