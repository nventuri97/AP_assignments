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

    /**
     * executePhases is the method that implements effectively the pipeline,
     * invoking the method in the proper way.
     */
    public final void executePhases(){
        output(collect(compute(emit())));
    }

    /**
     * Invoke the emit method of the emitStrategy set in the instance
     * @return a Stream of AJobs
     */
    public Stream<AJob<K,V>> emit(){
        return emitStrategy.emit();
    }

    /**
     * Compute stage of the pipeline that executes the work of the Job invoking execute() method
     * @param data the AJob Stream received frome emit stage
     * @return a Strem of Pair<K,V>
     */
    public final Stream<Pair<K,V>> compute(Stream<AJob<K,V>> data){
        return data.flatMap(AJob::execute);
    }

    /**
     * Collect stage of the pipeline that collects each key and create a list of value for each key
     * @param data the Pair Stream received from the compute stage
     * @return a Stream<Pair<K, List<V>>>
     */
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

    /**
     * Output stage of the pipeline that invokes the output method of the outputStrategy set in the instance
     * @param data the Stream<Pair<K, List<V>>> received from the collect stage
     */
    public void output(Stream<Pair<K, List<V>>> data){
        outputStrategy.output(data);
    }

    /**
     * The main entry point of the framework.
     * This method initializes a JobScheduler instance with concrete implementations
     * of the emission and output strategies, and then executes the pipeline.
     */
    public static void main(String[] args) {
        final String outputFilename="/home/nicola/Scrivania/Magistrale/Advanced Programming/AP_assignments/assignment_2/AnagramsCounter/output/count_anagrams.txt";

        JobScheduler<String, Integer> scheduler = new JobScheduler<>(
                new ConcreteEmitStrategy(),
                new ConcreteOutputStrategy(outputFilename)
        );
        scheduler.executePhases();
    }
}
