package pt1;

import java.io.*;
import java.util.*;

public class Account 
{
	private final String DIR = Driver.PATH;
	
	private File accountInfo; //The file where all the info can be found
	private String username; //The unique username for the account
	private String password; //The password for the specified account, is not unique
	private String id; //The unique id number for the account, mainly for Admin purposes
	private String payment; //The card number for the account to pay for tours
	private ArrayList<Integer> reserved; //The list of tour ID's the account has already payed for
	private ArrayList<Integer> cart; //The list of tours that the account has yet to pay for, the cart will reset when the program exits
	private boolean admin; //A T or F value to determine if the given account is an admin or not
	private String fullName; //The users real name format: first last
	
	/*
	 * Constructor used for existing accounts
	 */
	public Account(String username, String number) throws FileNotFoundException
	{
		accountInfo = new File(DIR + number + username);
		Scanner in = new Scanner(accountInfo);
		id = in.next();
		this.username = in.next();
		password = in.next();
		admin = in.nextBoolean();
		payment = in.next();
		fullName = in.next() + in.next();
		reserved = new ArrayList<>();
		while(in.hasNext())
		{
			reserved.add(in.nextInt());
		}
		cart = new ArrayList<>();
	}
	
	/*
	 * Constructor used to when a new account is created
	 */
	public Account(String username, String password, String number) throws IOException
	{
		Scanner in = new Scanner(System.in);
		accountInfo = new File(DIR + number + username);
		id = number;
		this.username = username;
		this.password = password;
		admin = false;
		System.out.println("Enter in your payment information: ");
		payment = in.next();
		System.out.println("Please enter your full name (First Last): ");
		fullName = in.next() + in.next();
		reserved = new ArrayList<>();
		cart = new ArrayList<>();
		log();
	}
	
	/*
	 * used to create the file and put the current available account info
	 */
	public void log() throws IOException
	{
		FileWriter write = new FileWriter(accountInfo, false);
		
		write.write(id + " " + username + " " + password + " " + admin + " " + payment + " " + fullName);
		
		write.close();
	}
	
	/*
	 * this mehtod will be called when the program terminates
	 * all updated data will thus be saved on to the appropriate file
	 */
	public void exit() throws IOException
	{
		FileWriter write = new FileWriter(accountInfo, false);
		
		write.write(id + " " + username + " " + password + " " + admin + " " + payment + " " + fullName + "\n");
		
		for(int x = 0; x < reserved.size(); x++)
		{
			write.write(reserved.get(x) + " ");
		}
		
		write.close();
	}
	
	/*
	 * the method to add tours to the reserved list
	 */
	public void addTour(Tour tour)
	{
		reserved.add(tour.getIdNumber());
	}

	public void addTour(int id) {reserved.add(id);}

	public void removeFromReserved(Tour tour){
		reserved.remove(tour.getIdNumber());
	}
	
	/*
	 * Adds tours to the cart to be ready for purchase
	 */
	public void addToCart(Tour tour)
	{
		cart.add(tour.getIdNumber());
	}

	public void removeFromCart(Tour tour){
		cart.remove(tour.getIdNumber());
	}

	/*
	 * The user can change their password at any time
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/*
	 * Methods called when the user views their account information
	 */
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}

	public String getFullName() {
		return fullName;
	}

	public String getId() {
		return id;
	}

	public String getPayment() {
		return payment;
	}

	public ArrayList<Integer> getReserved() {
		return reserved;
	}
	
	public ArrayList<Integer> getCart() {
		return cart;
	}
///////////////////////////////////////////////////////////////////
	
	public boolean isAdmin() {
		return admin;
	}
	
}
