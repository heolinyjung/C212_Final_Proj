package pt1;

////////////////////////////////////////////////////////////
//
//	H212 Final Project
//	Travel Agency: Cart
//
//	Last updated: 12/7/18
//  @author Adam Morrow, Heoliny Jung
//
////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Scanner;

public class Cart 
{
	private ArrayList<Integer> cart = new ArrayList<>(); //List of tour IDs used to store the tours in the cart
	private Account user; //The current account that the tours in the cart will be added to
	private double total;
	
	public Cart(Account user)
	{
		this.user = user;
		total = 0.0;
	}

	public void viewCart(){
	    Scanner in = new Scanner(System.in);
		print();
		if(!cart.isEmpty()){
            System.out.println("What would you like to do?" +
                    "\n1. Go back" +
                    "\n2. Remove a tour from cart" +
                    "\n3. Checkout");
            if(in.hasNextInt()){
                int choice = in.nextInt();
                switch (choice){
                    case 2: System.out.print("Enter the ID of the tour to remove: ");
                        choice = in.nextInt();
                        if(cart.contains(choice)){
                            cart.remove((Integer)choice);
                        }
                        else
                            System.out.println("No such ID number found");
                        break;
                    case 3: checkout();break;
                    default: System.out.println("Not a valid option");
                }
            }
        }
	}

	public void addToCart(int e){
	    cart.add(e);
    }

    public void remove(int e){
	    cart.remove(e);
    }

    public ArrayList getList(){
	    return this.cart;
    }
	
	/*
	 * Confirms the order total and the payment method
	 * Then add the newly purchased tours to the reserved tours list for that account
	 */
	public void checkout()
	{
		for(int x = 0; x < cart.size(); x++)
		{
			int id = cart.get(x);

			if(user.getReserved().contains(id)){
			    System.out.println(TourList.getTour(id).getName()+": You have already reserved a spot on this tour!");
			    cart.remove(id);
            }
            else
			    user.addTour(id);
		}
        System.out.println("Your total is " + getTotal());
        System.out.println("Form of Payment: " + user.getPayment());
		
		Scanner k = new Scanner(System.in);
		String yn = "";
		System.out.println("Would to like a receipt? (Y or N)");
		yn = k.next();
		
		if(yn.equals("Y") || yn.equals("y"))
			printRecipt();
		cart.clear();
	}
	
	/*
	 * Prints the contents of the cart
	 */
	public void print()
	{
	    if (cart.isEmpty()){
	        System.out.println("Your cart is empty.");
        }
        else{
            for(int x = 0; x < cart.size(); x++)
            {
                System.out.println(TourList.getTour(cart.get(x)).getName()+ "\t\t" + "$" + TourList.getTour(cart.get(x)).getCost()+"\n"+"ID: "+TourList.getTour(cart.get(x)).getIdNumber());
                System.out.printf("Subtotal: $%.2f\n",getTotal());
                total = 0;
            }
        }
	}

	public double getTotal(){
        for(int x = 0; x < cart.size(); x++){
            total += TourList.getTour(cart.get(x)).getCost();
        }
        return total;
    }

	public void printRecipt(){
	    total = 0;
		for(int x = 0; x < cart.size(); x++)
		{
            System.out.println(TourList.getTour(cart.get(x)).getName()+"\n"+ "\t\t" + "$" + TourList.getTour(cart.get(x)).getCost());
		}
		System.out.println("Your total is: $"+getTotal());
        total = 0;
	}
}
