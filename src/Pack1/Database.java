package Pack1;

import java.util.ArrayList;

public class Database {

	static ArrayList<Petition> petitions=new ArrayList<Petition>();
	private Database()
	{
	}
	
	
	public static void add(Petition p)
	{
		petitions.add(p);
	}
	
	public static ArrayList<Petition> get()
	{
		return petitions;
	}
}
