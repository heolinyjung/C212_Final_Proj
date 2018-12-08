package pt1;

////////////////////////////////////////////////////////////
//
//	H212 Final Project
//	Travel Agency: Driver
//
//	Last updated: 12/7/18
//  @author Adam Morrow, Heoliny Jung
//
////////////////////////////////////////////////////////////

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Driver
{
	
	public static final String PATH = "D:\\School FIles\\C212_java\\C212_Final_Proj";
	private static AccountList accounts = new AccountList();
	private static TourList tours = new TourList();
	
	/*
	 * The startup menu the first thing that the user will see when the program starts
	 */
	public Account startup(AccountList accounts) throws IOException
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
					if (num==-1) {
						System.out.print("Try again (1) or exit (2)?");
						int newIn = in.nextInt();
						if (newIn == 2){
							break;
						}
					}
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
				Account user = accounts.create(username, password);
				return user;
			}
			else
				System.out.println("Invalid input");
		}
		for (Tour tour : TourList.getTourObjectList()){
			tours.write(tour);
		}
		return null;
	}
	
	/*
	 * The menu that will appear after the user has successfully logged in
	 * or has created a new account
	 */
	public void accountMenu(Account user) throws IOException
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
				System.out.println("\nWhat would you like to do?"
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
						+ "\nCredit Card Info: " + user.getPayment()+"\n");
						break;
				case 3: reservedTours(user); break;
				case 4: if(user.isAdmin()) {System.out.print(accounts.toString());}break;
				default: System.out.println("Invalid Input"); break;
			}
		}
	}
	
	/*
	 * This menu will allow the user to add/remove tours from their list
	 * or just view them
	 */
	public void reservedTours(Account user) throws IOException
	{
		int input = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("Current Reserved Tours:");
		for (int i = 0; i < user.getReserved().size(); i++) {
			if (tours.getTourIDList().contains(user.getReserved().get(i))){
				System.out.println(user.getReserved().get(i)+" "+tours.getTour(user.getReserved().get(i)).getName());
			}
			else{
				user.removeFromReserved(tours.getTour(user.getReserved().get(i)));
			}
		}
		System.out.println();
		
		while(input != 1)
		{
			System.out.println("What would you like to do?"
					+ "\n1. Go Back"
					+ "\n2. Select Tour"
					+ "\n3. List all Tours"
					+ "\n4. Search Tours"
					+ "\n5. View cart");
			if(user.isAdmin())
					System.out.println("6. Add Tour");
			input = in.nextInt();
		
			switch(input)
			{
				case 1: accountMenu(user); break;
				case 2: selectTour(user); break;
				case 3: for(Tour tour : tours.listToursOrdered(0)) {
					System.out.println("\n"+tour.getIdNumber()+": "+tour.getName());
					if(user.getReserved().contains(tour.getIdNumber())){
						System.out.println("RESERVED");
					}
					if(tour.isCancelled()){
						System.out.println("^^CANCELLED^^");
					}
					else
						System.out.println();
				}System.out.println("\n");break;
				case 4: searchTours();break;
				case 5: user.getCart().viewCart();break;
				case 6: if(user.isAdmin()) {
					Tour newTour = new Tour();
					tours.add(newTour);
					tours.findID(newTour);
					editTour(newTour.getIdNumber());
				} break;
				default: System.out.println("Invalid Input"); break;
			}
		}
	}

	public void searchTours(){
		Scanner in = new Scanner(System.in);
		int input;
		System.out.println("How would you like to search?" +
				"\n1. By keyword" +
				"\n2. By date (earliest to latest)" +
				"\n3. By a specific date" +
				"\n4. By cost (lowest to highest)" +
				"\n5. By status");

		if (in.hasNextInt()){
			input = in.nextInt();
		}
		else{
			input = 6;
		}
		LinkedList<Tour> listTours = new LinkedList<>();
		switch(input){
			case 1 : listTours = tours.search(0);break;
			case 2 : listTours = tours.listToursOrdered(1);break;
			case 3 : listTours = tours.search(1);break;
			case 4 : listTours = tours.listToursOrdered(2);break;
			case 5 : listTours = tours.search(2);break;
			default: System.out.println("Not a valid selection.");
		}
		if (listTours.isEmpty()){
		    System.out.println("No matches found");
        }
        else{
            for(Tour tour : listTours){
                System.out.println(tour.toString()+"\n");
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
		Tour tour = tours.getTour(id);
		System.out.println(tour);

		while (input!=1){
			if(user.isAdmin())
			{
				System.out.println("What would you like to do?"
						+ "\n1. Go Back"
						+ "\n2. Edit Tour"
						+ "\n3. Remove Tour");
				input = in.nextInt();

				switch(input)
				{
					case 1: break;
					case 2: editTour(id); break;
					case 3: tours.remove(id); break;
					default: System.out.println("Invalid Input"); break;
				}
			}
			else
			{
				System.out.println("What would you like to do?"
						+ "\n1. Go Back"
						+ "\n2. Remove Tour from reserved"
						+ "\n3. Add Tour to cart");
				input = in.nextInt();

				switch(input)
				{
					case 1: break;
					case 2: user.removeFromReserved(tour); break;
					case 3: if(!tour.isCancelled()){
						user.addToCart(tour);
					}else{
						System.out.println("Could not add to cart because tour is cancelled.");
					}break;
					default: System.out.println("Invalid Input"); break;
				}
			}
		}
	}
	
	public void editTour(int id)
	{
	    Tour tour;
	    String date = "";
        String itn = "";
        String desc = "";
        double d = 0.0;
        String name = "";
        String loc = "";
        boolean stat = true;
		int input = 0;
		String s = "";
		if(tours.getTourIDList().contains(id)){
		    tour = TourList.getTour(id);
        }
        else{
            tour = new Tour();
        }

		Scanner in = new Scanner(System.in);
		while(input != 1)
		{
			System.out.println("What would you like to do?"
					+ "\n1. Go Back"
					+ "\n2. Change Date"
					+ "\n3. Change Itenerary"
					+ "\n4. Change Description"
					+ "\n5. Change Price"
					+ "\n6. Change Name"
					+ "\n7. Change Location"
                    + "\n8. Change Status");
			input = in.nextInt();
		
			//Complete the switch statement
			switch(input)
			{
				case 1: break;
				case 2:
				    System.out.println("Enter the new Date(YYYYMMDD): ");
					date = in.next();
					in.nextLine();
					break;
				case 3:
					System.out.println("Enter the new Itenerary: ");
					itn = in.nextLine();
					in.nextLine();
					break;
				case 4:
					System.out.println("Enter the new Description: ");
                    desc = in.nextLine();
					in.nextLine();
					break;
				case 5:
					while(d == 0.0)
					{
						System.out.println("Enter the new Price: ");
						d = in.nextDouble();
                        in.nextLine();
					}
					break;
				case 6:
					System.out.println("Enter the new Name: ");
                    name = in.nextLine();
					in.nextLine();
					break;
				case 7:
					System.out.println("Enter the new Location: ");
					loc = in.nextLine();
					in.nextLine();
					break;
                case 8: System.out.println("What is the status of this tour? (cancelled = c, scheduled = s)");
                    s = in.next();
                    if (s.equals("c")){
                        stat = true;
                    }
                    else if (s.equals("s")){
                        stat = false;
                    }
                    else{
                        System.out.println("Not a valid choice");
                    }
                    in.nextLine();break;
				default: System.out.println("Invalid Input"); break;
			}
		}
        tour.setCancelled(stat);
        tour.setCost(d);
        tour.setItinerary(itn);
        tour.setDescription(desc);
        tour.setDate(date);
        tour.setLocation(loc);
        tour.setName(name);
        if (!(tours.getTourIDList().contains(id))){
            tours.findID(tour);
            tours.add(tour);
        }
	}  

	public static void main(String[] args) throws IOException
	{
		Driver menu = new Driver();
		boolean cont = true;
		while(cont)
		{
			Account user = menu.startup(accounts);
			if(user==null){
				break;
			}
			menu.accountMenu(user);
		}
	}
}