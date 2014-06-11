package teambuilder;

public class TypeBoostingItem extends Item
{
	Type boostedType;
	public TypeBoostingItem(String name, String id, String desc, Type type, int gen)
	{
		super(name,id,desc,gen);
		boostedType = type;
	}
	public Type boostedType()
	{
		return boostedType;
	}
}
