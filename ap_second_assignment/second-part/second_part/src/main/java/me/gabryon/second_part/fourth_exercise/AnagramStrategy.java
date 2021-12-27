package me.gabryon.second_part.fourth_exercise;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import me.gabryon.second_part.job_scheduler.AJob;
import me.gabryon.second_part.job_scheduler.JobSchedulerStrategy;
import me.gabryon.second_part.job_scheduler.Pair;

/**
 *
 * @author gabryon
 */
public class AnagramStrategy extends JobSchedulerStrategy<String, String> {

    private final String absolutePath;
    
    public AnagramStrategy(String absolutePath) {
        this.absolutePath = absolutePath;
    }
    
    @Override
    protected Stream<AJob<String, String>> emit() {
       
        var dir = new File(absolutePath);
        if (!dir.isDirectory()) {
            throw new RuntimeException("The given path is not a directory!");
        }
                
        return Arrays.stream(dir.list())
                .filter(str -> str.endsWith(".txt"))
                .map(filename -> new AnagramJob(absolutePath + "/" + filename));
        
    }

    @Override
    protected void output(Stream<Pair<String, List<String>>> jobs) {
               
        try (var file = new FileOutputStream("./count_anagrams.csv")) {
                        
            jobs.forEach(pair -> {
                
                var line = String.format("%s,%d\n", pair.getKey(), pair.getValue().size());
                
                try {
                    file.write(line.getBytes());
                } catch (IOException ex) {
                    Logger.getLogger(AnagramStrategy.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        }
        catch (IOException ex) {
            Logger.getLogger(AnagramJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
