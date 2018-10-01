
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> movie = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
           String anID = record.get("id");
           String aTitle = record.get("title");
           String aYear = record.get("year"); 
           String theGenres = record.get("genre");
           String aDirector = record.get("director");
           String aCountry = record.get("country");
           String aPoster = record.get("poster");
           int theMinutes = Integer.parseInt(record.get("minutes"));
           Movie m = new Movie(anID, aTitle, aYear, theGenres, aDirector, aCountry, aPoster, 
                   theMinutes);
           movie.add(m);
        }
        return movie; 
    }
    
    public void testLoadMovies() {
        ArrayList<Movie> movie = loadMovies("ratedmoviesfull.csv");
        System.out.println(movie.size());
        //for (Movie m : movie) {
           //System.out.println(m);
        //}
        int num = 0; 
        for (Movie m : movie) {
           if (m.getGenres().contains("Comedy")) {
               num++;
           }
        }
        System.out.println(num);
        num = 0; 
        for (Movie m : movie) {
           if (m.getMinutes() > 150) {
               num++;
           }
        }
        System.out.println(num);
        num = 0;
        HashMap<String,Integer> map = new HashMap<String,Integer>(); 
        for (Movie m : movie) {
           if (!map.containsKey(m.getDirector())) {
              map.put(m.getDirector(), 1);
           }
           else {
              map.put(m.getDirector(), map.get(m.getDirector()) + 1);
           }
        }
        String maxDir = "";
        for (String s : map.keySet()) {
           if (map.get(s) > num) {
               num = map.get(s);
               maxDir = s; 
           }
        }
        System.out.println(num);
        System.out.println(maxDir);
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> ratings = new ArrayList<Rater>();
        HashMap<String, Rater> map = new HashMap<String, Rater>();
        filename = filename;
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
           String id = record.get("rater_id");
           String item = record.get("movie_id");
           double rating = Double.parseDouble(record.get("rating")); 
           if (!map.containsKey(id)) { 
              //Rater r = new Rater(id);
              Rater r = new PlainRater(id);
              //Rater r = new EfficientRater(id);
              r.addRating(item, rating);
              map.put(id, r);
              ratings.add(r);
           }
           else {
              ratings.get(ratings.indexOf(map.get(id))).addRating(item, rating);
           }
        }
        return ratings; 
    }
    
    public void testLoadRaters() {
        ArrayList<Rater> ratings = loadRaters("ratings.csv");
        System.out.println(ratings.size());
        //for (Rater r : ratings) {
           //System.out.print(r.getID() + ": ");
           //System.out.println(r.numRatings());
           //ArrayList<String> items = r.getItemsRated();
           //for (String s : items) {
               //System.out.println(s + ", " + r.getRating(s));
           //}
        //}
        for (Rater r : ratings) {
            if (r.getID().equals("193")) {
               System.out.println(r.numRatings());
            }
        }
        int max = 0; 
        for (Rater r : ratings) {
            if (r.numRatings() > max) {
               max = r.numRatings();
            }
        }
        int num = 0; 
        ArrayList<String> raters = new ArrayList<String>();
        for (Rater r : ratings) {
            if (r.numRatings() == max) {
               num++;
               raters.add(r.getID());
            }
        }
        System.out.println(num + " raters have the maximum # of ratings " + max);
        HashMap<String,Integer> map = new HashMap<String,Integer>(); 
        for (String s : raters) {
            System.out.print(s);
        }
        System.out.println();
        num = 0; 
        for (Rater r : ratings) {
           ArrayList<String> items = r.getItemsRated();
           for (String s : items) {
               if (s.equals("1798709")) {
                  num++;
               }
           }
        }
        System.out.println(num);
        num = 0; 
        ArrayList<String> numItems = new ArrayList<String>();
        for (Rater r : ratings) {
           ArrayList<String> items = r.getItemsRated();
           for (String s : items) {
               if (!numItems.contains(s)) {
                  numItems.add(s);
               }
           }
        }
        System.out.println(numItems.size());
    }

}
