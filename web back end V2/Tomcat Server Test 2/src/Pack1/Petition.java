package Pack1;

import java.util.ArrayList;

public class Petition {

	
	public String title;
	public String description;
	public String category;
	public long target;
	public long votes;
	public ArrayList<String> tags;
	public String name;
	public String email;
	
	public Petition(String cat,String titl,String desc, long trg,long v,ArrayList<String> tgs,String nm,String eml)
	{
		category=cat;
		title=titl;
		description=desc;
		votes=v;
		target=trg;
		name=nm;
		email=eml;
		tags=tgs;
	}
	
	public void getTags()
	{
		for(int i=0;i<tags.size();i++)
			System.out.println(tags.get(i));
	}
}
