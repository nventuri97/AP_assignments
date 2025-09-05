package uni.advanced_programming;

import java.util.List;
import java.util.stream.Stream;

/**
 * Abstract strategy to implement Pattern Strategy Framework, implemented through this interfaced that
 * declares the output() method
 * @param <K> the generic value for Key
 * @param <V> the generic value for Value
 */
public interface AbstractOutputStrategy<K,V> {
    /**
     * Abstract method output that has to be implemented in the class that implements the interface
     * and prints the results of the pipeline
     */
    public abstract void output(Stream<Pair<K, List<V>>> data);
}
