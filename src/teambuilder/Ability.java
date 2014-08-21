package teambuilder;

public class Ability 
{
	String name;
	String id;
	String desc;
	String shortdesc;
	//just a bunch of strings, any damage related stuff we deal with over in AbilityBoost.java
	public Ability(String name, String id, String desc, String shortdesc)
	{
		this.name = name;
		this.id = id;
		this.desc = desc;
		this.shortdesc = shortdesc;
	}
	public String getName()
	{
		return name;
	}
	public String getID()
	{
		return id;
	}
	public String getDesc()
	{
		return desc;
	}
	public String getShortDesc()
	{
		return shortdesc;
	}
	public String toString()
	{
		return getClass().getName()+"["+name+"]";
	}
}
