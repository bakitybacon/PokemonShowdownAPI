package examples;

import java.io.File;
import java.util.Scanner;

public class DonkelHurr 
{
	public static void main(String[]args) throws Exception
	{
		File file = new File("/home/cory/twerkspace/PokemonShowdownAPI/data/moves.js");
		Scanner fr = new Scanner(file);
		String currline = "";
		String type = "";
		String id = "";
		String name = "";
		String desc = "";
		String shortdesc = "";
		String range = "";
		String accuracy = "100";
		String basepower = "40";
		String pp =  "";
		String priority = "0";
		String isContact = "false";
		String usesPhysDef = "false";
		String isPhysical = "false";
		String hasSecondary = "false";
		String hitCount = "new int[]{1}";
		
		while(fr.hasNextLine())
		{
			currline = fr.nextLine();
			if(currline.startsWith("id"))
				id = currline.substring(4,currline.length()-1);
			if(currline.startsWith("name"))
				name = currline.substring(6,currline.length()-1);
			if(currline.startsWith("desc"))
				desc = currline.substring(6,currline.length()-1);
			if(currline.startsWith("shortDesc"))
				shortdesc = currline.substring(11,currline.length()-1);
			if(currline.startsWith("target"))
				range = currline.substring(8,currline.length()-1);
			if(currline.startsWith("accuracy"))
			{
				accuracy = currline.substring(10,currline.length()-1);
				if(accuracy.equals("true"))
					accuracy = "150";
			}
			if(currline.startsWith("basePower:"))
				basepower = currline.substring(11,currline.length()-1);
			if(currline.startsWith("pp"))
				pp = currline.substring(4,currline.length()-1);
			if(currline.startsWith("priority"))
				priority = currline.substring(10,currline.length()-1);
			if(currline.startsWith("category"))
			{
				isPhysical = currline.substring(11,currline.length()-2);
				if(isPhysical.equals("Physical"))
				{
					isPhysical = "true";
					isContact = "true";
					usesPhysDef = "true";
				}
				else
				{
					isPhysical = "false";
					isContact = "false";
					usesPhysDef = "false";
				}
			}
			if(currline.startsWith("defensiveCategory"))
			{
				String defcat = currline.substring(20,currline.length()-2);
				if(defcat.equals("Physical"))
					usesPhysDef = "true";
				else usesPhysDef = "false";
			}
			if(currline.startsWith("isContact"))
				isContact = "true";
			if(currline.startsWith("secondary"))
			{
				if(currline.endsWith("false,"))
					hasSecondary = "false";
				else hasSecondary = "true";
			}
			if(currline.startsWith("multihit:"))
			{
				if(currline.contains("]"))
				{
					String wasgoodnerd = currline.substring(11,currline.length()-2);
					hitCount = "new int[]{"+wasgoodnerd+"}";
				}
				else
				{
					String wasgoodnerd = currline.substring(10,currline.length()-1);
					hitCount = "new int[]{"+wasgoodnerd+"}";
				}
			}
			if(currline.startsWith("type"))
			{
				type = currline.substring(7,currline.length()-1);
				System.out.println("Move "+id.substring(1,id.length()-1)+" = new Move(Type."
						+type+","+id+","+name+","+desc+","+shortdesc+","+getRange(range)+
						","+accuracy+","+basepower+","+pp+","+priority+","+isContact+","+
						usesPhysDef+","+isPhysical+","+hasSecondary+","+hitCount+");"
						);
				hitCount = "new int[]{1}";
			}
		}
		fr.close();
	}
	
	public static String getRange(String s)
	{
		return kek(s);
	}
	static String kek(String trrget)
	{
		trrget = trrget.substring(1,trrget.length()-1);
		switch(trrget)
		{
		case "any":
			return"Range.Any1";
		case "normal":
		case "adjacentFoe":
			return"Range.Adjacent1";
		case "allAdjacentFoes":
			return"Range.Opponent2";
		case "adjacentAllyOrSelf":
			return"Range.AllyOrSelf";
		case "allyTeam":
			return"Range.Allies";
		case "self":
			return"Range.Self";
		case "allAdjacent":
			return"Range.AllAdjacent";
		case "adjacentAlly":
			return"Range.AdjacentAlly";
		case "all":
			return"Range.All";
		case "scripted":
			return"Range.Scripted";
		case "randomNormal":
			return"Range.Random";
		case "allySide":
			return"Range.AllySide";
		case "foeSide":
			return"Range.FoeSide";
		}
		return "HURHURHUR";
	}
}
