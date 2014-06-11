package teambuilder;

public class Move 
{
	private final Type type;
	private final int basepower;
	private final boolean usesPhysDef;
	private final Range range;
	private final int pp;
	private final int priority;
	private final boolean isPhysical;
	private final String id;
	private final String name;
	private final int[] hitCount;
	private final boolean hasSecondaryEffect;
	private final boolean isContact;
	private final int accuracy;
	
	public Move(Type type, String id, String name, String desc, String shortdesc, Range range, int accuracy, int basepower, int pp, int priority, boolean isContact,boolean usesPhysDef, boolean isPhysical, boolean hasSecondaryEffect, int[] hitCount)
	{
		this.type = type;
		this.basepower = basepower;
		this.usesPhysDef = usesPhysDef;
		this.pp = pp;
		this.range = range;
		this.priority = priority;
		this.isPhysical = isPhysical;
		this.name = name;
		this.id = id;
		this.hitCount = hitCount;
		this.hasSecondaryEffect = hasSecondaryEffect;
		this.isContact = isContact;
		this.accuracy = accuracy;
	}
	
	public Type getType()
	{
		return type;
	}
	public int getBasePower()
	{
		return basepower;
	}
	public boolean usesPhysDef()
	{
		//you can't just ask if it's physical or not, because of moves like psyshock
		return usesPhysDef;
	}
	public int pp()
	{
		return pp;
	}
	public int priority()
	{
		return priority;
	}
	public boolean isPhysical()
	{
		return isPhysical;
	}
	public String getID()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public Range getRange()
	{
		return range;
	}
	public int[] hitCount()
	{
		return hitCount;
	}
	public String toString()
	{
		return getClass().getName()+"["+
	"name="+name+",id="+id+",type="+type+
	",pp="+pp+",priority="+priority+",isPhysical="+isPhysical+",usesPhysicalDefense="+usesPhysDef()+"]";
	}

	public boolean hasSecondaryEffect() 
	{
		return hasSecondaryEffect;
	}

	public boolean isContact() 
	{
		return isContact;
	}

	public int getAccuracy() 
	{
		return accuracy;
	}
}
