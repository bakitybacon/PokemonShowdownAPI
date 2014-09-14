package messages;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import server.Format;
import teambuilder.Abilities;
import teambuilder.Ability;
import teambuilder.Gender;
import teambuilder.Item;
import teambuilder.Items;
import teambuilder.Moves;
import teambuilder.Pokemon;
import teambuilder.Species;
import teambuilder.SpeciesList;
import teambuilder.Status;
import teambuilder.Team;
import connect.User;

public class BattleRoom extends Room
{

	String id;
	public ArrayList<String> messages = new ArrayList<String>();
	String title;
	public boolean leavesOn;
	public boolean joinsOn;
	User player1 = new User();
	User player2 = new User();
	int gen = 1;
	Format format = new Format("OU", false, false, false);
	boolean isSingles = true;
	Team team1 = new Team();
	Team team2 = new Team();
	Pokemon currentpoke1 = new Pokemon(SpeciesList.placeholder);
	Pokemon currentpoke2 = new Pokemon(SpeciesList.placeholder);
	public static final HashMap<String, String> battlestats = new HashMap<>();
	static
	{
		battlestats.put("atk","attack");
		battlestats.put("def","defense");
		battlestats.put("spa","special attack");
		battlestats.put("spd","special defense");
		battlestats.put("spe","speed");
		battlestats.put("evasion","evasion");
		battlestats.put("accuracy","accuracy");
	}
	
	public BattleRoom(String id, String title)
	{
		this.id = id;
		this.title = title;
	}
	
	public void add(String message)
	{
		messages.add(message);
	}
	
	
	public String getID()
	{
		return id;
	}
	
	public String getTitle()
	{
		return title;
	}

	@Override
	public ArrayList<String> getMessages() 
	{
		return messages;
	}

	@Override
	public void join(String who) 
	{
		if(joinsOn)
			rawMessage(who + " joined.");
	}

	@Override
	public void leave(String who) 
	{
		if(leavesOn)
			rawMessage(who + " left.");
	}
	
	public void suppressLeaveJoins()
	{
		joinsOn = false;
		leavesOn = false;
	}

	public void rawMessage(String message)
	{
		add(message);
	}

	@Override
	public void chatMessage(String user, String message) 
	{
		add(user + ":" + message);
	}

	@Override
	public void deltaMessage(final String user, final String message, final int delta) 
	{
		//i'm not planning on adding timestamps
		chatMessage(user, message);
	}

	@Override
	public void htmlMessage(String message) 
	{
		// this will be implemented in the GUI
		rawMessage(message);
	}
	
	public void joinMessage(String who)
	{
		if(joinsOn)
			rawMessage(who + " joined.");
	}
	public void leaveMessage(String who)
	{
		if(leavesOn)
			rawMessage(who + " left.");
	}
	
	public void playerMessage(String num, String name, String avatar)
	{
		int avatarnum = 1;
		int pnum = 1;
		if(num.equals("p2"))
			pnum = 2;
		if(!avatar.replaceAll("[0-9]","").equals(""))
			avatarnum = Integer.parseInt(avatar);
		if(pnum == 1)
		{
			player1 = new User(name,avatarnum);
		}
		else player2 = new User(name,avatarnum);
	}
	
	
	
	public void generalMessage(String message)
	{
		//used so that this method can delegate to others
		//so we don't have like a 30 way catch in the main loop
		//TODO this
		
	}
	
	public void setFormat(Format f)
	{
		format = f;
	}
	
	public void tierMessage(String message)
	{
		rawMessage("Format: "+message);
	}
	
	public void ruleMessage(String message)
	{
		rawMessage("Rule: "+message);
	}
	
	public void ratedMessage()
	{
		rawMessage("Rated");
	}
	
	public void pokeMessage(String player, String data)
	{
		if(player.matches("^p1"))
		{
			team1.add(parseData(data));
			System.out.println(team1);
		}
		else if (player.matches("^p2"))
		{
			team2.add(parseData(data));
		}
	}
	
	
	
