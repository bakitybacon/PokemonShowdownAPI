package bots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import teambuilder.Abilities;
import teambuilder.Ability;
import teambuilder.Field;
import teambuilder.Gender;
import teambuilder.Item;
import teambuilder.Items;
import teambuilder.Move;
import teambuilder.Moves;
import teambuilder.Pokemon;
import teambuilder.Species;
import teambuilder.SpeciesList;
import damage.DamageCalc;

public class DrunkenLlama extends WebSocketClient
{
	ArrayList<Pokemon> team = new ArrayList<>();
	ArrayList<Pokemon> otherTeamPossibilities = new ArrayList<>();
	Pokemon picked;
	ArrayList<Pokemon> pickedPossibilities = new ArrayList<>();
	public boolean loggedIn = false;
	String battleID = "";
	boolean isP1 = true;
	
	public DrunkenLlama(URI serverURI) 
	{
		super(serverURI);
	}

	@Override
	public void onClose(int arg0, String arg1, boolean arg2) 
	{
		System.out.println("Closing. Exit Status: "+arg0);	
	}

	@Override
	public void onError(Exception exception) 
	{
		System.err.println(exception);	
	}

	@Override
	public void onMessage(String msg) 
	{
		if(msg.contains("\n"))
		{
			String[] cops = msg.split("\n");
			for(String cop : cops)
				onMessage(cop);
			return;
		}
		if(msg.startsWith("|challstr|"))
			getChallengeString(msg); 
		if(msg.startsWith("|request|"))
			handleRequest(msg);
		if(msg.startsWith("|player|") && msg.toLowerCase().contains("drunkenllamatest"))
		{
			if(msg.split("\\|",4)[3].equals("p1"))
				isP1 = true;
			else isP1 = false;
			//this is so we can figure out which pokemon are ours and which are our opponents during the 
			//|poke|p1|venusaur
			//|poke|p2|azumarill section
		}
		if(msg.startsWith(">"))
			battleID = msg.substring(1);
		if(msg.startsWith("|poke|"))
		{
			if(isP1)
				if(msg.split("\\|",4)[3].equals("p1"))
					return;
			addToOpponentTeam(msg.split("\\|",4)[3]);
		}
		if(msg.startsWith("|init|battle"))
			initBattle(msg);
	}
	
	@Override
	public void onOpen(ServerHandshake sh) 
	{
		System.out.println("Opening.");
	}
	
	private void initBattle(String msg)
	{
		send(battleID+"|/timer on");
	}
	
	private void addToOpponentTeam(String poke)
	{
		String[] dets = poke.split(",");
		String speciestring = dets[0];
		Species species = new SpeciesList().getFromName(speciestring);
		int level = 100;
		try
		{
			level = Integer.parseInt(dets[1].substring(2));
		}
		catch(NumberFormatException nfe){}
		//it always has one space and the L, for example ( L72 )
		//however, it can be left out, in which case it's level 100
		//since it makes sense that there is an error, squelching should be fine
		
		Gender gender = Gender.Male;
		if(dets.length == 2)
			gender = Gender.Unknown;
		else if(dets[2].equals("F"))
			gender = Gender.Female;
		otherTeamPossibilities.addAll(Pokemon.getPokemonPossibilities(species,gender,level));
	}
	
