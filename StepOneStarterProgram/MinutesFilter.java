
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int minMin;
    private int maxMin;
    
    public MinutesFilter(int minMin1, int maxMin1) {
       minMin = minMin1;
       maxMin = maxMin1; 
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= minMin && MovieDatabase.getMinutes(id) <= maxMin;
    }
}
