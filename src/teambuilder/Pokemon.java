package teambuilder;

import java.util.ArrayList;


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
	
	public Pokemon(Species species, String nick, Ability ability, Gender gender, int level, Item item, int[] stats, int currentHP, int[] boosts)
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
}