	private void parsePokemonJS(JSONArray js)
	{
		for(Object j : js)
		{
			JSONObject poke = (JSONObject)j;
			String details = (String)poke.get("details");
			String[] dets = details.split(",");
			String speciestring = dets[0];
			Species species = new SpeciesList().getFromName(speciestring);
			int level = 100;
			level = Integer.parseInt(dets[1].substring(2));//it always has one space and the L, for example ( L72)
			Gender gender = Gender.Male;
			if(dets.length == 2)
				gender = Gender.Unknown;
			else if(dets[2].equals("F"))
				gender = Gender.Female;
			String baseAbility = (String)poke.get("baseAbility");
			Ability ability = new Abilities().getByID(baseAbility);
			String baseItem = (String)poke.get("item");
			Item item = new Items().getByID(baseItem);
			String hp = (String)poke.get("condition");
			hp = hp.split("/")[0];
			int hitpoints = Integer.parseInt(hp);
			JSONObject stats = (JSONObject)poke.get("stats");
			int atk = (int)(long)stats.get("atk");
			int def = (int)(long)stats.get("def");
			int spa = (int)(long)stats.get("spa");
			int spd = (int)(long)stats.get("spd");
			int spe = (int)(long)stats.get("spe");
			int[] statarray = new int[]{hitpoints,atk,def,spa,spd,spe};
			JSONArray moves = (JSONArray)poke.get("moves");
			ArrayList<Move> movelist = new ArrayList<>();
			for(Object o : moves)
			{
				String mstring = (String)o;
				Move move = new Moves().getByID(mstring);
				movelist.add(move);
			}
			Pokemon pokemon = new Pokemon(species,ability,gender,level,item,statarray,new int[]{1,1,1,1,1},movelist);
			team.add(pokemon);
			System.out.println(pokemon);
		}
	}
	
	//public Pokemon(*Species species, *Ability ability, *Gender gender, *int level, *Item item, *int[] stats, int[] boosts)
	
	private void handleRequest(String msg)
	{
		msg = msg.substring(9);
		if(!msg.matches("\"teampreview\""))
			return;
		JSONObject jobj = (JSONObject)JSONValue.parse(msg);
		JSONObject side = (JSONObject)jobj.get("side");
		JSONArray team = (JSONArray)side.get("pokemon");
	}
	
	private void search()
	{
		send("|/search Challenge Cup 1-vs-1");
	}
	
