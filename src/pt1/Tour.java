package pt1;

import java.io.File;
import java.util.Date;

public class Tour {

    private File infoFile;
    private String name;
    private double cost;
    private String description;
    private String location;
    private String itinerary;
    private Date date;
    private int idNumber;
    private boolean isCancelled;

    /**
     * Constructs a tour object by reading a formatted tour text file
     * @param infoFile formatted text file
     */
    public Tour(File infoFile) {
        this.infoFile = infoFile;
    }

    //getters and setters. Only admins will be able to set these values.
    public String getName() {
        return name;
    }

    public void setName(String name, Account accnt) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost, Account accnt) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description, Account accnt) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location, Account accnt) {
        this.location = location;
    }

    public String getItinerary() {
        return itinerary;
    }

    public void setItinerary(String itinerary, Account accnt) {
        this.itinerary = itinerary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date, Account accnt) {
        this.date = date;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber, Account accnt) {
        this.idNumber = idNumber;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled, Account accnt) {
        isCancelled = cancelled;
    }

    /**
     * Returns a readable, formatted string tour.
     * @return tour string
     */
    @Override
    public String toString(){
        return null;
    }
}
