package pt1;

import java.util.Scanner;

public class Driver 
{
	/*
	 * The startup menu the first thing that the user will see when the program starts
	 */
	public int startup()
	{
		int input = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("What would you like to do?"
				+ "\n1. Exit"
				+ "\n2. Create a new Account"
				+ "\n3. Login");
		input = in.nextInt();
		
		return input;
	}
	
	/*
	 * The menu that will appear after the user has successfully logged in
	 * or has created a new account
	 */
	public int accountMenu(Account user)
	{
		int input = 0;
		Scanner in = new Scanner(System.in);
		if(!user.isAdmin())
		{
			System.out.println("What would you like to do?"
					+ "\n1. Exit"
					+ "\n2. View Account Info"
					+ "\n3. View/Edit Reserved Tours List");
			input = in.nextInt();
		}
		else
		{
			System.out.println("What would you like to do?"
					+ "\n1. Exit"
					+ "\n2. View Account Info"
					+ "\n3. View/Edit Reserved Tours List"
					+ "\n4. View Account List");
			input = in.nextInt();
		}
		
		return input;
	}
	
	/*
	 * This menu will allow the user to add/remove tours from their list
	 * or just view them
	 */
	public int reservedTours(Account user)
	{
		int input = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("What would you like to do?"
				+ "\n1. Exit"
				+ "\n2. Create a new Account"
				+ "\n3. Login"
				+ "\n4. Go Back");
		input = in.nextInt();
		
		return input;
	}
	
	/*
	 * Menu for when the user is accessing the ToursList
	 */
	public int TourList(Account user)
	{
		int input = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("What would you like to do?"
				+ "\n1. Exit"
				+ "\n2. Create a new Account"
				+ "\n3. Login"
				+ "\n4. Go Back");
		input = in.nextInt();
		
		return input;
	}
	
	/*
	 * Menu for when the user is navigating the Cart
	 */
	public int cart(Account user)
	{
		int input = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("What would you like to do?"
				+ "\n1. Exit"
				+ "\n2. Create a new Account"
				+ "\n3. Login"
				+ "\n4. Go Back");
		input = in.nextInt();
		
		return input;
	}

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in); 
		Driver menu = new Driver();
		AccountList accounts = new AccountList();
		//TourList tours = new TourList();
		int start = 0;
		int input = 0;
		String id = "";
		String username = "";
		String password = "";
		
		
		/*
		 * The program will use methods to call the menus and each menu will return a value
		 * to the main where it will be read and then call the appropriate action
		 */
		while(input != 1 )
		{
			while(start != 1)
			{
				start = menu.startup();
				
				if(start == 2)
				{
					System.out.print("Username: ");
					username = in.next();
					System.out.print("\nPassword: ");
					password = in.next();
					int num = accounts.login(username, password);
					id = Integer.toString(num);
					
				}
				else if(start > 3 || start < 0)
				{
					System.out.println("Not a valid input");
				}
				else
				{
					int check = 0;
					while(check == -1)
					{
					System.out.print("New Username: ");
					username = in.next();
					System.out.print("\nNew Password: ");
					password = in.next();
					check = accounts.check(username, password);
					}
					
				}
				
			}
			
			if(start == 2)
				Account user = accounts.create(username, password);
			else if(start == 3)
				Account user = new Account(username, id);
		}
		
			
				/*
				 * Code that calls all other menus to get input values from the user to 
				 * navigate the menus and preform tasks
				 */
		}
		
		

	}

}
