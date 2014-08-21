package teambuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Species 
{
	int num;
	String species;
	boolean canEvo;
	Type[] types;
	int[] baseStats;
	Ability[] mainabilities;
	Ability hiddenability;
	ArrayList<Move> moves = null;
	//most of the time we don't need to care about movesets
	//whenever we need them, we'll just open the movesets file
	public Species(int num,String species,boolean canEvo,Type[] types,int[] baseStats,Ability[] mainabilities,Ability hiddenability)
	{
		this.num = num;
		this.species = species;
		this.canEvo = canEvo;
		this.types = types;
		this.baseStats = baseStats;
		this.mainabilities = mainabilities;
		this.hiddenability = hiddenability;
	}
	public boolean canEvolve()
	{
		return canEvo;
	}
	public Type[] getTypes()
	{
		return types;
	}
	public String getName()
	{
		return species;
	}
	public int getDexNum()
	{
		return num;
	}
	public Ability[] getAbilities()
	{
		Ability[] abilities = new Ability[mainabilities.length+1];
		int iterator = 0;
		for(Ability a : mainabilities)
		{
			abilities[iterator++] = a;
		}
		abilities[iterator] = hiddenability;
		return abilities;
	}
	public Ability[] getMainAbilities()
	{
		return mainabilities;
	}
	public Ability getHiddenAbility()
	{
		return hiddenability;
	}
	public int[] getBaseStats()
	{
		return baseStats;
	}
	public boolean isDualType()
	{
		if(types.length == 2)
			return true;
		return false;
	}
	public int getHPStat(int level, int iv, int ev)
	{
		//shedinja catch
		if(baseStats[0]==1)
			return 1;
		//don't use this for hp
		//([iv+(2*Base)+ev/4]*level)/100+5*nature
		double top = iv + (2*baseStats[0])+ev/4.0+100;
		top *= level;
		top /= 100.0;
		top += 10;
		return (int)top;
		//concatenates, which is the desired operation
	}
	/**
	 * A method that takes levels, ivs, evs, and natures
	 * and calculates what the stat (not hp) would be.
	 * @return the stat
	 */
	public int getStat(int which, int level, int iv, int ev,double naturemod)
	{
		//don't use this for hp
		//([iv+(2*Base)+ev/4]*level)/100+5*nature
		double top = iv + (2*baseStats[which])+ev/4.0;
		top *= level;
		top /= 100.0;
		top += 5;
		top *= naturemod;
		return (int)top;
		//concatenates, which is the desired operation
	}
	
	public String getSpecies()
	{
		return species;
	}
	
	public int getStat(int which, int iv, int ev,double naturemod)
	{
		//don't use this for hp
		//([iv+(2*Base)+ev/4]*level)/100+5*nature
		double top = iv + (2*baseStats[which])+ev/4.0;
		top *= 100;
		top /= 100.0;
		top += 5;
		top *= naturemod;
		return (int)top;
		//concatenates, which is the desired operation
	}
	public int getStat(int which, int iv, int ev)
	{
		//don't use this for hp
		//([iv+(2*Base)+ev/4]*level)/100+5*nature
		double top = iv + (2*baseStats[which])+ev/4.0;
		top *= 100;
		top /= 100.0;
		top += 5;
		return (int)top;
		//concatenates, which is the desired operation
	}
	public int getMinStatAtLevel(int which,int level)
	{
		return getStat(which,level,0,0,0.9);
	}
	public int getMaxStatAtLevel(int which,int level)
	{
		return getStat(which,level,31,255,1.1);
	}
	public int getMidStatAtLevel(int which,int level)
	{
		return getStat(which,level,15,124,1);
	}
	public int getMinStat(int which)
	{
		return getStat(which,100,0,0,0.9);
	}
	public int getMaxStat(int which)
	{
		return getStat(which,100,31,255,1.1);
	}
	public int getMidStat(int which)
	{
		return getStat(which,100,15,124,1);
	}
	public ArrayList<Move> getMoves()
	{
		if(moves != null)
			return moves;
		moves = getMovesFromFile();
		return moves;
	}
	public ArrayList<Move> getMovesFromFile()
	{
		ArrayList<Move> moves = new ArrayList<>();
		try
		{
			File f = new File("data/movesets/"+getName().toLowerCase());
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine())
			{
				String line = sc.nextLine();
				if(line.equals("hiddenpower"))
					moves.addAll(Moves.hiddenpowerlist);
				Move move = new Moves().getByID(line);
				moves.add(move);
			}
			sc.close();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		return moves;
	}
	
	public String toString()
	{
		return getClass().getName()+"[dex="+num+",species="+species+",canEvo="+canEvo+",types="+Arrays.toString(types)
				+",base stats="+Arrays.toString(baseStats)+",mainabilities="+Arrays.toString(mainabilities)+",hiddenability="+hiddenability+"]";
		
		/*
		 * int num;
	String species;
	boolean canEvo;
	Type[] types;
	int[] baseStats;
	Ability[] mainabilities;
	Ability hiddenability;
	Move[] moves = null;
		 */
	}
	
	public boolean equals(Species other)
	{
		if(num != other.getDexNum())
			return false;
		if(!species.equals(other.getName()))
			return false;
		if(canEvo != other.canEvolve())
			return false;
		if(!Arrays.equals(types,other.getTypes()))
			return false;
		if(!Arrays.equals(baseStats,other.getBaseStats()))
			return false;
		if(!Arrays.equals(mainabilities,other.getMainAbilities()))
			return false;
		if(hiddenability == null)
		{
			if(other.getHiddenAbility() == null)
				return true;
			return false;
		}
		if(!hiddenability.equals(other.getHiddenAbility()))
			return false;
		return true;
	}
	//public Species(int num,String species,boolean canEvo,Type[] types,int[] baseStats,Ability[] mainabilities,Ability hiddenability)
	
	public static void main(String[]args)
	{
		Species garch = new SpeciesList().garchomp;
		System.out.println(garch.getHPStat(78,12,195));
	}
}
