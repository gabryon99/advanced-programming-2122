package me.gabryon.second_part.fourth_exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import me.gabryon.second_part.job_scheduler.AJob;
import me.gabryon.second_part.job_scheduler.Pair;

/**
 * The implementation of the class requested by the fourth exercise on the
 * first point.
 * @author gabryon
 */
public class AnagramJob extends AJob<String, String> {

    private final static int MAX_LENGTH = 4;
    
    /***
     * Compute the ciao of the string word.
     * @param word
     * @return The ciao string.
     */
    public static String ciao(String word) {
        
        var wordChars = word.toLowerCase().toCharArray();
        Arrays.sort(wordChars);
        
        return new String(wordChars, 0, wordChars.length);
    }
    
    /***
     * Evaluate if the word contains only letter characters.
     * @param word
     * @return True if the word contains only letter characters, false otherwise.
     */
    public static boolean isOnlyLetters(String word) {
        return word.chars().allMatch(Character::isLetter);
    }
    
    /**
     * The file name from which read the words.
     */
    private final String filename;
    
    public AnagramJob(String filename) {
        this.filename = filename;
    }
    
    @Override
    public Stream<Pair<String, String>> execute() {
        
        try {
            // As said by the specification: "You should ignore all words of less
            // than four characters, those containing non-alphabetic characters"
            return Arrays.stream(Files.readString(Paths.get(filename)).split("\\s+"))
                    .filter(str -> str.length() > MAX_LENGTH)
                    .filter(AnagramJob::isOnlyLetters)
                    .map(str -> {
                        return new Pair(ciao(str), str);
                    });
        } 
        catch (IOException ex) {
            Logger.getLogger(AnagramJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Return an empty stream if something goes wrong
        return Stream.empty();
    }
    
}
