package teambuilder;

public class Gem extends Item
{
	
	Type boostedType;
	public Gem(String name, String id, String desc, Type type, int gen)
	{
		super(name,id,desc,gen);
		boostedType = type;
	}
	public Type boostedType()
	{
		return boostedType;
	}
}
