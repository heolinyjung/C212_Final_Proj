package pt1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TourList {

    private File toursDirectory;
    private File toursIDFile;
    private File[] toursFileList;
    private ArrayList<Integer> tourIDlist = new ArrayList<>();
    private ArrayList<Tour> tourList;

    /**
     * Creates a new pt1.TourList given a file of pt1.Tour ID's.
     * Reads ID's from file then finds each corresponding tour file in tours directory and constructs new tour from it.
     * Adds tour to tourList. Adds ID to tourIDlist (might want to make that a hashmap?).
     * @param _toursDirectory Directory path for tours folder
     */
    public TourList(File _toursDirectory, File _toursIDFile) {
        try{
            this.toursIDFile = _toursIDFile;
            Scanner in = new Scanner(this.toursIDFile);
            while(in.hasNextLine()){
                String id = in.nextLine();
                id.trim();
                int idNum = Integer.parseInt(id);
                this.tourIDlist.add(idNum);
            }
        }catch(FileNotFoundException e){
            System.out.println("ID file not found");
        }
        System.out.println(tourIDlist.toString());
        this.toursDirectory = _toursDirectory;
        this.toursFileList = this.toursDirectory.listFiles();
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

    /**
     * Adds a tour to the tourList
     * @param tour tour to add to list
     */
    public void add(Tour tour){
        tourList.add(tour);
    }

    /**
     * Refreshes toursFileList when adding or removing tours.
     */
    public void refresh(){
        this.toursFileList = this.toursDirectory.listFiles();
    }

    //test
    public static void main(String[] args){
        File directory = new File("D:\\School FIles\\C212_java\\C212_Final_Proj\\Tours");
        File ids = new File("D:\\School FIles\\C212_java\\C212_Final_Proj\\tourIDs.txt");
        TourList tourlist = new TourList(directory,ids);
    }
}
