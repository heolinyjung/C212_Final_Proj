package pt1;

import java.io.*;
import java.util.*;

public class AccountList 
{
	/*
	private File signin; 	//Format: ID, Username, Password
							//each new line holds information for one persons account
	private Directory accounts;		//A variable that has access to all of the files in the accounts directory
	private ArrayList<String> list;	//A list of all the File names in the accounts directory
	*/
	public AccountList()
	{
		/*
	    signin = new File("accounts");
		list = new ArrayList<String>();
		
		Scanner in = new Scanner(signin);
		while()
		{
			list.add();
		}
		*/
	}

	/*
	 * The login function that will confirm the given password and username
	 * if the username and password are not found it will return a -1
	 * otherwise it will return the id number of that account
	 */
	public int login(String username, String password)
	{
	    /*
		int id = -1;
		Scanner in = new Scanner(signin);
		while(in.hasNextLine())
		{
			while(in.hasNext())
			{
				int currentID = in.nextInt();
				String name = in.next();
				String pass = in.next();
				if(name == username && pass == password)
				{
					id = currentID;
				}
			}
		}
		*/
		return 0;

	}

	/*
	 * This method is used when a new account is being created
	 * it instantiates an new account and returns the new account
	 */
	public Account create(String username,String password)
	{

		//int number = 0;
		//for(int x = 0; x < /*(amount of files in the directory)*/; x ++)
		//	number++;
		/*
		 * Add a line to the file signin with the new username, password, and id
		 * Also create a new Account Object with id = number, and of course using the given username and password
		 */
		
		//Account create = new Account(username, password, number);

		return null;
	}
	
	/*
	 * prints out all of the usernames and id numbers in the system
	 * This is for Admin access only
	 */
	public ArrayList<String> list()
	{
		return null;
	}
	
	/*
	 * This method takes an id number then return the appropriate account 
	 * This is for Admin access only
	 */
	public Account select(int n)
	{
	    /*
		Scanner in = new Scanner(signin);
		String account = "";
		for(int x = 0; x < list.size(); x++)
		{
			if(x + 1 == n)
			{
				account = list.get(x);
			}
		}
		*/
		//for(int x = 0; x < /*(amount of files in the directory)*/; x ++)
		{
		//	if(/*Current File in Directory*/ == account)
			{
		//		String num = in.nextInt();
		//		String name = in.next();
		//		return Account(name, num);
			}
		}
		return null;
	}
}
