package teambuilder;

public class ResistBerry extends Item
{
	Type type;
	public ResistBerry(String name, String id, String desc, Type type, int gen)
	{
		super(name,id,desc,gen);
		this.type = type;
	}
	public Type boostedType()
	{
		return type;
	}
}
