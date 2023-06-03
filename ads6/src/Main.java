public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>(true);

        graph.addEdge("Almaty", "Astana", 2.1);
        graph.addEdge("Almaty", "Shymkent", 7.2);
        graph.addEdge("Shymkent", "Astana", 3.9);
        graph.addEdge("Astana", "Kostanay", 3.5);
        graph.addEdge("Shymkent", "Kyzylorda", 5.4);
        graph.addEdge("Kostanay", "Kokshetau", 1.4);
        graph.addEdge("Kokshetau", "Petropavl", 2.2);

        System.out.println("Dijkstra:");
        Search<String> djk = new DijkstraSearch<>(graph, "Almaty");
        outputPath(djk, "Petropavl");
    }

    public static void outputPath(Search<String> search, String key) {
        for (String v : search.pathTo(key)) {
            System.out.print(v + " -> ");
        }
    }
}