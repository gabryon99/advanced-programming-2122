package me.gabryon.second_part.fourth_exercise;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import me.gabryon.second_part.job_scheduler.AJob;
import me.gabryon.second_part.job_scheduler.JobSchedulerStrategy;
import me.gabryon.second_part.job_scheduler.Pair;


public class AnagramStrategy extends JobSchedulerStrategy<String, String> {

    // Path to directory containing text files.
    private final String absolutePath;
    
    public AnagramStrategy(String absolutePath) {
        this.absolutePath = absolutePath;
    }
    
    @Override
    protected Stream<AJob<String, String>> emit() {
       
        var dir = new File(absolutePath);
        // If the given path is not a directory we cannot read its content!
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("The given path is not a directory!");
        }
                
        // From each file contained inside the directory, takes only
        // the file ending with .txt extensions and create a new Stream
        // of Jobs according to the exercise specification.
        return Arrays.stream(dir.list())
                .filter(str -> str.endsWith(".txt"))
                .map(filename -> new AnagramJob(absolutePath + "/" + filename));
     }

    @Override
    protected void output(Stream<Pair<String, List<String>>> jobs) {
        
        // Write the jobs down to the count_anagram.csv file
        try (var file = new FileOutputStream("./count_anagrams.csv")) {
                        
            // For each job print a line "<ciao_word>,<occurrences>" inside the CSV file
            jobs.forEach(pair -> {
                
                var line = String.format("%s,%d\n", pair.getKey(), pair.getValue().size());
                
                try {
                    file.write(line.getBytes());
                } 
                catch (IOException ex) {
                    Logger.getLogger(AnagramStrategy.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        }
        catch (IOException | IllegalStateException ex) {
            Logger.getLogger(AnagramJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
