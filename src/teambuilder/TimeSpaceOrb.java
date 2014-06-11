package teambuilder;

public class TimeSpaceOrb extends Item
{
	
	Type[] boostedTypes;
	public TimeSpaceOrb(String name, String id, String desc, Type[] types, int gen)
	{
		super(name,id,desc,gen);
		boostedTypes = types;
	}
	Type[] boostedTypes()
	{
		return boostedTypes;
	}
}
