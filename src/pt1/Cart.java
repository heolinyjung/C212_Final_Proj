package pt1;

public class Cart 
{
	private TourList cart; //List of tours used to store the tours in the cart
	private Account user; //The current account that the tours in the cart will be added to
	
	public Cart(Account user)
	{
		this.user = user;
		cart = user.cart();
	}
	
	/*
	 * Confirms the order total and the payment method
	 * Then add the newly purchased tours to the reserved tours list for that account
	 */
	public void checkout()
	{
		double total = 0;
		for(int x = 0; x < cart.size; x++)
		{
			total = total + cart.get(x).getPrice();
		}
		System.out.println("Your total is " + total);
		System.out.println("The payment number is " + user.getPayment());
		
		for(int x = 0; x < cart.size; x++)
		{
			user.addTour(cart.get(x));
		}
	}
	
	/*
	 * Prints the contents of the cart
	 */
	public void print()
	{
		System.out.println(cart);
	}

}
