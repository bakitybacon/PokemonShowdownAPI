package examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import teambuilder.Range;
import teambuilder.Type;

public class WeedSmokeur 
{
	public static void main(String[]args) throws FileNotFoundException
	{
		File file = new File("/home/cory/twerkspace/PokemonShowdownAPI/data/learnsets-g6.js");
		Scanner fr = new Scanner(file);
		String currline = "";
		boolean pokeNext = true;
		while(fr.hasNextLine())
		{
			currline = fr.nextLine();
			if(pokeNext)
			{
				System.out.print("new Move[]{");
				pokeNext = false;
			}
			else if(currline.endsWith("}}"))
			{
				pokeNext = true;
				System.out.println("Moves."+currline.substring(0,currline.length()-2)+"}");
			}
			else 
			{
				if(currline.equals("\"return\""))
					System.out.print("Moves.Return,");
				else System.out.print("Moves."+currline+",");
			}
		}
		fr.close();
	}
}
