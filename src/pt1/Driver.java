package pt1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver 
{
	
	public static final String PATH = "";
	private static AccountList accounts = new AccountList();
	private static TourList tours = new TourList();
	
	/*
	 * The startup menu the first thing that the user will see when the program starts
	 */
	public Account startup(AccountList accounts) throws FileNotFoundException
	{
		int input = 0;
		String username = "";
		String password = "";
		String id = "";
		int num = 0;
		
		Scanner in = new Scanner(System.in);
		System.out.println("What would you like to do?"
				+ "\n1. Exit"
				+ "\n2. Login"
				+ "\n3. Create a new Account");
		input = in.nextInt();
		
		while(input != 1)
		{
			if(input == 2)
			{
				while(num <= 0)
				{
					System.out.print("Username: ");
					username = in.next();
					System.out.print("\nPassword: ");
					password = in.next();
					num = accounts.login(username, password);
					id = Integer.toString(num);
				}
				Account user = new Account(username,id);
				return user;
			}
			else if(input == 3)
			{
				int check = 0;
				while(check <= 0)
				{
					System.out.print("New Username: ");
					username = in.next();
					System.out.print("\nNew Password: ");
					password = in.next();
					check = accounts.check(username, password);
				}
				Account user = new Account(username, password,id);
				return user;
			}
			else
				System.out.println("Invalid input");
		}
		return null;
	}
	
	/*
	 * The menu that will appear after the user has successfully logged in
	 * or has created a new account
	 */
	public void accountMenu(Account user)
	{
		int input = 0;
		Scanner in = new Scanner(System.in);
		
		while(input != 1)
		{
			if(!user.isAdmin())
			{
				System.out.println("What would you like to do?"
						+ "\n1. Sign Out"
						+ "\n2. View Account Info"
						+ "\n3. View/Edit Reserved Tours List");
				input = in.nextInt();
			}
			else
			{
				System.out.println("What would you like to do?"
						+ "\n1. Sign Out"
						+ "\n2. View Account Info"
						+ "\n3. View/Edit Tours List"
						+ "\n4. View Account List");
				input = in.nextInt();
			}
			
			switch(input)
			{
				case 1: user.exit(); break;
				case 2: System.out.println("ID: " + user.getId()
						+ "\nUsername: " + user.getUsername() 
						+ "\nPassword: " + user.getPassword()
						+ "\nReal Name: " + user.getFullName()
						+ "\nCredit Card Info: " + user.getPayment());
						break;
				case 3: reservedTours(user); break;
				case 4:if(user.isAdmin()) {accountList();}; break;
				default: System.out.println("Invalid Input"); break;
			}
		}
	}
	
	/*
	 * This menu will allow the user to add/remove tours from their list
	 * or just view them
	 */
	public void reservedTours(Account user)
	{
		int input = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("Current Reserved Tours");
		System.out.println(user.getReserved());
		
		while(input != 1)
		{
			System.out.println("What would you like to do?"
					+ "\n1. Go Back"
					+ "\n2. Select Tour"
					+ "\n3. List all Tours");
			if(user.isAdmin())
					System.out.println("4. Add Tour");
			input = in.nextInt();
		
			switch(input)
			{
				case 1: accountMenu(user); break;
				case 2: selectTour(user); break;
				case 3: tours.list(); break;
				case 4: if(user.isAdmin()) {tours.addTour();} break;
				default: System.out.println("Invalid Input"); break;
			}
		}
	}
	
	public void selectTour(Account user)
	{
		int input = 0;
		int id = 0;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the Tour ID: ");
		id = in.nextInt();
		Tour tour = tours.getTour(id)
		System.out.println(tour);
		
		if(user.isAdmin())
		{
			System.out.println("What would you like to do?"
					+ "\n1. Go Back"
					+ "\n2. List Accounts"
					+ "\n3. Edit Tour"
					+ "\n4. Remove Tour");
			input = in.nextInt();
			
			switch(input)
			{
				case 1: break;
				case 2: System.out.println(tours.listAccounts()); break;
				case 3: editTour(id); break;
				case 4: tours.removeTour(id); break;
				default: System.out.println("Invalid Input"); break;
			}
		}
		else
		{
			System.out.println("What would you like to do?"
					+ "\n1. Go Back"
					+ "\n2. Remove Tour"
					+ "\n3. Add Tour");
			input = in.nextInt();
			
			switch(input)
			{
				case 1: break;
				case 2: user.removeFromReserved(tour); break;
				case 3: user.addToCart(tour); break;
				default: System.out.println("Invalid Input"); break;
			}
		}
	}
	
	
	public void editTour(int id)
	{
		int input = 0;
		Scanner in = new Scanner(System.in);
		String s = "";
		Double d = 0.0;
		while(input != 1)
		{
			System.out.println("What would you like to do?"
					+ "\n1. Go Back"
					+ "\n2. Change Date"
					+ "\n3. Change Itenerary"
					+ "\n4. Change Description"
					+ "\n5. Change Price"
					+ "\n6. Change Name"
					+ "\n7. Change Location");
			input = in.nextInt();
		
			//Complete the switch statement
			switch(input)
			{
				case 1: break;
				case 2:
					while(s.length() != 8)
					{
						System.out.println("Enter the new Date(YYYYMMDD): ");
						s = in.next();
					}
					tours.getTour(id).setDate(s);
					break;
				case 3:
					System.out.println("Enter the new Itenerary: ");
					s = in.nextLine();
					tours.getTour(id).setItenerary(s);
					break;
				case 4:
					System.out.println("Enter the new Description: ");
					s = in.nextLine();
					tours.getTour(id).setDescription(s);
					break;
				case 5:
					while(d > 0.0 || !in.hasNextDouble())
					{
						System.out.println("Enter the new Price: ");
					}
					d = in.nextDouble();
					tours.getTour(id).setPrice(d);
					break;
				case 6:
					System.out.println("Enter the new Name: ");
					s = in.next();
					tours.getTour(id).setName(s);
					break;
				case 7:
					System.out.println("Enter the new Location: ");
					s = in.next();
					tours.getTour(id).setLocation(s);
					break;
				default: System.out.println("Invalid Input"); break;
			}
		}
	}  

	public static void main(String[] args) 
	{
			Driver menu = new Driver();
			Account user = menu.startup(accounts);
			menu.accountMenu(user);
			
			while(user != null)
			{
				user = menu.startup(accounts);
				menu.accountMenu(user);
			}
	}
}