	public void gametypeMessage(String message)
	{
		if(message.equals("singles"))
		{
			isSingles = true;
		}
	}
	
	public void damageMessage(String pokeString,String amount, String from, String of)
	{
		Pokemon pokemon = getPokemon(pokeString);
		String effect  = from.toLowerCase().replaceAll("[^a-z0-9]","");
		effect = cleanEffect(effect);
		if(from != null && !from.equals(""))
		{
			switch(effect)
			{
				case "stealthrock":
					rawMessage("Pointed stones dug into "+pokemon.getName() +"!");
					break;
				case "spikes":
					rawMessage(pokemon.getName() +" was hurt by spikes!");
					break;
				case "brn":
					rawMessage(pokemon.getName() +" was hurt by its burn!");
					break;
				case "psn":
					rawMessage(pokemon.getName() +" was hurt by poison!");
					break;
				case "lifeorb":
					rawMessage(pokemon.getName() +" lost some of its HP!");
					pokemon.setItem(Items.lifeorb);
					break;
				case "recoil":
					rawMessage(pokemon.getName() +" is damaged by recoil!");
					break;
				case "sandstorm":
					rawMessage(pokemon.getName() +" is buffeted by the sandstorm!");
					break;
				case "hail":
					rawMessage(pokemon.getName() +" is buffeted by the hail!");
					break;
				case "baddreams":
					rawMessage(pokemon.getName() +" is tormented!");
					break;
				case "nightmare":
					rawMessage(pokemon.getName() +" is locked in a nightmare!");
					break;
				case "confusion":
					rawMessage("It hurt itself in its confusion!");
					break;
				case "leechseed":
					rawMessage(pokemon.getName() +"'s health was sapped by Leech Seed!");
					break;
				case "flameburst":
					rawMessage("The bursting flame hit " +pokemon.getName() + "!");
					break;
				case "firepledge":
					rawMessage(pokemon.getName() + " is hurt by the sea of fire!");
					break;
				case "jumpkick":
				case "highjumpkick":
					rawMessage(pokemon.getName() + " kept going and crashed!");
					break;
				default:
					if(of != null && !of.equals(""))
					{
						rawMessage(pokemon.getName() + " is hurt by " + of + "'s " + from + "!");
					}
					else
						rawMessage(pokemon.getName() + " is hurt by "+from+"!");
			}
		}
		else 
		{
			rawMessage(pokemon.getName() + " lost " + parseDamage(pokemon.getHPFraction(),amount)+"% of its health!");
		}
		String[] damages = amount.split("/");
		double one = Double.parseDouble(damages[0]);
		double two = Double.parseDouble(damages[1]);
		double quo = one/two;
		int newhp = (int)(quo * pokemon.getStats()[0]);
		pokemon.setHP(newhp);
	}
	
	
	public void healMessage(String pokeString, String amount, String from, String of, String others)
	{
		Pokemon pokemon = getPokemon(pokeString);
		
		if(from != null && !from.equals(""))
		{
			String effect  = from.toLowerCase().replaceAll("[^a-z0-9]","");
			effect = cleanEffect(effect);
			switch(effect)
			{
			case "ingrain":
				rawMessage(pokemon.getName() + " absorbed nutrients through its roots!");
				break;
			case "aquaring":
				rawMessage("Aqua Ring restored "+pokemon.getName() + "'s HP!");
				break;
			case "raindish":
			case "dryskin":
			case "icebody":
				rawMessage(pokemon.getName()+"'s "+effect+" heals it!");
				pokemon.setAbility(Abilities.getByID(effect));
				break;
			case "healingwish":
				rawMessage("The healing wish came true for "+pokemon.getName()+"!");
				break;
			case "lunardance":
				rawMessage(pokemon.getName()+" became cloaked in mystical moonlight!");
				break;
			case "wish":
				String[] effects = others.split("\\:");
				for(String str : effects)
				{
					if(str.contains("[wisher]"))
						rawMessage(str.substring(9) + "'s wish came true!");
				}
				break;
			case "drain":
				String ofpoke = of.replaceAll("[\\s\\:]","").substring(3);
				rawMessage(ofpoke + " had its energy drained!");
				break;
			case "shellbell":
				rawMessage(pokemon.getName()+" restored a little of its HP using its Shell Bell!");
				pokemon.setItem(Items.shellbell);
				break;
			case "leftovers":
				rawMessage(pokemon.getName()+" restored a little of its HP using its Leftovers!");
				pokemon.setItem(Items.leftovers);
				break;
			default:
				effects = others.split("\\:");
				for(String str : effects)
				{
					if(str.contains("[absorb]"))
					{
						rawMessage(pokemon.getName() + "'s " + effect + " absorbs the attack!");
						break;
					}
				}
				if(effects.length == 0)
					rawMessage(pokemon.getName() + " restored some health!");
				else rawMessage(pokemon.getName() + " restored HP using its " + effect + "!");
			}
			
		}
		String[] damages = amount.split("/");
		double one = Double.parseDouble(damages[0]);
		double two = Double.parseDouble(damages[1]);
		double quo = one/two;
		int newhp = (int)(quo * pokemon.getStats()[0]);
		pokemon.setHP(newhp);
	}
	
