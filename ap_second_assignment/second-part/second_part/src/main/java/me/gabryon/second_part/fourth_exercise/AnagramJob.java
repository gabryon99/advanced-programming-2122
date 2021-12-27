package me.gabryon.second_part.fourth_exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import me.gabryon.second_part.job_scheduler.AJob;
import me.gabryon.second_part.job_scheduler.Pair;

/**
 *
 * @author gabryon
 */
public class AnagramJob extends AJob<String, String> {

    private final static int MAX_LENGTH = 4;
    
    public static String ciao(String word) {
        var wordChars = word.toLowerCase().toCharArray();
        Arrays.sort(wordChars);
        return new String(wordChars, 0, wordChars.length);
    }
    
    public static boolean isOnlyLetters(String word) {
        return word.chars().allMatch(Character::isLetter);
    }
    
    private String filename;
    
    public AnagramJob(String filename) {
        this.filename = filename;
    }
    
    @Override
    public Stream<Pair<String, String>> execute() {
        
        try {
            // You should ignore all words of less than four characters, 
            // and those containing non-alphabetic characters
            return Arrays.stream(Files.readString(Paths.get(filename)).split("\\s+"))
                    .filter(str -> str.length() > MAX_LENGTH)
                    .filter(AnagramJob::isOnlyLetters)
                    .map(str -> {
                        return new Pair(ciao(str), str);
                    });
        } 
        catch (IOException ex) {
            Logger.getLogger(AnagramJob.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
            return Stream.empty();
        }
    }
    
}
