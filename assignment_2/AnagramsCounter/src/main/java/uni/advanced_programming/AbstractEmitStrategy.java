package uni.advanced_programming;

import java.util.stream.Stream;

public interface AbstractEmitStrategy<K,V> {
    public abstract Stream<AJob<K,V>> emit();
}
