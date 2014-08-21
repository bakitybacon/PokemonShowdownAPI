package messages;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class Room 
{
	static HashMap<String,Color> cache = new HashMap<>();
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
	
	public static Color getNameColor(String name)
	{
		if(cache.containsKey(name))
			return(cache.get(name));
			
		try
		{
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(name.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			String hashtext = bigInt.toString(16);
			System.out.println(hashtext);
			//Now we need to zero pad it if you actually want the full 32 chars.
			while(hashtext.length() < 32)
			{
			  hashtext = "0"+hashtext;
			}
			System.out.println(Long.parseLong(hashtext.substring(4,8), 16) % 50 + 50);
			int s = (int)(Long.parseLong(hashtext.substring(4,8), 16) % 50 + 50);
			System.out.println(Long.parseLong(hashtext.substring(0,4), 16) % 360);
			int h = (int)(Long.parseLong(hashtext.substring(0,4), 16) % 360);
			System.out.println(Long.parseLong(hashtext.substring(8,12), 16) % 20 + 25);
			int l = (int)(Long.parseLong(hashtext.substring(8,12), 16) % 20 + 25);
			cache.put(name,HSLColor.toRGB(h,s,l));
			return HSLColor.toRGB(h,s,l);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Color(0,0,0);
	}
	
	public static BufferedImage resolveAvatar(int av)
	{
		String avatar = av + "";
		while(avatar.length() < 3)
			avatar = "0"+avatar;
		try 
		{
			BufferedImage bi = ImageIO.read(new File("sprites/trainers/"+avatar+".png"));
			return bi;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[]args) throws Exception
	{
		JFrame jf = new JFrame();
		jf.setSize(500,500);
		jf.setDefaultCloseOperation(3);
		jf.setVisible(true);
		JLabel jl = new JLabel("meme");
		for(int i = 1;i < 294; i++)
		{
			jl = new JLabel(new ImageIcon(resolveAvatar(i)));
			jf.add(jl);
			Thread.sleep(500);
		}
	}
}
