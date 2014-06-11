package messages;

import java.util.ArrayList;

public class BattleRoom extends Room
{

	String id;
	ArrayList<String> messages = new ArrayList<String>();
	String title;
	public boolean leavesOn;
	public boolean joinsOn;
	
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
		rawMessage(who + " joined.");
	}

	@Override
	public void leave(String who) 
	{
		rawMessage(who + " left.");
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
		// TODO i don't want to do this now
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

	@Override
	public void privateMessage(String to, String from, String msg) 
	{
		rawMessage("(Private to " + to + ") "+from+":"+msg);
	}
}
