package server;

import java.util.ArrayList;
import java.util.HashMap;

public class Format 
{
	boolean isSearchable;
	boolean isChallengeable;
	boolean isRandom;
	String name;
	public Format(String name, boolean isSearchable, boolean isChallengeable, boolean isRandom)
	{
		this.name = name;
		this.isSearchable = isSearchable;
		this.isChallengeable = isChallengeable;
		this.isRandom = isRandom;
	}
	
	public String toString()
	{
		return getClass().getName() + 
				"[name="+name+
					",searchable="+isSearchable+
					",challengeable="+isChallengeable+
					",random="+isRandom;
	}
	
	public boolean isSearchable()
	{
		return isSearchable;
	}
	public boolean isChallengeable()
	{
		return isChallengeable;
	}
	public boolean isRandom()
	{
		return isRandom;
	}
	
	public String getName()
	{
		return name;
	}
	
	public static ArrayList<HashMap<String,ArrayList<Format>>> parseFormats(String formats)
	{
		String flist = formats.split("\\|",3)[2];
		
		String[] mats = flist.split("\\|");
		
		boolean categoryNext = false;
		ArrayList<HashMap<String,ArrayList<Format>>> from = new ArrayList<>();
		ArrayList<Format> kekpot = new ArrayList<>();
		String categoryTitle = "";
		for(String pot : mats)
		{
			if(categoryNext == true)
			{
				categoryTitle = pot;
				categoryNext = false;
				continue;
			}
			if(pot.matches(",\\d"))
			{
				if(kekpot.size() == 0)
				{
					categoryNext = true;
					continue;
				}
				categoryNext = true;
				HashMap <String, ArrayList<Format>> steve = new HashMap<String, ArrayList<Format>>();
				steve.put(categoryTitle,kekpot);
				kekpot = new ArrayList<>();
				from.add(steve);
				continue;
			}
			Object[] props = getFormatProps(pot);
			kekpot.add(new Format((String)props[0],(Boolean)props[1],(Boolean)props[2],(Boolean)props[3]));
		}
		return from;
	}
	
	public static Object[] getFormatProps(String format)
	{
		boolean isSearchable = true;
		boolean isChallengeable = true;
		boolean isRandom = false;
		
		if(!format.contains(","))
			return new Object[]{format, true, true, false};
		
		if(format.endsWith(",#"))
		{
			isRandom = true;
			format = format.substring(0, format.length() - 2);
		}
		if(format.endsWith(",,"))
		{
			//only searching
			isChallengeable = false;
			format = format.substring(0, format.length() - 2);
		}
		if(format.endsWith(","))
		{
			//only challenging
			isSearchable = false;
			format = format.substring(0, format.length() - 1);
		}
		
		return new Object[]{format, isSearchable, isChallengeable, isRandom};
	}
}