	public void setHPMessage(String poke1, String health1, String poke2, String health2, String from)
	{
		Pokemon pokemon1 = getPokemon(poke1);
		Pokemon pokemon2 = getPokemon(poke2);
		if(from.contains("painsplit"))
			rawMessage("The battlers shared their pain!");
		String[] damages1 = health1.split("/");
		double one1 = Double.parseDouble(damages1[0]);
		double two1 = Double.parseDouble(damages1[1]);
		double quo1 = one1/two1;
		int newhp1 = (int)(quo1 * pokemon1.getStats()[0]);
		pokemon1.setHP(newhp1);
		String[] damages2 = health2.split("/");
		double one2 = Double.parseDouble(damages2[0]);
		double two2 = Double.parseDouble(damages2[1]);
		double quo2 = one2/two2;
		int newhp2 = (int)(quo2 * pokemon2.getStats()[0]);
		pokemon2.setHP(newhp2);
	}
	
	public void boostMessage(String pokeString,String stat,String amounts, String from)
	{
		int which = getStatIndex(stat);
		int amount = Integer.parseInt(amounts);
		
		Pokemon pokemon = getPokemon(pokeString);
		
		String level = ""; //sharply for 2, drastically for 3-6, nothing for 1
		if(amount == 2 || amount == -2)
			level = " sharply";
		else if (amount >= 3 || amount <= -3)
			level = " drastically";
		String direction = " raised ";
		if(amount < 0)
			direction = " lowered ";
		if(from != null && !from.equals(""))
		{
			if(from.contains("item"))
			{
				rawMessage("The "+from.substring(4)+ level + direction+pokemon.getName()
						+"'s "+battlestats.get(stat)+"!");
			}
			else rawMessage(pokemon.getName()+"'s "+cleanEffect(from)+level+direction+"its "+
					battlestats.get(stat)+"!");
		}
		else if(amount > 0 )
		{
				rawMessage(pokemon.getName() + "'s " + battlestats.get(stat) + level + " rose!");
		}
		else rawMessage(pokemon.getName() + "'s " + battlestats.get(stat) + level + " fell!");
		
		pokemon.changeStat(which,amount);
	}
	
	public void unboostMessage(String pokeString,String stat,String amounts, String from)
	{
		boostMessage(pokeString,stat,"-"+amounts,from);
	}
	
	public void setBoostMessage(String pokeString, String stat, String amount, String from, String of)
	{
		Pokemon pokemon = getPokemon(pokeString);
		pokemon.changeStat(getStatIndex(stat), Integer.parseInt(amount));
		if(from != null && !from.equals(""))
		{
			switch(cleanEffect(from))
			{
			case "bellydrum":
				rawMessage(pokemon.getName() + " cut its own HP and maximized its Attack!");
				break;
			case "angerpoint":
				rawMessage(pokemon.getName() + " maximized its Attack!");
				pokemon.setAbility(Abilities.angerpoint);
				break;
			}
		}
	}
	
