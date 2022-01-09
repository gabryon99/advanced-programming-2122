package me.gabryon.second_part.job_scheduler;

public final class ContextJobScheduler<K, V> {

    // The strategy to use in order to output the jobs.
    private JobSchedulerStrategy<K, V> strategy;
    
    /***
     * Create a new ContextJobScheduler with a given strategy to run.
     * @param strategy 
     */
    public ContextJobScheduler(JobSchedulerStrategy<K, V> strategy) {
        this.strategy = strategy;
    }
    
    /***
     * Set a new strategy to use during the framework execution.
     * @param strategy 
     */
    public void setStrategy(JobSchedulerStrategy<K, V> strategy) {
        this.strategy = strategy;
    }
    
    /***
     * Framework entry point. It is used to start the framework execution.
     */
    public final void main() {
        
        // A null strategy will throw a IllegalArgumentException
        if (strategy == null) {
            throw new IllegalArgumentException("The strategy cannot be null!");
        }
        
        // The framework pipeline is executed as written by the specification:
        
        // 1) Emit, which generates a stream of jobs to compute
        var emitted = strategy.emit();
        // 2) Which executes the Jobs invoking the AJob::execute method
        var computed = strategy.compute(emitted);
        // 3) Collect, which takes as input the output of compute and groups all the pairs with
        // the same keys in a single pair, having the same key and the list of all values;
        var collected = strategy.collect(computed);
        // 4) Output, which prints the result of collect in a convenient way
        strategy.output(collected);
    }
    
}
