package uni.advanced_programming;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * JobScheduler class that is a generic framework class
 * @param <K> Key
 * @param <V> Value
 */
public class JobScheduler<K,V> {

    private AbstractEmitStrategy<K,V> emitStrategy;
    private AbstractOutputStrategy<K,V> outputStrategy;

    /**
     * Empty constructor
     */
    public JobScheduler() {
    }

    /**
     * JobScheduler parametrized constructor
     * @param emitStrategy concrete emit strategy that implements the AbstractEmitStrategy interface
     * @param outputStrategy concrete output strategy that implements the AbstractOutputStrategy interface
     */
    public JobScheduler(AbstractEmitStrategy<K, V> emitStrategy, AbstractOutputStrategy<K, V> outputStrategy) {
        this.emitStrategy = emitStrategy;
        this.outputStrategy = outputStrategy;
    }

    /**
     * Setter for emitStrategy
     * @param emitStrategy concrete emit strategy that implements the AbstractEmitStrategy interface
     */
    public void setEmitStrategy(AbstractEmitStrategy<K, V> emitStrategy) {
        this.emitStrategy = emitStrategy;
    }

    /**
     * Setter for outputStrategy
     * @param outputStrategy concrete output strategy that implements the AbstractOutputStrategy interface
     */
    public void setOutputStrategy(AbstractOutputStrategy<K, V> outputStrategy) {
        this.outputStrategy = outputStrategy;
    }

    public final void executePhases(){
        output(collect(compute(emit())));
    }

    public Stream<AJob<K,V>> emit(){
        return emitStrategy.emit();
    }

    public final Stream<Pair<K,V>> compute(Stream<AJob<K,V>> data){
        return data.flatMap(AJob::execute);
    }

    public final Stream<Pair<K, List<V>>> collect(Stream<Pair<K,V>> data){
        Map<K, List<V>> grouped = data.collect(
                Collectors.groupingBy(
                        Pair::getKey,
                        Collectors.mapping(Pair::getValue, Collectors.toList())
                )
        );
        return grouped.entrySet().stream()
                .map(e -> new Pair<>(e.getKey(), e.getValue()));
    }

    public void output(Stream<Pair<K, List<V>>> data){
        outputStrategy.output(data);
    }

    public static void main(String[] args) {
        final String outputFilename="/home/nicola/Scrivania/Magistrale/Advanced Programming/AP_assignments/assignment_2/AnagramsCounter/output/count_anagrams.txt";

        JobScheduler<String, Integer> scheduler = new JobScheduler<>(
                new ConcreteEmitStrategy(),
                new ConcreteOutputStrategy(outputFilename)
        );
        scheduler.executePhases();
    }
}
