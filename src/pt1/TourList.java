package pt1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class TourList {

    private final String directoryString;
    private File toursDirectory;
    private File toursIDFile;
    private ArrayList<Integer> tourIDlist = new ArrayList<>();

    /**
     * Creates a new ArrayList tourIDList given a file of pt1.Tour ID's.
     * Reads ID's from file then adds each one to the ArrayList.
     * Instantiates a toursDirectory file and a toursIDFile file as instance variables
     * @param _toursDirectory Directory path for tours folder
     * @param _toursIDFile File path for tours ID file
     */
    public TourList(String _toursDirectory, String _toursIDFile) {
        directoryString = _toursDirectory;
        File directory = new File(_toursDirectory);
        File ids = new File(_toursIDFile);
        try{
            this.toursIDFile = ids;
            Scanner inIDs = new Scanner(this.toursIDFile);
            while(inIDs.hasNextLine()){
                String id = inIDs.nextLine();
                id.trim();
                int idNum = Integer.parseInt(id);
                this.tourIDlist.add(idNum);
            }
            inIDs.close();
            toursDirectory = directory;
        }catch(FileNotFoundException e){
            System.out.println("ID file not found");
        }
    }

    /**
     * Gets a tour using it's ID number.
     * @param id id number of tour
     * @return tour with that ID number
     */
    public Tour getTour(int id){
        if(tourIDlist.contains(id)){
            File tourFile = new File(toursDirectory+"\\"+id+".txt");
            Tour tour = new Tour(tourFile);
            return tour;
        }
        else{
            System.out.println("No such tour found");
            return null;
        }
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
        return null;
    }

    /**
     * returns the list of tours as a readable string
     * @return readable list
     */
    @Override
    public String toString(){
        String out = "";
        try{
            for(File i : toursDirectory.listFiles()){
                Scanner fileIn = new Scanner(i);
                out+="Tour "+fileIn.nextLine().trim()+": "+fileIn.nextLine().trim()+"\n";
                fileIn.close();
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return out;
    }

    /**
     * Adds a tour to the tour directory as a file.
     * @param tour tour to add to list
     */
    public void add(Tour tour){
        int id = tourIDlist.get(tourIDlist.size()-1)+1;
        tourIDlist.add(id);
        File tourFile = new File(directoryString+"\\"+id+".txt");
        tour.setIdNumber(id);
        try{
            PrintWriter writer = new PrintWriter(tourFile);
            writer.println(tour.getIdNumber());
            writer.println(tour.getName());
            writer.println(tour.getCost());
            writer.println(tour.getDescription());
            writer.println(tour.getLocation());
            writer.println(tour.getItinerary());
            writer.println(tour.getDate());
            writer.println(tour.isCancelled());
            writer.close();
            writeIDs();
        }catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeIDs(){
        try{
            PrintWriter writer = new PrintWriter(toursIDFile);
            ArrayList<Integer> ids = new ArrayList<>();
            for (File i : toursDirectory.listFiles()){
                Scanner fileIn = new Scanner(i);
                int id = Integer.parseInt(fileIn.nextLine().trim());
                ids.add(id);
            }
            Collections.sort(ids);
            for (int i : ids){
                writer.println(i);
            }
            writer.close();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    //test
    public static void main(String[] args){
        //When testing make sure to input your own file paths
        TourList tourlist = new TourList("D:\\School FIles\\C212_java\\C212_Final_Proj\\Tours","D:\\School FIles\\C212_java\\C212_Final_Proj\\tourIDs.txt");
        System.out.println(tourlist.directoryString);
        System.out.println("Please enter tour info");
        Tour newTour = new Tour("Hiking in Siberia",2000.00,"The Hiking in Siberia tour is for only the most experienced backpackers and outdoorsmen. Come venture through settings rarely seen by the human eye as you are enveloped by the Siberian tundra.","Yakutsk, Russia","itinerary","20190815");
        tourlist.writeIDs();
        System.out.printf(newTour.toString());
        System.out.println(newTour.getDate());
        System.out.println(tourlist.toString());
        //tourlist.add(newTour);
        //System.out.printf(tourlist.getTour(tourlist.tourIDlist.get(tourlist.tourIDlist.size()-1)).toString());
    }
}
