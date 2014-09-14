package teambuilder;

public class Volatile
{
	public static final Volatile confusion = new Volatile("Confused",-1);
	public static final Volatile healblock = new Volatile("Heal Block",-1);
	public static final Volatile yawn = new Volatile("Drowsy",-1);
	public static final Volatile flashfire = new Volatile("Flash Fire",1);
	public static final Volatile imprison = new Volatile("Imprisoning Foe",1);
	public static final Volatile formechange = new Volatile("",0);
	public static final Volatile typechange = new Volatile("",0);
	public static final Volatile typeadd = new Volatile("",0);
	public static final Volatile autotomize = new Volatile("Lightened",0);
	public static final Volatile miracleeye = new Volatile("Miracle Eye",-1);
	public static final Volatile foresight = new Volatile("Foresight",-1);
	public static final Volatile telekinesis = new Volatile("Telekinesis",0);
	public static final Volatile transform = new Volatile("Transformed",0);
	public static final Volatile powertrick = new Volatile("Power Trick",0);
	public static final Volatile curse = new Volatile("Curse",-1);
	public static final Volatile nightmare = new Volatile("Nightmare",-1);
	public static final Volatile attract = new Volatile("Attract",-1);
	public static final Volatile torment = new Volatile("Torment",-1);
	public static final Volatile taunt = new Volatile("Taunt",-1);
	public static final Volatile disable = new Volatile("Disable",-1);
	//embargo is useless in competitive because no trainers exist
	public static final Volatile embargo = new Volatile("Embargo",-1);
	public static final Volatile ingrain = new Volatile("Ingrain",1);
	public static final Volatile aquaring = new Volatile("Aqua Ring",1);
	//next three are Stockpile ×1, ×2, ×3
	public static final Volatile stockpile1 = new Volatile("Stockpile \u00D71",1);
	public static final Volatile stockpile2 = new Volatile("Stockpile \u00D72",1);
	public static final Volatile stockpile3 = new Volatile("Stockpile \u00D73",1);
	public static final Volatile perish1 = new Volatile("Perish in 1",-1);
	public static final Volatile perish2 = new Volatile("Perish in 2",-1);
	public static final Volatile perish3 = new Volatile("Perish in 3",-1);
	public static final Volatile airballoon = new Volatile("Air Balloon",1);
	public static final Volatile leechseed = new Volatile("Leech Seed",-1);
	public static final Volatile encore = new Volatile("Encore",-1);
	public static final Volatile mustrecharge = new Volatile("Must Recharge",-1);
	public static final Volatile bide = new Volatile("Bide",1);
	public static final Volatile magnetrise = new Volatile("Magnet Rise",1);
	public static final Volatile smackdown = new Volatile("Smack Down",-1);
	public static final Volatile focusenergy = new Volatile("Focus Energy",1);
	public static final Volatile slowstart = new Volatile("Slow Start",-1);
	public static final Volatile doomdesire = new Volatile("",1);
	public static final Volatile futuresight = new Volatile("",1);
	public static final Volatile mimic = new Volatile("Mimic",1);
	public static final Volatile watersport = new Volatile("Water Sport",1);
	public static final Volatile mudsport = new Volatile("Mud Sport",1);
	public static final Volatile substitute = new Volatile("",1);
	public static final Volatile uproar = new Volatile("Uproar",0);
	public static final Volatile roost = new Volatile("Landed",0);
	public static final Volatile protect = new Volatile("Protect",1);
	public static final Volatile quickguard = new Volatile("Quick Guard",1);
	public static final Volatile wideguard = new Volatile("Wide Guard",1);
	public static final Volatile helpinghand = new Volatile("Helping Hand",1);
	public static final Volatile magiccoat = new Volatile("Magic Coat",1);
	public static final Volatile destinybond = new Volatile("Destiny Bond",1);
	public static final Volatile snatch = new Volatile("Snatch",1);
	public static final Volatile grudge = new Volatile("Grudge",1);
	public static final Volatile endure = new Volatile("Endure",1);
	public static final Volatile focuspunch = new Volatile("Focusing",0);
	public static final Volatile powder = new Volatile("Powder",-1);
	public static final Volatile ragepowder = new Volatile("Rage Powder",1);
	public static final Volatile followme = new Volatile("Follow Me",1);
	public Volatile(String condition, int good)
	{
		this.condition = condition;
		this.good = good;
	}
	String condition = "";
	int good = 0;
}
