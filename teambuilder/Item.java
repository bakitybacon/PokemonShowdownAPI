package teambuilder;

public class Item 
{
	String name;
	String id;
	String desc;
	int gen;
	public Item(String id, String name, String desc, int gen)
	{
		this.name = name;
		this.id = id;
		this.desc = desc;
		this.gen = gen;
	}
	
	public String getID()
	{
		return id;
	}
	
	public String toString()
	{
		return getClass().getName()+"["+name+"]";
	}
}
