
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {
    
    private double getAverageByID(String id, int minimalRaters){
        int numRaters = 0;
        ArrayList<Integer> ratings = new ArrayList<Integer>(); 
        RaterDatabase rd = new RaterDatabase();
        rd.initialize("ratings.csv");
        for (Rater r : rd.getRaters()) {
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
    
    private double dotProduct(Rater me, Rater r) {
        double dotProduct = 0;
    	ArrayList<String> myMovies = me.getItemsRated();
    	for (String id: myMovies) {
			if (r.hasRating(id)) {
				double myRating = me.getRating(id);
				double rRating = r.getRating(id);
				myRating -= 5;
				rRating -= 5;
				dotProduct += myRating * rRating;
			}
		}
		return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
         ArrayList<Rating> list = new ArrayList<Rating>();
         Rater me = RaterDatabase.getRater(id);
         for(Rater r : RaterDatabase.getRaters()) {
          if (r != me) {
            // add dot_product(r,me) to list if r != me
            list.add(new Rating (r.getID(), dotProduct(me, r)));
          }
         }
         Collections.sort(list, Collections.reverseOrder());
         return list;
    }
    
        public ArrayList<Rating> getRecommendations(String id, int numRaters) {
    	ArrayList<Rating> list = getSimilarities(id);
    	ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    	for(String movieID: movies) {
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
    		for(int k=0; k < numRaters; k++) {
    			Rating r = list.get(k);
    			String raterID = r.getItem();
    			double weight = r.getValue();
    			Rater myRater = RaterDatabase.getRater(raterID);
    			if(myRater.hasRating(movieID)) {
    				countRaters++;
    				sum += weight * myRater.getRating(movieID);
    			}
    		}
    		weightedAverage = sum / countRaters;
    		res.add(new Rating(movieID, weightedAverage));
    	}
		Collections.sort(res, Collections.reverseOrder());
		return res;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
    	ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<Rating> list = getSimilarities(id);	
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());	
	    for (String movieID : movies) {
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
	    	for (int i = 0; i < numSimilarRaters; i++) {
	    		Rating r = list.get(i);
	    		double weight = r.getValue();
	    		String raterID = r.getItem();
	    		Rater myRater = RaterDatabase.getRater(raterID);
	    		if(myRater.hasRating(movieID)) {
	    			countRaters++;
	    			sum += weight * myRater.getRating(movieID);
	    		}
	    		
	    	}
	    	if (countRaters >= minimalRaters) {
	    		weightedAverage = sum / countRaters;
	    		res.add(new Rating(movieID, weightedAverage));
			}			
	    }
		Collections.sort(res, Collections.reverseOrder());
		return res;		
    }

     public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,
     Filter filterCriteria) {
    	ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<Rating> list = getSimilarities(id);	
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);	
	    for (String movieID : movies) {
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
	    	for (int i = 0; i < numSimilarRaters; i++) {
	    		Rating r = list.get(i);
	    		double weight = r.getValue();
	    		String raterID = r.getItem();
	    		Rater myRater = RaterDatabase.getRater(raterID);
	    		if(myRater.hasRating(movieID)) {
	    			countRaters++;
	    			sum += weight * myRater.getRating(movieID);
	    		}
	    		
	    	}
	    	if (countRaters >= minimalRaters) {
	    		weightedAverage = sum / countRaters;
	    		res.add(new Rating(movieID, weightedAverage));
			}			
	    }
		Collections.sort(res, Collections.reverseOrder());
		return res;		
    }
    
}
