package teambuilder;

import java.util.ArrayList;
import java.util.Arrays;


public class Pokemon 
{
	String nick;
	int level;
	Item item;
	int[] stats;
	Species species;
	ArrayList<Status> status = new ArrayList<>();
	int currentHP;
	int[] boosts;
	Ability ability;
	Gender gender;
	ArrayList<Move> moves;
	
	public Pokemon(Species species, String nick, Ability ability, Gender gender, int level, Item item, int[] stats, int currentHP, int[] boosts, ArrayList<Move> moves)
	{
		this.species = species;
		this.nick = nick;
		this.level = level;
		this.item = item;
		this.stats = stats;
		status.add(Status.Normal);
		this.currentHP = currentHP;
		this.boosts = boosts;
		this.ability = ability;
		this.gender = gender;
		this.moves = moves;
	}
	public Pokemon(Species species, Ability ability, Gender gender, int level, Item item, int[] stats, int currentHP, int[] boosts, ArrayList<Move> moves)
	{
		this.species = species;
		this.nick = species.getSpecies();
		this.level = level;
		this.item = item;
		this.stats = stats;
		status.add(Status.Normal);
		this.currentHP = currentHP;
		this.boosts = boosts;
		this.ability = ability;
		this.gender = gender;
		this.moves = moves;
	}
	public Pokemon(Species species, Ability ability, Gender gender, int level, Item item, int[] stats, int[] boosts, ArrayList<Move> moves)
	{
		this.species = species;
		this.nick = species.getSpecies();
		this.level = level;
		this.item = item;
		this.stats = stats;
		status.add(Status.Normal);
		this.currentHP = stats[0];
		this.boosts = boosts;
		this.ability = ability;
		this.gender = gender;
		this.moves = moves;
	}
	
	public static ArrayList<Pokemon> getPokemonPossibilities(Species species, Gender gender, int level)
	{
		ArrayList<Ability> possabilities = new ArrayList<Ability>(Arrays.asList(species.getAbilities()));
		
		ArrayList<Pokemon> pokes = new ArrayList<>();
		
		int[] minstats = new int[]{species.getHPStat(level,0,0),species.getMinStatAtLevel(1,level),species.getMinStatAtLevel(2,level),
				species.getMinStatAtLevel(3,level),species.getMinStatAtLevel(4,level),species.getMinStatAtLevel(5,level)};
		
		int[] midstats = new int[]{species.getHPStat(level,15,124),species.getMidStatAtLevel(1,level),species.getMidStatAtLevel(2,level)
				,species.getMidStatAtLevel(5,level),species.getMidStatAtLevel(4,level),species.getMidStatAtLevel(3,level)};
		
		int[] maxstats = new int[]{species.getHPStat(level,31,255),species.getMaxStatAtLevel(1,level),species.getMaxStatAtLevel(2,level),
				species.getMaxStatAtLevel(3,level),species.getMaxStatAtLevel(4,level),species.getMaxStatAtLevel(5,level)};
		
		for(Ability ab : possabilities)
		{
			ArrayList<Move> stabmoves = new ArrayList<>();
			for(Type t : species.getTypes())
			{
				Move phys = new Move(t,"testmove:"+t+"phys","testmove:"+t+"phys","testmove:"+t+"phys","testmove:"+t+"phys", 
						Range.Any1,100,70,420,0,true,true,true,false,new int[]{1});
				Move spec = new Move(t,"testmove:"+t+"spec","testmove:"+t+"spec","testmove:"+t+"spec","testmove:"+t+"spec", 
						Range.Any1,100,70,420,0,false,false,false,false,new int[]{1});
				stabmoves.add(phys);
				stabmoves.add(spec);
			}
			int[] boosts = new int[]{1,1,1,1,1};
			
			Pokemon min = new Pokemon(species, ab, gender, level, Items.pokeball,minstats,boosts,stabmoves);
			Pokemon mid = new Pokemon(species, ab, gender, level, Items.pokeball,midstats,boosts,stabmoves);
			Pokemon max = new Pokemon(species, ab, gender, level, Items.pokeball,maxstats,boosts,stabmoves);
			pokes.add(min);
			pokes.add(mid);
			pokes.add(max);
		}
		return pokes;
	}
	
	public Species getSpecies()
	{
		return species;
	}
	
	public String getNickname()
	{
		return nick;
	}
	public String getSpeciesName()
	{
		return species.getName();
	}
	public int dexnum()
	{
		return species.getDexNum();
	}
	public Type[] getTypes()
	{
		return species.getTypes();
	}
	public Ability[] getAbilities()
	{
		return species.getAbilities();
	}
	public int[] getBaseStats()
	{
		return species.getBaseStats();
	}
	public int[] getStats()
	{
		return stats;
	}
	public int getLevel()
	{
		return level;
	}
	public String getName()
	{
		return species.getName();
	}
	public boolean isDualType()
	{
		return species.isDualType();
	}
	public boolean getsStabFrom(Move m)
	{
		for(Type t : species.getTypes())
		{
			if(t.equals(m.getType()))
				return true;
		}
		return false;
	}
	public double weaknessModifier(Type type)
	{
		double mod = 1;
		for(Type t : species.getTypes())
		{
			mod *= Type.getEffectiveness(type, t);
		}
		return mod;
	}
	public Item getItem()
	{
		return item;
	}

	public boolean isWeakTo(Move move) 
	{
		double down = weaknessModifier(move.getType());
		if(down > 1)
			return true;
		return false;
	}
	
	public boolean canEvolve()
	{
		return species.canEvolve();
	}
	
	public ArrayList<Move> getMoves()
	{
		if(moves == null)
			moves = species.getMovesFromFile();
		return moves;
	}
	
	public void addStatus(Status st)
	{
		status.add(st);
	}
	public void removeStatus(Status st)
	{
		status.remove(st);
	}
	public ArrayList<Status> getStatus()
	{
		return status;
	}
	public int getHP()
	{
		return currentHP;
	}
	public double getHPFraction()
	{
		return currentHP/(stats[0]/1.0);
	}
	public int[] getBoosts()
	{
		return boosts;
	}
	public Ability getAbility()
	{
		return ability;
	}
	public boolean getsSandBoost()
	{
		for(Type t : getTypes())
		{
			if(t.equals(Type.Steel) || t.equals(Type.Rock) || t.equals(Type.Ground))
				return true;
		}
		return false;
	}
	public Gender gender()
	{
		return gender;
	}
	public String toString()
	{
		return getClass().getName()+"[Species="+getSpeciesName()+",nick="+nick+",ability"+
				ability+",gender="+gender+",item="+item+",stats="+Arrays.toString(stats)
				+",currentHP="+currentHP+",boosts="+Arrays.toString(boosts)+",moves="+moves+"]";
	}
	
	public static void main(String[]args)
	{
		Species species = new SpeciesList().arghonaut;
		System.out.println(species.getMoves());
	}
}
