package messages;

import java.util.ArrayList;

public abstract class Room 
{
	public abstract ArrayList<String> getMessages();	
	public abstract void join(String who);
	public abstract void leave(String who);
	public abstract void chatMessage(String user, String message);
	public abstract void deltaMessage(String user, String message, int delta);
	public abstract void rawMessage(String message);
	public abstract void htmlMessage(String message);
	public abstract String getTitle();
	public abstract void joinMessage(String who);
	public abstract void leaveMessage(String who);
	public abstract void privateMessage(String to, String from, String msg);
}
