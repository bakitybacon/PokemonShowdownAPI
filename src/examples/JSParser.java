package examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JSParser 
{
	public static void main(String[]args) throws FileNotFoundException
	{
		File file = new File("/home/cory/twerkspace/PokemonShowdownAPI/data/pokedex.js");
		File kekpot = new File("/home/cory/twerkspace/PokemonShowdownAPI/data/nfe.txt");
		File opperpowered = new File("/home/cory/twerkspace/PokemonShowdownAPI/bin/swag.txt");
		Scanner fr = new Scanner(file);
		Scanner potkek = new Scanner(kekpot);
		Scanner overpwrd = new Scanner(opperpowered);
		String num = "";
		String species = "";
		boolean inTypes = false;
		boolean inAbilities = false;
		String ha = "null";
		String baseStats = "";
		String swagString = "";
		while(fr.hasNextLine())
		{
			String currline = fr.nextLine();
			if(inAbilities)
			{
				if(currline.equals("},"))
				{
					inAbilities = false;
				}
				if(currline.startsWith("0"))
				{
					if(currline.endsWith(","))
						swagString += "Abilities."+currline.substring(4,currline.length()-2).toLowerCase().replaceAll("[^a-z]","");
					else 
						{
							swagString += "Abilities."+currline.substring(4,currline.length()-1).toLowerCase().replaceAll("[^a-z]","")+"},null,";
							swagString += overpwrd.nextLine() + ");";
						}
					currline = fr.nextLine();
					if(currline.startsWith("1"))
					{
						if(currline.endsWith(","))
							swagString += ",Abilities."+currline.substring(4,currline.length()-2).toLowerCase().replaceAll("[^a-z]","")+"},";
						else 
							swagString += ",Abilities."+currline.substring(4,currline.length()-1).toLowerCase().replaceAll("[^a-z]","")+"},";
						currline = fr.nextLine();
						if(currline.startsWith("H"))
						{
							swagString += "Abilities." +currline.substring(4,currline.length()-1).toLowerCase().replaceAll("[^a-z]",""+",");
							swagString += overpwrd.nextLine() + ");";
						}
						else 
						{
							swagString += "null,";
							swagString += overpwrd.nextLine() + ");";
							inAbilities = false;
						}
					}
					else if(currline.startsWith("H"))
					{
						swagString += "}, Abilities." +currline.substring(4,currline.length()-1).toLowerCase().replaceAll("[^a-z]","")+",";
						swagString += overpwrd.nextLine() + ");";
					}
					inAbilities = false;
					System.out.println(swagString);
					swagString = "";
				}
			}
			if(inTypes)
			{
				if(currline.equals("],"))
					inTypes = false;
				else if(currline.endsWith(","))
				{
					swagString += ("Type."+currline.substring(1,currline.length()-2)+",");
				}
				else swagString += ("Type."+currline.substring(1,currline.length()-1)+"},");
			}
			if(currline.startsWith("types:"))
			{
				inTypes = true;
				swagString += "new Type[]{";
			}
			if(currline.startsWith("hp:"))
				baseStats += "new int[]{"+currline.substring(4,currline.length()-1)+",";
			if(currline.startsWith("atk"))
				baseStats += currline.substring(5,currline.length()-1)+",";
			if(currline.startsWith("def"))
				baseStats += currline.substring(5,currline.length()-1)+",";
			if(currline.startsWith("spa"))
				baseStats += currline.substring(5,currline.length()-1)+",";
			if(currline.startsWith("spd"))
				baseStats += currline.substring(5,currline.length()-1)+",";
			if(currline.startsWith("spe:"))
			{
				baseStats += currline.substring(5,currline.length())+"},";
				swagString += (baseStats);
				baseStats = "";
			}
			if(currline.startsWith("num"))
			{
				num = currline.substring(5,currline.length()-1);
				swagString += ("new Species("+num+",");
			}
			if(currline.startsWith("species"))
			{
				species = currline.substring(10,currline.length()-2);
				swagString += ("\""+species+"\",");
				String speciessan = species.replaceAll("[^A-Za-z]","");
				speciessan = speciessan.toLowerCase();
				swagString = "public static final Species " + speciessan + " = " + swagString;
				String kek = species.substring(0,1).toUpperCase()+species.substring(1,species.length());
				boolean found = false;
				while(potkek.hasNextLine())
				{
					if(potkek.nextLine().equals(kek))
					{
						found = true;	
						break;
					}
				}
				potkek = new Scanner(kekpot);
				swagString += (found+",");
			}
			if(currline.startsWith("abilities:"))
			{
				swagString += "new Ability[]{";
				inAbilities = true;
			}
		}
		fr.close();
		potkek.close();
	}
}
