package teambuilder;

import teambuilder.Gem;
import teambuilder.Item;
import teambuilder.Move;
import teambuilder.Plate;
import teambuilder.Pokemon;
import teambuilder.ResistBerry;
import teambuilder.TimeSpaceOrb;
import teambuilder.Type;
import teambuilder.TypeBoostingItem;

public class ItemBoost 
{
	public static final double lightBallBoost = 2;
	public static final double deepToothBoost = 2;
	public static final double thickClubBoost = 2;
	public static final double choiceBoost = 1.5;
	public static final double soulDewOffenseBoost = 1.5;
	public static final double lifeBoost = 1.3;
	public static final double gemBoost = 1.3;
	public static final double typeBoost = 1.2;
	public static final double ebeltBoost = 1.2;
	public static final double physBoost = 1.1; //for wise glasses and muscle band
	public static final double noBoost = 1;
	public static final double avBoost = 0.67;
	public static final double evioliteBoost = 0.67;
	public static final double soulDewDefenseBoost = 0.67;
	public static final double resistBerryBoost = 0.5;
	public static final double deepScaleBoost = 0.5;
	public static final double metalPowderBoost = 0.5;
	public static double getOffensiveBoost(Move move, Item item, Pokemon user,Pokemon target)
	{
		if(item == null)
			return noBoost;
		if(item.getID().equals("lightball"))
		{
			if(user.getName().toLowerCase().equals("pikachu"))
					return lightBallBoost;
			return noBoost;
		}
		if(item.getID().equals("deepseatooth"))
		{
			if(user.getName().toLowerCase().equals("clamperl") && !move.isPhysical())
					return deepToothBoost;
			return noBoost;
		}
		if(item.getID().equals("souldew"))
		{
			if(user.getName().toLowerCase().equals("latias") || user.getName().toLowerCase().equals("latios"))
					if(!move.isPhysical())
						return soulDewOffenseBoost;
			return noBoost;
		}
		if(item.getID().equals("thickclub"))
		{
			if(user.getName().toLowerCase().equals("cubone") || user.getName().toLowerCase().equals("marowak"))
					if(move.isPhysical())
						return thickClubBoost;
			return noBoost;
		}
		if(item.getID().equals("wiseglasses"))
		{
			if(!move.isPhysical())
				return physBoost;
			return noBoost;
		}
		if(item.getID().equals("muscleband"))
		{
			if(move.isPhysical())
				return physBoost;
			return noBoost;
		}
		if(item.getID().equals("choiceband"))
		{
			if(move.isPhysical())
				return choiceBoost;
			return noBoost;
		}
		if(item.getID().equals("choicespecs"))
		{
			if(!move.isPhysical())
				return choiceBoost;
			return noBoost;
		}
		if(item.getID().equals("lifeorb"))
			return lifeBoost;
		if(item.getID().equals("expertbelt"))
		{
			if(target.isWeakTo(move))
				return ebeltBoost;
			return noBoost;
		}
		if(item instanceof Gem)
		{
			Gem gem = (Gem)item;
			if(move.getType().equals(gem.boostedType()))
				return gemBoost;
			return noBoost;
		}
		if(item instanceof Plate)
		{
			Plate plate = (Plate)item;
			if(move.getType().equals(plate.boostedType()))
				return typeBoost;
			return noBoost;
		}
		if(item instanceof TypeBoostingItem)
		{
			TypeBoostingItem tbi = (TypeBoostingItem)item;
			if(move.getType().equals(tbi.boostedType()))
				return typeBoost;
			return noBoost;
		}
		if(item instanceof TimeSpaceOrb)
		{
			TimeSpaceOrb ts = (TimeSpaceOrb)item;
			for(Type s : ts.boostedTypes())
			{
				if(move.getType().equals(s))
					return typeBoost;
			}
			return noBoost;
		}

		return noBoost;
	}
	public static double getDefensiveBoost(Move move, Item item, Pokemon target)
	{
		if(item == null)
			return noBoost;
		if(item instanceof ResistBerry)
		{
			ResistBerry rb = (ResistBerry)item;
			if(move.getType().equals(rb.boostedType()) &&  target.isWeakTo(move))
				return resistBerryBoost;
			if(move.getType().equals(rb.boostedType()) &&  move.getType().equals("normal"))
				return resistBerryBoost;
			return noBoost;
		}
		if(item.getID().equals("deepseascale"))
		{
			if(target.getName().toLowerCase().equals("clamperl") && !move.isPhysical())
					return deepScaleBoost;
			return noBoost;
		}
		if(item.getID().equals("metalpowder"))
		{
			if(target.getName().toLowerCase().equals("ditto") && move.isPhysical())
					return metalPowderBoost;
			return noBoost;
		}
		if(item.getID().equals("souldew"))
		{
			if(target.getName().toLowerCase().equals("latias") || target.getName().toLowerCase().equals("latios"))
					if(!move.isPhysical())
						return soulDewDefenseBoost;
			return noBoost;
		}
		if(item.getID().equals("eviolite"))
		{
			if(target.canEvolve())
				return evioliteBoost;
			return noBoost;
		}
		if(item.getID().equals("assaultvest"))
		{
			if(!move.isPhysical())
				return avBoost;
			return noBoost;
		}
		return noBoost;
	}
}
