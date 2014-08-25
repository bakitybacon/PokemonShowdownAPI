package damage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import teambuilder.Abilities;
import teambuilder.Ability;
import teambuilder.Field;
import teambuilder.FieldCondition;
import teambuilder.FieldConditionsBoost;
import teambuilder.Gem;
import teambuilder.Gender;
import teambuilder.Item;
import teambuilder.ItemBoost;
import teambuilder.Items;
import teambuilder.Move;
import teambuilder.Moves;
import teambuilder.Plate;
import teambuilder.Pokemon;
import teambuilder.SpeciesList;
import teambuilder.Status;
import teambuilder.TimeSpaceOrb;
import teambuilder.Type;
import teambuilder.TypeBoostingItem;

public class DamageCalc
{
	public static final double minMod = 0.85;
	public static final double maxMod = 1;
	
	public static final Ability[] moldbreakers = new Ability[]{Abilities.moldbreaker,Abilities.turboblaze,Abilities.teravolt};
	
	public static final ArrayList<Ability> waterimmune = new ArrayList<>();
	public static final ArrayList<Ability> eleimmune = new ArrayList<>();
	public static final ArrayList<Move> bullets = new ArrayList<>();
	public static final ArrayList<Move> sounds = new ArrayList<>();
	public static final ArrayList<Move> reckless = new ArrayList<>();
	public static final ArrayList<Move> ironfist = new ArrayList<>();
	public static final ArrayList<Move> megalauncher = new ArrayList<>();
	public static final ArrayList<Move> strongjaw = new ArrayList<>();
	public static final ArrayList<Item> megastones = new ArrayList<>();
	
	static
	{
		Collections.addAll(waterimmune,Abilities.dryskin, Abilities.waterabsorb,Abilities.stormdrain);
		Collections.addAll(eleimmune,Abilities.lightningrod, Abilities.voltabsorb,Abilities.motordrive);
		Collections.addAll(bullets,Moves.acidspray,Moves.aurasphere,Moves.barrage,Moves.bulletseed,Moves.eggbomb,Moves.electroball,
				Moves.focusblast,Moves.gyroball,Moves.iceball,Moves.magnetbomb,Moves.mistball,Moves.mudbomb,Moves.octazooka,Moves.rockwrecker,
				Moves.searingshot,Moves.seedbomb,Moves.shadowball,Moves.sludgebomb,Moves.weatherball,Moves.zapcannon);
		Collections.addAll(sounds,Moves.boomburst,Moves.bugbuzz,Moves.chatter,Moves.confide,Moves.disarmingvoice,Moves.echoedvoice,
				Moves.grasswhistle,Moves.growl,Moves.hypervoice,Moves.metalsound,Moves.nobleroar,Moves.partingshot,Moves.perishsong,
				Moves.relicsong,Moves.roar,Moves.round,Moves.screech,Moves.sing,Moves.snarl,Moves.snore,Moves.supersonic,Moves.uproar);
		Collections.addAll(reckless,Moves.bravebird,Moves.doubleedge,Moves.flareblitz,Moves.headcharge,
				Moves.headsmash,Moves.highjumpkick,Moves.jumpkick,Moves.submission,Moves.takedown,Moves.volttackle,
				Moves.wildcharge,Moves.woodhammer);
		Collections.addAll(ironfist,Moves.bulletpunch,Moves.cometpunch,Moves.dizzypunch,Moves.drainpunch,Moves.dynamicpunch,Moves.firepunch,
				Moves.focuspunch,Moves.hammerarm,Moves.icepunch,Moves.machpunch,Moves.megapunch,Moves.meteormash,Moves.poweruppunch,
				Moves.shadowpunch,Moves.skyuppercut,Moves.thunderpunch);
		Collections.addAll(megalauncher,Moves.aurasphere,Moves.darkpulse,Moves.dragonpulse,Moves.healpulse,Moves.waterpulse);
		Collections.addAll(strongjaw,Moves.bite,Moves.crunch,Moves.firefang,Moves.icefang,Moves.poisonfang,Moves.thunderfang);
		
	}
	
