package pt1;

////////////////////////////////////////////////////////////
//
//	H212 Final Project
//	Travel Agency: TourList
//
//	Last updated: 12/7/18
//  @author Adam Morrow, Heoliny Jung
//
////////////////////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class TourList {

    private static String dirPath = Driver.PATH+"\\Tours";
    private static File toursDirectory;
    private static ArrayList<Tour> tourList = new ArrayList<>();
    private static ArrayList<Integer> tourIDlist = new ArrayList<>();

    /**
     * Creates a new ArrayList tourIDList given a file of pt1.Tour ID's.
     * Reads ID's from file then adds each one to the ArrayList.
     * Instantiates a toursDirectory file and a toursIDFile file as instance variables
     */
    public TourList() {
        File directory = new File(dirPath);
        toursDirectory = directory;
        for (File tourFile : directory.listFiles()) {
            Tour tour = new Tour(tourFile);
            tourList.add(tour);
            tourIDlist.add(tour.getIdNumber());
        }
    }

    ///////////////ArrayList TourList Methods//////////////////

    /**
     * Gets a tour from the tourList using it's ID number.
     *
     * @param id id number of tour
     * @return tour with that ID number
     */
    public static Tour getTour(int id) {
        for (Tour tour : tourList) {
            if (tour.getIdNumber() == id) {
                return tour;
            }
        }
        System.out.println("No such tour found");
        return null;
    }

    public ArrayList<Integer> getTourIDList(){
        return tourIDlist;
    }


    public static ArrayList<Tour> getTourObjectList() {
        return tourList;
    }

    /**
     * Adds a tour object to this tourList.
     *
     * @param tour
     */
    public void add(Tour tour) {
        tourList.add(tour);
        tourIDlist.add(tour.getIdNumber());
    }

    /**
     * Removes a tour object from this tourList.
     *
     * @param tour
     */
    public void remove(Tour tour) {
        tourList.remove(tour);
        tourIDlist.remove(tour.getIdNumber());
    }

    /**
     * Removes a tour object from this tourList
     *
     * @param id
     */
    public void remove(int id) {
        for (Tour tour : tourList) {
            if (tour.getIdNumber() == id) {
                tourList.remove(tour);
                tourIDlist.remove(id);
            }
        }
    }

    /**
     * Generates a list of tours that contain the keyword provided in the chosen field.
     * The keyword is obtained after the function call and depends on what option is selected.
     * 0 = keyword
     * 1 = date
     * 2 = status
     *
     * @param index
     * @return
     */
    public LinkedList<Tour> search(int index) {
        LinkedList<Tour> tourQueue = new LinkedList<>();
        Scanner in = new Scanner(System.in);
        if (index == 0) {
            System.out.print("Please enter the keyword to search for: ");
            String word = in.next();
            for (Tour tour : tourList) {
                if (tour.getName().toLowerCase().equals(word.toLowerCase())) {
                    tourQueue.add(tour);
                } else if (tour.getName().toLowerCase().contains(word.toLowerCase())
                        || tour.getDescription().toLowerCase().contains(word.toLowerCase())
                        || tour.getLocation().toLowerCase().contains(word.toLowerCase())) {
                    tourQueue.add(tour);
                } else if (word.toLowerCase().contains(tour.getName().toLowerCase())) {
                    tourQueue.add(tour);
                }
            }
        } else if (index == 1) {
            String date = "";
            System.out.print("Enter year: ");
            date += in.next();
            System.out.print("Enter month: ");
            date += in.next();
            System.out.print("Enter day: ");
            date += in.next();

            for (Tour tour : tourList) {
                if (tour.getDate().equals(date)) {
                    tourQueue.add(tour);
                }
            }
        } else if (index == 2) {
            String statString = "";
            System.out.print("Enter to display:" +
                    "\n1 = scheduled tours" +
                    "\n2 = cancelled tours");
            statString += in.next();

            if (statString.equals("1")) {
                for (Tour tour : tourList) {
                    if (!tour.isCancelled()) {
                        tourQueue.add(tour);
                    }
                }
            } else if (statString.equals("2")) {
                for (Tour tour : tourList) {
                    if (tour.isCancelled()) {
                        tourQueue.add(tour);
                    }
                }
            } else {
                System.out.println("Not a valid input");
            }
        }
        return tourQueue;
    }

    /**
     * Generates a list of all tours in a certain order determined by the index.
     * Use poll() method on return to get tours in correct order.
     * 0 = id number
     * 1 = date
     * 2 = cost
     *
     * @param index type of search
     * @return LinkedList (Queue) of tours
     */
    public LinkedList<Tour> listToursOrdered(int index) {
        LinkedList<Tour> tourQueue = new LinkedList<>();
        if (index == 0) {
            Collections.sort(tourIDlist);
            for (int i : tourIDlist) {
                for (Tour tour : tourList) {
                    if (tour.getIdNumber() == i) {
                        tourQueue.add(tour);
                    }
                }
            }
        } else if (index == 1) {
            ArrayList<Integer> dates = new ArrayList<>();
            for (Tour tour : tourList) {
                dates.add(Integer.parseInt(tour.getDate()));
            }
            Collections.sort(dates);
            for (int i : dates) {
                for (Tour tour : tourList) {
                    if (Integer.parseInt(tour.getDate()) == i) {
                        tourQueue.add(tour);
                    }
                }
            }
        } else if (index == 2) {
            ArrayList<Double> costs = new ArrayList<>();
            for (Tour tour : tourList) {
                costs.add(tour.getCost());
            }
            Collections.sort(costs);
            for (double i : costs) {
                for (Tour tour : tourList) {
                    if (tour.getCost() == i) {
                        tourQueue.add(tour);
                    }
                }
            }
        }
        return tourQueue;
    }

    /**
     * returns the list of tours as a readable string
     *
     * @return readable list
     */
    @Override
    public String toString() {
        String out = "";
        try {
            for (File i : toursDirectory.listFiles()) {
                Scanner fileIn = new Scanner(i);
                out += "Tour " + fileIn.nextLine().trim() + ": " + fileIn.nextLine().trim() + "\n";
                fileIn.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return out;
    }

    ///////////////TourList File Methods//////////////////

    private void setDIR(String dirPath){

    }

    /**
     * Adds a tour as a new tour formatted file to the folder of the tour directory.
     * Uses largest id number + 1 as new id number.
     * This function must be called to give a tour an id number.
     *
     * @param tour tour to add to directory
     */
    public static void findID(Tour tour) {
        Collections.sort(tourIDlist);
        int id = tourIDlist.get(tourIDlist.size() - 1) + 1;
        tour.setIdNumber(id);
    }

    public void write(Tour tour) {
        int id = tour.getIdNumber();
        File tourFile = new File(dirPath + "\\" + id + ".txt");
        try {
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
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}