import java.io.File;
import java.util.ArrayList;

public class TourList {

    private File toursFile;
    private ArrayList<Integer> tourIDlist;
    private ArrayList<Tour> tourList;

    /**
     * Creates a new TourList given a file of Tour ID's.
     * Reads ID's from file then finds each corresponding tour file in tours directory and constructs new tour from it.
     * Adds tour to tourList. Adds ID to tourIDlist (might want to make that a hashmap?).
     * @param toursFile file of tour IDs
     */
    public TourList(File toursFile) {
        this.toursFile = toursFile;
    }

    /**
     * Gets a tour using it's ID number.
     * @param id id number of tour
     * @return tour with that ID number
     */
    public Tour selectTour(int id){
        return null;
    }

    /**
     * Generates a list of tours in a certain order determined by the index.
     * 0 = id number (list all)
     * 1 = name
     * 2 = date
     * 3 = location
     * 4 = cost
     * 5 = status (cancelled or not)
     * @param index type of search
     * @return list of tours
     */
    public ArrayList list(int index){
        return tourIDlist;
    }

    /**
     * returns the list of tours as a readable string
     * @return readable list
     */
    @Override
    public String toString(){
        return null;
    }
}
