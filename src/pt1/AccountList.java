package pt1;

////////////////////////////////////////////////////////////
//
//	H212 Final Project
//	Travel Agency: AccountList
//
//	Last updated: 12/7/18
//  @author Adam Morrow, Heoliny Jung
//
////////////////////////////////////////////////////////////

import java.io.*;
import java.util.*;

public class AccountList
{
	private final String DIR = Driver.PATH + "\\accountsdir";
	private ArrayList<String> names;	//list of usernames and passwords
	private File signin; 	//Format: ID, Username, Password
							//each new line holds information for one persons account
	private File accounts;	//A variable that has access to all of the files in the accounts directory
	private String[] list;	//A list of all the File names in the accounts directory
	private int num;	//Number of accounts in Dir
	
	public AccountList()
	{
		try{
			accounts = new File(DIR);
			signin = new File("accounts.txt");
			list = accounts.list();
			num = list.length;
			names = new ArrayList<>();

			Scanner in = new Scanner(signin);
			while(in.hasNextLine())
			{
				if(in.hasNext()){
					names.add(in.next());
				}
				else{
					break;
				}
			}
			in.close();
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * The login function that will confirm the given password and username
	 * if the username and password are not found it will return a -1
	 * otherwise it will return the id number of that account
	 */
	public int login(String username, String password)
	{
		int id = -1;
		for(int x = 0; x < names.size()/3; x++)
		{
			String currentID = names.get(x*3);
			String name = names.get(x*3 + 1);
			String pass = names.get(x*3 + 2);
			if(name.equals(username) && pass.equals(password))
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
		num++;
		String number = "" + num;
		
		FileWriter writer = new FileWriter(signin,true);
		writer.write(number + " " + username + " " + password+"\n");
		writer.close();
		
		Account create = new Account(username, password, number);
		return create;
	}
	
	public int check(String username, String password)
	{
		for(int x = 0; x < names.size(); x++)
		{
			if(!((x*3+2)>names.size())){
				if(password.equals(names.get(x*3+2)) && username.equals(names.get(x*3+1)))
				{
					System.out.println("That name is already taken");
					return -1;
				}
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

	/*
	public void deleteAccount(int n){
		File file = new File(DIR+"\\"+n+names.get(n)+".txt");
		if (file.delete()){
			System.out.println("Account deleted successfully");
		}
		else{
			System.out.println("Failed to delete, no such account found");
		}
	}
	*/

	public String toString(){
		String out = "";
		for(String filename : list){
			out+=filename.substring(0,filename.length()-4)+"\n";
		}
		return out;
	}
}
