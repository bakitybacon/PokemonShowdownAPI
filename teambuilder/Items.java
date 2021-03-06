package teambuilder;

import teambuilder.TypeBoostingItem;

public class Items 
{
	public static final Item abomasite = new Item("abomasite", "Abomasite", "Mega-evolves Abomasnow.",6);
	public static final Item absolite = new Item("absolite", "Absolite", "Mega-evolves Absol.",6);
	public static final Item absorbbulb = new Item("absorbbulb", "Absorb Bulb", "Raises Sp. Atk by 1 if hit by a Water-type attack. Single use.",5);
	public static final TimeSpaceOrb adamantorb = new TimeSpaceOrb(SpeciesList.dialga,"adamantorb", "Adamant Orb", "If holder is a Dialga, its Steel- and Dragon-type attacks have 1.2x power.", new Type[]{Type.Steel,Type.Dragon}, 4);
	public static final Item aerodactylite = new Item("aerodactylite", "Aerodactylite", "Mega-evolves Aerodactyl.",6);
	public static final Item aggronite = new Item("aggronite", "Aggronite", "Mega-evolves Aggron.",6);
	public static final Item aguavberry = new Item("aguavberry", "Aguav Berry", "Restores 1/8 max HP when at 1/2 max HP or less. May confuse. Single use.",3);
	public static final Item airballoon = new Item("airballoon", "Air Balloon", "Holder is immune to Ground-type attacks. Pops when holder is hit.",5);
	public static final Item alakazite = new Item("alakazite", "Alakazite", "Mega-evolves Alakazam.",6);
	public static final Item ampharosite = new Item("ampharosite", "Ampharosite", "Mega-evolves Ampharos.",6);
	public static final Item apicotberry = new Item("apicotberry", "Apicot Berry", "Raises Sp. Def by 1 when at 1/4 max HP or less. Single use.",3);
	public static final Item armorfossil = new Item("armorfossil", "Armor Fossil", "Can be revived into Shieldon.",4);
	public static final Item aspearberry = new Item("aspearberry", "Aspear Berry", "Holder is cured if it is frozen. Single use.",3);
	public static final Item assaultvest = new Item("assaultvest", "Assault Vest", "Holder's Sp. Def is 1.5x, but it can only use damaging moves.",6);
	public static final ResistBerry babiriberry = new ResistBerry("babiriberry", "Babiri Berry", "Halves damage taken from a super effective Steel-type attack. Single use.",Type.Steel,4);
	public static final Item banettite = new Item("banettite", "Banettite", "Mega-evolves Banette.",6);
	public static final Item belueberry = new Item("belueberry", "Belue Berry", "No competitive use.",3);
	public static final Item berryjuice = new Item("berryjuice", "Berry Juice", "Restores 20HP when at 1/2 max HP or less. Single use.",2);
	public static final Item bigroot = new Item("bigroot", "Big Root", "Holder gains 1.3x HP from draining moves, Aqua Ring, Ingrain, and Leech Seed.",4);
	public static final Item bindingband = new Item("bindingband", "Binding Band", "Holder's partial-trapping moves deal 1/6 max HP per turn instead of 1/8.",5);
	public static final TypeBoostingItem blackbelt = new TypeBoostingItem("blackbelt", "Black Belt", "Holder's Fighting-type attacks have 1.2x power.",Type.Fighting,2);
	public static final Item blacksludge = new Item("blacksludge", "Black Sludge", "Each turn, if holder is a Poison-type, restores 1/16 max HP; loses 1/8 if not.",4);
	public static final TypeBoostingItem blackglasses = new TypeBoostingItem("blackglasses", "Black Glasses", "Holder's Dark-type attacks have 1.2x power.",Type.Dark,2);
	public static final Item blastoisinite = new Item("blastoisinite", "Blastoisinite", "Mega-evolves Blastoise.",6);
	public static final Item blazikenite = new Item("blazikenite", "Blazikenite", "Mega-evolves Blaziken.",6);
	public static final Item blukberry = new Item("blukberry", "Bluk Berry", "No competitive use.",3);
	public static final Item brightpowder = new Item("brightpowder", "BrightPowder", "The accuracy of attacks against the holder is 0.9x.",2);
	public static final Gem buggem = new Gem("buggem", "Bug Gem", "Holder's first successful Bug-type attack will have 1.3x power. Single use.", Type.Bug, 5);
	public static final Item burndrive = new Item("burndrive", "Burn Drive", "Holder's Techno Blast is Fire-type.",5);
	public static final Item cellbattery = new Item("cellbattery", "Cell Battery", "Raises Attack by 1 if hit by an Electric-type attack. Single use.",5);
	public static final TypeBoostingItem charcoal = new TypeBoostingItem("charcoal", "Charcoal", "Holder's Fire-type attacks have 1.2x power.",Type.Fire,2);
	public static final Item charizarditex = new Item("charizarditex", "Charizardite X", "Mega-evolves Charizard into Mega Charizard X.",6);
	public static final Item charizarditey = new Item("charizarditey", "Charizardite Y", "Mega-evolves Charizard into Mega Charizard Y.",6);
	public static final ResistBerry chartiberry = new ResistBerry("chartiberry", "Charti Berry", "Halves damage taken from a super effective Rock-type attack. Single use.",Type.Rock,4);
	public static final Item cheriberry = new Item("cheriberry", "Cheri Berry", "Holder cures itself if it is paralyzed. Single use.",3);
	public static final Item cherishball = new Item("cherishball", "Cherish Ball", "A rare Poke Ball that has been crafted to commemorate an occasion.",4);
	public static final Item chestoberry = new Item("chestoberry", "Chesto Berry", "Holder wakes up if it is asleep. Single use.",3);
	public static final ResistBerry chilanberry = new ResistBerry("chilanberry", "Chilan Berry", "Halves damage taken from a Normal-type attack. Single use.",Type.Normal,4);
	public static final Item chilldrive = new Item("chilldrive", "Chill Drive", "Holder's Techno Blast is Ice-type.",5);
	public static final Item choiceband = new Item("choiceband", "Choice Band", "Holder's Attack is 1.5x, but it can only use the first move it selects.",3);
	public static final Item choicescarf = new Item("choicescarf", "Choice Scarf", "Holder's Speed is 1.5x, but it can only use the first move it selects.",4);
	public static final Item choicespecs = new Item("choicespecs", "Choice Specs", "Holder's Sp. Atk is 1.5x, but it can only use the first move it selects.",4);
	public static final ResistBerry chopleberry = new ResistBerry("chopleberry", "Chople Berry", "Halves damage taken from a super effective Fighting-type attack. Single use.",Type.Fighting,4);
	public static final Item clawfossil = new Item("clawfossil", "Claw Fossil", "Can be revived into Anorith.",3);
	public static final ResistBerry cobaberry = new ResistBerry("cobaberry", "Coba Berry", "Halves damage taken from a super effective Flying-type attack. Single use.",Type.Flying,4);
	public static final ResistBerry colburberry = new ResistBerry("colburberry", "Colbur Berry", "Halves damage taken from a super effective Dark-type attack. Single use.",Type.Dark,4);
	public static final Item cornnberry = new Item("cornnberry", "Cornn Berry", "No competitive use.",3);
	public static final Item coverfossil = new Item("coverfossil", "Cover Fossil", "Can be revived into Tirtouga.",5);
	public static final Item custapberry = new Item("custapberry", "Custap Berry", "Holder moves first in its priority bracket when at 1/4 max HP or less. Single use.",4);
	public static final Item damprock = new Item("damprock", "Damp Rock", "Holder's use of Rain Dance lasts 8 turns instead of 5.",4);
	public static final Gem darkgem = new Gem("darkgem", "Dark Gem", "Holder's first successful Dark-type attack will have 1.3x power. Single use.", Type.Dark, 5);
	public static final Item deepseascale = new Item("deepseascale", "DeepSeaScale", "If holder is a Clamperl, its Sp. Def is doubled.",3);
	public static final Item deepseatooth = new Item("deepseatooth", "DeepSeaTooth", "If holder is a Clamperl, its Sp. Atk is doubled.",3);
	public static final Item destinyknot = new Item("destinyknot", "Destiny Knot", "If holder becomes infatuated, the other Pokemon also becomes infatuated.",4);
	public static final Item diveball = new Item("diveball", "Dive Ball", "A Poke Ball that works especially well on Pokemon that live underwater.",3);
	public static final Item domefossil = new Item("domefossil", "Dome Fossil", "Can be revived into Kabuto.",3);
	public static final Item dousedrive = new Item("dousedrive", "Douse Drive", "Holder's Techno Blast is Water-type.",5);
	public static final Plate dracoplate = new Plate("dracoplate", "Draco Plate", "Holder's Dragon-type attacks have 1.2x power. Judgment is Dragon-type.", Type.Dragon, 4);
	public static final TypeBoostingItem dragonfang = new TypeBoostingItem("dragonfang", "Dragon Fang", "Holder's Dragon-type attacks have 1.2x power.",Type.Dragon,2);
	public static final Gem dragongem = new Gem("dragongem", "Dragon Gem", "Holder's first successful Dragon-type attack will have 1.3x power. Single use.", Type.Dragon, 5);
	public static final Plate dreadplate = new Plate("dreadplate", "Dread Plate", "Holder's Dark-type attacks have 1.2x power. Judgment is Dark-type.", Type.Dark, 4);
	public static final Item dreamball = new Item("dreamball", "Dream Ball", "A special Poke Ball that appears out of nowhere in a bag at the Entree Forest.",5);
	public static final Item durinberry = new Item("durinberry", "Durin Berry", "No competitive use.",3);
	public static final Item duskball = new Item("duskball", "Dusk Ball", "A Poke Ball that makes it easier to catch wild Pokemon at night or in caves.",4);
	public static final Plate earthplate = new Plate("earthplate", "Earth Plate", "Holder's Ground-type attacks have 1.2x power. Judgment is Ground-type.", Type.Ground, 4);
	public static final Item ejectbutton = new Item("ejectbutton", "Eject Button", "If holder is hit, it immediately switches out with a chosen ally. Single use.",5);
	public static final Item electirizer = new Item("electirizer", "Electirizer", "Evolves Electabuzz into Electivire when traded.",4);
	public static final Gem electricgem = new Gem("electricgem", "Electric Gem", "Holder's first successful Electric-type attack will have 1.3x power. Single use.", Type.Electric, 5);
	public static final Item energypowder = new Item("energypowder", "EnergyPowder", "Restores 50HP to one Pokemon but lowers Happiness.",2);
	public static final Item enigmaberry = new Item("enigmaberry", "Enigma Berry", "Restores 1/4 max HP when holder is hit by a super effective move. Single use.",3);
	public static final Item eviolite = new Item("eviolite", "Eviolite", "If holder's species can evolve, its Defense and Sp. Def are 1.5x.",5);
	public static final Item expertbelt = new Item("expertbelt", "Expert Belt", "Holder's super effective attacks against other Pokemon do 1.2x damage.",4);
	public static final Gem fairygem = new Gem("fairygem", "Fairy Gem", "Holder's first successful Fairy-type attack will have 1.3x power. Single use.", Type.Fairy, 6);
	public static final Item fastball = new Item("fastball", "Fast Ball", "A Poke Ball that makes it easier to catch Pokemon which are quick to run away.",2);
	public static final Gem fightinggem = new Gem("fightinggem", "Fighting Gem", "Holder's first successful Fighting-type attack will have 1.3x power. Single use.", Type.Fighting, 5);
	public static final Item figyberry = new Item("figyberry", "Figy Berry", "Restores 1/8 max HP when at 1/2 max HP or less. May confuse. Single use.",3);
	public static final Gem firegem = new Gem("firegem", "Fire Gem", "Holder's first successful Fire-type attack will have 1.3x power. Single use.", Type.Fire, 5);
	public static final Plate fistplate = new Plate("fistplate", "Fist Plate", "Holder's Fighting-type attacks have 1.2x power. Judgment is Fighting-type.", Type.Fighting, 4);
	public static final Item flameorb = new Item("flameorb", "Flame Orb", "At the end of every turn, this item attempts to burn the holder.",4);
	public static final Plate flameplate = new Plate("flameplate", "Flame Plate", "Holder's Fire-type attacks have 1.2x power. Judgment is Fire-type.", Type.Fire, 4);
	public static final Item floatstone = new Item("floatstone", "Float Stone", "Holder's weight is halved.",5);
	public static final Gem flyinggem = new Gem("flyinggem", "Flying Gem", "Holder's first successful Flying-type attack will have 1.3x power. Single use.", Type.Flying, 5);
	public static final Item focusband = new Item("focusband", "Focus Band", "Holder has a 10% chance to survive an attack that would KO it with 1HP.",2);
	public static final Item focussash = new Item("focussash", "Focus Sash", "If holder's HP is full, will survive an attack that would KO it with 1HP. Single use.",4);
	public static final Item fullincense = new Item("fullincense", "Full Incense", "Holder moves last in its priority bracket.",4);
	public static final Item ganlonberry = new Item("ganlonberry", "Ganlon Berry", "Raises Defense by 1 when at 1/4 max HP or less. Single use.",3);
	public static final Item garchompite = new Item("garchompite", "Garchompite", "Mega-evolves Garchomp.",6);
	public static final Item gardevoirite = new Item("gardevoirite", "Gardevoirite", "Mega-evolves Gardevoir.",6);
	public static final Item gengarite = new Item("gengarite", "Gengarite", "Mega-evolves Gengar.",6);
	public static final Gem ghostgem = new Gem("ghostgem", "Ghost Gem", "Holder's first successful Ghost-type attack will have 1.3x power. Single use.", Type.Ghost, 5);
	public static final Gem grassgem = new Gem("grassgem", "Grass Gem", "Holder's first successful Grass-type attack will have 1.3x power. Single use.", Type.Grass, 5);
	public static final Item greatball = new Item("greatball", "Great Ball", "A high-performance Ball that provides a higher catch rate than a Poke Ball.",1);
	public static final Item grepaberry = new Item("grepaberry", "Grepa Berry", "No competitive use.",3);
	public static final Item gripclaw = new Item("gripclaw", "Grip Claw", "Holder's partial-trapping moves always last 7 turns.",4);
	public static final TimeSpaceOrb griseousorb = new TimeSpaceOrb(SpeciesList.giratinaorigin,"griseousorb", "Griseous Orb", "If holder is a Giratina, its Ghost- and Dragon-type attacks have 1.2x power.", new Type[]{Type.Ghost,Type.Dragon}, 4);
	public static final Gem groundgem = new Gem("groundgem", "Ground Gem", "Holder's first successful Ground-type attack will have 1.3x power. Single use.", Type.Ground, 5);
	public static final Item gyaradosite = new Item("gyaradosite", "Gyaradosite", "Mega-evolves Gyarados.",6);
	public static final ResistBerry habanberry = new ResistBerry("habanberry", "Haban Berry", "Halves damage taken from a super effective Dragon-type attack. Single use.",Type.Dragon,4);
	public static final TypeBoostingItem hardstone = new TypeBoostingItem("hardstone", "Hard Stone", "Holder's Rock-type attacks have 1.2x power.",Type.Rock,2);
	public static final Item healball = new Item("healball", "Heal Ball", "A remedial Poke Ball that restores the caught Pokemon's HP and status problem.",4);
	public static final Item heatrock = new Item("heatrock", "Heat Rock", "Holder's use of Sunny Day lasts 8 turns instead of 5.",4);
	public static final Item heavyball = new Item("heavyball", "Heavy Ball", "A Poke Ball for catching very heavy Pokemon.",2);
	public static final Item helixfossil = new Item("helixfossil", "Helix Fossil", "Can be revived into Omanyte.",3);
	public static final Item heracronite = new Item("heracronite", "Heracronite", "Mega-evolves Heracross.",6);
	public static final Item hondewberry = new Item("hondewberry", "Hondew Berry", "No competitive use.",3);
	public static final Item houndoominite = new Item("houndoominite", "Houndoominite", "Mega-evolves Houndoom.",6);
	public static final Item iapapaberry = new Item("iapapaberry", "Iapapa Berry", "Restores 1/8 max HP when at 1/2 max HP or less. May confuse. Single use.",3);
	public static final Gem icegem = new Gem("icegem", "Ice Gem", "Holder's first successful Ice-type attack will have 1.3x power. Single use.", Type.Ice, 5);
	public static final Plate icicleplate = new Plate("icicleplate", "Icicle Plate", "Holder's Ice-type attacks have 1.2x power. Judgment is Ice-type.", Type.Ice, 4);
	public static final Item icyrock = new Item("icyrock", "Icy Rock", "Holder's use of Hail lasts 8 turns instead of 5.",4);
	public static final Plate insectplate = new Plate("insectplate", "Insect Plate", "Holder's Bug-type attacks have 1.2x power. Judgment is Bug-type.", Type.Bug, 4);
	public static final Item ironball = new Item("ironball", "Iron Ball", "Holder's Speed halved, becomes grounded, and takes 1x from Ground.",4);
	public static final Plate ironplate = new Plate("ironplate", "Iron Plate", "Holder's Steel-type attacks have 1.2x power. Judgment is Steel-type.", Type.Steel, 4);
	public static final Item jabocaberry = new Item("jabocaberry", "Jaboca Berry", "If holder is hit by a physical move, attacker loses 1/8 of its max HP. Single use.",4);
	public static final ResistBerry kasibberry = new ResistBerry("kasibberry", "Kasib Berry", "Halves damage taken from a super effective Ghost-type attack. Single use.",Type.Ghost,4);
	public static final ResistBerry kebiaberry = new ResistBerry("kebiaberry", "Kebia Berry", "Halves damage taken from a super effective Poison-type attack. Single use.",Type.Poison,4);
	public static final Item keeberry = new Item("keeberry", "Kee Berry", "Raises Defense by 1 if hit by a Physical attack. Single use.",6);
	public static final Item kelpsyberry = new Item("kelpsyberry", "Kelpsy Berry", "No competitive use.",3);
	public static final Item kangaskhanite = new Item("kangaskhanite", "Kangaskhanite", "Mega-evolves Kangaskhan.",6);
	public static final Item kingsrock = new Item("kingsrock", "King's Rock", "Holder's attacks without a chance to flinch gain a 10% chance to flinch.",2);
	public static final Item laggingtail = new Item("laggingtail", "Lagging Tail", "Holder moves last in its priority bracket.",4);
	public static final Item lansatberry = new Item("lansatberry", "Lansat Berry", "Holder gains the Focus Energy effect when at 1/4 max HP or less. Single use.",3);
	public static final Item latiasite = new Item("latiasite", "Latiasite", "Mega-evolves Latias.",6);
	public static final Item latiosite = new Item("latiosite", "Latiosite", "Mega-evolves Latios.",6);
	public static final Item laxincense = new Item("laxincense", "Lax Incense", "The accuracy of attacks against the holder is 0.9x.",3);
	public static final Item leftovers = new Item("leftovers", "Leftovers", "At the end of every turn, holder restores 1/16 of its max HP.",2);
	public static final Item leppaberry = new Item("leppaberry", "Leppa Berry", "Restores 10PP to the first of the holder's moves to reach 0PP. Single use.",3);
	public static final Item levelball = new Item("levelball", "Level Ball", "A Poke Ball for catching Pokemon that are a lower level than your own.",2);
	public static final Item liechiberry = new Item("liechiberry", "Liechi Berry", "Raises Attack by 1 when at 1/4 max HP or less. Single use.",3);
	public static final Item lifeorb = new Item("lifeorb", "Life Orb", "Holder's damaging moves do 1.3x damage; loses 1/10 max HP after the attack.",4);
	public static final Item lightball = new Item("lightball", "Light Ball", "If holder is a Pikachu, its Attack and Sp. Atk are doubled.",2);
	public static final Item lightclay = new Item("lightclay", "Light Clay", "Holder's use of Light Screen or Reflect lasts 8 turns instead of 5.",4);
	public static final Item loveball = new Item("loveball", "Love Ball", "Poke Ball for catching Pokemon that are the opposite gender of your Pokemon.",2);
	public static final Item lucarionite = new Item("lucarionite", "Lucarionite", "Mega-evolves Lucario.",6);
	public static final Item luckypunch = new Item("luckypunch", "Lucky Punch", "If holder is a Chansey, its critical hit ratio is boosted by 2.",2);
	public static final Item lumberry = new Item("lumberry", "Lum Berry", "Holder cures itself if it is confused or has a major status problem. Single use.",3);
	public static final Item luminousmoss = new Item("luminousmoss", "Luminous Moss", "Raises Special Defense by 1 if hit by a Water-type attack. Single use.",6);
	public static final Item lureball = new Item("lureball", "Lure Ball", "A Poke Ball for catching Pokemon hooked by a Rod when fishing.",2);
	public static final TimeSpaceOrb lustrousorb = new TimeSpaceOrb(SpeciesList.palkia,"lustrousorb", "Lustrous Orb", "If holder is a Palkia, its Water- and Dragon-type attacks have 1.2x power.", new Type[]{Type.Water,Type.Dragon}, 4);
	public static final Item luxuryball = new Item("luxuryball", "Luxury Ball", "A comfortable Poke Ball that makes a caught wild Pokemon quickly grow friendly.",3);
	public static final Item machobrace = new Item("machobrace", "Macho Brace", "Holder's Speed is halved.",3);
	public static final TypeBoostingItem magnet = new TypeBoostingItem("magnet", "Magnet", "Holder's Electric-type attacks have 1.2x power.",Type.Electric,2);
	public static final Item magoberry = new Item("magoberry", "Mago Berry", "Restores 1/8 max HP when at 1/2 max HP or less. May confuse. Single use.",3);
	public static final Item magostberry = new Item("magostberry", "Magost Berry", "No competitive use.",3);
	public static final Item mail = new Item("mail", "Mail", "This item cannot be given to or taken from a Pokemon, except by Knock Off.",2);
	public static final Item manectite = new Item("manectite", "Manectite", "Mega-evolves Manectric.",6);
	public static final Item marangaberry = new Item("marangaberry", "Maranga Berry", "Raises Special Defense by 1 if hit by a Special attack. Single use.",6);
	public static final Item masterball = new Item("masterball", "Master Ball", "The best Ball with the ultimate performance. It will catch any wild Pokemon.",1);
	public static final Item mawilite = new Item("mawilite", "Mawilite", "Mega-evolves Mawile.",6);
	public static final Plate meadowplate = new Plate("meadowplate", "Meadow Plate", "Holder's Grass-type attacks have 1.2x power. Judgment is Grass-type.", Type.Grass, 4);
	public static final Item medichamite = new Item("medichamite", "Medichamite", "Mega-evolves Medicham.",6);
	public static final Item mentalherb = new Item("mentalherb", "Mental Herb", "Cures holder if affected by Attract, Disable, Encore, Taunt, Torment. Single use.",3);
	public static final TypeBoostingItem metalcoat = new TypeBoostingItem("metalcoat", "Metal Coat", "Holder's Steel-type attacks have 1.2x power.",Type.Steel,2);
	public static final Item metalpowder = new Item("metalpowder", "Metal Powder", "If holder is a Ditto that hasn't Transformed, its Defense is doubled.",2);
	public static final Item metronome = new Item("metronome", "Metronome", "Damage of moves used on consecutive turns is increased. Max 2x after 5 turns.",4);
	public static final Item mewtwonitex = new Item("mewtwonitex", "Mewtwonite X", "Mega-evolves Mewtwo into Mega Mewtwo X.",6);
	public static final Item mewtwonitey = new Item("mewtwonitey", "Mewtwonite Y", "Mega-evolves Mewtwo into Mega Mewtwo Y.",6);
	public static final Item micleberry = new Item("micleberry", "Micle Berry", "Holder's next move has 1.2x accuracy when at 1/4 max HP or less. Single use.",4);
	public static final Plate mindplate = new Plate("mindplate", "Mind Plate", "Holder's Psychic-type attacks have 1.2x power. Judgment is Psychic-type.", Type.Psychic, 4);
	public static final TypeBoostingItem miracleseed = new TypeBoostingItem("miracleseed", "Miracle Seed", "Holder's Grass-type attacks have 1.2x power.",Type.Grass,2);
	public static final Item moonball = new Item("moonball", "Moon Ball", "A Poke Ball for catching Pokemon that evolve using the Moon Stone.",2);
	public static final Item muscleband = new Item("muscleband", "Muscle Band", "Holder's physical attacks have 1.1x power.",4);
	public static final TypeBoostingItem mysticwater = new TypeBoostingItem("mysticwater", "Mystic Water", "Holder's Water-type attacks have 1.2x power.",Type.Water,2);
	public static final Item nanabberry = new Item("nanabberry", "Nanab Berry", "No competitive use.",3);
	public static final Item nestball = new Item("nestball", "Nest Ball", "A Poke Ball that works especially well on weaker Pokemon in the wild.",3);
	public static final Item netball = new Item("netball", "Net Ball", "A Poke Ball that works especially well on Water- and Bug-type Pokemon.",3);
	public static final TypeBoostingItem nevermeltice = new TypeBoostingItem("nevermeltice", "Never-Melt Ice", "Holder's Ice-type attacks have 1.2x power.",Type.Ice,2);
	public static final Item nomelberry = new Item("nomelberry", "Nomel Berry", "No competitive use.",3);
	public static final Gem normalgem = new Gem("normalgem", "Normal Gem", "Holder's first successful Normal-type attack will have 1.3x power. Single use.", Type.Normal, 5);
	public static final ResistBerry occaberry = new ResistBerry("occaberry", "Occa Berry", "Halves damage taken from a super effective Fire-type attack. Single use.",Type.Fire,4);
	public static final Item oddincense = new Item("oddincense", "Odd Incense", "Holder's Psychic-type attacks have 1.2x power.",4);
	public static final Item oldamber = new Item("oldamber", "Old Amber", "Can be revived into Aerodactyl.",3);
	public static final Item oranberry = new Item("oranberry", "Oran Berry", "Restores 10HP when at 1/2 max HP or less. Single use.",3);
	public static final Item pamtreberry = new Item("pamtreberry", "Pamtre Berry", "No competitive use.",3);
	public static final Item parkball = new Item("parkball", "Park Ball", "A special Poke Ball for the Pal Park.",2);
	public static final ResistBerry passhoberry = new ResistBerry("passhoberry", "Passho Berry", "Halves damage taken from a super effective Water-type attack. Single use.",Type.Water,4);
	public static final ResistBerry payapaberry = new ResistBerry("payapaberry", "Payapa Berry", "Halves damage taken from a super effective Psychic-type attack. Single use.",Type.Psychic,4);
	public static final Item pechaberry = new Item("pechaberry", "Pecha Berry", "Holder is cured if it is poisoned. Single use.",3);
	public static final Item persimberry = new Item("persimberry", "Persim Berry", "Holder is cured if it is confused. Single use.",3);
	public static final Item petayaberry = new Item("petayaberry", "Petaya Berry", "Raises Sp. Atk by 1 when at 1/4 max HP or less. Single use.",3);
	public static final Item pinapberry = new Item("pinapberry", "Pinap Berry", "No competitive use.",3);
	public static final Item pinsirite = new Item("pinsirite", "Pinsirite", "Mega-evolves Pinsir.",6);
	public static final Plate pixieplate = new Plate("pixieplate", "Pixie Plate", "Holder's Fairy-type attacks have 1.2x power. Judgment is Fairy-type.", Type.Fairy, 6);
	public static final Item plumefossil = new Item("plumefossil", "Plume Fossil", "Can be revived into Archen.",5);
	public static final TypeBoostingItem poisonbarb = new TypeBoostingItem("poisonbarb", "Poison Barb", "Holder's Poison-type attacks have 1.2x power.",Type.Poison,2);
	public static final Gem poisongem = new Gem("poisongem", "Poison Gem", "Holder's first successful Poison-type attack will have 1.3x power. Single use.", Type.Poison, 5);
	public static final Item pokeball = new Item("pokeball", "Poke Ball", "A device for catching wild Pokemon. It is designed as a capsule system.",1);
	public static final Item pomegberry = new Item("pomegberry", "Pomeg Berry", "No competitive use.",3);
	public static final Item powerherb = new Item("powerherb", "Power Herb", "Holder's two-turn moves complete in one turn (except Sky Drop). Single use.",4);
	public static final Item premierball = new Item("premierball", "Premier Ball", "A rare Poke Ball that has been crafted to commemorate an event.",3);
	public static final Gem psychicgem = new Gem("psychicgem", "Psychic Gem", "Holder's first successful Psychic-type attack will have 1.3x power. Single use.", Type.Psychic, 5);
	public static final Item qualotberry = new Item("qualotberry", "Qualot Berry", "No competitive use.",3);
	public static final Item quickball = new Item("quickball", "Quick Ball", "A Poke Ball that provides a better catch rate at the start of a wild encounter.",4);
	public static final Item quickclaw = new Item("quickclaw", "Quick Claw", "Each turn, holder has a 20% chance to move first in its priority bracket.",2);
	public static final Item quickpowder = new Item("quickpowder", "Quick Powder", "If holder is a Ditto that hasn't Transformed, its Speed is doubled.",4);
	public static final Item rabutaberry = new Item("rabutaberry", "Rabuta Berry", "No competitive use.",3);
	public static final Item rarebone = new Item("rarebone", "Rare Bone", "No competitive use.",4);
	public static final Item rawstberry = new Item("rawstberry", "Rawst Berry", "Holder is cured if it is burned. Single use.",3);
	public static final Item razorclaw = new Item("razorclaw", "Razor Claw", "Holder's critical hit ratio is boosted by 1.",4);
	public static final Item razorfang = new Item("razorfang", "Razor Fang", "Holder's attacks without a chance to flinch gain a 10% chance to flinch.",4);
	public static final Item razzberry = new Item("razzberry", "Razz Berry", "No competitive use.",3);
	public static final Item redcard = new Item("redcard", "Red Card", "If holder is hit, it forces the attacker to switch to a random ally. Single use.",5);
	public static final Item repeatball = new Item("repeatball", "Repeat Ball", "A Poke Ball that works well on Pokemon species that were previously caught.",3);
	public static final ResistBerry rindoberry = new ResistBerry("rindoberry", "Rindo Berry", "Halves damage taken from a super effective Grass-type attack. Single use.",Type.Grass,4);
	public static final Item ringtarget = new Item("ringtarget", "Ring Target", "Holder's type immunities granted by its own typing are negated.",5);
	public static final Gem rockgem = new Gem("rockgem", "Rock Gem", "Holder's first successful Rock-type attack will have 1.3x power. Single use.", Type.Rock, 5);
	public static final Item rockincense = new Item("rockincense", "Rock Incense", "Holder's Rock-type attacks have 1.2x power.",4);
	public static final Item rockyhelmet = new Item("rockyhelmet", "Rocky Helmet", "If holder is hit by a contact move, the attacker loses 1/6 of its max HP.",5);
	public static final Item rootfossil = new Item("rootfossil", "Root Fossil", "Can be revived into Lileep.",3);
	public static final Item roseincense = new Item("roseincense", "Rose Incense", "Holder's Grass-type attacks have 1.2x power.",4);
	public static final ResistBerry roseliberry = new ResistBerry("roseliberry", "Roseli Berry", "Halves damage taken from a super effective Fairy-type attack. Single use.",Type.Fairy,6);
	public static final Item rowapberry = new Item("rowapberry", "Rowap Berry", "If holder is hit by a special move, attacker loses 1/8 of its max HP. Single use.",4);
	public static final Item safariball = new Item("safariball", "Safari Ball", "A special Poke Ball that is used only in the Safari Zone and Great Marsh.",1);
	public static final Item safetygoggles = new Item("safetygoggles", "Safety Goggles", "Protects the holder from weather-related damage and powder moves.",6);
	public static final Item salacberry = new Item("salacberry", "Salac Berry", "Raises Speed by 1 when at 1/4 max HP or less. Single use.",3);
	public static final Item scizorite = new Item("scizorite", "Scizorite", "Mega-evolves Scizor.",6);
	public static final Item scopelens = new Item("scopelens", "Scope Lens", "Holder's critical hit ratio is boosted by 1.",2);
	public static final Item seaincense = new Item("seaincense", "Sea Incense", "Holder's Water-type attacks have 1.2x power.",3);
	public static final TypeBoostingItem sharpbeak = new TypeBoostingItem("sharpbeak", "Sharp Beak", "Holder's Flying-type attacks have 1.2x power.",Type.Flying,2);
	public static final Item shedshell = new Item("shedshell", "Shed Shell", "Holder may switch out even when trapped by another Pokemon.",4);
	public static final Item shellbell = new Item("shellbell", "Shell Bell", "After an attack, holder gains 1/8 of the damage in HP dealt to other Pokemon.",3);
	public static final Item shockdrive = new Item("shockdrive", "Shock Drive", "Holder's Techno Blast is Electric-type.",5);
	public static final ResistBerry shucaberry = new ResistBerry("shucaberry", "Shuca Berry", "Halves damage taken from a super effective Ground-type attack. Single use.",Type.Ground,4);
	public static final TypeBoostingItem silkscarf = new TypeBoostingItem("silkscarf", "Silk Scarf", "Holder's Normal-type attacks have 1.2x power.",Type.Normal,3);
	public static final TypeBoostingItem silverpowder = new TypeBoostingItem("silverpowder", "SilverPowder", "Holder's Bug-type attacks have 1.2x power.",Type.Bug,2);
	public static final Item sitrusberry = new Item("sitrusberry", "Sitrus Berry", "Restores 1/4 max HP when at 1/2 max HP or less. Single use.",3);
	public static final Item skullfossil = new Item("skullfossil", "Skull Fossil", "Can be revived into Cranidos.",4);
	public static final Plate skyplate = new Plate("skyplate", "Sky Plate", "Holder's Flying-type attacks have 1.2x power. Judgment is Flying-type.", Type.Flying, 4);
	public static final Item smoothrock = new Item("smoothrock", "Smooth Rock", "Holder's use of Sandstorm lasts 8 turns instead of 5.",4);
	public static final Item snowball = new Item("snowball", "Snowball", "Raises Attack by 1 if hit by an Ice-type attack. Single use.",6);
	public static final TypeBoostingItem softsand = new TypeBoostingItem("softsand", "Soft Sand", "Holder's Ground-type attacks have 1.2x power.",Type.Ground,2);
	public static final Item souldew = new Item("souldew", "Soul Dew", "If holder is a Latias or a Latios, its Sp. Atk and Sp. Def are 1.5x.",3);
	public static final TypeBoostingItem spelltag = new TypeBoostingItem("spelltag", "Spell Tag", "Holder's Ghost-type attacks have 1.2x power.",Type.Ghost,2);
	public static final Item spelonberry = new Item("spelonberry", "Spelon Berry", "No competitive use.",3);
	public static final Plate splashplate = new Plate("splashplate", "Splash Plate", "Holder's Water-type attacks have 1.2x power. Judgment is Water-type.", Type.Water, 4);
	public static final Plate spookyplate = new Plate("spookyplate", "Spooky Plate", "Holder's Ghost-type attacks have 1.2x power. Judgment is Ghost-type.", Type.Ghost, 4);
	public static final Item sportball = new Item("sportball", "Sport Ball", "A special Poke Ball for the Bug-Catching Contest.",4);
	public static final Item starfberry = new Item("starfberry", "Starf Berry", "Raises a random stat by 2 when at 1/4 max HP or less (not acc/eva). Single use.",3);
	public static final Gem steelgem = new Gem("steelgem", "Steel Gem", "Holder's first successful Steel-type attack will have 1.3x power. Single use.", Type.Steel, 5);
	public static final Item stick = new Item("stick", "Stick", "If holder is a Farfetch'd, its critical hit ratio is boosted by 2.",2);
	public static final Item stickybarb = new Item("stickybarb", "Sticky Barb", "Each turn, holder loses 1/8 max HP. An attacker making contact can receive it.",4);
	public static final Plate stoneplate = new Plate("stoneplate", "Stone Plate", "Holder's Rock-type attacks have 1.2x power. Judgment is Rock-type.", Type.Rock, 4);
	public static final Item tamatoberry = new Item("tamatoberry", "Tamato Berry", "No competitive use.",3);
	public static final ResistBerry tangaberry = new ResistBerry("tangaberry", "Tanga Berry", "Halves damage taken from a super effective Bug-type attack. Single use.",Type.Bug,4);
	public static final Item thickclub = new Item("thickclub", "Thick Club", "If holder is a Cubone or a Marowak, its Attack is doubled.",2);
	public static final Item timerball = new Item("timerball", "Timer Ball", "A Poke Ball that becomes better the more turns there are in a battle.",3);
	public static final Item toxicorb = new Item("toxicorb", "Toxic Orb", "At the end of every turn, this item attempts to badly poison the holder.",4);
	public static final Plate toxicplate = new Plate("toxicplate", "Toxic Plate", "Holder's Poison-type attacks have 1.2x power. Judgment is Poison-type.", Type.Poison, 4);
	public static final TypeBoostingItem twistedspoon = new TypeBoostingItem("twistedspoon", "TwistedSpoon", "Holder's Psychic-type attacks have 1.2x power.",Type.Psychic,2);
	public static final Item tyranitarite = new Item("tyranitarite", "Tyranitarite", "Mega-evolves Tyranitar.",6);
	public static final Item ultraball = new Item("ultraball", "Ultra Ball", "An ultra-performance Ball that provides a higher catch rate than a Great Ball.",1);
	public static final Item venusaurite = new Item("venusaurite", "Venusaurite", "Mega-evolves Venusaur.",6);
	public static final ResistBerry wacanberry = new ResistBerry("wacanberry", "Wacan Berry", "Halves damage taken from a super effective Electric-type attack. Single use.",Type.Electric,4);
	public static final Gem watergem = new Gem("watergem", "Water Gem", "Holder's first successful Water-type attack will have 1.3x power. Single use.", Type.Water, 5);
	public static final Item watmelberry = new Item("watmelberry", "Watmel Berry", "No competitive use.",3);
	public static final Item waveincense = new Item("waveincense", "Wave Incense", "Holder's Water-type attacks have 1.2x power.",4);
	public static final Item weaknesspolicy = new Item("weaknesspolicy", "Weakness Policy", "Attack and Sp. Atk sharply increase when hit super effectively. Single use.",6);
	public static final Item wepearberry = new Item("wepearberry", "Wepear Berry", "No competitive use.",3);
	public static final Item whiteherb = new Item("whiteherb", "White Herb", "Restores all lowered stat stages to 0 when one is less than 0. Single use.",3);
	public static final Item widelens = new Item("widelens", "Wide Lens", "The accuracy of attacks by the holder is 1.1x.",4);
	public static final Item wikiberry = new Item("wikiberry", "Wiki Berry", "Restores 1/8 max HP when at 1/2 max HP or less. May confuse. Single use.",3);
	public static final Item wiseglasses = new Item("wiseglasses", "Wise Glasses", "Holder's special attacks have 1.1x power.",4);
	public static final ResistBerry yacheberry = new ResistBerry("yacheberry", "Yache Berry", "Halves damage taken from a super effective Ice-type attack. Single use.",Type.Ice,4);
	public static final Plate zapplate = new Plate("zapplate", "Zap Plate", "Holder's Electric-type attacks have 1.2x power. Judgment is Electric-type.", Type.Electric, 4);

	public static Item getByID(String id)
	{
		try 
		{
			java.lang.reflect.Field field = new Items().getClass().getDeclaredField(id.toLowerCase());
			//below wants the instance of the class
			//but since it is static, it is irrelevant what the instance is,
			//so passing null will not create errors
			Item item = (Item)field.get(null);
			return item;
		} 
		catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
}
