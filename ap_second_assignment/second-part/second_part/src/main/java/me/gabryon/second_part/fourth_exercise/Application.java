package me.gabryon.second_part.fourth_exercise;

import me.gabryon.second_part.job_scheduler.ContextJobScheduler;

/**
 * Main class and entry point for the fourth exercise.
 * @author gabryon
 */
public class Application {
    
    public static void main(String[] args) {
        
        // If no argument was provided then raise an error and close the program.
        if (args.length != 1) {
            System.err.println("usage: java Application /absolute/path");
            return;
        }
        
        // Create a new framework instance and invoke its main method to
        // start the execution.
        var strategy = new AnagramStrategy(args[0]);
        var scheduler = new ContextJobScheduler(strategy);
        scheduler.main();
        
    }
    
}
