package messages;

import java.util.ArrayList;

public class ChatRoom extends Room
{
	String name;
	ArrayList<String> users = new ArrayList<String>();
	ArrayList<String> messages = new ArrayList<String>();
	String title;
	public boolean leavesOn;
	public boolean joinsOn;
	public boolean battlesOn;
	public boolean renamesOn;
	
	public void add(String message)
	{
		if(messages.size() >= 300)
			messages.remove(messages.get(0));
		messages.add(message);
	}
	
	public ChatRoom(String name, String title, ArrayList<String> users)
	{
		this.name = name;
		this.users = users;
		this.title = title;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getName()
	{
		return name;
	}
	public ArrayList<String> getUsers()
	{
		return users;
	}
	
	public ArrayList<String> getMessages()
	{
		return messages;
	}
	
	public void join(String who)
	{
		users.add(who);
	}
	public void leave(String who)
	{
		users.remove(who);
	}
	public void rawMessage(String message)
	{
		add(message);
	}

	@Override
	public void chatMessage(String user, String message) 
	{
		add(user + ": " + message);
	}

	@Override
	public void deltaMessage(final String user, final String message, final int delta) 
	{
		//i'm not planning on adding timestamps right now
		//this will be a later feature
		chatMessage(user, message);
	}

	@Override
	public void htmlMessage(String message) 
	{
		// TODO i don't want to do this now
		rawMessage(message);
	}
	
	public void joinMessage(String user)
	{
		if(joinsOn)
			rawMessage(user + " joined.");
	}
	public void leaveMessage(String user)
	{
		if(leavesOn)
			rawMessage(user + " left.");
	}
	public void battleMessage(String roomid, String p1, String p2)
	{
		if(battlesOn)
			rawMessage("Battle started between" + p1 + " and " + p2 + ":" + roomid + ".");
	}
	public void renameMessage(String user, String name)
	{
		if(renamesOn)
			rawMessage(user + "changed his/her name to " + name );
	}

	@Override
	public void privateMessage(String to, String from, String msg) 
	{
		rawMessage("(Private to " + to + ") "+from+":"+msg);
	}
}

