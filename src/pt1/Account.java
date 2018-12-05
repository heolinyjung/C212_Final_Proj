package pt1;

import java.io.*;
import java.util.*;

public class Account 
{
	private File accountInfo; //The file where all the info can be found
	private String username; //The unique username for the account
	private String password; //The password for the specified account, is not unique
	private String id; //The unique id number for the account, mainly for Admin purposes
	private String payment; //The card number for the account to pay for tours
	private TourList reserved; //The list of tour the account has already payed for
	private Cart cart; //The list of tours that the account has yet to pay for, the cart will reset when the program exits
	private boolean admin; //A T or F value to determine if the given account is an admin or not
	private String fullName; //The users real name format: first last
	
	/*
	 * Constructor used for existing accounts
	 */
	/*
	public Account(String username, String number)
	{
		accountInfo = new File(number + username);
		Scanner in = new Scanner(accountInfo);
		id = in.next();
		this.username = in.next();
		password = in.next();
		admin = in.nextBoolean();
		payment = in.next();
		fullName = in.next() + in.next();
		while(in.hasNext())
		{
			reserved.add(in);
		}
		cart = new TourList();
	}
	*/
	/*
	 * Constructor used to when a new account is created
	 */
	/*
	public Account(String username, String password, String number)
	{
		Scanner in = new Scanner(System.in);
		accountInfo = new File(number + username);
		id = number;
		this.username = username;
		password = password;
		admin = false;
		System.out.println("Enter in your payment information: ");
		payment = in.next();
		System.out.println("Please enter your full name (First Last): ");
		fullName = in.next() + in.next();
		reserved = new TourList();
		cart = new TourList();
	}
	*/
	/*
	 * this mehtod will be called when the program terminates
	 * all updated data will thus be saved on to the appropriate file
	 */
	public void exit()
	{
		/*
		 * Write all of the data from this class onto its appropriate file
		 * this will overwrite all previous data on the file
		 * The order will be the same as in the constructor
		 */
	}
	
	/*
	 * the method to add tours to the reserved list
	 */
	public void addTour(Tour tour)
	{
		reserved.add(tour);
	}
	
	/*
	 * Adds tours to the cart to be ready for purchase
	 */
	public void addToCart(Tour tour)
	{
		cart.addToCart(tour);
	}

	/*
	 * The user can change their password at any time
	 */
	public void setPassword(String password) {
		this.password = password;
		/*
		 * update the AccountList signin file
		 */
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

	public TourList getReserved() {
		return reserved;
	}
///////////////////////////////////////////////////////////////////
	
	public boolean isAdmin() {
		return admin;
	}
	
}