	public void swapBoostMessage(String poke1, String poke2, String stats, String from)
	{
		Pokemon pokemon1 = getPokemon(poke1);
		Pokemon pokemon2 = getPokemon(poke2);
		ArrayList<Integer> statarray = new ArrayList<>();
		for(String s : stats.split("\\,"))
		{
			statarray.add(getStatIndex(s));
		}
		for(int i = 0; i < statarray.size();i ++)
		{
			int f = statarray.get(i);
			double temp = pokemon1.getBoosts()[f];
			pokemon1.setBoost(f, pokemon2.getStats()[i]);
			pokemon2.setBoost(f, temp);
		}
		if(from != null && !from.equals(""))
		{
			switch(cleanEffect(from))
			{
			case "guardswap":
				rawMessage(pokemon1.getName() + " switched all changes to its Defense and Sp. Def with the target!");
				break;
			case "heartswap":
				rawMessage(pokemon1.getName() + " switched stat changes with the target!");
				break;
			case "powerswap":
				rawMessage(pokemon1.getName() + " switched all changes to its Attack and Sp. Atk with the target!");
				break;
			}
		}
	}
	
	public void restoreBoostMessage(String pokeString)
	{
		Pokemon pokemon = getPokemon(pokeString);
		for(int i = 0; i < 7; i++)
		{
			if(pokemon.getBoosts()[i] < 0)
				pokemon.setBoost(i,1);
		}
		
	}
	public void copyBoostMessage(String poke1, String poke2, String stats, String effect)
	{
		Pokemon pokemon1 = getPokemon(poke1);
		Pokemon pokemon2 = getPokemon(poke2);
		for(int i = 0; i < 7; i ++)
		{
			pokemon1.setBoost(i,pokemon2.getBoosts()[i]);
		}
		rawMessage(pokemon1.getName() + " copied "+pokemon2.getName()+"'s stat changes!");
	}
	public void clearBoostMessage(String pokeString)
	{
		Pokemon pokemon = getPokemon(pokeString);
		for(int i = 0; i < 7; i ++)
			pokemon.setBoost(i,1);
		rawMessage(pokemon.getName() + "'s stat changes were removed!");
	}
	public void invertBoostMessage(String pokeString)
	{
		Pokemon pokemon = getPokemon(pokeString);
		for(int i = 0; i < 7; i ++)
			pokemon.setBoost(i,1.0/pokemon.getBoosts()[i]);
		rawMessage(pokemon.getName() + "'s stat changes were inverted!");
	}
	public void clearAllBoostMessage()
	{
		for(Pokemon p : team1)
			p.clearBoosts();
		for(Pokemon p : team2)
			p.clearBoosts();
		rawMessage("All stat changes were eliminated!");
	}
	
	public void critMessage()
	{
		rawMessage("A critical hit!");
	}
	
	public void superEffectiveMessage()
	{
		rawMessage("It's super effective!");
	}

	public void resistedMessage()
	{
		rawMessage("It's not very effecitve...");
	}
	public void immuneMessage(String pokeString, String effect, String other)
	{
		Pokemon pokemon = getPokemon(pokeString);
		if(effect.toLowerCase().equals("confusion"))
		{
			rawMessage(pokemon.getName() + " doesn't become confused!");
			return;
		}
		if(other != null && !other.equals(""))
		{
			String[] effs = other.split(",");
			for(String f : effs)
			{
				if(f.contains("msg"))
				{
					rawMessage("It doesn't affect "+pokemon.getName()+"...");
					return;
				}
			}
		}
		else rawMessage("It had no effect!");
	}
	
