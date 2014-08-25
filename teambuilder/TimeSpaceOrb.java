package teambuilder;

public class TimeSpaceOrb extends Item
{
	
	Type[] boostedTypes;
	Species boostedPokemon;
	public TimeSpaceOrb(Species boostedpokemon,String name, String id, String desc, Type[] types, int gen)
	{
		super(name,id,desc,gen);
		boostedTypes = types;
		boostedPokemon = boostedpokemon; 
	}
	public Type[] boostedTypes()
	{
		return boostedTypes;
	}
	public Species boostedPokemon()
	{
		return boostedPokemon;
	}
}
