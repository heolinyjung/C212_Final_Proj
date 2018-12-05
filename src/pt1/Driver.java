package pt1;

import java.util.Scanner;

public class Driver 
{
	/*
	 * The startup menu the first thing that the user will see when the program starts
	 */
	public int startup()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("What would you like to do?"
				+ "\n1. Login"
				+ "\n2. Create a new Account"
				+ "\n3. Exit");
		int input = in.nextInt();
		
		return input;
	}
	
	/*
	 * The menu that will appear after the user has successfully logged in
	 * or has created a new account
	 */
	public int accountMenu(Account user)
	{
		Scanner in = new Scanner(System.in);
		int input;
		if(!user.isAdmin())
		{
			System.out.println("What would you like to do?"
					+ "\n1. View Account Info"
					+ "\n2. View/Edit Reserved Tours List"
					+ "\n3. Exit");
			input = in.nextInt();
		}
		else
		{
			System.out.println("What would you like to do?"
					+ "\n1. View Account Info"
					+ "\n2. View/Edit Reserved Tours List"
					+ "\n3. View Account List"
					+ "\n4. Exit");
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
		//Display Options
        return 0;
	}
	
	/*
	 * Menu for when the user is accessing the ToursList
	 */
	public int TourList(Account user)
	{
		//Display Options
        return 0;
	}
	
	/*
	 * Menu for when the user is navigating the Cart
	 */
	public int cart(Account user)
	{
		//Display Options
        return 0;
	}

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in); 
		Driver menu = new Driver();
		AccountList list = new AccountList();
		int start = menu.startup();
		
		/*
		 * The program will use methods to call the menus and each menu will return a value
		 * to the main where it will be read and then call the appropriate action
		 */
		if(start == 1)
		{
			System.out.print("Username: ");
			String username = in.next();
			System.out.print("Password: ");
			String password = in.next();
			int num = list.login(username, password);
			String id = Integer.toString(num);
			//Account user = new Account(username, id);
		}
		else if(start == 2)
		{
			System.out.print("New Username: ");
			String username = in.next();
			System.out.print("New Password: ");
			String password = in.next();
			Account user = list.create(username, password);
		}
		
		while(start != 3)
		{
			/*
			 * Code that calls all other menus to get input values from the user to 
			 * navigate the menus and preform tasks
			 */
		}
		
		

	}

}
