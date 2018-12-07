package pt1;

import java.util.ArrayList;
import java.util.Scanner;

public class Cart 
{
	private final String DIR = Driver.PATH;
	private ArrayList<Integer> cart; //List of tours used to store the tours in the cart
	private Account user; //The current account that the tours in the cart will be added to
	private double total;
	
	public Cart(Account user)
	{
		this.user = user;
		cart = user.getCart();
		total = 0.0;
	}
	
	/*
	 * Confirms the order total and the payment method
	 * Then add the newly purchased tours to the reserved tours list for that account
	 */
	public void checkout()
	{
		System.out.println("Your total is " + total);
		System.out.println("Form of Payment: " + user.getPayment());
		
		for(int x = 0; x < cart.size(); x++)
		{
			int id = cart.get(x);

			user.addTour(id);
		}
		
		Scanner k = new Scanner(System.in);
		String yn = "";
		System.out.println("Would to like a receipt? (Y or N)");
		yn = k.next();
		
		if(yn == "Y" || yn == "y")
			print();
	}
	
	/*
	 * Prints the contents of the cart
	 */
	public void print()
	{
		for(int x = 0; x < cart.size(); x++)
		{
			System.out.println(TourList.getTour(cart.get(x)).getName() + "\t\t" + "$" + TourList.getTour(cart.get(x)).getCost());
			total = total + TourList.getTour(cart.get(x)).getCost();
		}
	}

}
