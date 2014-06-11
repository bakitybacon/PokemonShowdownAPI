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
}
