public interface Search<V> {
    boolean hasPathTo(V destination);

    Iterable<V> pathTo(V destination);
}