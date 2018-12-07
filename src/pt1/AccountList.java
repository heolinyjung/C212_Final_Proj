package pt1;

import java.io.*;
import java.util.*;

public class AccountList 
{
	private final String DIR = Driver.PATH;
	private ArrayList<String> names;	//list of usernames and passwords
	private File signin; 	//Format: ID, Username, Password
							//each new line holds information for one persons account
	private File accounts;	//A variable that has access to all of the files in the accounts directory
	private String[] list;	//A list of all the File names in the accounts directory
	private int num;	//Number of accounts in Dir
	
	public AccountList() throws FileNotFoundException
	{
		accounts = new File(DIR + "\\accountsdir");
		signin = new File("accounts.txt");
		list = accounts.list();
		num = list.length;
		names = new ArrayList<String>();
				
		Scanner in = new Scanner(signin);
		while(in.hasNextLine())
		{
			names.add(in.next());
		}
		in.close();
	}
	
	/*
	 * The login function that will confirm the given password and username
	 * if the username and password are not found it will return a -1
	 * otherwise it will return the id number of that account
	 */
	public int login(String username, String password) throws FileNotFoundException
	{
		int id = -1;
		for(int x = 0; x < names.size()/3; x++)
		{
			String currentID = names.get(x*3);
			String name = names.get(x*3 + 1);
			String pass = names.get(x*3 + 2);
			if(name == username && pass == password)
			{
				id = Integer.parseInt(currentID);
			}
		}
		
		if(id == -1)
			System.out.println("Your username or password was incorrect");
		return id;
	}

	/*
	 * This method is used when a new account is being created
	 * it instantiates an new account and returns the new account
	 */
	public Account create(String username,String password) throws IOException
	{
		String number = "" + num++;
		
		FileWriter writer = new FileWriter(signin, true);
		writer.write(number + " " + username + " " + password);
		writer.close();
		
		Account create = new Account(username, password, number);
		return create;
	}
	
	public int check(String username, String password)
	{
		for(int x = 0; x < names.size(); x++)
		{
			if(password.equals(names.get(x*3+2)) && username.equals(names.get(x*3+1)))
			{
				System.out.println("That name is already taken");
				return -1;
			}
		}
		
		return 1;
	}
	
	/*
	 * prints out all of the usernames and id numbers in the system
	 * This is for Admin access only
	 */
	public void list()
	{
		for(int x = 0; x < list.length; x++)
		{
			System.out.println(list[x]);
		}
	}
	
	/*
	 * This method takes an id number then return the appropriate account 
	 * This is for Admin access only
	 */
	public int select(int n)
	{
		for(int x = 0; x < names.size(); x++)
		{
			if(names.get(x*3).equals(n))
			{
				return 1;
			}
		}
		
		return -1;
	}
}
