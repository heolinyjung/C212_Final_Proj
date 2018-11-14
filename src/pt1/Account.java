package pt1;

import java.io.*;
import java.util.*;

public class Account 
{
	private File accountInfo;
	private String name;
	private String id;
	private String payment;
	private TourList reserved;
	private boolean admin;
	
	public Account(String username, String number) throws FileNotFoundException
	{
		name = username;
		id = number;
		accountInfo = new File(id + name);
		Scanner in = new Scanner(accountInfo);
		admin = in.nextBoolean();
		payment = in.next();
		while(in.hasNext())
		{
			//reserved.add(in.next());
		}
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getPayment() {
		return payment;
	}

	public TourList getReserved() {
		return reserved;
	}

	public boolean isAdmin() {
		return admin;
	}
	
}
