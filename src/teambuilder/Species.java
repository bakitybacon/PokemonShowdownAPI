package teambuilder;

public class Species 
{
	int num;
	String species;
	boolean canEvo;
	Type[] types;
	int[] baseStats;
	Ability[] mainabilities;
	Ability hiddenability;
	Move[] moves;
	public Species(int num,String species,boolean canEvo,Type[] types,int[] baseStats,Ability[] mainabilities,Ability hiddenability,Move[] moves)
	{
		this.num = num;
		this.species = species;
		this.canEvo = canEvo;
		this.types = types;
		this.baseStats = baseStats;
		this.mainabilities = mainabilities;
		this.hiddenability = hiddenability;
		this.moves = moves;
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
	public Move[] getMoves()
	{
		return moves;
	}
}
