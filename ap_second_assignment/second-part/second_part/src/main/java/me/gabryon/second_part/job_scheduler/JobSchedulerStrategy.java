package me.gabryon.second_part.job_scheduler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class JobSchedulerStrategy<K, V> {
    
    /***
     * Generates a stream of jobs.
     * @return 
     */
    abstract protected Stream<AJob<K, V>> emit();
    
    /***
     * Executes the jobs received from emit by invoking execute on
     * them, and returns a single stream of key/value pairs obtained by 
     * concatenating the output of the jobs.
     * @property FrozenSpot
     * @return 
     */
    public final Stream<Pair<K, V>> compute() {
        return emit().flatMap((job) -> job.execute());
    }
    
    /***
     * Takes as input the output of compute and groups all the pairs with
     * the same keys in a single pair, having the same key and the list of all values;
     * @return 
     */
    protected final Stream<Pair<K, List<V>>> collect() {        
        var map = compute()
                .collect(Collectors.groupingBy(Pair::getKey, Collectors.mapping(Pair::getValue, Collectors.toList())));
        return map.entrySet().stream().map(entry -> new Pair(entry.getKey(), entry.getValue()));
    }
    
    /***
     * Prints the result of collect in a convenient way.
     * @param jobs
     */
    abstract protected void output(Stream<Pair<K, List<V>>> jobs);
}
