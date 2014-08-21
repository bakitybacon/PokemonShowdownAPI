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
import teambuilder.Moves;
import teambuilder.Pokemon;
import teambuilder.SpeciesList;
import teambuilder.Type;

public class DamageCalc
{
	public static final double minMod = 0.85;
	public static final double maxMod = 1;
	
	public static double[] getSinglesDamage(Pokemon attacker, Pokemon defender, Move move, Field field, boolean isCrit)
	{

		//since a lot of moves are 0 base power, there's no reason to go through the formula
		//when they can't possibly do damage
		if(move.getBasePower() == 0)
			return new double[]{0,0};
		//formula:
		//{[(2 * level + 10) / 250] * attack/defense * base + 2 }*mods
		//mods are stab, type effectiveness, crit, held items, and a random component
		
		if(attacker.getAbility().equals("pixilate"))
		{
			if(move.getType().equals(Type.Normal))
				move.setType(Type.Ice);
			move.setBasePower((int)(move.getBasePower()*1.3));
		}
		else if(attacker.getAbility().equals("aerilate"))
		{
			if(move.getType().equals(Type.Normal))
				move.setType(Type.Flying);
			move.setBasePower((int)(move.getBasePower()*1.3));
		}
		else if(attacker.getAbility().equals("refrigerate"))
		{
			if(move.getType().equals(Type.Normal))
				move.setType(Type.Ice);
			move.setBasePower((int)(move.getBasePower()*1.3));
		}
		if(attacker.getAbility().equals("normalize"))
			move.setType(Type.Normal);
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
		mods *= ItemBoost.getDefensiveBoost(move,defender.getItem(),defender); //defensive items, e.g. assault vest
		mods *= AbilityBoost.getOffensiveBoost(attacker.getAbility(), attacker, defender, null, null, move, field, isCrit);
		mods *= AbilityBoost.getDefensiveBoost(defender.getAbility(), attacker, defender, null, null, move, field);
		for(FieldCondition fc : field.getSide2())
		{
			mods *= FieldConditionsBoost.getFieldConditionsBoost(fc, move, defender);
		}
		System.out.println("Move "+move.getName()+" by Pokemon "+attacker.getName()+" on Pokemon "+defender.getName()+": "+Arrays.toString(new double[]{before * mods * minMod,before * mods * maxMod}));
		return new double[]{before * mods * minMod,before * mods * maxMod};
	}
	
	public static void main(String[]args)
	{
		Pokemon attaque = new Pokemon(new SpeciesList().sylveon,"yourfaceon",Abilities.scrappy, Gender.Male, 100,Items.choicespecs,new int[]{331,149,166,350,296,156}, 331, new int[]{1,1,1,1,1}, null);
		Pokemon defense = new Pokemon(new SpeciesList().cofagrigus,"whourse",Abilities.runaway, Gender.Female, 100,Items.chopleberry,new int[]{394,167,256,140,394,166}, 394, new int[]{1,1,1,1,1}, null);
		Move move = Moves.stockpile;
		Field field = new Field();
		field.add1(FieldCondition.LightScreen);
		System.out.println(Arrays.toString(getSinglesDamage(attaque,defense,move, field,false)));
	}
}
