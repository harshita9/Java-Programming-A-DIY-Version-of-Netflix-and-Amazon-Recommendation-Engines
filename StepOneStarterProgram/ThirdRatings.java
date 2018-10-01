
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
      this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){
        int numRaters = 0;
        ArrayList<Integer> ratings = new ArrayList<Integer>(); 
        for (Rater r : myRaters) {
           ArrayList<String> items = r.getItemsRated(); 
           for (String s : items) {
               if (s.equals(id)) {
                  ratings.add((int)r.getRating(s));
               }
           }
        }
        double sum = 0; 
        for (Integer i : ratings) {
            sum += i;
        }
        if (ratings.size() >= minimalRaters) {
          return sum/((double)ratings.size()); 
        }
        return 0.0; 
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratings = new ArrayList<Rating>(); 
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String ID : movies) {
               Rating rt = new Rating(ID, getAverageByID(ID, minimalRaters));
               if (rt.getValue() != 0.0) {
                   ratings.add(rt); 
               }
        }
        return ratings; 
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> ratings = new ArrayList<Rating>(); 
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String ID : movies) {
               Rating rt = new Rating(ID, getAverageByID(ID, minimalRaters));
               if (rt.getValue() != 0.0) {
                   ratings.add(rt); 
               }
        }
        return ratings; 
    }
}
