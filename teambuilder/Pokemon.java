package teambuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Pokemon 
{
	String nick;
	int level;
	Item item;
	int[] stats;
	Species species;
	ArrayList<Status> status = new ArrayList<>();
	int currentHP;
	double[] boosts;
	Ability ability;
	Gender gender;
	ArrayList<Move> moves;
	Type[] types;
	
	static ArrayList<Double> statstages = new ArrayList<Double>();
	static ArrayList<Double> evstages = new ArrayList<Double>();
	static
	{
		Collections.addAll(statstages, 1/4.0,1/3.5,1/3.0,1/2.5,1/2.0,1/1.5,1/1.0,1.5,2/1.0,2.5,3/1.0,3.5,4/1.0);
		Collections.addAll(evstages, 1/3.0,3/8.0,3/7.0,1/2.0,3/5.0,3/4.0,1/1.0,4/3.0,5/3.0,2/1.0,7/3.0,8/3.0,3/1.0);
	}
	
	
	public Pokemon(Species species, String nick, Ability ability, Gender gender, int level, Item item, int[] stats, int currentHP, double[] boosts, ArrayList<Move> moves)
	{
		if(stats.length != 6)
			throw new IllegalArgumentException("Please supply 6 stat values.");
		if(boosts.length != 7)
			throw new IllegalArgumentException("Please supply 7 boost values.");
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
		types = species.getTypes();
	}
	public Pokemon(Species species, Ability ability, Gender gender, int level, Item item, int[] stats, int currentHP, double[] boosts, ArrayList<Move> moves)
	{
		if(stats.length != 6)
			throw new IllegalArgumentException("Please supply 6 stat values.");
		if(boosts.length != 7)
			throw new IllegalArgumentException("Please supply 7 boost values.");
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
		types = species.getTypes();
	}
	public Pokemon(Species species, Ability ability, Gender gender, int level, Item item, int[] stats, double[] boosts, ArrayList<Move> moves)
	{
		if(stats.length != 6)
			throw new IllegalArgumentException("Please supply 6 stat values.");
		if(boosts.length != 7)
			throw new IllegalArgumentException("Please supply 7 boost values.");
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
		types = species.getTypes();
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
			double[] boosts = new double[]{1,1,1,1,1,1,1};
			
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
		return types;
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
		for(Type t : types)
		{
			if(t.equals(m.getType()))
				return true;
		}
		return false;
	}
	public double weaknessModifier(Type type)
	{
		double mod = 1;
		for(Type t : types)
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
	public double[] getBoosts()
	{
		return boosts;
	}
	public Ability getAbility()
	{
		return ability;
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
		Pokemon defense = new Pokemon(new SpeciesList().cofagrigus,"whourse",Abilities.runaway, Gender.Female, 100,Items.chopleberry,new int[]{394,167,256,140,394,166}, 394, new double[]{1,1,1,1,1,1,1}, new ArrayList<Move>());
		System.out.println(statstages);
		System.out.println(evstages);
		defense.increaseAttack(1);
		defense.increaseDefense(1);
		defense.increaseSpAttack(1);
		defense.increaseSpDefense(1);
		defense.increaseSpeed(1);
		defense.increaseEvasion(3);
		System.out.println(Arrays.toString(defense.getBoosts()));
		System.out.println(defense.countBoosts());
		
	}
	public void setItem(Item item) 
	{
		this.item = item;
	}
	public void decreaseAttack(int amount)
	{
		changeStat(0,-amount);
	}
	public void increaseAttack(int amount)
	{
		changeStat(0,amount);
	}
	public void decreaseDefense(int amount)
	{
		changeStat(1,-amount);
	}
	public void increaseDefense(int amount)
	{
		changeStat(1,amount);
	}
	public void decreaseSpAttack(int amount)
	{
		changeStat(2,-amount);
	}
	public void increaseSpAttack(int amount)
	{
		changeStat(2,amount);
	}
	public void decreaseSpDefense(int amount)
	{
		changeStat(3,-amount);
	}
	public void increaseSpDefense(int amount)
	{
		changeStat(3,amount);
	}
	public void decreaseSpeed(int amount)
	{
		changeStat(4,-amount);
	}
	public void increaseSpeed(int amount)
	{
		changeStat(4,amount);
	}
	public void decreaseEvasion(int amount)
	{
		changeEvasion(5,-amount);
	}
	public void increaseEvasion(int amount)
	{
		changeEvasion(5,amount);
	}
	public void decreaseAccuracy(int amount)
	{
		changeEvasion(6,-amount);
	}
	public void increaseAccuracy(int amount)
	{
		changeEvasion(6,amount);
	}
	public void changeStat(int which, int amount)
	{
		if(boosts[which] == 0.25 && amount < 0)
			return;
		if(boosts[which] == 4 && amount > 0)
			return;
		if(statstages.indexOf(boosts[which]) + amount >= statstages.size() - 1)
		{
			boosts[which] = 4;
			return;
		}
		if(statstages.indexOf(boosts[which]) + amount < 0)
		{
			boosts[which] = 0.25;
			return;
		}
		boosts[which] = statstages.get(statstages.indexOf(boosts[which]) + amount);
	}
	public void changeEvasion(int which, int amount)
	{
		if(boosts[which] == 1/3.0 && amount < 0)
			return;
		if(boosts[which] == 3 && amount > 0)
			return;
		if(evstages.indexOf(boosts[which]) + amount >= evstages.size() - 1)
		{
			boosts[which] = 3;
			return;
		}
		if(evstages.indexOf(boosts[which]) + amount < 0)
		{
			boosts[which] = 1/3.0;
			return;
		}
		boosts[which] = evstages.get(evstages.indexOf(boosts[which]) + amount);
	}
	
	public int countBoosts()
	{
		int boostcount = 0;
		for(int i = 0; i < 5; i++)
		{
			boostcount += Math.max(0,statstages.indexOf(boosts[i]) - 6);
		}
		for(int i = 5; i < 7; i++)
		{
			boostcount += Math.max(0,evstages.indexOf(boosts[i]) - 6);
		}
		return boostcount;
	}
	
	public void setAbility(Ability ability) 
	{
		this.ability = ability;
	}
	
	public boolean isHealthy()
	{
		if(status.contains(Status.Toxic))
			return false;
		if(status.contains(Status.Sleep))
			return false;
		if(status.contains(Status.Paralysis))
			return false;
		if(status.contains(Status.Burn))
			return false;
		if(status.contains(Status.Poison))
			return false;
		if(status.contains(Status.Freeze))
			return false;
		return true;
	}
	public boolean isBurned()
	{
		return status.contains(Status.Burn);
	}
	public boolean isToxic()
	{
		return status.contains(Status.Toxic);
	}
	public boolean isAsleep()
	{
		return status.contains(Status.Sleep);
	}
	public boolean isParalyzed()
	{
		return status.contains(Status.Paralysis);
	}
	public boolean isFrozen()
	{
		return status.contains(Status.Freeze);
	}
	public boolean isRegularlyPoisoned()
	{
		return status.contains(Status.Poison);
	}
	public boolean isPoisoned()
	{
		return status.contains(Status.Poison) || status.contains(Status.Toxic);
	}
	public boolean isType(Type type)
	{
		for(Type t : types)
		{
			if(t.equals(type))
				return true;
		}
		return false;
	}
	public void setTypes(Type[] types) 
	{
		this.types = types;
	}
}
