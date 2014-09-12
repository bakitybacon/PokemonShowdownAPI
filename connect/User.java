package connect;

import java.util.ArrayList;

public class User 
{
	private String name = "";
	private int avatar = 0;
	private boolean isGuest = false;
	
	private ArrayList<String> pms = new ArrayList<String>();
	
	public User()
	{
		
	}
	public User(String name)
	{
		this.name = name;
	}
	public User(String name, int avatar)
	{
		this.name = name;
		this.avatar = avatar;
	}
	public User(String name, boolean isGuest, int avatar)
	{
		this.name = name;
		this.isGuest = isGuest;
		this.avatar = avatar;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getAvatar()
	{
		return avatar;
	}
	public void setAvatar(int avatar)
	{
		this.avatar = avatar;
	}
	public boolean isGuest()
	{
		return isGuest;
	}
	
	public void receivePM(String who, String contents)
	{
		pms.add(who + ": " + contents);
	}
	
	public String toString()
	{
		return this.getClass().getName() + "[Username="+name+",isGuest="+isGuest+",avatar="+avatar+"]";
	}
}