	private void login(String uname,String pass, int keyid, String challstr)
	{
		if(keyid == -5 || challstr == null)
			return;

		URL url = null;
		try 
		{
			url = new URL("https://play.pokemonshowdown.com/action.php");
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("act", "login");
        params.put("name", uname);
        params.put("pass", pass);
        params.put("challengekeyid", keyid);
        params.put("challenge", challstr);

        StringBuilder postData = new StringBuilder();
        byte[] postDataBytes = new byte[]{};
        for (Map.Entry<String,Object> param : params.entrySet()) 
        {
            if (postData.length() != 0) postData.append('&');
            try 
            {
				postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
				postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	            postDataBytes = postData.toString().getBytes("UTF-8");
			} 
            catch (IOException e) 
            {
				e.printStackTrace();
			}
            
        }
        try
        {
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.addRequestProperty("User-Agent", 
	                "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");

	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(postDataBytes);
	
	        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")));
	        String start = "";
	        while(in.hasNextLine())
	        {
	        	start += in.nextLine();
	        }
	        start = start.replaceFirst("]", "");
	        in.close();
	        JSONObject obj = null;
			try 
			{
				obj = (JSONObject) new JSONParser().parse(start);
			} 
			catch (ParseException e) 
			{
				e.printStackTrace();
			}
		
	        Object assertion = obj.get("assertion");
	        send("|/trn "+uname+",0,"+assertion);
        }
        catch(IOException ioe)
        {
        	ioe.printStackTrace();
        }
        System.out.println("Successfully logged in.");
        loggedIn = true;
        send("|/avatar 278");
	}

	private void start() 
	{
		search();
	}
	
	private void getChallengeString(String chstring)
	{
		String[] args = chstring.split("\\|",4);
		//use the number of strings so that if the user has a | in the name it doesn't spaz out
		//the args are
		//0: nothing. this is the one before |challstr|
		//1: the challstr string
		//2: the Key ID
		//3: the challenge string
		
		int KeyID = Integer.parseInt(args[2]);
		String challstr = args[3];		
		
		login("DrunkenLlamaTest","bakitybacon",KeyID,challstr);
	}
	
	public Pokemon chooseBest()
	{
		if(team.size() == 0 || otherTeamPossibilities.size() == 0)
			return null;
		double topscore = 0;
		Pokemon top = team.get(0);
		for(Pokemon poke : team)
		{
			System.out.println("Pokemon "+poke.getName());
			double pokescore = 0;
			double speciesscore = 0;
			double speciescount = 1;
			for(int i = 0; i < otherTeamPossibilities.size();i+=3)
			{
				double score = getScore(poke,otherTeamPossibilities.get(i),otherTeamPossibilities.get(i+1),otherTeamPossibilities.get(i+2));
				Pokemon hello = otherTeamPossibilities.get(i);
				System.out.println("Score against species "+hello.getName()+" with Ability "+hello.getAbility().getName()+" is "+score);
				speciesscore += score;
				if(otherTeamPossibilities.size() < i+4 ||
						!otherTeamPossibilities.get(i+4).getSpecies().equals(otherTeamPossibilities.get(i).getSpecies()))
				{
					pokescore += speciesscore/speciescount;
					System.out.println("The score for species "+otherTeamPossibilities.get(i).getSpeciesName()+": "+speciesscore/speciescount);
					speciesscore = 0;
					speciescount = 1;
				}
				else speciescount++;
			}
			System.out.println("Pokemon "+poke.getName()+": "+pokescore);
			if(pokescore > topscore)
			{
				top = poke;
				topscore = pokescore;
			}
		}
		System.out.println(topscore);
		return top;
	}
	
	public double getScore(Pokemon poke, Pokemon max, Pokemon mid, Pokemon min)
	{
		if(canBeat(poke,max) == 1)
			return 1;
		if(canBeat(poke,min) == 0)
			return 0;
		if(canBeat(poke,mid) == 1)
			return 0.75;
		if(canBeat(poke,mid) == 0)
			return 0.25;
		return 0.5;
	}
	
	public double canBeat(Pokemon a, Pokemon b)
	{
		//responses range from:
			//certain victory for a: 1
			//unknown victory: 0.5
			//certain victory for b: 0
		int KONumberMaxA = 0;
		int KONumberMinA = 0;
		int KONumberMaxB = 0;
		int KONumberMinB = 0;
		double maximumdamage = 0;
		double minimumdamage = 0;
		boolean aOutspeedsB = false;
		if(a.getStats()[5] > b.getStats()[5])
			aOutspeedsB = true;
		for(Move m : a.getMoves())
		{
			double maxdamage = DamageCalc.getSinglesDamage(a,b,m,new Field(),false)[0];
			double mindamage = DamageCalc.getSinglesDamage(a,b,m,new Field(),false)[1];
			if(maximumdamage < maxdamage)
			{
				maximumdamage = maxdamage;
				minimumdamage = mindamage;
				KONumberMaxA = KONumber(b.getStats()[0],maximumdamage);
				KONumberMinA = KONumber(b.getStats()[0],minimumdamage);
			}
		}
		maximumdamage = 0;
		minimumdamage = 0;
		for(Move m : b.getMoves())
		{
			double maxdamage = DamageCalc.getSinglesDamage(b,a,m,new Field(),false)[0];
			double mindamage = DamageCalc.getSinglesDamage(b,a,m,new Field(),false)[1];
			if(maximumdamage < maxdamage)
			{
				maximumdamage = maxdamage;
				minimumdamage = mindamage;
				KONumberMaxB = KONumber(a.getStats()[0],maximumdamage);
				KONumberMinB = KONumber(a.getStats()[0],minimumdamage);
			}
		}
		
		if(KONumberMaxA - KONumberMinB >= 2)
			return 0;
		if(KONumberMaxB - KONumberMinA >= 2)
			return 1;
		if(KONumberMaxA - KONumberMinB == 1 && !aOutspeedsB)
			return 0;
		if(KONumberMaxB - KONumberMinA == 1 && aOutspeedsB)
			return 0;
		return 0.5;
	}
	
	public int KONumber(int hp, double damage)
	{
		double num = hp/damage;
		num = Math.ceil(num);
		return (int)num;
	}

	public static void main(String[] args) throws Exception
	{
		DrunkenLlama dl = new DrunkenLlama(new URI("ws://sim.smogon.com:8000/showdown/websocket"));
		//dl.connectBlocking();
		//while(!dl.loggedIn)
			Thread.sleep(300);
		JSONArray jar = (JSONArray)JSONValue.parse("[{\"ident\":\"p1: Venusaur\",\"details\":\"Venusaur, L72, M\",\"condition\":\"197/197\",\"active\":true,\"stats\":{\"atk\":133,\"def\":143,\"spa\":170,\"spd\":189,\"spe\":187},\"moves\":[\"razorleaf\",\"energyball\",\"furycutter\",\"bind\"],\"baseAbility\":\"chlorophyll\",\"item\":\"safariball\",\"pokeball\":\"pokeball\",\"canMegaEvo\":false},{\"ident\":\"p1: Ninjask\",\"details\":\"Ninjask, L76, F\",\"condition\":\"183/183\",\"active\":false,\"stats\":{\"atk\":189,\"def\":122,\"spa\":97,\"spd\":120,\"spe\":269},\"moves\":[\"aircutter\",\"uturn\",\"sleeptalk\",\"frustration\"],\"baseAbility\":\"speedboost\",\"item\":\"spookyplate\",\"pokeball\":\"pokeball\",\"canMegaEvo\":false},{\"ident\":\"p1: Swalot\",\"details\":\"Swalot, L76, F\",\"condition\":\"244/244\",\"active\":false,\"stats\":{\"atk\":134,\"def\":164,\"spa\":166,\"spd\":132,\"spe\":141},\"moves\":[\"captivate\",\"poisongas\",\"waterpulse\",\"wringout\"],\"baseAbility\":\"liquidooze\",\"item\":\"rowapberry\",\"pokeball\":\"pokeball\",\"canMegaEvo\":false},{\"ident\":\"p1: Wooper\",\"details\":\"Wooper, L97, F\",\"condition\":\"296/296\",\"active\":false,\"stats\":{\"atk\":146,\"def\":96,\"spa\":73,\"spd\":55,\"spe\":49},\"moves\":[\"mudslap\",\"stockpile\",\"round\",\"swallow\"],\"baseAbility\":\"unaware\",\"item\":\"magnet\",\"pokeball\":\"pokeball\",\"canMegaEvo\":false},{\"ident\":\"p1: Starmie\",\"details\":\"Starmie, L73\",\"condition\":\"191/191\",\"active\":false,\"stats\":{\"atk\":161,\"def\":145,\"spa\":142,\"spd\":179,\"spe\":213},\"moves\":[\"gyroball\",\"doubleedge\",\"rapidspin\",\"blizzard\"],\"baseAbility\":\"illuminate\",\"item\":\"occaberry\",\"pokeball\":\"pokeball\",\"canMegaEvo\":false},{\"ident\":\"p1: Karrablast\",\"details\":\"Karrablast, L87, F\",\"condition\":\"189/189\",\"active\":false,\"stats\":{\"atk\":168,\"def\":135,\"spa\":146,\"spd\":92,\"spe\":125},\"moves\":[\"facade\",\"raindance\",\"pursuit\",\"frustration\"],\"baseAbility\":\"noguard\",\"item\":\"roseincense\",\"pokeball\":\"pokeball\",\"canMegaEvo\":false}]");
		dl.parsePokemonJS(jar);
		dl.onMessage("|poke|p2|Golbat, L77, F");
		dl.onMessage("|poke|p2|Raichu, L75, M");
		dl.onMessage("|poke|p2|Tauros, L74, M");
		dl.onMessage("|poke|p2|Granbull, L77, M");
		dl.onMessage("|poke|p2|Turtwig, L87, F");
		dl.onMessage("|poke|p2|Sandshrew, L88, F");
		System.out.println(dl.chooseBest());
		//dl.close();
	}
}