	public void missMessage(String pokeString, String targstring)
	{
		Pokemon pokemon = getPokemon(pokeString);
		if(targstring != null && !targstring.equals(""))
		{
			Pokemon target = getPokemon(targstring);
			rawMessage(target.getName() + " avoided the attack!");
		}
		else rawMessage(pokemon.getName() + "'s attack missed!");
		
	}
	
	public void failMessage(String pokeString, String effect, String from, String others)
	{
		Pokemon pokemon = getPokemon(pokeString);
		switch(cleanEffect(effect))
		{
		case "brn":
			rawMessage(pokemon.getName() + " is already burned.");
			break;
		case "tox":
		case "psn":
			rawMessage(pokemon.getName() + " is already poisoned.");
			break;
		case "slp":
			if(cleanEffect(from).equals("uproar"))
				rawMessage(pokemon.getName() + " can't sleep in an uproar!");
			else rawMessage(pokemon.getName() + " is already asleep.");
			break;
		case "frz":
			rawMessage(pokemon.getName() + " is already frozen.");
			break;
		case "substitute":
			if(others.contains("[weak]"))
				rawMessage(pokemon.getName() + " was too weak to make a substitute!");
			else rawMessage(pokemon.getName() + " already has a substitute!");
			break;
		case "skydrop":
			if(others.contains("[heavy]"))
				rawMessage(pokemon.getName() + " is too heavy to be lifted!");
			else rawMessage("But it failed!");
			break;
		case "unboost":
			rawMessage(pokemon.getName() + "'s stats were not lowered!");
		default:
			rawMessage("But it failed!");
		}
	}
	
	public void noTargetMessage()
	{
		rawMessage("But there was no target!");
	}
	
	public void ohkoMessage()
	{
		rawMessage("It's a one-hit KO!");
	}

	public void hitCountMessage(String poke, String amount)
	{
		rawMessage("Hit " + amount + " time(s)!");
	}
	public void nothingMessage()
	{
		rawMessage("But nothing happened!");
	}
	public void waitingMessage(String mon1, String mon2)
	{
		Pokemon poke1 = getPokemon(mon1);
		Pokemon poke2 = getPokemon(mon2);
		rawMessage(poke1.getName() + " is waiting for "+poke2.getName() + "'s move...");
	}

	public void combineMessage()
	{
		rawMessage("The two moves joined! It's a combined move!");
	}
	
	public void prepareMessage()
	{
		//TODO this seems to be something to do with animation 
		//on the main server
	}
	
