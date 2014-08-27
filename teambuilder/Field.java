package teambuilder;

import java.util.ArrayList;

public class Field extends ArrayList<ArrayList<FieldCondition>>
{
	ArrayList<FieldCondition> side1 = new ArrayList<>();
	ArrayList<FieldCondition> side2 = new ArrayList<>();
	private static final long serialVersionUID = -382604305258842867L;
	public Field()
	{
		super();
		
	}
	public void addBoth(FieldCondition fc)
	{
		side1.add(fc);
		side2.add(fc);
	}
	public void add1(FieldCondition fc)
	{
		side1.add(fc);
	}
	public void add2(FieldCondition fc)
	{
		side2.add(fc);
	}
	public ArrayList<FieldCondition> getSide1()
	{
		return side1;
	}
	public ArrayList<FieldCondition> getSide2()
	{
		return side2;
	}
	public void removeWeather()
	{
		if(side1.contains(FieldCondition.Sun))
			side1.remove(FieldCondition.Sun);
		if(side1.contains(FieldCondition.Sandstorm))
			side1.remove(FieldCondition.Sandstorm);
		if(side1.contains(FieldCondition.Rain))
			side1.remove(FieldCondition.Rain);
		if(side1.contains(FieldCondition.Hail))
			side1.remove(FieldCondition.Hail);
		if(side2.contains(FieldCondition.Sun))
			side2.remove(FieldCondition.Sun);
		if(side2.contains(FieldCondition.Sandstorm))
			side2.remove(FieldCondition.Sandstorm);
		if(side2.contains(FieldCondition.Rain))
			side2.remove(FieldCondition.Rain);
		if(side2.contains(FieldCondition.Hail))
			side2.remove(FieldCondition.Hail);
	}
	public void removeScreens() 
	{
		if(side2.contains(FieldCondition.LightScreen))
			side2.remove(FieldCondition.LightScreen);
		if(side2.contains(FieldCondition.Reflect))
			side2.remove(FieldCondition.Reflect);
	}
	public boolean isGravity()
	{
		if(side1.contains(FieldCondition.Gravity))
			return true;
		if(side2.contains(FieldCondition.Gravity))
			return true;
		return false;
	}
	public boolean isSandstorm()
	{
		if(side1.contains(FieldCondition.Sandstorm))
			return true;
		if(side2.contains(FieldCondition.Sandstorm))
			return true;
		return false;
	}
	public boolean isRain()
	{
		if(side1.contains(FieldCondition.Rain))
			return true;
		if(side2.contains(FieldCondition.Rain))
			return true;
		return false;
	}
	public boolean isHail()
	{
		if(side1.contains(FieldCondition.Hail))
			return true;
		if(side2.contains(FieldCondition.Hail))
			return true;
		return false;
	}
	public boolean isSun()
	{
		if(side1.contains(FieldCondition.Sun))
			return true;
		if(side2.contains(FieldCondition.Sun))
			return true;
		return false;
	}
	public boolean isForesight()
	{
		if(side2.contains(FieldCondition.Foresight))
			return true;
		return false;
	}
	public boolean isHelpingHand()
	{
		if(side1.contains(FieldCondition.HelpingHand))
			return true;
		return false;
	}
	public boolean hasWeather()
	{
		if(side1.contains(FieldCondition.Rain) ||
				side1.contains(FieldCondition.Sun) ||
				side1.contains(FieldCondition.Hail) ||
				side1.contains(FieldCondition.Sandstorm))
			return true;
		return false;
	}
	public boolean isReflect() 
	{
		if(side2.contains(FieldCondition.Reflect))
			return true;
		return false;
	}
	public boolean Lightscreen() 
	{
		if(side2.contains(FieldCondition.LightScreen))
			return true;
		return false;
	}
}
