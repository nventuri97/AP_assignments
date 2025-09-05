package uni.advanced_programming;

import java.util.stream.Stream;

/**
 * Abstract strategy to implement Pattern Strategy Framework, implemented through this interfaced that
 * declares the emit() method
 * @param <K> the generic value for Key
 * @param <V> the generic value for Value
 */
public interface AbstractEmitStrategy<K,V> {
    /**
     * Abstract method emit that has to be implemented in the class that implements the interface
     * @return a stream of type Stream<AJob<K,V>>
     */
    public abstract Stream<AJob<K,V>> emit();
}