	public void statusMessage(String pokeString, String status, String from)
	{
		Pokemon pokemon = getPokemon(pokeString);
		switch(status)
		{
		case "brn":
			if(from != null && !from.equals(""))
				rawMessage(pokemon.getName() + " was burned by the "+cleanEffect(from)+"!");
			else rawMessage(pokemon.getName()+" was burned!");
			pokemon.addStatus(Status.Burn);
			break;
		case "tox":
			if(from != null && !from.equals(""))
				rawMessage(pokemon.getName() + " was badly poisoned by the "+cleanEffect(from)+"!");
			else rawMessage(pokemon.getName()+" was badly poisoned!");
			pokemon.addStatus(Status.Toxic);
			break;
		case "psn":
			if(from != null && !from.equals(""))
				rawMessage(pokemon.getName() + " was poisoned by the "+cleanEffect(from)+"!");
			else rawMessage(pokemon.getName()+" was poisoned!");
			pokemon.addStatus(Status.Poison);
			break;
		case "slp":
			if(from != null && !from.equals(""))
			{
				if(from.equals("rest"))
					rawMessage(pokemon.getName() + " slept and became healthy!");
				else rawMessage(pokemon.getName() + " was badly poisoned by the "+cleanEffect(from)+"!");
			}
			pokemon.addStatus(Status.Sleep);
			break;
		case "par":
			rawMessage(pokemon.getName() + " is paralyzed! It may be unable to move!");
			pokemon.addStatus(Status.Paralysis);
			break;
		case "frz":
			rawMessage(pokemon.getName() + "  was frozen solid!");
			pokemon.addStatus(Status.Freeze);
			break;
		}
	}
	
	
	public void cureStatusMessage(String pokeString, String status, String from, String of)
	{
		Pokemon poke = getPokemon(pokeString);
		Pokemon ofpoke = getPokemon(of);
		poke.clearStatus();
		if(from != null && !from.equals(""))
		{
			switch(cleanEffect(from))
			{
			case "psychoshift":
				rawMessage(poke.getName() + " moved its status onto "+ofpoke + " !");
			default:
				rawMessage(poke.getName() + "'s "+from+" heals its status!");
			}
		}
		else switch(status.toLowerCase().replaceAll("[^a-z0-9]",""))
		{
		case "brn":
			if(from.startsWith("item"))
			{
				rawMessage(poke.getName() + "'s " + cleanEffect(from) + " healed its burn!");
			}
			else rawMessage(poke.getName() + " was healed of its burn!");
			break;
		case "psn":
		case "tox":
			if(from.startsWith("item"))
			{
				rawMessage(poke.getName() + "'s " + cleanEffect(from) + " healed its poisoning!");
			}
			else rawMessage(poke.getName() + " was healed of its poisoning!");
			break;
		case "slp":
			if(from.startsWith("item"))
			{
				rawMessage(poke.getName() + "'s " + cleanEffect(from) + " woke it up!");
			}
			else rawMessage(poke.getName() + "'s paralysis was cured!");
			break;
		case "frz":
			if(from.startsWith("item"))
			{
				rawMessage(poke.getName() + "'s " + cleanEffect(from) + " defrosted it!");
			}
			else rawMessage(poke.getName() + " thawed out!");
			break;
		case "par":
			if(from.startsWith("item"))
			{
				rawMessage(poke.getName() + "'s " + cleanEffect(from) + " cured its paralysis!");
			}
			else rawMessage(poke.getName() + " woke up!");
			break;
		}
	}
	
	public void cureTeamMessage(String pokeString, String effect)
	{
		Pokemon poke = getPokemon(pokeString);
		if(team1.contains(poke))
			for(Pokemon p : team1)
				p.clearStatus();
		switch(cleanEffect(effect))
		{
		case "healbell":
			rawMessage("A bell chimed!");
			break;
		case "aromatherapy":
			rawMessage("A soothing aroma wafted through the area!");
			break;
		default:
			rawMessage(poke.getName()+ "'s team was cured!");
		}
	}
	
	public void itemMessage(String pokeString, String itemstr, String from, String of)
	{
		// a lot of item detection happens here
		
		Pokemon poke = getPokemon(pokeString);
		Pokemon ofpoke = getPokemon(of);
		Item items = Items.getByID(itemstr);
		String item = items.getID();
		
		if(from != null && !from.equals(""))
		{
			switch(cleanEffect(from))
			{
			case "recycle":
			case "pickup":
				rawMessage(poke.getName() + " found one "+item+"!");
				poke.setItem(items);
				break;
			case "frisk":
				rawMessage(ofpoke.getName() + " frisked its target and found one "+item);
				poke.setItem(items);
				break;
			case "thief":
			case "covet":
				rawMessage(poke.getName() + " stole "+ofpoke.getName()+"'s "+item+"!");
				poke.setItem(items);
				ofpoke.setItem(null);
				break;
			case "harvest":
				rawMessage(poke.getName() + " harvested one "+item+"!");
				poke.setItem(items);
				break;
			case "bestow":
				rawMessage(poke.getName() + " received "+item+" from "+ofpoke.getName()+"!");
				poke.setItem(items);
				ofpoke.setItem(null);
				break;
			default:
				rawMessage(poke.getName() + " obtained one "+item+".");
				poke.setItem(items);
				break;
			}
		}
		else if(item.toLowerCase().replaceAll("[^a-z0-9]","").equals("airballoon"))
		{
			rawMessage(poke.getName() + " floats in the air with its Air Balloon!");
			poke.setItem(items);
		}
	}
	public void endItemMessage()
	{
		//TODO
	}
	public void abilityMessage()
	{
		//TODO
	}
	public void endAbilityMessage()
	{
		//TODO
	}
	public void transformMessage()
	{
		//TODO
	}
	public void formeChangeMessage()
	{
		//TODO
	}
	public void startMessage()
	{
		//TODO
	}
	public void endMessage()
	{
		//TODO
	}
	
