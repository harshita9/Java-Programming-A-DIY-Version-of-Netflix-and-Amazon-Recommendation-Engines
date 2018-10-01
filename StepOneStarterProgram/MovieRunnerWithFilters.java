
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {

    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        int minimalRaters = 35; 
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv"); 
        ArrayList<Rating> ratings = tr.getAverageRatings(minimalRaters);
        Collections.sort(ratings);
        System.out.println("read data for " + md.size() + " movies");
        System.out.println("found " + ratings.size() + " movies");
        //for (Rating r : ratings) {
            //System.out.println(r.getValue() + " " + md.getTitle(r.getItem()));
            //break;
        //}
        //System.out.println(ratings.size());
    }
    
    public void printAverageRatingsByYear() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        int minimalRaters = 20; 
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv"); 
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, new YearAfterFilter(2000));
        Collections.sort(ratings);
        System.out.println("read data for " + md.size() + " movies");
        System.out.println("found " + ratings.size() + " movies");
        //for (Rating r : ratings) {
            //System.out.println(r.getValue() + " " + md.getYear(r.getItem()) + " " + md.getTitle(r.getItem()));
            //break;
        //}
        //System.out.println(ratings.size());
    } 
    
    public void printAverageRatingsByGenre() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        int minimalRaters = 20; 
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv"); 
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, new GenreFilter("Comedy"));
        Collections.sort(ratings);
        System.out.println("read data for " + md.size() + " movies");
        System.out.println("found " + ratings.size() + " movies");
        //for (Rating r : ratings) {
            //System.out.println(r.getValue() + " " + md.getTitle(r.getItem()));
            //System.out.println("    " + md.getGenres(r.getItem()));
            //break;
        //}
        //System.out.println(ratings.size());
    } 
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        int minimalRaters = 5; 
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv"); 
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, new MinutesFilter(105, 135));
        Collections.sort(ratings);
        System.out.println("read data for " + md.size() + " movies");
        System.out.println("found " + ratings.size() + " movies");
        //for (Rating r : ratings) {
            //System.out.println(r.getValue() + " Time: " + md.getMinutes(r.getItem()) + " " + md.getTitle(r.getItem()));
            //break;
        //}
        //System.out.println(ratings.size());
    } 
    
    public void printAverageRatingsByDirectors() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        int minimalRaters = 4; 
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv"); 
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        Collections.sort(ratings);
        System.out.println("read data for " + md.size() + " movies");
        System.out.println("found " + ratings.size() + " movies");
        //for (Rating r : ratings) {
            //System.out.println(r.getValue() + " " + md.getTitle(r.getItem()));
            //System.out.println("    " + md.getDirector(r.getItem()));
            //break;
        //}
        //System.out.println(ratings.size());
    } 
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        int minimalRaters = 8; 
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv"); 
        YearAfterFilter yf = new YearAfterFilter(1990);
        GenreFilter gf = new GenreFilter("Drama");
        AllFilters af = new AllFilters();
        af.addFilter(yf);
        af.addFilter(gf);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, af);
        Collections.sort(ratings);
        System.out.println("read data for " + md.size() + " movies");
        System.out.println("found " + ratings.size() + " movies");
        //for (Rating r : ratings) {
            //System.out.println(r.getValue() + " " + md.getYear(r.getItem()) + " " + md.getTitle(r.getItem()));
            //System.out.println("    " + md.getGenres(r.getItem()));
            //break;
        //}
        //System.out.println(ratings.size());
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        int minimalRaters = 3; 
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmoviesfull.csv"); 
        MinutesFilter mf = new MinutesFilter(90, 180);
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        AllFilters af = new AllFilters();
        af.addFilter(mf);
        af.addFilter(df);
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, af);
        Collections.sort(ratings);
        System.out.println("read data for " + md.size() + " movies");
        System.out.println("found " + ratings.size() + " movies");
        //for (Rating r : ratings) {
            //System.out.println(r.getValue() + " Time: " + md.getMinutes(r.getItem()) + " " + md.getTitle(r.getItem()));
            //System.out.println("    " + md.getDirector(r.getItem()));
            //break;
        //}
        //System.out.println(ratings.size());
    }
}
