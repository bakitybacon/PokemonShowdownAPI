package teambuilder;

public class Plate extends Item
{
	Type boostedType;
	public Plate(String name, String id, String desc, Type type, int gen)
	{
		super(name,id,desc,gen);
		boostedType = type;
	}
	Type boostedType()
	{
		return boostedType;
	}
	
	public static Type getTypeFromName(String kind)
	{
		switch(kind.toLowerCase())
		{
		case "pixie":
			return Type.Fairy;
		case "draco":
			return Type.Dragon;
		case "dread":
			return Type.Dark;
		case "earth":
			return Type.Ground;
		case "fist":
			return Type.Fighting;
		case "flame":
			return Type.Fire;
		case "icicle":
			return Type.Ice;
		case "insect":
			return Type.Bug;
		case "iron":
			return Type.Steel;
		case "meadow":
			return Type.Grass;
		case "mind":
			return Type.Psychic;
		case "sky":
			return Type.Flying;
		case "splash":
			return Type.Water;
		case "spooky":
			return Type.Ghost;
		case "stone":
			return Type.Rock;
		case "toxic":
			return Type.Poison;
		case "zap":
			return Type.Electric;
		}
		return null;
	}
}
