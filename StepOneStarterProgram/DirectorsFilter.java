
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class DirectorsFilter implements Filter{
    private String myDirector;
    
    public DirectorsFilter(String directors) {
        myDirector = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        String first = MovieDatabase.getDirector(id);
        String second = myDirector;

        //split the second string into words
        List<String> wordsOfSecond = Arrays.asList(second.split(","));
    
        //split and compare each word of the first string           
        for (String word : first.split(",")) {
            if(wordsOfSecond.contains(word)) {
              return true;
            }  
        }
        return false; 
    }
}
