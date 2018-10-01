
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {
    public void printAverageRatings() {
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        System.out.println(sr.getMovieSize());
        System.out.println(sr.getRaterSize());
        int minimalRaters = 12; 
        ArrayList<Rating> ratings = sr.getAverageRatings(minimalRaters);
        Collections.sort(ratings);
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + sr.getTitle(r.getItem()));
            //break;
        }
        //System.out.println(ratings.size());
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        int minimalRaters = 1; 
        ArrayList<Rating> ratings = sr.getAverageRatings(minimalRaters);
        for (Rating r : ratings) {
            if (r.getItem().equals(sr.getID("Vacation"))) {
              System.out.println(r.getValue());
            }
        }
    }
}