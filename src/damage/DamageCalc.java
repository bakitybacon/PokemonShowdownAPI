package damage;

import java.util.Arrays;

import teambuilder.Abilities;
import teambuilder.AbilityBoost;
import teambuilder.Field;
import teambuilder.FieldCondition;
import teambuilder.FieldConditionsBoost;
import teambuilder.Gender;
import teambuilder.ItemBoost;
import teambuilder.Items;
import teambuilder.Move;
import teambuilder.Pokemon;
import teambuilder.Range;
import teambuilder.SpeciesList;
import teambuilder.Type;

public class DamageCalc
{
	public static final double minMod = 0.85;
	public static final double maxMod = 1;
	
	public static double[] getSinglesDamage(Pokemon attacker, Pokemon defender, Move move, Field field, boolean isCrit)
	{
		//formula:
		//{[(2 * level + 10) / 250] * attack/defense * base + 2 }*mods
		//mods are stab, type effectiveness, crit, held items, and a random component
		
		double levelmod = (attacker.getLevel()*2+10)/250.0;
		double attack = 0;
		double defense = 1;
		if(move.usesPhysDef())
		{
			defense = defender.getStats()[2] * defender.getBoosts()[1];
		}
		else
		{
			defense = defender.getStats()[4] * defender.getBoosts()[3];
		}
		if(move.isPhysical())
		{
			attack = attacker.getStats()[1] * attacker.getBoosts()[0];
		}
		else
		{
			attack = attacker.getStats()[3] * attacker.getBoosts()[2];
		}
		double defensemod = attack/defense;
		int basepower = move.getBasePower();
		double before = levelmod * defensemod * basepower + 2;
		double mods = 1;
		if(attacker.getsStabFrom(move))
					mods *= 1.5;
		
		double weakness = defender.weaknessModifier(move.getType());
		if(weakness == 0 && defender.getItem().getID().equals("ringtarget"))
		{
			weakness = 1;
			for(Type t : defender.getTypes())
			{
				if(defender.weaknessModifier(t) == 0)
					weakness *= 1;
				else weakness *= defender.weaknessModifier(t);
			}
		}
		if(weakness == 0 && attacker.getAbility().getID().equals("scrappy"))
		{
			boolean isGhost = false;
			weakness = 1;
			for(Type t : defender.getTypes())
			{
				if(t.equals(Type.Ghost))
				{
					isGhost = true;
					continue;
				}
				weakness *= defender.weaknessModifier(t);
			}
			if(!isGhost)
				weakness = 0;
		}
		mods *= weakness;
		if(isCrit)
			mods *= 1.5;
		mods *= ItemBoost.getOffensiveBoost(move,attacker.getItem(),attacker,defender); //offensive items, e.g. life orb
		System.out.println(ItemBoost.getOffensiveBoost(move,attacker.getItem(),attacker, defender));
		mods *= ItemBoost.getDefensiveBoost(move,defender.getItem(),defender); //defensive items, e.g. assault vest
		System.out.println(ItemBoost.getDefensiveBoost(move,defender.getItem(),defender));
		mods *= AbilityBoost.getOffensiveBoost(attacker.getAbility(), attacker, defender, null, null, move, field, isCrit);
		mods *= AbilityBoost.getDefensiveBoost(defender.getAbility(), attacker, defender, null, null, move, field);
		System.out.println(mods);
		for(FieldCondition fc : field.getSide2())
		{
			mods *= FieldConditionsBoost.getFieldConditionsBoost(fc, move, defender);
		}
		System.out.println(mods);
		return new double[]{before * mods * minMod,before * mods * maxMod};
	}
	
	public static void main(String[]args)
	{
		//Pokemon attaque = new Pokemon(SpeciesList.sylveon,"yourfaceon",Abilities.scrappy, Gender.Male, 100,Items.choicespecs,new int[]{331,149,166,350,296,156}, 331, new int[]{1,1,1,1,1});
		//Pokemon defense = new Pokemon(SpeciesList.cofagrigus,"whourse",Abilities.runaway, Gender.Female, 100,Items.chopleberry,new int[]{394,167,256,140,394,166}, 394, new int[]{1,1,1,1,1});
		//Move move = new Move(Type.Normal,"HopperVoice", "lesweed",Range.Opponent1, 65,42,0,false,false,false,1);
		//Field field = new Field();
		//field.add1(FieldCondition.LightScreen);
		//System.out.println(Arrays.toString(getSinglesDamage(attaque,defense,move, field,false)));
	}
}