	public static double[] getSinglesDamage(Pokemon attacker, Pokemon defender, Move move, Field field, boolean isCrit)
	{

		//since a lot of moves are 0 base power, there's no reason to go through the formula
		//when they can't possibly do damage
		if(move.getBasePower() == 0)
			return new double[]{0,0};
		//formula:
		//{[(2 * level + 10) / 250] * attack/defense * base + 2 }*mods
		//mods are stab, type effectiveness, crit, held items, and a random component
		
		Ability attackab = attacker.getAbility();
		Ability defenseab = defender.getAbility();
		
		field = checkAirLock(attackab,defenseab,field);
		attacker = checkKlutz(attacker);
		defender = checkKlutz(defender);
		
		if(defender.getAbility().equals(Abilities.intimidate))
		{
			String id = attacker.getAbility().getID();
			if(id.equals("contrary") || id.equals("defiant"))
					attacker.increaseAttack(1);
			else if(id.equals("clearbody") || id.equals("whitesmoke")
					|| id.equals("hypercutter")){}
			else if(id.equals("simple"))
				attacker.decreaseAttack(2);
			else attacker.decreaseAttack(1);
		}
		
		if(defender.getAbility().equals(Abilities.download))
		{
			if(defender.getStats()[4] >= defender.getStats()[2])
				attacker.increaseSpAttack(1);
			else attacker.increaseAttack(1);
		}
		
		field = checkInfiltrator(attacker, field);
		
		if(isCrit && (defender.getAbility().equals(Abilities.shellarmor) || defender.getAbility().equals(Abilities.battlearmor)))
		{
			isCrit = false;
		}
		
		for(Ability a : moldbreakers)
		{
			if(a.equals(attacker.getAbility()))
				defender.setAbility(Abilities.nothing);
		}
		
		////////////////////////////
		//  changing move types   //
		////////////////////////////
		
		String mid = move.getID();
		
		if(mid.equals("weatherball"))
		{
			move.setType(field.getSide1().contains(FieldCondition.Sun) ? Type.Fire
	                : field.getSide1().contains(FieldCondition.Rain) ? Type.Water
	                : field.getSide1().contains(FieldCondition.Sandstorm) ? Type.Rock
	                : field.getSide1().contains(FieldCondition.Hail) ? Type.Ice
	                : Type.Normal);
		}
		
		if(mid.equals("judgment") && attacker.getItem() instanceof Plate)
		{
			move.setType(((Plate)attacker.getItem()).boostedType());
		}
		
		
			////////////////////////////
			//  altering base power   //
			////////////////////////////
		
		double attackspeed = getFinalSpeed(attacker,field);
		double defendspeed = getFinalSpeed(defender,field);
		
		if(mid.equals("electroball"))
		{
			double r = Math.floor(attackspeed / defendspeed);
			if(r >= 4)
				move.setBasePower(150);
			else if (r >= 3)
				move.setBasePower(120);
			else if (r >= 2)
				move.setBasePower(80);
			else move.setBasePower(60);
		}
		else if(mid.equals("gyroball"))
		{
			move.setBasePower((int) Math.round(Math.min(150, Math.floor(25 * defendspeed / attackspeed))));
		}
		else if(mid.equals("payback"))
		{
			if(attacker.getStats()[5] > defender.getStats()[5])
				move.setBasePower(50);
			else move.setBasePower(100);
		}
		else if(mid.equals("punishment"))
		{
			move.setBasePower(Math.min(200, 60 + 20 * defender.countBoosts()));
		}
		else if(mid.equals("storedpower"))
		{
			move.setBasePower(20 + 20 * attacker.countBoosts());
		}
		else if(mid.equals("hex"))
		{
			//as opposed to absolute values
			//we are multiplying relative, because in gen5 it was base 50
			//and in gen6 it was 100
			if(!defender.isHealthy())
				move.setBasePower(move.getBasePower()  * 2);
		}
		else if(mid.equals("acrobatics"))
		{
			if(attacker.getItem() == null || attacker.getItem().getID().equals("flyinggem"))
				move.setBasePower(110);
			else move.setBasePower(55);
		}
		else if(mid.equals("wakeupslap"))
		{
			if(!defender.getStatus().contains(Status.Sleep))
				move.setBasePower(move.getBasePower()  * 2);
		}
		else if(mid.equals("weatherball"))
		{
			if(field.hasWeather())
				move.setBasePower(move.getBasePower() * 2);
		}
		else if(mid.equals("eruption") || mid.equals("waterspout"))
		{
			move.setBasePower(Math.max(1, (int)Math.floor(150 * attacker.getHPFraction())));
		}
		else if(mid.equals("reversal") || mid.equals("flail"))
		{
			double hp = attacker.getHPFraction();
			if(hp > 70)
				move.setBasePower(20);
			else if(hp > 35 && hp <= 70)
				move.setBasePower(40);
			else if(hp > 20 && hp <= 35)
				move.setBasePower(80);
			else if(hp > 10 && hp <= 20)
				move.setBasePower(100);
			else if(hp > 5 && hp <= 10)
				move.setBasePower(150);
			else move.setBasePower(200);
		}
		
		int basepower = move.getBasePower();
		
		///////////////////////////////
		// changing bp based on mods //
		///////////////////////////////
		
		ArrayList<Integer> bpmod = new ArrayList<>();
		
		String id = attackab.getID();
		
		if(id.equals("techinician") && basepower <= 60 ||
				id.equals("flareboost") && attacker.isBurned() && !move.isPhysical() ||
				id.equals("toxicboost") && attacker.isPoisoned() && move.isPhysical())
			bpmod.add(0x1800);
		
		else if(id.equals("analytic") && attacker.getStats()[5] < defender.getStats()[5])
			bpmod.add(0x14CD);
		
		else if(id.equals("sandforce") && field.isSandstorm())
		{
			ArrayList<Type> types = new ArrayList<Type>();
			types.add(Type.Ground);
			types.add(Type.Rock);
			types.add(Type.Steel);
			if(types.contains(move.getType()))
				bpmod.add(0x14CD);
		}
		else if(id.equals("sandforce") && field.isSandstorm())
		{
			ArrayList<Type> types = new ArrayList<Type>();
			types.add(Type.Ground);
			types.add(Type.Rock);
			types.add(Type.Steel);
			if(types.contains(move.getType()))
				bpmod.add(0x14CD);
		}
		
		else if(id.equals("reckless") && reckless.contains(move) ||
				id.equals("ironfist") && ironfist.contains(move))
		{
			bpmod.add(0x1333);
		}
		else if(id.equals("sheerforce") && move.hasSecondaryEffect())
			bpmod.add(0x14CD);
		
		String did = defenseab.getID();
		
		if(did.equals("heatproof") && move.getType().equals(Type.Fire))
			bpmod.add(0x800);
		else if(did.equals("dryskin") && move.getType().equals(Type.Fire))
			bpmod.add(0x1400);
		
		Item i = attacker.getItem();
		if(i instanceof TypeBoostingItem)
		{
			TypeBoostingItem tbi = (TypeBoostingItem)i;
			if(move.getType().equals(tbi.boostedType()))
				bpmod.add(0x1333);
		}
		else if(i instanceof Gem)
		{
			bpmod.add(0x14CD);
		}
		else if(i.getID().equals("muscleband") && move.isPhysical() ||
				i.getID().equals("wiseglasses") && !move.isPhysical())
			bpmod.add(0x1199);
		else if(i instanceof TimeSpaceOrb)
		{
			TimeSpaceOrb tsi = (TimeSpaceOrb)i;
			if(attacker.getSpecies().equals(tsi.boostedPokemon()))
			{
				if(attacker.getsStabFrom(move))
					bpmod.add(0x1333);
			}
		}
		
		if(field.isHelpingHand())
			bpmod.add(0x1800);
		
		if(attackab.getID().equals("pixilate") && move.getType().equals(Type.Normal))
		{
			move.setType(Type.Fairy);
			bpmod.add(0x14CD);
		}
		else if(attackab.getID().equals("refrigerate") && move.getType().equals(Type.Normal))
		{
			move.setType(Type.Ice);
			bpmod.add(0x14CD);
		}
		else if(attackab.getID().equals("aerilate") && move.getType().equals(Type.Normal))
		{
			move.setType(Type.Flying);
			bpmod.add(0x14CD);
		}
		else if(attackab.getID().equals("normalize"))
			move.setType(Type.Normal);
		else if(attackab.getID().equals("darkaura") || defenseab.getID().equals("darkaura"))
		{
			if(move.getType().equals(Type.Dark))
			{
				if(attackab.getID().equals("aurabreak") || defenseab.getID().equals("aurabreak"))
				{
					bpmod.add(0xAAA);
				}
				else bpmod.add(0x1555);
			}
		}
		else if(id.equals("fairyaura") || defenseab.getID().equals("fairyaura"))
		{
			if(move.getType().equals(Type.Fairy))
			{
				if(attackab.getID().equals("aurabreak") || defenseab.getID().equals("aurabreak"))
				{
					bpmod.add(0xAAA);
				}
				else bpmod.add(0x1555);
			}
		}
		else if(id.equals("toughclaws") && move.isContact())
		{
			bpmod.add(0x1547);
		}
		
		if(mid.equals("facade") && !attacker.isHealthy() ||
				mid.equals("brine") && defender.getHPFraction() <= 0.5 ||
				mid.equals("venoshock") && defender.isPoisoned())
			bpmod.add(0x2000);
		else if (mid.equals("solarbeam") && field.hasWeather() && !field.isSun())
			bpmod.add(0x800);
		else if(mid.equals("knockoff") && defender.getItem() != null ||
				(defender.getSpecies().getID().equals("giratinaorigin") && defender.getItem().getID().equals("griseousorb")) ||
				defender.getSpecies().getID().matches("arceus") && i instanceof Plate)
			bpmod.add(0x1800);
		
		basepower = Math.max(1, pokeRound(basepower * chainMods(bpmod) / 0x1000));
		
		
		////////////
		// ATTACK //
		////////////
		
	
		double attack = 1;
		double defense = 1;
		if(mid.equals("foulplay"))
		{
			attack = defender.getStats()[1] * defender.getBoosts()[0];
		}
		else if(move.isPhysical())
		{
			if(defenseab.getID().equals("unaware"))
				attack = attacker.getStats()[1];
			else if(isCrit && attacker.getBoosts()[0] < 1)
				attack = attacker.getStats()[1];
			else attack = attacker.getStats()[1] * attacker.getBoosts()[0];
		}
		else
		{
			if(defenseab.getID().equals("unaware"))
				attack = attacker.getStats()[3];
			else if(isCrit && attacker.getBoosts()[2] < 1)
				attack = attacker.getStats()[3];
			else attack = attacker.getStats()[3] * attacker.getBoosts()[2];
		}
		
		if(id.equals("hustle") && move.isPhysical())
		{
			attack = pokeRound(attack * 3/2.0);
		}
		
		ArrayList<Integer> attackmod = new ArrayList<>();
		
		if(did.equals("thickfat") && (move.getType().equals(Type.Fire) || move.getType().equals(Type.Ice)))
			attackmod.add(0x800);
		
		if(id.equals("guts") && !attacker.isHealthy() && move.isPhysical() ||
				id.equals("overgrow") && attacker.getHPFraction() <= 1/3.0 && move.getType().equals(Type.Grass) ||
				id.equals("blaze") && attacker.getHPFraction() <= 1/3.0 && move.getType().equals(Type.Fire) ||
				id.equals("torrent") && attacker.getHPFraction() <= 1/3.0 && move.getType().equals(Type.Water) ||
				id.equals("swarm") && attacker.getHPFraction() <= 1/3.0 && move.getType().equals(Type.Bug))
		{
			attackmod.add(0x1800);
		}
		
		else if(id.equals("flashfireactivated") && move.getType().equals(Type.Fire))
			attackmod.add(0x1800);
		
		else if(id.equals("solarpower") && field.isSun() && !move.isPhysical() ||
				id.equals("flowergift") && field.isSun() && move.isPhysical())
			attackmod.add(0x1800);
		
		else if(id.equals("defeatist") && attacker.getHPFraction() <= 0.5 ||
				id.equals("slowstart") && move.isPhysical())
			attackmod.add(0x800);
			
		else if(id.equals("hugepower") || id.equals("purepower") && move.isPhysical())
			attackmod.add(0x2000);
		
		String iid = attacker.getItem().getID();
		
		if(iid.equals("thickclub") && (attacker.getSpecies().getID().equals("cubone") || attacker.getSpecies().getID().equals("marowak"))
				&& move.isPhysical() || 
				(iid.equals("deepseatooth") && attacker.getSpecies().getID().equals("clamperl")
				&& !move.isPhysical()) || (iid.equals("lightball") && attacker.getSpecies().getID().equals("pikachu")))
				attackmod.add(0x2000);
		
		else if(iid.equals("souldew") && attacker.getSpecies().getID().matches("lati[oa]s") && !move.isPhysical() ||
				(iid.equals("choiceband") && move.isPhysical()) ||
				(iid.equals("choicespecs") && !move.isPhysical()))
		{
			attackmod.add(0x1800);
		}
		
		attack = Math.max(1,pokeRound(attack * chainMods(attackmod)/0x1000));
				
				
		
		if(move.usesPhysDef())
		{
			if(isCrit && defender.getBoosts()[1] > 1)
				defense = defender.getStats()[2];
			defense = defender.getStats()[2] * defender.getBoosts()[1];
		}
		else
		{
			if(isCrit && defender.getBoosts()[1] > 1)
				defense = defender.getStats()[2];
			defense = defender.getStats()[4] * defender.getBoosts()[3];
		}
		double defensemod = attack/defense;
		double levelmod = (attacker.getLevel()*2+10)/250.0;
		double before = levelmod * defensemod * basepower + 2;
		double mods = 1;
		if(attacker.getsStabFrom(move))
					mods *= 1500;
		
		double weakness = 1;
		//instead of just checking for two, i'm now checking for every type since trick-or-treat now exists
		for(Type t : defender.getTypes())
		{
			weakness *= getMoveEffectiveness(move,t,field.isForesight() || defenseab.getID().equals("scrappy"),field.isGravity());
		}
		if(weakness == 0)
			return new double[]{0,0};
		
		if((defenseab.getID().equals("wonderguard") && weakness <= 1) ||
			move.getType().equals(Type.Grass) && defenseab.getID().equals("sapsipper") || 
			 move.getType().equals(Type.Fire) && defenseab.getID().equals("flashfire") ||
			 move.getType().equals(Type.Water) && waterimmune.contains(defenseab) ||
			 move.getType().equals(Type.Electric) && eleimmune.contains(defenseab) ||
			 move.getType().equals(Type.Ground) && !field.isGravity() && defenseab.getID().equals("levitate") ||
			 bullets.contains(move) && defenseab.getID().equals("bulletproof") ||
			 sounds.contains(move) && defenseab.getID().equals("soundproof"))
		{
				return new double[]{0,0};
		}
		if(move.getType().equals(Type.Ground) && !field.isGravity() && defender.getItem().getID().equals("airballoon"))
			 return new double[]{0,0};
		
		if(mid.equals("seismictoss") || mid.equals("nightshade"))
		{
			int level = attacker.getLevel();
			if(attackab.getID().equals("attackab"))
				return new double[]{2 * level,2 * level};
			return new double[]{level,level};
		}
		
		
		mods *= weakness;
		if(isCrit)
			mods *= 1.5;
		mods *= ItemBoost.getOffensiveBoost(move,attacker.getItem(),attacker,defender); //offensive items, e.g. life orb
		mods *= ItemBoost.getDefensiveBoost(move,defender.getItem(),defender); //defensive items, e.g. assault vest

		for(FieldCondition fc : field.getSide2())
		{
			mods *= FieldConditionsBoost.getFieldConditionsBoost(fc, move, defender);
		}
		System.out.println("Move "+move.getName()+" by Pokemon "+attacker.getName()+" on Pokemon "+defender.getName()+": "+Arrays.toString(new double[]{before * mods * minMod,before * mods * maxMod}));
		return new double[]{before * mods * minMod,before * mods/1000 * maxMod};
	}
	
