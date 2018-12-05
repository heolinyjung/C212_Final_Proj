package pt1;

import java.io.*;
import java.util.*;

public class Account 
{
	private final String ACCOUNTDIR = "c:\\School Stuff\\CS Intro to Software Systems\\Eclipse\\Java Projects\\FinalProject\\accountsDir\\";
	
	private File accountInfo; //The file where all the info can be found
	private String username; //The unique username for the account
	private String password; //The password for the specified account, is not unique
	private String id; //The unique id number for the account, mainly for Admin purposes
	private String payment; //The card number for the account to pay for tours
	private TourList reserved; //The list of tour the account has already payed for
	private TourList cart; //The list of tours that the account has yet to pay for, the cart will reset when the program exits
	private boolean admin; //A T or F value to determine if the given account is an admin or not
	private String fullName; //The users real name format: first last
	
	/*
	 * Constructor used for existing accounts
	 */
	public Account(String username, String number) throws FileNotFoundException
	{
		accountInfo = new File(ACCOUNTDIR + number + username);
		Scanner in = new Scanner(accountInfo);
		id = in.next();
		this.username = in.next();
		password = in.next();
		admin = in.nextBoolean();
		payment = in.next();
		fullName = in.next() + in.next();
		/*
		while(in.hasNext())
		{
			reserved.add(in);
		}
		cart = new TourList();
		*/
	}
	
	/*
	 * Constructor used to when a new account is created
	 */
	public Account(String username, String password, String number)
	{
		Scanner in = new Scanner(System.in);
		accountInfo = new File(ACCOUNTDIR + number + username);
		id = number;
		this.username = username;
		this.password = password;
		admin = false;
		System.out.println("Enter in your payment information: ");
		payment = in.next();
		System.out.println("Please enter your full name (First Last): ");
		fullName = in.next() + in.next();
		//reserved = new TourList();
		//cart = new TourList();
		log();
	}
	
	/*
	 * used to create the file and put the current available account info
	 */
	public void log()
	{
		FileWriter write = new FileWriter(accountInfo, false);
		
		write.write(id + " " + username + " " + password + " " + admin + " " + payment + " " + fullName);
		
		write.close();
	}
	
	/*
	 * this mehtod will be called when the program terminates
	 * all updated data will thus be saved on to the appropriate file
	 */
	public void exit()
	{
		FileWriter write = new FileWriter(accountInfo, false);
		
		write.write(id + " " + username + " " + password + " " + admin + " " + payment + " " + fullName + "\n");
		
		for(int x = 0; x < reserved.getList().size(); x++)
		{
			write.write(reserved.getList().get(x) + " ");
		}
		
		write.close();
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
		cart.add(tour);
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

	public TourList getReserved() {
		return reserved;
	}
	
	public TourList getCart() {
		return cart;
	}
///////////////////////////////////////////////////////////////////
	
	public boolean isAdmin() {
		return admin;
	}
	
}
