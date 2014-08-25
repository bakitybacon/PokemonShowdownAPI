package teambuilder;

public class FieldConditionsBoost 
{
	public static final double weatherBoost = 1.5;
	public static final double noBoost = 1;
	public static final double weatherResist = 1.5;
	public static double getFieldConditionsBoost(FieldCondition fc, Move move, Pokemon defense)
	{
		double mods = 1;
		if(fc.equals(FieldCondition.Sun))
		{
			if(move.getType().equals(Type.Fire))
				mods *= weatherBoost; 
			if(move.getType().equals(Type.Water))
				mods *= weatherResist;
		}
		else if(fc.equals(FieldCondition.Rain))
		{
			if(move.getType().equals(Type.Water))
				mods *= weatherBoost; 
			if(move.getType().equals(Type.Fire))
				mods *= weatherResist;
		}
		else if(fc.equals(FieldCondition.Sandstorm))
		{
			if(!move.isPhysical() && defense.getsSandBoost())
				mods *= weatherBoost; 	
		}
		if(fc.equals(FieldCondition.MistyTerrain))
		{
			if(move.getType().equals(Type.Dragon))
				mods *= weatherResist; 
		}
		else if(fc.equals(FieldCondition.GrassyTerrain))
		{
			if(move.getType().equals(Type.Dragon))
				mods *= weatherBoost; 
		}
		else if(fc.equals(FieldCondition.ElectricTerrain))
		{
			if(move.getType().equals(Type.Electric))
				mods *= weatherBoost; 
		}
		if(fc.equals(FieldCondition.LightScreen))
		{
			if(!move.isPhysical())
				mods *= 0.5; 
		}
		if(fc.equals(FieldCondition.Reflect))
		{
			if(move.isPhysical())
				mods *= 0.5; 
		}
		return mods;
	}
}
