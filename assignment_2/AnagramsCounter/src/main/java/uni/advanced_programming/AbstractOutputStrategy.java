package uni.advanced_programming;

import java.util.List;
import java.util.stream.Stream;

public interface AbstractOutputStrategy<K,V> {
    public abstract void output(Stream<Pair<K, List<V>>> data);
}