	private static Field checkAirLock(Ability a, Ability b, Field f)
	{
		if(!a.equals(Abilities.airlock) && !a.equals(Abilities.cloudnine) && !b.equals(Abilities.airlock) && !b.equals(Abilities.cloudnine))
			return f;
		//we know that weather needs to be removed
		f.removeWeather();
		return f;
	}
	
	private static Pokemon checkKlutz(Pokemon a)
	{
		if(a.getAbility().equals(Abilities.klutz))
			a.setItem(null);
		return a;
	}
	
	private static Field checkInfiltrator(Pokemon a, Field f)
	{
		if(a.getAbility().equals(Abilities.infiltrator))
			f.removeScreens();
		return f;
	}
	
	private static double getMoveEffectiveness(Move move, Type type, boolean hitsGhost, boolean isGravity)
	{
		if(hitsGhost && type.equals(Type.Ghost) && (move.getType().equals(Type.Normal) || move.getType().equals(Type.Fighting)))
			return 1;
		if(isGravity && type.equals(Type.Flying) && move.getType().equals(Type.Ground))
			return 1;
		if(move.getID().equals("freezedry") && type.equals(Type.Water))
			return 2;
		if(move.getID().equals("flyingpress"))
			return Type.getEffectiveness(Type.Fighting, type) * Type.getEffectiveness(Type.Flying,type);
		return Type.getEffectiveness(move.getType(), type);
	}
	
