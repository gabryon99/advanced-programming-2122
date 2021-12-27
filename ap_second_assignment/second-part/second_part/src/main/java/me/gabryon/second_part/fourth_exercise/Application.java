package me.gabryon.second_part.fourth_exercise;

import me.gabryon.second_part.job_scheduler.ContextJobScheduler;

/**
 *
 * @author gabryon
 */
public class Application {
    
    public static void main(String[] args) {
        
        if (args.length != 1) {
            System.err.println("usage: java Application /absolute/path");
            return;
        }
        
        var strategy = new AnagramStrategy(args[0]);
        var scheduler = new ContextJobScheduler(strategy);
        scheduler.execute();
        
    }
    
}
