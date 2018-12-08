package pt1;

////////////////////////////////////////////////////////////
//
//	H212 Final Project
//	Travel Agency: Tour
//
//	Last updated: 12/7/18
//  @author Adam Morrow, Heoliny Jung
//
////////////////////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tour {

    /*
    Tour File Format:
    ID NUM
    NAME
    COST
    DESCRIPTION
    LOCATION
    ITINERARY
    DATE
    IS CANCELLED
     */
    private File infoFile;
    private String name;
    private double cost;
    private String description;
    private String location;
    private String itinerary;
    private String date; //In format YYYYMMDD
    private int idNumber;
    private boolean isCancelled;

    /**
     * Constructs a tour object by reading a formatted tour text file.
     * Format should follow format described above.
     *
     * @param infoFile formatted text file
     */
    public Tour(File infoFile) {
        this.infoFile = infoFile;
        try {
            Scanner in = new Scanner(this.infoFile);
            int i = 0;
            while (in.hasNextLine()) {
                switch (i) {
                    case 0:
                        idNumber = Integer.parseInt(in.nextLine().trim());
                    case 1:
                        name = in.nextLine().trim();
                    case 2:
                        cost = Double.parseDouble(in.nextLine().trim());
                    case 3:
                        description = in.nextLine().trim();
                    case 4:
                        location = in.nextLine().trim();
                    case 5:
                        itinerary = in.nextLine().trim();
                    case 6:
                        date = in.nextLine().trim();
                    case 7:
                        isCancelled = Boolean.parseBoolean(in.nextLine().trim());
                }
                i++;
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Constructs a tour object by taking in parameters for all instance variables besides infoFile, ID, and isCancelled.
     * infoFile and idNumber will be determined when it is added to the TourList object.
     * isCancelled defaults to false.
     * Date must be input in YYYYMMDD format
     *
     * @param _name        name of tour
     * @param _cost        total cost of tour
     * @param _description description of tour
     * @param _location    location of tour
     * @param _itinerary   itinerary of tour
     * @param _date        date of tour
     */
    public Tour(String _name, double _cost, String _description, String _location, String _itinerary, String _date) {
        this.name = _name;
        this.cost = _cost;
        this.description = _description;
        this.location = _location;
        this.itinerary = _itinerary;
        this.date = _date;
        this.isCancelled = false;
    }

    public Tour(){}

    //getters and setters. Only admins will be able to set these values.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getItinerary() {
        return itinerary;
    }

    public void setItinerary(String itinerary) {
        this.itinerary = itinerary;
    }

    public String getDate() {
        return date;
    }

    /**
     * Creates and returns a readable, formatted version of the date in form MM/DD/YYYY.
     *
     * @return Formatted string date
     */
    public String getFormattedDate() {
        return date.substring(4, 6) + "/" + date.substring(6) + "/" + date.substring(0, 4);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    /**
     * Returns a readable string tour in this format:
     * (if tour is cancelled) This tour has been cancelled.
     * NAME
     * LOCATION
     * DESCRIPTION
     * DATE
     * ITINERARY
     * COST
     * IDNUM
     *
     * @return formatted tour string
     */
    @Override
    public String toString() {
        String out = "";
        if (isCancelled) {
            out += "This tour has been cancelled.\n";
        }
        out += name + "\n";
        out += location + "\n";
        out += description + "\n";
        out += this.getFormattedDate() + "\n";
        out += itinerary + "\n";
        out += "$" + String.format("%.2f", cost) + "\n";
        out += "Tour number " + idNumber + "\n";
        return out;
    }

    /**
     * Returns equals if ID number match.
     * Assumes no two tours have same IDs.
     *
     * @param other tour object
     * @return is equal
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Tour) {
            Tour otherTour = (Tour) other;
            if (idNumber == otherTour.getIdNumber()) {
                return true;
            } else
                return false;
        }
        else
            return true;
    }
}