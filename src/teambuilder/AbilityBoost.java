package teambuilder;

public class AbilityBoost 
{
	public static final double tintedBoost = 2;
	public static final double lowHPBoost = 1.5;
	public static final double strongJawBoost = 1.5;
	public static final double technicianBoost = 1.5;
	public static final double sniperBoost = 1.5;
	public static final double gutsBoost = 1.5;
	public static final double adaptabilityBoost = 4/3.0;//1.5 is already there, so to increase to 2 just multiply by 4/3
	public static final double ateBoost = 1.3;//aerilate, pixilate, etc.
	public static final double noBoost = 1;
	public static final double drySkinFireBoost = 1.25;
	public static final double filterBoost = 0.75;
	public static final double defeatistBoost = 0.5;
	public static final double thickFatBoost = 0.5;
	public static final double immunity = 0;
	
	public static double getOffensiveBoost(Ability ability, Pokemon attack, Pokemon defense, Pokemon ally, Pokemon opponent, Move move, Field field,boolean isCrit)
	{
		if(ability.getID().equals("adaptability"))
		{
			if(attack.getsStabFrom(move))
				return adaptabilityBoost;
			return noBoost;
		}
		if(ability.getID().equals("analytic"))
		{
			if(ally == null)
			{
				if(opponent == null)
				{
					int speed1 = attack.getStats()[6];
					int speed2 = defense.getStats()[6];
					if(speed1 < speed2)
						return 4/3.0;
					return noBoost;
				}
				int speed1 = attack.getStats()[6];
				int speed2 = defense.getStats()[6];
				int speed3 = opponent.getStats()[6];
				if(speed1 < speed2 && speed1 < speed3)
					return 4/3.0;
				return noBoost;
			}
			if(opponent == null)
			{
				int speed1 = attack.getStats()[6];
				int speed2 = defense.getStats()[6];
				int speed3 = ally.getStats()[6];
				if(speed1 < speed2 && speed1 < speed3)
					return 4/3.0;
				return noBoost;
			}
			int speed1 = attack.getStats()[6];
			int speed2 = ally.getStats()[6];
			int speed3 = opponent.getStats()[6];
			int speed4 = defense.getStats()[6];
			if(speed1 < speed2 && speed1 < speed3 && speed1 < speed4)
				return 4/3.0;
			return noBoost;
		}
		if(ability.getID().equals("aerilate"))
		{
			if(move.getType().equals(Type.Normal) && !move.getID().equals("hiddenpower"))
			{
				if(defense.weaknessModifier(Type.Flying) != defense.weaknessModifier(Type.Normal))
					return defense.weaknessModifier(Type.Flying)*ateBoost;
				return ateBoost;
			}
			return noBoost;
		}
		if(ability.getID().equals("pixilate"))
		{
			if(move.getType().equals(Type.Normal) && !move.getID().equals("hiddenpower"))
			{
				if(defense.weaknessModifier(Type.Fairy) != defense.weaknessModifier(Type.Normal))
					return defense.weaknessModifier(Type.Fairy)*ateBoost;
				return ateBoost;
			}
			return noBoost;
		}
		if(ability.getID().equals("refrigerate"))
		{
			if(move.getType().equals(Type.Normal) && !move.getID().equals("hiddenpower"))
			{
				if(defense.weaknessModifier(Type.Ice) != defense.weaknessModifier(Type.Normal))
					return defense.weaknessModifier(Type.Ice)*ateBoost;
				return ateBoost;
			}
			return noBoost;
		}
		if(ability.getID().equals("normalize"))
		{
			double one = defense.weaknessModifier(move.getType());
			//undo bonuses/things from other moves
			return 1/one * defense.weaknessModifier(Type.Normal);
		}
		if(ability.getID().equals("blaze"))
		{
			if(move.getType().equals(Type.Fire) && attack.getHPFraction() < .33)
			{
				return lowHPBoost;
			}
		}
		if(ability.getID().equals("overgrow"))
		{
			if(move.getType().equals(Type.Grass) && attack.getHPFraction() < .33)
			{
				return lowHPBoost;
			}
		}
		if(ability.getID().equals("torrent"))
		{
			if(move.getType().equals(Type.Water) && attack.getHPFraction() < .33)
			{
				return lowHPBoost;
			}
		}
		if(ability.getID().equals("swarm"))
		{
			if(move.getType().equals(Type.Bug) && attack.getHPFraction() < .33)
			{
				return lowHPBoost;
			}
		}
		if(ability.getID().equals("hugepower") || ability.getID().equals("purepower"))
		{
			if(move.isPhysical())
				return 2;
			return noBoost;
		}
		if(ability.getID().equals("hustle"))
		{
			if(move.isPhysical())
				return 1.5;
			return noBoost;
		}
		if(ability.getID().equals("flowergift"))
		{
			if(move.isPhysical() && field.side1.contains(FieldCondition.Sun))
				return 1.5;
			return noBoost;
		}
		if(ability.getID().equals("unaware"))
		{
			if(move.isPhysical() && defense.getBoosts()[1] != 1)
				return defense.getBoosts()[0];
			if(!move.isPhysical() && attack.getBoosts()[3] != 1)
				return defense.getBoosts()[2];
			return noBoost;
		}
		if(ability.getID().equals("defeatist"))
		{
			if(attack.getHPFraction() < .5)
				return defeatistBoost;
			return noBoost;
		}
		if(ability.getID().equals("slowstart"))
		{
			if(move.isPhysical())
				return defeatistBoost;
			//if it's after 5 turns, the "finally got it going!" string will show
			//and we'll just remove any ability
			return noBoost;
		}
		if(ability.getID().equals("toughclaws"))
		{
			if(move.isContact())
				return 4/3.0;
			return noBoost;
		}
		if(ability.getID().equals("rivalry"))
		{
			Gender one = attack.gender();
			Gender two = defense.gender();
			if(one == two)
				return 5/4.0;
			if(two.equals(Gender.Unknown))
				return noBoost;
			return 3/4.0;
		}
		if(ability.getID().equals("sheerforce"))
		{
			if(move.hasSecondaryEffect())
				return 4/3.0;
			return noBoost;
		}
		if(ability.getID().equals("strongjaw"))
		{
			switch(move.getID())
			{
			case "bite":
			case "crunch":
			case "firefang":
			case "icefang":
			case "poisonfang":
			case "thunderfang":
				return strongJawBoost;
			default:
				return noBoost;
			}
		}
		if(ability.getID().equals("megalauncher"))
		{
			switch(move.getID())
			{
			case "aurasphere":
			case "darkpulse":
			case "dragonpulse":
			case "healpulse":
			case "waterpulse":
				return strongJawBoost;
			default:
				return noBoost;
			}
		}
		if(ability.getID().equals("ironfist"))
		{
			switch(move.getID())
			{
			case "bulletpunch":
			case "cometpunch":
			case "dizzypunch":
			case "drainpunch":
			case "dynamicpunch":
			case "firepunch":
			case "focuspunch":
			case "hammerarm":
			case "icepunch":
			case "machpunch":
			case "megapunch":
			case "meteormash":
			case "poweruppunch":
			case "shadowpunch":
			case "skyuppercut":
			case "thunderpunch":
				return 1.2;
			default:
				return noBoost;
			}
		}
		if(ability.getID().equals("reckless"))
		{
			switch(move.getID())
			{
			case "bravebird":
			case "doubleedge":
			case "flareblitz":
			case "headcharge":
			case "headsmash":
			case "highjumpkick":
			case "jumpkick":
			case "submission":
			case "takedown":
			case "volttackle":
			case "woodhammer":
			case "wildcharge":
				return 1.2;
			default:
				return noBoost;
			}
		}
		if(ability.getID().equals("technician"))
		{
			if(move.getBasePower() > 0 && move.getBasePower() < 60)
				return technicianBoost;
			return noBoost;
		}
		if(ability.getID().equals("tintedlens"))
		{
			if(defense.weaknessModifier(move.getType()) < 1)
				return tintedBoost;
			return noBoost;
		}
		if(ability.getID().equals("sniper"))
		{
			if(!isCrit)
				return noBoost;
			else return sniperBoost;
		}
		if(ability.getID().equals("fairyaura"))
		{
			if(move.getType().equals(Type.Fairy) && defense.getAbility().getID().equals("aurabreak"))
				return 0.75;
			if(move.getType().equals(Type.Fairy))
				return 1.3;
			else return noBoost;
		}
		if(ability.getID().equals("darkaura"))
		{
			if(move.getType().equals(Type.Dark) && defense.getAbility().getID().equals("aurabreak"))
				return 0.75;
			if(move.getType().equals(Type.Dark))
				return 1.3;
			else return noBoost;
		}
		if(ability.getID().equals("flareboost"))
		{
			if(!move.isPhysical() && attack.getStatus().contains(Status.Burn))
				return gutsBoost;
			else return noBoost;
		}
		if(ability.getID().equals("toxicboost"))
		{
			if(move.isPhysical() && attack.getStatus().contains(Status.Poison))
				return gutsBoost;
			else return noBoost;
		}
		if(ability.getID().equals("guts"))
		{
			if(move.isPhysical())
			{
				if(attack.getStatus().contains(Status.Poison) || attack.getStatus().contains(Status.Burn) || 
						attack.getStatus().contains(Status.Paralysis) || attack.getStatus().contains(Status.Sleep))
					return gutsBoost;
				else return noBoost;
			}
			else return noBoost;
		}
		if(ability.getID().equals("infiltrator"))
		{
			if(move.isPhysical() && field.side2.contains(FieldCondition.Reflect))
				return 2;
			if(!move.isPhysical() && field.side2.contains(FieldCondition.LightScreen))
				return 2;
			return noBoost;
		}
		if(ability.getID().equals("sandforce"))
		{
			if(field.side1.contains(FieldCondition.Sandstorm))
			{
				Type t = move.getType();
				if(t.equals(Type.Rock)||t.equals(Type.Steel)||t.equals(Type.Ground))
					return 1.5;
				return noBoost;
			}
			return noBoost;
		}
		if(ability.getID().equals("solarpower"))
		{
			if(field.side1.contains(FieldCondition.Sun))
			{
				if(!move.isPhysical())
					return 1.5;
				return noBoost;
			}
			return noBoost;
		}
		if(ability.getID().equals("parentalbond"))
		{
			if(move.getBasePower() == 0)
				return noBoost;
			if(move.getID().equals("poweruppunch"))
			{
				return 1.75;
			}
			return 1.5;
		}
		if(ability.getID().equals("plus"))
		{
			if(ally == null)
				return noBoost;
			if(ally.getAbility().getID().equals("minus") && !move.isPhysical())
				return 1.5;
			return noBoost;
		}
		if(ability.getID().equals("minus"))
		{
			if(ally == null)
				return noBoost;
			if(ally.getAbility().getID().equals("plus") && !move.isPhysical())
				return 1.5;
			return noBoost;
		}
		return noBoost;
	}
	public static double getDefensiveBoost(Ability ability, Pokemon attack, Pokemon defense,Pokemon ally, Pokemon opponent, Move move, Field field)
	{
		if(ability.getID().equals("levitate"))
		{
			if(attack.getAbility().getID().equals("moldbreaker") || 
					attack.getAbility().getID().equals("turboblaze") ||
					attack.getAbility().getID().equals("teravolt"))
				return noBoost;
			if(move.getType().equals(Type.Ground))
			{
				return immunity;
			}
			return noBoost;
		}
		if(ability.getID().equals("lightningrod") || ability.getID().equals("motordrive") || ability.getID().equals("voltabsorb"))
		{
			if(attack.getAbility().getID().equals("moldbreaker") || 
					attack.getAbility().getID().equals("turboblaze") ||
					attack.getAbility().getID().equals("teravolt"))
				return noBoost;
			if(move.getType().equals(Type.Electric))
			{
				return immunity;
			}
			return noBoost;
		}
		if(ability.getID().equals("stormdrain") || ability.getID().equals("waterabsorb"))
		{
			if(attack.getAbility().getID().equals("moldbreaker") || 
					attack.getAbility().getID().equals("turboblaze") ||
					attack.getAbility().getID().equals("teravolt"))
				return noBoost;
			if(move.getType().equals(Type.Water))
			{
				return immunity;
			}
			return noBoost;
		}
		if(ability.getID().equals("dryskin"))
		{
			if(move.getType().equals(Type.Water))
			{
				if(attack.getAbility().getID().equals("moldbreaker") || 
						attack.getAbility().getID().equals("turboblaze") ||
						attack.getAbility().getID().equals("teravolt"))
					return noBoost;
				return immunity;
			}
			if(move.getType().equals(Type.Fire))
				return drySkinFireBoost;
			return noBoost;
		}
		if(ability.getID().equals("sapsipper"))
		{
			if(attack.getAbility().getID().equals("moldbreaker") || 
					attack.getAbility().getID().equals("turboblaze") ||
					attack.getAbility().getID().equals("teravolt"))
			return noBoost;
			if(move.getType().equals(Type.Grass))
			{
				return immunity;
			}
			return noBoost;
		}
		if(ability.getID().equals("flashfire"))
		{
			if(attack.getAbility().getID().equals("moldbreaker") || 
					attack.getAbility().getID().equals("turboblaze") ||
					attack.getAbility().getID().equals("teravolt"))
				return noBoost;
			if(move.getType().equals(Type.Fire))
			{
				return immunity;
			}
			return noBoost;
		}
		if(ability.getID().equals("wonderguard"))
		{
			if(attack.getAbility().getID().equals("moldbreaker") || 
					attack.getAbility().getID().equals("turboblaze") ||
					attack.getAbility().getID().equals("teravolt"))
				return noBoost;
			if(!defense.isWeakTo(move))
			{
				return immunity;
			}
			return noBoost;
		}
		if(ability.getID().equals("thickfat"))
		{
			if(attack.getAbility().getID().equals("moldbreaker") || 
					attack.getAbility().getID().equals("turboblaze") ||
					attack.getAbility().getID().equals("teravolt"))
			return noBoost;
			if(move.getType().equals(Type.Fire) || move.getType().equals(Type.Ice))
			{
				return thickFatBoost;
			}
			return noBoost;
		}
		if(ability.getID().equals("thickfat"))
		{
			if(attack.getAbility().getID().equals("moldbreaker") || 
					attack.getAbility().getID().equals("turboblaze") ||
					attack.getAbility().getID().equals("teravolt"))
			return noBoost;
			if(move.getType().equals(Type.Fire))
			{
				return thickFatBoost;
			}
			return noBoost;
		}
		if(ability.getID().equals("grasspelt"))
		{
			if(move.isPhysical() && field.getSide1().contains(FieldCondition.GrassyTerrain))
			{
				return thickFatBoost;
			}
			return noBoost;
		}
		if(ability.getID().equals("unaware"))
		{
			if(attack.getAbility().getID().equals("moldbreaker") || 
					attack.getAbility().getID().equals("turboblaze") ||
					attack.getAbility().getID().equals("teravolt"))
				return noBoost;
			if(move.isPhysical() && attack.getBoosts()[0] != 1)
				return 1/(double)attack.getBoosts()[0];
			if(!move.isPhysical() && attack.getBoosts()[2] != 1)
				return 1/(double)attack.getBoosts()[2];
			return noBoost;
		}
		if(ability.getID().equals("bulletproof"))
		{
			if(attack.getAbility().getID().equals("moldbreaker") || 
					attack.getAbility().getID().equals("turboblaze") ||
					attack.getAbility().getID().equals("teravolt"))
				return noBoost;
			switch(move.getID())
			{
			case "acidspray":
			case "aurasphere":
			case "barrage":
			case "bulletseed":
			case "eggbomb":
			case "electroball":
			case "focusblast":
			case "gyroball":
			case "iceball":
			case "magnetbomb":
			case "mistball":
			case "mudbomb":
			case "octazooka":
			case "rockwrecker":
			case "searingshot":
			case "seedbomb":
			case "shadowball":
			case "sludgebomb":
			case "weatherball":
			case "zapcannon":
				return immunity;
			default:
				return noBoost;
			}
		}
		if(ability.getID().equals("soundproof"))
		{
			if(attack.getAbility().getID().equals("moldbreaker") || 
					attack.getAbility().getID().equals("turboblaze") ||
					attack.getAbility().getID().equals("teravolt"))
				return noBoost;
			switch(move.getID())
			{
			case "bugbuzz":
			case "chatter":
			case "echoedvoice":
			case "grasswhistle":
			case "growl":
			case "healbell":
			case "hypervoice":
			case "metalsound":
			case "perishsong":
			case "relicsong":
			case "roar":
			case "round":
			case "screech":
			case "sing":
			case "snarl":
			case "snore":
			case "supersonic":
			case "uproar":
				return immunity;
			default:
				return noBoost;
			}
		}
		if(ability.getID().equals("filter") || ability.getID().equals("solidrock"))
		{
			if(attack.getAbility().getID().equals("moldbreaker") || 
					attack.getAbility().getID().equals("turboblaze") ||
					attack.getAbility().getID().equals("teravolt"))
				return noBoost;
			if(defense.weaknessModifier(move.getType()) > 1)
				return filterBoost;
			return noBoost;
		}
		if(ability.getID().equals("fairyaura"))
		{
			if(move.getType().equals(Type.Fairy) && attack.getAbility().getID().equals("aurabreak"))
				return 0.75;
			if(move.getType().equals(Type.Fairy))
				return 1.3;
			return noBoost;
		}
		if(ability.getID().equals("darkaura"))
		{
			if(move.getType().equals(Type.Dark) && attack.getAbility().getID().equals("aurabreak"))
				return 0.75;
			if(move.getType().equals(Type.Dark))
				return 1.3;
			return noBoost;
		}
		if(ability.getID().equals("furcoat"))
		{
			if(move.usesPhysDef())
			{
				return 0.5;
			}
			return noBoost;
		}
		if(ability.getID().equals("marvelscale"))
		{
			if(attack.getAbility().getID().equals("moldbreaker") || 
					attack.getAbility().getID().equals("turboblaze") ||
					attack.getAbility().getID().equals("teravolt"))
				return noBoost;
			if(move.isPhysical())
			{
				if(attack.getStatus().contains(Status.Poison) || attack.getStatus().contains(Status.Burn) || 
						attack.getStatus().contains(Status.Paralysis) || attack.getStatus().contains(Status.Sleep)
						|| attack.getStatus().contains(Status.Freeze))
					return 1/gutsBoost;
				else return noBoost;
			}
			else return noBoost;
		}
		if(ability.getID().equals("multiscale"))
		{
			if(attack.getAbility().getID().equals("moldbreaker") || 
					attack.getAbility().getID().equals("turboblaze") ||
					attack.getAbility().getID().equals("teravolt"))
				return noBoost;
			if(defense.getHPFraction() == 1)
				return 0.5;
			return noBoost;
		}
		if(ability.getID().equals("telepathy"))
		{
			if(ally == null)
				return noBoost;
			//swag idfk how to do this
			return noBoost;
		}
		if(ability.getID().equals("flowergift"))
		{
			if(attack.getAbility().getID().equals("moldbreaker") || 
					attack.getAbility().getID().equals("turboblaze") ||
					attack.getAbility().getID().equals("teravolt"))
				return noBoost;
			if(!move.isPhysical() && field.side1.contains(FieldCondition.Sun))
				return 2/3.0;
			return noBoost;
		}
		return noBoost;
	}
}
