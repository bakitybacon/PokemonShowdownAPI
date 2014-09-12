package connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

import messages.BattleRoom;
import messages.ChatRoom;
import messages.Room;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import server.Format;
import teambuilder.Pokemon;

public class Client extends WebSocketClient
{

	private int KeyID = -5;
	private String challstr = "";
	private boolean loggedIn = false;
	private static final String separator = "\\|"; //escaped character for splitting messages
	private User user = new User();
	private HashMap<String,Room> rooms = new HashMap<String,Room>();
	public String currentroom = "";
	private boolean isBattle = false;
	public String currentTitle = "";
	public Room currentRoom;
	public ChatRoom currentChat;
	private ArrayList<HashMap<String,ArrayList<Format>>> formats = new ArrayList<>();
	public boolean isSearching = false;
	public Format searchingFormat = null;
	
	public Client(URI serverURI) 
	{
		super(serverURI);
	}

	@Override
	public void onClose(int arg0, String arg1, boolean arg2) 
	{
		System.out.println("Closing.");	
	}

	@Override
	public void onError(Exception e) 
	{
		e.printStackTrace();
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
		if(msg.equals(""))
			return;
		System.out.println(msg);	
		if(msg.startsWith("|challstr|"))
			getChallengeString(msg);
		else if(msg.startsWith("|updateuser|"))
			updateUser(msg);
		if(msg.startsWith("|init|"))
			init(msg);
		if(msg.startsWith("|title|"))
			title(msg);
		if(msg.startsWith("|users|"))
			userList(msg);
		if(msg.startsWith("|formats|"))
			formats = Format.parseFormats(msg);
		else if (msg.startsWith(">"))
		{
			currentroom = msg.substring(1);
			if(currentRoom == null)
				return;
			if(rooms.containsKey(currentroom) && !currentRoom.getTitle().toLowerCase().equals(currentroom.toLowerCase()))
				currentRoom = rooms.get(currentroom);
		}
		if(msg.startsWith("||") || (msg.charAt(0) != '|' && msg.charAt(0) != '>'))
			currentRoom.rawMessage(msg.substring(2));
		if(msg.startsWith("|html|") || msg.startsWith("|raw|"))
			currentRoom.htmlMessage(msg.substring(5));
		if(msg.startsWith("|join|") || msg.startsWith("|j|") ||  msg.startsWith("|J|"))
			joinMessage(msg);
		if(msg.startsWith("|leave|") || msg.startsWith("|l|") ||  msg.startsWith("|L|"))
			leaveMessage(msg);
		if(msg.startsWith("|c|") || msg.startsWith("|chat|"))
			chatMessage(msg);
		if(msg.startsWith("|tc|"))
			deltaMessage(msg);
		if(msg.startsWith("|popup|"))
			popup(msg);
		if(msg.startsWith("|pm|"))
			pm(msg);
		if(msg.startsWith("|deinit|"))
			deinit(msg);
		if(msg.startsWith("|battle|")  || msg.startsWith("|b|") ||  msg.startsWith("|B|"))
			battleStarted(msg);
		if(msg.startsWith("|unlink|"))
			//this doesn't really do anything far as I can tell.
			//only sent after ban/warn/mute/lock, which are all broadcast by a message
			//in the form: |c|~|/warn (target)
			unlink(msg);
		if(msg.startsWith("|nametaken|"))
			nameTaken(msg);
		if(msg.startsWith("|usercount|"))
			nameTaken(msg);
	}

	@Override
	public void onOpen(ServerHandshake sh) 
	{
		System.out.println("Connected!");
	}
	
	public void leaveMessage(String msg)
	{
		String[] args = msg.split("|", 3);
		currentRoom.leaveMessage(args[2]);
	}
	
	public void joinMessage(String msg)
	{
		String[] args = msg.split(separator, 3);
		currentRoom.joinMessage(args[2]);
	}
	
	public void init(String init)
	{
		String[] args = init.split(separator, 3);
		//arg 0: empty
		//arg 1: init
		//arg 2: what kind of room it is (chat/battle)
		if(args[2].equals("chat"))
			isBattle = false;
		else isBattle = true;
	}
	
	public void pm(String message)
	{
		String[] args = message.split(separator,5);
		String who = args[2];
		String text = args[3];
		
		Iterator it = rooms.entrySet().iterator();
		while (it.hasNext()) 
		{
			Map.Entry<String,Room> pairs = (Map.Entry<String,Room>)it.next();
			pairs.getValue().privateMessage(user.getName(),who,text);
			it.remove(); // avoids a ConcurrentModificationException]
		}
	}
	
	public void updateSearch(String msg)
	{
		
		String json = msg.split(separator,3)[2];
		JSONObject jsob = (JSONObject)JSONValue.parse(json);
		if((Boolean)jsob.get("searching") == false)
		{
			isSearching = false;
			searchingFormat = null;
			return;
		}
		isSearching = true;
		JSONObject formats = (JSONObject)jsob.get("searching");
		formats.get("format");
		
	}
	
