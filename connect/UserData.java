package connect;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class UserData
{
	public static String separator = "\\|";
	String userid = "";
	long avatar;
	ArrayList<String> rooms;
	String ip;
	//we only want the room names, because we're not actually interested in the text yet
	public UserData(String userid, long avatar, ArrayList<String> rooms, String ip)
	{
		this.userid = userid;
		this.avatar = avatar;
		this.rooms = rooms;
		this.ip = ip;
	}
	
	public String toString()
	{
		return getClass().getName() + 
				"[userid="+userid+
				",avatar="+avatar+
				",rooms="+rooms+
				",ip="+ip+"]";
	}
	
	//|queryresponse|userdetails|{"userid":"magmaadmin","avatar":169,"rooms":{"trivia":{},"battle-randombattle-123124594":{"p1":" AsianZeus","p2":" Magma Admin~"},"battle-randombattle-123124794":{"p1":" coacha","p2":" Crucialy"}},"ip":"199.168.74.42"}
	//|queryresponse|rooms|{"official":[{"title":"Lobby","desc":"Chat it up with friends, rivals and foes in the Pokemon Showdown Lobby!","userCount":1304}],"chat":[{"title":"Tournaments","desc":"Scripted tournaments here! | Rules & FAQ: http://goo.my/tourssite","userCount":160},{"title":"Monotype","desc":"Talonflame banned: http://goo.my/jd3 | http://goo.my/monotype","userCount":84},{"title":"Anime and Manga","desc":"The Happy Animu and Mango Place. Rules: http://bit.ly/1fVqbje","userCount":41},{"title":"YouTube","desc":"A room for Youtubers and streamers. Not for advertising your YouTube channel.","userCount":90},{"title":"Trivia","desc":"Trivia! Site: http://pstrivia.weebly.com/ Tracking: http://goo.gl/BVni5K","userCount":40},{"title":"The Studio","desc":"Welcome to The Studio! We're a ?MUSIC? sharing room! Site: http://goo.gl/CfacMJ","userCount":29},{"title":"NEXT","desc":"Play and talk about NEXT, a game mod, now with gen six mechanics! /gennext","userCount":25},{"title":"Sports","desc":"Talk about sports! Rules: http://goo.my/sports","userCount":23},{"title":"Video Games","desc":"Check out our website: tinyurl.com/ShowdownVG Steam group: tinyurl.com/PSgaming","userCount":26},{"title":"Fran�ais","desc":"Forum de la communaut�: http://frenchcommunity.soforums.com/index.php","userCount":101},{"title":"Espa�ol","desc":"Bienvenido a la comunidad habla hispana! Web: http://ps-salaespanol.weebly.com/","userCount":229},{"title":"Italiano","desc":"Tryouts per la SWC > http://s.battlingarena.forumcommunity.net/?t=56198857","userCount":77},{"title":"Portugu�s","desc":"Para quem fala portugu�s! Regras: http://www.smogon.com/sim/pt/regras","userCount":93},{"title":"Other Metas","desc":"Other Metagames - everything you know has been slightly altered","userCount":25},{"title":"Little Cup","desc":" Get involved in littlecup http://goo.my/lcf | http://goo.my/frogs - frog blog","userCount":46},{"title":"NeverUsed","desc":"Renamed to NU Alpha (in OM) because RU is still in beta. | No ban discussion.","userCount":64},{"title":"Ubers","desc":"Ubers �ber alles.","userCount":36},{"title":"RarelyUsed","desc":"8D | Venomoth Banned | http://www.smogon.com/forums/forums/rarelyused.302/","userCount":25},{"title":"UnderUsed","desc":": UnderUsed site: http://psunderused.wix.com/uutier (Banlist within)","userCount":47},{"title":"Debates and Discussions","desc":"Talk about real-world issues or deeper things. Don't try to be edgy here.","userCount":22},{"title":"The Happy Place","desc":"Need help with your life problems? Ask here! Rules: http://goo.gl/hcSuqn","userCount":51},{"title":"OverUsed","desc":"Hosting weekly OU tournaments! Info: http://pastebin.com/RkR7eBVC","userCount":142},{"title":"VGC","desc":"Discuss the official Pok�mon Video Game Championships tournament series.","userCount":75},{"title":"Roleplaying","desc":"RP info: http://bit.ly/1tmOhKA Voice contest! Winner announced today~","userCount":77},{"title":"CAP Project","desc":"Discuss the CAP Project here! CAP 18 in progress!","userCount":26},{"title":"Mafia","desc":"Play the Mafia game here! Room Website: http://ps-mafia.weebly.com/","userCount":27},{"title":"Old Gens","desc":"From competitive to in-game, discuss generations 1-5 here!","userCount":24},{"title":"Academics ","desc":"Academics: Homework Help | Game Shows | General Euphoria","userCount":26},{"title":"Doubles","desc":"All about doubles! http://goo.my/smogdubs | Mega Kangaskhan retest happening","userCount":24},{"title":"Deutsche","desc":"Forum: http://ps-deutsche.forumieren.com,    Regeln: http://bit.ly/1cLF3jF)","userCount":40},{"title":"Pokemon In-Game","desc":"Pok�mon Omega Ruby & Alpha Sapphire announced! HOENN REMAKE HYPE!!","userCount":43},{"title":"Scavengers","desc":"Welcome to Scavengers! More information here: http://goo.my/scavengers","userCount":27},{"title":"Competitive Tutoring","desc":"A place to improve your teambuilding and battling skills. pastebin.com/jy8Hyq0q","userCount":94},{"title":"Art","desc":" Current Contest ABILITY SHIFT >>  http://tinyurl.com/kdt668g","userCount":37},{"title":"Game Corner","desc":"Welcome to Game Corner! Always open to new ideas! http://goo.my/psgc","userCount":37},{"title":"TV, Books and Films","desc":"Film of the day is Pulp Fiction; room info http://goo.my/pstbf","userCount":25},{"title":"Cafe Le Wow","desc":"PS's Food Room! #PutAnEggOnIt | Website: http://goo.my/lewow","userCount":26},{"title":"Wi-Fi","desc":"Room Site http://tinyurl.com/WifiWeb / Wi-Fi Lists http://tinyurl.com/WifiSheets","userCount":262},{"title":"Survivor","desc":"Welcome to Survivor! Website - http://survivor-ps.weebly.com/","userCount":25},{"title":"Dutch","desc":"Welkom in de Nederlandse kamer! Website: http://goo.my/psdutch","userCount":31},{"title":"Table Top","desc":"A Text-Based RPG made for everyone! http://tinyurl.com/TableTopUsers ","userCount":29},{"title":"MOBA","desc":"Official MOBA Room! (LoL, DotA2, Smite etc.) Rules: http://tinyurl.com/nfd4kop","userCount":26},{"title":"Tech & Code","desc":"Want to learn to code? http://www.codecademy.com/ | We aren't PS technical help.","userCount":25}],"userCount":9762,"battleCount":1854}
	public static UserData parseUserData(String stuff)
	{
		String json = stuff.split(separator,4)[3];
		JSONObject obj = (JSONObject)JSONValue.parse(json);
		String uname = (String)obj.get("userid");
		long avatar = (Long)obj.get("avatar");
		String ip = (String)obj.get("ip");
		JSONObject rooms = (JSONObject)obj.get("rooms");
		ArrayList<String> roomlist = new ArrayList<>();
		for(Object e : rooms.keySet())
		{
			String room = (String)e;
			roomlist.add(room);
		}
		return new UserData(uname,avatar,roomlist, ip);
	}
	public static void main(String[] args)
	{
		System.out.println(parseUserData("|queryresponse|userdetails|{\"userid\":\"magmaadmin\",\"avatar\":169,\"rooms\":{\"trivia\":{},\"battle-randombattle-123124594\":{\"p1\":\" AsianZeus\",\"p2\":\" Magma Admin~\"},\"battle-randombattle-123124794\":{\"p1\":\" coacha\",\"p2\":\" Crucialy\"}},\"ip\":\"199.168.74.42\"}"));
	}
}
