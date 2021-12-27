package me.gabryon.second_part.job_scheduler;

public final class ContextJobScheduler<K, V> {

    private JobSchedulerStrategy<K, V> strategy;
    
    public ContextJobScheduler(JobSchedulerStrategy<K, V> strategy) {
        this.strategy = strategy;
    }
    
    public void setStrategy(JobSchedulerStrategy<K, V> strategy) {
        this.strategy = strategy;
    }
    
    public void execute() {
        
        if (strategy == null) {
            throw new RuntimeException("The strategy cannot be null!");
        }
        
        strategy.output(strategy.collect());
    }
    
}