	public void deinit(String msg)
	{
		String which = msg.split(separator, 3)[2];
		rooms.remove(which.toLowerCase());
	}
	
	public void title(String title)
	{
		String[] args = title.split(separator, 3);
		//arg 0: empty
		//arg 1: title literal string
		//arg 2: what the title is
		currentTitle = args[2];
		
		//at this point, if there is a battle, we can call the constructor
		//if it is a chat, we still need userList()
		if(isBattle)
		{
			BattleRoom broom = new BattleRoom(currentroom,currentTitle);
			rooms.put(currentroom, broom);
			currentRoom = broom;
		}
	}
	
	public void userList(String userlist)
	{
		String[] args = userlist.split(separator, 3);
		//every user is here, so this can get massive
		String list = args[2];
		//commas are disallowed in names, so splitting on , won't cause any name glitching
		String[] names = list.split(",");
		ArrayList<String> namelist = new ArrayList<String>();
		for(String nem : names)
		{
			namelist.add(nem);
		}
		//at this point, init() and title() will have been called, so we now have enough
		//data to run a constructor for chatrooms
		ChatRoom cr = new ChatRoom(currentroom,currentTitle, namelist);
		rooms.put(currentroom, cr);
		currentRoom = cr;
		currentChat = cr;
	}
	
	public void unlink(String message)
	{
		
	}
	
	public void userCount(String message)
	{
		
	}
	
	public void battleStarted(String msg)
	{
		String[] args = msg.split(separator, 5);
		String roomid = args[2];
		String user1 = args[3];
		String user2 = args[4];		
		
		currentChat.battleMessage(roomid,user1,user2);
	}
	
	private void getChallengeString(String chstring)
	{
		String[] args = chstring.split(separator,4);
		//use the number of strings so that if the user has a | in the name it doesn't spaz out
		//the args are
		//0: nothing. this is the one before |challstr|
		//1: the challstr string
		//2: the Key ID
		//3: the challenge string
		
		KeyID = Integer.parseInt(args[2]);
		challstr = args[3];		
		
		login("Spheal 4 Ubers","swagswag");
	}
	
	private void updateUser(String upstring)
	{
		String[] args = upstring.split(separator,5); 
		//use the number of strings so that if the user has a | in the name it doesn't spaz out
		args[4] = args[4].replaceAll("\\s", "");
		if(Integer.parseInt(args[3]) == 1)
			user = new User(args[2],false,Integer.parseInt(args[4]));
		else user = new User(args[2],
				true,Integer.parseInt(args[4]));
		System.out.println(user);
	}
	
	private void login(String uname, String pass)
	{
		if(KeyID == -5 || challstr == null)
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
        params.put("challengekeyid", KeyID);
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
	        System.out.println(obj);
	        Object assertion = obj.get("assertion");
	        send("|/trn "+uname+",0,"+assertion);
        }
        catch(IOException ioe)
        {
        	ioe.printStackTrace();
        }
        loggedIn = true;
	}
	
	public void join(String room)
	{
		send("|/join "+room);
	}
	
	public void sendMessage(String room, String message)
	{
		send(room + "|" + message);
	}
	
	public void chatMessage(String msg)
	{
		//annoyingly, some users can have metacharacters in their name
		//so we have to sanitize
		msg.replaceAll("[(\\[{^$|)?*+.\\]]", "\\$1");
		String[] args = msg.split(separator, 4);
		if(args[2].startsWith(" "))
			args[2] = args[2].substring(1);
		
		if(!args[2].equals("~"))
		{
			currentRoom.chatMessage(args[2],args[3]);
			return;
		}
	}
	
	public void deltaMessage(String msg)
	{
		//annoyingly, some users can have metacharacters in their name
		//so we have to sanitize
		msg.replaceAll("[(\\[{^$|)?*+.\\]]", "\\$1");
		String[] args = msg.split("\\|", 5);
		if(args[3].startsWith(" "))
			args[3] = args[3].substring(1);
		currentRoom.chatMessage(args[3],args[4]);
	}
	
	
	
	public ArrayList<HashMap<String,ArrayList<Format>>> getFormats()
	{
		return formats;
	}
	
	void popup(String message)
	{
		JOptionPane.showMessageDialog(null,message.split(separator,3)[2]);
	}
	
	void nameTaken(String msg)
	{
		String[] args = msg.split("\\|", 4);
		if(!args[2].equals(""))
			JOptionPane.showMessageDialog(null,"Name change failed (username: "+args[2]+"): "+args[3]);
		else
			JOptionPane.showMessageDialog(null,"Swag");
	}

	
	public static void main(String[] args) throws URISyntaxException, InterruptedException
	{
		Client ec = new Client(new URI("ws://sim.smogon.com:8000/showdown/websocket"));
		ec.connectBlocking(); //running connect() will cause problems, so wait for it to finish
		
		ec.join("battle-ou-162741245");
		
		System.out.println(ec.currentRoom.getMessages());
	}
	
}