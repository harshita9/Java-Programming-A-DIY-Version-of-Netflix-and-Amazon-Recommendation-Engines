
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings {
    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        int minimalRaters = 35; 
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmovies_short.csv"); 
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
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");
        int minimalRaters = 8; 
        MovieDatabase md = new MovieDatabase();
        md.initialize("ratedmovies_short.csv"); 
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
    
    public void printSimilarRatings() {
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
	    RaterDatabase.initialize("ratings.csv");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
		FourthRatings fr = new FourthRatings();
		String raterID = "337";
		int numSimilarRaters = 10;
		int minimalRaters = 3;
		ArrayList<Rating> ratings = fr.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
	    if (ratings.size() == 0 || ratings.size() == 1) {
	    	System.out.println(ratings.size() + " movie matched");
	    }
	    else {
	    	System.out.println(ratings.size() + " movies matched");
	    }
	    for(int i=0; i< ratings.size(); i++) {
	    	if (i<15) {
	    	    System.out.println(ratings.get(i).getValue() + MovieDatabase.getTitle(ratings.get(i).getItem()));
	    	}
	    }
	}
	
	public void printSimilarRatingsByGenre() {
	    RaterDatabase.initialize("ratings.csv");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
		FourthRatings fr = new FourthRatings();
		String raterID = "964";
		int numSimilarRaters = 20;
		int minimalRaters = 5;
		ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, new GenreFilter("Mystery"));
	    if (ratings.size() == 0 || ratings.size() == 1) {
	    	System.out.println(ratings.size() + " movie matched");
	    }
	    else {
	    	System.out.println(ratings.size() + " movies matched");
	    }
	    for(int i=0; i< ratings.size(); i++) {
	    	if (i<15) {
	    	    System.out.println(ratings.get(i).getValue() + MovieDatabase.getTitle(ratings.get(i).getItem()));
	    	}
	    }
	}
	
	public void printSimilarRatingsByDirector() {
	    RaterDatabase.initialize("ratings.csv");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
		FourthRatings fr = new FourthRatings();
		String raterID = "120";
		int numSimilarRaters = 10;
		int minimalRaters = 2;
		ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
	    if (ratings.size() == 0 || ratings.size() == 1) {
	    	System.out.println(ratings.size() + " movie matched");
	    }
	    else {
	    	System.out.println(ratings.size() + " movies matched");
	    }
	    for(int i=0; i< ratings.size(); i++) {
	    	if (i<15) {
	    	    System.out.println(ratings.get(i).getValue() + MovieDatabase.getTitle(ratings.get(i).getItem()));
	    	}
	    }
	}
	
	public void printSimilarRatingsByGenreAndMinutes() {
	    RaterDatabase.initialize("ratings.csv");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
		FourthRatings fr = new FourthRatings();
		String raterID = "168";
		int numSimilarRaters = 10;
		int minimalRaters = 3;
		MinutesFilter mf = new MinutesFilter(80, 160);
        GenreFilter gf = new GenreFilter("Drama");
        AllFilters af = new AllFilters();
        af.addFilter(mf);
        af.addFilter(gf);
		ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, af);
	    if (ratings.size() == 0 || ratings.size() == 1) {
	    	System.out.println(ratings.size() + " movie matched");
	    }
	    else {
	    	System.out.println(ratings.size() + " movies matched");
	    }
	    for(int i=0; i< ratings.size(); i++) {
	    	if (i<15) {
	    	    System.out.println(ratings.get(i).getValue() + MovieDatabase.getTitle(ratings.get(i).getItem()));
	    	}
	    }
	}
	
	public void printSimilarRatingsByYearAfterAndMinutes() {
	    RaterDatabase.initialize("ratings.csv");
	    System.out.println("read data for " + RaterDatabase.size() + " raters");
	    MovieDatabase.initialize("ratedmoviesfull.csv");
	    System.out.println("read data for " + MovieDatabase.size() + " movies");
		FourthRatings fr = new FourthRatings();
		String raterID = "314";
		int numSimilarRaters = 10;
		int minimalRaters = 5;
		MinutesFilter mf = new MinutesFilter(70, 200);
        YearAfterFilter yf = new YearAfterFilter(1975);
        AllFilters af = new AllFilters();
        af.addFilter(mf);
        af.addFilter(yf);
		ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, af);
	    if (ratings.size() == 0 || ratings.size() == 1) {
	    	System.out.println(ratings.size() + " movie matched");
	    }
	    else {
	    	System.out.println(ratings.size() + " movies matched");
	    }
	    for(int i=0; i< ratings.size(); i++) {
	    	if (i<15) {
	    	    System.out.println(ratings.get(i).getValue() + MovieDatabase.getTitle(ratings.get(i).getItem()));
	    	}
	    }
	}
}
