package pt1;

import java.util.ArrayList;

public class Cart
{
	private TourList tourList; //List of tours used to find tour data
    private ArrayList<Tour> tourCart = new ArrayList<>(); //Should we use this?
    private ArrayList<Integer> tourIDs = new ArrayList<>(); //List of tour IDs for all tours in cart

    public Cart(TourList _tourList){
        this.tourList = _tourList;
    }
	
	/*
	 * Confirms the order total and the payment method
	 * Then add the newly purchased tours to the reserved tours list for that account
	 */
	public void checkout()
	{
		double total = 0;
		/*
		for(int x = 0; x < cart.size; x++)
		{
			total = total + cart.get(x).getCost();
		}
		System.out.println("Your total is " + total);
		System.out.println("The payment number is " + user.getPayment());
		
		for(int x = 0; x < cart.size; x++)
		{
			user.addTour(cart.get(x));
		}
		*/
	}

	public void addToCart(int id){
	    Tour tour = tourList.getTour(id);
	    if(tour==null){
	        System.out.println("Not a valid tour");
        }
        else{
            tourIDs.add(id);
            tourCart.add(tour);
        }
    }

    public void addToCart(Tour tour){
	    tourCart.add(tour);
	    tourIDs.add(tour.getIdNumber());
    }

	/*
	 * Prints the contents of the cart
	 */
	public void print()
	{
		System.out.println(tourIDs);
	}

}