	/*
	 * function chainMods(mods) {
    var M = 0x1000;
    for(var i = 0; i < mods.length; i++) {
        if(mods[i] !== 0x1000) {
            M = ((M * mods[i]) + 0x800) >> 12;
        }
    }
    return M;
}
	 */
	
	public static int chainMods(ArrayList<Integer> mods)
	{
		int m = 0x1000;
		for(int i : mods)
		{
			if(i != 0x1000)
			{
				m = ((m * i) + 0x800) >> 12;
			}
		}
		return m;
	}
	
	public static int getFinalSpeed(Pokemon poke, Field field)
	{
		double speed = poke.getStats()[5] * poke.getBoosts()[5];
		String id = poke.getItem().getID();
		if(id.equals("choicescarf"))
			speed = Math.floor(speed * 1.5);
		else if(id.equals("machobrace") || id.equals("ironball"))
			speed = Math.floor(speed / 2.0);
		
		id = poke.getAbility().getID();
		if(id.equals("chlorophyll") && field.isSun() ||
				id.equals("sandrush") && field.isSandstorm() ||
				id.equals("swiftswim") && field.isRain())
			speed *= 2;
		return pokeRound(speed);
	}
	
	public static int pokeRound(double num)
	{
		return (num % 1 > 0.5) ? (int)Math.ceil(num) : (int)Math.floor(num);
	}
	
	public static void main(String[]args)
	{
		Pokemon attaque = new Pokemon(new SpeciesList().sylveon,"yourfaceon",Abilities.scrappy, Gender.Male, 100,Items.choicespecs,new int[]{331,149,166,350,296,156}, 331, new double[]{1,1,1,1,1,1,1}, null);
		Pokemon defense = new Pokemon(new SpeciesList().cofagrigus,"whourse",Abilities.runaway, Gender.Female, 100,Items.chopleberry,new int[]{394,167,256,140,394,166}, 394, new double[]{1,1,1,1,1,1,1}, null);
		Move move = Moves.stockpile;
		Field field = new Field();
		field.add1(FieldCondition.LightScreen);
		System.out.println(Arrays.toString(getSinglesDamage(attaque,defense,move, field,false)));
	}
}
