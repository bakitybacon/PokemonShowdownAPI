package examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JOptionPane;

import server.Format;

public class regexishard 
{

	public static void parseFormats(String formats)
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
				HashMap <String, ArrayList<Format>> steve= new HashMap<String, ArrayList<Format>>();
				System.out.println("Le category title is le: " + categoryTitle);
				steve.put(categoryTitle,kekpot);
				System.out.println(steve);
				kekpot = new ArrayList<>();
				from.add(steve);
				continue;
			}
			Object[] props = getFormatProps(pot);
			kekpot.add(new Format((String)props[0],(Boolean)props[1],(Boolean)props[2],(Boolean)props[3]));
		}
		System.out.println(from);
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
	
	static void popup(String message)
	{
		System.out.println(message.split("\\|",4));
		JOptionPane.showMessageDialog(null,message.split("\\|",4)[2]);
	}
	
	public static void main(String[] yolos)
	{
		parseFormats("|formats|,1|XY Singles|Random Battle,#|Unrated Random Battle,,,#|OU|Ubers|UU|RU (beta)|NU (beta)|LC|XY Battle Spot Singles|XY Battle Spot Special 4|Custom Game,|,1|XY Doubles|Random Doubles Battle,#|Smogon Doubles,|Smogon Doubles (suspect test)|Smogon Doubles Ubers,|Smogon Doubles UU,|XY Battle Spot Doubles|VGC 2014|Doubles Challenge Cup,,#|Doubles Custom Game,|,1|OM of the Month|Almost Any Ability|OU Theorymon|,1|Other Metagames|CAP|Challenge Cup,#|Challenge Cup 1-vs-1,#|Hackmons|Balanced Hackmons|1v1|OU Monotype|Sky Battles|Inverse Battle|Ability Exchange,|Ability Shift,|Alphabet Cup,|Averagemons,|Gen-NEXT OU,|Middle Cup,|STABmons,|[Gen 5] Glitchmons,|,2|BW2 Singles|[Gen 5] OU|[Gen 5] Ubers|[Gen 5] UU|[Gen 5] RU|[Gen 5] NU|[Gen 5] LC|[Gen 5] GBU Singles|[Gen 5] Custom Game,|,2|BW2 Doubles|[Gen 5] Smogon Doubles|[Gen 5] GBU Doubles|[Gen 5] Doubles Custom Game,|,2|Past Generations|[Gen 4] OU (beta)|[Gen 4] Ubers (beta)|[Gen 4] UU (beta)|[Gen 4] LC (beta)|[Gen 4] Custom Game,|[Gen 3] OU (beta)|[Gen 3] Custom Game,|[Gen 2] OU (beta)|[Gen 2] Custom Game,|[Gen 1] OU (beta)|[Gen 1] Custom Game,");
	}
}