	public void singleTurnMessage(String pokeString, String effect, String from, String of)
	{
		Pokemon pokemon = getPokemon(pokeString);
		if(effect != null && !effect.equals(""))
		{
			switch(cleanEffect(effect))
			{
			case "roost":
				rawMessage(pokemon.getName()+" landed on the ground!");
				break;
			case "quickguard":
				rawMessage("Quick Guard protected "+pokemon.getName()+"'s team!");
				break;
			case "wideguard":
				rawMessage("Wide Guard protected "+pokemon.getName()+"'s team!");
				break;
			case "protect":
				rawMessage(pokemon.getName() + " protected itself!");
				break;
			case "endure":
				rawMessage(pokemon.getName() + " braced itself!");
				break;
			case "helpinghand":
				rawMessage(of + " is read to help"+pokemon.getName());
				break;
			case "focuspunch":
				rawMessage(pokemon.getName() + " is tightening its focus!");
				break;
			case "snatch":
				rawMessage(pokemon.getName() + " waits for a target to make a move!");
				break;
			case "magiccoat":
				rawMessage(pokemon.getName() + " shrouded itself with Magic Coat!");
				break;
			case "matblock":
				rawMessage(pokemon.getName() + " intends to flip up a mat and block incoming attacks!");
				break;
			case "electrify":
				rawMessage(pokemon.getName() + "'s moves have been electrified!");
				break;
			}
		}
		pokemon.addMove(Moves.getByID(effect));
	}
	public void activateMessage()
	{
		//TODO
	}
	
	public void sideStartMessage()
	{
		//TODO
	}
	public void sideEndMessage()
	{
		//TODO
	}
	public void weatherMessage()
	{
		//TODO
	}
	public void fieldStartMessage()
	{
		//TODO
	}
	public void fieldEndMessage()
	{
		//TODO
	}
	public void fieldActivateMessage()
	{
		//TODO
	}
	public void message()
	{
		//TODO
	}
	public void animMessage()
	{
		//TODO
	}
	public void hintMessage()
	{
		//TODO
	}
	
	public void turnMessage(String which)
	{
		rawMessage("Turn "+which);
	}
	
	private String cleanEffect(String effect) 
	{
		if(effect.startsWith("item"))
			effect = effect.substring(4,effect.length());
		else if(effect.startsWith("ability"))
			effect = effect.substring(7,effect.length());
		else if(effect.startsWith("move"))
			effect = effect.substring(4,effect.length());
		return effect;
	}
	
	private int getStatIndex(String stat)
	{
		switch(stat.toLowerCase())
		{
		case "atk":
			return 0;
		case "def":
			return 1;
		case "satk":
		case "spa":
			return 2;
		case "sdef":
		case "spd":
			return 3;
		case "spe":
			return 4;
		case "evasion":
			return 5;
		case "accuracy":
			return 6;

		}
		return 0;
	}

	public void switchMessage(String player, String pokeid, String health)
	{
		player = player.replaceAll("\\s","");
		pokeid = pokeid.replaceAll("\\s","");
		String[] args = player.split("\\:");
		player = args[0];
		String playstring = (player.equals("p1a") ? "Go! " : player2.getName() + " sent out ");
		String nick = args[1];
		String species = pokeid.split(",")[0];
		if(!nick.equals(species))
			playstring += nick + "("+species+")!";
		else playstring += species + "!";
		rawMessage(playstring);
		
		Pokemon pokemon = getPokemon(pokeid);
		pokemon.clearBoosts();
	}
	
