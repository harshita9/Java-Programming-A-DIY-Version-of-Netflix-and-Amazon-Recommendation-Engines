
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
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
        ArrayList<String> movie = new ArrayList<String>(); 
        for (Rater r : myRaters) {
           ArrayList<String> items = r.getItemsRated(); 
           for (String s : items) {
                 if (!movie.contains(s)) {
                     movie.add(s);
                     Rating rt = new Rating(s, getAverageByID(s, minimalRaters));
                     if (rt.getValue() != 0.0) {
                        ratings.add(rt); 
                     }
                }
           }
        }
        return ratings; 
    }
    
    public String getTitle(String id) {
        String mTitle = "NO SUCH ID.";
        for (Movie m : myMovies) { 
               if (m.getID().equals(id)) {
                   mTitle = m.getTitle();
               }
        }
        return mTitle;
    }
    
    public String getID(String title) {
        String mID = "NO SUCH TITLE.";
        for (Movie m : myMovies) { 
               if (m.getTitle().equals(title)) {
                   mID = m.getID();
               }
        }
        return mID;
    }
}