	public String moveMessage(String pokeid, String move, String target)
	{
		pokeid = pokeid.replaceAll("\\s","");
		target = target.replaceAll("\\s","");
		String[] args = pokeid.split("\\:");
		String playstring = (args[0].equals("p1a") ? "" : "The opposing ");
		String poke = args[1];
		playstring += poke;
		playstring += " used ";
		playstring += move + "!";
		return playstring;
	}

	@Override
	public void privateMessage(String to, String from, String msg) 
	{
		rawMessage("(Private to " + to + ") "+from+":"+msg);
	}
	
	private Pokemon getPokemon(String pokeString)
	{
		pokeString = pokeString.toLowerCase();
		pokeString = pokeString.replaceAll("[^a-z0-9\\:]","");
		String[] pokedata = pokeString.split("\\:",2);
		String player = pokedata[0];
		Pokemon pokemon = new Pokemon(SpeciesList.placeholder);
		if(player.matches("^p1[a-f]$"))
		{
			pokemon = team1.getByID(new SpeciesList().getFromName(pokedata[1]));	
		}
		else pokemon = team2.getByID(new SpeciesList().getFromName(pokedata[1]));
		return pokemon;
	}
	
	private String parseDamage(double hpFraction, String amount) 
	{
		//amount is always a quotient, so just divide on the /
		String[] nums = amount.split("\\/");
		double first = Double.parseDouble(nums[0]);
		double second = Double.parseDouble(nums[1]);
		
		double quotient = first/second;
		
		if(second <= 100)
		{
			DecimalFormat df = new DecimalFormat("#"); 

			return df.format((hpFraction - quotient) * 100);
		}
		else
		{
			DecimalFormat df = new DecimalFormat("#.#"); 

			return df.format((hpFraction - quotient) * 100);
		}
	}
	
	private static Pokemon parseData(String data)
	{
		data = data.replaceAll("\\s","");
		String[] datar = data.split(",");
		//this is a pretty annoying thing since data can be left out
		//first is always species, second can either be gender or level or shiny
		//most of the time there is no level, but it'd be 3
		String speciesstr = datar[0];
		Gender gender = Gender.Unknown;
		int level = 100;
		for(int i = 1;i < datar.length; i++ )
		{
			if(datar[i].matches("^[0-9]+$"))
			{
				//this is only numbers, so it has to be a level
				level = Integer.parseInt(datar[1]);
			}
			else if(datar[i].matches("^[MF]$"))
			{
				//gender
				if(datar[i].equals("M"))
					gender = Gender.Male;
				else gender = Gender.Female;
			}
		}
		Species species = SpeciesList.placeholder;
		
		species = new SpeciesList().getFromName(speciesstr.toLowerCase().replaceAll("[^a-z0-9]",""));
		Ability ability = Abilities.nothing;
		if(species.getAbilities().length == 1)
		{
			ability = species.getAbilities()[0];
		}
		return new Pokemon(species, ability, gender, level, null, species.getBasicStatsAtLevel(level), species.getStatAtLevel(0,31,0,1, level), new double[]{1,1,1,1,1,1,1}, null);
	}
	
	public static void main(String[]args)
	{
		BattleRoom br = new BattleRoom("yolo,","swag,");
		//p1a: Sableye|Sableye, F|100/100
		br.playerMessage("p1","ToppKeck","24");
		br.playerMessage("p2","Swagmaster Steve","99");
		br.pokeMessage("p1", "Porygon-Z");
		br.pokeMessage("p1", "Chansey");
		br.damageMessage("p1a: Porygon-Z", "133/200", "","");
		System.out.println(br.messages);
		System.out.println(br.team1);
		br.damageMessage("p1a: Porygon-Z", "19/40", "","");
		System.out.println(br.messages);
		System.out.println(br.team1);
		br.healMessage("p1a: Porygon-Z","100/100","drain","p2a: Dragonite","[wisher] Chansey");
		System.out.println(br.messages);
		System.out.println(br.team1);
		br.boostMessage("p1a: Porygon-Z","atk","4","Liechi Berry");
		System.out.println(br.messages);
		System.out.println(br.team1);
	}
}
